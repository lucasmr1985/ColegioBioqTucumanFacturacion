
package Controlador;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LoginController implements Initializable {

    Stage prevStage;
    
    public void setPrevStage(Stage stage){
         this.prevStage = stage;
    }
    
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXPasswordField txtContrasña;
    
    @FXML
    public void ingresar(){
        try {
            
            System.out.println("Usuario: "+txtUsuario.getText());
            System.out.println("Contraseña: "+txtContrasña.getText());
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Principal.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            
            stage.setScene(new Scene(root));
            
            stage.show();
            prevStage.close();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }

}
