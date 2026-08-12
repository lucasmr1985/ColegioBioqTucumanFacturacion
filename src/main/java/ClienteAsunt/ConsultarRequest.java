/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;

public class ConsultarRequest {

    private String tipo_identificacion;
    private String identificacion_afiliado;
    private String n_autorizacion;
    private String cuit_efector;

    public ConsultarRequest() {
    }

    public ConsultarRequest(String tipo_identificacion, String identificacion_afiliado, String n_autorizacion, String cuit_efector) {
        this.tipo_identificacion = tipo_identificacion;
        this.identificacion_afiliado = identificacion_afiliado;
        this.n_autorizacion = n_autorizacion;
        this.cuit_efector = cuit_efector;
    }

    public String getTipo_identificacion() {
        return tipo_identificacion;
    }

    public void setTipo_identificacion(String tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }

    public String getIdentificacion_afiliado() {
        return identificacion_afiliado;
    }

    public void setIdentificacion_afiliado(String identificacion_afiliado) {
        this.identificacion_afiliado = identificacion_afiliado;
    }

    public String getN_autorizacion() {
        return n_autorizacion;
    }

    public void setN_autorizacion(String n_autorizacion) {
        this.n_autorizacion = n_autorizacion;
    }

    public String getCuit_efector() {
        return cuit_efector;
    }

    public void setCuit_efector(String cuit_efector) {
        this.cuit_efector = cuit_efector;
    }
}