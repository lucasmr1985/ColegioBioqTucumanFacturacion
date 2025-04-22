
package ClienteSwissMedicalApi;

/**
 *
 * @author Lucas Robles
 */
public class DetalleResponse {
    private long transac;
    private double canti;
    private double recha;
    private String denoItem;
    private double valorCopa;

    public DetalleResponse() {
    }

    public long getTransac() {
        return transac;
    }

    public void setTransac(long transac) {
        this.transac = transac;
    }

    public double getCanti() {
        return canti;
    }

    public void setCanti(double canti) {
        this.canti = canti;
    }

    public double getRecha() {
        return recha;
    }

    public void setRecha(double recha) {
        this.recha = recha;
    }

    public String getDenoItem() {
        return denoItem;
    }

    public void setDenoItem(String denoItem) {
        this.denoItem = denoItem;
    }

    public double getValorCopa() {
        return valorCopa;
    }

    public void setValorCopa(double valorCopa) {
        this.valorCopa = valorCopa;
    }
    
    public void muestraRespuesta(){
    
        System.out.println("Transac=" + this.getTransac());
        System.out.println("Cant=" + this.getCanti());
        System.out.println("Recha=" + this.getRecha());
        System.out.println("DenoItem=" + this.getDenoItem());
        System.out.println("ValorCopa=" + this.getValorCopa());        
    
    }
}
