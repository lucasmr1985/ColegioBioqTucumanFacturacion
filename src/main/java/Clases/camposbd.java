package Clases;

public class camposbd {

    String numero_afiliado, nombre_afiliado, orden, codigo_analisis, analisis,importe;

    public camposbd(String numero_afiliado, String nombre_afiliado, String orden, String codigo_analisis, String analisis, String importe) {
        this.numero_afiliado = numero_afiliado;
        this.nombre_afiliado = nombre_afiliado;
        this.orden = orden;
        this.codigo_analisis = codigo_analisis;
        this.analisis = analisis;
        this.importe = importe;
    }

    public String getNumero_afiliado() {
        return numero_afiliado;
    }

    public String getNombre_afiliado() {
        return nombre_afiliado;
    }

    public String getOrden() {
        return orden;
    }

    public String getCodigo_analisis() {
        return codigo_analisis;
    }

    public String getAnalisis() {
        return analisis;
    }

    public String getImporte() {
        return importe;
    }

    public void setNumero_afiliado(String numero_afiliado) {
        this.numero_afiliado = numero_afiliado;
    }

    public void setNombre_afiliado(String nombre_afiliado) {
        this.nombre_afiliado = nombre_afiliado;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public void setCodigo_analisis(String codigo_analisis) {
        this.codigo_analisis = codigo_analisis;
    }

    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    
    
}
