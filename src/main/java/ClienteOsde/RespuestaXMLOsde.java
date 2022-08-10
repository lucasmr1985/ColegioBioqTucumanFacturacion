/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteOsde;

/**
 *
 * @author Lucas Robles
 */
public class RespuestaXMLOsde {
    private Rta rta;
    private String NroReferencia;
    private String TipoTransaccion;
    private Beneficiario beneficiario;
    private Credencial credencial;
    private DetalleProcedimientos detalleProcedimientos;

    public RespuestaXMLOsde() {
    }

    public Rta getRta() {
        return rta;
    }

    public void setRta(Rta rta) {
        this.rta = rta;
    }

    public String getNroReferencia() {
        return NroReferencia;
    }

    public void setNroReferencia(String NroReferencia) {
        this.NroReferencia = NroReferencia;
    }

    public String getTipoTransaccion() {
        return TipoTransaccion;
    }

    public void setTipoTransaccion(String TipoTransaccion) {
        this.TipoTransaccion = TipoTransaccion;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public Credencial getCredencial() {
        return credencial;
    }

    public void setCredencial(Credencial credencial) {
        this.credencial = credencial;
    }

    public DetalleProcedimientos getDetalleProcedimientos() {
        return detalleProcedimientos;
    }

    public void setDetalleProcedimientos(DetalleProcedimientos detalleProcedimientos) {
        this.detalleProcedimientos = detalleProcedimientos;
    }
        
}





