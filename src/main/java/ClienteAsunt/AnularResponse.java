/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;

public class AnularResponse {

    private String estado;
    private String n_autorizacion;
    private Long n_anulacion;

    public AnularResponse() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getN_autorizacion() {
        return n_autorizacion;
    }

    public void setN_autorizacion(String n_autorizacion) {
        this.n_autorizacion = n_autorizacion;
    }

    public Long getN_anulacion() {
        return n_anulacion;
    }

    public void setN_anulacion(Long n_anulacion) {
        this.n_anulacion = n_anulacion;
    }
}
