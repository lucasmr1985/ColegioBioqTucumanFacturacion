package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexionMariaDB {

    private Connection connection;
    public String db = "colegiobioquimicos";
    public String url = "jdbc:mariadb://db.cobituc.info:3306/" + db;

    public String user = "usFacturacion";
    public String pass = "Cole978++";

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void EstablecerConexion() {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionMariaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarConexion() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection Conectar() {
        Connection link = null;
        try {
            //Cargamos el Driver MySQL
            Class.forName("org.mariadb.jdbc.Driver");
            //Creamos un enlace hacia la base de datos
            link = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionMariaDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return link;
    }
}
