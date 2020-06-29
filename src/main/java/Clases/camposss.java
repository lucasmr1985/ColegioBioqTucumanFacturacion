package Clases;

public class camposss {

    String codigo, coseguro,descripcion,precio;

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getPrecio() {
        return precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCoseguro(String coseguro) {
        this.coseguro = coseguro;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCoseguro() {
        return coseguro;
    }

    public camposss(String codigo, String coseguro, String descripcion, String precio) {
        this.codigo = codigo;
        this.coseguro = coseguro;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    
    
    
}
