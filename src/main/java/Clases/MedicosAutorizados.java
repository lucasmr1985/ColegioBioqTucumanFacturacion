package Clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Fernando Lencina
 */
public class MedicosAutorizados {

    private int idObraSocial;
    private int matricula;
    private String observacion;

    public MedicosAutorizados() {
        idObraSocial = 0;
        matricula = 0;
        observacion = null;
    }

    public static MedicosAutorizados CrearMedicosAutorizados() {
        return new MedicosAutorizados();
    }

    public MedicosAutorizados(int idObraSocial, int matricula, String observacion) {
        this.idObraSocial = idObraSocial;
        this.matricula = matricula;
        this.observacion = observacion;
    }

    public int getIdObraSocial() {
        return idObraSocial;
    }

    public void setIdObraSocial(int idObraSocial) {
        this.idObraSocial = idObraSocial;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public static void cargarMedicosAutorizados(Connection connection, ArrayList<MedicosAutorizados> lista) {
        try {
            Statement instruccion = connection.createStatement();
            ResultSet rs = instruccion.executeQuery("Select id_obrasocial, matricula,  observacion\n"
                    + "from medicos_baja\n"
                    + "where tipo_estado=1");
            lista.removeAll(lista);
            while (rs.next()) {
                lista.add(new MedicosAutorizados( rs.getInt("id_obrasocial"), rs.getInt("matricula"), rs.getString("observacion")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean buscarMedicosAutorizados(int matricula, ArrayList<MedicosAutorizados> lista) {
        MedicosAutorizados resultado = null;
        for (MedicosAutorizados Medico : lista) {
            System.out.println("Medico.getMatricula():"+Medico.getMatricula());
            System.out.println("matricula:"+matricula);
            if (Medico.getMatricula() == matricula) {                
                resultado = Medico;
                break;
            }
        }
        if (resultado != null) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.matricula;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MedicosAutorizados other = (MedicosAutorizados) obj;
        if (this.matricula != other.matricula) {
            return false;
        }
        return true;
    }
}
