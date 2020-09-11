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

public class Practicas {

    private IntegerProperty idPracticasNBU;
    private IntegerProperty codigoPractica;
    private StringProperty determinacion;

    public Practicas(int idPracticasNBU, int codigoPractica, String determinacion) {
        this.idPracticasNBU = new SimpleIntegerProperty(idPracticasNBU);
        this.codigoPractica = new SimpleIntegerProperty(codigoPractica);
        this.determinacion = new SimpleStringProperty(determinacion);       
    }

    //Metodos atributo: idPracticasNBU
    public int getIdPracticasNBU() {
        return idPracticasNBU.get();
    }

    public void setIdPracticasNBU(int idPracticasNBU) {
        this.idPracticasNBU = new SimpleIntegerProperty(idPracticasNBU);
    }

    public IntegerProperty IdPracticasNBUProperty() {
        return idPracticasNBU;
    }
    //Metodos atributo: codigoPractica

    public int getCodigoPractica() {
        return codigoPractica.get();
    }

    public void setCodigoPractica(int codigoPractica) {
        this.codigoPractica = new SimpleIntegerProperty(codigoPractica);
    }

    public IntegerProperty CodigoPracticaProperty() {
        return codigoPractica;
    }
    //Metodos atributo: determinacion

    public String getDeterminacion() {
        return determinacion.get();
    }

    public void setDeterminacion(String determinacion) {
        this.determinacion = new SimpleStringProperty(determinacion);
    }

    public StringProperty DeterminacionProperty() {
        return determinacion;
    }
   

    public static void cargarPractica(Connection connection, ObservableList<Practicas> lista, int idOS) {
        try {
            Statement instruccion = connection.createStatement();
            ResultSet rs;
            rs = instruccion.executeQuery("SELECT id_practicasnbu, codigo_practica, determinacion, id_obrasocial FROM obrasocial_tiene_practicasnbu WHERE id_obrasocial= " + idOS);
            while (rs.next()) {
                lista.add(new Practicas(rs.getInt("id_practicasnbu"), rs.getInt("codigo_practica"), rs.getString("determinacion")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return  codigoPractica.get() + " - " + determinacion.get();
    }
    

}
