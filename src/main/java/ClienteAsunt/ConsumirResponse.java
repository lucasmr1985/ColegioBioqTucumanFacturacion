/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;

import java.util.List;

public class ConsumirResponse {

    private String n_autorizacion;
    private String nombre_afiliado;
    private String nombre_prestador;
    private List<AtencionPracticaResponse> practicas;
    private String nombre_efector;
    private String mensaje;

    public ConsumirResponse() {
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

    public String getNombre_efector() {
        return nombre_efector;
    }

    public void setNombre_efector(String nombre_efector) {
        this.nombre_efector = nombre_efector;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
