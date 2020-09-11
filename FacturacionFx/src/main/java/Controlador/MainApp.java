package Controlador;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage primerStage) throws Exception {
        
        primerStage.setTitle("Ingreso");
        
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        
        Pane myPane = (Pane)myLoader.load();
        
        LoginController controller = (LoginController) myLoader.getController();
        
       controller.setPrevStage(primerStage);

   Scene myScene = new Scene(myPane);        
   primerStage.setScene(myScene);
   primerStage.show();
        
       
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}
