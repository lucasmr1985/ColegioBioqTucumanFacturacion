package Clases;

public class Localidad {

    private String nombre;
    private String cp;

    public Localidad(String nombre, String cp) {
        this.nombre = nombre;
        this.cp = cp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
}
