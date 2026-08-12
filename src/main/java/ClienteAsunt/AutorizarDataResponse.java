/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;

import java.util.List;

public class AutorizarDataResponse {

    private String estado;
    private String tipo_identificacion_afiliado;
    private String identificacion_afiliado;
    private String plan;
    private Long n_autorizacion;
    private String total_copago;
    private List<AutorizacionItemResponse> autorizaciones;

    public AutorizarDataResponse() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo_identificacion_afiliado() {
        return tipo_identificacion_afiliado;
    }

    public void setTipo_identificacion_afiliado(String tipo_identificacion_afiliado) {
        this.tipo_identificacion_afiliado = tipo_identificacion_afiliado;
    }

    public String getIdentificacion_afiliado() {
        return identificacion_afiliado;
    }

    public void setIdentificacion_afiliado(String identificacion_afiliado) {
        this.identificacion_afiliado = identificacion_afiliado;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Long getN_autorizacion() {
        return n_autorizacion;
    }

    public void setN_autorizacion(Long n_autorizacion) {
        this.n_autorizacion = n_autorizacion;
    }

    public String getTotal_copago() {
        return total_copago;
    }

    public void setTotal_copago(String total_copago) {
        this.total_copago = total_copago;
    }

    public List<AutorizacionItemResponse> getAutorizaciones() {
        return autorizaciones;
    }

    public void setAutorizaciones(List<AutorizacionItemResponse> autorizaciones) {
        this.autorizaciones = autorizaciones;
    }
}
