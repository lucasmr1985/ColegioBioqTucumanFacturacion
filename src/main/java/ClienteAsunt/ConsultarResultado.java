/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;

public class ConsultarResultado {

    private boolean exito;
    private ConsultarResponse respuestaOk;
    private ConsultarErrorResponse respuestaError;
    private String rawResponse;
    private int statusCode;

    public ConsultarResultado() {
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public ConsultarResponse getRespuestaOk() {
        return respuestaOk;
    }

    public void setRespuestaOk(ConsultarResponse respuestaOk) {
        this.respuestaOk = respuestaOk;
    }

    public ConsultarErrorResponse getRespuestaError() {
        return respuestaError;
    }

    public void setRespuestaError(ConsultarErrorResponse respuestaError) {
        this.respuestaError = respuestaError;
    }

    public String getRawResponse() {
        return rawResponse;
    }

    public void setRawResponse(String rawResponse) {
        this.rawResponse = rawResponse;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
