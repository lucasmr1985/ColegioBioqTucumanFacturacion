/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;

import java.util.List;

public class ConsultarResponse {

    private String n_autorizacion;
    private String nombre_afiliado;
    private String nombre_prestador;
    private List<AtencionPracticaResponse> practicas;

    public ConsultarResponse() {
    }

    public String getN_autorizacion() {
        return n_autorizacion;
    }

    public void setN_autorizacion(String n_autorizacion) {
        this.n_autorizacion = n_autorizacion;
    }

    public String getNombre_afiliado() {
        return nombre_afiliado;
    }

    public void setNombre_afiliado(String nombre_afiliado) {
        this.nombre_afiliado = nombre_afiliado;
    }

    public String getNombre_prestador() {
        return nombre_prestador;
    }

    public void setNombre_prestador(String nombre_prestador) {
        this.nombre_prestador = nombre_prestador;
    }

    public List<AtencionPracticaResponse> getPracticas() {
        return practicas;
    }

    public void setPracticas(List<AtencionPracticaResponse> practicas) {
        this.practicas = practicas;
    }
}
