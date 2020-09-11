package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class ObraSocial {

    private IntegerProperty idObraSocial;
    private IntegerProperty codigo;
    private StringProperty nombre;

    public ObraSocial(int idObraSocial, int codigo, String nombre) {
        this.idObraSocial = new SimpleIntegerProperty(idObraSocial);
        this.codigo = new SimpleIntegerProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
    }

    //Metodos atributo: idObraSocial
    public int getIdObraSocial() {
        return idObraSocial.get();
    }

    public void setIdObraSocial(int idObraSocial) {
        this.idObraSocial = new SimpleIntegerProperty(idObraSocial);
    }

    public IntegerProperty IdObraSocialProperty() {
        return idObraSocial;
    }
    //Metodos atributo: codigo

    public int getCodigo() {
        return codigo.get();
    }

    public void setCodigo(int codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public IntegerProperty CodigoProperty() {
        return codigo;
    }
    //Metodos atributo: nombre

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
    }

    public StringProperty NombreProperty() {
        return nombre;
    }

    public static ObraSocial buscarOS(String nombre, ObservableList<ObraSocial> lista) {
        ObraSocial resultado = null;

        for (ObraSocial obras : lista) {

            if ((obras.getCodigo()+" - "+obras.getNombre()).equals(nombre)) {

                resultado = obras;
                break;

            }
        }

        return resultado;
    }

    public static void cargarOS(Connection connection, ObservableList<ObraSocial> lista) {
        try {
            Statement instruccion = connection.createStatement();
            ResultSet rs = instruccion.executeQuery("SELECT id_obrasocial, Int_codigo_obrasocial, nombre_obrasocial FROM obrasocial");
            lista.removeAll();
            while (rs.next()) {
                lista.add(new ObraSocial(rs.getInt("id_obrasocial"), rs.getInt("Int_codigo_obrasocial"), rs.getString("nombre_obrasocial")));
                //System.out.println(rs.getInt("id_obrasocial")+" - "+rs.getInt("Int_codigo_obrasocial")+" - "+rs.getString("nombre_obrasocial"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return codigo.get() + " - " + nombre.get();
    }

}
