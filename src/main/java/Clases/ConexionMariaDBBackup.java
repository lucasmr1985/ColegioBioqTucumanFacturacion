package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexionMariaDBBackup {

    public String db = "back_up_colegiobioquimicos";
    //public String url = "jdbc:mysql://localhost:3306/"+db;
    public String url = "jdbc:mariadb://db.cobituc.info:3306/" + db;
//public String url = "jdbc:mariadb://3.16.170.11:3306/"+db;
    // public String url = "jdbc:mysql://3.16.3.243:3306/"+db;

    public String user = "root";
    public String pass = "Cole978-+";

    //public String pass = "Cole978-+";
    /*  public String db = "proveeduriaprueba";
    public String url = "jdbc:mysql://localhost:3306/"+db;


    public String user = "root";
    public String pass = "";*/

    public Connection Conectar() {
        Connection link = null;
        try {
            //Cargamos el Driver MySQL
            Class.forName("org.mariadb.jdbc.Driver");
            //Creamos un enlace hacia la base de datos
            link = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionMariaDBBackup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return link;
    }
}
