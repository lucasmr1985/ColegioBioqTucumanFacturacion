
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


public class Afiliados{
	private IntegerProperty dniAfiliado;
	private StringProperty nombreAfiliado;
	private StringProperty numeroAfiliado;

	public Afiliados(int dniAfiliado, String nombreAfiliado, String numeroAfiliado) { 
		this.dniAfiliado = new SimpleIntegerProperty(dniAfiliado);
		this.nombreAfiliado = new SimpleStringProperty(nombreAfiliado);
		this.numeroAfiliado = new SimpleStringProperty(numeroAfiliado);
	}

	//Metodos atributo: dniAfiliado
	public int getDniAfiliado() {
		return dniAfiliado.get();
	}
	public void setDniAfiliado(int dniAfiliado) {
		this.dniAfiliado = new SimpleIntegerProperty(dniAfiliado);
	}
	public IntegerProperty DniAfiliadoProperty() {
		return dniAfiliado;
	}
	//Metodos atributo: nombreAfiliado
	public String getNombreAfiliado() {
		return nombreAfiliado.get();
	}
	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = new SimpleStringProperty(nombreAfiliado);
	}
	public StringProperty NombreAfiliadoProperty() {
		return nombreAfiliado;
	}
	//Metodos atributo: numeroAfiliado
	public String getNumeroAfiliado() {
		return numeroAfiliado.get();
	}
	public void setNumeroAfiliado(String numeroAfiliado) {
		this.numeroAfiliado = new SimpleStringProperty(numeroAfiliado);
	}
	public StringProperty NumeroAfiliadoProperty() {
		return numeroAfiliado;
	}
        
        public static void cargarAfiliado(Connection connection, ObservableList<Afiliados> lista, int idOS, int dni) {
        try {
            Statement instruccion = connection.createStatement();
            ResultSet rs;
            rs = instruccion.executeQuery("SELECT nombre_afiliado,dni_afiliado,numero_afiliado FROM afiliados WHERE (dni_afiliado=" + dni + "  AND id_obra_social=" + idOS + ") OR (numero_afiliado=" + dni + "  AND id_obra_social=" + idOS + ") ");
            while (rs.next()) {
                lista.add(new Afiliados(rs.getInt("dni_afiliado"), rs.getString("nombre_afiliado"), rs.getString("numero_afiliado")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
        public static void cargarPersona(Connection connection, ObservableList<Afiliados> lista, int idOS, int dni) {
        try {
            Statement instruccion = connection.createStatement();
            ResultSet rs;
            rs = instruccion.executeQuery("SELECT dni_persona,apellido_persona,nombre_persona FROM personas WHERE dni_persona=" + dni);
            while (rs.next()) {
                lista.add(new Afiliados(rs.getInt("dni_persona"), rs.getString("apellido_persona")+" "+rs.getString("nombre_persona"), ""));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
        public static Afiliados buscarAfiliado(int dni, ObservableList<Afiliados> lista) {
        Afiliados resultado = null;

        for (Afiliados afiliado : lista) {

            if (afiliado.getDniAfiliado()== dni) {

                resultado = afiliado;
                break;

            }
        }

        return resultado;
    }
}