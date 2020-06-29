package Clases;

import Formularios.MainL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ordenes {

    private int idOrden;

    public boolean validaOrden(int numero) {
        boolean valida = false;
        ConexionMariaDB mysql = new ConexionMariaDB();
        Connection cn = mysql.Conectar();

        try {
            String controlOrden = "SELECT id_orden, numero_orden FROM vista_ordenes_ipsst WHERE numero_orden= " + numero;
            Statement stControl = cn.createStatement();
            ResultSet rsControl = stControl.executeQuery(controlOrden);

            if (rsControl.next()) {
                valida = true;
                idOrden = rsControl.getInt(1);
            } else {
                valida = false;
            }
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valida;
    }

    public void guardaRegistroSS(int orden) {

        ConexionMariaDB mysql = new ConexionMariaDB();
        Connection cn = mysql.Conectar();

        try {
            int n;
            Date fecha = new Date(0);
            Time hora;
            
            String controlOrden = "INSERT INTO ordenes_subsidio (duplicadas, fecha) VALUES(?,now())";
            PreparedStatement st = cn.prepareStatement(controlOrden);

            st.setInt(1, orden);
            
           
           n = st.executeUpdate();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getIdOrden() {
        return idOrden;
    }

}
