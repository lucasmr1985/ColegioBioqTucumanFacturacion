package Controlador;

import static Controlador.PrincipalController.cuit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class OsdeAfiliadoController implements Initializable {

    ObservableList<String> items = FXCollections.observableArrayList();
    public static String habilitado = "", nombreafiliado = "", dni = "", Codigo_afiliado = "", CSC = "";
    String hora = "", fechaosde = "", pasaporte = "", mensaje = "", respuesta = "";

    @FXML
    private JFXComboBox<String> comboTipoServicio;

    @FXML
    private JFXTextField txtCodigoSeguridad;

    @FXML
    private JFXTextField txtAfiliado;

    @FXML
    private JFXButton btnValidar;

    @FXML
    private FontAwesomeIconView fntValidar;

    @FXML

    void eventoValidar() {
        Stage stage = (Stage) btnValidar.getScene().getWindow();

        ////////////////////////////////////////////
        String numero_afiliado = txtAfiliado.getText();

        CSC = txtCodigoSeguridad.getText();
        if (numero_afiliado.length() > 11) {
            numero_afiliado = numero_afiliado.substring(numero_afiliado.length() - 11, numero_afiliado.length());
        }
        System.out.println("numero_afiliado " + numero_afiliado);
        mensaje = "<Mensaje><EncabezadoMensaje><VersionMsj>1.0</VersionMsj><TipoTransaccion>01A</TipoTransaccion><IdMsj>" + hora + "</IdMsj><InicioTrx><FechaTrx>" + fechaosde + "</FechaTrx><HoraTrx>" + hora + "</HoraTrx></InicioTrx><Terminal><TipoTerminal>PC</TipoTerminal><NumeroTerminal>1</NumeroTerminal></Terminal><Financiador><CodigoFinanciador>11</CodigoFinanciador><CuitFinanciador>30546741253</CuitFinanciador></Financiador><Prestador><CuitPrestador>" + cuit + "</CuitPrestador></Prestador></EncabezadoMensaje><EncabezadoAtencion><Efector/><Prescriptor/><Credencial><NumeroCredencial>" + numero_afiliado + "</NumeroCredencial><VersionCredencial>" + CSC + "</VersionCredencial></Credencial><Preautorizacion/><Documentacion/><Atencion/><Diagnostico/><CodFinalizacionTratamiento/><MensajeParaFinanciador/></EncabezadoAtencion><DetalleProcedimientos/></Mensaje>";

        HttpURLConnectionExample http = new HttpURLConnectionExample();

        System.out.println("Testing 1 - Send Http GET request");
        try {
            respuesta = http.sendGet(mensaje);
        } catch (Exception ex) {
            System.out.println(ex);

            JOptionPane.showMessageDialog(null, "Error al conectarse al servidor de Osde");
        }
        int pos0 = respuesta.indexOf("Afiliado : ") + 11;
        int pos1 = pos0 + 11;
        System.out.println(respuesta.substring(pos0, pos1));
        Codigo_afiliado = respuesta.substring(pos0, pos1);

        int pos = respuesta.indexOf("MensajeDisplay");
        int pos2 = respuesta.indexOf("</MensajeDisplay");

        int pos3 = respuesta.indexOf("Nombre:");
        int pos4 = respuesta.indexOf("Nro.Plan:");
        if (respuesta.substring(pos + 15, pos + 17).equals("OK")) {
            habilitado = respuesta.substring(pos + 15, pos + 17);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setHeaderText("Validación OSDE");
            alert.setContentText("Afiliado habilitado");
            
            alert.show();
            DialogPane dialogPane = alert.getDialogPane();
dialogPane.getStylesheets().add(
   getClass().getResource("/styles/myDialogs.css").toExternalForm());
dialogPane.getStyleClass().add("myDialog");
            //JOptionPane.showMessageDialog(null, "El Afiliado esta habilitado");
            nombreafiliado = respuesta.substring(pos3 + 7, pos4);
            dni = "";
        } else {
            JOptionPane.showMessageDialog(null, respuesta.substring(pos + 15, pos2));
            txtAfiliado.requestFocus();
        }
        ////////////////////////////////////////////    

        stage.close();
    }

    @FXML

    void eventoTipo() {
        switch (comboTipoServicio.getValue()) {
            case "Ambulatorio":
                PrincipalController.tipo_orden = 1;
                System.out.println("Ambulatorio");
                break;
            case "Internado":
                PrincipalController.tipo_orden = 3;
                System.out.println("Internación");
                break;
            case "Domicilio":
                PrincipalController.tipo_orden = 4;
                System.out.println("Domicilio");
                break;
            default:
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        items.addAll("Ambulatorio", "Internado");
        comboTipoServicio.setItems(items);

    }

}
