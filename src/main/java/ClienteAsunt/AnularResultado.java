/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;

public class AnularResultado {

    private boolean exito;
    private AnularResponse respuestaOk;
    private AnularErrorResponse respuestaError;
    private String rawResponse;
    private int statusCode;

    public AnularResultado() {
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public AnularResponse getRespuestaOk() {
        return respuestaOk;
    }

    public void setRespuestaOk(AnularResponse respuestaOk) {
        this.respuestaOk = respuestaOk;
    }

    public AnularErrorResponse getRespuestaError() {
        return respuestaError;
    }

    public void setRespuestaError(AnularErrorResponse respuestaError) {
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
