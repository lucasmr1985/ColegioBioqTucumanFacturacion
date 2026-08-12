/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;

public class ElegibilidadResultado {

    private boolean exito;
    private ElegibilidadResponse respuestaOk;
    private ElegibilidadErrorResponse respuestaError;
    private String rawResponse;
    private int statusCode;

    public ElegibilidadResultado() {
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public ElegibilidadResponse getRespuestaOk() {
        return respuestaOk;
    }

    public void setRespuestaOk(ElegibilidadResponse respuestaOk) {
        this.respuestaOk = respuestaOk;
    }

    public ElegibilidadErrorResponse getRespuestaError() {
        return respuestaError;
    }

    public void setRespuestaError(ElegibilidadErrorResponse respuestaError) {
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