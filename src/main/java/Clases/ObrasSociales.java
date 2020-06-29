package Clases;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ObrasSociales {

    private int idObraSocial;
    private int codigo;
    private String nombre;
    private double importeUnidadDeArancel;
    private String observacion;
    private Blob ImagenAdjunta;
    private String RutaArchivoAdjunto;

    public ObrasSociales(int idObraSocial, int codigo, String nombre, double importeUnidadDeArancel) {
        this.idObraSocial = idObraSocial;
        this.codigo = codigo;
        this.nombre = nombre;
        this.importeUnidadDeArancel = importeUnidadDeArancel;
        this.observacion = observacion;
    }

    public ObrasSociales(int codigo, String nombre,String observacion, Blob ImagenAdjunta, String RutaArchivoAdjunto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.observacion = observacion;
        this.ImagenAdjunta = ImagenAdjunta;
        this.RutaArchivoAdjunto = RutaArchivoAdjunto;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void setImagenAdjunta(Blob ImagenAdjunta) {
        this.ImagenAdjunta = ImagenAdjunta;
    }

    public void setRutaArchivoAdjunto(String RutaArchivoAdjunto) {
        this.RutaArchivoAdjunto = RutaArchivoAdjunto;
    }

    public String getObservacion() {
        return observacion;
    }

    public Blob getImagenAdjunta() {
        return ImagenAdjunta;
    }

    public String getRutaArchivoAdjunto() {
        return RutaArchivoAdjunto;
    }
    
   

    public int getIdObraSocial() {
        return idObraSocial;
    }

    public void setIdObraSocial(int idObraSocial) {
        this.idObraSocial = idObraSocial;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getImporteUnidadDeArancel() {
        return importeUnidadDeArancel;
    }

    public void setImporteUnidadDeArancel(double importeUnidadDeArancel) {
        this.importeUnidadDeArancel = importeUnidadDeArancel;
    }

    public static void CargarObraSocialInformacionFactura(Connection connection,ArrayList<ObrasSociales> lista) {
        
        try {
            Statement instruccion = connection.createStatement();
            ResultSet rs = instruccion.executeQuery("SELECT * FROM vista_obrasocial_informacion");
            lista.removeAll(lista);
            while (rs.next()) {                
                lista.add(new ObrasSociales(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("observacion"),rs.getBlob("imagen_adjunta"),rs.getString("ruta_archivo_adjunto")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObrasSociales BuscarOsInformacionFactura(String nombre, ArrayList<ObrasSociales> lista) {
        ObrasSociales info = null;
        for (ObrasSociales obrasocial : lista) {
            if ((obrasocial.getCodigo() + " - " + obrasocial.getNombre()).equals(nombre)) {
                info = obrasocial;
                break;
            }
        }
        return info;
    }
    
    public static void cargarOS(Connection connection, ArrayList<ObrasSociales> lista) {
        try {
            Statement instruccion = connection.createStatement();
            ResultSet rs = instruccion.executeQuery("SELECT id_obrasocial,razonsocial_obrasocial,importeunidaddearancel_obrasocial,codigo_obrasocial FROM obrasocial");
            lista.removeAll(lista);
            while (rs.next()) {
                lista.add(new ObrasSociales(rs.getInt("id_obrasocial"), rs.getInt("codigo_obrasocial"), rs.getString("razonsocial_obrasocial"), rs.getDouble("importeunidaddearancel_obrasocial")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObrasSociales buscarOS(String nombre, ArrayList<ObrasSociales> lista) {
        ObrasSociales resultado = null;
        for (ObrasSociales obras : lista) {
            if ((obras.getCodigo() + " - " + obras.getNombre()).equals(nombre)) {
                resultado = obras;
                break;
            }
        }
        return resultado;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }

}
