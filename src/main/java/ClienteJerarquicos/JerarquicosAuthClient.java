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
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Cliente de autenticación OAuth2 (flujo "Client Credentials") para el
 * "API External Web Service" de Jerárquicos Salud.
 *
 * Pide el access_token una única vez y lo reutiliza mientras siga vigente
 * (según "expires_in"), renovándolo automáticamente cuando expira.
 */
public class JerarquicosAuthClient {

    // TODO: reemplazar por las credenciales reales enviadas por Jerárquicos Salud
    // (herramientasdigitales@jerarquicos.com) apenas estén disponibles.
    private static final String CLIENT_ID = "336DD3B4-0714-473E-9DA6-BE6DA13CF63D";
    private static final String CLIENT_SECRET = "i4Jzuv78ei0eLgtUZedSup3dltnXXR3v";
    private static final String SCOPE = null; // opcional; ej: "audience-external-apigateway-01"

    private static final String URL_TOKEN_PRUEBAS = "https://serviciosstg.jerarquicos.com:10712/auth/External/token";
    private static final String URL_TOKEN_PRODUCCION = "https://apis.jerarquicos.com:10712/auth/External/token";

    // Cambiar a true para apuntar al ambiente productivo.
    private static final boolean AMBIENTE_PRODUCCION = true;

    // Margen de seguridad para renovar el token un poco antes de que venza,
    // y así evitar que expire en pleno viaje de una request.
    private static final long MARGEN_EXPIRACION_MS = 10_000;

    private final Gson gson = new Gson();

    private String accessTokenCacheado;
    private long expiraEnMillis;

    public JerarquicosAuthClient() {
    }

    public static String getUrlBaseApi() {
        return AMBIENTE_PRODUCCION
                ? "https://apis.jerarquicos.com:10712/api/ExternalWebService/"
                : "https://serviciosstg.jerarquicos.com:10712/api/ExternalWebService/";
    }

    /**
     * Devuelve un access_token vigente, pidiendo uno nuevo si todavía no se
     * generó o si el que teníamos cacheado ya venció.
     */
    public synchronized String obtenerAccessToken() throws IOException, JerarquicosApiException {
        if (accessTokenCacheado == null || System.currentTimeMillis() >= expiraEnMillis) {
            TokenResponse token = solicitarNuevoToken();
            accessTokenCacheado = token.getAccessToken();
            expiraEnMillis = System.currentTimeMillis()
                    + Math.max(0, token.getExpiresIn() * 1000L - MARGEN_EXPIRACION_MS);
        }
        return accessTokenCacheado;
    }

    /**
     * Header "Authorization" listo para agregar a cualquier request a los
     * servicios de Socios (ValidarToken, ValidarCobertura, etc.).
     */
    public String obtenerAuthorizationHeader() throws IOException, JerarquicosApiException {
        return "Bearer " + obtenerAccessToken();
    }

    private TokenResponse solicitarNuevoToken() throws IOException, JerarquicosApiException {
        HttpURLConnection connection = null;
        try {
            String urlToken = AMBIENTE_PRODUCCION ? URL_TOKEN_PRODUCCION : URL_TOKEN_PRUEBAS;

            URL url = new URL(urlToken);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(30000);
            connection.setDoOutput(true);

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept", "application/json");

            String body = "grant_type=client_credentials"
                    + "&client_id=" + encode(CLIENT_ID)
                    + "&client_secret=" + encode(CLIENT_SECRET)
                    + (SCOPE != null && !SCOPE.trim().isEmpty() ? "&scope=" + encode(SCOPE) : "");

            try (OutputStream os = connection.getOutputStream()) {
                os.write(body.getBytes(StandardCharsets.UTF_8));
                os.flush();
            }

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

            return gson.fromJson(responseBody, TokenResponse.class);

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

    private String encode(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, "UTF-8");
    }
}
