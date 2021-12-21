
package Clases.ClienteOspe;

import java.util.ArrayList;

public class respuestaXMLospe {

    private String respuesta;
    private String codigo;
    private String afiliado;
    private String dni;
    private String mensaje;
    private String respuestaAdicional;
    private String NroReferencia;
    private ArrayList<practicaXMLospe> practicas;
        
    
    public ArrayList<practicaXMLospe> getPracticas() {
        return practicas;
    }

    public void setPracticas(ArrayList<practicaXMLospe> practicas) {
        this.practicas = practicas;
    }
    
    public respuestaXMLospe() {
    }

    public String getNroReferencia() {
        return NroReferencia;
    }

    public void setNroReferencia(String NroReferencia) {
        this.NroReferencia = NroReferencia;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
     public String getRespuestaAdicional() {
        return respuestaAdicional;
    }

    public void setRespuestaAdicional(String respuestaAdicional) {
        this.respuestaAdicional = respuestaAdicional;
    }
}
