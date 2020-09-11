package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMariaDB {
    private Connection connection;
    private String url = "jdbc:mariadb://localhost:3306/colegiobioquimicos";
   //private String url = "jdbc:mariadb://db.cobituc.info:3306/colegiobioquimicos";
    private String usuario = "root";
    private String contraseña = "Cole978++";
    //private String pass = "Cole978-+";
   
    public Connection getConnection() {
		return connection;
	}
    
    public void setConnection(Connection connection) {
		this.connection = connection;
	}

    public void EstablecerConexion() {
       
        try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(url, usuario, contraseña);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
    public void cerrarConexion(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
