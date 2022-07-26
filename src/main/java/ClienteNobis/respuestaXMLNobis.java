package ClienteNobis;

import java.util.ArrayList;

public class respuestaXMLNobis {

    private String afiliado;
    private String numeroAfi;
    private String dni;
    private String estado;
    private String mensaje;
    private String cod;
    private String num;
    private String Cose_Neto;
    private String Cose_IVA;
    private String Cose_Total;
//    private ArrayList<practicaXMLNobis> practicas;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCose_Neto() {
        return Cose_Neto;
    }

    public void setCose_Neto(String Cose_Neto) {
        this.Cose_Neto = Cose_Neto;
    }

    public String getCose_IVA() {
        return Cose_IVA;
    }

    public void setCose_IVA(String Cose_IVA) {
        this.Cose_IVA = Cose_IVA;
    }

    public String getCose_Total() {
        return Cose_Total;
    }

    public void setCose_Total(String Cose_Total) {
        this.Cose_Total = Cose_Total;
    }

    public String getNumeroAfi() {
        return numeroAfi;
    }

    public void setNumeroAfi(String numeroAfi) {
        this.numeroAfi = numeroAfi;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public respuestaXMLNobis() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(String afiliado) {
        this.afiliado = afiliado;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
//    public ArrayList<practicaXMLNobis> getPracticas() {
//        return practicas;
//    }
//    public void setPracticas(ArrayList<practicaXMLNobis> practicas) {
//        this.practicas = practicas;
//    }
//    public String getNroReferencia() {
//        return NroReferencia;
//    }
//
//    public void setNroReferencia(String NroReferencia) {
//        this.NroReferencia = NroReferencia;
//    }
//    
//     public String getRespuestaAdicional() {
//        return respuestaAdicional;
//    }
//
//    public void setRespuestaAdicional(String respuestaAdicional) {
//        this.respuestaAdicional = respuestaAdicional;
//    }
   

}
