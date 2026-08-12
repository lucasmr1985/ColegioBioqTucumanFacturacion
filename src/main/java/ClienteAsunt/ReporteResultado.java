/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;

public class ReporteResultado {

    private boolean exito;
    private ReporteResponse respuestaOk;
    private ReporteErrorResponse respuestaError;
    private String rawResponse;
    private int statusCode;

    public ReporteResultado() {
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public ReporteResponse getRespuestaOk() {
        return respuestaOk;
    }

    public void setRespuestaOk(ReporteResponse respuestaOk) {
        this.respuestaOk = respuestaOk;
    }

    public ReporteErrorResponse getRespuestaError() {
        return respuestaError;
    }

    public void setRespuestaError(ReporteErrorResponse respuestaError) {
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
