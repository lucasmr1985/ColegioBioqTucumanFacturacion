/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class AsuntValidadorClient {

    private static final String ENDPOINT_AUTORIZAR = "https://asunt.bymovi.com/api/validador_prestadores/autorizar";
    private static final String ENDPOINT_ELEGIBILIDAD = "https://asunt.bymovi.com/api/validador_prestadores/elegibilidad";
    private static final String ENDPOINT_ANULAR = "https://asunt.bymovi.com/api/validador_prestadores/atenciones/%s/anular";
    private static final String ENDPOINT_REPORTE = "https://asunt.bymovi.com/api/validador_prestadores/atenciones/%s/reporte";
    private static final String ENDPOINT_CONSULTAR = "https://asunt.bymovi.com/api/validador_prestadores/atenciones/consultar";
    private static final String ENDPOINT_CONSUMIR = "https://asunt.bymovi.com/api/validador_prestadores/atenciones/consumir";

    private final String token;
    private final Gson gson;

    public AsuntValidadorClient(String token) {
        this.token = token;
        this.gson = new GsonBuilder().create();
    }

    public AutorizarResponse autorizar(AutorizarRequest request) throws IOException, AsuntApiException {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(ENDPOINT_AUTORIZAR);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(30000);
            connection.setDoOutput(true);

            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("X-Authorization", token);

            String jsonRequest = gson.toJson(request);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonRequest.getBytes(StandardCharsets.UTF_8);
                os.write(input);
                os.flush();
            }

            int statusCode = connection.getResponseCode();
            String responseBody = readResponseBody(
                    statusCode >= 200 && statusCode < 300 ? connection.getInputStream() : connection.getErrorStream()
            );

            if (statusCode >= 200 && statusCode < 300) {
                return gson.fromJson(responseBody, AutorizarResponse.class);
            }

            ApiErrorResponse errorResponse = null;

            errorResponse = gson.fromJson(responseBody, ApiErrorResponse.class);

            throw new AsuntApiException(statusCode, responseBody, errorResponse);

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

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }

        return response.toString();
    }

    public ElegibilidadResultado elegibilidad(ElegibilidadRequest request) throws IOException, AsuntApiException {
        HttpURLConnection connection = null;

        try {
            String urlConParametros = ENDPOINT_ELEGIBILIDAD
                    + "?tipo_identificacion_afiliado=" + encode(request.getTipo_identificacion_afiliado())
                    + "&identificacion_afiliado=" + encode(request.getIdentificacion_afiliado());

            URL url = new URL(urlConParametros);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(30000);
            connection.setDoOutput(false);

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("X-Authorization", token);

            int statusCode = connection.getResponseCode();
            String responseBody = readResponseBody(
                    statusCode >= 200 && statusCode < 300
                            ? connection.getInputStream()
                            : connection.getErrorStream()
            );

            if (responseBody == null || responseBody.trim().isEmpty()) {
                throw new AsuntApiException(statusCode, "Respuesta vacía del servicio.", null);
            }

            String body = responseBody.trim();

            if (!body.startsWith("{")) {
                throw new AsuntApiException(statusCode, responseBody, null);
            }

            JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();

            ElegibilidadResultado resultado = new ElegibilidadResultado();
            resultado.setStatusCode(statusCode);
            resultado.setRawResponse(responseBody);

            if (jsonObject.has("data")) {
                ElegibilidadResponse ok = gson.fromJson(responseBody, ElegibilidadResponse.class);
                resultado.setExito(true);
                resultado.setRespuestaOk(ok);
                return resultado;
            }

            if (jsonObject.has("mensaje")) {
                ElegibilidadErrorResponse error = gson.fromJson(responseBody, ElegibilidadErrorResponse.class);
                resultado.setExito(false);
                resultado.setRespuestaError(error);
                return resultado;
            }

            throw new AsuntApiException(statusCode, responseBody, null);

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public AnularResultado anular(String numeroAutorizacion) throws IOException, AsuntApiException {
        HttpURLConnection connection = null;

        try {
            String urlFinal = String.format(ENDPOINT_ANULAR, encode(numeroAutorizacion));

            URL url = new URL(urlFinal);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(30000);
            connection.setDoOutput(false);

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("X-Authorization", token);

            int statusCode = connection.getResponseCode();
            String responseBody = readResponseBody(
                    statusCode >= 200 && statusCode < 300
                            ? connection.getInputStream()
                            : connection.getErrorStream()
            );

            if (responseBody == null || responseBody.trim().isEmpty()) {
                throw new AsuntApiException(statusCode, "Respuesta vacía del servicio.", null);
            }

            String body = responseBody.trim();

            if (!body.startsWith("{")) {
                throw new AsuntApiException(statusCode, responseBody, null);
            }

            com.google.gson.JsonObject jsonObject
                    = new com.google.gson.JsonParser().parse(body).getAsJsonObject();

            AnularResultado resultado = new AnularResultado();
            resultado.setStatusCode(statusCode);
            resultado.setRawResponse(responseBody);

            if (jsonObject.has("estado") && jsonObject.has("n_anulacion")) {
                AnularResponse ok = gson.fromJson(responseBody, AnularResponse.class);
                resultado.setExito(true);
                resultado.setRespuestaOk(ok);
                return resultado;
            }

            if (jsonObject.has("error")) {
                AnularErrorResponse error = gson.fromJson(responseBody, AnularErrorResponse.class);
                resultado.setExito(false);
                resultado.setRespuestaError(error);
                return resultado;
            }

            throw new AsuntApiException(statusCode, responseBody, null);

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

        public ReporteResultado reporte(String numeroAutorizacion) throws IOException, AsuntApiException {
        HttpURLConnection connection = null;

        try {
            String urlFinal = String.format(ENDPOINT_REPORTE, encode(numeroAutorizacion));

            URL url = new URL(urlFinal);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(30000);
            connection.setDoOutput(false);

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("X-Authorization", token);

            int statusCode = connection.getResponseCode();
            String responseBody = readResponseBody(
                    statusCode >= 200 && statusCode < 300
                            ? connection.getInputStream()
                            : connection.getErrorStream()
            );

            if (responseBody == null || responseBody.trim().isEmpty()) {
                throw new AsuntApiException(statusCode, "Respuesta vacía del servicio.", null);
            }

            String body = responseBody.trim();

            if (!body.startsWith("{")) {
                throw new AsuntApiException(statusCode, responseBody, null);
            }

            com.google.gson.JsonObject jsonObject
                    = new com.google.gson.JsonParser().parse(body).getAsJsonObject();

            ReporteResultado resultado = new ReporteResultado();
            resultado.setStatusCode(statusCode);
            resultado.setRawResponse(responseBody);

            if (jsonObject.has("link")) {
                ReporteResponse ok = gson.fromJson(responseBody, ReporteResponse.class);
                resultado.setExito(true);
                resultado.setRespuestaOk(ok);
                return resultado;
            }

            if (jsonObject.has("error")) {
                ReporteErrorResponse error = gson.fromJson(responseBody, ReporteErrorResponse.class);
                resultado.setExito(false);
                resultado.setRespuestaError(error);
                return resultado;
            }

            throw new AsuntApiException(statusCode, responseBody, null);

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
   
    
    

    public void descargarReportePdfYAbrir(String urlPdf, String numAutorizacion) throws IOException, AsuntApiException {
        HttpURLConnection connection = null;
        String folder = "C:\\Descargas-CBT\\" + numAutorizacion + ".pdf";
        try {
            URL url = new URL(urlPdf);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(30000);
            connection.setDoOutput(false);

            connection.setRequestProperty("X-Authorization", token);
            connection.setRequestProperty("Accept", "application/pdf");

            int statusCode = connection.getResponseCode();

            if (statusCode < 200 || statusCode >= 300) {
                String responseBody = readResponseBody(connection.getErrorStream());
                throw new AsuntApiException(statusCode, responseBody, null);
            }

            File archivo = new File(folder);

            try (InputStream inputStream = connection.getInputStream();
                    FileOutputStream fileOutputStream = new FileOutputStream(archivo)) {

                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                fileOutputStream.flush();
            }

            if (!Desktop.isDesktopSupported()) {
                throw new IOException("Desktop no está soportado en este entorno.");
            }

            Desktop desktop = Desktop.getDesktop();

            if (!desktop.isSupported(Desktop.Action.OPEN)) {
                throw new IOException("La acción OPEN no está soportada en este entorno.");
            }

            desktop.open(archivo);

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
    public ConsultarResultado consultar(ConsultarRequest request) throws IOException, AsuntApiException {
    HttpURLConnection connection = null;

    try {
        String urlConParametros = ENDPOINT_CONSULTAR
                + "?tipo_identificacion=" + encode(request.getTipo_identificacion())
                + "&identificacion_afiliado=" + encode(request.getIdentificacion_afiliado())
                + "&n_autorizacion=" + encode(request.getN_autorizacion())
                + "&cuit_efector=" + encode(request.getCuit_efector());

        URL url = new URL(urlConParametros);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(30000);
        connection.setDoOutput(false);

        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("X-Authorization", token);

        int statusCode = connection.getResponseCode();
        String responseBody = readResponseBody(
                statusCode >= 200 && statusCode < 300
                        ? connection.getInputStream()
                        : connection.getErrorStream()
        );

        if (responseBody == null || responseBody.trim().isEmpty()) {
            throw new AsuntApiException(statusCode, "Respuesta vacía del servicio.", null);
        }

        String body = responseBody.trim();

        if (!body.startsWith("{")) {
            throw new AsuntApiException(statusCode, responseBody, null);
        }

        com.google.gson.JsonObject jsonObject =
                new com.google.gson.JsonParser().parse(body).getAsJsonObject();

        ConsultarResultado resultado = new ConsultarResultado();
        resultado.setStatusCode(statusCode);
        resultado.setRawResponse(responseBody);

        if (jsonObject.has("n_autorizacion") && jsonObject.has("practicas")) {
            ConsultarResponse ok = gson.fromJson(responseBody, ConsultarResponse.class);
            resultado.setExito(true);
            resultado.setRespuestaOk(ok);
            return resultado;
        }

        if (jsonObject.has("mensaje") || jsonObject.has("error")) {
            ConsultarErrorResponse error = gson.fromJson(responseBody, ConsultarErrorResponse.class);
            resultado.setExito(false);
            resultado.setRespuestaError(error);
            return resultado;
        }

        throw new AsuntApiException(statusCode, responseBody, null);

    } finally {
        if (connection != null) {
            connection.disconnect();
        }
    }
}
    
    public ConsumirResultado consumir(ConsumirRequest request) throws IOException, AsuntApiException {
    HttpURLConnection connection = null;

    try {
        URL url = new URL(ENDPOINT_CONSUMIR);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(30000);
        connection.setDoOutput(true);

        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("X-Authorization", token);

        String jsonRequest = gson.toJson(request);

        try (java.io.OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonRequest.getBytes(java.nio.charset.StandardCharsets.UTF_8);
            os.write(input);
            os.flush();
        }

        int statusCode = connection.getResponseCode();
        String responseBody = readResponseBody(
                statusCode >= 200 && statusCode < 300
                        ? connection.getInputStream()
                        : connection.getErrorStream()
        );

        if (responseBody == null || responseBody.trim().isEmpty()) {
            throw new AsuntApiException(statusCode, "Respuesta vacía del servicio.", null);
        }

        String body = responseBody.trim();

        if (!body.startsWith("{")) {
            throw new AsuntApiException(statusCode, responseBody, null);
        }

        com.google.gson.JsonObject jsonObject =
                new com.google.gson.JsonParser().parse(body).getAsJsonObject();

        ConsumirResultado resultado = new ConsumirResultado();
        resultado.setStatusCode(statusCode);
        resultado.setRawResponse(responseBody);

        if (jsonObject.has("n_autorizacion") && jsonObject.has("practicas")) {
            ConsumirResponse ok = gson.fromJson(responseBody, ConsumirResponse.class);
            resultado.setExito(true);
            resultado.setRespuestaOk(ok);
            return resultado;
        }

        if (jsonObject.has("mensaje") || jsonObject.has("error")) {
            ConsumirErrorResponse error = gson.fromJson(responseBody, ConsumirErrorResponse.class);
            resultado.setExito(false);
            resultado.setRespuestaError(error);
            return resultado;
        }

        throw new AsuntApiException(statusCode, responseBody, null);

    } finally {
        if (connection != null) {
            connection.disconnect();
        }
    }
}
    

    private String encode(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, "UTF-8");
    }
}
