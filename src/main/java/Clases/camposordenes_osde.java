

package Clases;


public class camposordenes_osde {
    String numero_afiliado, nombre_afiliado, orden, codigo_analisis, analisis,importe,codigo_fac,fecha;

    public String getNumero_afiliado() {
        return numero_afiliado;
    }

    public void setNumero_afiliado(String numero_afiliado) {
        this.numero_afiliado = numero_afiliado;
    }

    public String getNombre_afiliado() {
        return nombre_afiliado;
    }

    public void setNombre_afiliado(String nombre_afiliado) {
        this.nombre_afiliado = nombre_afiliado;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getCodigo_analisis() {
        return codigo_analisis;
    }

    public void setCodigo_analisis(String codigo_analisis) {
        this.codigo_analisis = codigo_analisis;
    }

    public String getAnalisis() {
        return analisis;
    }

    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getCodigo_fac() {
        return codigo_fac;
    }

    public void setCodigo_fac(String codigo_fac) {
        this.codigo_fac = codigo_fac;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public camposordenes_osde(String numero_afiliado, String nombre_afiliado, String orden, String codigo_analisis, String analisis, String importe, String codigo_fac, String fecha) {
        this.numero_afiliado = numero_afiliado;
        this.nombre_afiliado = nombre_afiliado;
        this.orden = orden;
        this.codigo_analisis = codigo_analisis;
        this.analisis = analisis;
        this.importe = importe;
        this.codigo_fac = codigo_fac;
        this.fecha = fecha;
    }

}