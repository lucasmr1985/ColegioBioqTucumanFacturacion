/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;

public class ConsumirRequest {

    private String n_autorizacion;

    public ConsumirRequest() {
    }

    public ConsumirRequest(String n_autorizacion) {
        this.n_autorizacion = n_autorizacion;
    }

    public String getN_autorizacion() {
        return n_autorizacion;
    }

    public void setN_autorizacion(String n_autorizacion) {
        this.n_autorizacion = n_autorizacion;
    }
}
