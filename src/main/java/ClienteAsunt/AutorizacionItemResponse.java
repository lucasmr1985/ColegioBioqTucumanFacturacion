/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;

public class AutorizacionItemResponse {

    private String estado_practica;
    private String codigo_practica;
    private String nombre_practica;
    private String copago;
    private Integer cantidad_solicitada;
    private Integer cantidad_aprobada;

    public AutorizacionItemResponse() {
    }

    public String getEstado_practica() {
        return estado_practica;
    }

    public void setEstado_practica(String estado_practica) {
        this.estado_practica = estado_practica;
    }

    public String getCodigo_practica() {
        return codigo_practica;
    }

    public void setCodigo_practica(String codigo_practica) {
        this.codigo_practica = codigo_practica;
    }

    public String getNombre_practica() {
        return nombre_practica;
    }

    public void setNombre_practica(String nombre_practica) {
        this.nombre_practica = nombre_practica;
    }

    public String getCopago() {
        return copago;
    }

    public void setCopago(String copago) {
        this.copago = copago;
    }

    public Integer getCantidad_solicitada() {
        return cantidad_solicitada;
    }

    public void setCantidad_solicitada(Integer cantidad_solicitada) {
        this.cantidad_solicitada = cantidad_solicitada;
    }

    public Integer getCantidad_aprobada() {
        return cantidad_aprobada;
    }

    public void setCantidad_aprobada(Integer cantidad_aprobada) {
        this.cantidad_aprobada = cantidad_aprobada;
    }
}