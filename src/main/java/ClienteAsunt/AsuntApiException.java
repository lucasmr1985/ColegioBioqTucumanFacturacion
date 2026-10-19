/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;

import ClienteAsunt.ApiErrorResponse;

public class AsuntApiException extends java.lang.Exception {

    private final int statusCode;
    private final String responseBody;
    private final ApiErrorResponse errorResponse;

    public AsuntApiException(int statusCode, String responseBody, ApiErrorResponse errorResponse) {
        this(null, statusCode, responseBody, errorResponse);
    }

    public AsuntApiException(String contexto, int statusCode, String responseBody, ApiErrorResponse errorResponse) {
        super(buildMessage(contexto, statusCode, responseBody, errorResponse));
        this.statusCode = statusCode;
        this.responseBody = responseBody;
        this.errorResponse = errorResponse;
    }

    private static String buildMessage(String contexto, int statusCode, String responseBody, ApiErrorResponse errorResponse) {
        StringBuilder sb = new StringBuilder();
        sb.append("Error al invocar ASUNT");
        if (contexto != null && !contexto.isEmpty()) {
            sb.append(" (").append(contexto).append(")");
        }
        sb.append(". HTTP ").append(statusCode);

        if (errorResponse != null && errorResponse.getError() != null) {
            sb.append(" - ").append(errorResponse.getError());
        }

        if (responseBody != null && !responseBody.trim().isEmpty()) {
            String body = responseBody.trim();
            if (body.startsWith("<")) {
                sb.append(" - El servidor devolvió una página de error HTML (error interno de ASUNT)");
            } else {
                sb.append(" - Respuesta: ").append(body);
            }
        }

        return sb.toString();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public ApiErrorResponse getErrorResponse() {
        return errorResponse;
    }
}