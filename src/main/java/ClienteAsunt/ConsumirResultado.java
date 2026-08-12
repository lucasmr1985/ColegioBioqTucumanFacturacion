/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;

public class ConsumirResultado {

    private boolean exito;
    private ConsumirResponse respuestaOk;
    private ConsumirErrorResponse respuestaError;
    private String rawResponse;
    private int statusCode;

    public ConsumirResultado() {
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public ConsumirResponse getRespuestaOk() {
        return respuestaOk;
    }

    public void setRespuestaOk(ConsumirResponse respuestaOk) {
        this.respuestaOk = respuestaOk;
    }

    public ConsumirErrorResponse getRespuestaError() {
        return respuestaError;
    }

    public void setRespuestaError(ConsumirErrorResponse respuestaError) {
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
