/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClienteAsunt;

public class ElegibilidadErrorResponse {

    private String tipo_identificacion_afiliado;
    private String identificacion_afiliado;
    private String mensaje;

    public ElegibilidadErrorResponse() {
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}