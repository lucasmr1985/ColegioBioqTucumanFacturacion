package Controlador;

import Modelo.Afiliados;
import Modelo.ConexionMariaDB;
import Modelo.ObraSocial;
import Modelo.Practicas;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import org.controlsfx.control.textfield.TextFields;

public class PrincipalController implements Initializable {

    private int idOS;
    public static String contraseña_usuario, matricula_colegiado, nombre_colegiado, cuit="27306274304";
    public static int idobraimprime = 0, totalRow = 0, tipo_orden = 1;
    
    //Componentes del formulario
    
     @FXML
    private JFXTextField txtMesOrdenes;

    @FXML
    private JFXTextField txtAñoOrdenes;

    @FXML
    private JFXTextField txtObraSocial;

    @FXML
    private JFXTextField txtDocumento;

    @FXML
    private JFXTextField txtNombreAfiliado;

    @FXML
    private JFXTextField txtNumenoAfiliado;

    @FXML
    private JFXTextField txtMatriculaPrescripcion;

    @FXML
    private JFXTextField txtNumeroOrden;

    @FXML
    private JFXDatePicker dtpkFechaRealizacion;

    @FXML
    private JFXCheckBox chkCoseguro;

    @FXML
    private JFXTextField txtCoseguro;

    @FXML
    private JFXDatePicker dtpkFechaCoseguro;

    @FXML
    private JFXTextField txtBuscarPractica;

    @FXML
    private JFXButton btnAceptar;
    
    //Colecciones
    private ObservableList<ObraSocial> listaOS;
    private ObservableList<Practicas> listaPracticas;
    private ObservableList<Afiliados> listaAfiliados;

    private ConexionMariaDB conexion;

    //Métodos
    @FXML
    public void salir() {
        System.out.println("Aquí sale del programa");
        System.exit(0);
    }

    @FXML
    public void eventoOS(ActionEvent evt){
        listaPracticas = FXCollections.observableArrayList();
        conexion = new ConexionMariaDB();
        conexion.EstablecerConexion();
        idOS = ObraSocial.buscarOS(txtObraSocial.getText(), listaOS).getIdObraSocial();
        Practicas.cargarPractica(conexion.getConnection(), listaPracticas, idOS);
        TextFields.bindAutoCompletion(txtBuscarPractica, listaPracticas);

////////////////////////////////
if(txtObraSocial.getText().equals("3100 - OSDE")) {
try {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/OsdeAfiliado.fxml"));
        
        
            Parent root = fxmlLoader.load();
            OsdeAfiliadoController controlador = fxmlLoader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
////////////////////////////////
        
        txtDocumento.requestFocus();
        conexion.cerrarConexion();
    }

    @FXML
    public void eventoDNI() {
        int obrasocial = 0;
        listaAfiliados = FXCollections.observableArrayList();
        conexion = new ConexionMariaDB();
        conexion.EstablecerConexion();
        if (!txtDocumento.equals("")) {
            if (!txtDocumento.equals("11111111")) {
                if (idOS == 11 || idOS == 12 || idOS == 13 || idOS == 14 || idOS == 15 || idOS == 90) {
                    obrasocial = 11;
                } else {
                    obrasocial = idOS;
                }
                Afiliados.cargarAfiliado(conexion.getConnection(), listaAfiliados, obrasocial, Integer.valueOf(txtDocumento.getText()));

                if (listaAfiliados.isEmpty()) {
                    Afiliados.cargarPersona(conexion.getConnection(), listaAfiliados, obrasocial, Integer.valueOf(txtDocumento.getText()));
                }
                txtNombreAfiliado.setText(listaAfiliados.get(0).getNombreAfiliado());
                txtNumenoAfiliado.setText(listaAfiliados.get(0).getNumeroAfiliado());

            }
            //Afiliados.cargarPractica(conexion.getConnection(), listaPracticas, ObraSocial.buscarOS(txtObraSocial.getText(), listaOS).getIdObraSocial());
        }
        txtMatriculaPrescripcion.requestFocus();
        conexion.cerrarConexion();

        /*if (!dni.equals("")) {
            if (!dni.equals("11111111")) {
                int i = 0, obrasocial = 0;
                try {
                    if (id_obra_social == 11 || id_obra_social == 12 || id_obra_social == 13 || id_obra_social == 14 || id_obra_social == 15 || id_obra_social == 90) {
                        obrasocial = 11;
                    } else {
                        obrasocial = id_obra_social;
                    }
                    //String sSQL = "SELECT nombre_afiliado,dni_afiliado,numero_afiliado FROM afiliados WHERE (dni_afiliado=" + dni + "  AND id_obra_social=" + id_obra_social + ") OR (numero_afiliado=" + dni + "  AND id_obra_social=" + id_obra_social + ") ";
                    String sSQL = "SELECT nombre_afiliado,dni_afiliado,numero_afiliado FROM afiliados WHERE (dni_afiliado=" + dni + "  AND id_obra_social=" + obrasocial + ") OR (numero_afiliado=" + dni + "  AND id_obra_social=" + obrasocial + ") ";
                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sSQL);
                    if (rs.next()) {
                        txtnombreafiliado.setText(rs.getString("nombre_afiliado"));
                        txtnumafiliado.setText(rs.getString("numero_afiliado"));
                        txtdocumento.setText(rs.getString("dni_afiliado"));
                        txtnombreafiliado.setEditable(false);
                        txtnumafiliado.setEnabled(true);
                        txtnumafiliado.setEditable(true);
                        txtnumafiliado.requestFocus();
                        band = 1;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                if (band == 0) {
                    try {
                        String sSQL = "SELECT dni_persona,apellido_persona,nombre_persona FROM personas WHERE dni_persona=" + dni;
                        Statement st = cn.createStatement();
                        ResultSet rs = st.executeQuery(sSQL);
                        while (rs.next()) {
                            documento_afiliado = dni;
                            nombre_afiliado = rs.getString("apellido_persona") + " " + rs.getString("nombre_persona");
                            numero_afiliado = "";
                            band2 = 1;
                            txtdocumento.setText(documento_afiliado);
                            txtnombreafiliado.setEditable(false);
                            txtnombreafiliado.setEditable(false);
                            txtnombreafiliado.setText(nombre_afiliado);
                            txtnumafiliado.setText(numero_afiliado);
                            txtnumafiliado.setEnabled(true);
                            txtnumafiliado.setEditable(true);
                            txtnumafiliado.requestFocus();
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                    if (band2 == 0) {
                        txtdocumento.setEnabled(true);
                        txtnombreafiliado.setEditable(true);
                        txtnombreafiliado.setEnabled(true);
                        txtnumafiliado.setText("");
                        txtnumafiliado.setEnabled(true);
                        jLabel18.setEnabled(true);
                        txtnombreafiliado.requestFocus();
                    }
                }
            } else {
                txtdocumento.setEnabled(true);
                txtdocumento.setEditable(true);
                txtnombreafiliado.setEnabled(true);
                txtnombreafiliado.setEditable(true);
                txtnumafiliado.setText("");
                txtnumafiliado.setEnabled(true);
                txtnumafiliado.setEditable(true);
                jLabel18.setEnabled(true);
                txtnombreafiliado.requestFocus();
            }
        }*/
    }

    @FXML
    public void eventoMatricula(KeyEvent evt) throws IOException {
        txtMatriculaPrescripcion.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
/*
        if (evt.getKeyCode() == KeyEvent.ANY) {

            if (txtObraSocial.equals("3100 - OSDE")) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/OsdeAfiliado.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Ayuda");
                stage.setScene(new Scene(root1));
                stage.show();

//  new OsdeAfiliado(this, true).setVisible(true);
                /*if (habilitado.equals("OK")) {
                        txtdocumento.setText("11111111");
                        txtdocumento.setEditable(false);
                        txtnombreafiliado.setText(OsdeAfiliado.nombreafiliado);
                        txtnumafiliado.setText(OsdeAfiliado.Codigo_afiliado);
                        txtmatricula.setText("");
                        txtmatricula.requestFocus();
                        habilitarpanel1();
                    }
            } else {
                    if (obra.equals("50015 - BOREAL")) {
                        new BorealAfiliado(this, true).setVisible(true);
                        if (BorealAfiliado.habilitado.equals("OK")) {
                            txtdocumento.setText("11111111");
                            txtdocumento.setEditable(false);
                            txtnombreafiliado.setText(BorealAfiliado.nombreafiliado);
                            txtnumafiliado.setText(BorealAfiliado.Codigo_afiliado);
                            txtmatricula.setText("");
                            txtmatricula.requestFocus();
                            habilitarpanel1();
                        }
                    } else {
                        if (obra.equals("10070 - SWISS MEDICAL GROUP S.A. - ONLINE")) {
                            new SwissAfiliado(this, true).setVisible(true);
                            if (habilitado.equals("OK")) {
                                txtdocumento.setText("11111111");
                                txtdocumento.setEditable(false);
                                txtnombreafiliado.setText(SwissAfiliado.nombreafiliado);
                                txtnumafiliado.setText(SwissAfiliado.Codigo_afiliado);
                                txtmatricula.setText("");
                                txtmatricula.requestFocus();
                                habilitarpanel1();
                            }
                        } else {
                            if (obra.equals("9000 - ASOCIACION MUTUAL SANCOR")) {
                                new SancorAfiliado(this, true).setVisible(true);
                                if (SancorAfiliado.habilitado.equals("OK")) {
                                    txtdocumento.setText(SancorAfiliado.dni);
                                    txtdocumento.setEditable(false);
                                    txtnombreafiliado.setText(SancorAfiliado.nombreafiliado);
                                    txtnumafiliado.setText(SancorAfiliado.Codigo_afiliado);
                                    txtmatricula.setText("");
                                    txtmatricula.requestFocus();
                                    habilitarpanel1();
                                }
                            } else {
                                if (obra.equals("1805 - SUBSIDIO DE SALUD - ONLINE")) {
                                    new SubsidioAfiliado(this, true).setVisible(true);
                                    if (SubsidioAfiliado.habilitado.equals("OK")) {
                                        txtdocumento.setText(SubsidioAfiliado.dni);
                                        txtdocumento.setEditable(false);
                                        txtnombreafiliado.setText(SubsidioAfiliado.nombreafiliado);
                                        txtnumafiliado.setText(SubsidioAfiliado.Codigo_afiliado);
                                        txtmatricula.setText("");
                                        txtmatricula.requestFocus();
                                        habilitarpanel1();
                                    }
                                } else {
                                    if (obra.equals("1806 - SUBSIDIO DE SALUD - AUTORIZACION - ONLINE")) {
                                        new SubsidioAfiliado(this, true).setVisible(true);
                                        if (SubsidioAfiliado.habilitado.equals("OK")) {
                                            txtdocumento.setText(SubsidioAfiliado.dni);
                                            txtdocumento.setEditable(false);
                                            txtnombreafiliado.setText(SubsidioAfiliado.nombreafiliado);
                                            txtnumafiliado.setText(SubsidioAfiliado.Codigo_afiliado);
                                            txtmatricula.setText("");
                                            txtmatricula.requestFocus();
                                            habilitarpanel1();
                                        }
                                    } else {
                                        if (obra.equals("3102 - OSDE - OFFLINE")) {
                                            new osdeOffline(this, true).setVisible(true);
                                            txtpractica.setText("");
                                            btnpaciente.doClick();
                                        } else {
                                            if (obra.equals("512 - MEDIFE - ONLINE- PRE PAGA C.M.C.  S.A.")) {
                                                new MedifeAfiliado(this, true).setVisible(true);
                                                if (MedifeAfiliado.habilitado.equals("OK")) {
                                                    txtdocumento.setText(MedifeAfiliado.dni);
                                                    txtdocumento.setEditable(false);
                                                    txtnombreafiliado.setText(MedifeAfiliado.nombreafiliado);
                                                    txtnumafiliado.setText(MedifeAfiliado.Codigo_afiliado);
                                                    txtmatricula.setText("");
                                                    txtmatricula.requestFocus();
                                                    habilitarpanel1();
                                                }
                                            } else {
                                                if (obra.equals("37701 - JERARQUICOS SALUD - EMP. BNA - ONLINE")) {
                                                    try {
                                                        new JerarquicosAfiliado(this, true).setVisible(true);
                                                    } catch (DatatypeConfigurationException ex) {
                                                        Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                    if (JerarquicosAfiliado.habilitado.equals("OK")) {
                                                        txtdocumento.setText(JerarquicosAfiliado.dni);
                                                        txtdocumento.setEditable(false);
                                                        txtnombreafiliado.setText(JerarquicosAfiliado.nombreafiliado);
                                                        txtnumafiliado.setText(JerarquicosAfiliado.Codigo_afiliado);
                                                        txtmatricula.setText("");
                                                        txtmatricula.requestFocus();
                                                        habilitarpanel1();
                                                    }
                                                } else {
                                                    txtpractica.setText("");
                                                    btnpaciente.doClick();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                 
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_SUBTRACT) {
                // txtpractica.setText("");
                // btnborrar.doClick();
            } else {
                if (evt.getKeyCode() == KeyEvent.VK_DIVIDE) {

                    //   txtpractica.setText("");
                    //  btnobrasocial.doClick();
                } else {
                    // txtmatricula.setText("");
                }
            }
        }
*/
    }
    
    @FXML
    public void osdeAfiliado(ActionEvent evt){
        
        try {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/OsdeAfiliado.fxml"));
        
        
            Parent root = fxmlLoader.load();
            OsdeAfiliadoController controlador = fxmlLoader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        conexion = new ConexionMariaDB();
        conexion.EstablecerConexion();
        listaOS = FXCollections.observableArrayList();
        ObraSocial.cargarOS(conexion.getConnection(), listaOS);
        TextFields.bindAutoCompletion(txtObraSocial, listaOS);
        conexion.cerrarConexion();

    }

}
