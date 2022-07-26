
package ClienteNobis;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DocumentElement")
public class RespuestaInsertarAutorizacionAmb {
    
    @XmlElement(name = "Mensaje")
    private String Mensaje;    
    @XmlElement(name = "Estado")
    private String Estado;    
    @XmlElement(name = "Cod")
    private String Cod;
    @XmlElement(name = "Num")
    private String Num;
    @XmlElement(name = "Cose_Neto")
    private String Cose_Neto;
    @XmlElement(name = "Cose_IVA")
    private String Cose_IVA;
    @XmlElement(name = "Cose_Total")
    private String Cose_Total;

    public RespuestaInsertarAutorizacionAmb() {
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getCod() {
        return Cod;
    }

    public void setCod(String Cod) {
        this.Cod = Cod;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String Num) {
        this.Num = Num;
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
    
    
    
}
