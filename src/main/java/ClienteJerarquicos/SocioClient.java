/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteJerarquicos;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Cliente de los servicios "Socio" del "API External Web Service" de
 * Jerárquicos Salud (requiere el access_token de {@link JerarquicosAuthClient}).
 */
public class SocioClient {

    private static final String ENDPOINT_VALIDAR_COBERTURA = "Socio/ValidarCobertura";

    private final Gson gson = new Gson();

    public SocioClient() {
    }

    /**
     * GET Socio/ValidarCobertura por NumeroSocio + OrdenSocio.
     *
     * @param fechaReferencia formato YYYY-MM-DD
     * @param numeroSocio número del socio según figura en la credencial
     * @param ordenSocio orden del socio (00 titular, 01/02/... adherentes)
     * @param tokenSocio cadena alfanumérica de 6 caracteres que el socio le
     * dicta al profesional, reemplazando su firma ológrafa (no confundir con
     * el access_token OAuth2). Opcional: puede ir null/vacío.
     * @param accessToken access_token OAuth2 vigente (ver JerarquicosAuthClient),
     * se envía en el header Authorization, no como parámetro de la consulta.
     */
    public SocioResponse validarCoberturaPorSocio(String fechaReferencia, int numeroSocio, int ordenSocio,
            String tokenSocio, String accessToken) throws IOException, JerarquicosApiException {
        String query = "FechaReferencia=" + encode(fechaReferencia)
                + "&NumeroSocio=" + numeroSocio
                + "&OrdenSocio=" + ordenSocio
                + tokenSocioQueryParam(tokenSocio);
        return validarCobertura(query, accessToken);
    }

    /**
     * GET Socio/ValidarCobertura por tipo y número de documento.
     *
     * @param fechaReferencia formato YYYY-MM-DD
     * @param idTipoDocumento 1=DNI, 2=LE, 3=LC, 4=CI, 5=PASS
     * @param numeroDocumento número de documento del socio
     * @param tokenSocio cadena alfanumérica de 6 caracteres que el socio le
     * dicta al profesional, reemplazando su firma ológrafa (no confundir con
     * el access_token OAuth2). Opcional: puede ir null/vacío.
     * @param accessToken access_token OAuth2 vigente (ver JerarquicosAuthClient),
     * se envía en el header Authorization, no como parámetro de la consulta.
     */
    public SocioResponse validarCoberturaPorDocumento(String fechaReferencia, int idTipoDocumento,
            String numeroDocumento, String tokenSocio, String accessToken) throws IOException, JerarquicosApiException {
        String query = "FechaReferencia=" + encode(fechaReferencia)
                + "&IdTipoDocumento=" + idTipoDocumento
                + "&NumeroDocumento=" + encode(numeroDocumento)
                + tokenSocioQueryParam(tokenSocio);
        return validarCobertura(query, accessToken);
    }

    private String tokenSocioQueryParam(String tokenSocio) throws IOException {
        return (tokenSocio != null && !tokenSocio.trim().isEmpty())
                ? "&Token=" + encode(tokenSocio.trim())
                : "";
    }

    private SocioResponse validarCobertura(String query, String accessToken) throws IOException, JerarquicosApiException {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(JerarquicosAuthClient.getUrlBaseApi() + ENDPOINT_VALIDAR_COBERTURA + "?" + query);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(30000);
            connection.setDoOutput(false);

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            int statusCode = connection.getResponseCode();
            String responseBody = readResponseBody(
                    statusCode >= 200 && statusCode < 300 ? connection.getInputStream() : connection.getErrorStream()
            );

            if (statusCode < 200 || statusCode >= 300) {
                ApiErrorResponse errorResponse = null;
                try {
                    errorResponse = gson.fromJson(responseBody, ApiErrorResponse.class);
                } catch (Exception ignore) {
                    // la respuesta de error no vino en el formato {title, detail} esperado
                }
                throw new JerarquicosApiException(statusCode, responseBody, errorResponse);
            }

            return gson.fromJson(responseBody, SocioResponse.class);

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private String readResponseBody(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return "";
        }
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }

    private String encode(String value) throws IOException {
        return URLEncoder.encode(value, "UTF-8");
    }
}
