package Formularios;

import Clases.ConexionMariaDB;
import Clases.ConexionMariaDBBackup;
import Clases.Contraseña_Boreal;
import Clases.NumberToLetterConverter;
import static Clases.HiloInicio.contadorafiliado;
import static Clases.HiloInicio.contadorobrasocial;
import static Clases.HiloInicio.dniafiliado;
import static Clases.HiloInicio.nomafiliado;
import static Clases.HiloInicio.numafiliado;
import static Clases.HiloInicio.idobrasocial;
import static Clases.HiloInicio.obrasocial;
import static Clases.HiloInicio.analisis;
import static Clases.HiloInicio.contadoranalisis;
import static Clases.HiloInicio.novedad;
import static Clases.HiloInicio.periodo_backup;
import static Clases.HiloInicio.version;
import static Clases.HiloInicio.version_actual;
import static Clases.HiloInicio.listaMedicos;
import static Formularios.Detalle_Obrasocial.nombre_obrasocial;
import Clases.Hilo_Espera;
import Clases.MedicosAutorizados;
import Clases.ImagenPDF;
import Clases.Ordenes;
import Clases.ReadXMLFile;
import Clases.TripleDes;
import Clases.camposboreal;
import Clases.camposordenes_osde;
import Clases.camposordenes_ss;
import Clases.camposss;
import Clases.export_excel;
import Clases.solomayusculas;
import Clases.validar_orden;
import static ClienteAsunt.AsuntConexionWsdl.conexionWsdl;
import static Formularios.Detalle_Practicas.observacion;
import static Formularios.Login.cuit;
import static Formularios.Login.validacion_pami;
import static Formularios.Login.id_usuario;
import static Formularios.Login.matricula_colegiado;
import static Formularios.Login.nombre_colegiado;
import static Formularios.Login.periodo_colegiado;
import static Formularios.LoginAdmin.estadologinadmin;
import static Formularios.LoginAdmin.estadologinsecretaria;
import static Formularios.OsdeAfiliado.habilitado;
import static Formularios.importar.Importar;
import static Formularios.importar.archivo;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import ClienteBoreal.WsBorealExecuteResponse;
import ClienteIPSST3.OrdenAutorizarCHEQUEARFACTIBILIDADResponse;
import ClienteIPSST3.OrdenAutorizarEMITIRResponse;
import ClienteIPSST4.OrdenValidarExecuteResponse;
import ClienteIPSST5.OrdenDevolverExecuteResponse;
import ClienteIPSST6.OrdenValidadaAnularExecuteResponse;
import ClienteJerarquicos.CriterioAnulacionConsumoWeb;
import ClienteJerarquicos.CriterioAutorizacionConsumoWeb;
import ClienteJerarquicos.CriterioPracticaRequiereAutorizacion;
import ClienteJerarquicos.IServicioPublico;
import ClienteJerarquicos.ObjectFactory;
import ClienteJerarquicos.RespuestaAutorizacionConsumoWeb;
import ClienteJerarquicos.RespuestaBase;
import ClienteJerarquicos.Servicio;
import ClienteJerarquicos.SolicitudValidacionPracticaRequiereAutorizacion;
import ClienteMedife.WebServiceIA;
import ClienteMedife.WebServiceIASoap;
import ClienteOspe.ExecuteFileTransactionSL;
import ClienteOspe.WSActiviaC;
import ClienteOspe.WSActiviaCSoap;
import ClienteSancor.PAWESSAV2ANULACIONResponse;
import ClienteSancor.PAWESSAV2AUTORIZACIONResponse;
import ClienteSwissMedicalApi.Cancelacion;
import ClienteSwissMedicalApi.CancelacionResponse;
import ClienteSwissMedicalApi.Registracion;
import ClienteSwissMedicalApi.RegistracionResponse;
import ClienteSwissMedicalApi.Device;
import ClienteSwissMedicalApi.LoginResponse;
import ClienteSwissMedicalApi.LoginError;
import static Formularios.BorealAfiliado.tipo_credencial;
import static Formularios.JerarquicosAfiliado.NumeroOrden;
import static Formularios.JerarquicosAfiliado.NumeroSocio;
import static Formularios.Login.estadopeec;
import static Formularios.MedifeAfiliado.plan;
import static Formularios.OsdeAfiliado.CSC_OS;
import static Formularios.SwissAfiliado.CSC_SW;
import static Formularios.SwissAfiliado.Codigo_afiliado;
import static Formularios.SwissAfiliado.apiKey;
import controlador.Funciones;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.RowFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableRowSorter;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import modelo.Practica;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrintManager;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import com.google.gson.Gson;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

public class MainL extends javax.swing.JFrame {

    public static String url, documento_afiliado, nombre_afiliado, numero_afiliado, fecha, fecha2, id_obra2 = "", localidad_lab, domicilio_lab, total_pesos_letras, total_centavos_letras, periodo, periododjj;
    // static String año;
    public static int bandera = 1, contadorPracticas = 0, idObraSocialOnline = 0;
    int id_obra_social, banderamodifica = 0, contadorj = 0, id_orden, pacientes = 0, practicas = 0, total = 0, contadorobra = 0;
    static int plan;
    DefaultTableModel model, model1, model_tabla_facturacion;
    DefaultTableCellRenderer alinearCentro, alinearDerecha, alinearIzquierda;
    HiloOrdenes hilo;
    Hilo_Espera hilo90 = new Hilo_Espera("");
    Hilo_Espera hilo91 = new Hilo_Espera("");
    String hora = "", fechaMySql = "", Codigo_afiliado = "", pasaporte = "", mensaje = "", documento;
    Hiloobrasocial hilo2;
    HiloModificaOrdenes hilo3;
    HiloOrdenesImportar hilo4;
    Vector columna = new Vector();
    Vector filas = new Vector();
    public static DefaultTableModel myModel;
    public static String ObraSocial, CodObra, mes = "", año = "", coseg;
    public static int idobraimprime = 0, totalRow = 0, tipo_orden = 1;
    String[] practica = new String[2000];
    String[] preciopractica = new String[2000];
    String[] codfacpractica = new String[2000];
    String[] idpractica = new String[2000];
    String[][] tablaimportar;
    Object prueba[] = new Object[10];
    TextAutoCompleter textAutoAcompleter;
    TextAutoCompleter textAutoAcompleter2;
    public static String ruta = "C:\\Descargas-CBT\\";
    String obra = "", mensajepractica = "", mensajeanulacion = "", respuestapractica = "", respuestaafiliado = "", respuestaanulacion = "";
    public static String ipLocal = "", hostLocal = "";
    String ip2 = "";
    XMLGregorianCalendar date_jerarquicos;
    String fechahora_medife = "", codigo_seguridad_medife = "", fecha_txt = "";
    TableRowSorter sorter = null;
    private ConexionMariaDB conexion;
    public static ArrayList<Practica> listaPracticas;

    public MainL() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        this.setTitle("Principal");
        this.setLocationRelativeTo(null);
        cargarip();
        cargarperiodo();
        textAutoAcompleter2 = new TextAutoCompleter(txtobrasocial);
        cargarobrasocial();
        cargarorden();
        dobleclick();
        cargarnovedad();
        cargarfechamedife();
        txtordenes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Character c = evt.getKeyChar();
                if (Character.isLetter(c)) {
                    evt.setKeyChar(Character.toUpperCase(c));
                }
            }
        });

        tablapracticas.getColumnModel().getColumn(4).setMaxWidth(0);
        tablapracticas.getColumnModel().getColumn(4).setMinWidth(0);
        tablapracticas.getColumnModel().getColumn(4).setPreferredWidth(0);
        ////////////////////////////////////////////////////////////////////////
        textAutoAcompleter = new TextAutoCompleter(txtpractica);

        lblcolegiado.setText(nombre_colegiado);
        lblcolegiado1.setText(nombre_colegiado);
        lblcolegiado2.setText(nombre_colegiado);
        lblcolegiado3.setText(nombre_colegiado);

        deshabilitarpanel1();
        txtfecha.setEnabled(false);
        txtobrasocial.setEnabled(false);
        jLabel5.setEnabled(false);
        cbotipo.setVisible(false);
        txtDiaOrden.setVisible(false);
        jLabel21.setVisible(false);

        txtnombreafiliado.setDocument(new solomayusculas());
        jPanel7.setEnabled(false);
        txtmes3.setEnabled(false);
        txtaño3.setEnabled(false);
        btnimportar.setEnabled(false);
        tablaordenes1.setEnabled(false);
        btnaceptar2.setEnabled(false);
        btncancelar2.setEnabled(false);
        btnsalir3.setEnabled(false);
        Facturacion.setEnabled(false);
        txtmes1.setEnabled(false);
        txtaño1.setEnabled(false);
        btnbuscar.setEnabled(false);
        txtordenes.setEnabled(false);
        tablaordenes.setEnabled(false);
        btnimprimirdjj.setEnabled(false);
        btnimprimirobra.setEnabled(false);
        btncancelar3.setEnabled(false);
        btncancelar1.setEnabled(false);
        btnsalir1.setEnabled(false);
        txtmes.requestFocus();
        jTabbedPane2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mensaje(evt);
            }
        });
        txtcoseguro.setEnabled(false);
        txtfechacoseguro.setEnabled(false);
    }

    public void mensaje(ChangeEvent evt) {
        // Ultima pestaña
        JTabbedPane seleccion = (JTabbedPane) evt.getSource();

        if (jTabbedPane2.getSelectedIndex() == 1) {
            txtordenes.setText("");
            new LoginAdmin(this, true).setVisible(true);
            habilitacionPanelUno();
        }

        if (seleccion.getSelectedIndex() == 2) {
            new LoginAdmin(this, true).setVisible(true);
            habilitacionPanelDos();
        }
    }

    public void llama_excel() {
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + ruta + "Errores Transferencia" + ".xls");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void habilitacionPanelDos() {

        jTabbedPane2.setSelectedIndex(2);
        txtordenes.setText("");
        Facturacion.setEnabled(false);
        txtmes1.setEnabled(false);
        txtaño1.setEnabled(false);
        btnbuscar.setEnabled(false);
        txtordenes.setEnabled(false);
        tablaordenes.setEnabled(false);
        btnimprimirdjj.setEnabled(false);
        btnimprimirobra.setEnabled(false);
        btncancelar3.setEnabled(false);
        btncancelar1.doClick();
        btncancelar1.setEnabled(false);
        btnsalir1.setEnabled(false);

        if (estadologinadmin == true) {
            Facturacion.setEnabled(true);
            txtmes1.setEnabled(true);
            txtaño1.setEnabled(true);
            txtordenes.setEnabled(true);
            txtmes1.setEditable(true);
            txtaño1.setEditable(true);
            txtordenes.setEditable(true);
            btnbuscar.setEnabled(true);
            tablaordenes.setEnabled(true);
            btnimprimirdjj.setEnabled(true);
            btnimprimirobra.setEnabled(true);
            btncancelar3.setEnabled(true);
            btncancelar1.setEnabled(true);
            btncancelar1.doClick();
            btnsalir1.setEnabled(true);
        }
        if (estadologinsecretaria == true) {
            Facturacion.setEnabled(true);
            txtmes1.setEnabled(true);
            txtaño1.setEnabled(true);
            txtordenes.setEnabled(true);
            txtmes1.setEditable(true);
            txtaño1.setEditable(true);
            txtordenes.setEditable(true);
            btnbuscar.setEnabled(true);
            tablaordenes.setEnabled(true);
            btnimprimirdjj.setEnabled(true);
            btnimprimirobra.setEnabled(false);
            btncancelar3.setEnabled(false);
            btncancelar1.setEnabled(true);
            btncancelar1.doClick();
            btnsalir1.setEnabled(true);
            txttotal.setText("------");
        }
        return;
    }

    void habilitacionPanelUno() {
        jTabbedPane2.setSelectedIndex(1);
        txtordenes.setText("");
        jPanel7.setEnabled(false);
        txtmes3.setEnabled(false);
        txtaño3.setEnabled(false);
        btnimportar.setEnabled(false);
        tablaordenes1.setEnabled(false);
        btnaceptar2.setEnabled(false);
        btncancelar2.setEnabled(false);
        btnsalir3.setEnabled(false);

        if (estadologinadmin == true) {
            jPanel7.setEnabled(true);
            txtmes3.setEnabled(true);
            txtaño3.setEnabled(true);
            btnimportar.setEnabled(true);
            tablaordenes1.setEnabled(true);
            btnaceptar2.setEnabled(true);
            btncancelar2.setEnabled(true);
            btnsalir3.setEnabled(true);
            txtmes1.requestFocus();
            return;
        }
        if (estadologinsecretaria == true) {
            System.out.println(estadologinsecretaria);
            JOptionPane.showMessageDialog(null, "EL usuario no posee permisos para realizar Transferencias de ordenes");
            return;
        }

    }

    void habilitartabla() {
        Detalle_Practicas.num_orden = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 6).toString();
        Detalle_Practicas.id_orden = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 0).toString();
        Detalle_Practicas.afiliado = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 4).toString();
        Detalle_Practicas.obrasocial = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString();
        Detalle_Practicas.fecha = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 7).toString();
        Detalle_Practicas.total = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 9).toString();
        Detalle_Practicas.dni = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 11).toString();
        Detalle_Practicas.observacion = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 12).toString();

        new Detalle_Practicas(null, true).setVisible(true);
    }

    void cargarPracticas(int idObraSocial) {
        conexion = new ConexionMariaDB();
        conexion.EstablecerConexion();
        listaPracticas = new ArrayList<>();
        Practica.cargarPracticasIosfa(conexion.getConnection(), listaPracticas, idObraSocial);
        conexion.cerrarConexion();
    }

    ///////////////////////////////////////HILO Ordenes///////////////////////////////////////////////
    public class HiloOrdenes extends Thread {

        JProgressBar progreso;

        public HiloOrdenes(JProgressBar progreso1) {
            super();
            this.progreso = progreso1;
        }

        public void run() {
            new Thread(hilo90).start();
            String periodo2 = txtaño1.getText() + txtmes1.getText();
            periodo = periodo2;
            String[] titulos = {"Orden", "Periodo", "Obra Social", "N° Afiliado", "Nombre Afiliado", "Matricula Prescripcion", "N° Orden Prescripcion", "Fecha Orden", "Colegiado", "Total", "Estado", "Dni", "Obervacion", "Nº"};//estos seran los titulos de la tabla.            
            String[] datos = new String[14];

            model_tabla_facturacion = new DefaultTableModel(null, titulos) {
                ////Celdas no editables////////
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            if (Integer.valueOf(periodo2) >= (periodo_backup)) {
                ConexionMariaDB cc = new ConexionMariaDB();
                Connection cn = cc.Conectar();
                try {
                    Statement St = cn.createStatement();
                    //ResultSet Rs = St.executeQuery("SELECT ordenes.id_orden, ordenes.periodo,obrasocial.razonsocial_obrasocial,ordenes.numero_afiliado, ordenes.nombre_afiliado,ordenes.matricula_prescripcion,ordenes.numero_orden,ordenes.fecha_orden,colegiados.matricula_colegiado,round(ordenes.total,2),ordenes.estado_orden, ordenes.observacion,ordenes.dni_afiliado FROM colegiados INNER JOIN ordenes ON colegiados.id_colegiados = ordenes.id_colegiados INNER JOIN obrasocial ON obrasocial.id_obrasocial = ordenes.id_obrasocial WHERE (colegiados.id_colegiados=" + id_usuario + "  AND ordenes.periodo=" + periodo2 + ")");
                    int i = 1;
                    //ResultSet Rs = St.executeQuery("SELECT id_orden, periodo,razonsocial_obrasocial,numero_afiliado, nombre_afiliado,matricula_prescripcion,numero_orden,fecha_orden,matricula_colegiado,ordenes_total,estado_orden, observacion,dni_afiliado,coseguro FROM vista_ordenes WHERE (id_colegiados=" + id_usuario + "  AND periodo=" + periodo2 + ")");
                    ResultSet Rs = St.executeQuery("SELECT  ordenes.id_orden,  ordenes.periodo, obrasocial.razonsocial_obrasocial ,ordenes.numero_afiliado,\n"
                            + "  ordenes.nombre_afiliado, ordenes.matricula_prescripcion,ordenes.numero_orden,ordenes.fecha_orden,colegiados.matricula_colegiado,\n"
                            + "  SUM(detalle_ordenes.precio_practica) AS ordenes_total,ordenes.estado_orden,  ordenes.observacion,\n"
                            + "   ordenes.dni_afiliado,ordenes.coseguro\n"
                            + " FROM\n"
                            + "colegiados\n"
                            + "    JOIN ordenes ON colegiados.id_colegiados = ordenes.id_colegiados\n"
                            + "    JOIN detalle_ordenes ON detalle_ordenes.id_orden = ordenes.id_orden\n"
                            + "    JOIN obrasocial ON obrasocial.id_obrasocial = ordenes.id_obrasocial\n"
                            + "WHERE\n"
                            + "  detalle_ordenes.estado=0 and ordenes.id_colegiados=" + id_usuario + " and periodo=" + periodo2 + " \n"
                            + "GROUP BY\n"
                            + "  ordenes.id_orden");
                    String estado = "";
                    while (Rs.next()) {
                        if (Rs.getInt(11) == 0) {
                            estado = "ANULADA";
                        }
                        if (Rs.getInt(11) == 1) {
                            estado = "OK";
                        }
                        if (Rs.getInt(11) == 2) {
                            estado = "OBSERVADA";
                        }
                        if (Rs.getInt(11) == 3) {
                            estado = "AUDITORIA";
                        }
                        datos[0] = Rs.getString(1);
                        datos[1] = Rs.getString(2);
                        datos[2] = Rs.getString(3);
                        datos[3] = Rs.getString(4);
                        datos[4] = Rs.getString(5);
                        datos[5] = Rs.getString(6);
                        datos[6] = Rs.getString(7);
                        datos[7] = Rs.getString(8);
                        datos[8] = Rs.getString(9);
                        datos[9] = Rs.getString(10);
                        datos[10] = estado;
                        datos[11] = Rs.getString(13);
                        if (Rs.getString(12) != null) {
                            datos[12] = Rs.getString(12);
                        } else {
                            datos[12] = "";
                        }
                        datos[13] = completarceros(String.valueOf(i), 4);
                        model_tabla_facturacion.addRow(datos);
                        i++;
                    }
                    tablaordenes.setModel(model_tabla_facturacion);
                    tablaordenes.getColumnModel().getColumn(1).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(1).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(1).setPreferredWidth(0);
                    /////////////////////////////////////////////////////////////////
                    tablaordenes.getColumnModel().getColumn(8).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(8).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(8).setPreferredWidth(0);
                    /////////////////////////////////////////////////////////////////////                            
                    tablaordenes.getColumnModel().getColumn(11).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(11).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(11).setPreferredWidth(0);
                    ///////////////////////////////////////////////////////////////////////
                    tablaordenes.getColumnModel().getColumn(12).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(12).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(12).setPreferredWidth(0);
                    alinear();
                    tablaordenes.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(4).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(5).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(6).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(7).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(8).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(9).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(10).setCellRenderer(alinearCentro);
                    progreso.setValue(100);
                    txtordenes.requestFocus();
                    btnbuscar.setEnabled(true);
                    sorter = new TableRowSorter(model_tabla_facturacion);
                    sorter.setRowFilter(RowFilter.regexFilter(".*.*"));
                    tablaordenes.setRowSorter(sorter);
                    cn.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                hilo90.stop();
                cargatotalesordenesfacturacion();
                cargatotales();
            } else {
                ConexionMariaDBBackup cc = new ConexionMariaDBBackup();
                Connection cn2 = cc.Conectar();
                try {
                    Statement St = cn2.createStatement();
                    ResultSet Rs = St.executeQuery("SELECT  ordenes.id_orden,  ordenes.periodo, obrasocial.razonsocial_obrasocial ,ordenes.numero_afiliado,\n"
                            + "  ordenes.nombre_afiliado, ordenes.matricula_prescripcion,ordenes.numero_orden,ordenes.fecha_orden,colegiados.matricula_colegiado,\n"
                            + "  SUM(detalle_ordenes.precio_practica) AS ordenes_total,ordenes.estado_orden,  ordenes.observacion,\n"
                            + "   ordenes.dni_afiliado,ordenes.coseguro\n"
                            + " FROM\n"
                            + "colegiados\n"
                            + "    JOIN ordenes ON colegiados.id_colegiados = ordenes.id_colegiados\n"
                            + "    JOIN detalle_ordenes ON detalle_ordenes.id_orden = ordenes.id_orden\n"
                            + "    JOIN obrasocial ON obrasocial.id_obrasocial = ordenes.id_obrasocial\n"
                            + "WHERE\n"
                            + "  detalle_ordenes.estado=0 and ordenes.id_colegiados=" + id_usuario + " and periodo=" + periodo2 + " \n"
                            + "GROUP BY\n"
                            + "  ordenes.id_orden");
                    String estado = "";
                    int i = 1;
                    while (Rs.next()) {
                        if (Rs.getInt(11) == 0) {
                            estado = "ANULADA";
                        }
                        if (Rs.getInt(11) == 1) {
                            estado = "OK";
                        }
                        if (Rs.getInt(11) == 2) {
                            estado = "OBSERVADA";
                        }
                        if (Rs.getInt(11) == 3) {
                            estado = "AUDITORIA";
                        }
                        datos[0] = Rs.getString(1);
                        datos[1] = Rs.getString(2);
                        datos[2] = Rs.getString(3);
                        datos[3] = Rs.getString(4);
                        datos[4] = Rs.getString(5);
                        datos[5] = Rs.getString(6);
                        datos[6] = Rs.getString(7);
                        datos[7] = Rs.getString(8);
                        datos[8] = Rs.getString(9);
                        datos[9] = Rs.getString(10);
                        datos[10] = estado;
                        datos[11] = Rs.getString(13);
                        if (Rs.getString(12) != null) {
                            datos[12] = Rs.getString(12);
                        } else {
                            datos[12] = "";
                        }
                        datos[13] = completarceros(String.valueOf(i), 4);
                        model_tabla_facturacion.addRow(datos);
                        i++;
                    }
                    tablaordenes.setModel(model_tabla_facturacion);
                    tablaordenes.getColumnModel().getColumn(1).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(1).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(1).setPreferredWidth(0);
                    /////////////////////////////////////////////////////////////////
                    tablaordenes.getColumnModel().getColumn(8).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(8).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(8).setPreferredWidth(0);
                    /////////////////////////////////////////////////////////////////////                            
                    tablaordenes.getColumnModel().getColumn(11).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(11).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(11).setPreferredWidth(0);
                    ///////////////////////////////////////////////////////////////////////
                    tablaordenes.getColumnModel().getColumn(12).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(12).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(12).setPreferredWidth(0);
                    alinear();
                    tablaordenes.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(4).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(5).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(6).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(7).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(8).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(9).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(10).setCellRenderer(alinearCentro);
                    progreso.setValue(100);
                    cargatotales();
                    txtordenes.requestFocus();
                    btnbuscar.setEnabled(true);
                    sorter = new TableRowSorter(model_tabla_facturacion);
                    sorter.setRowFilter(RowFilter.regexFilter(".*.*"));
                    tablaordenes.setRowSorter(sorter);
                    cargatotalesordenesfacturacion();
                    cn2.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);

                }
                hilo90.stop();
            }
            bandera = 0;
        }

        public void pausa(int mlSeg) {
            try {
                Thread.sleep(mlSeg);
            } catch (Exception e) {
            }

        }

    }

    void creamyModel() {
        myModel = new DefaultTableModel() {
            ////Celdas no editables////////
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        myModel.addColumn("Matricula");
        myModel.addColumn("Fecha Orden");
        myModel.addColumn("Cod. OS");
        myModel.addColumn("Nombre Paciente");
        myModel.addColumn("Numero de Afiliado");
        myModel.addColumn("Numero de Orden");
        myModel.addColumn("Fecha Prescripsión");
        myModel.addColumn("Mat. Presc.");
        myModel.addColumn("DNI");
        myModel.addColumn("Coseguro");
        int i = 1;
        while (i <= 100) {
            myModel.addColumn("P" + String.valueOf(i));
            i++;
        }

    }

    /////////////////////////////////////////Leo archivo Text///////////////////////////////////////////////    
    void leer_archivo() {

        creamyModel();
        tablaordenes1.setModel(myModel);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(url));
            String line = br.readLine();
            int row;
            for (row = 0; row < 11; row++) {
                for (int column = 0; column < 5; column++) {
                    while (line != null) {
                        String[] rowfields = line.split(";");
                        myModel.addRow(rowfields);

                        line = br.readLine();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        tablaordenes1.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaordenes1.getColumnModel().getColumn(0).setMinWidth(0);
        tablaordenes1.getColumnModel().getColumn(0).setPreferredWidth(0);
        int i = 10;
        while (i < 110) {
            tablaordenes1.getColumnModel().getColumn(i).setMaxWidth(0);
            tablaordenes1.getColumnModel().getColumn(i).setMinWidth(0);
            tablaordenes1.getColumnModel().getColumn(i).setPreferredWidth(0);
            i++;
        }
    }

    public class Hiloobrasocial extends Thread {

        JProgressBar progreso;

        public Hiloobrasocial(JProgressBar progreso1) {
            super();
            this.progreso = progreso1;
        }

        public void run() {

            cursor();
            ////////////////////carga tabla///////////////////////////////////////////////            
            DecimalFormat df = new DecimalFormat("0.00");
            String periodo2 = txtaño1.getText() + txtmes1.getText();
            String[] titulos = {"Orden", "Periodo", "Obra Social", "N° Afiliado", "Nombre Afiliado", "Matricula Prescripcion", "N° Orden Prescripcion", "Fecha Orden", "Colegiado", "Total", "Estado", "Dni", "Obervacion", "Nº"};//estos seran los titulos de la tabla.            
            String[] datos = new String[14];
            model_tabla_facturacion = new DefaultTableModel(null, titulos) {
                ////Celdas no editables////////
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            if (Integer.valueOf(periodo) >= periodo_backup) {
                ConexionMariaDB cc = new ConexionMariaDB();
                Connection cn = cc.Conectar();
                try {

                    /////////////////////////////////////////////////////
                    Statement st5 = cn.createStatement();
                    ResultSet rs5 = st5.executeQuery("SELECT direccion_laboratorio,localidad_laboratorio FROM colegiados WHERE matricula_colegiado=" + matricula_colegiado);
                    rs5.next();

                    domicilio_lab = rs5.getString(1);
                    localidad_lab = rs5.getString(2);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                try {
                    Statement St = cn.createStatement();
                    //ResultSet Rs = St.executeQuery("SELECT ordenes.id_orden, ordenes.periodo,obrasocial.razonsocial_obrasocial,ordenes.numero_afiliado, ordenes.nombre_afiliado,ordenes.matricula_prescripcion,ordenes.numero_orden,ordenes.fecha_orden,colegiados.matricula_colegiado,round(ordenes.total,2),ordenes.estado_orden, ordenes.observacion,ordenes.dni_afiliado  FROM colegiados INNER JOIN ordenes ON colegiados.id_colegiados = ordenes.id_colegiados INNER JOIN obrasocial ON obrasocial.id_obrasocial = ordenes.id_obrasocial WHERE colegiados.id_colegiados=" + id_usuario + "  AND (ordenes.periodo=" + periododjj + " AND obrasocial.id_obrasocial=" + idobraimprime + ")");
                    ResultSet Rs = St.executeQuery("SELECT  ordenes.id_orden,  ordenes.periodo, obrasocial.razonsocial_obrasocial ,ordenes.numero_afiliado,\n"
                            + "  ordenes.nombre_afiliado, ordenes.matricula_prescripcion,ordenes.numero_orden,ordenes.fecha_orden,colegiados.matricula_colegiado,\n"
                            + "  SUM(detalle_ordenes.precio_practica) AS ordenes_total,ordenes.estado_orden,  ordenes.observacion,\n"
                            + "   ordenes.dni_afiliado,ordenes.coseguro\n"
                            + " FROM\n"
                            + "colegiados\n"
                            + "    JOIN ordenes ON colegiados.id_colegiados = ordenes.id_colegiados\n"
                            + "    JOIN detalle_ordenes ON detalle_ordenes.id_orden = ordenes.id_orden\n"
                            + "    JOIN obrasocial ON obrasocial.id_obrasocial = ordenes.id_obrasocial\n"
                            + "WHERE detalle_ordenes.estado=0 and ordenes.estado_orden!=0 and ordenes.id_colegiados=" + id_usuario + " and periodo=" + periododjj + " and ordenes.id_obrasocial=" + idobraimprime + "\n"
                            + " GROUP BY ordenes.id_orden");

                    String estado = "";
                    int i = 1;

                    while (Rs.next()) {
                        if (Rs.getInt(11) != 0) {
                            if (Rs.getInt(11) != 0) {
                                if (Rs.getInt(11) == 3) {
                                    estado = "AUDITORIA";
                                } else {
                                    estado = "OK";
                                }
                            } else {
                                estado = "ANULADA";
                            }
                            datos[0] = Rs.getString(1);
                            datos[1] = Rs.getString(2);
                            datos[2] = Rs.getString(3);
                            datos[3] = Rs.getString(4);
                            datos[4] = Rs.getString(5);
                            datos[5] = Rs.getString(6);
                            datos[6] = Rs.getString(7);
                            datos[7] = Rs.getString(8);
                            datos[8] = Rs.getString(9);
                            datos[9] = Rs.getString(10);
                            datos[10] = estado;
                            datos[11] = Rs.getString(13);
                            if (Rs.getString(12) != null) {
                                datos[12] = Rs.getString(12);
                            } else {
                                datos[12] = "";
                            }
                            datos[13] = completarceros(String.valueOf(i), 4);
                            model_tabla_facturacion.addRow(datos);
                            i++;
                        }
                    }
                    System.out.println("error 1");
                    tablaordenes.setModel(model_tabla_facturacion);
                    tablaordenes.getColumnModel().getColumn(1).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(1).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(1).setPreferredWidth(0);
                    tablaordenes.getColumnModel().getColumn(8).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(8).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(8).setPreferredWidth(0);
                    tablaordenes.getColumnModel().getColumn(11).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(11).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(11).setPreferredWidth(0);
                    tablaordenes.getColumnModel().getColumn(12).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(12).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(12).setPreferredWidth(0);
                    alinear();
                    tablaordenes.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(4).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(5).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(6).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(7).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(8).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(9).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(10).setCellRenderer(alinearCentro);
                    //cargatotalesordenesfacturacion();
                    txtordenes.requestFocus();
                    btnbuscar.setEnabled(true);
                    sorter = new TableRowSorter(model_tabla_facturacion);
                    sorter.setRowFilter(RowFilter.regexFilter(".*.*"));
                    tablaordenes.setRowSorter(sorter);
                    cargatotales();
                    cargatotalesordenesfacturacion();
                } catch (SQLException ex) {
                    cursor2();
                    JOptionPane.showMessageDialog(null, ex);

                }
                System.out.println("error 2");
                /////////////////////////////imprime reporte////////////////////////////////
                int n, i = 0, pacientes = 0, practicas = 0, band = 0;
                LinkedList<camposordenes_osde> Resultados = new LinkedList<camposordenes_osde>();
                LinkedList<camposordenes_ss> Resultados_ss = new LinkedList<camposordenes_ss>();
                Resultados.clear();
                Resultados_ss.clear();
                String orden = "";
                double coseguro = 0.00;
                int cantidad = tablaordenes.getRowCount(), contador = 0, codigo_obra = 0;
                progreso.setMaximum(cantidad);
                double pesos = 0, centavos = 0;
                double importetotal = 0, totalconcoseguro = 0.00, coseguro_ss = 0.00;
                double totalpesospracticas = 0, total = Double.valueOf(txttotal.getText());
                if (!ObraSocial.equals("") && tablaordenes.getRowCount() != 0) {
                    try {
                        ///proceso de generacion de facturacion
                        try {
                            Statement st = cn.createStatement();
                            ResultSet rs = null;
                            if (idobraimprime == 11 || idobraimprime == 12
                                    || idobraimprime == 14 || idobraimprime == 90 || idobraimprime == 100
                                    || idobraimprime == 103 || idobraimprime == 15 || idobraimprime == 113) {

                                String sql = "SELECT  detalle_ordenes.*, ordenes.*, colegiados.matricula_colegiado,obrasocial.int_codigo_obrasocial,obrasocial.id_obrasocial,obrasocial.id_obrasocial,round(detalle_ordenes.precio_practica/obrasocial.importeunidaddearancel_obrasocial,1) as UB\n"
                                        + "FROM ordenes\n"
                                        + "INNER JOIN colegiados  USING (id_colegiados)\n"
                                        + "INNER JOIN detalle_ordenes USING (id_orden)\n"
                                        + "INNER JOIN obrasocial USING (id_obrasocial)\n"
                                        + "WHERE ordenes.periodo=" + periododjj + "  AND colegiados.id_colegiados=" + id_usuario + "  AND obrasocial.id_obrasocial=" + idobraimprime + "\n"
                                        + "AND ordenes.estado_orden!=0 and detalle_ordenes.estado=0 ";

                                rs = st.executeQuery(sql);
                            } else {
                                String sql = "SELECT  detalle_ordenes.*, ordenes.*, colegiados.matricula_colegiado,obrasocial.int_codigo_obrasocial,obrasocial.id_obrasocial,obrasocial.id_obrasocial\n"
                                        + "FROM ordenes\n"
                                        + "INNER JOIN colegiados  USING (id_colegiados)\n"
                                        + "INNER JOIN detalle_ordenes USING (id_orden)\n"
                                        + "INNER JOIN obrasocial USING (id_obrasocial)\n"
                                        + "WHERE ordenes.periodo=" + periododjj + "  AND colegiados.id_colegiados=" + id_usuario + "  AND obrasocial.id_obrasocial=" + idobraimprime + "\n"
                                        + "AND ordenes.estado_orden!=0 and detalle_ordenes.estado=0 ";
                                rs = st.executeQuery(sql);
                            }

                            total = 0;
                            int id_ordenes = 0;
                            if (idobraimprime == 89 || idobraimprime == 108 || idobraimprime == 21) {
                                Statement st7 = cn.createStatement();
                                String sql7 = "SELECT  sum(coseguro)\n"
                                        + "FROM ordenes\n"
                                        + "WHERE periodo=" + periodo + " and estado_orden=1 and id_obrasocial=89 and id_colegiados=" + id_usuario;
                                ResultSet rs7 = st7.executeQuery(sql7);
                                if (rs7.next()) {
                                    coseguro = Redondeardosdigitos(rs7.getDouble(1));
                                } else {
                                    coseguro = 0;
                                }
                            }
                            System.out.println("error 3");
                            while (rs.next()) {
                                CodObra = CodObra.replace(" ", "");
                                codigo_obra = Integer.valueOf(CodObra);
                                if (idobraimprime == 11 || idobraimprime == 12
                                        || idobraimprime == 14 || idobraimprime == 90 || idobraimprime == 100
                                        || idobraimprime == 103 || idobraimprime == 15 || idobraimprime == 113) {
                                    camposordenes_ss tipo_ss;
                                    //System.out.println(rs.getString("id_orden"));
                                    if (id_ordenes == 0) {
                                        tipo_ss = new camposordenes_ss(rs.getString("numero_afiliado"), rs.getString("nombre_afiliado").trim(), rs.getString("numero_orden"), rs.getString("cod_practica"), rs.getString("nombre_practica"), df.format(rs.getDouble("precio_practica")), rs.getString("cod_practica_fac"), rs.getString("fecha_orden"), df.format(rs.getDouble("UB")), df.format(rs.getDouble("detalle_ordenes.coseguro")));
                                        id_ordenes = rs.getInt("id_orden");
                                        pacientes = pacientes + 1;
                                    } else {
                                        if (id_ordenes != rs.getInt("id_orden")) {
                                            tipo_ss = new camposordenes_ss(rs.getString("numero_afiliado"), rs.getString("nombre_afiliado").trim(), rs.getString("numero_orden"), rs.getString("cod_practica"), rs.getString("nombre_practica"), df.format(rs.getDouble("precio_practica")), rs.getString("cod_practica_fac"), rs.getString("fecha_orden"), df.format(rs.getDouble("UB")), df.format(rs.getDouble("detalle_ordenes.coseguro")));
                                            band = 1;
                                            id_ordenes = rs.getInt("id_orden");
                                            pacientes = pacientes + 1;
                                        } else {
                                            tipo_ss = new camposordenes_ss("''", "''", "''", rs.getString("cod_practica"), rs.getString("nombre_practica"), df.format(rs.getDouble("precio_practica")), rs.getString("cod_practica_fac"), "''", df.format(rs.getDouble("UB")), df.format(rs.getDouble("detalle_ordenes.coseguro")));
                                        }
                                    }
                                    total = Redondeardosdigitos(total);
                                    total = total + Redondeardosdigitos(rs.getDouble("precio_practica"));
                                    coseguro = Redondeardosdigitos(coseguro);
                                    coseguro = coseguro + Redondeardosdigitos(rs.getDouble("detalle_ordenes.coseguro"));
                                    practicas = practicas + 1;
                                    Resultados_ss.add(tipo_ss);
                                    System.out.println(total);
                                } else {
                                    camposordenes_osde tipo;
                                    if (id_ordenes == 0) {
                                        tipo = new camposordenes_osde(rs.getString("numero_afiliado"), rs.getString("nombre_afiliado").trim(), rs.getString("numero_orden"), rs.getString("cod_practica"), rs.getString("nombre_practica"), df.format(rs.getDouble("precio_practica")), rs.getString("cod_practica_fac"), rs.getString("fecha_orden"));
                                        id_ordenes = rs.getInt("id_orden");
                                        pacientes = pacientes + 1;
                                    } else {
                                        if (id_ordenes != rs.getInt("id_orden")) {
                                            tipo = new camposordenes_osde(rs.getString("numero_afiliado"), rs.getString("nombre_afiliado").trim(), rs.getString("numero_orden"), rs.getString("cod_practica"), rs.getString("nombre_practica"), df.format(rs.getDouble("precio_practica")), rs.getString("cod_practica_fac"), rs.getString("fecha_orden"));
                                            band = 1;
                                            id_ordenes = rs.getInt("id_orden");
                                            pacientes = pacientes + 1;
                                        } else {
                                            tipo = new camposordenes_osde("''", "''", "''", rs.getString("cod_practica"), rs.getString("nombre_practica"), df.format(rs.getDouble("precio_practica")), rs.getString("cod_practica_fac"), "''");
                                        }
                                    }
                                    total = Redondeardosdigitos(total);
                                    total = total + Redondeardosdigitos(rs.getDouble("precio_practica"));
                                    practicas = practicas + 1;
                                    Resultados.add(tipo);
                                }
                                contador++;
                                progreso.setValue(contador);
                            }
                            cn.close();
                        } catch (Exception e) {
                            cursor2();
                            JOptionPane.showMessageDialog(null, e);
                        }

                        pesos = Redondeardosdigitos(total);
                        importetotal = pesos;
                        totalconcoseguro = importetotal - coseguro;

                        System.out.println(importetotal);

                        ///////////////////////////////////////////////////////////////////////////////////
                        String mes = "", año = periodo.substring(0, 4), cadena = periodo.substring(4, 6);
                        if (cadena.equals("01")) {
                            mes = "Enero";
                        }
                        if (cadena.equals("02")) {
                            mes = "Febrero";
                        }
                        if (cadena.equals("03")) {
                            mes = "Marzo";
                        }
                        if (cadena.equals("04")) {
                            mes = "Abril";
                        }
                        if (cadena.equals("05")) {
                            mes = "Mayo";
                        }
                        if (cadena.equals("06")) {
                            mes = "Junio";
                        }
                        if (cadena.equals("07")) {
                            mes = "Julio";
                        }
                        if (cadena.equals("08")) {
                            mes = "Agosto";
                        }
                        if (cadena.equals("09")) {
                            mes = "Septiembre";
                        }
                        if (cadena.equals("10")) {
                            mes = "Octubre";
                        }
                        if (cadena.equals("11")) {
                            mes = "Noviembre";
                        }
                        if (cadena.equals("12")) {
                            mes = "Diciembre";
                        }
                        periodo2 = mes + " " + año;
                        centavos = Redondearcentavos(pesos);
                        pesos = Redondeardosdigitos(pesos - centavos / 100);
                        total_pesos_letras = NumberToLetterConverter.convertNumberToLetter(pesos);
                        total_centavos_letras = NumberToLetterConverter.convertNumberToLetter(centavos);
                        ///////////////////////////////////////////////////////////////////////////////////////
                        //JDialog viewer = new JDialog(new javax.swing.JFrame(), "Reporte", true);
                        JFrame viewer = new JFrame();
                        viewer.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
                        viewer.setSize(800, 600);
                        viewer.setLocationRelativeTo(null);//
                        ////////////////////////////////////////////////////////////////////////////////////////////////

                        //////////////////////////////Resumen //////////////////////////////////////
                        if (matricula_colegiado.equals("20012") || matricula_colegiado.equals("30012") || matricula_colegiado.equals("31360")) {
                            int dialogButton;
                            int opcion = JOptionPane.YES_NO_OPTION;
                            dialogButton = JOptionPane.showConfirmDialog(null, "Desea generar el resumen por terminal de carga", "", opcion);
                            if (dialogButton == 0) {
                                resumen_ordenes();
                            }
                        }
                        ///////////////////////////////////////////////////////////////////////////
                        System.out.println("error 4");
                        Map parametros = new HashMap();
                        parametros.put("matricula", matricula_colegiado);
                        parametros.put("periodo", periododjj);
                        parametros.put("fecha", fecha2);
                        parametros.put("obra_social", ObraSocial);
                        parametros.put("num_obra_social", CodObra);
                        parametros.put("laboratorio", nombre_colegiado);
                        parametros.put("domicilio_lab", domicilio_lab);
                        parametros.put("localidad", localidad_lab);
                        parametros.put("pacientes", String.valueOf(pacientes));
                        parametros.put("practicas", String.valueOf(practicas));

                        if (CodObra.equals("513") || CodObra.equals("511")) {  ////////comparo para saber que pdf realizar 511 - MEDIFE
                            try {
                                pesos = pesos * 1.105;
                                centavos = Redondearcentavos(pesos);
                                pesos = Redondeardosdigitos(pesos - centavos / 100);
                                total_pesos_letras = NumberToLetterConverter.convertNumberToLetter(pesos);
                                total_centavos_letras = NumberToLetterConverter.convertNumberToLetter(centavos);
                                JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Ordenes_IVA.jasper"));
                                ///////////////////////////////////////////////////C:\Users\Lucas\Documents\NetBeansProjects\colegio bioquimicos\src\Reportes
                                parametros.put("total_letras_pesos", total_pesos_letras + " PESOS");
                                parametros.put("total_letras_centavos", total_centavos_letras + " CENTAVOS");
                                parametros.put("Subtotal", df.format(importetotal));
                                parametros.put("IVA", df.format(importetotal * 0.21));
                                parametros.put("total", df.format(importetotal * 1.21));
                                JasperPrint jPrint = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(Resultados));
                                ImagenPDF pdf = new ImagenPDF();
                                pdf.createPdf(jPrint, "C:\\Descargas-CBT\\" + periodo + "-" + matricula_colegiado + "-" + CodObra);
                                JasperViewer jv = new JasperViewer(jPrint, false);
                                viewer.getContentPane().add(jv.getContentPane());
                                viewer.setVisible(true);

                            } catch (JRException ex) {
                                System.err.println("Error iReport: " + ex.getMessage());
                            }
                        }

                        if (CodObra.equals("50015") || CodObra.equals("40813") || CodObra.equals("2700")) {
                            try {
                                JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Ordenes_coseguro.jasper"));
                                parametros.put("total_letras_pesos", total_pesos_letras + " PESOS");
                                parametros.put("total_letras_centavos", total_centavos_letras + " CENTAVOS");
                                parametros.put("total", df.format(importetotal));
                                ///Coseguro
                                parametros.put("coseguro", df.format(coseguro));
                                parametros.put("totalconcoseguro", df.format(totalconcoseguro));
                                JasperPrint jPrint = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(Resultados));

                                ImagenPDF pdf = new ImagenPDF();
                                pdf.createPdf(jPrint, "C:\\Descargas-CBT\\" + periodo + "-" + matricula_colegiado + "-" + CodObra);
                                JasperViewer jv = new JasperViewer(jPrint, false);
                                viewer.getContentPane().add(jv.getContentPane());
                                viewer.setVisible(true);

                            } catch (JRException ex) {
                                System.err.println("Error iReport: " + ex.getMessage());
                            }
                        }
                        if (CodObra.equals("1800") || CodObra.equals("1801") || CodObra.equals("1802")
                                || CodObra.equals("1803") || CodObra.equals("1804") || CodObra.equals("1805")
                                || CodObra.equals("1806") || CodObra.equals("1810") || CodObra.equals("1807")) {
                            try {
                                pesos = totalconcoseguro;
                                centavos = Redondearcentavos(pesos);
                                pesos = Redondeardosdigitos(pesos - centavos / 100);
                                total_pesos_letras = NumberToLetterConverter.convertNumberToLetter(pesos);
                                total_centavos_letras = NumberToLetterConverter.convertNumberToLetter(centavos);
                                JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Ordenes_SS.jasper"));
                                ///////////////////////////////////////////////////C:\Users\Lucas\Documents\NetBeansProjects\colegio bioquimicos\src\Reportes
                                parametros.put("total_letras_pesos", total_pesos_letras + " PESOS");
                                parametros.put("total_letras_centavos", total_centavos_letras + " CENTAVOS");
                                parametros.put("total", df.format(importetotal));
                                ///Coseguro
                                parametros.put("cuit", cuit);
                                parametros.put("coseguro", df.format(coseguro));
                                parametros.put("totalconcoseguro", df.format(totalconcoseguro));
                                JasperPrint jPrint = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(Resultados_ss));
                                //JasperExportManager.exportReportToPdfFile(jPrint, "C:\\Descargas-CBT\\" + periodo + "-" + matricula_colegiado + "-" + CodObra + ".pdf");
                                // JasperExportManager.exportReportToPdfFile(jPrint, "J:\\PDF-FACTURACION\\" + periodo + "-" + matricula + "-" + cod_obra + ".pdf");
                                //JasperViewer.viewReport(jPrint, true);
                                ImagenPDF pdf = new ImagenPDF();
                                pdf.createPdf(jPrint, "C:\\Descargas-CBT\\" + periodo + "-" + matricula_colegiado + "-" + CodObra);
                                JasperViewer jv = new JasperViewer(jPrint, false);
                                viewer.getContentPane().add(jv.getContentPane());
                                viewer.setVisible(true);

                            } catch (JRException ex) {
                                System.err.println("Error iReport: " + ex.getMessage());
                            }
                        }
                        if (CodObra.equals("3100") || CodObra.equals("3101") || CodObra.equals("3102")) {
                            try {
                                JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Ordenes_OSDE.jasper"));
                                ///////////////////////////////////////////////////C:\Users\Lucas\Documents\NetBeansProjects\colegio bioquimicos\src\Reportes
                                parametros.put("pacientes", String.valueOf(pacientes));
                                parametros.put("practicas", String.valueOf(practicas));
                                parametros.put("total_letras_pesos", total_pesos_letras + " PESOS");
                                parametros.put("total_letras_centavos", total_centavos_letras + " CENTAVOS");
                                parametros.put("total", df.format(importetotal));
                                JasperPrint jPrint = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(Resultados));
                                //JasperExportManager.exportReportToPdfFile(jPrint, "C:\\Descargas-CBT\\" + periodo + "-" + matricula_colegiado + "-" + CodObra + ".pdf");
                                // JasperExportManager.exportReportToPdfFile(jPrint, "J:\\PDF-FACTURACION\\" + periodo + "-" + matricula + "-" + cod_obra + ".pdf");
                                ///JasperViewer.viewReport(jPrint, true);
                                ImagenPDF pdf = new ImagenPDF();
                                pdf.createPdf(jPrint, "C:\\Descargas-CBT\\" + periodo + "-" + matricula_colegiado + "-" + CodObra);
                                JasperViewer jv = new JasperViewer(jPrint, false);
                                viewer.getContentPane().add(jv.getContentPane());
                                viewer.setVisible(true);

                            } catch (JRException ex) {
                                System.err.println("Error iReport: " + ex.getMessage());
                            }
                        }
                        if (CodObra.equals("50015") || CodObra.equals("1800") || CodObra.equals("1801") || CodObra.equals("1802")
                                || CodObra.equals("1803") || CodObra.equals("1804") || CodObra.equals("1805")
                                || CodObra.equals("1806") || CodObra.equals("1810") || CodObra.equals("1807") || CodObra.equals("511")
                                || CodObra.equals("40813") || CodObra.equals("2700")) {

                        } else {
                            System.out.println(CodObra);
                            try {
                                JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Ordenes_Fecha.jasper"));
                                ///////////////////////////////////////////////////C:\Users\Lucas\Documents\NetBeansProjects\colegio bioquimicos\src\Reportes
                                parametros.put("pacientes", String.valueOf(pacientes));
                                parametros.put("practicas", String.valueOf(practicas));
                                parametros.put("total_letras_pesos", total_pesos_letras + " PESOS");
                                parametros.put("total_letras_centavos", total_centavos_letras + " CENTAVOS");
                                parametros.put("total", df.format(importetotal));
                                JasperPrint jPrint = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(Resultados));

                                ImagenPDF pdf = new ImagenPDF();
                                pdf.createPdf(jPrint, "C:\\Descargas-CBT\\" + periododjj + "-" + matricula_colegiado + "-" + CodObra);
                                JasperViewer jv = new JasperViewer(jPrint, false);
                                viewer.getContentPane().add(jv.getContentPane());
                                viewer.setVisible(true);
                                cursor2();
                            } catch (JRException ex) {
                                cursor2();
                                System.err.println("Error iReport: " + ex.getMessage());
                            }
                        }
                        cursor2();
                    } catch (Exception e) {
                        cursor2();
                        JOptionPane.showMessageDialog(null, e);
                    }
                    cursor2();
                }
                ////////////////////////////////////////////////////////////////////////////////
                System.out.println("error 5");
            } else {
                System.out.println("error 6");
                ConexionMariaDBBackup cc = new ConexionMariaDBBackup();
                Connection cn2 = cc.Conectar();

                try {

                    /////////////////////////////////////////////////////
                    Statement st5 = cn2.createStatement();
                    ResultSet rs5 = st5.executeQuery("SELECT direccion_laboratorio,localidad_laboratorio FROM colegiados WHERE matricula_colegiado=" + matricula_colegiado);
                    rs5.next();

                    domicilio_lab = rs5.getString(1);
                    localidad_lab = rs5.getString(2);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

                try {
                    Statement St = cn2.createStatement();
                    //ResultSet Rs = St.executeQuery("SELECT ordenes.id_orden, ordenes.periodo,obrasocial.razonsocial_obrasocial,ordenes.numero_afiliado, ordenes.nombre_afiliado,ordenes.matricula_prescripcion,ordenes.numero_orden,ordenes.fecha_orden,colegiados.matricula_colegiado,round(ordenes.total,2),ordenes.estado_orden, ordenes.observacion,ordenes.dni_afiliado  FROM colegiados INNER JOIN ordenes ON colegiados.id_colegiados = ordenes.id_colegiados INNER JOIN obrasocial ON obrasocial.id_obrasocial = ordenes.id_obrasocial WHERE colegiados.id_colegiados=" + id_usuario + "  AND (ordenes.periodo=" + periododjj + " AND obrasocial.id_obrasocial=" + idobraimprime + ")");
                    ResultSet Rs = St.executeQuery("SELECT  ordenes.id_orden,  ordenes.periodo, obrasocial.razonsocial_obrasocial ,ordenes.numero_afiliado,\n"
                            + "  ordenes.nombre_afiliado, ordenes.matricula_prescripcion,ordenes.numero_orden,ordenes.fecha_orden,colegiados.matricula_colegiado,\n"
                            + "  SUM(detalle_ordenes.precio_practica) AS ordenes_total,ordenes.estado_orden,  ordenes.observacion,\n"
                            + "   ordenes.dni_afiliado,ordenes.coseguro\n"
                            + " FROM\n"
                            + "colegiados\n"
                            + "    JOIN ordenes ON colegiados.id_colegiados = ordenes.id_colegiados\n"
                            + "    JOIN detalle_ordenes ON detalle_ordenes.id_orden = ordenes.id_orden\n"
                            + "    JOIN obrasocial ON obrasocial.id_obrasocial = ordenes.id_obrasocial\n"
                            + "WHERE\n"
                            + "  detalle_ordenes.estado=0 and ordenes.id_colegiados=" + id_usuario + " and periodo=" + periodo2 + " and ordenes.id_obrasocial=" + idobraimprime + "\n"
                            + "GROUP BY\n"
                            + "  ordenes.id_orden");
                    String estado = "";
                    int i = 1;
                    while (Rs.next()) {
                        if (Rs.getInt(11) == 1) {
                            if (Rs.getInt(11) == 1) {
                                estado = "OK";
                            } else if (Rs.getInt(11) == 3) {
                                estado = "AUDITORIA";
                            } else {
                                estado = "ANULADA";
                            }
                            datos[0] = Rs.getString(1);
                            datos[1] = Rs.getString(2);
                            datos[2] = Rs.getString(3);
                            datos[3] = Rs.getString(4);
                            datos[4] = Rs.getString(5);
                            datos[5] = Rs.getString(6);
                            datos[6] = Rs.getString(7);
                            datos[7] = Rs.getString(8);
                            datos[8] = Rs.getString(9);
                            datos[9] = Rs.getString(10);
                            datos[10] = estado;
                            datos[11] = Rs.getString(13);
                            if (Rs.getString(12) != null) {
                                datos[12] = Rs.getString(12);
                            } else {
                                datos[12] = "";
                            }
                            datos[13] = completarceros(String.valueOf(i), 4);
                            model_tabla_facturacion.addRow(datos);
                            i++;
                        }
                    }

                    tablaordenes.setModel(model_tabla_facturacion);
                    tablaordenes.getColumnModel().getColumn(1).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(1).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(1).setPreferredWidth(0);
                    tablaordenes.getColumnModel().getColumn(8).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(8).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(8).setPreferredWidth(0);
                    tablaordenes.getColumnModel().getColumn(11).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(11).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(11).setPreferredWidth(0);
                    tablaordenes.getColumnModel().getColumn(12).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(12).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(12).setPreferredWidth(0);
                    alinear();
                    tablaordenes.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(4).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(5).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(6).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(7).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(8).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(9).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(10).setCellRenderer(alinearCentro);
                    //cargatotalesordenesfacturacion();
                    txtordenes.requestFocus();
                    btnbuscar.setEnabled(true);
                    sorter = new TableRowSorter(model_tabla_facturacion);
                    sorter.setRowFilter(RowFilter.regexFilter(".*.*"));
                    tablaordenes.setRowSorter(sorter);
                    cargatotales();
                    cargatotalesordenesfacturacion();
                } catch (SQLException ex) {
                    cursor2();
                    JOptionPane.showMessageDialog(null, ex);

                }
                System.out.println("error 7");
                /////////////////////////////imprime reporte////////////////////////////////
                int n, i = 0, pacientes = 0, practicas = 0, band = 0;
                LinkedList<camposordenes_osde> Resultados = new LinkedList<camposordenes_osde>();
                LinkedList<camposordenes_ss> Resultados_ss = new LinkedList<camposordenes_ss>();
                Resultados.clear();
                Resultados_ss.clear();
                String orden = "";
                double coseguro = 0.00;
                int cantidad = tablaordenes.getRowCount(), contador = 0, codigo_obra = 0;
                progreso.setMaximum(cantidad);
                double pesos = 0, centavos = 0;
                double importetotal = 0, totalconcoseguro = 0.00, coseguro_ss = 0.00;
                double totalpesospracticas = 0, total = Double.valueOf(txttotal.getText());
                if (!ObraSocial.equals("") && tablaordenes.getRowCount() != 0) {
                    try {
                        ///proceso de generacion de facturacion
                        try {

                            String sql = "SELECT  detalle_ordenes.*, ordenes.*, colegiados.matricula_colegiado,obrasocial.int_codigo_obrasocial,obrasocial.id_obrasocial,obrasocial.id_obrasocial,detalle_ordenes.precio_practica/obrasocial.importeunidaddearancel_obrasocial as UB\n"
                                    + "FROM ordenes\n"
                                    + "INNER JOIN colegiados  USING (id_colegiados)\n"
                                    + "INNER JOIN detalle_ordenes USING (id_orden)\n"
                                    + "INNER JOIN obrasocial USING (id_obrasocial)\n"
                                    + "WHERE ordenes.periodo=" + periododjj + "  AND colegiados.id_colegiados=" + id_usuario + "  AND obrasocial.id_obrasocial=" + idobraimprime + "\n"
                                    + "AND ordenes.estado_orden!=0 and detalle_ordenes.estado=0 order by ordenes.id_orden, detalle_ordenes.id_detalle";

                            Statement st = cn2.createStatement();
                            ResultSet rs = st.executeQuery(sql);

                            total = 0;
                            int id_ordenes = 0;
                            if (idobraimprime == 89) {
                                Statement st7 = cn2.createStatement();
                                String sql7 = "SELECT  sum(coseguro)\n"
                                        + "FROM ordenes\n"
                                        + "WHERE periodo=" + periodo + " and estado_orden=1 and id_obrasocial=89 and id_colegiados=" + id_usuario;
                                ResultSet rs7 = st7.executeQuery(sql7);
                                if (rs7.next()) {
                                    coseguro = Redondeardosdigitos(rs7.getDouble(1));
                                }
                            }

                            while (rs.next()) {

                                CodObra = CodObra.replace(" ", "");
                                codigo_obra = Integer.valueOf(CodObra);

                                if (codigo_obra == 1800 || codigo_obra == 1801 || codigo_obra == 1802
                                        || codigo_obra == 1803 || codigo_obra == 1804 || codigo_obra == 1805
                                        || codigo_obra == 1806 || codigo_obra == 18010) {
                                    camposordenes_ss tipo_ss;
                                    //System.out.println(rs.getString("id_orden"));
                                    if (id_ordenes == 0) {
                                        tipo_ss = new camposordenes_ss(rs.getString("numero_afiliado"), rs.getString("nombre_afiliado"), rs.getString("numero_orden"), rs.getString("cod_practica"), rs.getString("nombre_practica"), df.format(rs.getDouble("precio_practica")), rs.getString("cod_practica_fac"), rs.getString("fecha_orden"), df.format(rs.getDouble("UB")), df.format(rs.getDouble("detalle_ordenes.coseguro")));
                                        id_ordenes = rs.getInt("id_orden");
                                        pacientes = pacientes + 1;
                                    } else {
                                        if (id_ordenes != rs.getInt("id_orden")) {
                                            tipo_ss = new camposordenes_ss(rs.getString("numero_afiliado"), rs.getString("nombre_afiliado"), rs.getString("numero_orden"), rs.getString("cod_practica"), rs.getString("nombre_practica"), df.format(rs.getDouble("precio_practica")), rs.getString("cod_practica_fac"), rs.getString("fecha_orden"), df.format(rs.getDouble("UB")), df.format(rs.getDouble("detalle_ordenes.coseguro")));
                                            band = 1;
                                            id_ordenes = rs.getInt("id_orden");
                                            pacientes = pacientes + 1;
                                        } else {
                                            tipo_ss = new camposordenes_ss("''", "''", "''", rs.getString("cod_practica"), rs.getString("nombre_practica"), df.format(rs.getDouble("precio_practica")), rs.getString("cod_practica_fac"), "''", df.format(rs.getDouble("UB")), df.format(rs.getDouble("detalle_ordenes.coseguro")));
                                        }
                                    }
                                    total = Redondeardosdigitos(total);
                                    total = total + Redondeardosdigitos(rs.getDouble("precio_practica"));
                                    coseguro = Redondeardosdigitos(coseguro);
                                    coseguro = coseguro + Redondeardosdigitos(rs.getDouble("detalle_ordenes.coseguro"));
                                    practicas = practicas + 1;
                                    Resultados_ss.add(tipo_ss);
                                    System.out.println(total);
                                } else {
                                    camposordenes_osde tipo;
                                    if (id_ordenes == 0) {
                                        tipo = new camposordenes_osde(rs.getString("numero_afiliado"), rs.getString("nombre_afiliado"), rs.getString("numero_orden"), rs.getString("cod_practica"), rs.getString("nombre_practica"), df.format(rs.getDouble("precio_practica")), rs.getString("cod_practica_fac"), rs.getString("fecha_orden"));
                                        id_ordenes = rs.getInt("id_orden");
                                        pacientes = pacientes + 1;
                                    } else {
                                        if (id_ordenes != rs.getInt("id_orden")) {
                                            tipo = new camposordenes_osde(rs.getString("numero_afiliado"), rs.getString("nombre_afiliado"), rs.getString("numero_orden"), rs.getString("cod_practica"), rs.getString("nombre_practica"), df.format(rs.getDouble("precio_practica")), rs.getString("cod_practica_fac"), rs.getString("fecha_orden"));
                                            band = 1;
                                            id_ordenes = rs.getInt("id_orden");
                                            pacientes = pacientes + 1;
                                        } else {
                                            tipo = new camposordenes_osde("''", "''", "''", rs.getString("cod_practica"), rs.getString("nombre_practica"), df.format(rs.getDouble("precio_practica")), rs.getString("cod_practica_fac"), "''");
                                        }
                                    }
                                    total = Redondeardosdigitos(total);
                                    total = total + Redondeardosdigitos(rs.getDouble("precio_practica"));
                                    practicas = practicas + 1;
                                    Resultados.add(tipo);
                                }
                                contador++;
                                progreso.setValue(contador);
                            }
                            cn2.close();
                        } catch (Exception e) {
                            cursor2();
                            JOptionPane.showMessageDialog(null, e);
                        }

                        pesos = Redondeardosdigitos(total);
                        importetotal = pesos;
                        totalconcoseguro = importetotal - coseguro;

                        System.out.println(importetotal);

                        ///////////////////////////////////////////////////////////////////////////////////
                        String mes = "", año = periodo.substring(0, 4), cadena = periodo.substring(4, 6);
                        if (cadena.equals("01")) {
                            mes = "Enero";
                        }
                        if (cadena.equals("02")) {
                            mes = "Febrero";
                        }
                        if (cadena.equals("03")) {
                            mes = "Marzo";
                        }
                        if (cadena.equals("04")) {
                            mes = "Abril";
                        }
                        if (cadena.equals("05")) {
                            mes = "Mayo";
                        }
                        if (cadena.equals("06")) {
                            mes = "Junio";
                        }
                        if (cadena.equals("07")) {
                            mes = "Julio";
                        }
                        if (cadena.equals("08")) {
                            mes = "Agosto";
                        }
                        if (cadena.equals("09")) {
                            mes = "Septiembre";
                        }
                        if (cadena.equals("10")) {
                            mes = "Octubre";
                        }
                        if (cadena.equals("11")) {
                            mes = "Noviembre";
                        }
                        if (cadena.equals("12")) {
                            mes = "Diciembre";
                        }
                        periodo2 = mes + " " + año;
                        centavos = Redondearcentavos(pesos);
                        pesos = Redondeardosdigitos(pesos - centavos / 100);
                        total_pesos_letras = NumberToLetterConverter.convertNumberToLetter(pesos);
                        total_centavos_letras = NumberToLetterConverter.convertNumberToLetter(centavos);
                        ///////////////////////////////////////////////////////////////////////////////////////
                        //JDialog viewer = new JDialog(new javax.swing.JFrame(), "Reporte", true);
                        JFrame viewer = new JFrame();
                        viewer.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
                        viewer.setSize(800, 600);
                        viewer.setLocationRelativeTo(null);
                        ////////////////////////////////////////////////////////////////////////////////////////////////
                        if (CodObra.equals("511")) { ////////comparo para saber que pdf realizar 511 - MEDIFE
                            try {
                                pesos = pesos * 1.21;
                                centavos = Redondearcentavos(pesos);
                                pesos = Redondeardosdigitos(pesos - centavos / 100);
                                total_pesos_letras = NumberToLetterConverter.convertNumberToLetter(pesos);
                                total_centavos_letras = NumberToLetterConverter.convertNumberToLetter(centavos);
                                JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Ordenes_IVA.jasper"));
                                ///////////////////////////////////////////////////C:\Users\Lucas\Documents\NetBeansProjects\colegio bioquimicos\src\Reportes
                                Map parametros = new HashMap();
                                parametros.put("matricula", matricula_colegiado);
                                parametros.put("periodo", periodo2);
                                parametros.put("fecha", fecha2);
                                parametros.put("obra_social", ObraSocial);
                                parametros.put("num_obra_social", CodObra);
                                parametros.put("laboratorio", nombre_colegiado);
                                parametros.put("domicilio_lab", domicilio_lab);
                                parametros.put("localidad", localidad_lab);
                                parametros.put("pacientes", String.valueOf(pacientes));
                                parametros.put("practicas", String.valueOf(practicas));
                                parametros.put("total_letras_pesos", total_pesos_letras + " PESOS");
                                parametros.put("total_letras_centavos", total_centavos_letras + " CENTAVOS");
                                parametros.put("Subtotal", df.format(importetotal));
                                parametros.put("IVA", df.format(importetotal * 0.21));
                                parametros.put("total", df.format(importetotal * 1.21));
                                JasperPrint jPrint = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(Resultados));
                                /// JasperExportManager.exportReportToPdfFile(jPrint, "C:\\PDF-FACTURACION\\PDF-FACTURACION\\" + periodo + "-" + matricula_colegiado + "-" + CodObra + ".pdf");
                                //JasperExportManager.exportReportToPdfFile(jPrint, "J:\\PDF-FACTURACION\\" + periodo + "-" + matricula + "-" + cod_obra + ".pdf");
                                // JasperViewer.viewReport(jPrint, true);

                                ImagenPDF pdf = new ImagenPDF();
                                pdf.createPdf(jPrint, "C:\\Descargas-CBT\\" + periodo + "-" + matricula_colegiado + "-" + CodObra);
                                JasperViewer jv = new JasperViewer(jPrint, false);
                                viewer.getContentPane().add(jv.getContentPane());
                                viewer.setVisible(true);

                            } catch (JRException ex) {
                                System.err.println("Error iReport: " + ex.getMessage());
                            }
                        }

                        if (CodObra.equals("50015")) {
                            try {
                                JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Ordenes_coseguro.jasper"));
                                Map parametros = new HashMap();
                                parametros.put("matricula", matricula_colegiado);
                                parametros.put("periodo", periodo2);
                                parametros.put("fecha", fecha2);
                                parametros.put("obra_social", ObraSocial);
                                parametros.put("num_obra_social", CodObra);
                                parametros.put("laboratorio", nombre_colegiado);
                                parametros.put("domicilio_lab", domicilio_lab);
                                parametros.put("localidad", localidad_lab);
                                parametros.put("pacientes", String.valueOf(pacientes));
                                parametros.put("practicas", String.valueOf(practicas));
                                parametros.put("total_letras_pesos", total_pesos_letras + " PESOS");
                                parametros.put("total_letras_centavos", total_centavos_letras + " CENTAVOS");
                                parametros.put("total", df.format(importetotal));
                                ///Coseguro
                                parametros.put("coseguro", df.format(coseguro));
                                parametros.put("totalconcoseguro", df.format(totalconcoseguro));
                                JasperPrint jPrint = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(Resultados));

                                ImagenPDF pdf = new ImagenPDF();
                                pdf.createPdf(jPrint, "C:\\Descargas-CBT\\" + periodo + "-" + matricula_colegiado + "-" + CodObra);
                                JasperViewer jv = new JasperViewer(jPrint, false);
                                viewer.getContentPane().add(jv.getContentPane());
                                viewer.setVisible(true);

                            } catch (JRException ex) {
                                System.err.println("Error iReport: " + ex.getMessage());
                            }
                        }
                        if (CodObra.equals("1800") || CodObra.equals("1801") || CodObra.equals("1802")
                                || CodObra.equals("1803") || CodObra.equals("1804") || CodObra.equals("1805")
                                || CodObra.equals("1806") || CodObra.equals("1810") || CodObra.equals("1807")) {
                            try {
                                pesos = totalconcoseguro;
                                centavos = Redondearcentavos(pesos);
                                pesos = Redondeardosdigitos(pesos - centavos / 100);
                                total_pesos_letras = NumberToLetterConverter.convertNumberToLetter(pesos);
                                total_centavos_letras = NumberToLetterConverter.convertNumberToLetter(centavos);
                                JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Ordenes_SS.jasper"));
                                ///////////////////////////////////////////////////C:\Users\Lucas\Documents\NetBeansProjects\colegio bioquimicos\src\Reportes
                                Map parametros = new HashMap();
                                parametros.put("matricula", matricula_colegiado);
                                parametros.put("periodo", periodo2);
                                parametros.put("fecha", fecha2);
                                parametros.put("obra_social", ObraSocial);
                                parametros.put("num_obra_social", CodObra);
                                parametros.put("laboratorio", nombre_colegiado);
                                parametros.put("domicilio_lab", domicilio_lab);
                                parametros.put("localidad", localidad_lab);
                                parametros.put("pacientes", String.valueOf(pacientes));
                                parametros.put("practicas", String.valueOf(practicas));
                                parametros.put("total_letras_pesos", total_pesos_letras + " PESOS");
                                parametros.put("total_letras_centavos", total_centavos_letras + " CENTAVOS");
                                parametros.put("total", df.format(importetotal));
                                ///Coseguro
                                parametros.put("cuit", cuit);
                                parametros.put("coseguro", df.format(coseguro));
                                parametros.put("totalconcoseguro", df.format(totalconcoseguro));
                                JasperPrint jPrint = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(Resultados_ss));
                                //JasperExportManager.exportReportToPdfFile(jPrint, "C:\\Descargas-CBT\\" + periodo + "-" + matricula_colegiado + "-" + CodObra + ".pdf");
                                // JasperExportManager.exportReportToPdfFile(jPrint, "J:\\PDF-FACTURACION\\" + periodo + "-" + matricula + "-" + cod_obra + ".pdf");
                                //JasperViewer.viewReport(jPrint, true);
                                ImagenPDF pdf = new ImagenPDF();
                                pdf.createPdf(jPrint, "C:\\Descargas-CBT\\" + periodo + "-" + matricula_colegiado + "-" + CodObra);
                                JasperViewer jv = new JasperViewer(jPrint, false);
                                viewer.getContentPane().add(jv.getContentPane());
                                viewer.setVisible(true);

                            } catch (JRException ex) {
                                System.err.println("Error iReport: " + ex.getMessage());
                            }
                        }
                        if (CodObra.equals("3100") || CodObra.equals("3101") || CodObra.equals("3102")) {
                            try {
                                JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Ordenes_OSDE.jasper"));
                                ///////////////////////////////////////////////////C:\Users\Lucas\Documents\NetBeansProjects\colegio bioquimicos\src\Reportes
                                Map parametros = new HashMap();
                                parametros.put("matricula", matricula_colegiado);
                                System.out.println("...1");
                                parametros.put("periodo", periodo2);
                                System.out.println("...2");
                                parametros.put("fecha", fecha2);
                                parametros.put("obra_social", ObraSocial);
                                parametros.put("num_obra_social", CodObra);
                                parametros.put("laboratorio", nombre_colegiado);
                                parametros.put("domicilio_lab", domicilio_lab);
                                parametros.put("localidad", localidad_lab);
                                parametros.put("pacientes", String.valueOf(pacientes));
                                parametros.put("practicas", String.valueOf(practicas));
                                parametros.put("total_letras_pesos", total_pesos_letras + " PESOS");
                                parametros.put("total_letras_centavos", total_centavos_letras + " CENTAVOS");
                                parametros.put("total", df.format(importetotal));
                                JasperPrint jPrint = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(Resultados));
                                //JasperExportManager.exportReportToPdfFile(jPrint, "C:\\Descargas-CBT\\" + periodo + "-" + matricula_colegiado + "-" + CodObra + ".pdf");
                                // JasperExportManager.exportReportToPdfFile(jPrint, "J:\\PDF-FACTURACION\\" + periodo + "-" + matricula + "-" + cod_obra + ".pdf");
                                ///JasperViewer.viewReport(jPrint, true);
                                ImagenPDF pdf = new ImagenPDF();
                                pdf.createPdf(jPrint, "C:\\Descargas-CBT\\" + periodo + "-" + matricula_colegiado + "-" + CodObra);
                                JasperViewer jv = new JasperViewer(jPrint, false);
                                viewer.getContentPane().add(jv.getContentPane());
                                viewer.setVisible(true);

                            } catch (JRException ex) {
                                System.err.println("Error iReport: " + ex.getMessage());
                            }
                        }
                        if (CodObra.equals("50015") || CodObra.equals("1800") || CodObra.equals("1801") || CodObra.equals("1802")
                                || CodObra.equals("1803") || CodObra.equals("1804") || CodObra.equals("1805")
                                || CodObra.equals("1806") || CodObra.equals("1810") || CodObra.equals("511") || CodObra.equals("1807")
                                || CodObra.equals("40813") || CodObra.equals("2700")) {

                        } else {
                            System.out.println(CodObra);
                            try {
                                JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Ordenes_Fecha.jasper"));
                                ///////////////////////////////////////////////////C:\Users\Lucas\Documents\NetBeansProjects\colegio bioquimicos\src\Reportes
                                Map parametros = new HashMap();
                                parametros.put("matricula", matricula_colegiado);
                                parametros.put("periodo", periodo2);
                                parametros.put("fecha", fecha2);
                                parametros.put("obra_social", ObraSocial);
                                parametros.put("num_obra_social", CodObra);
                                parametros.put("laboratorio", nombre_colegiado);
                                parametros.put("domicilio_lab", domicilio_lab);
                                parametros.put("localidad", localidad_lab);
                                parametros.put("pacientes", String.valueOf(pacientes));
                                parametros.put("practicas", String.valueOf(practicas));
                                parametros.put("total_letras_pesos", total_pesos_letras + " PESOS");
                                parametros.put("total_letras_centavos", total_centavos_letras + " CENTAVOS");
                                parametros.put("total", df.format(importetotal));
                                JasperPrint jPrint = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(Resultados));
                                //JasperExportManager.exportReportToPdfFile(jPrint, "C:\\Descargas-CBT\\" + periodo + "-" + matricula_colegiado + "-" + CodObra + ".pdf");
                                //  JasperExportManager.exportReportToPdfFile(jPrint, "J:\\PDF-FACTURACION\\" + periodo + "-" + matricula + "-" + cod_obra + ".pdf");
                                ///JasperViewer.viewReport(jPrint, true);
                                ImagenPDF pdf = new ImagenPDF();
                                pdf.createPdf(jPrint, "C:\\Descargas-CBT\\" + periodo + "-" + matricula_colegiado + "-" + CodObra);
                                JasperViewer jv = new JasperViewer(jPrint, false);
                                viewer.getContentPane().add(jv.getContentPane());
                                viewer.setVisible(true);

                            } catch (JRException ex) {
                                System.err.println("Error iReport: " + ex.getMessage());
                            }
                        }
                        System.out.println("error 8");
                    } catch (Exception e) {
                        cursor2();
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            }
            cursor2();
        }

        public void pausa(int mlSeg) {
            try {
                Thread.sleep(mlSeg);
            } catch (Exception e) {
            }

        }

        public void resumen_ordenes() {
            System.out.println("error 9");
            ConexionMariaDB cc = new ConexionMariaDB();
            Connection cn = cc.Conectar();
            ////////////////Previsualizacion///////////////////////////
            JFrame viewer = new JFrame();
            viewer.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
            viewer.setSize(800, 600);
            viewer.setLocationRelativeTo(null);
            JasperViewer jv = null;
            ///////////////////////////////////////////////////////////
            Map parametros = new HashMap();
            parametros.put("matricula", matricula_colegiado);
            System.out.println("matricula:" + matricula_colegiado);
            parametros.put("periodo", periododjj);
            System.out.println("periodo:" + periododjj);
            parametros.put("fecha", fecha2);
            parametros.put("obra_social", ObraSocial);
            System.out.println("obra_social:" + ObraSocial);
            parametros.put("num_obra_social", CodObra);
            System.out.println("num_obra_social:" + CodObra);
            parametros.put("laboratorio", nombre_colegiado);
            parametros.put("domicilio_lab", domicilio_lab);
            parametros.put("localidad", localidad_lab);
            try {
                JasperReport report_comprobante = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Resumen_ordenes.jasper"));
                JasperPrint jPrint_comprobante = JasperFillManager.fillReport(report_comprobante, parametros, cn);
                jv = new JasperViewer(jPrint_comprobante, false);
                viewer.getContentPane().add(jv.getContentPane());
                viewer.setVisible(true);
            } catch (JRException ex) {
                System.err.println("Error iReport: " + ex.getMessage());
            }

        }
    }

    public double Redondeardosdigitos(double numero) {
        return Math.rint(numero * 100) / 100;
    }

    public javax.swing.JProgressBar getjProgressBar1() {
        return progreso;
    }

    public javax.swing.JProgressBar getjProgressBar2() {
        return progreso3;
    }

    public void iniciarSplash() {
        this.getjProgressBar1().setBorderPainted(false);
        this.getjProgressBar1().setForeground(new Color(100, 100, 100, 100));

        this.getjProgressBar1().setStringPainted(true);
    }

    public void iniciarSplash2() {
        this.getjProgressBar2().setBorderPainted(false);
        this.getjProgressBar2().setForeground(new Color(100, 100, 100, 100));

        this.getjProgressBar2().setStringPainted(true);
    }

    ////para alinear columnas////
    void alinear() {
        alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
    }

    String invertir(String entrada) {
        if ((null == entrada) || (entrada.length() <= 1)) {
            return entrada;
        }
        String salida = "";
        int i = 0;
        /////Año/////
        for (i = 6; i <= 9; i++) {
            salida = salida + entrada.charAt(i);
        }
        salida = salida + "-";
        ///Mes///
        for (i = 3; i <= 4; i++) {
            salida = salida + entrada.charAt(i);
        }
        salida = salida + "-";
        ////Dia////
        for (i = 0; i <= 1; i++) {
            salida = salida + entrada.charAt(i);
        }

        return salida;

    }

    void cargarorden() {
        String sSQL = "";
        String numero = "";
        ConexionMariaDB mysql = new ConexionMariaDB();
        Connection cn = mysql.Conectar();

        sSQL = "SELECT MAX(id_orden) AS id_orden FROM ordenes";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            rs.last();
            if (rs.getInt("id_orden") != 0) {
                id_orden = rs.getInt("id_orden") + 1;
            } else {
                id_orden = 1;
            }
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    void cargarobrasocial() {
        int i = 0;
        borrarobrasocial();
        // Recorro y cargo las obras sociales
        while (i < contadorobrasocial) {
            textAutoAcompleter2.addItem(obrasocial[i]);

            i++;
        }

        textAutoAcompleter2.setMode(0); // infijo     

        // textAutoAcompleter.setMode(1); // sufijo
        //textAutoAcompleter.setCaseSensitive(true); // Sensible a mayúsculas
        textAutoAcompleter2.setCaseSensitive(false); //No sensible a mayúsculas        

    }

    void cargarfechamedife() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat formato2 = new SimpleDateFormat("yyMMddHHmmss");
        SimpleDateFormat formato3 = new SimpleDateFormat("yyyyMMdd");
        Date currentDate = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);

        fechahora_medife = formato.format(currentDate);
        codigo_seguridad_medife = formato2.format(currentDate) + formato3.format(currentDate);
    }

    void cargarpractica() {
        TextAutoCompleter textAutoAcompleter = new TextAutoCompleter(txtpractica);
        int i = 0;
        // Recorro y cargo las obras sociales
        while (i < contadoranalisis) {

            textAutoAcompleter.addItem(analisis[i]);
            i++;
        }

        textAutoAcompleter.setMode(0); // infijo
        textAutoAcompleter.setCaseSensitive(false); //No sensible a mayúsculas
    }

    void borrarpractica() {
        textAutoAcompleter.removeAllItems();
    }

    void borrarobrasocial() {
        textAutoAcompleter2.removeAllItems();
    }

    void cargarpracticaconobra() {
        int i = 0;
        practica = new String[500000];
        preciopractica = new String[500000];
        // codfacpractica = new String[500000];
        contadorj = 0;
        ConexionMariaDB cc = new ConexionMariaDB();
        Connection cn = cc.Conectar();
        try {
            Statement St = cn.createStatement();
            ResultSet Rs = null;
            if (Login.estadopeec == 1) {
                Rs = St.executeQuery("SELECT codigo_practica, preciototal, determinacion, codigo_fac_practicas_obrasocial, id_practicasnbu FROM obrasocial_tiene_practicasnbu  "
                        + "WHERE id_obrasocial=" + id_obra_social);
            } else {
                Rs = St.executeQuery("SELECT codigo_practica, precioSinPEEC, determinacion, codigo_fac_practicas_obrasocial, id_practicasnbu FROM obrasocial_tiene_practicasnbu  "
                        + "WHERE id_obrasocial=" + id_obra_social);
            }

            while (Rs.next()) {
                practica[contadorj] = (Rs.getString(1) + " - " + Rs.getString(3));
                textAutoAcompleter.addItem(practica[contadorj]);
                preciopractica[contadorj] = Rs.getString(2);
                codfacpractica[contadorj] = Rs.getString(4);
                idpractica[contadorj] = Rs.getString(5);
                contadorj++;
            }
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        textAutoAcompleter.setMode(0); // infijo
        // textAutoAcompleter.setMode(1); // sufijo
        //textAutoAcompleter.setCaseSensitive(true); // Sensible a mayúsculas
        textAutoAcompleter.setCaseSensitive(false);
    }

    void cargarafiliado() {
        TextAutoCompleter textAutoAcompletar = new TextAutoCompleter(txtnumafiliado);
        TextAutoCompleter textAutoAcompletar2 = new TextAutoCompleter(txtnombreafiliado);
        TextAutoCompleter textAutoAcompletar3 = new TextAutoCompleter(txtdocumento);
        int i = 0;
        // Recorro y cargo las obras sociales
        while (i < contadorafiliado) {
            textAutoAcompletar.addItem(numafiliado[i]);
            textAutoAcompletar2.addItem(nomafiliado[i]);
            textAutoAcompletar3.addItem(dniafiliado[i]);
            i++;
        }

        //textAutoAcompleter.setMode(-1); // prefijo, viene por defecto
        textAutoAcompletar2.setMode(0); // infijo
        textAutoAcompletar3.setMode(0);
        // textAutoAcompleter.setMode(1); // sufijo
        //textAutoAcompleter.setCaseSensitive(true); // Sensible a mayúsculas
        textAutoAcompletar2.setCaseSensitive(false); //No sensible a mayúsculas
    }

    void cargarip() {
        ////////////////////////////////////////////////////////////////////////
        try {
            String thisIp = InetAddress.getLocalHost().getHostAddress();
            String thisIpPublic = InetAddress.getLocalHost().getHostAddress();
            String thisname = InetAddress.getLocalHost().getHostName();
            ip2 = thisIp + "-" + thisname;
            ipLocal = thisIp;
            hostLocal = thisname;
        } catch (Exception e) {
            e.printStackTrace();
        }
        /////////////////////////////////////////////////////////////////////
        ConexionMariaDB mysql = new ConexionMariaDB();
        Connection cn = mysql.Conectar();
        String sSQL8 = "SELECT now()";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL8);

            rs.next();
            fecha = rs.getString(1).substring(8, 10) + "/" + rs.getString(1).substring(5, 7) + "/" + rs.getString(1).substring(0, 4);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al traer fecha del servidor");
        }
        /////////////////////////////////////////////////////////////////////
        SimpleDateFormat formatoTiempo = new SimpleDateFormat("HHmmss");
        java.util.Date currentDate1 = new java.util.Date();
        GregorianCalendar calendar1 = new GregorianCalendar();
        calendar1.setTime(currentDate1);
        hora = formatoTiempo.format(currentDate1);
    }

    void cargarperiodo() {
        SimpleDateFormat formato2 = new SimpleDateFormat("d' de 'MMMM' de 'yyyy");
        Date currentDate = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);
        fecha2 = formato2.format(currentDate);
        /////////////////////////////////////////////////////////
        String año2 = "", mes2 = "", dia2 = "", salida = "";
        calendar.setTime(currentDate);
        int i = 0;
        ///Mes/// 21/06/1985
        for (i = 3; i <= 4; i++) {
            mes2 = mes2 + fecha.charAt(i);
        }
        /////Año/////
        for (i = 6; i <= 9; i++) {
            año2 = año2 + fecha.charAt(i);
        }
        ////Dia////
        for (i = 8; i <= 9; i++) {
            dia2 = dia2 + fecha.charAt(i);
        }
        periodo = año2 + mes2;
        fecha_txt = dia2 + "/" + txtmes.getText() + "/" + txtaño.getText();
        txtfecha.setText(fecha);
    }

    void cargar_fecha_jerarquicos() throws DatatypeConfigurationException {
        java.util.Date currentDate1 = new java.util.Date();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(currentDate1);
        date_jerarquicos = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        System.out.println("Fecha Jerarquicos: " + date_jerarquicos);
    }

    void limpiar_variables() {
        txtdocumento.setText("");
        documento_afiliado = "";
        txtnombreafiliado.setText("");
        txtnumafiliado.setText("");
        txtnumorden.setText("");
        borrartabla();
        txtfecha.setText(fecha);
        txtDiaOrden.setText("");
    }

    void habilitarpanel1() {
        jLabel20.setEnabled(true);
        jLabel6.setEnabled(true);
        jLabel8.setEnabled(true);
        txtmatricula.setEnabled(true);
        txtnumorden.setEnabled(true);
        jLabel9.setEnabled(true);
        jLabel10.setEnabled(true);
        jLabel11.setEnabled(true);
        txtdocumento.setEnabled(true);
        txtfecha.setEnabled(true);
        txtdocumento.setEditable(true);
        txtmatricula.setEditable(true);
        txtnumafiliado.setEnabled(true);
        txtnumafiliado.setEditable(true);
        jPanel1.setEnabled(true);
        tablapracticas.setEnabled(true);
    }

    void deshabilitarpanel1() {

        jLabel6.setEnabled(false);
        jLabel20.setEnabled(false);
        jLabel8.setEnabled(false);
        jLabel9.setEnabled(false);
        jLabel10.setEnabled(false);
        txtfecha.setEnabled(false);
        txtfecha.setText("");
        txtdocumento.setEnabled(false);
        txtdocumento.setText("");
        txtnombreafiliado.setEnabled(false);
        txtnombreafiliado.setText("");
        txtnumafiliado.setEnabled(false);
        txtnumafiliado.setText("");
        txtnumafiliado.setEnabled(false);
        txtnumafiliado.setEditable(false);
        txtmatricula.setText("");
        txtnumorden.setEnabled(false);
        txtnumorden.setText("");
        txtpractica.setEnabled(false);
        txtpractica.setText("");
        jLabel11.setEnabled(false);
        tablapracticas.setEnabled(false);
        txtmes.setFocusable(true);
    }

    void cursor() {
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        /* this.btnCursor02.setCursor(new Cursor(Cursor.HAND_CURSOR));
         this.btnCursor03.setCursor(new Cursor(Cursor.MOVE_CURSOR));
         this.btnCursor04.setCursor(new Cursor(Cursor.TEXT_CURSOR));*/

        //this.add(this.btnimprimir1);
        /* this.add(this.btnCursor02);
         this.add(this.btnCursor03);
         this.add(this.btnCursor04);*/
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        this.pack();
    }

    void cursor2() {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        /* this.btnCursor02.setCursor(new Cursor(Cursor.HAND_CURSOR));
         this.btnCursor03.setCursor(new Cursor(Cursor.MOVE_CURSOR));
         this.btnCursor04.setCursor(new Cursor(Cursor.TEXT_CURSOR));*/

        //this.add(this.btnimprimir1);
        /* this.add(this.btnCursor02);
         this.add(this.btnCursor03);
         this.add(this.btnCursor04);*/
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        Practicas = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        Modificar = new javax.swing.JMenuItem();
        Anular = new javax.swing.JMenuItem();
        Habilita = new javax.swing.JMenuItem();
        Imprimir = new javax.swing.JMenuItem();
        grupoBoton = new javax.swing.ButtonGroup();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        Ordenes = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablapracticas = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        txtobrasocial = new javax.swing.JTextField();
        txtnombreafiliado = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtpractica = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtnumorden = new javax.swing.JFormattedTextField();
        txtfecha = new javax.swing.JFormattedTextField();
        txtmatricula = new javax.swing.JTextField();
        txttotal1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtfechacoseguro = new javax.swing.JFormattedTextField();
        chkcoseguro = new javax.swing.JCheckBox();
        txtcoseguro = new javax.swing.JTextField();
        txtnumafiliado = new javax.swing.JTextField();
        txtDiaOrden = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        cbotipo = new javax.swing.JComboBox<>();
        txtdocumento = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtmes = new javax.swing.JFormattedTextField();
        txtaño = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();
        btnaceptar = new javax.swing.JButton();
        btnborrar = new javax.swing.JButton();
        btnobrasocial = new javax.swing.JButton();
        btnpaciente = new javax.swing.JButton();
        lblcolegiado = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        progreso3 = new javax.swing.JProgressBar();
        txtmes3 = new javax.swing.JFormattedTextField();
        txtaño3 = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        btnimportar = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaordenes1 = new javax.swing.JTable();
        formatoArchivoBoton = new javax.swing.JButton();
        btnaceptar2 = new javax.swing.JButton();
        btncancelar2 = new javax.swing.JButton();
        btnsalir3 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        txttotalordenes1 = new javax.swing.JTextField();
        lblcolegiado1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        Facturacion = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        progreso = new javax.swing.JProgressBar();
        txtmes1 = new javax.swing.JFormattedTextField();
        txtaño1 = new javax.swing.JFormattedTextField();
        btnbuscar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        btncancelar1 = new javax.swing.JButton();
        btnsalir1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaordenes = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        txtordenes = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txttotalordenes = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txttotalanuladas = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txttotalobservadas = new javax.swing.JTextField();
        btnimprimirdjj = new javax.swing.JButton();
        btnimprimirobra = new javax.swing.JButton();
        btncancelar3 = new javax.swing.JButton();
        lblcolegiado2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        Utilitarios = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtnovedad = new javax.swing.JTextPane();
        jPanel5 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        btnobrasociales = new javax.swing.JButton();
        btnnomenclador = new javax.swing.JButton();
        btnnbu = new javax.swing.JButton();
        btnPrensa = new javax.swing.JButton();
        btnSubsidio = new javax.swing.JButton();
        btnIosfa = new javax.swing.JButton();
        btnsalir2 = new javax.swing.JButton();
        btnsalir4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        btnSubsidio1 = new javax.swing.JButton();
        lblcolegiado3 = new javax.swing.JLabel();
        btnsalir5 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        Practicas.setText("Ver Detalle");
        Practicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PracticasActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Practicas);

        Modificar.setText("Modificar Orden");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });
        jPopupMenu2.add(Modificar);

        Anular.setText("Anular Orden");
        Anular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnularActionPerformed(evt);
            }
        });
        jPopupMenu2.add(Anular);

        Habilita.setText("Ver Orden");
        Habilita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HabilitaActionPerformed(evt);
            }
        });
        jPopupMenu2.add(Habilita);

        Imprimir.setText("Comprobante");
        Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImprimirActionPerformed(evt);
            }
        });
        jPopupMenu2.add(Imprimir);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(656, 673));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jTabbedPane2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jTabbedPane2.setPreferredSize(new java.awt.Dimension(651, 668));
        jTabbedPane2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabbedPane2KeyPressed(evt);
            }
        });

        Ordenes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OrdenesKeyPressed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingreso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N
        jPanel1.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Obra Social:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Nombre del Afiliado:");
        jLabel6.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("N° de Afiliado:");
        jLabel8.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Matricula Prescripcion:");
        jLabel9.setEnabled(false);

        tablapracticas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tablapracticas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número", "Código", "Descripción", "Precio", "Cod_Fac", "id_practica"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablapracticas.setEnabled(false);
        tablapracticas.setNextFocusableComponent(btnaceptar);
        tablapracticas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablapracticasKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablapracticas);

        txtobrasocial.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtobrasocial.setForeground(new java.awt.Color(0, 102, 204));
        txtobrasocial.setNextFocusableComponent(txtdocumento);
        txtobrasocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtobrasocialActionPerformed(evt);
            }
        });
        txtobrasocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtobrasocialKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtobrasocialKeyReleased(evt);
            }
        });

        txtnombreafiliado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnombreafiliado.setForeground(new java.awt.Color(0, 102, 204));
        txtnombreafiliado.setNextFocusableComponent(txtnumafiliado);
        txtnombreafiliado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreafiliadoActionPerformed(evt);
            }
        });
        txtnombreafiliado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnombreafiliadoKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Numero de Orden:");
        jLabel11.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Fecha de Realización:");
        jLabel10.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Buscar Practica:");
        jLabel12.setEnabled(false);

        txtpractica.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtpractica.setForeground(new java.awt.Color(0, 102, 204));
        txtpractica.setNextFocusableComponent(btnaceptar);
        txtpractica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpracticaActionPerformed(evt);
            }
        });
        txtpractica.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpracticaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpracticaKeyReleased(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Documento:");

        txtnumorden.setForeground(new java.awt.Color(0, 102, 204));
        txtnumorden.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnumorden.setNextFocusableComponent(txtfecha);
        txtnumorden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnumordenActionPerformed(evt);
            }
        });
        txtnumorden.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnumordenKeyPressed(evt);
            }
        });

        txtfecha.setForeground(new java.awt.Color(0, 102, 204));
        try {
            txtfecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtfecha.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtfecha.setDropMode(javax.swing.DropMode.INSERT);
        txtfecha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtfecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfechaKeyPressed(evt);
            }
        });

        txtmatricula.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtmatricula.setForeground(new java.awt.Color(0, 102, 204));
        txtmatricula.setToolTipText("Matricula de Prescripción Medica");
        txtmatricula.setNextFocusableComponent(txtnumorden);
        txtmatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmatriculaActionPerformed(evt);
            }
        });
        txtmatricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmatriculaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmatriculaKeyReleased(evt);
            }
        });

        txttotal1.setEditable(false);
        txttotal1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txttotal1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttotal1.setNextFocusableComponent(txtmatricula);
        txttotal1.setOpaque(false);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Total: $");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("Precio Coseguro: $");
        jLabel24.setEnabled(false);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Fecha de Coseguro:");
        jLabel25.setEnabled(false);

        txtfechacoseguro.setForeground(new java.awt.Color(0, 102, 204));
        try {
            txtfechacoseguro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtfechacoseguro.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtfechacoseguro.setDropMode(javax.swing.DropMode.INSERT);
        txtfechacoseguro.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtfechacoseguro.setNextFocusableComponent(txtpractica);
        txtfechacoseguro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfechacoseguroKeyPressed(evt);
            }
        });

        chkcoseguro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chkcoseguro.setForeground(new java.awt.Color(51, 51, 51));
        chkcoseguro.setText("Coseguro");
        chkcoseguro.setNextFocusableComponent(txtcoseguro);
        chkcoseguro.setOpaque(false);
        chkcoseguro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkcoseguroActionPerformed(evt);
            }
        });
        chkcoseguro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkcoseguroKeyPressed(evt);
            }
        });

        txtcoseguro.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtcoseguro.setForeground(new java.awt.Color(0, 102, 204));
        txtcoseguro.setText("0.00");
        txtcoseguro.setToolTipText("Buscar por dni paciente o num paciente o nom paciente o num orden");
        txtcoseguro.setDropMode(javax.swing.DropMode.INSERT);
        txtcoseguro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcoseguroActionPerformed(evt);
            }
        });
        txtcoseguro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcoseguroKeyPressed(evt);
            }
        });

        txtnumafiliado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnumafiliado.setForeground(new java.awt.Color(0, 102, 204));
        txtnumafiliado.setNextFocusableComponent(txtmatricula);
        txtnumafiliado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnumafiliadoKeyPressed(evt);
            }
        });

        txtDiaOrden.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtDiaOrden.setForeground(new java.awt.Color(0, 102, 204));
        txtDiaOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaOrdenActionPerformed(evt);
            }
        });
        txtDiaOrden.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDiaOrdenKeyPressed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Día de Orden:");

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728979 - info.png"))); // NOI18N
        jButton7.setText("¿Cómo Facturar?");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        cbotipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbotipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Orden Médica Electrónica", "Bono Especialista", "Bono Generalista" }));
        cbotipo.setNextFocusableComponent(txtdocumento);
        cbotipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbotipoActionPerformed(evt);
            }
        });
        cbotipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbotipoKeyPressed(evt);
            }
        });

        txtdocumento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtdocumento.setForeground(new java.awt.Color(0, 102, 204));
        txtdocumento.setNextFocusableComponent(txtnombreafiliado);
        txtdocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdocumentoActionPerformed(evt);
            }
        });
        txtdocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdocumentoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdocumentoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdocumentoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtpractica))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtobrasocial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbotipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7))
                    .addComponent(jSeparator2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkcoseguro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtnumorden))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcoseguro, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtfechacoseguro)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDiaOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnombreafiliado))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnumafiliado)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jButton7)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtobrasocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbotipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtnombreafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txtdocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(txtmatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnumafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(txtnumorden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(txtfechacoseguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkcoseguro)
                    .addComponent(txtcoseguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtpractica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttotal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Período de Facturación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Mes:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Año:");

        txtmes.setForeground(new java.awt.Color(0, 102, 204));
        try {
            txtmes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtmes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtmes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmesKeyPressed(evt);
            }
        });

        txtaño.setForeground(new java.awt.Color(0, 102, 204));
        try {
            txtaño.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtaño.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtaño.setNextFocusableComponent(txtobrasocial);
        txtaño.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtañoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtaño, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtmes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Bauhaus 93", 1, 60)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 204, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cbt2.png"))); // NOI18N

        btnsalir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728935 - exit left logout.png"))); // NOI18N
        btnsalir.setMnemonic('s');
        btnsalir.setText("Salir");
        btnsalir.setToolTipText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btnaceptar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnaceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728898 - add plus.png"))); // NOI18N
        btnaceptar.setMnemonic('a');
        btnaceptar.setText("Aceptar");
        btnaceptar.setToolTipText("[Alt + a] o [+]");
        btnaceptar.setNextFocusableComponent(btnborrar);
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
            }
        });

        btnborrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnborrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728918 - cancel error exit fault.png"))); // NOI18N
        btnborrar.setMnemonic('b');
        btnborrar.setText("Borrar Todo");
        btnborrar.setToolTipText("[Alt + b] o [-]");
        btnborrar.setNextFocusableComponent(btnsalir);
        btnborrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnborrarActionPerformed(evt);
            }
        });

        btnobrasocial.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnobrasocial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728922 - apartment building city.png"))); // NOI18N
        btnobrasocial.setMnemonic('o');
        btnobrasocial.setText("Cambiar Obra Social");
        btnobrasocial.setToolTipText("[Alt + o] o [/]");
        btnobrasocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnobrasocialActionPerformed(evt);
            }
        });

        btnpaciente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnpaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728899 - alarm bell.png"))); // NOI18N
        btnpaciente.setMnemonic('p');
        btnpaciente.setText("Cambiar Paciente");
        btnpaciente.setToolTipText("[Alt + p] o [*]");
        btnpaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpacienteActionPerformed(evt);
            }
        });

        lblcolegiado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblcolegiado.setForeground(new java.awt.Color(0, 102, 255));
        lblcolegiado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("Cerrar Sesión");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OrdenesLayout = new javax.swing.GroupLayout(Ordenes);
        Ordenes.setLayout(OrdenesLayout);
        OrdenesLayout.setHorizontalGroup(
            OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrdenesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OrdenesLayout.createSequentialGroup()
                        .addComponent(btnaceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnobrasocial, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnpaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnborrar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(OrdenesLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(OrdenesLayout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OrdenesLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                .addComponent(lblcolegiado, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)))
                        .addComponent(jLabel3)))
                .addContainerGap())
        );
        OrdenesLayout.setVerticalGroup(
            OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrdenesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(OrdenesLayout.createSequentialGroup()
                        .addComponent(lblcolegiado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnobrasocial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnaceptar)
                        .addComponent(btnpaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnsalir)
                        .addComponent(btnborrar)))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Ordenes", null, Ordenes, "Ordenes");
        Ordenes.getAccessibleContext().setAccessibleName("");
        Ordenes.getAccessibleContext().setAccessibleParent(Ordenes);

        jPanel7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel7KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPanel7KeyReleased(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Período de Facturación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Mes:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Año:");

        progreso3.setFont(new java.awt.Font("Tahoma", 0, 6)); // NOI18N
        progreso3.setForeground(new java.awt.Color(100, 100, 100));
        progreso3.setString("");

        txtmes3.setForeground(new java.awt.Color(0, 102, 204));
        try {
            txtmes3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtmes3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtmes3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmes3KeyPressed(evt);
            }
        });

        txtaño3.setForeground(new java.awt.Color(0, 102, 204));
        try {
            txtaño3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtaño3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtaño3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaño3ActionPerformed(evt);
            }
        });
        txtaño3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtaño3KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progreso3, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmes3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtaño3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(txtmes3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtaño3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progreso3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel18.setFont(new java.awt.Font("Bauhaus 93", 1, 60)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 204, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cbt2.png"))); // NOI18N

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingreso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N
        jPanel9.setEnabled(false);

        btnimportar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnimportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728983 - folder.png"))); // NOI18N
        btnimportar.setMnemonic('t');
        btnimportar.setText("Agregar Archivo");
        btnimportar.setToolTipText("[Alt + t]");
        btnimportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimportarActionPerformed(evt);
            }
        });
        btnimportar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnimportarKeyPressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        tablaordenes1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tablaordenes1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4", "Título 5", "Título 6", "Título 7", "Título 8", "Título 9", "Título 10", "Título 11", "Título 12", "Título 13", "Título 14", "Título 15", "Título 16", "Título 17", "Título 18", "Título 19", "Título 20", "Título 21", "Título 22", "Título 23", "Título 24", "Título 25", "Título 26", "Título 27", "Título 28", "Título 29"
            }
        ));
        tablaordenes1.setComponentPopupMenu(jPopupMenu1);
        tablaordenes1.setNextFocusableComponent(btnaceptar2);
        tablaordenes1.setOpaque(false);
        tablaordenes1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaordenes1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaordenes1KeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tablaordenes1);

        formatoArchivoBoton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        formatoArchivoBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728899 - alarm bell.png"))); // NOI18N
        formatoArchivoBoton.setText("Formato de archivo");
        formatoArchivoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formatoArchivoBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btnimportar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(formatoArchivoBoton))
                    .addComponent(jSeparator4)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnimportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(formatoArchivoBoton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnaceptar2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnaceptar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728934 - enter login right.png"))); // NOI18N
        btnaceptar2.setMnemonic('a');
        btnaceptar2.setText("Aceptar");
        btnaceptar2.setToolTipText("[Alt + a]");
        btnaceptar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptar2ActionPerformed(evt);
            }
        });

        btncancelar2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btncancelar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728918 - cancel error exit fault.png"))); // NOI18N
        btncancelar2.setMnemonic('b');
        btncancelar2.setText("Borrar Tabla");
        btncancelar2.setToolTipText("[Alt + b]");
        btncancelar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar2ActionPerformed(evt);
            }
        });

        btnsalir3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnsalir3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728935 - exit left logout.png"))); // NOI18N
        btnsalir3.setMnemonic('s');
        btnsalir3.setText("Salir");
        btnsalir3.setToolTipText("[Alt + s]");
        btnsalir3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnsalir3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir3ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Total de Ordenes no procesadas:");

        txttotalordenes1.setEditable(false);
        txttotalordenes1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txttotalordenes1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttotalordenes1.setNextFocusableComponent(txtmatricula);
        txttotalordenes1.setOpaque(false);

        lblcolegiado1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblcolegiado1.setForeground(new java.awt.Color(0, 102, 255));
        lblcolegiado1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("Cerrar Sesión");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblcolegiado1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addComponent(jButton4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotalordenes1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnaceptar2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncancelar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsalir3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lblcolegiado1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsalir3)
                    .addComponent(btncancelar2)
                    .addComponent(btnaceptar2)
                    .addComponent(txttotalordenes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Importar", null, jPanel7, "Importar");

        Facturacion.setRequestFocusEnabled(false);
        Facturacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FacturacionKeyPressed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Período de Facturación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Mes:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Año:");

        progreso.setFont(new java.awt.Font("Tahoma", 0, 6)); // NOI18N
        progreso.setForeground(new java.awt.Color(100, 100, 100));
        progreso.setString("");

        txtmes1.setForeground(new java.awt.Color(0, 102, 204));
        try {
            txtmes1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtmes1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtmes1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmes1KeyPressed(evt);
            }
        });

        txtaño1.setForeground(new java.awt.Color(0, 102, 204));
        try {
            txtaño1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtaño1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtaño1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtaño1KeyPressed(evt);
            }
        });

        btnbuscar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728907 - battery full.png"))); // NOI18N
        btnbuscar.setMnemonic('a');
        btnbuscar.setText("Aceptar");
        btnbuscar.setToolTipText("[Alt + a]");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });
        btnbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnbuscarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(progreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmes1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtaño1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtaño1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(txtmes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progreso, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel13.setFont(new java.awt.Font("Bauhaus 93", 1, 60)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 204, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cbt2.png"))); // NOI18N

        btncancelar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btncancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728918 - cancel error exit fault.png"))); // NOI18N
        btncancelar1.setMnemonic('c');
        btncancelar1.setText("Cancelar");
        btncancelar1.setToolTipText("[Alt + c]");
        btncancelar1.setNextFocusableComponent(btnsalir1);
        btncancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar1ActionPerformed(evt);
            }
        });

        btnsalir1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnsalir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728935 - exit left logout.png"))); // NOI18N
        btnsalir1.setMnemonic('s');
        btnsalir1.setText("Salir");
        btnsalir1.setToolTipText("[Alt + s]");
        btnsalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir1ActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar Ordenes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N

        tablaordenes.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tablaordenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaordenes.setComponentPopupMenu(jPopupMenu2);
        tablaordenes.setNextFocusableComponent(btnimprimirdjj);
        tablaordenes.setOpaque(false);
        tablaordenes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaordenes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaordenesKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tablaordenes);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Total: $");

        txttotal.setEditable(false);
        txttotal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txttotal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txttotal.setBorder(null);
        txttotal.setNextFocusableComponent(txtmatricula);
        txttotal.setOpaque(false);

        txtordenes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtordenes.setForeground(new java.awt.Color(0, 102, 204));
        txtordenes.setToolTipText("Buscar por dni paciente o num paciente o nom paciente o num orden");
        txtordenes.setEnabled(false);
        txtordenes.setNextFocusableComponent(tablaordenes);
        txtordenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtordenesActionPerformed(evt);
            }
        });
        txtordenes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtordenesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtordenesKeyReleased(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Ordenes OK:");

        txttotalordenes.setEditable(false);
        txttotalordenes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txttotalordenes.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txttotalordenes.setBorder(null);
        txttotalordenes.setOpaque(false);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Anuladas:");

        txttotalanuladas.setEditable(false);
        txttotalanuladas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txttotalanuladas.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txttotalanuladas.setBorder(null);
        txttotalanuladas.setOpaque(false);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Observadas:");

        txttotalobservadas.setEditable(false);
        txttotalobservadas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txttotalobservadas.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txttotalobservadas.setBorder(null);
        txttotalobservadas.setOpaque(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtordenes, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotalordenes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotalanuladas, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotalobservadas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotal)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtordenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttotalobservadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)
                            .addComponent(jLabel14))
                        .addComponent(txttotalordenes)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(txttotalanuladas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        txttotal.getAccessibleContext().setAccessibleName("");

        btnimprimirdjj.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnimprimirdjj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728912 - book reading.png"))); // NOI18N
        btnimprimirdjj.setMnemonic('i');
        btnimprimirdjj.setText("Detalle de O. S.");
        btnimprimirdjj.setToolTipText("[Alt + i]");
        btnimprimirdjj.setNextFocusableComponent(btnimprimirobra);
        btnimprimirdjj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirdjjActionPerformed(evt);
            }
        });

        btnimprimirobra.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnimprimirobra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728930 - down download.png"))); // NOI18N
        btnimprimirobra.setMnemonic('d');
        btnimprimirobra.setText("Cerrar Periodo y Descargar DDJJ");
        btnimprimirobra.setToolTipText("[Alt + d]");
        btnimprimirobra.setNextFocusableComponent(btncancelar1);
        btnimprimirobra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirobraActionPerformed(evt);
            }
        });

        btncancelar3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btncancelar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728928 - document paper.png"))); // NOI18N
        btncancelar3.setMnemonic('c');
        btncancelar3.setText("Resumen de O.S.");
        btncancelar3.setToolTipText("[Alt + c]");
        btncancelar3.setNextFocusableComponent(btnsalir1);
        btncancelar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar3ActionPerformed(evt);
            }
        });

        lblcolegiado2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblcolegiado2.setForeground(new java.awt.Color(0, 102, 255));
        lblcolegiado2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setText("Cerrar Sesión");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FacturacionLayout = new javax.swing.GroupLayout(Facturacion);
        Facturacion.setLayout(FacturacionLayout);
        FacturacionLayout.setHorizontalGroup(
            FacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FacturacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(FacturacionLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(FacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FacturacionLayout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jButton6))
                            .addGroup(FacturacionLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblcolegiado2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addComponent(jLabel13)
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addGroup(FacturacionLayout.createSequentialGroup()
                        .addComponent(btnimprimirdjj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncancelar3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnimprimirobra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncancelar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsalir1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        FacturacionLayout.setVerticalGroup(
            FacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FacturacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FacturacionLayout.createSequentialGroup()
                        .addComponent(lblcolegiado2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6))
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsalir1)
                    .addComponent(btnimprimirobra)
                    .addComponent(btncancelar1)
                    .addComponent(btncancelar3)
                    .addComponent(btnimprimirdjj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Facturación", null, Facturacion, "Facturación");
        Facturacion.getAccessibleContext().setAccessibleParent(Facturacion);

        Utilitarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UtilitariosKeyPressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Bauhaus 93", 1, 60)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 204, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cbt2.png"))); // NOI18N

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Novedades", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N

        txtnovedad.setEditable(false);
        txtnovedad.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnovedad.setForeground(new java.awt.Color(0, 102, 204));
        jScrollPane5.setViewportView(txtnovedad);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Utilitarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N

        jButton3.setBackground(new java.awt.Color(0, 0, 204));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728979 - info.png"))); // NOI18N
        jButton3.setText("Versión");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnobrasociales.setBackground(new java.awt.Color(0, 0, 204));
        btnobrasociales.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnobrasociales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728922 - apartment building city.png"))); // NOI18N
        btnobrasociales.setMnemonic('o');
        btnobrasociales.setText("Obras Sociales");
        btnobrasociales.setToolTipText("[Alt + o]");
        btnobrasociales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnobrasocialesActionPerformed(evt);
            }
        });
        btnobrasociales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnobrasocialesKeyPressed(evt);
            }
        });

        btnnomenclador.setBackground(new java.awt.Color(0, 0, 204));
        btnnomenclador.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnnomenclador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728959 - announcement flyer news newspaper .png"))); // NOI18N
        btnnomenclador.setMnemonic('n');
        btnnomenclador.setText("Nomenclador");
        btnnomenclador.setToolTipText("[Alt + n]");
        btnnomenclador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnomencladorActionPerformed(evt);
            }
        });
        btnnomenclador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnnomencladorKeyPressed(evt);
            }
        });

        btnnbu.setBackground(new java.awt.Color(0, 0, 204));
        btnnbu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnnbu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728933 - document edit.png"))); // NOI18N
        btnnbu.setMnemonic('o');
        btnnbu.setText("Nomenclador Bioquimico");
        btnnbu.setToolTipText("[Alt + u]");
        btnnbu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnbuActionPerformed(evt);
            }
        });
        btnnbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnnbuKeyPressed(evt);
            }
        });

        btnPrensa.setBackground(new java.awt.Color(0, 0, 204));
        btnPrensa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnPrensa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/prensa.png"))); // NOI18N
        btnPrensa.setText("Prensa");
        btnPrensa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrensaActionPerformed(evt);
            }
        });

        btnSubsidio.setBackground(new java.awt.Color(0, 0, 204));
        btnSubsidio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSubsidio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/subsidio.png"))); // NOI18N
        btnSubsidio.setText("Subsidio");
        btnSubsidio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubsidioActionPerformed(evt);
            }
        });

        btnIosfa.setBackground(new java.awt.Color(0, 0, 204));
        btnIosfa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnIosfa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/iosfa.png"))); // NOI18N
        btnIosfa.setText("Iosfa");
        btnIosfa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIosfaActionPerformed(evt);
            }
        });

        btnsalir2.setBackground(new java.awt.Color(0, 0, 204));
        btnsalir2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnsalir2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logocbt.png"))); // NOI18N
        btnsalir2.setMnemonic('s');
        btnsalir2.setText("Pagina Web CBT");
        btnsalir2.setToolTipText("[Alt + s]");
        btnsalir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir2ActionPerformed(evt);
            }
        });

        btnsalir4.setBackground(new java.awt.Color(0, 0, 204));
        btnsalir4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnsalir4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728983 - folder.png"))); // NOI18N
        btnsalir4.setMnemonic('c');
        btnsalir4.setText("Carpeta de descargas");
        btnsalir4.setToolTipText("[Alt + c]");
        btnsalir4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir4ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 0, 204));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/teamviewer-icon-32.png"))); // NOI18N
        jButton1.setText("Asistencia Remota");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(0, 0, 204));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728901 - archive office.png"))); // NOI18N
        jButton8.setText("Info Digital");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        btnSubsidio1.setBackground(new java.awt.Color(0, 0, 204));
        btnSubsidio1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSubsidio1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/logo_pami.png"))); // NOI18N
        btnSubsidio1.setText("Padrón PAMI");
        btnSubsidio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubsidio1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnPrensa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnbu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSubsidio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSubsidio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnobrasociales, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnIosfa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnomenclador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnsalir4, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsalir2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnnbu, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnobrasociales)
                            .addComponent(jButton3))
                        .addComponent(btnnomenclador)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPrensa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSubsidio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIosfa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsalir2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSubsidio1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsalir4)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        lblcolegiado3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblcolegiado3.setForeground(new java.awt.Color(0, 102, 255));
        lblcolegiado3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnsalir5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnsalir5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728935 - exit left logout.png"))); // NOI18N
        btnsalir5.setMnemonic('s');
        btnsalir5.setText("Salir");
        btnsalir5.setToolTipText("[Alt + s]");
        btnsalir5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir5ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton5.setText("Cerrar Sesión");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UtilitariosLayout = new javax.swing.GroupLayout(Utilitarios);
        Utilitarios.setLayout(UtilitariosLayout);
        UtilitariosLayout.setHorizontalGroup(
            UtilitariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UtilitariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(UtilitariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UtilitariosLayout.createSequentialGroup()
                        .addGap(0, 664, Short.MAX_VALUE)
                        .addComponent(btnsalir5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UtilitariosLayout.createSequentialGroup()
                        .addGroup(UtilitariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(UtilitariosLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton5)
                                .addGap(94, 94, 94))
                            .addGroup(UtilitariosLayout.createSequentialGroup()
                                .addComponent(lblcolegiado3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)))
                        .addComponent(jLabel15)))
                .addContainerGap())
        );
        UtilitariosLayout.setVerticalGroup(
            UtilitariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UtilitariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(UtilitariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(UtilitariosLayout.createSequentialGroup()
                        .addComponent(lblcolegiado3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsalir5)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Utilitarios", null, Utilitarios, "Utilitarios");

        jScrollPane3.setViewportView(jTabbedPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }

    void cargaractualizacion() {
        ConexionMariaDB mysql = new ConexionMariaDB();
        Connection cn = mysql.Conectar();
        String sSQL = "SELECT version FROM actulaizacion ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                version = (rs.getString("version"));
            }
            cn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "No se pudo conectar al servidor... Verifique su conexión a internet");
        }
    }

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        btnaceptar.setEnabled(false);
        int banderaControl = 0;
        int banderaMedicoAutorizado = 1;
        int estadoPami = 1;
        int estado_orden;
        String fechaDate = "";
        /////////////////PAMO//////////////////////
        if (tablapracticas.getRowCount() > 0) {
            if (id_obra_social == 58) {
                ConexionMariaDB mysqlpAMI = new ConexionMariaDB();
                Connection cnPAMI = mysqlpAMI.Conectar();
                String num_orden_PAMI = txtnumorden.getText();
                txtfecha.setText(txtDiaOrden.getText() + "/" + txtmes.getText() + "/" + txtaño.getText());
                fechaDate = txtaño.getText() + "-" + txtmes.getText() + "-" + txtDiaOrden.getText();

                if (banderamodifica == 0) {
                    try {
                        String controlOrden = "SELECT COUNT(numero_orden) FROM vista_ordenes_control WHERE id_obrasocial=58 and numero_orden= " + num_orden_PAMI;
//                        String controlOrden = "SELECT numero_orden FROM vista_ordenes_control WHERE id_obrasocial=58 and numero_orden= " + num_orden_PAMI;
                        Statement stControl = cnPAMI.createStatement();
                        ResultSet rsControl = stControl.executeQuery(controlOrden);
                        rsControl.next();
                        banderaControl = rsControl.getInt(1);
                        if (banderaControl == 0) {
                            estado_orden = 3;
                            if (!txtnumafiliado.getText().equals("")
                                    && txtnumafiliado.getText().length() == 14
                                    && !txtnombreafiliado.getText().equals("")
                                    && isNumeric(txtDiaOrden.getText())
                                    && txtDiaOrden.getText().length() == 2) {
                                if ((txtnumorden.getText().substring(0, 4).equals("33" + txtaño.getText().substring(2))
                                        || txtnumorden.getText().substring(0, 4).equals("33" + (Integer.valueOf(txtaño.getText().substring(2)) - 1)))
                                        && txtnumorden.getText().length() == 13
                                        && cbotipo.getSelectedIndex() == 0) {
                                    System.out.println("pami 1");
                                    estadoPami = 1;

                                } else if ((txtnumorden.getText().substring(2, 4).equals(txtaño.getText().substring(2))
                                        || txtnumorden.getText().substring(2, 4).equals(String.valueOf(Integer.valueOf(txtaño.getText().substring(2)) - 1)))
                                        && txtnumorden.getText().length() == 11
                                        && cbotipo.getSelectedIndex() == 1) {
                                    System.out.println("pami 2");
                                    estadoPami = 1;
                                } else if (cbotipo.getSelectedIndex() == 2) {
                                    System.out.println("pami 3");
                                    estadoPami = 1;
                                } else {
                                    estadoPami = 0;
                                    JOptionPane.showMessageDialog(null, "Número de orden incorrecto");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Debe completar todos los datos obligatorios");
                                txtnumafiliado.requestFocus();
                                estadoPami = 0;
                            }
                        } else {
                            estadoPami = 0;
                            JOptionPane.showMessageDialog(null, "La orden ya se encuentra cargada. Separar bono");
                        }
                    } catch (SQLException ex) {
                        estadoPami = 0;
                        Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    estado_orden = 3;
                    if (!txtnumafiliado.getText().equals("")
                            && txtnumafiliado.getText().length() == 14
                            && !txtnombreafiliado.getText().equals("")
                            && isNumeric(txtDiaOrden.getText())
                            && txtDiaOrden.getText().length() == 2) {
                        if ((txtnumorden.getText().substring(0, 4).equals("33" + txtaño.getText().substring(2))
                                || txtnumorden.getText().substring(0, 4).equals("33" + (Integer.valueOf(txtaño.getText().substring(2)) - 1)))
                                && txtnumorden.getText().length() == 13
                                && cbotipo.getSelectedIndex() == 0) {
                            System.out.println("pami 1");
                            estadoPami = 1;

                        } else if ((txtnumorden.getText().substring(2, 4).equals(txtaño.getText().substring(2))
                                || txtnumorden.getText().substring(2, 4).equals(String.valueOf(Integer.valueOf(txtaño.getText().substring(2)) - 1)))
                                && txtnumorden.getText().length() == 11
                                && cbotipo.getSelectedIndex() == 1) {
                            System.out.println("pami 2");
                            estadoPami = 1;
                        } else if (cbotipo.getSelectedIndex() == 2) {
                            System.out.println("pami 3");
                            estadoPami = 1;
                        } else {
                            estadoPami = 0;
                            JOptionPane.showMessageDialog(null, "Número de orden incorrecto");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Debe completar todos los datos obligatorios");
                        txtnumafiliado.requestFocus();
                        estadoPami = 0;
                    }
                }
            } else {
                fechaDate = invertir(txtfecha.getText());
            }
            ///////////////////////////////////////////
            System.out.println("estadoPami " + estadoPami);
            cargarip();
            try {
                cargar_fecha_jerarquicos();
            } catch (DatatypeConfigurationException ex) {
                Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
            }
            habilitado = "";
            respuestapractica = "";
            String plan_ss = "";
            String coseguro_ss = "";
            int respuesta = 0;
            int banderaControlMedico = 0;
            cargarfecha();
            int banderaEstadoOffline = 1, bandera_periodo = 0, bandera_medife = 1, bandera_obra_social_comun = 1, bandera_osde = 1, bandera_boreal = 1, bandera_sw = 1, bandera_sancor = 1, bandera_subsidio = 1, bandera_actualizacion = 0, bandera_jerarquicos = 1, bandera_iosfa = 1, bandera_ospe = 1, bandera_asunt = 1;
            ConexionMariaDB mysql = new ConexionMariaDB();
            double COSEGURO = 0.0;
            Connection cn = mysql.Conectar();
            String num_orden = txtnumorden.getText(), nom_afiliado = txtnombreafiliado.getText(), coseguro = "", fecha_servidor;
            String sSQL2 = "SELECT usuario_laboratorio,periodos FROM colegiados ";

            String sSQL9 = "SELECT ADDTIME(now(), '00:10:00');";
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sSQL2);
                Statement st1 = cn.createStatement();
                //////////////////////////// Verifico el mes
                ResultSet rs9 = st1.executeQuery(sSQL9);
                rs9.next();
                fecha_servidor = rs9.getString(1).substring(0, 4) + rs9.getString(1).substring(5, 7);
                /////////////////////////////////VERIFICAMOS PERIODO
                bandera_periodo = 0;
                banderaControlMedico = comprobar_medico(Integer.valueOf(txtmatricula.getText()));
                cargaractualizacion();
                if (!version.equals(version_actual)) {
                    bandera_actualizacion = 1;
                    new Actualizacion(this, true).setVisible(true);
                }
                if (!(txtaño.getText() + txtmes.getText()).equals(fecha_servidor)) {
                    if (obra.equals("50015 - BOREAL") || obra.equals("10070 - SWISS MEDICAL GROUP S.A. - ONLINE") || obra.equals("3100 - OSDE") || obra.equals("3101 - OSDE  ( RESPONSABLES INSCRIPTOS)") || obra.equals("1805 - SUBSIDIO DE SALUD - ONLINE") || obra.equals("9000 - ASOCIACION MUTUAL SANCOR") || obra.equals("1806 - SUBSIDIO DE SALUD - AUTORIZACION - ONLINE")) {
                        JOptionPane.showMessageDialog(null, "El periodo es diferente al que desea Validar ");
                        bandera_periodo = 1;
                    }
                }
                while (rs.next()) {
                    if (Login.nombre_usuario.equals(rs.getString("usuario_laboratorio"))) {
                        periodo_colegiado = rs.getInt("periodos");
                        break;
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
            System.out.println("bandera_periodo:" + bandera_periodo);
            System.out.println("bandera_actualizacion:" + bandera_actualizacion);
            System.out.println("banderaControlMedico:" + banderaControlMedico);
            if (bandera_periodo == 0 && bandera_actualizacion == 0 && banderaControlMedico == 0 && estadoPami == 1) {
                if (obra.equals("50015 - BOREAL")
                        || obra.equals("10070 - SWISS MEDICAL GROUP S.A. - ONLINE")
                        || obra.equals("3100 - OSDE")
                        || obra.equals("3101 - OSDE  ( RESPONSABLES INSCRIPTOS)")
                        || obra.equals("1805 - SUBSIDIO DE SALUD - ONLINE")
                        || obra.equals("9000 - ASOCIACION MUTUAL SANCOR")
                        || obra.equals("1806 - SUBSIDIO DE SALUD - AUTORIZACION - ONLINE")
                        || obra.equals("512 - MEDIFE - ONLINE OBLIGATORIO PRE PAGA C.M.C.  S.A.")
                        || obra.equals("513 - MEDIFE - ONLINE VOLUNTARIO PRE PAGA C.M.C.  S.A.")
                        || obra.equals("37701 - JERARQUICOS SALUD - EMP. BNA - ONLINE")
                        //  || obra.equals("40813 - IOSFA")
                        || obra.equals("40600 - OSPE- OBRA SOCIAL DE PETROLEROS")
                        || obra.equals("2700 - UNT - Accion  Social de la  UNT")) {

                    if (obra.equals("3100 - OSDE") || obra.equals("3101 - OSDE  ( RESPONSABLES INSCRIPTOS)")) {

                        plan_ss = "";
                        coseguro_ss = "";
                        cursor();
                        String mensajenuevo = "";
                        //////////////////////////////////////////////////////////////
                        if (tablapracticas.getRowCount() != 0) {

                            int n2, i = 0;
                            String cod_practca, nombre_practca, practicas = "", id_practicas = "";
                            n2 = tablapracticas.getRowCount();

                            if (n2 != 0) {
                                while (i < n2) {
                                    id_practicas = id_practicas + String.valueOf(tablapracticas.getValueAt(i, 5).toString());
                                    practicas = practicas + String.valueOf(tablapracticas.getValueAt(i, 1).toString());
                                    plan_ss = plan_ss + "00";
                                    coseguro_ss = coseguro_ss + "00000.0";
                                    cod_practca = tablapracticas.getValueAt(i, 4).toString();
                                    // Verificacion de Practicas
                                    if (cod_practca.equals("664418")) {
//                                    JOptionPane.showMessageDialog(null, "La Matricula medica No está autorizada a realizar la practica 664418 - DIMERO D");
//                                    banderaMedicoAutorizado = 0;
//                                    bandera_osde = 0;
//                                    break;
                                        if (MedicosAutorizados.buscarMedicosAutorizados(Integer.valueOf(txtmatricula.getText()), listaMedicos)) {
                                            //   banderaMedicoAutorizado = 1;
                                            bandera_osde = 1;
                                        } else {
                                            JOptionPane.showMessageDialog(null, "La Matricula medica No está autorizada a realizar la practica 664418 - DIMERO D");
                                            banderaMedicoAutorizado = 0;
                                            bandera_osde = 0;
                                            break;
                                        }
                                    }
                                    nombre_practca = tablapracticas.getValueAt(i, 2).toString();
                                    mensajenuevo = mensajenuevo + "<DetalleProcedimientos><NroItem>" + (i + 1) + "</NroItem><CodPrestacion>" + cod_practca + "</CodPrestacion><CodAlternativo></CodAlternativo><TipoPrestacion>" + tipo_orden + "</TipoPrestacion><ArancelPrestacion>0</ArancelPrestacion><CantidadSolicitada>1</CantidadSolicitada><DescripcionPrestacion>" + nombre_practca + "</DescripcionPrestacion></DetalleProcedimientos>";
                                    i++;
                                }
                            }
                            //System.out.println("bandera medico: " + banderaMedicoAutorizado);
                            if (banderaMedicoAutorizado == 1) {
                                /////////////////////////////////////////////////////////////////////////////////
                                mensajepractica = "<Mensaje><EncabezadoMensaje><VersionMsj>1.0</VersionMsj><TipoTransaccion>02L</TipoTransaccion><IdMsj>" + hora + "</IdMsj><InicioTrx><FechaTrx>" + fechaMySql + "</FechaTrx><HoraTrx>" + hora + "</HoraTrx></InicioTrx><Financiador><CodigoFinanciador>11</CodigoFinanciador><CuitFinanciador>30546741253</CuitFinanciador></Financiador><Prestador><CuitPrestador>" + cuit + "</CuitPrestador><RazonSocial>" + nombre_colegiado + "</RazonSocial></Prestador></EncabezadoMensaje><EncabezadoAtencion><Efector/><Prescriptor><NroMatriculaPrescriptor>" + txtmatricula.getText() + "</NroMatriculaPrescriptor></Prescriptor><Credencial><NumeroCredencial>" + txtnumafiliado.getText() + "</NumeroCredencial><VersionCredencial>" + CSC_OS + "</VersionCredencial></Credencial><Preautorizacion/><Documentacion/><Atencion/><Diagnostico/><CodFinalizacionTratamiento/><MensajeParaFinanciador/></EncabezadoAtencion>" + mensajenuevo + "</Mensaje>";
                                HttpOsdePractica http2 = new HttpOsdePractica();
                                System.out.println("Testing 2 - Send Http GET request");
                                try {
                                    http2.sendGet();
                                    /////////////////////////////////////////////////////////////////
                                    int pos = respuestapractica.indexOf("MensajeDisplay");
                                    int pos2 = respuestapractica.indexOf("/MensajeDisplay");
                                    if (respuestapractica.substring(pos + 15, pos + 17).equals("OK")) {
                                        estado_orden = 1;
                                        mensajenuevo = "";
                                        int pos3 = respuestapractica.indexOf("<NroReferencia>");
                                        int pos4 = respuestapractica.indexOf("</NroReferencia>");
                                        num_orden = respuestapractica.substring(pos3 + 15, pos4);

                                        validar_orden osde = new validar_orden();

                                        respuesta = osde.valida(
                                                Integer.valueOf(txtaño.getText() + txtmes.getText()),
                                                txtnombreafiliado.getText(),
                                                txtdocumento.getText(),
                                                txtnumafiliado.getText(),
                                                Integer.valueOf(txtmatricula.getText()),
                                                num_orden,
                                                fecha,
                                                Double.valueOf(txttotal1.getText()),
                                                fecha,
                                                hora,
                                                ip2,
                                                id_obra_social,
                                                id_usuario,
                                                n2,
                                                practicas,
                                                Double.valueOf(txtcoseguro.getText()),
                                                txtfechacoseguro.getText(),
                                                tipo_orden,
                                                observacion,
                                                plan_ss,
                                                coseguro_ss,
                                                estado_orden,
                                                fechaDate);

                                        if (respuesta == 0) {//en el caso se q no se grabe en nuestro servidor se anula del wsdl
                                            System.out.println("Anulacion Osde");
                                            mensajeanulacion = "<Mensaje><EncabezadoMensaje><VersionMsj>1.0</VersionMsj><NroReferenciaCancel>" + num_orden + "</NroReferenciaCancel><TipoTransaccion>04A</TipoTransaccion><IdMsj>" + hora + "</IdMsj><InicioTrx><FechaTrx>" + fechaMySql + "</FechaTrx><HoraTrx>" + hora + "</HoraTrx></InicioTrx><Financiador><CodigoFinanciador>11</CodigoFinanciador><CuitFinanciador>30546741253</CuitFinanciador></Financiador><Prestador><CuitPrestador>" + cuit + "</CuitPrestador></Prestador></EncabezadoMensaje><EncabezadoAtencion><Efector/><Prescriptor/><Credencial><NumeroCredencial>" + txtnumafiliado.getText() + "</NumeroCredencial></Credencial><Atencion><FechaAtencion>" + fecha + "</FechaAtencion></Atencion></EncabezadoAtencion></Mensaje>";
                                            HttpOsdeAnulacion http = new HttpOsdeAnulacion();
                                            System.out.println("Testing 3 - Send Http GET request");
                                            try {
                                                cursor2();
                                                http.sendGet();
                                                bandera_osde = 0;
                                                JOptionPane.showMessageDialog(null, "La orden no fue validada");
                                            } catch (Exception ex) {
                                                cursor2();
                                                bandera_osde = 0;
                                                JOptionPane.showMessageDialog(null, "Error al intentar anular orden del servidor de OSDE");
                                                JOptionPane.showMessageDialog(null, ex);

                                            }
                                        } else {
                                            cursor2();
                                            bandera_osde = 1;
                                            JOptionPane.showMessageDialog(null, "Nro. Transaccion: " + num_orden.substring(3, 9));
                                            System.out.println("borrar 1");
                                            borrartabla();
                                        }

                                    } else {

                                        estado_orden = 0;
                                        mensajenuevo = "";
                                        int pos3 = respuestapractica.indexOf("<NroReferencia>");
                                        int pos4 = respuestapractica.indexOf("</NroReferencia>");
                                        num_orden = respuestapractica.substring(pos3 + 15, pos4);

                                        observacion = respuestapractica;
                                        if (observacion.length() > 500) {
                                            observacion = observacion.substring(0, 500);
                                        }

                                        validar_orden osde = new validar_orden();

                                        respuesta = osde.valida(
                                                Integer.valueOf(txtaño.getText() + txtmes.getText()),
                                                txtnombreafiliado.getText(),
                                                txtdocumento.getText(),
                                                txtnumafiliado.getText(),
                                                Integer.valueOf(txtmatricula.getText()),
                                                num_orden,
                                                fecha,
                                                Double.valueOf(txttotal1.getText()),
                                                fecha,
                                                hora,
                                                ip2,
                                                id_obra_social,
                                                id_usuario,
                                                n2,
                                                practicas,
                                                Double.valueOf(txtcoseguro.getText()),
                                                txtfechacoseguro.getText(),
                                                tipo_orden,
                                                observacion,
                                                plan_ss,
                                                coseguro_ss,
                                                estado_orden,
                                                fechaDate);
                                        System.out.println(respuestapractica);
                                        if (respuesta == 0) {//en el caso se q no se grabe en nuestro servidor se anula del wsdl
                                            cursor2();
                                            JOptionPane.showMessageDialog(null, "La orden no pudo ser cargada");
                                            bandera_osde = 0;
                                        } else {
                                            cursor2();
                                            bandera_osde = 0;
                                            JOptionPane.showMessageDialog(null, "La orden no pudo ser cargada en el servidor de osde");
                                            JOptionPane.showMessageDialog(null, respuestapractica.substring(pos + 15, pos2));
                                            System.out.println("borrar 2");
                                            borrartabla();
                                        }
                                    }

                                } catch (Exception ex) {
                                    cursor2();
                                    bandera_osde = 0;
                                    JOptionPane.showMessageDialog(this, ex);
                                    JOptionPane.showMessageDialog(null, "Error al conectarse al servidor de Osde");
                                }

                            } else {
                                cursor2();
                                // JOptionPane.showMessageDialog(null, "No hay practicas en la tabla...");
                            }

                        }
                    }
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if (obra.equals("10070 - SWISS MEDICAL GROUP S.A. - ONLINE")) {
                        plan_ss = "";
                        coseguro_ss = "";
                        cursor();
                        //////////////////////////////////////////////////////////////
                        if (tablapracticas.getRowCount() != 0) {
                            int n2, i = 0;
                            String cod_practca, nombre_practca, practicas = "", id_practicas = "";
                            n2 = tablapracticas.getRowCount();
                            mensajepractica = n2 + "^";
                            if (n2 != 0) {
                                while (i < n2) {
                                    plan_ss = plan_ss + "00";
                                    coseguro_ss = coseguro_ss + "00000.0";
                                    id_practicas = id_practicas + String.valueOf(tablapracticas.getValueAt(i, 5).toString());
                                    practicas = practicas + String.valueOf(tablapracticas.getValueAt(i, 1).toString()).substring(0, 6);
                                    cod_practca = tablapracticas.getValueAt(i, 1).toString().substring(0, 6);
                                    nombre_practca = tablapracticas.getValueAt(i, 2).toString();
                                    if (i == n2) {
                                        mensajepractica = mensajepractica + "*" + cod_practca + "*1**";
                                    } else {
                                        mensajepractica = mensajepractica + "*" + cod_practca + "*1**|";
                                    }
                                    i++;
                                }
                            }

                            ///////////////////////////////////////////////////////////////////////////////////////                        
                            apiSwPracticas post = new apiSwPracticas();
                            System.out.println("Testing 2 - Send Http Post request");
                            try {
                                System.out.println("intento de registracion");
                                int estado = post.sendPost();
                                System.out.println("trae estadosw");
                                if (estado != 0) {
                                    //////HABILITADO//////
                                    estado_orden = 1;
                                    num_orden = String.valueOf(estado);
                                    validar_orden swm = new validar_orden();

                                    respuesta = swm.valida(
                                            Integer.valueOf(txtaño.getText() + txtmes.getText()),
                                            txtnombreafiliado.getText(),
                                            txtdocumento.getText(),
                                            txtnumafiliado.getText(),
                                            Integer.valueOf(txtmatricula.getText()),
                                            num_orden,
                                            fecha,
                                            Double.valueOf(txttotal1.getText()),
                                            fecha,
                                            hora,
                                            ip2,
                                            id_obra_social,
                                            id_usuario,
                                            n2,
                                            practicas,
                                            Double.valueOf(txtcoseguro.getText()),
                                            txtfechacoseguro.getText(),
                                            tipo_orden,
                                            observacion,
                                            plan_ss,
                                            coseguro_ss,
                                            estado_orden,
                                            fechaDate);
                                    if (respuesta == 0) {//en el caso se q no se grabe en nuestro servidor se anula del wsdl
                                        JOptionPane.showMessageDialog(null, "La orden no fue validada");
                                        System.out.println("Anulacion sw");
                                        try {
                                            apiSwAnulacion postC = new apiSwAnulacion();
                                            postC.sendPost(txtnumafiliado.getText(), num_orden, apiKey);
                                            bandera_sw = 0;
                                            cursor2();
                                            JOptionPane.showMessageDialog(null, "La orden no fue validada");
                                        } catch (Exception ex) {
                                            cursor2();
                                            JOptionPane.showMessageDialog(null, ex);
                                            bandera_sw = 0;
                                        }

                                    } else {
                                        cursor2();
                                        bandera_sw = 1;
                                        JOptionPane.showMessageDialog(null, "Nro. Transaccion: " + num_orden);
                                        borrartabla();
                                    }
                                } else {
                                    //////ERROR//////
                                    estado_orden = 0;
                                    num_orden = String.valueOf(estado);

                                    validar_orden swm = new validar_orden();

                                    respuesta = swm.valida(
                                            Integer.valueOf(txtaño.getText() + txtmes.getText()),
                                            txtnombreafiliado.getText(),
                                            txtdocumento.getText(),
                                            txtnumafiliado.getText(),
                                            Integer.valueOf(txtmatricula.getText()),
                                            num_orden,
                                            fecha,
                                            Double.valueOf(txttotal1.getText()),
                                            fecha,
                                            hora,
                                            ip2,
                                            id_obra_social,
                                            id_usuario,
                                            n2,
                                            practicas,
                                            Double.valueOf(txtcoseguro.getText()),
                                            txtfechacoseguro.getText(),
                                            tipo_orden,
                                            observacion,
                                            plan_ss,
                                            coseguro_ss,
                                            estado_orden,
                                            fechaDate);
                                    if (respuesta == 0) {//en el caso de q no se grabe en nuestro servidor se anula del wsdl
                                        cursor2();
                                        JOptionPane.showMessageDialog(null, "La orden no pudo ser cargada");
                                        bandera_sw = 0;

                                    } else {
                                        cursor2();
                                        bandera_sw = 0;
                                        JOptionPane.showMessageDialog(null, "La orden no pudo ser cargada en el servidor de Swiss Medical");

                                        borrartabla();
                                    }

                                }
                            } catch (Exception ex) {
                                cursor2();
                                JOptionPane.showMessageDialog(this, ex);
                                JOptionPane.showMessageDialog(null, "Error al conectarse al servidor de Swiss Medical");
                            }
                        } else {
                            cursor2();
                            //JOptionPane.showMessageDialog(null, "No hay practicas en la tabla...");
                        }
                    }
                    //location	C:\Repositorio_git\facturacion\src\main\resources\Reportes\Comprobante_Ospe.jrxml
                    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if (obra.equals("40600 - OSPE- OBRA SOCIAL DE PETROLEROS")) {
                        plan_ss = "";
                        coseguro_ss = "";
                        cursor();
                        String DetalleProcedimientos = "";
                        if (tablapracticas.getRowCount() != 0) {
                            int n2, i = 0;
                            String cod_practca, practicas = "", id_practicas = "";
                            n2 = tablapracticas.getRowCount();
                            if (n2 != 0) {
                                while (i < n2) {
                                    plan_ss = plan_ss + "00";
                                    coseguro_ss = coseguro_ss + "00000.0";
                                    id_practicas = id_practicas + String.valueOf(tablapracticas.getValueAt(i, 5).toString());
                                    cod_practca = tablapracticas.getValueAt(i, 1).toString();
                                    practicas = practicas + String.valueOf(tablapracticas.getValueAt(i, 1).toString()).substring(0, 6);
                                    DetalleProcedimientos = DetalleProcedimientos + "<DetalleProcedimientos><CodPrestacion>" + cod_practca + "</CodPrestacion><TipoPrestacion>1</TipoPrestacion><CantidadSolicitada>1</CantidadSolicitada></DetalleProcedimientos>";
                                    i++;
                                }
                            }

                            try {
                                ExecuteFileTransactionSL mensaje = new ExecuteFileTransactionSL();
                                mensaje.setPos("0000");
                                String xml = "<Mensaje>\n"
                                        + "	<EncabezadoMensaje>\n"
                                        + "		<VersionMsj>ACT20</VersionMsj>\n"
                                        + "		<TipoMsj>OL</TipoMsj>\n"
                                        + "		<TipoTransaccion>02A</TipoTransaccion>\n"
                                        + "		<IdMsj></IdMsj>\n"
                                        + "		<InicioTrx>\n"
                                        + "             <FechaTrx>" + fechaMySql + "</FechaTrx>\n"
                                        + "             <HoraTrx>" + hora + "</HoraTrx>\n"
                                        + "		</InicioTrx>\n"
                                        + "		<Terminal>\n"
                                        + "            <TipoTerminal>PC</TipoTerminal>\n"
                                        + "            <NumeroTerminal>21000037</NumeroTerminal>\n"
                                        + "        </Terminal>\n"
                                        + "        <Financiador>\n"
                                        + "            <CodigoFinanciador>OSPE</CodigoFinanciador>\n"
                                        + "        </Financiador>\n"
                                        + "        <Prestador>\n"
                                        + "            <CuitPrestador>30522483881</CuitPrestador>\n"
                                        + "            <RazonSocial>Colegio de Bioquimicos de Tucuman</RazonSocial>\n"
                                        + "        </Prestador>\n"
                                        + "	</EncabezadoMensaje>\n"
                                        + "	<EncabezadoAtencion>\n"
                                        + "        <Credencial>\n"
                                        + "            <NumeroCredencial>" + OspeAfiliado.Codigo_afiliado + "</NumeroCredencial>\n"
                                        + "            <ModoIngreso>M</ModoIngreso>\n"
                                        + "            <CodigoSeguridad>" + OspeAfiliado.CSC_OS + "</CodigoSeguridad>\n"
                                        + "        </Credencial>\n"
                                        + "        <Efector>\n"
                                        + "            <CuitEfector>" + cuit + "</CuitEfector>\n"
                                        + "        </Efector>\n"
                                        + "        <Prescriptor>\n"
                                        + "            <NroMatriculaPrescriptor>" + txtmatricula.getText() + "</NroMatriculaPrescriptor>\n"
                                        + "        </Prescriptor>\n"
                                        + "	</EncabezadoAtencion>\n"
                                        + DetalleProcedimientos + "\n"
                                        + "</Mensaje>";
                                mensaje.setFileContent(xml);
                                System.out.println("Send:" + mensaje.getPos() + " " + mensaje.getFileContent());
                                String resultado = null;
                                try {
                                    WSActiviaC servicio = new WSActiviaC();
                                    WSActiviaCSoap port = servicio.getWSActiviaCSoap();
                                    resultado = port.executeFileTransactionSL(mensaje.getPos(), mensaje.getFileContent());
                                    System.out.println("resultado:" + resultado);
                                    ///observacion=resultado;
                                    //Generate XML
                                    try {
                                        FileWriter archivo2 = new FileWriter("C:/Facturacion Laboratorios/respuesta.xml");
                                        archivo2.write(resultado);
                                        archivo2.close();
                                        System.out.println("");
                                        ReadXMLFile respuestaOspe2 = new ReadXMLFile();
                                        if (respuestaOspe2.ReadXMLOspe02A().getCodigo().equals("00")) {
                                            //////HABILITADO//////
                                            DecimalFormat df = new DecimalFormat("0.00");
                                            LinkedList<camposboreal> Resultados = new LinkedList<camposboreal>();
                                            Resultados.clear();
                                            estado_orden = 1;
                                            num_orden = respuestaOspe2.ReadXMLOspe02A().getNroReferencia();

                                            ///cargar coseguro
                                            String detallePracticas = "";
                                            int j = 0;
                                            while (j < n2) {
                                                camposboreal tipo;
                                                tipo = new camposboreal(respuestaOspe2.ReadXMLOspe02A().getPracticas().get(j).getCodPrestacion(), respuestaOspe2.ReadXMLOspe02A().getPracticas().get(j).getImporteACargoAfiliado());
                                                Resultados.add(tipo);
                                                COSEGURO = COSEGURO + Double.valueOf(respuestaOspe2.ReadXMLOspe02A().getPracticas().get(j).getImporteACargoAfiliado());
                                                detallePracticas = detallePracticas + "\n" + respuestaOspe2.ReadXMLOspe02A().getPracticas().get(j).getCodPrestacion() + " - " + respuestaOspe2.ReadXMLOspe02A().getPracticas().get(j).getMensajeRta() + " - Coseguro: $" + respuestaOspe2.ReadXMLOspe02A().getPracticas().get(j).getImporteACargoAfiliado();
                                                j++;
                                            }

                                            validar_orden ospe = new validar_orden();

                                            respuesta = ospe.valida(
                                                    Integer.valueOf(txtaño.getText() + txtmes.getText()),
                                                    txtnombreafiliado.getText(),
                                                    txtdocumento.getText(),
                                                    txtnumafiliado.getText(),
                                                    Integer.valueOf(txtmatricula.getText()),
                                                    num_orden,
                                                    fecha,
                                                    Double.valueOf(txttotal1.getText()),
                                                    fecha,
                                                    hora,
                                                    ip2,
                                                    id_obra_social,
                                                    id_usuario,
                                                    n2,
                                                    practicas,
                                                    Double.valueOf(txtcoseguro.getText()),
                                                    txtfechacoseguro.getText(),
                                                    tipo_orden,
                                                    observacion,
                                                    plan_ss,
                                                    coseguro_ss,
                                                    estado_orden,
                                                    fechaDate);
                                            if (respuesta == 0) {//en el caso se q no se grabe en nuestro servidor se anula del wsdl
                                                JOptionPane.showMessageDialog(null, "La orden no fue validada");
                                                System.out.println("Anulacion ospe");
//                                                ////////////////////////////////////////////////////////////////////////////
                                                try {
                                                    ///   ExecuteFileTransactionSL mensaje = new ExecuteFileTransactionSL();
                                                    mensaje.setPos("0000");

                                                    xml = "<Mensaje>\n"
                                                            + "     <EncabezadoMensaje>\n"
                                                            + "		<VersionMsj>ACT20</VersionMsj>\n"
                                                            + "		<NroReferenciaCancel>" + num_orden + "</NroReferenciaCancel>\n"
                                                            + "		<TipoMsj>OL</TipoMsj>\n"
                                                            + "		<TipoTransaccion>04A</TipoTransaccion>\n"
                                                            + "		<IdMsj/>\n"
                                                            + "		<InicioTrx>\n"
                                                            + "             <FechaTrx>" + fechaMySql + "</FechaTrx>\n"
                                                            + "             <HoraTrx>" + hora + "</HoraTrx>\n"
                                                            + "		</InicioTrx>\n"
                                                            + "		<Terminal>\n"
                                                            + "            <TipoTerminal>PC</TipoTerminal>\n"
                                                            + "            <NumeroTerminal>21000037</NumeroTerminal>\n"
                                                            + "		</Terminal>\n"
                                                            + "		<Validador/>\n"
                                                            + "		<Financiador>\n"
                                                            + "            <CodigoFinanciador>OSPE</CodigoFinanciador>\n"
                                                            + "		</Financiador>\n"
                                                            + "		<Prestador>\n"
                                                            + "            <CuitPrestador>30522483881</CuitPrestador>\n"
                                                            + "            <RazonSocial>Colegio de Bioquimicos de Tucuman</RazonSocial>\n"
                                                            + "		</Prestador>\n"
                                                            + "	</EncabezadoMensaje>\n"
                                                            + "	<EncabezadoAtencion>\n"
                                                            + "		<FechaAtencion>" + fechaMySql + "</FechaAtencion>\n"
                                                            + "	</EncabezadoAtencion>\n"
                                                            + "</Mensaje>";
                                                    mensaje.setFileContent(xml);
                                                    System.out.println("Send:" + mensaje.getPos() + " " + mensaje.getFileContent());
                                                    resultado = null;
                                                    try {
                                                        resultado = port.executeFileTransactionSL(mensaje.getPos(), mensaje.getFileContent());
                                                        System.out.println("resultado:" + resultado);
                                                        //Generate XML
                                                        try {
                                                            FileWriter archivo3 = new FileWriter("C:/Facturacion Laboratorios/respuesta.xml");
                                                            archivo3.write(resultado);
                                                            archivo3.close();
                                                            System.out.println("");
                                                            ReadXMLFile respuestaOspe3 = new ReadXMLFile();
                                                            respuestaOspe3.ReadXMLOspe04A();
                                                        } catch (Exception er) {
                                                            System.out.println("error al generar archivo " + er);
                                                        }
                                                    } catch (Exception e) {
                                                        System.out.println("error al conectarse con servidor " + resultado);
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("e" + e);
                                                }
                                                bandera_ospe = 0;
                                            } else {
                                                cursor2();
                                                bandera_ospe = 1;

                                                ///////////////////imprimir coseguro///////////////////////////////////////////////////////////////////////////////////////
                                                int opcion = JOptionPane.showConfirmDialog(null, "Nro. Transaccion: " + num_orden + "\n" + " Mensaje WS: " + respuestaOspe2.ReadXMLOspe02A().getRespuesta() + " - " + respuestaOspe2.ReadXMLOspe02A().getMensaje() + detallePracticas + "\nDesea Imprimir el comprobante?", "Ospe Impresión", JOptionPane.YES_NO_OPTION);
                                                if (opcion == 0) {
                                                    try {
                                                        JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Comprobante_Ospe.jasper"));
                                                        ///////////////////////////////////////////////////C:\Users\Lucas\Documents\NetBeansProjects\colegio bioquimicos\src\Reportes
                                                        Map parametros = new HashMap();
                                                        parametros.put("numero_autorizacion", num_orden);
                                                        parametros.put("fecha", fecha2);
                                                        parametros.put("nombre", nom_afiliado);
                                                        parametros.put("numero", txtnumafiliado.getText());
                                                        parametros.put("total", df.format(COSEGURO));
                                                        parametros.put("bioquimico", Login.nombre_colegiado);
                                                        JasperPrint jPrint = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(Resultados));
                                                        JasperPrintManager.printReport(jPrint, false);
                                                    } catch (Exception e) {
                                                        cursor2();
                                                        JOptionPane.showMessageDialog(null, e);
                                                    }
                                                }
                                                borrartabla();
                                            }
                                        } else {
                                            //////ERROR//////
                                            estado_orden = 0;
                                            num_orden = respuestaOspe2.ReadXMLOspe02A().getNroReferencia();

                                            validar_orden ospe = new validar_orden();

                                            respuesta = ospe.valida(
                                                    Integer.valueOf(txtaño.getText() + txtmes.getText()),
                                                    txtnombreafiliado.getText(),
                                                    txtdocumento.getText(),
                                                    txtnumafiliado.getText(),
                                                    Integer.valueOf(txtmatricula.getText()),
                                                    num_orden,
                                                    fecha,
                                                    Double.valueOf(txttotal1.getText()),
                                                    fecha,
                                                    hora,
                                                    ip2,
                                                    id_obra_social,
                                                    id_usuario,
                                                    n2,
                                                    practicas,
                                                    Double.valueOf(txtcoseguro.getText()),
                                                    txtfechacoseguro.getText(),
                                                    tipo_orden,
                                                    observacion,
                                                    plan_ss,
                                                    coseguro_ss,
                                                    estado_orden,
                                                    fechaDate);
                                            if (respuesta == 0) {//en el caso de q no se grabe en nuestro servidor se anula del wsdl
                                                cursor2();
                                                JOptionPane.showMessageDialog(null, "La orden no pudo ser cargada");
                                                bandera_ospe = 0;

                                            } else {
                                                cursor2();
                                                bandera_ospe = 0;
                                                String error = "";
                                                int j = 0;
                                                while (j < n2) {
                                                    error = error + "\n" + respuestaOspe2.ReadXMLOspe02A().getPracticas().get(j).getCodPrestacion() + " - " + respuestaOspe2.ReadXMLOspe02A().getPracticas().get(j).getMensajeRta() + " - Coseguro: $" + respuestaOspe2.ReadXMLOspe02A().getPracticas().get(j).getImporteACargoAfiliado();
                                                    j++;
                                                }
                                                JOptionPane.showMessageDialog(null, "La orden no pudo ser cargada en el servidor de OSPE: N° de ref: " + respuestaOspe2.ReadXMLOspe02A().getNroReferencia() + "\n" + " Mensaje WS: " + respuestaOspe2.ReadXMLOspe02A().getCodigo() + " " + respuestaOspe2.ReadXMLOspe02A().getRespuesta() + "\n" + error);
                                                borrartabla();
                                            }

                                        }

                                    } catch (Exception er) {
                                        System.out.println("error al generar archivo " + er);
                                    }
                                } catch (Exception e) {
                                    System.out.println("error al conectarse con servidor " + resultado);
                                }
                            } catch (Exception e) {
                                System.out.println("e" + e);
                            }

                        } else {
                            cursor2();
                            ///  JOptionPane.showMessageDialog(null, "No hay practicas en la tabla...");
                        }
                    }
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if (obra.equals("50015 - BOREAL")) {
                        plan_ss = "";
                        coseguro_ss = "";
                        cursor();
                        String mensajenuevo = "";
                        if (tablapracticas.getRowCount() != 0) {
                            int n2, i = 0;
                            String cod_practca, practicas = "", id_practicas = "";
                            n2 = tablapracticas.getRowCount();
                            if (n2 != 0) {
                                while (i < n2) {
                                    id_practicas = id_practicas + String.valueOf(tablapracticas.getValueAt(i, 5).toString());
                                    cod_practca = tablapracticas.getValueAt(i, 1).toString();
                                    practicas = practicas + String.valueOf(tablapracticas.getValueAt(i, 1).toString()).substring(0, 6);
                                    if (!cod_practca.equals("660001 ")) {
                                        mensajenuevo = mensajenuevo + "<Practica><LineaNro>" + (i + 1) + "</LineaNro><SeccionId>15</SeccionId><PracticaId>" + cod_practca + "</PracticaId><PracticaItem>5</PracticaItem><PracticaCantSol>1</PracticaCantSol><PracticaCantAprob></PracticaCantAprob><PracticaDes></PracticaDes><PracticaCoseguro></PracticaCoseguro><PracticaIdEstado></PracticaIdEstado><PracticaObs></PracticaObs><PracticaPreAutorizacion></PracticaPreAutorizacion></Practica>";
                                    }
                                    i++;
                                }
                            }
                            String emisor = "CBT" + completarceros(matricula_colegiado, 9);
                            Contraseña_Boreal contraseña = new Contraseña_Boreal();
                            String clave = contraseña.Boreal_Contraseña();
                            /////////////////////////////////////////////////////////////////////////////////
                            if (tipo_credencial == 0) {
                                mensajepractica = "<BOREAL><Mensaje><Canal>ID</Canal><SitioEmisor>" + emisor + "</SitioEmisor><Empresa>BOREAL</Empresa><Receptor><Nombre>BOREAL</Nombre><ID>222023</ID><Tipo>IIN</Tipo></Receptor><MsgTipo><Tipo>ZQA</Tipo><Evento>Z02</Evento><Estructura>ZQA_Z02</Estructura></MsgTipo><MsgEntorno>P</MsgEntorno></Mensaje><Seguridad><Usuario>cobitucws</Usuario><Clave>" + clave + "</Clave></Seguridad><Prestador><PrestadorId>" + cuit + "</PrestadorId><PrestadorNombre>colegiobioquimico</PrestadorNombre><PrestadorTipoIdent>CU</PrestadorTipoIdent></Prestador><Afiliado><AfiliadoNroCredencial>" + txtnumafiliado.getText().substring(0, 8) + "</AfiliadoNroCredencial><AfiliadoGf>" + txtnumafiliado.getText().substring(9, 10) + "</AfiliadoGf><TipoIdentificador>HC</TipoIdentificador></Afiliado><Practicas>" + mensajenuevo + "</Practicas></BOREAL>";
                            }
                            if (tipo_credencial == 1) {
                                mensajepractica = "<BOREAL><Mensaje><Canal>ID</Canal><SitioEmisor>" + emisor + "</SitioEmisor><Empresa>BOREAL</Empresa><Receptor><Nombre>BOREAL</Nombre><ID>222023</ID><Tipo>IIN</Tipo></Receptor><MsgTipo><Tipo>ZQA</Tipo><Evento>Z02</Evento><Estructura>ZQA_Z02</Estructura></MsgTipo><MsgEntorno>P</MsgEntorno></Mensaje><Seguridad><Usuario>cobitucws</Usuario><Clave>" + clave + "</Clave></Seguridad><Prestador><PrestadorId>" + cuit + "</PrestadorId><PrestadorNombre>colegiobioquimico</PrestadorNombre><PrestadorTipoIdent>CU</PrestadorTipoIdent></Prestador><Afiliado><AfiliadoNroCredencial>" + txtnumafiliado.getText() + "</AfiliadoNroCredencial><AfiliadoGf></AfiliadoGf><TipoIdentificador>DU</TipoIdentificador></Afiliado><Practicas>" + mensajenuevo + "</Practicas></BOREAL>";
                            }
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////            
                            System.out.println("Enviar mensaje boreal - Send Http GET request");
                            System.out.println(mensajepractica);
                            ///////////////////////////////////////////////////////////////////////////////////////////////
                            ClienteBoreal.WsBorealExecute servicio = new ClienteBoreal.WsBorealExecute();
                            servicio.setIngresoxml(mensajepractica);
                            respuestapractica = execute(servicio).getEgresoxml();
                            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
                            System.out.println("respuesta boreal - Get Http GET request");
                            System.out.println(respuestapractica);
                            ////////////////////////////////////////////////////////////////////////////////////////////////
                            int pos = respuestapractica.indexOf("<AutEstadoId>");
                            int pos2 = respuestapractica.indexOf("</AutEstadoId>");
                            if (respuestapractica.substring(pos + 13, pos2).equals("B000")) {
                                estado_orden = 1;
                                DecimalFormat df = new DecimalFormat("0.00");
                                LinkedList<camposboreal> Resultados = new LinkedList<camposboreal>();
                                Resultados.clear();

                                try {
                                    DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                                    InputSource is = new InputSource();
                                    is.setCharacterStream(new StringReader(respuestapractica));
                                    Document doc = db.parse(is);
                                    NodeList nodes = doc.getElementsByTagName("Practica");
                                    int pos5 = respuestapractica.indexOf("<AutCod>");
                                    int pos6 = respuestapractica.indexOf("</AutCod>");
                                    num_orden = respuestapractica.substring(pos5 + 8, pos6);
                                    String codigo = "", coseguroboreal = "";
                                    //////////////Documento
                                    int pos7 = respuestapractica.indexOf("<EfectorId>");
                                    int pos8 = respuestapractica.indexOf("</EfectorId>");
                                    documento = respuestapractica.substring(pos7 + 11, pos8);

                                    for (int i2 = 0; i2 < nodes.getLength(); i2++) {
                                        Element element = (Element) nodes.item(i2);
                                        NodeList name = element.getElementsByTagName("PracticaId");
                                        Element line = (Element) name.item(0);
                                        System.out.println("PracticaId: " + getCharacterDataFromElement(line));
                                        codigo = getCharacterDataFromElement(line);
                                        NodeList title = element.getElementsByTagName("PracticaCoseguro");
                                        line = (Element) title.item(0);
                                        System.out.println(getCharacterDataFromElement(line));
                                        double cos = Double.valueOf(getCharacterDataFromElement(line));
                                        COSEGURO = COSEGURO + cos;
                                        coseguroboreal = "$ " + getCharacterDataFromElement(line);
                                        /////////////////////////////////////////////////////////
                                        plan_ss = plan_ss + "00";
                                        coseguro_ss = coseguro_ss + completarceros(String.valueOf(COSEGURO), 7);
                                        ////////////////////////////////////////////////////////
                                        camposboreal tipo;
                                        tipo = new camposboreal(codigo, coseguroboreal);
                                        Resultados.add(tipo);
                                        //////////////////////////////////////////////////////////
                                    }

                                    ///////////////////////////////////////////////////////////////////////////////////////////////////////
                                    validar_orden boreal = new validar_orden();
                                    respuesta = boreal.valida(
                                            Integer.valueOf(txtaño.getText() + txtmes.getText()),
                                            txtnombreafiliado.getText(),
                                            txtdocumento.getText(),
                                            txtnumafiliado.getText(),
                                            Integer.valueOf(txtmatricula.getText()),
                                            num_orden,
                                            fecha,
                                            Double.valueOf(txttotal1.getText()),
                                            fecha,
                                            hora,
                                            ip2,
                                            id_obra_social,
                                            id_usuario,
                                            n2,
                                            practicas,
                                            COSEGURO,
                                            fecha,
                                            tipo_orden,
                                            observacion,
                                            plan_ss,
                                            coseguro_ss,
                                            estado_orden,
                                            fechaDate);
                                    if (respuesta == 0) {//en el caso se q no se grabe en nuestro servidor se anula del wsdl
                                        System.out.println("Anulacion boreal");
                                        /////////////////////////////////////////////////////////////////////////////////
                                        mensajepractica = "<Boreal><Mensaje><Canal>ID</Canal><SitioEmisor>" + emisor + "</SitioEmisor><Receptor><Nombre>BOREAL</Nombre><ID>222023</ID><Tipo>IIN</Tipo></Receptor><MsgTipo><Tipo>ZQA</Tipo><Evento>Z04</Evento><Estructura>ZQA_Z02</Estructura></MsgTipo></Mensaje><Seguridad><Usuario>cobitucws</Usuario><Clave>" + clave + "</Clave></Seguridad><Prestador><PrestadorId>" + cuit + "</PrestadorId><PrestadorTipoIdent>CU</PrestadorTipoIdent></Prestador><Autorizacion><AutCod></AutCod><AutEstadoId></AutEstadoId><AutObs></AutObs><AutCodAnulacion>" + num_orden + "</AutCodAnulacion></Autorizacion></Boreal>";
                                        System.out.println("Envio de mensaje boreal - Send Http GET request");
                                        System.out.println(mensajepractica);
                                        ///////////////////////////////////////////////////////////////////////////////////////////////
                                        ClienteBoreal.WsBorealExecute servicioAnulacion = new ClienteBoreal.WsBorealExecute();
                                        servicioAnulacion.setIngresoxml(mensajepractica);
                                        respuestapractica = execute(servicioAnulacion).getEgresoxml();
                                        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
                                        System.out.println("Respuesta boreal - Get Http GET request");
                                        System.out.println(respuestapractica);
                                        ////////////////////////////////////////////////////////////////////////////////////////////////
                                        int posI = respuestapractica.indexOf("<AutEstadoId>");
                                        int posF = respuestapractica.indexOf("</AutEstadoId>");
                                        /// JOptionPane.showMessageDialog(null, respuestapractica.substring(pos + 13, pos2));
                                        if (respuestapractica.substring(posI + 13, posF).equals("B000")) {
                                            int posI2 = respuestapractica.indexOf("<AutCod>");
                                            int posF2 = respuestapractica.indexOf("</AutCod>");
                                            num_orden = respuestapractica.substring(posI2 + 8, posF2);
                                            cursor2();
                                            bandera_boreal = 0;
                                            JOptionPane.showMessageDialog(null, "La orden no fue validada");
                                        }
                                        if (respuestapractica.substring(pos + 13, pos2).equals("M054")) {
                                            habilitado = "ERROR";
                                            int pos3 = respuestapractica.indexOf("<AutObs>");
                                            int pos4 = respuestapractica.indexOf("</AutObs>");
                                            cursor2();
                                            bandera_boreal = 0;
                                            JOptionPane.showMessageDialog(null, respuestapractica.substring(pos3 + 8, pos4));
                                            // bandera_boreal = 0;
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Nro. Autorización: " + num_orden);
                                        ///////////////////imprimir coseguro///////////////////////////////////////////////////////////////////////////////////////
                                        nom_afiliado = respuestapractica.substring(respuestapractica.indexOf("<AfiliadoNombre>") + 16, respuestapractica.indexOf("</AfiliadoNombre>"));
                                        int opcion = JOptionPane.showConfirmDialog(null, "Coseguro: $" + COSEGURO + "\nDesea Imprimir un comprobante?", "Boreal Impresíon", JOptionPane.YES_NO_OPTION);
                                        if (opcion == 0) {
                                            try {
                                                JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Comprobante_boreal.jasper"));
                                                ///////////////////////////////////////////////////C:\Users\Lucas\Documents\NetBeansProjects\colegio bioquimicos\src\Reportes
                                                Map parametros = new HashMap();
                                                parametros.put("numero_autorizacion", num_orden);
                                                parametros.put("fecha", fecha2);
                                                parametros.put("nombre", nom_afiliado);
                                                parametros.put("numero", txtnumafiliado.getText());
                                                parametros.put("total", df.format(COSEGURO));
                                                JasperPrint jPrint = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(Resultados));
                                                JasperPrintManager.printReport(jPrint, false);
                                            } catch (Exception e) {
                                                cursor2();
                                                JOptionPane.showMessageDialog(null, e);
                                            }
                                        }
                                        bandera_boreal = 1;
                                        borrartabla();
                                    }

                                } catch (Exception ex) {
                                    cursor2();
                                    JOptionPane.showMessageDialog(this, ex);
                                }
                                ////
                                txtnombreafiliado.setText(nom_afiliado);
                                txtnumorden.setText(num_orden);
                                txtcoseguro.setText(String.valueOf(COSEGURO));
                                txtfechacoseguro.setText(fecha);
                                txtdocumento.setText(documento);
                            } else {
                                int pos3 = respuestapractica.indexOf("<AutObs>");
                                int pos4 = respuestapractica.indexOf("</AutObs>");
                                observacion = respuestapractica.substring(pos3 + 8, pos4);

                                if (observacion.length() > 500) {
                                    observacion = observacion.substring(0, 500);
                                }
                                ///Orden no se pudo grabar en boreal
                                estado_orden = 0;
                                ///////////////////////////////////////////////////////////////////////////////////////////////////////
                                validar_orden boreal = new validar_orden();
                                respuesta = boreal.valida(
                                        Integer.valueOf(txtaño.getText() + txtmes.getText()),
                                        txtnombreafiliado.getText(),
                                        txtdocumento.getText(),
                                        txtnumafiliado.getText(),
                                        Integer.valueOf(txtmatricula.getText()),
                                        "0",
                                        fecha,
                                        Double.valueOf(txttotal1.getText()),
                                        fecha,
                                        hora,
                                        ip2,
                                        id_obra_social,
                                        id_usuario,
                                        n2,
                                        practicas,
                                        COSEGURO,
                                        fecha,
                                        tipo_orden,
                                        observacion,
                                        plan_ss,
                                        coseguro_ss,
                                        estado_orden,
                                        fechaDate);
                                if (respuesta == 0) {
                                    try {
                                        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                                        InputSource is = new InputSource();
                                        is.setCharacterStream(new StringReader(respuestapractica));
                                        Document doc = db.parse(is);
                                        NodeList nodes = doc.getElementsByTagName("Practica");

                                        String salida = "";
                                        for (int i2 = 0; i2 < nodes.getLength(); i2++) {
                                            Element element = (Element) nodes.item(i2);
                                            NodeList name = element.getElementsByTagName("PracticaId");
                                            Element line = (Element) name.item(0);
                                            System.out.println("PracticaId: " + getCharacterDataFromElement(line));
                                            salida = salida + "\t" + getCharacterDataFromElement(line);
                                            NodeList title = element.getElementsByTagName("PracticaObs");
                                            line = (Element) title.item(0);
                                            System.out.println(getCharacterDataFromElement(line));
                                            salida = salida + "      " + getCharacterDataFromElement(line) + "-\n";
                                        }
                                        JOptionPane.showMessageDialog(null, salida);

                                        ///anulo
                                        System.out.println("Anulacion boreal");
                                        int pos5 = respuestapractica.indexOf("<AutCod>");
                                        int pos6 = respuestapractica.indexOf("</AutCod>");
                                        num_orden = respuestapractica.substring(pos5 + 8, pos6);
                                        /////////////////////////////////////////////////////////////////////////////////
                                        mensajepractica = "<Boreal><Mensaje><Canal>ID</Canal><SitioEmisor>" + emisor + "</SitioEmisor><Receptor><Nombre>BOREAL</Nombre><ID>222023</ID><Tipo>IIN</Tipo></Receptor><MsgTipo><Tipo>ZQA</Tipo><Evento>Z04</Evento><Estructura>ZQA_Z02</Estructura></MsgTipo></Mensaje><Seguridad><Usuario>cobitucws</Usuario><Clave>" + clave + "</Clave></Seguridad><Prestador><PrestadorId>" + cuit + "</PrestadorId><PrestadorTipoIdent>CU</PrestadorTipoIdent></Prestador><Autorizacion><AutCod></AutCod><AutEstadoId></AutEstadoId><AutObs></AutObs><AutCodAnulacion>" + num_orden + "</AutCodAnulacion></Autorizacion></Boreal>";
                                        System.out.println("Envio de mensaje boreal - Send Http GET request");
                                        System.out.println(mensajepractica);
                                        ///////////////////////////////////////////////////////////////////////////////////////////////
                                        ClienteBoreal.WsBorealExecute servicioAnulacion = new ClienteBoreal.WsBorealExecute();
                                        servicioAnulacion.setIngresoxml(mensajepractica);
                                        respuestapractica = execute(servicioAnulacion).getEgresoxml();
                                        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
                                        System.out.println("Respuesta boreal - Get Http GET request");
                                        System.out.println(respuestapractica);
                                        ////////////////////////////////////////////////////////////////////////////////////////////////
                                        int posI = respuestapractica.indexOf("<AutEstadoId>");
                                        int posF = respuestapractica.indexOf("</AutEstadoId>");
                                        /// JOptionPane.showMessageDialog(null, respuestapractica.substring(pos + 13, pos2));
                                        if (respuestapractica.substring(posI + 13, posF).equals("B000")) {// 
                                            int posI2 = respuestapractica.indexOf("<AutCod>");
                                            int posF2 = respuestapractica.indexOf("</AutCod>");
                                            num_orden = respuestapractica.substring(posI2 + 8, posF2);
                                            cursor2();
                                            bandera_boreal = 0;
                                            JOptionPane.showMessageDialog(null, "La orden no fue validada");
                                            habilitado = "ERROR";
                                        } else {
                                            cursor2();
                                            JOptionPane.showMessageDialog(null, "Error al intentar anular orden numero " + num_orden + " informar a CBT");
                                            JOptionPane.showMessageDialog(null, "IMPORTANTE: TOME NOTA del numero " + num_orden);
                                            borrartabla();
                                            bandera_boreal = 0;
                                        }
                                    } catch (Exception e) {
                                        cursor2();
                                        bandera_boreal = 0;
                                        JOptionPane.showMessageDialog(null, e);
                                    }
                                } else {
                                    try {
                                        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                                        InputSource is = new InputSource();
                                        is.setCharacterStream(new StringReader(respuestapractica));
                                        Document doc = db.parse(is);
                                        NodeList nodes = doc.getElementsByTagName("Practica");

                                        String salida = "";
                                        for (int i2 = 0; i2 < nodes.getLength(); i2++) {
                                            Element element = (Element) nodes.item(i2);
                                            NodeList name = element.getElementsByTagName("PracticaId");
                                            Element line = (Element) name.item(0);
                                            System.out.println("PracticaId: " + getCharacterDataFromElement(line));
                                            salida = salida + "\t" + getCharacterDataFromElement(line);
                                            NodeList title = element.getElementsByTagName("PracticaObs");
                                            line = (Element) title.item(0);
                                            System.out.println(getCharacterDataFromElement(line));
                                            salida = salida + "      " + getCharacterDataFromElement(line) + "-\n";
                                        }
                                        JOptionPane.showMessageDialog(null, salida);

                                        ///anulo
                                        System.out.println("Anulacion boreal");
                                        int pos5 = respuestapractica.indexOf("<AutCod>");
                                        int pos6 = respuestapractica.indexOf("</AutCod>");
                                        num_orden = respuestapractica.substring(pos5 + 8, pos6);
                                        /////////////////////////////////////////////////////////////////////////////////
                                        mensajepractica = "<Boreal><Mensaje><Canal>ID</Canal><SitioEmisor>" + emisor + "</SitioEmisor><Receptor><Nombre>BOREAL</Nombre><ID>222023</ID><Tipo>IIN</Tipo></Receptor><MsgTipo><Tipo>ZQA</Tipo><Evento>Z04</Evento><Estructura>ZQA_Z02</Estructura></MsgTipo></Mensaje><Seguridad><Usuario>cobitucws</Usuario><Clave>" + clave + "</Clave></Seguridad><Prestador><PrestadorId>" + cuit + "</PrestadorId><PrestadorTipoIdent>CU</PrestadorTipoIdent></Prestador><Autorizacion><AutCod></AutCod><AutEstadoId></AutEstadoId><AutObs></AutObs><AutCodAnulacion>" + num_orden + "</AutCodAnulacion></Autorizacion></Boreal>";
                                        System.out.println("Envio de mensaje boreal - Send Http GET request");
                                        System.out.println(mensajepractica);
                                        ///////////////////////////////////////////////////////////////////////////////////////////////
                                        ClienteBoreal.WsBorealExecute servicioAnulacion = new ClienteBoreal.WsBorealExecute();
                                        servicioAnulacion.setIngresoxml(mensajepractica);
                                        respuestapractica = execute(servicioAnulacion).getEgresoxml();
                                        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
                                        System.out.println("Respuesta boreal - Get Http GET request");
                                        System.out.println(respuestapractica);
                                        ////////////////////////////////////////////////////////////////////////////////////////////////
                                        int posI = respuestapractica.indexOf("<AutEstadoId>");
                                        int posF = respuestapractica.indexOf("</AutEstadoId>");
                                        /// JOptionPane.showMessageDialog(null, respuestapractica.substring(pos + 13, pos2));
                                        if (respuestapractica.substring(posI + 13, posF).equals("B000")) {
                                            int posI2 = respuestapractica.indexOf("<AutCod>");
                                            int posF2 = respuestapractica.indexOf("</AutCod>");
                                            num_orden = respuestapractica.substring(posI2 + 8, posF2);
                                            cursor2();
                                            bandera_boreal = 0;
                                            JOptionPane.showMessageDialog(null, "La orden no fue validada");
                                            habilitado = "ERROR";
                                        } else {
                                            cursor2();
                                            JOptionPane.showMessageDialog(null, "Error al intentar anular orden numero " + num_orden + " informar a CBT");
                                            JOptionPane.showMessageDialog(null, "IMPORTANTE: TOME NOTA del numero " + num_orden);
                                            borrartabla();
                                            bandera_boreal = 0;
                                        }
                                    } catch (Exception e) {
                                        cursor2();
                                        bandera_boreal = 0;
                                        JOptionPane.showMessageDialog(null, "Error al intentar Anular orden del servidor de Boreal");
                                        JOptionPane.showMessageDialog(null, e);
                                    }
                                }

                            }
                        } else {
                            cursor2();
                            ///  JOptionPane.showMessageDialog(null, "No hay practicas en la tabla...");
                        }
                    }
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if (obra.equals("9000 - ASOCIACION MUTUAL SANCOR")) {
                        plan_ss = "";
                        coseguro_ss = "";
                        cursor();
                        Contraseña_Boreal contraseña2 = new Contraseña_Boreal();
                        String clave2 = contraseña2.Boreal_Contraseña().substring(8, 16);
                        //System.out.println(contraseña2.Boreal_Contraseña());
                        //System.out.println(clave2);
                        short cantidad = 1;
                        //////////////////////////////////////////////////////////
                        ClienteSancor.PAWESSAV2AUTORIZACION servicio = new ClienteSancor.PAWESSAV2AUTORIZACION();
                        ClienteSancor.Prestaciones P1 = new ClienteSancor.Prestaciones();
                        ClienteSancor.ArrayOfPrestacionesItem i1 = new ClienteSancor.ArrayOfPrestacionesItem();
                        ClienteSancor.PrestacionesItem item;
                        if (tablapracticas.getRowCount() != 0) {
                            int n2, i = 0;
                            String cod_practca, practicas = "", id_practicas = "";
                            n2 = tablapracticas.getRowCount();
                            if (n2 != 0) {
                                while (i < n2) {
                                    plan_ss = plan_ss + "00";
                                    coseguro_ss = coseguro_ss + "00000.0";
                                    id_practicas = id_practicas + String.valueOf(tablapracticas.getValueAt(i, 5).toString());
                                    practicas = practicas + String.valueOf(tablapracticas.getValueAt(i, 1).toString()).substring(0, 6);
                                    cod_practca = tablapracticas.getValueAt(i, 1).toString();
                                    item = new ClienteSancor.PrestacionesItem();
                                    item.setPrestacion(cod_practca);
                                    item.setCantidad(cantidad);
                                    ///item.setFormulario(1);
                                    item.setTipoNomenclador("NU");
                                    i1.getPrestacionesItem().add(item);
                                    P1.setPrestacionesItem(i1);
                                    servicio.setPrestaciones(P1);
                                    i++;
                                }
                            }
                            ///////////////////////////////////////////////////////////////////////
                            long efector = Long.valueOf(cuit);
                            servicio.setModo("P");
                            servicio.setNroorden(Integer.valueOf(clave2));
                            servicio.setEntidad(8999);
                            servicio.setTiponroefector("CU");
                            servicio.setNroefector(efector);
                            servicio.setFormaidafiliado("AS");
                            servicio.setAfiliado(Integer.valueOf(txtnumafiliado.getText()));
                            servicio.setMatriculaprescribiente(Integer.valueOf(txtmatricula.getText()));
                            servicio.setUsuario("WSRVSSA");
                            servicio.setClave("15WSSA08");

                            PAWESSAV2AUTORIZACIONResponse respuesta_sancor = autorizacion(servicio);
                            if (respuesta_sancor.getDescripcionrespuesta().equals("AUTORIZADO")) {
                                estado_orden = 1;
                                num_orden = String.valueOf(respuesta_sancor.getNroautorizacion());
                                txtnumorden.setText(num_orden);
                                /////////////////////grabo en servidor nuestro///////////////////////////////////////////////////////////////////////////
                                validar_orden sancor = new validar_orden();
                                System.out.println(practicas);
                                respuesta = sancor.valida(
                                        Integer.valueOf(txtaño.getText() + txtmes.getText()),
                                        txtnombreafiliado.getText(),
                                        txtdocumento.getText(),
                                        txtnumafiliado.getText(),
                                        Integer.valueOf(txtmatricula.getText()),
                                        num_orden,
                                        fecha,
                                        Double.valueOf(txttotal1.getText()),
                                        fecha,
                                        hora,
                                        ip2,
                                        id_obra_social,
                                        id_usuario,
                                        n2,
                                        practicas,
                                        Double.valueOf(txtcoseguro.getText()),
                                        fecha,
                                        tipo_orden,
                                        observacion,
                                        plan_ss,
                                        coseguro_ss,
                                        estado_orden,
                                        fechaDate);
                                if (respuesta == 0) {//en el caso se q no se grabe en nuestro servidor se anula del wsdl
                                    System.out.println("Anulacion sancor");
                                    ClienteSancor.PAWESSAV2ANULACION servicioanulacion = new ClienteSancor.PAWESSAV2ANULACION();
                                    servicioanulacion.setModo("P");
                                    servicioanulacion.setEntidad(8999);
                                    servicioanulacion.setNroautorizacion(Integer.valueOf(num_orden));
                                    servicioanulacion.setUsuario("WSRVSSA");
                                    servicioanulacion.setClave("15WSSA08");
                                    PAWESSAV2ANULACIONResponse anulacion_sancor = anulacion(servicioanulacion);
                                    if (anulacion_sancor.getCodigorespuesta() == 35) {
                                        num_orden = String.valueOf(anulacion_sancor.getNroordenrta());
                                        cursor2();
                                        bandera_sancor = 0;
                                        JOptionPane.showMessageDialog(null, "La orden no fue validada");
                                    } else {
                                        cursor2();
                                        bandera_sancor = 0;
                                        JOptionPane.showMessageDialog(null, anulacion(servicioanulacion).getDescripcionrespuesta());
                                    }
                                } else {
                                    cursor2();
                                    bandera_sancor = 1;
                                    JOptionPane.showMessageDialog(null, "Nro. Transaccion: " + num_orden);
                                    borrartabla();
                                }
                            } else {
                                estado_orden = 0;
                                String mensaje = "";
                                for (int j = 0; j < n2; j++) {
                                    mensaje = mensaje + "Error: " + respuesta_sancor.getCodigorespuesta() + "---" + respuesta_sancor.getPrestacionesrtav2().getPrestacionesRtaPrestacionesRtaItem().getPrestacionesRtaItem().get(j).getPrestacion() + "---" + respuesta_sancor.getPrestacionesrtav2().getPrestacionesRtaPrestacionesRtaItem().getPrestacionesRtaItem().get(j).getDescripcionErrorPrest() + "\n";
                                }
                                System.out.println(mensaje);
                                observacion = mensaje;
                                if (observacion.length() > 500) {
                                    observacion = observacion.substring(0, 500);
                                }
                                /////////////////////grabo en servidor nuestro///////////////////////////////////////////////////////////////////////////
                                validar_orden sancor = new validar_orden();
                                System.out.println(practicas);
                                respuesta = sancor.valida(
                                        Integer.valueOf(txtaño.getText() + txtmes.getText()),
                                        txtnombreafiliado.getText(),
                                        txtdocumento.getText(),
                                        txtnumafiliado.getText(),
                                        Integer.valueOf(txtmatricula.getText()),
                                        "0",
                                        fecha,
                                        Double.valueOf(txttotal1.getText()),
                                        fecha,
                                        hora,
                                        ip2,
                                        id_obra_social,
                                        id_usuario,
                                        n2,
                                        practicas,
                                        Double.valueOf(txtcoseguro.getText()),
                                        fecha,
                                        tipo_orden,
                                        observacion,
                                        plan_ss,
                                        coseguro_ss,
                                        estado_orden,
                                        fechaDate);
                                if (respuesta == 0) {//en el caso se q no se grabe en nuestro servidor se anula del wsdl
                                    cursor2();
                                    JOptionPane.showMessageDialog(null, "La orden no pudo ser cargada");
                                    bandera_sancor = 0;

                                } else {
                                    cursor2();
                                    bandera_sancor = 0;
                                    JOptionPane.showMessageDialog(null, "La orden no pudo ser cargada en el servidor de Sancor");
                                    JOptionPane.showMessageDialog(null, respuesta_sancor.getDescripcionrespuesta() + "\n" + mensaje);
                                    borrartabla();
                                }
                            }

                        } else {
                            cursor2();
                            //  JOptionPane.showMessageDialog(null, "No hay practicas en la tabla...");
                        }
                    }
                    /////////////1805-subsidio on line//////////////////////
                    if (obra.equals("1805 - SUBSIDIO DE SALUD - ONLINE")) {
                        plan_ss = "";
                        coseguro_ss = "";
                        ConexionMariaDB cc3 = new ConexionMariaDB();
                        Connection cnn = cc3.Conectar();
                        cursor();
                        int cuentaPracticas = 0;
                        DecimalFormat df = new DecimalFormat("0.00");
                        LinkedList<camposss> Resultados = new LinkedList<camposss>();
                        Resultados.clear();
                        ClienteIPSST3.OrdenAutorizarCHEQUEARFACTIBILIDAD servicio = new ClienteIPSST3.OrdenAutorizarCHEQUEARFACTIBILIDAD();
                        servicio.setAficuil(txtnumafiliado.getText());
                        servicio.setOrdentipo("B");
                        servicio.setPrescriptor(Integer.valueOf(txtmatricula.getText()));
                        servicio.setPrestador(Integer.valueOf(matricula_colegiado));
                        //servicio.setPrestador(1177);
                        servicio.setUsuario(2);//
                        servicio.setToken("iq12Ii35o");
                        /////////////////////////////////
                        ClienteIPSST3.ArrayOfWSPractica item = new ClienteIPSST3.ArrayOfWSPractica();
                        ClienteIPSST3.WSPractica practica;
                        //////////////////////////////////////////////
                        int n2, iv = 0, iv1 = 0, iv2 = 0, iv3 = 0;
                        int i = 0;
                        String cod_practca, cod_practica, cadena_practicas = "", id_practicas = "";
                        String[] practicas = new String[100];
                        n2 = tablapracticas.getRowCount();
                        /////////////////////////////////////////////////
                        if (tablapracticas.getRowCount() != 0) {
                            n2 = tablapracticas.getRowCount();
                            i = 0;
                            if (n2 != 0) {
                                try {
                                    Statement st = cnn.createStatement();
                                    while (iv < n2) {
                                        id_practicas = id_practicas + String.valueOf(tablapracticas.getValueAt(i, 5).toString());
                                        cadena_practicas = cadena_practicas + String.valueOf(tablapracticas.getValueAt(iv, 1).toString()).substring(0, 6);
                                        cod_practca = tablapracticas.getValueAt(iv, 1).toString().substring(0, 6);
                                        String consultaExcluida = "SELECT practica_2, practica_1 from practicas_excluidas where practica_1 =" + cod_practca;
                                        ResultSet rs = st.executeQuery(consultaExcluida);
                                        while (rs.next()) {
                                            iv1 = 0;
                                            cod_practica = rs.getString(1);
                                            while (iv1 < n2) {
                                                if (iv1 != iv) {
                                                    cod_practca = tablapracticas.getValueAt(iv1, 1).toString().trim();
                                                    if (cod_practca.equals(cod_practica)) {
                                                        practicas[iv3] = "El Codigo " + rs.getString(2) + " No puede ser cargado con " + cod_practca;
                                                        cuentaPracticas = 1;
                                                        iv3++;
                                                    }
                                                }
                                                iv1++;
                                            }
                                        }
                                        iv++;
                                    }
                                    cnn.close();
                                } catch (SQLException e) {
                                    cursor2();
                                    JOptionPane.showMessageDialog(null, e);
                                }
                            }
                        } else {
                            cursor2();
                            // JOptionPane.showMessageDialog(null, "No hay practicas en la tabla...");
                        }
                        if (cuentaPracticas == 0) {
                            iv = 0;
                            while (iv < n2) {
                                cod_practca = tablapracticas.getValueAt(iv, 1).toString().substring(0, 6);
                                practica = new ClienteIPSST3.WSPractica();
                                practica.setNomPrestacion(cod_practca);
                                practica.setCantidad(1);
                                item.getWSPractica().add(practica);
                                servicio.setPracticas(item);
                                iv++;
                            }
                            OrdenAutorizarCHEQUEARFACTIBILIDADResponse respuesta_factibilidad_ss = chequearfactibilidad(servicio);
                            if (respuesta_factibilidad_ss.getFactibilidad() == 1) {
                                contadorPracticas = 0;
                                habilitado = "AUTORIZADO";
                                double coseguro2 = 0.0, precio = 0.0, subtotal = 0.0, total = 0.0;
                                String Salida = "";
                                if (tablapracticas.getRowCount() != 0) {
                                    i = 0;
                                    n2 = tablapracticas.getRowCount();
                                    if (n2 != 0) {
                                        while (i < n2) {
                                            coseguro2 = coseguro2 + respuesta_factibilidad_ss.getPracticas().getWSPractica().get(i).getCoseguro();
                                            Salida = Salida + "\n" + "Practica: " + respuesta_factibilidad_ss.getPracticas().getWSPractica().get(i).getNomPrestacion() + " Coseguro: $" + respuesta_factibilidad_ss.getPracticas().getWSPractica().get(i).getCoseguro();
                                            camposss tipo;
                                            subtotal = subtotal + Double.valueOf(tablapracticas.getValueAt(i, 3).toString());
                                            tipo = new camposss(respuesta_factibilidad_ss.getPracticas().getWSPractica().get(i).getNomPrestacion(), String.valueOf(respuesta_factibilidad_ss.getPracticas().getWSPractica().get(i).getCoseguro()), String.valueOf(tablapracticas.getValueAt(i, 2)), String.valueOf(tablapracticas.getValueAt(i, 3)));
                                            Resultados.add(tipo);
                                            i++;
                                        }
                                    }
                                } else {
                                    cursor2();
                                    JOptionPane.showMessageDialog(null, "No hay practicas en la tabla...");
                                }
                                num_orden = "";
                                txtnumorden.setText(num_orden);
                                cursor2();
                                int opcion = JOptionPane.showConfirmDialog(this, "          AUTORIZADO " + Salida + "\n      COSEGURO TOTAL: $" + coseguro2, "Subsidio Impresíon", JOptionPane.OK_CANCEL_OPTION);
                                if (opcion == 0) {
                                    cursor();
                                    ClienteIPSST3.OrdenAutorizarEMITIR servicio2 = new ClienteIPSST3.OrdenAutorizarEMITIR();
                                    servicio2.setAficuil(txtnumafiliado.getText());
                                    servicio2.setOrdentipo("B");
                                    servicio2.setUsuario(2);//
                                    servicio2.setToken("iq12Ii35o");
                                    servicio2.setPrescriptor(Integer.valueOf(txtmatricula.getText()));
                                    servicio2.setPrestador(Integer.valueOf(matricula_colegiado));
                                    /////////////////////////////////////////////////////////////////////////////
                                    ClienteIPSST3.ArrayOfWSPractica item2 = new ClienteIPSST3.ArrayOfWSPractica();
                                    ClienteIPSST3.WSPractica practica2;
                                    if (tablapracticas.getRowCount() != 0) {
                                        i = 0;
                                        n2 = tablapracticas.getRowCount();
                                        if (n2 != 0) {
                                            while (i < n2) {
                                                cod_practca = tablapracticas.getValueAt(i, 1).toString().substring(0, 6);
                                                practica2 = new ClienteIPSST3.WSPractica();
                                                practica2.setNomPrestacion(cod_practca);
                                                practica2.setCantidad(1);
                                                item2.getWSPractica().add(practica2);
                                                servicio2.setPracticas(item2);
                                                i++;
                                            }
                                        }
                                    } else {
                                        cursor2();
                                        JOptionPane.showMessageDialog(null, "No hay practicas en la tabla...");
                                    }
                                    System.out.println("-------------------------------------");
                                    OrdenAutorizarEMITIRResponse Respuesta_emite = emitir(servicio2);
                                    if (Respuesta_emite.getMotivorechazo().toString().equals("")) {

                                        num_orden = String.valueOf(Respuesta_emite.getOrdnumero());
                                        //////////////////cargar coseguro y planes///////////////////////////////////////////////////////////////////////////////////
                                        i = 0;
                                        n2 = Respuesta_emite.getPracticas().getWSPractica().size();
                                        while (i < n2) {
                                            plan_ss = plan_ss + completarceros(String.valueOf(Respuesta_emite.getPracticas().getWSPractica().get(i).getPlanCobertura()), 2);
                                            coseguro_ss = coseguro_ss + completarceros(String.valueOf(Respuesta_emite.getPracticas().getWSPractica().get(i).getCoseguro()), 7);
                                            i++;
                                        }
                                        estado_orden = 1;
                                        /////////////////////grabo en servidor nuestro///////////////////////////////////////////////////////////////////////////
                                        validar_orden ss_1805 = new validar_orden();
                                        respuesta = ss_1805.valida(
                                                Integer.valueOf(txtaño.getText() + txtmes.getText()),
                                                txtnombreafiliado.getText(),
                                                txtdocumento.getText(),
                                                txtnumafiliado.getText(),
                                                Integer.valueOf(txtmatricula.getText()),
                                                num_orden,
                                                fecha,
                                                Double.valueOf(txttotal1.getText()),
                                                fecha,
                                                hora,
                                                ip2,
                                                id_obra_social,
                                                id_usuario,
                                                n2,
                                                cadena_practicas,
                                                Double.valueOf(txtcoseguro.getText()),
                                                fecha,
                                                tipo_orden,
                                                observacion,
                                                plan_ss,
                                                coseguro_ss,
                                                estado_orden,
                                                fechaDate);
                                        if (respuesta == 0) {//en el caso se q no se grabe en nuestro servidor se anula del wsdl
                                            System.out.println("Anulacion ss 1805");
                                            ClienteIPSST5.OrdenDevolverExecute servicio_anulacion = new ClienteIPSST5.OrdenDevolverExecute();
                                            servicio_anulacion.setAficuil(txtnumafiliado.getText());
                                            servicio_anulacion.setOrdtipo("B");
                                            servicio_anulacion.setPrestador(Integer.valueOf(Login.matricula_colegiado));
                                            servicio_anulacion.setUsuario(2);//
                                            servicio_anulacion.setToken("iq12Ii35o");
                                            servicio_anulacion.setOrdnumero(Integer.valueOf(num_orden));
                                            OrdenDevolverExecuteResponse respuesta_devolucion = execute_2(servicio_anulacion);
                                            if (respuesta_devolucion.getEstadoactual().equals("DEVUELTA")) {
                                                cursor2();
                                                bandera_subsidio = 0;
                                                JOptionPane.showMessageDialog(null, "La orden no fue validada");
                                            } else {
                                                cursor2();
                                                JOptionPane.showMessageDialog(null, respuesta_devolucion.getMotivo());
                                                try {
                                                    JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Reporte_subsidio.jasper"));
                                                    ///////////////////////////////////////////////////C:\Users\Lucas\Documents\NetBeansProjects\colegio bioquimicos\src\Reportes
                                                    Map parametros = new HashMap();
                                                    parametros.put("fecha", fecha2);
                                                    parametros.put("nombre", txtnombreafiliado.getText());
                                                    parametros.put("numero", txtnumafiliado.getText());
                                                    parametros.put("bioquimico", Login.nombre_colegiado + " - " + Login.matricula_colegiado);
                                                    parametros.put("mensaje", respuesta_devolucion.getMotivo());
                                                    JasperPrint jPrint2 = JasperFillManager.fillReport(report, parametros, new JREmptyDataSource());
                                                    int opcionSS = JOptionPane.showConfirmDialog(this, "Desea Imprimir un comprobante?", "Subsidio Impresíon", JOptionPane.YES_NO_OPTION);
                                                    if (opcionSS == 0) {
                                                        JasperPrintManager.printReport(jPrint2, false);
                                                    }

                                                    ImagenPDF pdf = new ImagenPDF();
                                                    pdf.createPdf(jPrint2, "C:\\Descargas-CBT\\" + "Orden Rechazada de " + txtnombreafiliado.getText());
                                                    System.out.println(plan_ss);
                                                    System.out.println(coseguro_ss);
                                                } catch (Exception e) {
                                                    cursor2();
                                                    JOptionPane.showMessageDialog(null, e);
                                                }
                                                bandera_subsidio = 0;
                                                borrartabla();
                                            }
                                        } else {
                                            cursor2();
                                            JOptionPane.showMessageDialog(null, "Nro. Transaccion: " + Respuesta_emite.getOrdnumero());
                                            ////////////////////////////////////////////////////////////////////////////////////////////
                                            try {
                                                JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Comprobante_subsidio.jasper"));
                                                ///////////////////////////////////////////////////C:\Users\Lucas\Documents\NetBeansProjects\colegio bioquimicos\src\Reportes
                                                Map parametros = new HashMap();
                                                parametros.put("numero_autorizacion", num_orden);
                                                parametros.put("fecha", fecha2);
                                                parametros.put("nombre", txtnombreafiliado.getText());
                                                parametros.put("numero", txtnumafiliado.getText());
                                                parametros.put("Medico", txtmatricula.getText());
                                                parametros.put("bioquimico", Login.nombre_colegiado + " - " + Login.matricula_colegiado);
                                                parametros.put("total", df.format(coseguro2));
                                                parametros.put("subtotal", df.format(subtotal));
                                                JasperPrint jPrint = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(Resultados));
                                                ImagenPDF pdf = new ImagenPDF();
                                                pdf.createPdf(jPrint, "C:\\Descargas-CBT\\" + txtnombreafiliado.getText() + " - " + num_orden);
                                                JasperPrint jPrint2 = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(Resultados));
                                                int opcionSS = JOptionPane.showConfirmDialog(this, "Desea Imprimir un comprobante?", "Subsidio Impresíon", JOptionPane.YES_NO_OPTION);
                                                if (opcionSS == 0) {
                                                    JasperPrintManager.printReport(jPrint2, false);
                                                }
                                                System.out.println(plan_ss);
                                                System.out.println(coseguro_ss);
                                            } catch (Exception e) {
                                                cursor2();
                                                JOptionPane.showMessageDialog(null, e);
                                            }
                                            bandera_subsidio = 1;
                                            borrartabla();
                                        }
                                        ///////////////////////////////////////////////////////////////////////////////////////////////////
                                    } else {
                                        bandera_subsidio = 0;
                                        cursor2();
                                        JOptionPane.showMessageDialog(null, Respuesta_emite.getMotivorechazo());
                                        try {
                                            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Reporte_subsidio.jasper"));
                                            ///////////////////////////////////////////////////C:\Users\Lucas\Documents\NetBeansProjects\colegio bioquimicos\src\Reportes
                                            Map parametros = new HashMap();
                                            parametros.put("fecha", fecha2);
                                            parametros.put("nombre", txtnombreafiliado.getText());
                                            parametros.put("numero", txtnumafiliado.getText());
                                            parametros.put("bioquimico", Login.nombre_colegiado + " - " + Login.matricula_colegiado);
                                            parametros.put("mensaje", Respuesta_emite.getMotivorechazo());
                                            JasperPrint jPrint2 = JasperFillManager.fillReport(report, parametros, new JREmptyDataSource());
                                            int opcionSS = JOptionPane.showConfirmDialog(this, "Desea Imprimir un comprobante?", "Subsidio Impresíon", JOptionPane.YES_NO_OPTION);
                                            if (opcionSS == 0) {
                                                JasperPrintManager.printReport(jPrint2, false);
                                            }
                                            ImagenPDF pdf = new ImagenPDF();
                                            pdf.createPdf(jPrint2, "C:\\Descargas-CBT\\" + "Orden Rechazada de " + txtnombreafiliado.getText());
                                            System.out.println(plan_ss);
                                            System.out.println(coseguro_ss);
                                        } catch (Exception e) {
                                            cursor2();
                                            JOptionPane.showMessageDialog(null, e);
                                        }
                                        borrartabla();
                                    }
                                } else {
                                    bandera_subsidio = 0;
                                    cursor2();
                                }
                            } else {
                                bandera_subsidio = 0;
                                cursor2();
                                if (respuesta_factibilidad_ss.getFactibilidad() == 0) {
                                    habilitado = "ERROR";
                                    String Salida = "";
                                    if (tablapracticas.getRowCount() != 0) {
                                        i = 0;
                                        n2 = tablapracticas.getRowCount();
                                        if (n2 != 0) {
                                            while (i < n2) {
                                                Salida = Salida + "\n" + "Practica: " + respuesta_factibilidad_ss.getPracticas().getWSPractica().get(i).getNomPrestacion() + " Motivo: " + respuesta_factibilidad_ss.getPracticas().getWSPractica().get(i).getMotivo();
                                                i++;
                                            }
                                        }
                                    } else {
                                        cursor2();
                                        //JOptionPane.showMessageDialog(null, "No hay practicas en la tabla...");
                                    }
                                    JOptionPane.showMessageDialog(null, respuesta_factibilidad_ss.getMotivorechazo() + Salida);
                                    try {
                                        JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Reporte_subsidio.jasper"));
                                        ///////////////////////////////////////////////////C:\Users\Lucas\Documents\NetBeansProjects\colegio bioquimicos\src\Reportes
                                        Map parametros = new HashMap();
                                        parametros.put("fecha", fecha2);
                                        parametros.put("nombre", txtnombreafiliado.getText());
                                        parametros.put("numero", txtnumafiliado.getText());
                                        parametros.put("bioquimico", Login.nombre_colegiado + " - " + Login.matricula_colegiado);
                                        parametros.put("mensaje", respuesta_factibilidad_ss.getMotivorechazo() + Salida);
                                        JasperPrint jPrint2 = JasperFillManager.fillReport(report, parametros, new JREmptyDataSource());
                                        int opcionSS = JOptionPane.showConfirmDialog(this, "Desea Imprimir un comprobante?", "Subsidio Impresíon", JOptionPane.YES_NO_OPTION);
                                        if (opcionSS == 0) {
                                            JasperPrintManager.printReport(jPrint2, false);
                                        }
                                        ImagenPDF pdf = new ImagenPDF();
                                        pdf.createPdf(jPrint2, "C:\\Descargas-CBT\\" + "Orden Rechazada de " + txtnombreafiliado.getText());
                                        System.out.println(plan_ss);
                                        System.out.println(coseguro_ss);
                                    } catch (Exception e) {
                                        cursor2();
                                        JOptionPane.showMessageDialog(null, e);
                                    }
                                    bandera_subsidio = 0;
                                }
                                borrartabla();
                            }
                        } else {
                            cursor2();
                            bandera_subsidio = 0;
                            String Texto = "";
                            while (iv2 < iv3) {
                                Texto = Texto + " " + practicas[iv2] + "\n";
                                iv2++;
                            }
                            JOptionPane.showMessageDialog(null, "Hay prácticas que ya están contenidas en un módulo" + "\n" + Texto);
                            txtpractica.setText("");
                        }
                    }//pendiente 

                    if (obra.equals("1806 - SUBSIDIO DE SALUD - AUTORIZACION - ONLINE")) {
                        plan_ss = "";
                        coseguro_ss = "";
                        System.out.println("1806 - SUBSIDIO DE SALUD - AUTORIZACION - ONLINE");
                        bandera_subsidio = 0;
                        borrartabla();
                        DefaultTableModel temp = (DefaultTableModel) tablapracticas.getModel();
                        int n;
                        ClienteIPSST4.OrdenValidarExecute servicio = new ClienteIPSST4.OrdenValidarExecute();
                        servicio.setAficuil(txtnumafiliado.getText());
                        try {
                            if (isNumeric(txtnumorden.getText())) {
                                servicio.setOrdnumero(Long.valueOf(txtnumorden.getText()));
                                servicio.setPrestador(Integer.valueOf(matricula_colegiado));
                                servicio.setUsuario(2);//
                                servicio.setToken("iq12Ii35o");
                                cursor();
                                ClienteIPSST4.OrdenValidarExecuteResponse respuesta_valida = execute_1(servicio);
                                System.out.println(respuesta_valida.getMotivo());
                                System.out.println(respuesta_valida.getOrdenvalida());
                                /////////////////////////////////
                                if (Integer.valueOf(respuesta_valida.getOrdenvalida()) == 1) {
                                    int n2, i = 0, cantidad_practica;
                                    String cod_practca, nombre_practica;
                                    n2 = respuesta_valida.getPracticas().getWSPracticaValidar().size();
                                    System.out.println("cantidad: " + n2);
                                    if (n2 != 0) {
                                        while (i < n2) {
                                            cod_practca = respuesta_valida.getPracticas().getWSPracticaValidar().get(i).getPractica();
                                            nombre_practica = respuesta_valida.getPracticas().getWSPracticaValidar().get(i).getDescripcion();
                                            plan_ss = plan_ss + completarceros(String.valueOf(respuesta_valida.getPracticas().getWSPracticaValidar().get(i).getPlan()), 2);
                                            cantidad_practica = respuesta_valida.getPracticas().getWSPracticaValidar().get(i).getCantidad();
                                            int j = 0;
                                            int fila = 0;
                                            n = tablapracticas.getRowCount();
                                            while (j < contadorj) {
                                                System.out.println("Practica" + cod_practca);
                                                if (cod_practca.equals(codfacpractica[j])) {
                                                    System.out.println("Practica- vector" + codfacpractica[j]);
                                                    if (n != 0) {
                                                        if (cantidad_practica > 1) {
                                                            int k = 0, contador = n;
                                                            while (k < cantidad_practica) {
                                                                fila = contador;
                                                                Object nuevo[] = {
                                                                    fila + 1, "", ""};
                                                                temp.addRow(nuevo);
                                                                tablapracticas.setValueAt(cod_practca, fila, 1);
                                                                tablapracticas.setValueAt(nombre_practica, fila, 2);
                                                                tablapracticas.setValueAt(preciopractica[j], fila, 3);
                                                                tablapracticas.setValueAt(codfacpractica[j], fila, 4);
                                                                tablapracticas.setValueAt(idpractica[j], fila, 5);
                                                                bandera_subsidio = 1;
                                                                k++;
                                                                contador++;
                                                            }
                                                        } else {
                                                            fila = n;
                                                            Object nuevo[] = {
                                                                fila + 1, "", ""};
                                                            temp.addRow(nuevo);
                                                            tablapracticas.setValueAt(cod_practca, fila, 1);
                                                            tablapracticas.setValueAt(nombre_practica, fila, 2);
                                                            tablapracticas.setValueAt(preciopractica[j], fila, 3);
                                                            tablapracticas.setValueAt(codfacpractica[j], fila, 4);
                                                            tablapracticas.setValueAt(idpractica[j], fila, 5);
                                                            bandera_subsidio = 1;

                                                        }
                                                    } else {
                                                        if (cantidad_practica > 1) {
                                                            int k = 0, contador = 0;
                                                            while (k < cantidad_practica) {
                                                                if (contador == 0) {
                                                                    Object nuevo[] = {
                                                                        "1", "", ""};
                                                                    temp.addRow(nuevo);
                                                                    tablapracticas.setValueAt(cod_practca, 0, 1);
                                                                    tablapracticas.setValueAt(nombre_practica, 0, 2);
                                                                    tablapracticas.setValueAt(preciopractica[j], 0, 3);
                                                                    tablapracticas.setValueAt(codfacpractica[j], fila, 4);
                                                                    tablapracticas.setValueAt(idpractica[j], fila, 5);
                                                                    bandera_subsidio = 1;
                                                                    contador = contador + 1;
                                                                } else {
                                                                    fila = contador;
                                                                    Object nuevo[] = {
                                                                        fila + 1, "", ""};
                                                                    temp.addRow(nuevo);
                                                                    tablapracticas.setValueAt(cod_practca, fila, 1);
                                                                    tablapracticas.setValueAt(nombre_practica, fila, 2);
                                                                    tablapracticas.setValueAt(preciopractica[j], fila, 3);
                                                                    tablapracticas.setValueAt(codfacpractica[j], fila, 4);
                                                                    tablapracticas.setValueAt(idpractica[j], fila, 5);
                                                                    bandera_subsidio = 1;
                                                                    contador = contador + 1;
                                                                }
                                                                k++;
                                                            }
                                                        } else {
                                                            Object nuevo[] = {
                                                                "1", "", ""};
                                                            temp.addRow(nuevo);
                                                            tablapracticas.setValueAt(cod_practca, 0, 1);
                                                            tablapracticas.setValueAt(nombre_practica, 0, 2);
                                                            tablapracticas.setValueAt(preciopractica[j], 0, 3);
                                                            tablapracticas.setValueAt(codfacpractica[j], 0, 4);
                                                            tablapracticas.setValueAt(idpractica[j], 0, 5);
                                                            bandera_subsidio = 1;
                                                        }

                                                    }
                                                    System.out.println(cod_practca);
                                                    break;
                                                }
                                                j++;
                                                tablapracticas.getColumnModel().getColumn(0).setPreferredWidth(10);
                                                tablapracticas.getColumnModel().getColumn(1).setPreferredWidth(10);
                                                tablapracticas.getColumnModel().getColumn(2).setPreferredWidth(300);
                                                tablapracticas.getColumnModel().getColumn(3).setPreferredWidth(10);
                                                ///////Ultima Fila///////
                                                tablapracticas.getColumnModel().getColumn(4).setMaxWidth(0);
                                                tablapracticas.getColumnModel().getColumn(4).setMinWidth(0);
                                                tablapracticas.getColumnModel().getColumn(4).setPreferredWidth(0);

                                                Rectangle r = tablapracticas.getCellRect(tablapracticas.getRowCount() - 1, 0, true);
                                                tablapracticas.scrollRectToVisible(r);
                                                tablapracticas.getSelectionModel().setSelectionInterval(tablapracticas.getRowCount() - 1, tablapracticas.getRowCount() - 1);
                                                //////////////////////////
                                            }
                                            i++;
                                        }
                                        cargartotalpracticas();
                                        cursor2();
                                    } else {
                                        cursor2();
                                        JOptionPane.showMessageDialog(null, "Las practicas no se encuentran disponibles en nuestro servidor.\n Aguarde 24Hs para realizar la carga");
                                        System.out.println("ANULO");
                                        ////////////////ANULACION//////////////////////////////////////////
                                        ClienteIPSST6.OrdenValidadaAnularExecute ANULACION = new ClienteIPSST6.OrdenValidadaAnularExecute();
                                        ANULACION.setAficuil(txtnumafiliado.getText());
                                        ANULACION.setPrestador(Login.matricula_colegiado);
                                        ANULACION.setUsuario(2);//
                                        ANULACION.setToken("iq12Ii35o");
                                        ANULACION.setOrdnumero(Integer.valueOf(txtnumorden.getText()));

                                        OrdenValidadaAnularExecuteResponse respuesta_devuelve = execute_3(ANULACION);
                                        if (respuesta_devuelve.getOrdenanuladada() == 1) {
                                            habilitado = "AUTORIZADO";
                                            cursor2();
                                            System.out.println("Orden anulala del servidor de Subsidio");
                                        } else {
                                            habilitado = "ERROR";
                                            cursor2();
                                            JOptionPane.showMessageDialog(null, respuesta_devuelve.getMotivo());
                                            try {
                                                JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Reporte_subsidio.jasper"));
                                                //////////////////////////////////////////////////
                                                Map parametros = new HashMap();
                                                parametros.put("fecha", fecha2);
                                                parametros.put("nombre", txtnombreafiliado.getText());
                                                parametros.put("numero", txtnumafiliado.getText());
                                                parametros.put("bioquimico", Login.nombre_colegiado + " - " + Login.matricula_colegiado);
                                                parametros.put("mensaje", respuesta_devuelve.getMotivo());
                                                JasperPrint jPrint2 = JasperFillManager.fillReport(report, parametros, new JREmptyDataSource());
                                                JasperPrintManager.printReport(jPrint2, false);
                                                ImagenPDF pdf = new ImagenPDF();
                                                pdf.createPdf(jPrint2, "C:\\Descargas-CBT\\" + "Orden Rechazada de " + txtnombreafiliado.getText());
                                                System.out.println(plan_ss);
                                                System.out.println(coseguro_ss);
                                            } catch (Exception e) {
                                                cursor2();
                                                JOptionPane.showMessageDialog(null, e);
                                            }
                                        }
                                        System.out.println("8------");
                                    }
                                    if (bandera_subsidio == 1) {
                                        estado_orden = 1;
                                        ////////////////////////////////////////////////////////////////////////////////////////////
                                        String Salida = "Practicas Autorizadas", cadena_practicas = "", id_practicas = "";
                                        if (n2 != 0) {
                                            i = 0;
                                            n2 = tablapracticas.getRowCount();
                                            while (i < n2) {
                                                coseguro_ss = coseguro_ss + "00000.0";
                                                id_practicas = id_practicas + String.valueOf(tablapracticas.getValueAt(i, 5).toString());
                                                cadena_practicas = cadena_practicas + tablapracticas.getValueAt(i, 1).toString();
                                                Salida = Salida + "\n" + (i + 1) + " - Practica: " + tablapracticas.getValueAt(i, 1).toString() + " - " + tablapracticas.getValueAt(i, 2).toString();
                                                i++;
                                            }
                                            cursor2();
                                            JOptionPane.showMessageDialog(null, Salida);
                                            /////////////////////grabo en servidor nuestro///////////////////////////////////////////////////////////////////////////
                                            cursor();
                                            validar_orden ss_1806 = new validar_orden();
                                            respuesta = ss_1806.valida(
                                                    Integer.valueOf(txtaño.getText() + txtmes.getText()),
                                                    txtnombreafiliado.getText(),
                                                    txtdocumento.getText(),
                                                    txtnumafiliado.getText(),
                                                    Integer.valueOf(txtmatricula.getText()),
                                                    txtnumorden.getText(),
                                                    txtfecha.getText(),
                                                    Double.valueOf(txttotal1.getText()),
                                                    fecha,
                                                    hora,
                                                    ip2,
                                                    id_obra_social,
                                                    id_usuario,
                                                    n2,
                                                    cadena_practicas,
                                                    Double.valueOf(txtcoseguro.getText()),
                                                    fecha,
                                                    tipo_orden,
                                                    observacion,
                                                    plan_ss,
                                                    coseguro_ss,
                                                    estado_orden,
                                                    fechaDate);
                                            if (respuesta == 0) {//en el caso se q no se grabe en nuestro servidor se anula del wsdl
                                                System.out.println("Anulacion ss 1806");
                                                ClienteIPSST6.OrdenValidadaAnularExecute servicio_anulacion = new ClienteIPSST6.OrdenValidadaAnularExecute();
                                                servicio_anulacion.setAficuil(txtnumafiliado.getText());
                                                servicio_anulacion.setPrestador(String.valueOf(id_usuario));
                                                servicio_anulacion.setUsuario(2);//
                                                servicio_anulacion.setToken("iq12Ii35o");
                                                servicio_anulacion.setOrdnumero(Integer.valueOf(txtnumorden.getText()));
                                                bandera_subsidio = 0;
                                                OrdenValidadaAnularExecuteResponse respuesta_devuelve = execute_3(servicio_anulacion);
                                                if (respuesta_devuelve.getOrdenanuladada() == 1) {
                                                    bandera_subsidio = 0;
                                                    cursor2();
                                                    JOptionPane.showMessageDialog(null, "La orden no fue validada");
                                                } else {
                                                    cursor2();
                                                    JOptionPane.showMessageDialog(null, respuesta_devuelve.getMotivo());
                                                    bandera_subsidio = 0;
                                                }
                                            } else {
                                                cursor2();
                                                bandera_subsidio = 1;
                                            }
                                        } else {
                                            cursor2();
                                            JOptionPane.showMessageDialog(null, "Las practicas no se encuentran disponibles en nuestro servidor.\n Aguarde 24Hs para realizar la carga");
                                        }
                                    }
                                } else {
                                    cursor2();
                                    JOptionPane.showMessageDialog(null, respuesta_valida.getMotivo());
                                }
                            } else {
                                cursor2();
                                JOptionPane.showMessageDialog(null, "Número de orden inválido");
                                txtnumorden.requestFocus();
                                txtnumorden.selectAll();
                            }
                        } catch (NumberFormatException e) {
                            cursor2();
                            JOptionPane.showMessageDialog(null, "Número de orden inválido");
                            txtnumorden.requestFocus();
                            txtnumorden.selectAll();
                        }
                    }
                    ////////////////////////MEDIFE
                    if (obra.equals("512 - MEDIFE - ONLINE OBLIGATORIO PRE PAGA C.M.C.  S.A.")
                            || obra.equals("513 - MEDIFE - ONLINE VOLUNTARIO PRE PAGA C.M.C.  S.A.")) {
                        cursor();
                        num_orden = "";
                        TripleDes tpDatos = new TripleDes();
                        /////////////////////////////////////////////////////////////////////////////////
                        plan_ss = "";
                        coseguro_ss = "";
                        cursor();
                        short cantidad = 1;
                        //////////////////////////////////////////////////////////
                        if (tablapracticas.getRowCount() != 0) {
                            int n2, i = 0;
                            String cod_practca, practicas = "", cadena_PR1 = "", id_practicas = "";
                            n2 = tablapracticas.getRowCount();
                            if (n2 != 0) {
                                while (i < n2) {
                                    plan_ss = plan_ss + "00";
                                    coseguro_ss = coseguro_ss + "00000.0";
                                    id_practicas = id_practicas + String.valueOf(tablapracticas.getValueAt(i, 5).toString());
                                    practicas = practicas + String.valueOf(tablapracticas.getValueAt(i, 1).toString()).substring(0, 6);
                                    cod_practca = completarceros(tablapracticas.getValueAt(i, 4).toString(), 6);
                                    cadena_PR1 = cadena_PR1
                                            + "PR1|" + (i + 1) + "||" + cod_practca + "^^3\r\n"
                                            + "AUT||||||||1\r\n"
                                            + "ZAU||||||0&$\r\n";
                                    i++;
                                }
                            }
                            ///////////////////////////////////////////////////////////////////////                        
                            String Mensaje = "MSH|^~\\&|TRIA0100M|TRIA00007526|MEDIFE|MEDIFE^222222^IIN|" + fechahora_medife + "||ZQA^Z02^ZQA_Z02|" + codigo_seguridad_medife + "|P|2.4|||NE|AL|ARG\r\n"
                                    + "PRD|PS^Prestador Solicitante||^^^T||||30522483881^CU|\r\n"
                                    + "PRD|EF^Efector||^^^T||||" + cuit + "^CU&M&C|\r\n"
                                    + "PID|||" + MedifeAfiliado.Codigo_afiliado + "^^^MEDIFE^HC^MEDIFE||UNKNOWN\r\n"
                                    + cadena_PR1
                                    + "PV1||O||P|||||||||||||||||||||||||||||||||||||||||||||||V";

                            String clave = "IA007526";
                            String usuario = "IA007526";
                            String tipo = "SI";
                            String llave = "1234567890123456ABCDEFGH";

                            String pszMsg = tpDatos.EncriptarStr(Mensaje, llave);

                            try { // Call Web Service Operation
                                WebServiceIA service = new WebServiceIA();
                                WebServiceIASoap port = service.getWebServiceIASoap();
                                // TODO initialize WS operation arguments here
                                String pszUser = tpDatos.EncriptarStr(usuario, llave);
                                String pszPwd = tpDatos.EncriptarStr(clave, llave);
                                String pszMsgType = tpDatos.EncriptarStr(tipo, llave);

                                // TODO process result here
                                String result = port.enviar(pszMsg, pszUser, pszPwd, pszMsgType);
                                System.out.println("Respuesta = " + result);
                                ///busco la respuesta
                                i = result.indexOf("ZAU");
                                int pipe = 0;
                                while (i < result.indexOf("PRD")) {
                                    if (pipe == 3) {
                                        mensaje = mensaje + result.charAt(i);
                                    }
                                    if (result.charAt(i) == '|') {
                                        pipe++;
                                    }
                                    i++;
                                }
                                ////busco numero de respuesta
                                i = result.indexOf("ZAU");
                                pipe = 0;
                                while (i < result.indexOf("PRD")) {
                                    if (pipe == 2) {
                                        if (result.charAt(i) != '|') {
                                            num_orden = num_orden + result.charAt(i);
                                        }
                                    }
                                    if (result.charAt(i) == '|') {
                                        pipe++;
                                    }
                                    i++;
                                }
                                mensaje = mensaje.replace("^", " ");
                                String codigo_respuesta = mensaje.substring(0, 4);
                                System.out.println(num_orden + " " + mensaje);
                                System.out.println(codigo_respuesta);
                                System.out.println("tipo_orden Medife2:" + tipo_orden);
                                if (codigo_respuesta.equals("B000") || codigo_respuesta.equals("B001")) {
                                    estado_orden = 1;
                                    txtnumorden.setText(num_orden);
                                    /////////////////////grabo en servidor nuestro///////////////////////////////////////////////////////////////////////////
                                    validar_orden medife = new validar_orden();
                                    System.out.println(practicas);
                                    respuesta = medife.valida(
                                            Integer.valueOf(txtaño.getText() + txtmes.getText()),
                                            txtnombreafiliado.getText(),
                                            txtdocumento.getText(),
                                            txtnumafiliado.getText(),
                                            Integer.valueOf(txtmatricula.getText()),
                                            num_orden,
                                            fecha,
                                            Double.valueOf(txttotal1.getText()),
                                            fecha,
                                            hora,
                                            ip2,
                                            id_obra_social,
                                            id_usuario,
                                            n2,
                                            practicas,
                                            Double.valueOf(txtcoseguro.getText()),
                                            fecha,
                                            tipo_orden,
                                            observacion,
                                            plan_ss,
                                            coseguro_ss,
                                            estado_orden,
                                            fechaDate);
                                    if (respuesta == 0) {//en el caso se q no se grabe en nuestro servidor se anula del wsdl
                                        num_orden = "";
                                        System.out.println("Anulacion medife");

                                        String Anulacion = "MSH|^~\\&|TRIA0100M|TRIA00007526|MEDIFE|MEDIFE^222222^IIN|" + fechahora_medife + "||ZQA^Z04^ZQA_Z02|" + codigo_seguridad_medife + "|P|2.4|||NE|AL|ARG\r\n"
                                                + "ZAU||" + num_orden + "\r\n"
                                                + "PRD|PS^Prestador Solicitante||^^^T||||30522483881^CU|\r\n"
                                                + "PRD|EF^Efector||^^^T||||" + cuit + "^CU&M&C|\r\n"
                                                + "PID|||" + MedifeAfiliado.Codigo_afiliado + "^^^MEDIFE^HC^MEDIFE||UNKNOWN";
                                        clave = "IA007526";
                                        usuario = "IA007526";
                                        tipo = "SI";
                                        llave = "1234567890123456ABCDEFGH";

                                        pszMsg = tpDatos.EncriptarStr(Anulacion, llave);

                                        try { // Call Web Service Operation
                                            WebServiceIA service2 = new WebServiceIA();
                                            WebServiceIASoap port2 = service2.getWebServiceIASoap();
                                            // TODO initialize WS operation arguments here
                                            pszUser = tpDatos.EncriptarStr(usuario, llave);
                                            pszPwd = tpDatos.EncriptarStr(clave, llave);
                                            pszMsgType = tpDatos.EncriptarStr(tipo, llave);
                                            // TODO process result here
                                            result = port2.enviar(pszMsg, pszUser, pszPwd, pszMsgType);
                                            System.out.println("Respuesta = " + result);
                                            ///busco la respuesta
                                            i = result.indexOf("ZAU");
                                            pipe = 0;
                                            while (i < result.indexOf("PRD")) {
                                                if (pipe == 3) {
                                                    mensaje = mensaje + result.charAt(i);
                                                }
                                                if (result.charAt(i) == '|') {
                                                    pipe++;
                                                }
                                                i++;
                                            }
                                            ////busco numero de respuesta
                                            i = result.indexOf("ZAU");
                                            pipe = 0;
                                            while (i < result.indexOf("PRD")) {
                                                if (pipe == 2) {
                                                    num_orden = num_orden + result.charAt(i);
                                                }
                                                if (result.charAt(i) == '|') {
                                                    pipe++;
                                                }
                                                i++;
                                            }
                                            mensaje = mensaje.replace("^", " ");
                                            codigo_respuesta = mensaje.substring(0, 4);
                                            System.out.println(num_orden + " " + mensaje);
                                        } catch (Exception ex) {
                                            cursor2();
                                            JOptionPane.showMessageDialog(null, ex);
                                        }
                                        if (codigo_respuesta.equals("B000") || codigo_respuesta.equals("B001")) {
                                            bandera_medife = 0;
                                            JOptionPane.showMessageDialog(null, "La orden no fue validada");
                                        } else {
                                            cursor2();
                                            bandera_medife = 0;
                                            JOptionPane.showMessageDialog(null, mensaje);
                                        }
                                    } else {
                                        cursor2();
                                        bandera_medife = 1;
                                        JOptionPane.showMessageDialog(null, "Nro. Transaccion: " + num_orden);
                                        borrartabla();
                                    }
                                } else {

                                    estado_orden = 0;
                                    txtnumorden.setText(num_orden);

                                    observacion = result;
                                    if (observacion.length() > 500) {
                                        observacion = observacion.substring(0, 500);
                                    }

                                    /////////////////////grabo en servidor nuestro///////////////////////////////////////////////////////////////////////////
                                    validar_orden medife = new validar_orden();
                                    System.out.println(practicas);
                                    respuesta = medife.valida(
                                            Integer.valueOf(txtaño.getText() + txtmes.getText()),
                                            txtnombreafiliado.getText(),
                                            txtdocumento.getText(),
                                            txtnumafiliado.getText(),
                                            Integer.valueOf(txtmatricula.getText()),
                                            num_orden,
                                            fecha,
                                            Double.valueOf(txttotal1.getText()),
                                            fecha,
                                            hora,
                                            ip2,
                                            id_obra_social,
                                            id_usuario,
                                            n2,
                                            practicas,
                                            Double.valueOf(txtcoseguro.getText()),
                                            fecha,
                                            tipo_orden,
                                            observacion,
                                            plan_ss,
                                            coseguro_ss,
                                            estado_orden,
                                            fechaDate);

                                    if (respuesta == 0) {//en el caso se q no se grabe en nuestro servidor se anula del wsdl
                                        cursor2();
                                        JOptionPane.showMessageDialog(null, "La orden no pudo ser cargada");
                                        bandera_medife = 0;

                                    } else {
                                        cursor2();
                                        bandera_medife = 0;
                                        JOptionPane.showMessageDialog(null, "La orden no pudo ser cargada en el servidor de Medife");
                                        JOptionPane.showMessageDialog(null, mensaje);
                                        borrartabla();
                                    }
                                }
                                cursor2();
                            } catch (Exception ex) {
                                cursor2();
                                JOptionPane.showMessageDialog(null, ex);
                            }
                        } else {
                            cursor2();
                            //  JOptionPane.showMessageDialog(null, "No hay practicas en la tabla...");
                        }
                    }
                    ////////////////////////JERARQUICOS
                    if (obra.equals("37701 - JERARQUICOS SALUD - EMP. BNA - ONLINE")) {

                        ///JERARQUICOS SALUD - EMPLEADOS BANCOS NACIONAL
                        //  if (obra.equals("37700 - JERARQUICOS SALUD - EMPLEADOS BANCOS NACIONAL")) {///JERARQUICOS SALUD - EMPLEADOS BANCOS NACIONAL
                        cursor();
                        num_orden = "";
                        String salida = "";
                        String practicas = "", id_practicas = "";
                        if (tablapracticas.getRowCount() != 0) {
                            int n2, i = 0, bandera_respuesta = 0;
                            String cod_practca, coleccion = "", nombre;
                            n2 = tablapracticas.getRowCount();

                            if (n2 != 0) {
                                while (i < n2) {
                                    cod_practca = tablapracticas.getValueAt(i, 1).toString();
                                    id_practicas = id_practicas + String.valueOf(tablapracticas.getValueAt(i, 5).toString());
                                    practicas = practicas + String.valueOf(tablapracticas.getValueAt(i, 1).toString()).substring(0, 6);
                                    nombre = tablapracticas.getValueAt(i, 2).toString();
                                    try { // Call Web Service Operation

                                        ObjectFactory factory = new ObjectFactory();
                                        JAXBElement<String> codigo = factory.createCriterioPracticaRequiereAutorizacionCodigoNomencladorConvenio(cod_practca);
                                        JAXBElement<Integer> convenio = factory.createCriterioPracticaRequiereAutorizacionIdConvenio(451);

                                        System.out.println(codigo);
                                        System.out.println(convenio);

                                        CriterioPracticaRequiereAutorizacion autorizacion = new CriterioPracticaRequiereAutorizacion();

                                        autorizacion.setCodigoNomencladorConvenio(codigo);
                                        autorizacion.setIdConvenio(convenio);
                                        autorizacion.setIdPlan(plan);

                                        JAXBElement<CriterioPracticaRequiereAutorizacion> criterio2 = factory.createSolicitudValidacionPracticaRequiereAutorizacionCriterioPracticaRequiereAutorizacion(autorizacion);

                                        SolicitudValidacionPracticaRequiereAutorizacion solicitud = new SolicitudValidacionPracticaRequiereAutorizacion();
                                        solicitud.setCriterioPracticaRequiereAutorizacion(criterio2);

                                        ClienteJerarquicos.Servicio service = new ClienteJerarquicos.Servicio();
                                        ClienteJerarquicos.IServicioPublico port = service.getBasicHttpBindingIServicioPublico();

                                        ClienteJerarquicos.RespuestaBase result = port.validarPracticaRequiereAutorizacion(solicitud);

                                        JAXBElement<RespuestaBase> respuestajxe = factory.createValidarPracticaRequiereAutorizacionResponseValidarPracticaRequiereAutorizacionResult(result);

                                        ClienteJerarquicos.ValidarPracticaRequiereAutorizacionResponse resultado = new ClienteJerarquicos.ValidarPracticaRequiereAutorizacionResponse();

                                        resultado.setValidarPracticaRequiereAutorizacionResult(respuestajxe);

                                        JAXBElement<String> respuestaString = result.getDTOSerializado();
                                        observacion = respuestaString.getValue();
                                        if (respuestaString.getValue() != null) {

                                            String respuesta2 = resultado.getValidarPracticaRequiereAutorizacionResult().getValue().getResultado().value();

                                            System.out.println("Result = " + result.getResultado().value());

                                            System.out.println("Result2 = " + respuesta2);

                                            System.out.println("Result3 = " + respuestaString.getValue());

                                            int pos = respuestaString.getValue().indexOf("\"RequiereAutorizacion\":false");
                                            int pos2 = respuestaString.getValue().indexOf("\"RequiereAutorizacion\":true");
                                            if (pos > 0) {

                                                salida = salida + "Practica: " + cod_practca + ": Autorizada\r\n";
                                                if (coleccion.equals("")) {
                                                    coleccion = coleccion + "{'Cantidad':1,'CodigoConvenio':'" + cod_practca + "','DescripcionCodigoConvenio':'" + nombre + "'}";
                                                } else {
                                                    System.out.println("cod_prac=" + coleccion.indexOf(cod_practca));
                                                    int pos_prac = coleccion.indexOf(cod_practca);//32

                                                    if (pos_prac > 0) {

                                                        System.out.println("pos_cant =" + coleccion.indexOf("'Cantidad':", pos_prac - 32));
                                                        int pos_cant = coleccion.indexOf("'Cantidad':", pos_prac - 32);

                                                        System.out.println("cantidad =" + coleccion.substring(pos_prac - 20, pos_prac - 19));
                                                        int cantidad = Integer.valueOf(coleccion.substring(pos_prac - 20, pos_prac - 19));

                                                        String cadena_vieja = "{'Cantidad':" + cantidad + ",'CodigoConvenio':'" + cod_practca + "','DescripcionCodigoConvenio':'" + nombre + "'}";

                                                        String cadena_nueva = "{'Cantidad':" + (cantidad + 1) + ",'CodigoConvenio':'" + cod_practca + "','DescripcionCodigoConvenio':'" + nombre + "'}";

                                                        System.out.println("vieja: " + cadena_vieja);
                                                        System.out.println("nueva: " + cadena_nueva);

                                                        System.out.println("coleccion: " + coleccion);

                                                        coleccion = coleccion.replace(cadena_vieja, cadena_nueva);
                                                    } else {
                                                        coleccion = coleccion + ",{'Cantidad':1,'CodigoConvenio':'" + cod_practca + "','DescripcionCodigoConvenio':'" + nombre + "'}";
                                                    }

                                                }
                                                bandera_respuesta = 0;
                                            }
                                            if (pos2 > 0) {
                                                salida = salida + "Practica: " + cod_practca + ":Requiere autorización\r\n";
                                                bandera_respuesta = 1;
                                                bandera_jerarquicos = 0;
                                            }
                                        } else {
                                            ClienteJerarquicos.Notificacion notificaciones = result.getNotificaciones().getValue().getNotificacion().get(0);

                                            String respuesta2 = resultado.getValidarPracticaRequiereAutorizacionResult().getValue().getResultado().value();

                                            System.out.println("Result = " + result.getResultado().value());

                                            System.out.println("Result2 = " + respuesta2);

                                            System.out.println("Result3 = " + respuestaString.getValue());

                                            System.out.println("Result4 = " + notificaciones.getMensaje().getValue());
                                            salida = salida + "Practica: " + cod_practca + ":" + notificaciones.getMensaje().getValue() + "\r\n";
                                            bandera_respuesta = 1;
                                        }
                                    } catch (Exception ex) {
                                        Logger.getLogger(JerarquicosAfiliado.class.getName()).log(Level.SEVERE, null, ex);
                                        bandera_respuesta = 1;
                                        bandera_jerarquicos = 0;
                                    }
                                    i++;
                                }
                                ///
                                ////////////////////////////////////////////////////////////
                                if (bandera_respuesta == 0 && bandera_jerarquicos != 0) {
                                    JOptionPane.showMessageDialog(null, salida);
                                    System.out.println("Coleccion de datos: " + coleccion);
                                    try {
                                        String datos = "{"
                                                + "'ApellidoEfector':'" + Login.nombre_colegiado + "',"
                                                + "'NombreEfector':'" + Login.nombre_colegiado + "',"
                                                + "'CuilEfector':'" + Login.cuit + "',"
                                                + "'Diagnostico':'',"
                                                + "'FechaConsumo':'" + date_jerarquicos + "',"
                                                + "'IdConvenio':451,"
                                                + "'NroSocio':" + String.valueOf(NumeroSocio) + ","
                                                + "'OrdenSocio':" + String.valueOf(NumeroOrden) + ","
                                                + "'ConsumosWeb':[" + coleccion + "],"
                                                + "'DTOSerializado':null,"
                                                + "'IdTransaccion':0,"
                                                + "'EstadoAutorizacion':null"
                                                + "}";
                                        ObjectFactory factory = new ObjectFactory();
                                        JAXBElement<String> Dtoserializado = factory.createCriterioAutorizacionConsumoWebDTOSerializado(datos);

                                        ClienteJerarquicos.CriterioAutorizacionConsumoWeb criterio = new ClienteJerarquicos.CriterioAutorizacionConsumoWeb();

                                        criterio.setDTOSerializado(Dtoserializado);
                                        criterio.setGenerarCupon(false);

                                        ClienteJerarquicos.SolicitudAutorizacionConsumoWeb solicitud = new ClienteJerarquicos.SolicitudAutorizacionConsumoWeb();

                                        JAXBElement<CriterioAutorizacionConsumoWeb> consumo = factory.createSolicitudAutorizacionConsumoWebCriterioAutorizacionConsumoWeb(criterio);

                                        solicitud.setCriterioAutorizacionConsumoWeb(consumo);
                                        // TODO process result here
                                        System.out.println("input " + solicitud.getCriterioAutorizacionConsumoWeb().getValue().getDTOSerializado().getValue());
                                        // Call Web Service Operation
                                        Servicio service = new Servicio();
                                        IServicioPublico port = service.getBasicHttpBindingIServicioPublico();
                                        // TODO initialize WS operation arguments here
                                        ClienteJerarquicos.RespuestaAutorizacionConsumoWeb result = port.autorizarConsumo(solicitud);

                                        JAXBElement<RespuestaAutorizacionConsumoWeb> respuestajxe = factory.createAutorizarConsumoResponseAutorizarConsumoResult(result);

                                        ClienteJerarquicos.AutorizarConsumoResponse resultado = new ClienteJerarquicos.AutorizarConsumoResponse();

                                        resultado.setAutorizarConsumoResult(respuestajxe);

                                        JAXBElement<String> respuestaString = result.getDTOSerializado();

                                        /// String respuesta2 = resultado.getAutorizarConsumoResult().getValue().
                                        System.out.println("Result = " + result.getResultado().value());

                                        /// System.out.println("Result2 = " + respuesta2);
                                        System.out.println("Result consumo = " + respuestaString.getValue());
                                        //"Nombre":"EXITO"},"IdTransaccion":297361}
                                        int pos_ok = respuestaString.getValue().indexOf("\"Nombre\":\"EXITO\"");
                                        if (pos_ok > 0) {
                                            estado_orden = 1;
                                            int pos_trans = respuestaString.getValue().indexOf("\"IdTransaccion\":");
                                            num_orden = respuestaString.getValue().substring(pos_trans + 16, pos_trans + 22);
                                            txtnumorden.setText(num_orden);
                                            /////////////////////grabo en servidor nuestro///////////////////////////////////////////////////////////////////////////
                                            validar_orden jerarquicos = new validar_orden();
                                            System.out.println(practicas);
                                            respuesta = jerarquicos.valida(
                                                    Integer.valueOf(txtaño.getText() + txtmes.getText()),
                                                    txtnombreafiliado.getText(),
                                                    txtdocumento.getText(),
                                                    txtnumafiliado.getText(),
                                                    Integer.valueOf(txtmatricula.getText()),
                                                    num_orden,
                                                    fecha,
                                                    Double.valueOf(txttotal1.getText()),
                                                    fecha,
                                                    hora,
                                                    ip2,
                                                    id_obra_social,
                                                    id_usuario,
                                                    n2,
                                                    practicas,
                                                    Double.valueOf(txtcoseguro.getText()),
                                                    fecha,
                                                    tipo_orden,
                                                    observacion,
                                                    plan_ss,
                                                    coseguro_ss,
                                                    estado_orden,
                                                    fechaDate);
                                            if (respuesta == 0) {//en el caso se q no se grabe en nuestro servidor se anula del wsdl

                                                System.out.println("Anulacion Jerarquicos");
                                                bandera_jerarquicos = 0;

                                                ClienteJerarquicos.CriterioAnulacionConsumoWeb autorizacion = new ClienteJerarquicos.CriterioAnulacionConsumoWeb();
                                                autorizacion.setIdConvenio(451);
                                                autorizacion.setIdTransaccion(Integer.valueOf(num_orden));

                                                JAXBElement<CriterioAnulacionConsumoWeb> criterio3 = factory.createSolicitudAnulacionConsumoWebCriterioAnulacionConsumoWeb(autorizacion);

                                                ClienteJerarquicos.SolicitudAnulacionConsumoWeb solicitud3 = new ClienteJerarquicos.SolicitudAnulacionConsumoWeb();
                                                solicitud3.setCriterioAnulacionConsumoWeb(criterio3);

                                                ClienteJerarquicos.Servicio service3 = new ClienteJerarquicos.Servicio();

                                                ClienteJerarquicos.IServicioPublico port3 = service3.getBasicHttpBindingIServicioPublico();

                                                ClienteJerarquicos.RespuestaBase result3 = port3.anularAutorizacionConsumo(solicitud3);
                                                String respuesta3 = result3.getDTOSerializado().getValue();

                                                int pos_ok2 = respuesta3.indexOf("\"Nombre\":\"EXITO\"");
                                                if (pos_ok2 > 0) {
                                                    cursor2();
                                                    JOptionPane.showMessageDialog(null, "La orden fue anulada del servidor de Jerarquicos Salud");
                                                } else {
                                                    cursor2();
                                                    JOptionPane.showMessageDialog(null, "Error " + respuesta3);
                                                }
                                            } else {
                                                cursor2();
                                                bandera_jerarquicos = 1;
                                                JOptionPane.showMessageDialog(null, "Nro. Transaccion: " + num_orden);
                                                borrartabla();
                                            }
                                        } else {
                                            bandera_jerarquicos = 0;
                                        }
                                    } catch (Exception ex) {
                                        bandera_jerarquicos = 0;
                                        Logger.getLogger(JerarquicosAfiliado.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                } else {

                                    bandera_respuesta = 1;

                                    estado_orden = 0;
                                    int pos_trans = observacion.indexOf("\"IdTransaccion\":");
                                    num_orden = observacion.substring(pos_trans + 16, pos_trans + 22);
                                    txtnumorden.setText(num_orden);
                                    if (observacion.length() > 500) {
                                        observacion = observacion.substring(0, 500);
                                    }
                                    /////////////////////grabo en servidor nuestro///////////////////////////////////////////////////////////////////////////
                                    validar_orden jerarquicos = new validar_orden();
                                    System.out.println(practicas);
                                    respuesta = jerarquicos.valida(
                                            Integer.valueOf(txtaño.getText() + txtmes.getText()),
                                            txtnombreafiliado.getText(),
                                            txtdocumento.getText(),
                                            txtnumafiliado.getText(),
                                            Integer.valueOf(txtmatricula.getText()),
                                            num_orden,
                                            fecha,
                                            Double.valueOf(txttotal1.getText()),
                                            fecha,
                                            hora,
                                            ip2,
                                            id_obra_social,
                                            id_usuario,
                                            n2,
                                            practicas,
                                            Double.valueOf(txtcoseguro.getText()),
                                            fecha,
                                            tipo_orden,
                                            observacion,
                                            plan_ss,
                                            coseguro_ss,
                                            estado_orden,
                                            fechaDate);
                                    if (respuesta == 0) {//en el caso se q no se grabe en nuestro servidor se anula del wsdl
                                        cursor2();
                                        JOptionPane.showMessageDialog(null, "La orden no pudo ser cargada");
                                        bandera_jerarquicos = 0;

                                    } else {
                                        cursor2();
                                        bandera_jerarquicos = 0;
                                        JOptionPane.showMessageDialog(null, "La orden no pudo ser cargada en el servidor de Jerarquicos");
                                        JOptionPane.showMessageDialog(null, salida);
                                        borrartabla();
                                    }

                                }
                                /////////////////////////////////////////////////////////
                            }
                        }
                    }
                    if (obra.equals("40813 - IOSFA")) {

                        double coseguroIosfa = 0;
                        if (chkcoseguro.isSelected()) {
                            int cuentaPracticasComunes = 0;
                            String cadenaPractica;
                            for (int i = 0; i < tablapracticas.getRowCount(); i++) {
                                cadenaPractica = tablapracticas.getValueAt(i, 1).toString() + " - " + tablapracticas.getValueAt(i, 2).toString();
                                System.out.println("Tipo práctica:" + Practica.buscarPractica(cadenaPractica, listaPracticas).getTipoPractica());
                                switch (Practica.buscarPractica(cadenaPractica, listaPracticas).getTipoPractica()) {

                                    case 4:
                                        if (Practica.buscarPractica(cadenaPractica, listaPracticas).getPrecioTotal() > 500) {
                                            coseguroIosfa = coseguroIosfa + 500;
                                        } else {
                                            coseguroIosfa = coseguroIosfa + Practica.buscarPractica(cadenaPractica, listaPracticas).getPrecioTotal();
                                        }
                                        break;
                                    case 3:
                                        if (Practica.buscarPractica(cadenaPractica, listaPracticas).getPrecioTotal() > 200) {
                                            coseguroIosfa = coseguroIosfa + 200;
                                        } else {
                                            coseguroIosfa = coseguroIosfa + Practica.buscarPractica(cadenaPractica, listaPracticas).getPrecioTotal();
                                        }
                                        break;
                                    case 2:
                                        if (Practica.buscarPractica(cadenaPractica, listaPracticas).getPrecioTotal() > 100) {
                                            coseguroIosfa = coseguroIosfa + 100;
                                        } else {
                                            coseguroIosfa = coseguroIosfa + Practica.buscarPractica(cadenaPractica, listaPracticas).getPrecioTotal();
                                        }
                                        break;
                                    case 1:
                                        cuentaPracticasComunes++;
                                        break;
                                }
                            }
                            if (cuentaPracticasComunes > 0) {
                                if (cuentaPracticasComunes > 6) {
                                    int excedente = cuentaPracticasComunes - 6;
                                    coseguroIosfa = coseguroIosfa + excedente * 40;
                                }
                                coseguroIosfa = coseguroIosfa + 100;
                            }
                        }
                        String cadena_practicas = "", id_practicas = "";
                        int n2 = tablapracticas.getRowCount();
                        if (tablapracticas.getRowCount() != 0) {
                            int i = 0;
                            if (n2 != 0) {
                                while (i < n2) {
                                    plan_ss = plan_ss + "00";
                                    coseguro_ss = coseguro_ss + "00000.0";
                                    id_practicas = id_practicas + String.valueOf(tablapracticas.getValueAt(i, 5).toString());
                                    cadena_practicas = cadena_practicas + tablapracticas.getValueAt(i, 1).toString().substring(0, 6);
                                    System.out.println(cadena_practicas);
                                    i++;
                                }
                            }
                        } else {
                            cursor2();
                        }
                        estado_orden = 1;
                        System.out.println(id_practicas + "  " + n2);
                        /////////////////////grabo en servidor nuestro///////////////////////////////////////////////////////////////////////////
                        validar_orden obra_social_comun = new validar_orden();
                        respuesta = obra_social_comun.valida(
                                Integer.valueOf(txtaño.getText() + txtmes.getText()),//periodo
                                txtnombreafiliado.getText(),//nombre_afiliado
                                txtdocumento.getText(),//dni_afiliado
                                txtnumafiliado.getText(),//numero_afiliado
                                Integer.valueOf(txtmatricula.getText()),//matricula_presc
                                txtnumorden.getText(),//numero_orden
                                txtfecha.getText(),//fecha_orden
                                Double.valueOf(txttotal1.getText()),//total_orden
                                fecha,//fecha_carga,
                                hora,//hora_carga,
                                ip2,//ip,
                                id_obra_social,//id_obrasocial,
                                id_usuario,//id_usuario,
                                n2,// cantidad_practicas,
                                cadena_practicas,//practicas
                                coseguroIosfa,//Double.valueOf(txtcoseguro.getText()),
                                fecha,//fecha,
                                tipo_orden,//tipo_orden,
                                observacion,//observacion,
                                plan_ss,//0,
                                coseguro_ss,
                                estado_orden,
                                fechaDate);//0.00
                        if (chkcoseguro.isSelected()) {

                            int opcion = JOptionPane.showConfirmDialog(null, "Coseguro: $" + coseguroIosfa + "\nDesea Imprimir un comprobante?", "IOSFA Impresíon", JOptionPane.YES_NO_OPTION);

                            if (opcion == 0) {
                                cursor();
                                ConexionMariaDB cc = new ConexionMariaDB();
                                Connection cnn = cc.Conectar();
                                ////////////////Previsualizacion///////////////////////////
                                JDialog viewer = new JDialog(new javax.swing.JFrame(), "Coseguro IOSFA", true);
                                viewer.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
                                viewer.setSize(800, 600);
                                viewer.setLocationRelativeTo(null);
                                JasperViewer jv = null;
                                ///////////////////////////////////////////////////////////
                                Map parametros = new HashMap();
                                parametros.put("id_orden", respuesta);
                                try {
                                    JasperReport report_comprobante = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/comprobante_iosfa.jasper"));
                                    JasperPrint jPrint_comprobante = JasperFillManager.fillReport(report_comprobante, parametros, cn);
                                    //JasperExportManager.exportReportToPdfFile(jPrint_validacion, "C:\\Descargas-CBT\\" + periodo + "-" + txtcolegiado.getText() + "-validacion-.pdf");
                                    //JasperPrintManager.printReport(jPrint_comprobante, false);
                                    jv = new JasperViewer(jPrint_comprobante, false);
                                    viewer.getContentPane().add(jv.getContentPane());
                                    viewer.setVisible(true);
                                    cnn.close();
                                } catch (JRException ex) {
                                    System.err.println("Error iReport: " + ex.getMessage());
                                } catch (SQLException ex) {
                                    Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                cursor2();
                            }
                        }
                        if (respuesta == 0) {//en el caso se q no se grabe en nuestro servidor se anula del wsdl
                            bandera_obra_social_comun = 0;
                        } else {
                            bandera_obra_social_comun = 1;
                        }
                    }
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if (obra.equals("2700 - UNT - Accion  Social de la  UNT")) {
                        JSONObject jsonConsulta = new JSONObject();
                        plan_ss = "";
                        coseguro_ss = "";
                        cursor();
                        short cantidad = 1;
                        if (tablapracticas.getRowCount() != 0) {
                            int n2, i = 0;
                            String cod_practca = "", practicas = "", id_practicas = "";
                            n2 = tablapracticas.getRowCount();
                            if (n2 != 0) {
                                while (i < n2) {
                                    plan_ss = plan_ss + "00";
                                    coseguro_ss = coseguro_ss + "00000.0";
                                    id_practicas = id_practicas + String.valueOf(tablapracticas.getValueAt(i, 5).toString());
                                    practicas = practicas + String.valueOf(tablapracticas.getValueAt(i, 1).toString()).substring(0, 6);
                                    if (cod_practca.equals("")) {
                                        cod_practca = tablapracticas.getValueAt(i, 1).toString();
                                    } else {
                                        cod_practca = cod_practca + "," + tablapracticas.getValueAt(i, 1).toString();
                                    }

                                    i++;
                                }
                            }
                            ///////////////////////////////////////////////////////////////////////
                            String efector = cuit;
                            String numAfiliado = txtnumafiliado.getText();
                            System.out.println("efector: " + efector);
                            System.out.println("numAfiliado: " + numAfiliado);
                            System.out.println("fechaDate: " + fechaDate);
                            System.out.println("cod_practca: " + cod_practca);

                            jsonConsulta.put("servicio", 2);
                            jsonConsulta.put("fecha", fechaDate);
                            jsonConsulta.put("dni", numAfiliado);
                            jsonConsulta.put("tipo", "DNI");
                            jsonConsulta.put("cuit", efector);
                            jsonConsulta.put("practica", cod_practca);
                            jsonConsulta.put("observacion", "");
                            jsonConsulta.put("diagnostico", "");
                            jsonConsulta.put("convenio", "1003");
                            JSONObject json2;
                            try {
                                json2 = conexionWsdl(jsonConsulta);
                                System.out.println("json respuesta generar orden: " + json2.toString());

                                if (json2.getBoolean("resultado") == true) {
                                    estado_orden = 1;
                                    num_orden = String.valueOf(json2.getString("nroautorizacion"));
                                    txtnumorden.setText(num_orden);
                                    /////////////////////grabo en servidor nuestro///////////////////////////////////////////////////////////////////////////
                                    validar_orden asunt = new validar_orden();
                                    System.out.println(practicas);
                                    respuesta = asunt.valida(
                                            Integer.valueOf(txtaño.getText() + txtmes.getText()),
                                            txtnombreafiliado.getText(),
                                            txtdocumento.getText(),
                                            txtnumafiliado.getText(),
                                            Integer.valueOf(txtmatricula.getText()),
                                            num_orden,
                                            fecha,
                                            Double.valueOf(txttotal1.getText()),
                                            fecha,
                                            hora,
                                            ip2,
                                            id_obra_social,
                                            id_usuario,
                                            n2,
                                            practicas,
                                            Double.valueOf(txtcoseguro.getText()),
                                            fecha,
                                            tipo_orden,
                                            observacion,
                                            plan_ss,
                                            coseguro_ss,
                                            estado_orden,
                                            fechaDate);
                                    if (respuesta == 0) {//en el caso se q no se grabe en nuestro servidor se anula del wsdl
                                        System.out.println("Anulacion asunt");

                                        jsonConsulta.put("servicio", 3);
                                        jsonConsulta.put("codigo", num_orden);
                                        JSONObject json3 = conexionWsdl(jsonConsulta);
                                        System.out.println("json respuesta anulacion orden: " + json3.toString());

                                        if (json3.getBoolean("resultado") == true) {
                                            num_orden = String.valueOf(0);
                                            cursor2();
                                            bandera_asunt = 0;
                                            JOptionPane.showMessageDialog(null, "La orden no fue validada");
                                        } else {
                                            cursor2();
                                            bandera_asunt = 0;
                                            JOptionPane.showMessageDialog(null, json3.getString("mensaje"));
                                        }
                                    } else {
                                        cursor2();
                                        bandera_asunt = 1;
                                        JOptionPane.showMessageDialog(null, "Nro. Transaccion: " + num_orden);
                                        borrartabla();
                                    }
                                } else {
                                    estado_orden = 0;
                                    observacion = json2.getString("mensaje");
                                    if (observacion.length() > 500) {
                                        observacion = observacion.substring(0, 500);
                                    }
                                    /////////////////////grabo en servidor nuestro///////////////////////////////////////////////////////////////////////////
                                    validar_orden asunt = new validar_orden();
                                    System.out.println(practicas);
                                    respuesta = asunt.valida(
                                            Integer.valueOf(txtaño.getText() + txtmes.getText()),
                                            txtnombreafiliado.getText(),
                                            txtdocumento.getText(),
                                            txtnumafiliado.getText(),
                                            Integer.valueOf(txtmatricula.getText()),
                                            "0",
                                            fecha,
                                            Double.valueOf(txttotal1.getText()),
                                            fecha,
                                            hora,
                                            ip2,
                                            id_obra_social,
                                            id_usuario,
                                            n2,
                                            practicas,
                                            Double.valueOf(txtcoseguro.getText()),
                                            fecha,
                                            tipo_orden,
                                            observacion,
                                            plan_ss,
                                            coseguro_ss,
                                            estado_orden,
                                            fechaDate);
                                    if (respuesta == 0) {//en el caso se q no se grabe en nuestro servidor se anula del wsdl
                                        cursor2();
                                        JOptionPane.showMessageDialog(null, "La orden no pudo ser cargada");
                                        bandera_asunt = 0;

                                    } else {
                                        cursor2();
                                        bandera_asunt = 0;
                                        JOptionPane.showMessageDialog(null, "La orden no pudo ser cargada en el servidor de Asunt");
                                        JOptionPane.showMessageDialog(null, json2.getString("mensaje"));
                                        borrartabla();
                                    }
                                }

                            } catch (IOException ex) {
                                bandera_asunt = 0;
                                Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(null, "La orden no pudo ser cargada en el servidor de Asunt " + ex);
                            } catch (ParserConfigurationException ex) {
                                bandera_asunt = 0;
                                Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(null, "La orden no pudo ser cargada en el servidor de Asunt " + ex);
                            } catch (SAXException ex) {
                                bandera_asunt = 0;
                                Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(null, "La orden no pudo ser cargada en el servidor de Asunt " + ex);
                            }
                        } else {
                            cursor2();
                            //  JOptionPane.showMessageDialog(null, "No hay practicas en la tabla...");
                        }
                    }

                } else {

                    cursor();
                    System.out.println("Obra social OFFLINE");
                    ////////////////////////////OBRAS SOCIALES OFFLINE////////////////////////////////////////////////////////////////
                    plan_ss = "";
                    coseguro_ss = "";
                    String cadena_practicas = "", id_practicas = "";
                    int n2 = tablapracticas.getRowCount();
                    if (tablapracticas.getRowCount() != 0) {
                        int i = 0;
                        if (n2 != 0) {
                            while (i < n2) {
                                plan_ss = plan_ss + "00";
                                coseguro_ss = coseguro_ss + "00000.0";
                                id_practicas = id_practicas + String.valueOf(tablapracticas.getValueAt(i, 5).toString());
                                if (txtobrasocial.getText().equals("3102 - OSDE - OFFLINE") && tipo_orden != 3) {
                                    System.out.println("banderaEstadoOffline verifica:" + banderaEstadoOffline);
                                    //System.out.println("String.valueOf(tablapracticas.getValueAt(i, 1).toString())"+String.valueOf(tablapracticas.getValueAt(i, 1).toString()));
                                    if (String.valueOf(tablapracticas.getValueAt(i, 1).toString()).equals("664418")) {//verifica dimero d
                                        if (MedicosAutorizados.buscarMedicosAutorizados(Integer.valueOf(txtmatricula.getText()), listaMedicos)) {
                                            banderaEstadoOffline = 1;
                                            System.out.println("banderaEstadoOffline ingresa:" + banderaEstadoOffline);
                                        } else {
                                            cursor2();
                                            JOptionPane.showMessageDialog(null, "La Matricula medica No está autorizada a realizar la practica 664418 - DIMERO D");
                                            banderaEstadoOffline = 0;
                                            break;
                                        }

                                    }
                                }

                                cadena_practicas = cadena_practicas + tablapracticas.getValueAt(i, 1).toString().substring(0, 6);
                                System.out.println(cadena_practicas);
                                i++;
                            }
                        }
                    } else {
                        cursor2();
                        JOptionPane.showMessageDialog(null, "No hay practicas en la tabla...");
                        banderaEstadoOffline = 0;
                    }
                    ///////////////////////////////////////////////////////////////////////
                    if ("1800 - SUBSIDIO DE SALUD - IPSSPT".equals(txtobrasocial.getText())
                            || "1801 - SUBSIDIO DE SALUD - MATERNO INFANTIL".equals(txtobrasocial.getText()) || "1803 - SUBSIDIO DE SALUD - RECIPROCIDAD".equals(txtobrasocial.getText()) || "1804 - SUBSIDIO DE SALUD - INTERNADO".equals(txtobrasocial.getText()) || "1810 - SUBSIDIO DE SALUD - PRODIASS-PLAN PREVENCION".equals(txtobrasocial.getText()) || "1815 - SUBSIDIO DE SALUD - REFACTURACION".equals(txtobrasocial.getText())) {
                        long ordnumero = Long.valueOf(txtnumorden.getText());
                        long resto3 = 0, resto2, resto = ordnumero;
                        long total = 0, i = 0, d, digito;
                        long totalsobre11, totalmod11, orddigitoverificador;
                        resto3 = resto / 100;
                        digito = resto - (resto3 * 100);
                        resto = resto3;
                        do {
                            i++;
                            resto2 = resto / 10;
                            d = resto - (resto2 * 10);
                            total = total + (d * (i + 1));
                            resto = resto2;
                        } while (resto > 0);
                        totalsobre11 = total / 11;
                        totalmod11 = total - (totalsobre11 * 11);
                        orddigitoverificador = 11 - totalmod11;
                        if (orddigitoverificador == digito) {
                            banderaEstadoOffline = 1;
                            System.out.println("resto3 " + resto3);
//                            txtnumorden.setText(String.valueOf(resto3));
                        } else {
                            if (orddigitoverificador == 11) {
                                banderaEstadoOffline = 1;
                                System.out.println("resto3 " + resto3);
//                                txtnumorden.setText(String.valueOf(resto3));
                            } else {
                                cursor2();
                                JOptionPane.showMessageDialog(null, "Numero de orden incorrecto de Subsidio...");
                                banderaEstadoOffline = 0;
                            }
                        }
                    }
                    if (banderamodifica == 0 && banderaEstadoOffline == 1) {
                        if (!txtnumorden.getText().equals("") && txtdocumento.getText().length() >= 4 && !txtnombreafiliado.getText().equals("")) {
                            if (id_obra_social == 58) {
                                estado_orden = 3;
                            } else {
                                estado_orden = 1;
                            }
                            System.out.println(id_practicas + "  " + n2);
                            /////////////////////grabo en servidor nuestro///////////////////////////////////////////////////////////////////////////
                            validar_orden obra_social_comun = new validar_orden();
                            respuesta = obra_social_comun.valida(
                                    Integer.valueOf(txtaño.getText() + txtmes.getText()),//periodo
                                    txtnombreafiliado.getText(),//nombre_afiliado
                                    txtdocumento.getText(),//dni_afiliado
                                    txtnumafiliado.getText(),//numero_afiliado
                                    Integer.valueOf(txtmatricula.getText()),//matricula_presc
                                    txtnumorden.getText(),//numero_orden
                                    txtfecha.getText(),//fecha_orden
                                    Double.valueOf(txttotal1.getText()),//total_orden
                                    fecha,//fecha_carga,
                                    hora,//hora_carga,
                                    ip2,//ip,
                                    id_obra_social,//id_obrasocial,
                                    id_usuario,//id_usuario,
                                    n2,// cantidad_practicas,
                                    cadena_practicas,//practicas
                                    Double.valueOf(txtcoseguro.getText()),//Double.valueOf(txtcoseguro.getText()),
                                    fecha,//fecha,
                                    tipo_orden,//tipo_orden,
                                    observacion,//observacion,
                                    plan_ss,//0,
                                    coseguro_ss,
                                    estado_orden,
                                    fechaDate);//0.00                        
                            if (respuesta == 0) {//en el caso se q no se grabe en nuestro servidor se anula del wsdl
                                bandera_obra_social_comun = 0;
                            } else {
                                bandera_obra_social_comun = 1;
                            }
                        } else {
                            cursor2();
                            JOptionPane.showMessageDialog(null, "Debe completar todos los datos obligatorios...");
                        }
                    }
                }
                cursor();
                periodo = txtaño.getText() + txtmes.getText();
                int p = 0;
                if (periodo_colegiado <= Integer.valueOf(periodo)) {
                    try {
                        String sSQL4 = "SELECT estado FROM periodos ";
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        ///System.out.println("bandera_jerarquicos = " + bandera_jerarquicos);
                        if (rs4.getBoolean("estado") == true
                                && bandera_osde == 1 && bandera_sancor == 1 && bandera_boreal == 1
                                && bandera_sw == 1 && bandera_subsidio == 1
                                && bandera_jerarquicos == 1 && bandera_obra_social_comun == 1
                                && bandera_iosfa == 1 && bandera_medife == 1 && banderaEstadoOffline == 1 && bandera_asunt == 1 && bandera_ospe == 1) {
                            if (!txtnombreafiliado.getText().equals("") && !txtdocumento.getText().equals("") && !txtnumafiliado.getText().equals("")) {
                                if (tablapracticas.getRowCount() != 0) {
                                    double total = 0.0, totalordenes = 0.0;
                                    String num_afiliado, dni_afiliado, fecha_orden, matricula_presc, mes, año, cod_practca, cod_fac_practca;
                                    String nombre_practca;
                                    int n2, n3, i;
                                    num_afiliado = txtnumafiliado.getText();
                                    dni_afiliado = txtdocumento.getText();
                                    fecha_orden = txtfecha.getText();
                                    matricula_presc = txtmatricula.getText();
                                    mes = txtmes.getText();
                                    año = txtaño.getText();
                                    coseguro = txtcoseguro.getText();
                                    if (banderamodifica == 0) {
                                        if (periodo_colegiado > Integer.valueOf(periodo)) {
                                            p = 1;////periodo cerrado
                                        }
                                        jLabel6.setEnabled(false);
                                        jLabel8.setEnabled(false);
                                        jLabel9.setEnabled(true);
                                        jLabel1.setEnabled(false);
                                        jLabel2.setEnabled(false);
                                        txtfecha.setEnabled(false);
                                        txtdocumento.setEnabled(false);
                                        txtnumorden.setText("");
                                        txtnombreafiliado.setEnabled(false);
                                        txtnumafiliado.setEnabled(false);
                                        System.out.println("obra: " + obra);
                                        if (!obra.equals("10070 - SWISS MEDICAL GROUP S.A. - ONLINE")
                                                && !obra.equals("3100 - OSDE")
                                                && !obra.equals("9000 - ASOCIACION MUTUAL SANCOR")
                                                && !obra.equals("512 - MEDIFE - ONLINE OBLIGATORIO PRE PAGA C.M.C.  S.A.")
                                                && !obra.equals("3102 - OSDE - OFFLINE")
                                                && !obra.equals("513 - MEDIFE - ONLINE VOLUNTARIO PRE PAGA C.M.C.  S.A.")) {
                                            tipo_orden = 1;
                                        }
                                        txtobrasocial.setEnabled(false);
                                        jLabel5.setEnabled(false);
                                        borrartabla();
                                        contadorPracticas = 0;
                                        txtfecha.setEnabled(true);
                                        txtfecha.setEditable(true);
                                        jLabel10.setEnabled(true);
                                        jLabel1.setEnabled(false);
                                        jLabel2.setEnabled(false);
                                        txtmes.setEnabled(false);
                                        txttotal1.setText("");
                                        txtaño.setEnabled(false);
                                        txtcoseguro.setText("0.00");
                                        txtcoseguro.setEnabled(false);
                                        jLabel24.setEnabled(false);
                                        txtfechacoseguro.setText("");
                                        txtfechacoseguro.setEnabled(false);
                                        chkcoseguro.setSelected(false);
                                        jLabel25.setEnabled(false);
                                        String matr = txtmatricula.getText();
                                        txtmatricula.select(0, matr.length());
                                        txtmatricula.requestFocus();
                                    } //////////////////////MODIFICAR ORDEN//////////////////////
                                    else {
                                        System.out.println("banderaEstadoOffline:" + banderaEstadoOffline);
                                        if (banderaEstadoOffline == 1) {
                                            //////////////////////////////////elimina detalle anterior///////////////////////////////////////////////me es mas facil ;)
                                            try {
                                                PreparedStatement pst3 = cn.prepareStatement("DELETE FROM detalle_ordenes WHERE id_orden ='" + id_orden + "'");
                                                pst3.execute();

                                            } catch (Exception e) {
                                                observacion = null;
                                                cursor2();
                                                JOptionPane.showMessageDialog(null, "Error en la base de datos...");
                                                JOptionPane.showMessageDialog(null, e);
                                            }
                                            //////////////////////////////////////////////////detalle de orden////////////////////
                                            try {
                                                i = 0;
                                                n2 = tablapracticas.getRowCount();
                                                if (n2 != 0) {
                                                    //   cargarorden();
                                                    while (i < n2) {

                                                        String SQL = "INSERT INTO detalle_ordenes(id_orden, cod_practica,nombre_practica,precio_practica,cod_practica_fac)"
                                                                + "VALUES(?,?,?,?,?)";
                                                        PreparedStatement st2 = cn.prepareStatement(SQL);
                                                        cod_practca = tablapracticas.getValueAt(i, 1).toString();
                                                        nombre_practca = tablapracticas.getValueAt(i, 2).toString();
                                                        total = Double.valueOf(tablapracticas.getValueAt(i, 3).toString());
                                                        cod_fac_practca = tablapracticas.getValueAt(i, 4).toString();
                                                        totalordenes = totalordenes + total;
                                                        st2.setInt(1, id_orden);
                                                        st2.setString(2, cod_practca);
                                                        st2.setString(3, nombre_practca);
                                                        st2.setDouble(4, Redondear(total));
                                                        st2.setString(5, cod_fac_practca);
                                                        n3 = st2.executeUpdate();
                                                        if (n3 > 0) {
                                                        }
                                                        i++;
                                                    }
                                                }
                                            } catch (SQLException ex) {
                                                observacion = null;
                                                cursor2();
                                                JOptionPane.showMessageDialog(null, ex);
                                            }
                                            ////////////////////////////ordenes////////////////////////////////////////////////////////
                                            String sSQL3 = "UPDATE ordenes SET periodo=?, id_obrasocial=?, nombre_afiliado=?, dni_afiliado=?, numero_afiliado=?,"
                                                    + "matricula_prescripcion=?, numero_orden=?,"
                                                    + "fecha_orden=? , id_colegiados=?, total=? WHERE id_orden=" + id_orden;
                                            PreparedStatement pst = cn.prepareStatement(sSQL3);
                                            pst.setString(1, año + mes);
                                            pst.setInt(2, id_obra_social);
                                            pst.setString(3, nom_afiliado);
                                            pst.setString(4, dni_afiliado);
                                            pst.setString(5, num_afiliado);
                                            pst.setString(6, matricula_presc);
                                            pst.setString(7, num_orden);
                                            pst.setString(8, fecha_orden);
                                            pst.setInt(9, id_usuario);
                                            pst.setDouble(10, Redondear(totalordenes));
                                            p = 3;
                                            int n4 = pst.executeUpdate();
                                            if (n4 > 0) {
                                                cursor2();
                                                JOptionPane.showMessageDialog(null, "Se modificó con exito la orden...");
                                            }
                                            btnborrar.doClick();
                                            banderamodifica = 0;
                                        }
                                    }
                                } else {
                                    cursor2();
                                    if (periodo_colegiado > Integer.valueOf(periodo)) {
                                        p = 1;////periodo cerrado
                                    }
                                    jLabel6.setEnabled(false);
                                    jLabel8.setEnabled(false);
                                    jLabel9.setEnabled(true);
                                    jLabel1.setEnabled(false);
                                    jLabel2.setEnabled(false);
                                    txtfecha.setEnabled(false);
                                    txtdocumento.setEnabled(false);
                                    txtnumorden.setText("");
                                    txtnombreafiliado.setEnabled(false);
                                    txtnumafiliado.setEnabled(false);
                                    System.out.println("obra: " + obra);
                                    if (!obra.equals("10070 - SWISS MEDICAL GROUP S.A. - ONLINE") && !obra.equals("3100 - OSDE") && !obra.equals("9000 - ASOCIACION MUTUAL SANCOR") && !obra.equals("512 - MEDIFE - ONLINE OBLIGATORIO PRE PAGA C.M.C.  S.A.") && !obra.equals("513 - MEDIFE - ONLINE VOLUNTARIO PRE PAGA C.M.C.  S.A.") && !obra.equals("3102 - OSDE - OFFLINE")) {
                                        tipo_orden = 1;
                                    }
                                    txtobrasocial.setEnabled(false);
                                    jLabel5.setEnabled(false);
                                    contadorPracticas = 0;
                                    jLabel10.setEnabled(true);
                                    jLabel1.setEnabled(false);
                                    jLabel2.setEnabled(false);
                                    txtmes.setEnabled(false);
                                    txttotal1.setText("");
                                    txtaño.setEnabled(false);
                                    txtcoseguro.setText("0.00");
                                    txtcoseguro.setEnabled(false);
                                    jLabel24.setEnabled(false);
                                    txtfechacoseguro.setText("");
                                    txtfechacoseguro.setEnabled(false);
                                    chkcoseguro.setSelected(false);
                                    jLabel25.setEnabled(false);
                                    String matr = txtmatricula.getText();
                                    txtmatricula.select(0, matr.length());
                                    txtmatricula.requestFocus();
                                }
                            } else {
                                cursor2();
                                JOptionPane.showMessageDialog(null, "Debe ingresar datos sobre el afiliado");
                            }
                        } else {
                            cursor2();
                            JOptionPane.showMessageDialog(null, "No se pudo grabar la orden...");
                        }
                        cn.close();
                    } catch (Exception e) {
                        observacion = null;
                        cursor2();
                        JOptionPane.showMessageDialog(null, e);
                        JOptionPane.showMessageDialog(null, "Error en la base de datos");
                    }
                }
                if (p == 1) {
                    cursor2();
                    JOptionPane.showMessageDialog(null, "El periodo ya fue finalizado...");
                    jLabel6.setEnabled(false);
                    jLabel8.setEnabled(false);
                    jLabel9.setEnabled(true);
                    jLabel1.setEnabled(false);
                    jLabel2.setEnabled(false);
                    txtfecha.setEnabled(false);
                    String matr = txtmatricula.getText();
                    txtmatricula.select(0, matr.length());
                    txtdocumento.setEnabled(false);
                    txtnumorden.setText("");
                    txtnombreafiliado.setEnabled(false);
                    txtnumafiliado.setEnabled(false);
                    System.out.println("obra: " + obra);
                    if (!obra.equals("10070 - SWISS MEDICAL GROUP S.A. - ONLINE") && !obra.equals("3100 - OSDE") && !obra.equals("9000 - ASOCIACION MUTUAL SANCOR") && !obra.equals("512 - MEDIFE - ONLINE OBLIGATORIO PRE PAGA C.M.C.  S.A.") && !obra.equals("513 - MEDIFE - ONLINE VOLUNTARIO PRE PAGA C.M.C.  S.A.") && !obra.equals("3102 - OSDE - OFFLINE")) {//3102 - OSDE - OFFLINE
                        tipo_orden = 1;
                    }
                    txtobrasocial.setEnabled(false);
                    jLabel5.setEnabled(false);
                    txttotal1.setText("");
                    borrartabla();
                    txtmes.setEnabled(true);
                    txtaño.setEnabled(true);
                    txtmes.setEditable(true);
                    txtaño.setEditable(true);
                    txtmes.requestFocus();
                    cargarperiodo();
                    txtfecha.setEnabled(true);
                    txtfecha.setEditable(true);
                    jLabel10.setEnabled(true);
                    txtcoseguro.setText("0.00");
                    txtcoseguro.setEnabled(false);
                    jLabel24.setEnabled(false);
                    txtfechacoseguro.setText("");
                    txtfechacoseguro.setEnabled(false);
                    chkcoseguro.setSelected(false);
                    jLabel25.setEnabled(false);
                    contadorPracticas = 0;
                }
                if (p == 2) {
                    cursor2();
                    JOptionPane.showMessageDialog(null, "Todavía no cerró el período vigente...");
                    jLabel6.setEnabled(false);
                    //  jLabel18.setEnabled(false);
                    jLabel8.setEnabled(false);
                    jLabel9.setEnabled(true);
                    jLabel1.setEnabled(false);
                    jLabel2.setEnabled(false);
                    txtfecha.setEnabled(false);
                    String matr = txtmatricula.getText();
                    txtmatricula.select(0, matr.length());
                    txtdocumento.setEnabled(false);
                    txtnumorden.setText("");
                    txttotal1.setText("");
                    txtnombreafiliado.setEnabled(false);
                    txtnumafiliado.setEnabled(false);
                    System.out.println("obra: " + obra);
                    if (!obra.equals("10070 - SWISS MEDICAL GROUP S.A. - ONLINE") && !obra.equals("3100 - OSDE") && !obra.equals("9000 - ASOCIACION MUTUAL SANCOR") && !obra.equals("512 - MEDIFE - ONLINE OBLIGATORIO PRE PAGA C.M.C.  S.A.") && !obra.equals("513 - MEDIFE - ONLINE VOLUNTARIO PRE PAGA C.M.C.  S.A.") && !obra.equals("3102 - OSDE - OFFLINE")) {
                        tipo_orden = 1;
                    }
                    txtobrasocial.setEnabled(false);
                    jLabel5.setEnabled(false);
                    borrartabla();
                    txtmes.setEnabled(true);
                    txtaño.setEnabled(true);
                    txtmes.setEditable(true);
                    txtaño.setEditable(true);
                    txtmes.requestFocus();
                    // cargarperiodo();
                    txtfecha.setEnabled(true);
                    txtfecha.setEditable(true);
                    jLabel10.setEnabled(true);
                    txtcoseguro.setText("0.00");
                    txtcoseguro.setEnabled(false);
                    jLabel24.setEnabled(false);
                    txtfechacoseguro.setText("");
                    txtfechacoseguro.setEnabled(false);
                    chkcoseguro.setSelected(false);
                    jLabel25.setEnabled(false);
                    contadorPracticas = 0;
                }

            } else {
                cursor2();
                JOptionPane.showMessageDialog(null, "Error al intentar ingresar la orden");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar al menos una práctica...");
        }

        observacion = null;
        cursor2();
        btnaceptar.setEnabled(true);
    }//GEN-LAST:event_btnaceptarActionPerformed

    String completarceros(String v, int d
    ) {
        String ceros = "";
        if (v.length() < d) {
            for (int i = v.length(); i < d; i++) {
                ceros += "0";
            }
            v = ceros + v;
        }
        return v;
    }

    private void tablapracticasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablapracticasKeyPressed
        DefaultTableModel temp = (DefaultTableModel) tablapracticas.getModel();
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            tablapracticas.transferFocus();
            evt.consume();
        }

        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            if (tablapracticas.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "No seleccionó ninguna fila...");
            } else {
                //  if(){
                temp.removeRow(tablapracticas.getSelectedRow());
                cargartotalpracticas();
                //  }else{
//                <Mensaje><EncabezadoMensaje><VersionMsj>1.0</VersionMsj><NroReferenciaCancel>000098351394</NroReferenciaCancel><TipoTransaccion>04A</TipoTransaccion><IdMsj>3354</IdMsj><InicioTrx><FechaTrx>20091005</FechaTrx><HoraTrx>193020</HoraTrx></InicioTrx><Financiador><CodigoFinanciador>11</CodigoFinanciador><CuitFinanciador>30546741253</CuitFinanciador></Financiador><Prestador><CuitPrestador>30708402911</CuitPrestador></Prestador></EncabezadoMensaje><EncabezadoAtencion><Efector/><Prescriptor/><Credencial><NumeroCredencial>60671956201</NumeroCredencial></Credencial><Atencion><FechaAtencion>20091001</FechaAtencion></Atencion></EncabezadoAtencion></Mensaje>
                // }
            }
        }

    }//GEN-LAST:event_tablapracticasKeyPressed

    void borrartabla() {
        DefaultTableModel temp = (DefaultTableModel) tablapracticas.getModel();
        int a = temp.getRowCount() - 1;  //Índices van de 0 a n-1
        for (int i = a; i >= 0; i--) {
            temp.removeRow(i);
        }
    }

    void borrartablaordenes() {
        DefaultTableModel temp = (DefaultTableModel) tablaordenes.getModel();
        int a = temp.getRowCount() - 1;  //Índices van de 0 a n-1
        for (int i = a; i >= 0; i--) {
            temp.removeRow(i);
        }
    }

    void borrartablaimportar() {
        DefaultTableModel temp = (DefaultTableModel) tablaordenes1.getModel();
        int a = temp.getRowCount() - 1;  //Índices van de 0 a n-1
        for (int i = a; i >= 0; i--) {
            temp.removeRow(i);
        }
    }

    void cargarfecha() {

        //SimpleDateFormat formatoTiempo = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat formatoTiempo = new SimpleDateFormat("HHmmss");
        java.util.Date currentDate1 = new java.util.Date();
        GregorianCalendar calendar1 = new GregorianCalendar();
        calendar1.setTime(currentDate1);
        hora = formatoTiempo.format(currentDate1);
        /////////////////////////////////////
        //SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        java.util.Date currentDate = new java.util.Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);
        fechaMySql = formato.format(currentDate);
    }

    public class apiSwLogin {

        public String sendPost() throws Exception {
            String uriLogin = "https://mobile.swissmedical.com.ar/pre/api-smg/v0/auth-login";
            String jsonString;
            Gson gsonEnvio = new Gson();
            Gson gsonRespuesta = new Gson();
            Gson gsonError = new Gson();
            String resJson = "";
            String respuestaToken = "0";
            try {
                System.out.println("/////////////////////////////// LOGIN   /////////////////////////////////////////////////////////////////////////////////////////////////////");
                //Creamos el cliente de conexión al API Restful
                Client client = ClientBuilder.newClient();

                //Creamos el target lo cuál es nuestra URL junto con el nombre del método a llamar
                WebTarget targetLogin = client.target(uriLogin);

                //Creamos nuestra solicitud que realizará el request
                Invocation.Builder solicitud = targetLogin.request();

                //Creamos y llenamos nuestro objeto BaseReq con los datos que solicita el API
                ClienteSwissMedicalApi.Login req = new ClienteSwissMedicalApi.Login();
                req.setApiKey("06715fdb87c4adb2c176");
                req.setUsrLoginName("hl7ApiUser");
                req.setPassword("Swiss1234");
                req.setCuit("30522483881");
                Device dev = new Device();
                dev.setMessagingid(id_usuario + "B" + hora);
                dev.setDeviceid(ipLocal);
                dev.setDevicename(hostLocal);
                dev.setBloqueado(0);
                dev.setRecordar(0);
                req.setDevice(dev);
                // sdfsdfsdfsd
                //Convertimos el objeto req a un json
                jsonString = gsonEnvio.toJson(req);
                System.out.println(jsonString);

                //Enviamos nuestro json vía post al API Restful
                Response post = solicitud.post(Entity.json(jsonString));

                //Recibimos la respuesta y la leemos en una clase de tipo String, en caso de que el json sea tipo json y no string, debemos usar la clase de tipo JsonObject.class en lugar de String.class
                String responseJson = post.readEntity(String.class);
                resJson = responseJson;

                //Imprimimos el status de la solicitud
                System.out.println("Estatus: " + post.getStatus());

                switch (post.getStatus()) {
                    case 200:
                        resJson = responseJson;
                        System.out.println("/////////////////////////////// Respuesta Login   /////////////////////////////////////////////////////////////////////////////////////////////////////");
                        //Imprimimos la respuesta del API Restful
                        System.out.println(resJson);
                        LoginResponse respuestaLogin = gsonRespuesta.fromJson(resJson, LoginResponse.class);
                        respuestaLogin.muestraRespuesta();
                        respuestaToken = respuestaLogin.getToken();
                        break;
                    default:
                        resJson = "Error";
                        LoginError ErrorLogin = gsonError.fromJson(resJson, LoginError.class);
                        ErrorLogin.mostrarError();
                        respuestaToken = "0";
                        break;
                }
                ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            } catch (Exception e) {
                //En caso de un error en la solicitud, llenaremos res con la exceptión para verificar que sucedió
                resJson = e.toString();
            }
            return respuestaToken;
        }
    }

    public class apiSwPracticas {

        private final String USER_AGENT = "Mozilla/5.0";
        int estado_sw = 0;

        // HTTP GET request
        public int sendPost() throws Exception {

            BufferedReader in = null;
            String resJson = "";
            try {

                String uriRegistracion = "https://mobile.swissmedical.com.ar/pre/api-smg/v1.0/prestadores/hl7/registracion";

                String jsonString;
                Gson gsonEnvio = new Gson();
                Gson gsonRespuesta = new Gson();
                Gson gsonError = new Gson();
                System.out.println("/////////////////////////////// Registracion   /////////////////////////////////////////////////////////////////////////////////////////////////////");
                //Proceso de registracion de prestaciones
                //Creamos el target lo cuál es nuestra URL junto con el nombre del método a llamar
                Client client = ClientBuilder.newClient();
                WebTarget targetPrestacion = client.target(uriRegistracion);

                //Creamos nuestra solicitud que realizará el request
                Invocation.Builder solicitudPrestacion = targetPrestacion.request();

                //Creamos y llenamos nuestro objeto BaseReq con los datos que solicita el API
                Registracion practicas = new Registracion();
                practicas.setCreden(SwissAfiliado.Codigo_afiliado + "|" + CSC_SW);
                practicas.setAlta(fechaMySql);
                practicas.setFecdif(fechaMySql);
                practicas.setManual("0");
                practicas.setTicketExt(0);
                practicas.setInterNro(2);
                practicas.setAutoriz(0);
                practicas.setRechaExt(0);
                practicas.setParam1(mensajepractica);
                practicas.setParam2("");
                practicas.setParam3("");
                practicas.setTipoEfector("CUIT");
                practicas.setIdEfector(cuit);
                practicas.setTipoPrescr("Matricula");
                practicas.setIdPrescr(txtmatricula.getText());

                //Convertimos el objeto req a un json
                jsonString = gsonEnvio.toJson(practicas);
                System.out.println(jsonString);
                Object Type = "application/json";
                Object Accept = "application/json";
                //Enviamos nuestro json vía post al API Restful
                solicitudPrestacion.header("Content-Type", Type).head();
                solicitudPrestacion.header("Accept", Accept).head();
                solicitudPrestacion.header("Authorization", apiKey).head();

                Response postR = solicitudPrestacion.post(Entity.json(jsonString));

                //Recibimos la respuesta y la leemos en una clase de tipo String, en caso de que el json sea tipo json y no string, debemos usar la clase de tipo JsonObject.class en lugar de String.class
                String responseJsonRegistracion = postR.readEntity(String.class);
                resJson = responseJsonRegistracion;

                //Imprimimos el status de la solicitud
                System.out.println("Estatus Registracion: " + postR.getStatus());

                switch (postR.getStatus()) {
                    case 200:
                        resJson = responseJsonRegistracion;
                        System.out.println("/////////////////////////////// Respuesta Registracion   /////////////////////////////////////////////////////////////////////////////////////////////////////");
                        //Imprimimos la respuesta del API Restful
                        System.out.println(resJson);
                        RegistracionResponse respuestaRegistracion = gsonRespuesta.fromJson(resJson, RegistracionResponse.class);
                        respuestaRegistracion.muestraRespuesta();
                        if (respuestaRegistracion.getCabecera().getRechaCabecera().equals("0")) {
                            estado_sw = Integer.valueOf(respuestaRegistracion.getCabecera().getTransac());
                            System.out.println("estado_sw" + estado_sw);
                        } else {
                            estado_sw = 0;
                            observacion = respuestaRegistracion.getCabecera().getRechaCabeDeno() + respuestaRegistracion.getDetalle();
                            System.out.println("observacion" + observacion);
                        }
                        break;
                    default:
                        resJson = "Error";
                        System.out.println("Error de Registracion");
                        estado_sw = 0;
                        observacion = postR.getStatusInfo().toString() + ": " + postR.getStatus();
                        System.out.println("estado_sw:" + estado_sw + " observacion:" + observacion);
                    /// break;
                }
                System.out.println("termina registracion");
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            } catch (Exception e) {
                //En caso de un error en la solicitud, llenaremos res con la exceptión para verificar que sucedió
                resJson = e.toString();
                System.out.println("Exception" + e);
                estado_sw = 0;
            }
            return estado_sw;
        }
    }

    public class HttpOsdePractica {

        private final String USER_AGENT = "Mozilla/5.0";

        // HTTP GET request
        public void sendGet() {
            BufferedReader in = null;

            try {
                String urlString = "http://ws.itcsoluciones.com:48080/jSitelServlet/Do?" + "pas=" + URLEncoder.encode("bda221f8-a7e3-11e4-b085-000c29a675b5", "UTF-8") + "&msj=" + URLEncoder.encode(mensajepractica, "UTF-8");

                URL obj = new URL(urlString);////////////////////////////////////////////////////////////////////////bda221f8-a7e3-11e4-b085-000c29a675b5
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                // optional default is GET
                con.setRequestMethod("GET");

                //add request header
                con.setRequestProperty("User-Agent", USER_AGENT);

                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'GET' request to URL : " + urlString);
                System.out.println("Response Code : " + responseCode);

                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                //print result
                System.out.println(response.toString());
                respuestapractica = response.toString();
            } catch (IOException e) {
                System.out.println("Error " + e);
                respuestapractica = e.getMessage();
            } finally {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public class HttpBorealPractica {

        private final String USER_AGENT = "Mozilla/5.0";

        // HTTP GET request
        public void sendGet() throws Exception {
            BufferedReader in = null;
            try {
                String urlString = "http://sistemasboreal.com.ar:5480/WsBoreal/servlet/awsboreal?wsdl" + URLEncoder.encode(mensajepractica, "UTF-8");

                URL obj = new URL(urlString);////////////////////////////////////////////////////////////////////////bda221f8-a7e3-11e4-b085-000c29a675b5
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                // optional default is GET
                con.setRequestMethod("GET");

                //add request header
                con.setRequestProperty("User-Agent", USER_AGENT);

                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'GET' request to URL : " + urlString);
                System.out.println("Response Code : " + responseCode);

                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                //print result
                System.out.println(response.toString());
                respuestapractica = response.toString();
            } catch (IOException e) {
                System.out.println("Error " + e);
                respuestapractica = e.getMessage();
            } finally {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public class HttpOsdeAnulacion {

        private final String USER_AGENT = "Mozilla/5.0";

        // HTTP GET request
        public void sendGet() throws Exception {
            BufferedReader in = null;
            String urlString = null;
            String inputLine = null;
            try {
                urlString = "http://ws.itcsoluciones.com:48080/jSitelServlet/Do?" + "pas=" + URLEncoder.encode("bda221f8-a7e3-11e4-b085-000c29a675b5", "UTF-8") + "&msj=" + URLEncoder.encode(mensajeanulacion, "UTF-8");

                URL obj = new URL(urlString);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                // optional default is GET
                con.setRequestMethod("GET");

                //add request header
                con.setRequestProperty("User-Agent", USER_AGENT);

                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'GET' request to URL : " + urlString);
                System.out.println("Response Code : " + responseCode);

                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
//                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                System.out.println(response.toString());
                respuestaanulacion = response.toString();
            } catch (IOException e) {
                System.out.println("Error " + e);
                respuestaanulacion = e.getMessage();
            } finally {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public class apiSwAnulacion {

        private final String USER_AGENT = "Mozilla/5.0";
        String resJson = "";
        int estadoSw = 0;

        // HTTP GET request
        public int sendPost(String numeroTransaccion, String numeroCredecial, String token) throws Exception {
            BufferedReader in = null;
            String uriCancelacion = null;
            String jsonString;
            Gson gsonEnvio = new Gson();
            Gson gsonRespuesta = new Gson();
            Gson gsonError = new Gson();
            try {
                uriCancelacion = "https://mobile.swissmedical.com.ar/pre/api-smg/v1.1/prestadores/hl7/cancela-prestacion";
                Client client = ClientBuilder.newClient();
                System.out.println("/////////////////////////////// Cancelacion   /////////////////////////////////////////////////////////////////////////////////////////////////////");
                WebTarget targetCancelacion = client.target(uriCancelacion);
                //Creamos nuestra solicitud que realizará el request
                Invocation.Builder solicitudCancelacion = targetCancelacion.request();
                //Creamos y llenamos nuestro objeto BaseReq con los datos que solicita el API
                Cancelacion anula = new Cancelacion();
                anula.setCreden(numeroCredecial);
                anula.setAlta(fechaMySql);
                anula.setTicketExt(numeroTransaccion);
                anula.setParam1("0");
                //Convertimos el objeto req a un json
                jsonString = gsonEnvio.toJson(anula);
                System.out.println(jsonString);
                //Enviamos nuestro json vía post al API Restful
                String Type = "application/json";
                String Accept = "application/json";
                solicitudCancelacion.header("Content-Type", Type).head();
                solicitudCancelacion.header("Accept", Accept).head();
                solicitudCancelacion.header("Authorization", token).head();
                Response postC = solicitudCancelacion.post(Entity.json(jsonString));
                //Recibimos la respuesta y la leemos en una clase de tipo String, en caso de que el json sea tipo json y no string, debemos usar la clase de tipo JsonObject.class en lugar de String.class
                String responseJsonCancelacion = postC.readEntity(String.class);
                resJson = responseJsonCancelacion;
                //Imprimimos el status de la solicitud
                System.out.println("Estatus Cancelacion: " + postC.getStatus());
                switch (postC.getStatus()) {
                    case 200:
                        resJson = responseJsonCancelacion;
                        CancelacionResponse respuestaCancelacion = gsonRespuesta.fromJson(resJson, CancelacionResponse.class);
                        respuestaCancelacion.muestraRespuesta();
                        if (respuestaCancelacion.getCabecera().getRechaCabecera().equals("0")) {
                            estadoSw = Integer.valueOf(respuestaCancelacion.getCabecera().getTransac());
                            System.out.println("estado_sw" + estadoSw);
                        } else {
                            estadoSw = 0;
                            observacion = respuestaCancelacion.getCabecera().getRechaCabeDeno() + respuestaCancelacion.getDetalle();
                            System.out.println("observacion" + observacion);
                        }
                        break;
                    default:
                        resJson = "Error";
                        System.out.println("Error de Registracion de cancelacion");
                        estadoSw = 0;
                        observacion = postC.getStatusInfo().toString() + ": " + postC.getStatus();
                        System.out.println("estado_sw:" + estadoSw + " observacion:" + observacion);
                }
                System.out.println("/////////////////////////////// Respuesta Cancelacion   /////////////////////////////////////////////////////////////////////////////////////////////////////");
                //Imprimimos la respuesta del API Restful
                System.out.println(resJson);
            } catch (Exception e) {
                //En caso de un error en la solicitud, llenaremos res con la exceptión para verificar que sucedió
                resJson = e.toString();
                System.out.println("Exception" + e);
                estadoSw = 0;
            }
            return estadoSw;
        }
    }


    private void txtnombreafiliadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreafiliadoActionPerformed
        txtnombreafiliado.transferFocus();
    }//GEN-LAST:event_txtnombreafiliadoActionPerformed

    private void txtobrasocialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtobrasocialKeyReleased
        if (txtobrasocial.getText().equals("")) {
            deshabilitarpanel1();
            cargarperiodo();
            txtobrasocial.requestFocus();
        }
    }//GEN-LAST:event_txtobrasocialKeyReleased

    private void txtpracticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpracticaActionPerformed
        if (obra.equals("1805 - SUBSIDIO DE SALUD - ONLINE")) {
            if (tablapracticas.getRowCount() <= 12) {
                if (!txtpractica.getText().equals("+")) {
                    DefaultTableModel temp = (DefaultTableModel) tablapracticas.getModel();
                    String cod = "", nom = "", cadena = txtpractica.getText();
                    int n = tablapracticas.getRowCount();
                    int i = 0, j = 1;
                    ///cod/// 0000 - aaaaaaaaa
                    while (i < cadena.length()) {
                        if (String.valueOf(cadena.charAt(i)).equals("-")) {
                            j = i + 2;
                            i = cadena.length();
                        } else {
                            cod = cod + cadena.charAt(i);
                        }
                        i++;
                    }
                    ///nom/// 0000 - aaaaaaaaa
                    while (j < cadena.length()) {
                        nom = nom + cadena.charAt(j);
                        j++;
                    }
                    ////////////////////////////////////
                    int band = 0;
                    if (!cadena.equals("")) {
                        i = 0;
                        int fila = 0;
                        while (i < contadorj) {
                            if (cadena.equals(practica[i])) {
                                if (n != 0) {
                                    fila = n;
                                    Object nuevo[] = {
                                        fila + 1, "", ""};
                                    temp.addRow(nuevo);
                                    tablapracticas.setValueAt(cod.substring(0, 6), fila, 1);
                                    tablapracticas.setValueAt(nom, fila, 2);
                                    tablapracticas.setValueAt(preciopractica[i], fila, 3);
                                    tablapracticas.setValueAt(codfacpractica[i], fila, 4);
                                    tablapracticas.setValueAt(idpractica[i], fila, 5);
                                } else {
                                    Object nuevo[] = {
                                        "1", "", ""};
                                    temp.addRow(nuevo);
                                    tablapracticas.setValueAt(cod.substring(0, 6), 0, 1);
                                    tablapracticas.setValueAt(nom, 0, 2);
                                    tablapracticas.setValueAt(preciopractica[i], 0, 3);
                                    tablapracticas.setValueAt(codfacpractica[i], fila, 4);
                                    tablapracticas.setValueAt(idpractica[i], fila, 5);

                                    if (codfacpractica[i].equals("668298")) {
                                        JOptionPane.showMessageDialog(this, "Modulo no Aceptado, debe cargar el codigo 660174 - Colesterol Total");
                                    }
                                }
                                band = 1;
                            }
                            i++;
                            tablapracticas.getColumnModel().getColumn(0).setPreferredWidth(10);
                            tablapracticas.getColumnModel().getColumn(1).setPreferredWidth(10);
                            tablapracticas.getColumnModel().getColumn(2).setPreferredWidth(300);
                            tablapracticas.getColumnModel().getColumn(3).setPreferredWidth(10);
                            ///////Ultima Fila///////
                            tablapracticas.getColumnModel().getColumn(4).setMaxWidth(0);
                            tablapracticas.getColumnModel().getColumn(4).setMinWidth(0);
                            tablapracticas.getColumnModel().getColumn(4).setPreferredWidth(0);
                            ///////////////////////////////////////////////////////////////////
                            tablapracticas.getColumnModel().getColumn(5).setMaxWidth(0);
                            tablapracticas.getColumnModel().getColumn(5).setMinWidth(0);
                            tablapracticas.getColumnModel().getColumn(5).setPreferredWidth(0);
                            Rectangle r = tablapracticas.getCellRect(tablapracticas.getRowCount() - 1, 0, true);
                            tablapracticas.scrollRectToVisible(r);
                            tablapracticas.getSelectionModel().setSelectionInterval(tablapracticas.getRowCount() - 1, tablapracticas.getRowCount() - 1);
                            //////////////////////////
                            cargartotalpracticas();
                        }
                        if (band == 0) {
                            JOptionPane.showMessageDialog(null, "La practica no es aceptada por la Obra Social...");
                            txtpractica.requestFocus();
                        }
                    }

                    txtpractica.setText("");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Superó el límite permitido");
            }
        } else {
            if (obra.equals("3100 - OSDE") || obra.equals("3101 - OSDE  ( RESPONSABLES INSCRIPTOS)") || obra.equals("3102 - OSDE - OFFLINE")) {
                if (tablapracticas.getRowCount() <= 24) {
                    if (!txtpractica.getText().equals("+")) {
                        DefaultTableModel temp = (DefaultTableModel) tablapracticas.getModel();
                        String cod = "", nom = "", cadena = txtpractica.getText();
                        int n = tablapracticas.getRowCount();
                        int i = 0, j = 1;
                        ///cod/// 000000 - aaaaaaaaa
                        while (i < cadena.length()) {
                            if (String.valueOf(cadena.charAt(i)).equals("-")) {
                                j = i + 2;
                                i = cadena.length();
                            } else {
                                cod = cod + cadena.charAt(i);
                            }
                            i++;
                        }
                        ///nom/// 000000 - aaaaaaaaa
                        while (j < cadena.length()) {
                            nom = nom + cadena.charAt(j);
                            j++;
                        }
                        ////////////////////////////////////
                        int band = 0;
                        if (!cadena.equals("")) {
                            i = 0;
                            int fila = 0;
                            while (i < contadorj) {
                                if (cadena.equals(practica[i])) {
                                    if (tipo_orden == 1) {
                                        if (!cod.substring(0, 6).equals("661001")) {
                                            if (n != 0) {
                                                fila = n;
                                                Object nuevo[] = {
                                                    fila + 1, "", ""};
                                                temp.addRow(nuevo);
                                                tablapracticas.setValueAt(cod.substring(0, 6), fila, 1);
                                                tablapracticas.setValueAt(nom, fila, 2);
                                                tablapracticas.setValueAt(preciopractica[i], fila, 3);
                                                tablapracticas.setValueAt(codfacpractica[i], fila, 4);
                                                tablapracticas.setValueAt(idpractica[i], fila, 5);
                                            } else {
                                                Object nuevo[] = {
                                                    "1", "", ""};
                                                temp.addRow(nuevo);
                                                tablapracticas.setValueAt(cod.substring(0, 6), 0, 1);
                                                tablapracticas.setValueAt(nom, 0, 2);
                                                tablapracticas.setValueAt(preciopractica[i], 0, 3);
                                                tablapracticas.setValueAt(codfacpractica[i], fila, 4);
                                                tablapracticas.setValueAt(idpractica[i], fila, 5);
                                            }
                                            band = 1;
                                        } else {
                                            band = 2;
                                            JOptionPane.showMessageDialog(null, "Esta practica no puede ser ingresada en una orden ambulatoria");
                                        }
                                    }
                                    if (tipo_orden == 3) {
                                        if (!cod.substring(0, 6).equals("660001")) {
                                            if (n != 0) {
                                                fila = n;
                                                Object nuevo[] = {
                                                    fila + 1, "", ""};
                                                temp.addRow(nuevo);
                                                tablapracticas.setValueAt(cod.substring(0, 6), fila, 1);
                                                tablapracticas.setValueAt(nom, fila, 2);
                                                tablapracticas.setValueAt(preciopractica[i], fila, 3);
                                                tablapracticas.setValueAt(codfacpractica[i], fila, 4);
                                                tablapracticas.setValueAt(idpractica[i], fila, 5);
                                            } else {
                                                Object nuevo[] = {
                                                    "1", "", ""};
                                                temp.addRow(nuevo);
                                                tablapracticas.setValueAt(cod.substring(0, 6), 0, 1);
                                                tablapracticas.setValueAt(nom, 0, 2);
                                                tablapracticas.setValueAt(preciopractica[i], 0, 3);
                                                tablapracticas.setValueAt(codfacpractica[i], fila, 4);
                                                tablapracticas.setValueAt(idpractica[i], fila, 5);
                                            }
                                            band = 1;
                                        } else {
                                            band = 2;
                                            JOptionPane.showMessageDialog(null, "Esta practica no puede ser ingresada en una orden de internnación");
                                        }
                                    }
                                    if (tipo_orden == 4) {
                                        if (!cod.substring(0, 6).equals("661001")) {
                                            if (n != 0) {
                                                fila = n;
                                                Object nuevo[] = {
                                                    fila + 1, "", ""};
                                                temp.addRow(nuevo);
                                                tablapracticas.setValueAt(cod.substring(0, 6), fila, 1);
                                                tablapracticas.setValueAt(nom, fila, 2);
                                                tablapracticas.setValueAt(preciopractica[i], fila, 3);
                                                tablapracticas.setValueAt(codfacpractica[i], fila, 4);
                                                tablapracticas.setValueAt(idpractica[i], fila, 5);
                                            } else {
                                                Object nuevo[] = {
                                                    "1", "", ""};
                                                temp.addRow(nuevo);
                                                tablapracticas.setValueAt(cod.substring(0, 6), 0, 1);
                                                tablapracticas.setValueAt(nom, 0, 2);
                                                tablapracticas.setValueAt(preciopractica[i], 0, 3);
                                                tablapracticas.setValueAt(codfacpractica[i], fila, 4);
                                                tablapracticas.setValueAt(idpractica[i], fila, 5);
                                            }
                                            band = 1;
                                        } else {
                                            band = 2;
                                            JOptionPane.showMessageDialog(null, "Esta practica no puede ser ingresada en una orden domiciliaria");
                                        }
                                    }
                                }
                                i++;
                                tablapracticas.getColumnModel().getColumn(0).setPreferredWidth(10);
                                tablapracticas.getColumnModel().getColumn(1).setPreferredWidth(10);
                                tablapracticas.getColumnModel().getColumn(2).setPreferredWidth(300);
                                tablapracticas.getColumnModel().getColumn(3).setPreferredWidth(10);
                                ///////Ultima Fila///////
                                tablapracticas.getColumnModel().getColumn(4).setMaxWidth(0);
                                tablapracticas.getColumnModel().getColumn(4).setMinWidth(0);
                                tablapracticas.getColumnModel().getColumn(4).setPreferredWidth(0);
                                ///////////////////////////////////////////////////////////////////
                                tablapracticas.getColumnModel().getColumn(5).setMaxWidth(0);
                                tablapracticas.getColumnModel().getColumn(5).setMinWidth(0);
                                tablapracticas.getColumnModel().getColumn(5).setPreferredWidth(0);
                                Rectangle r = tablapracticas.getCellRect(tablapracticas.getRowCount() - 1, 0, true);
                                tablapracticas.scrollRectToVisible(r);
                                tablapracticas.getSelectionModel().setSelectionInterval(tablapracticas.getRowCount() - 1, tablapracticas.getRowCount() - 1);
                                //////////////////////////
                                cargartotalpracticas();
                            }
                            if (band == 0) {
                                JOptionPane.showMessageDialog(null, "La practica no es aceptada por la Obra Social...");
                                txtpractica.requestFocus();
                            }
                        }

                        txtpractica.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Superó el límite permitido");
                }
            } else if (id_obra_social == 58) {

                DefaultTableModel temp = (DefaultTableModel) tablapracticas.getModel();
                String cod = "", nom = "", cadena = txtpractica.getText();
                int n = tablapracticas.getRowCount();
                int i = 0, j = 1;
                ///cod/// 0000 - aaaaaaaaa
                while (i < cadena.length()) {
                    if (String.valueOf(cadena.charAt(i)).equals("-")) {
                        j = i + 2;
                        i = cadena.length();
                    } else {
                        cod = cod + cadena.charAt(i);
                    }
                    i++;
                }
                ///nom/// 0000 - aaaaaaaaa
                while (j < cadena.length()) {
                    nom = nom + cadena.charAt(j);
                    j++;
                }

                int band = 0;

                if (cbotipo.getSelectedItem().equals("Orden Médica Electrónica")) {
                    if (!cadena.equals("")) {
                        i = 0;
                        int fila = 0;

                        while (i < contadorj) {
                            if (cadena.equals(practica[i])) {
                                /* if (n != 0) {
                                    fila = n;
                                } else {
                                    fila = 0;
                                }*/
                                Object nuevo[] = {
                                    n + 1, cod.substring(0, 6), nom, preciopractica[i], codfacpractica[i], idpractica[i]};
                                temp.addRow(nuevo);
                                /* tablapracticas.setValueAt(cod.substring(0, 6), fila, 1);
                                tablapracticas.setValueAt(nom, fila, 2);
                                tablapracticas.setValueAt(preciopractica[i], fila, 3);
                                tablapracticas.setValueAt(codfacpractica[i], fila, 4);
                                tablapracticas.setValueAt(idpractica[i], fila, 5);*/
                                band = 1;

                            }
                            i++;

                            tablapracticas.getColumnModel().getColumn(0).setPreferredWidth(10);
                            tablapracticas.getColumnModel().getColumn(1).setPreferredWidth(20);
                            tablapracticas.getColumnModel().getColumn(2).setPreferredWidth(400);
                            tablapracticas.getColumnModel().getColumn(3).setPreferredWidth(20);

                            tablapracticas.getColumnModel().getColumn(4).setPreferredWidth(0);
                            tablapracticas.getColumnModel().getColumn(5).setPreferredWidth(0);
                            tablapracticas.getColumnModel().getColumn(4).setMaxWidth(0);
                            tablapracticas.getColumnModel().getColumn(5).setMaxWidth(0);
                            tablapracticas.getColumnModel().getColumn(4).setMinWidth(0);
                            tablapracticas.getColumnModel().getColumn(5).setMinWidth(0);

                            alinear();
                            tablapracticas.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
                            tablapracticas.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
                            tablapracticas.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
                            tablapracticas.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);

                            Rectangle r = tablapracticas.getCellRect(tablapracticas.getRowCount() - 1, 0, true);
                            tablapracticas.scrollRectToVisible(r);
                            tablapracticas.getSelectionModel().setSelectionInterval(tablapracticas.getRowCount() - 1, tablapracticas.getRowCount() - 1);
                            cargartotalpracticas();
                        }
                        if (band == 0) {
                            JOptionPane.showMessageDialog(null, "La practica no es aceptada por la Obra Social...");
                            txtpractica.requestFocus();
                        }

                    }
                } else if (cbotipo.getSelectedItem().equals("Bono Especialista")) {
                    if (tablapracticas.getRowCount() <= 2) {
                        if (!cadena.equals("")) {
                            i = 0;
                            int fila = 0;
                            while (i < contadorj) {
                                if (cadena.equals(practica[i])) {
                                    if (n != 0) {
                                        fila = n;

                                    } else {
                                        fila = 0;
                                        /* if (codfacpractica[i].equals("668298")//perfil lipidido
                                            || codfacpractica[i].equals("660481")///hepatograma
                                            || codfacpractica[i].equals("660171")//coagulograma
                                            ) {
                                        if (codfacpractica[i].equals("668298")) {
                                            JOptionPane.showMessageDialog(this, "Modulo no Aceptado, debe cargar el codigo 660174 - Colesterol Total");
                                        }
                                        if (codfacpractica[i].equals("660481")) {
                                            JOptionPane.showMessageDialog(this, "Modulo no Aceptado, debe cargar el codigo 660873 - GOT");
                                        }
                                        if (codfacpractica[i].equals("660171")) {
                                            JOptionPane.showMessageDialog(this, "Modulo no Aceptado, debe cargar el codigo 660771 - TP");
                                        }
                                        Object nuevo[] = {
                                            "1", "", ""};
                                        temp.addRow(nuevo);
                                        tablapracticas.setValueAt(cod, fila, 1);
                                        tablapracticas.setValueAt(nom, fila, 2);
                                        tablapracticas.setValueAt(preciopractica[i], fila, 3);
                                        tablapracticas.setValueAt(codfacpractica[i], fila, 4);
                                        tablapracticas.setValueAt(false, fila, 5);
                                    } else {

                                        tablapracticas.setValueAt(cod, fila, 1);
                                        tablapracticas.setValueAt(nom, fila, 2);
                                        tablapracticas.setValueAt(preciopractica[i], fila, 3);
                                        tablapracticas.setValueAt(codfacpractica[i], fila, 4);
                                        tablapracticas.setValueAt(true, fila, 5);
                                    }*/

                                    }

                                    if (codfacpractica[i].equals("668298")//perfil lipidido
                                            || codfacpractica[i].equals("660481")///hepatograma
                                            || codfacpractica[i].equals("660171")//coagulograma
                                            ) {
                                        if (codfacpractica[i].equals("668298")) {
                                            JOptionPane.showMessageDialog(this, "Modulo no Aceptado, debe cargar el codigo 660174 - Colesterol Total");
                                        }
                                        if (codfacpractica[i].equals("660481")) {
                                            JOptionPane.showMessageDialog(this, "Modulo no Aceptado, debe cargar el codigo 660873 - GOT");
                                        }
                                        if (codfacpractica[i].equals("660171")) {
                                            JOptionPane.showMessageDialog(this, "Modulo no Aceptado, debe cargar el codigo 660771 - TP ");
                                        }

                                    } else {
                                        Object nuevo[] = {
                                            fila + 1, "", ""};
                                        temp.addRow(nuevo);
                                        tablapracticas.setValueAt(cod.substring(0, 6), fila, 1);
                                        tablapracticas.setValueAt(nom, fila, 2);
                                        tablapracticas.setValueAt(preciopractica[i], fila, 3);
                                        tablapracticas.setValueAt(codfacpractica[i], fila, 4);
                                        tablapracticas.setValueAt(idpractica[i], fila, 5);
                                    }
                                    band = 1;

                                }
                                i++;
                                tablapracticas.getColumnModel().getColumn(0).setPreferredWidth(10);
                                tablapracticas.getColumnModel().getColumn(1).setPreferredWidth(20);
                                tablapracticas.getColumnModel().getColumn(2).setPreferredWidth(400);
                                tablapracticas.getColumnModel().getColumn(3).setPreferredWidth(20);

                                tablapracticas.getColumnModel().getColumn(4).setPreferredWidth(0);
                                tablapracticas.getColumnModel().getColumn(5).setPreferredWidth(0);
                                tablapracticas.getColumnModel().getColumn(4).setMaxWidth(0);
                                tablapracticas.getColumnModel().getColumn(5).setMaxWidth(0);
                                tablapracticas.getColumnModel().getColumn(4).setMinWidth(0);
                                tablapracticas.getColumnModel().getColumn(5).setMinWidth(0);

                                alinear();
                                tablapracticas.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
                                tablapracticas.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
                                tablapracticas.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
                                tablapracticas.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);

                                Rectangle r = tablapracticas.getCellRect(tablapracticas.getRowCount() - 1, 0, true);
                                tablapracticas.scrollRectToVisible(r);
                                tablapracticas.getSelectionModel().setSelectionInterval(tablapracticas.getRowCount() - 1, tablapracticas.getRowCount() - 1);

                                cargartotalpracticas();
                            }
                            if (band == 0) {
                                JOptionPane.showMessageDialog(null, "La practica no es aceptada por la Obra Social...");
                                txtpractica.requestFocus();
                            }

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Superó el límite permitido");
                    }
                } else {
                    if (!cadena.equals("")) {
                        if (tablapracticas.getRowCount() <= 4) {
                            i = 0;
                            int fila = 0;

                            while (i < contadorj) {
                                if (cadena.equals(practica[i])) {
                                    /* if (n != 0) {
                                    fila = n;
                                } else {
                                    fila = 0;
                                }*/
                                    Object nuevo[] = {
                                        n + 1, cod.substring(0, 6), nom, preciopractica[i], codfacpractica[i], idpractica[i]};
                                    temp.addRow(nuevo);
                                    /* tablapracticas.setValueAt(cod.substring(0, 6), fila, 1);
                                tablapracticas.setValueAt(nom, fila, 2);
                                tablapracticas.setValueAt(preciopractica[i], fila, 3);
                                tablapracticas.setValueAt(codfacpractica[i], fila, 4);
                                tablapracticas.setValueAt(idpractica[i], fila, 5);*/
                                    band = 1;

                                }
                                i++;

                                tablapracticas.getColumnModel().getColumn(0).setPreferredWidth(10);
                                tablapracticas.getColumnModel().getColumn(1).setPreferredWidth(20);
                                tablapracticas.getColumnModel().getColumn(2).setPreferredWidth(400);
                                tablapracticas.getColumnModel().getColumn(3).setPreferredWidth(20);

                                tablapracticas.getColumnModel().getColumn(4).setPreferredWidth(0);
                                tablapracticas.getColumnModel().getColumn(5).setPreferredWidth(0);
                                tablapracticas.getColumnModel().getColumn(4).setMaxWidth(0);
                                tablapracticas.getColumnModel().getColumn(5).setMaxWidth(0);
                                tablapracticas.getColumnModel().getColumn(4).setMinWidth(0);
                                tablapracticas.getColumnModel().getColumn(5).setMinWidth(0);

                                alinear();
                                tablapracticas.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
                                tablapracticas.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
                                tablapracticas.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
                                tablapracticas.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);

                                Rectangle r = tablapracticas.getCellRect(tablapracticas.getRowCount() - 1, 0, true);
                                tablapracticas.scrollRectToVisible(r);
                                tablapracticas.getSelectionModel().setSelectionInterval(tablapracticas.getRowCount() - 1, tablapracticas.getRowCount() - 1);
                                cargartotalpracticas();
                            }
                            if (band == 0) {
                                JOptionPane.showMessageDialog(null, "La practica no es aceptada por la Obra Social...");
                                txtpractica.requestFocus();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Superó el límite permitido");
                        }
                    }
                }

                txtpractica.setText("");
            } else if (id_obra_social == 52 || id_obra_social == 53) {///superar practicas swiss medical
                if (tablapracticas.getRowCount() <= 19) {
                    if (!txtpractica.getText().equals("+")) {
                        DefaultTableModel temp = (DefaultTableModel) tablapracticas.getModel();
                        String cod = "", nom = "", cadena = txtpractica.getText();
                        int n = tablapracticas.getRowCount();
                        int i = 0, j = 1;
                        ///cod/// 000000 - aaaaaaaaa
                        while (i < cadena.length()) {
                            if (String.valueOf(cadena.charAt(i)).equals("-")) {
                                j = i + 2;
                                i = cadena.length();
                            } else {
                                cod = cod + cadena.charAt(i);
                            }
                            i++;
                        }
                        ///nom/// 000000 - aaaaaaaaa
                        while (j < cadena.length()) {
                            nom = nom + cadena.charAt(j);
                            j++;
                        }
                        ////////////////////////////////////
                        int band = 0;
                        if (!cadena.equals("")) {
                            i = 0;
                            int fila = 0;
                            while (i < contadorj) {
                                if (cadena.equals(practica[i])) {
                                    if (n != 0) {
                                        fila = n;

                                    } else {
                                        fila = 0;
                                    }
                                    Object nuevo[] = {
                                        fila + 1, "", ""};
                                    temp.addRow(nuevo);
                                    tablapracticas.setValueAt(cod.substring(0, 6), fila, 1);
                                    tablapracticas.setValueAt(nom, fila, 2);
                                    tablapracticas.setValueAt(preciopractica[i], fila, 3);
                                    tablapracticas.setValueAt(codfacpractica[i], fila, 4);
                                    tablapracticas.setValueAt(idpractica[i], fila, 5);
                                    band = 1;
                                }
                                i++;
                                tablapracticas.getColumnModel().getColumn(0).setPreferredWidth(10);
                                tablapracticas.getColumnModel().getColumn(1).setPreferredWidth(10);
                                tablapracticas.getColumnModel().getColumn(2).setPreferredWidth(300);
                                tablapracticas.getColumnModel().getColumn(3).setPreferredWidth(10);
                                ///////Ultima Fila///////
                                tablapracticas.getColumnModel().getColumn(4).setMaxWidth(0);
                                tablapracticas.getColumnModel().getColumn(4).setMinWidth(0);
                                tablapracticas.getColumnModel().getColumn(4).setPreferredWidth(0);
                                ///////////////////////////////////////////////////////////////////
                                tablapracticas.getColumnModel().getColumn(5).setMaxWidth(0);
                                tablapracticas.getColumnModel().getColumn(5).setMinWidth(0);
                                tablapracticas.getColumnModel().getColumn(5).setPreferredWidth(0);
                                Rectangle r = tablapracticas.getCellRect(tablapracticas.getRowCount() - 1, 0, true);
                                tablapracticas.scrollRectToVisible(r);
                                tablapracticas.getSelectionModel().setSelectionInterval(tablapracticas.getRowCount() - 1, tablapracticas.getRowCount() - 1);
                                //////////////////////////
                                cargartotalpracticas();
                            }
                            if (band == 0) {
                                JOptionPane.showMessageDialog(null, "La practica no es aceptada por la Obra Social...");
                                txtpractica.requestFocus();
                            }
                        }

                        txtpractica.setText("");

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Superó el límite permitido");
                }
            } else {
                if (!txtpractica.getText().equals("+")) {
                    DefaultTableModel temp = (DefaultTableModel) tablapracticas.getModel();
                    String cod = "", nom = "", cadena = txtpractica.getText();
                    int n = tablapracticas.getRowCount();
                    int i = 0, j = 1;
                    ///cod/// 000000 - aaaaaaaaa
                    while (i < cadena.length()) {
                        if (String.valueOf(cadena.charAt(i)).equals("-")) {
                            j = i + 2;
                            i = cadena.length();
                        } else {
                            cod = cod + cadena.charAt(i);
                        }
                        i++;
                    }
                    ///nom/// 000000 - aaaaaaaaa
                    while (j < cadena.length()) {
                        nom = nom + cadena.charAt(j);
                        j++;
                    }
                    ////////////////////////////////////
                    int band = 0;
                    if (!cadena.equals("")) {
                        i = 0;
                        int fila = 0;
                        while (i < contadorj) {
                            if (cadena.equals(practica[i])) {
                                if (n != 0) {
                                    fila = n;

                                } else {
                                    fila = 0;
                                }
                                Object nuevo[] = {
                                    fila + 1, "", ""};
                                temp.addRow(nuevo);
                                tablapracticas.setValueAt(cod.substring(0, 6), fila, 1);
                                tablapracticas.setValueAt(nom, fila, 2);
                                tablapracticas.setValueAt(preciopractica[i], fila, 3);
                                tablapracticas.setValueAt(codfacpractica[i], fila, 4);
                                tablapracticas.setValueAt(idpractica[i], fila, 5);
                                band = 1;
                            }
                            i++;
                            tablapracticas.getColumnModel().getColumn(0).setPreferredWidth(10);
                            tablapracticas.getColumnModel().getColumn(1).setPreferredWidth(10);
                            tablapracticas.getColumnModel().getColumn(2).setPreferredWidth(300);
                            tablapracticas.getColumnModel().getColumn(3).setPreferredWidth(10);
                            ///////Ultima Fila///////
                            tablapracticas.getColumnModel().getColumn(4).setMaxWidth(0);
                            tablapracticas.getColumnModel().getColumn(4).setMinWidth(0);
                            tablapracticas.getColumnModel().getColumn(4).setPreferredWidth(0);
                            ///////////////////////////////////////////////////////////////////
                            tablapracticas.getColumnModel().getColumn(5).setMaxWidth(0);
                            tablapracticas.getColumnModel().getColumn(5).setMinWidth(0);
                            tablapracticas.getColumnModel().getColumn(5).setPreferredWidth(0);
                            Rectangle r = tablapracticas.getCellRect(tablapracticas.getRowCount() - 1, 0, true);
                            tablapracticas.scrollRectToVisible(r);
                            tablapracticas.getSelectionModel().setSelectionInterval(tablapracticas.getRowCount() - 1, tablapracticas.getRowCount() - 1);
                            //////////////////////////
                            cargartotalpracticas();
                        }
                        if (band == 0) {
                            JOptionPane.showMessageDialog(null, "La practica no es aceptada por la Obra Social...");
                            txtpractica.requestFocus();
                        }
                    }

                    txtpractica.setText("");
                }
            }
        }
    }//GEN-LAST:event_txtpracticaActionPerformed


    private void txtpracticaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpracticaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            jTabbedPane2.setSelectedIndex(0);
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {
            jTabbedPane2.setSelectedIndex(3);
        }
    }//GEN-LAST:event_txtpracticaKeyPressed
    void cargarnovedad() {
        txtnovedad.setText(novedad);

    }
    private void btnborrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnborrarActionPerformed
        txtmes.setEnabled(true);
        txtaño.setEnabled(true);
        txtmes.setEditable(true);
        txtaño.setEditable(true);
        txtobrasocial.setText("");
        txtobrasocial.setEditable(true);
        txttotal1.setText("");
        banderamodifica = 0;
        contadorPracticas = 0;
        borrartabla();
        deshabilitarpanel1();
        txtcoseguro.setText("0.00");
        txtcoseguro.setEnabled(false);
        jLabel24.setEnabled(false);
        txtfechacoseguro.setText("");
        txtfechacoseguro.setEnabled(false);
        jLabel25.setEnabled(false);
        tipo_orden = 1;
        txtfecha.setEditable(true);
        txtmes.requestFocus();
        chkcoseguro.setSelected(false);
        btnaceptar.setEnabled(true);
        //----------------------------------------------------------------------------------------------------
        cbotipo.setVisible(false);
        txtDiaOrden.setVisible(false);
        txtDiaOrden.setVisible(false);
        jLabel21.setVisible(false);
        //------------------------------------------------------------------------------------------------------
    }//GEN-LAST:event_btnborrarActionPerformed

    ///////////////////////////////////////HILO mofifica Ordenes///////////////////////////////////////////////
    public class HiloModificaOrdenes extends Thread {

        JProgressBar progreso;

        public HiloModificaOrdenes(JProgressBar progreso1) {
            super();
            this.progreso = progreso1;
        }

        public void run() {
            int bandera = 0;
            if (!txtaño1.getText().equals("") || !txtmes1.getText().equals("")) {

                txtmes.setText(txtmes1.getText());
                txtaño.setText(txtaño1.getText());
                ConexionMariaDB cc = new ConexionMariaDB();
                Connection cn = cc.Conectar();
                try {

                    /////////////////////////////////////////////////////
                    Statement st5 = cn.createStatement();
                    ResultSet rs5 = st5.executeQuery("SELECT id_colegiados,nombre_colegiado,matricula_colegiado,direccion_laboratorio,localidad_laboratorio,validador,periodos FROM colegiados WHERE matricula_colegiado=" + matricula_colegiado);
                    rs5.next();
                    if (rs5.getInt("periodos") <= Integer.valueOf(txtaño.getText() + txtmes.getText())) {

                        bandera = 1;
                    } else {
                        bandera = 0;
                        JOptionPane.showMessageDialog(progreso, "No se puede modificar un periodo cerrado...");
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

                if (bandera == 1) {
                    jTabbedPane2.setSelectedIndex(0);

                    txtdocumento.setEnabled(true);
                    txtnumafiliado.setEnabled(true);
                    txtnombreafiliado.setEnabled(true);

                    txtmes.setEnabled(true);
                    txtaño.setEnabled(true);
                    txtmatricula.setEnabled(true);
                    txtnumorden.setEnabled(true);

                    txtfecha.setEnabled(true);

                    txtpractica.setEnabled(true);

                    txtdocumento.setEditable(true);
                    txtnumafiliado.setEditable(true);
                    txtnombreafiliado.setEditable(true);

                    txtmes.setEditable(true);
                    txtaño.setEditable(true);
                    txtmatricula.setEditable(true);
                    txtnumorden.setEditable(true);
                    txtfecha.setEditable(true);
                    txtpractica.setEditable(true);

                    txtmatricula.setText(tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 5).toString());
                    txtnumorden.setText(tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 6).toString());
                    txtfecha.setText(tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 7).toString());
                    txtDiaOrden.setText(tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 7).toString().substring(0, 2));
                    id_orden = Integer.valueOf(tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 0).toString());
                    cargartabla(tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 0).toString());

                    tablapracticas.setEnabled(true);
                    txtmes.requestFocus();
                    borrartablaordenes();
                }
            }
        }

        public void pausa(int mlSeg) {
            try {
                // pausa para el splash
                Thread.sleep(mlSeg);
            } catch (Exception e) {
            }

        }

    }

    void cargartabla(String valor) {
        cursor();
        String[] Titulo = {"Numero", "Cod", "Practica", "Precio", "Cod Fac", "id_practica"};
        String[] Registros = new String[6];
        int i = 1, obra = 0;
        model = new DefaultTableModel(null, Titulo) {
            ////Celdas no editables////////
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        ConexionMariaDB cc = new ConexionMariaDB();
        Connection cn = cc.Conectar();

        /*try {
            ////////////////////////gordo mentiroso/////////////////////////////
            Statement st7 = cn.createStatement();
            ResultSet rs7 = st7.executeQuery("SELECT id_orden,id_obrasocial,dni_afiliado,nombre_afiliado,numero_afiliado FROM ordenes WHERE id_orden=" + valor);
            rs7.next();
            id_obra_social = rs7.getInt("id_obrasocial");
            txtdocumento.setText(rs7.getString("dni_afiliado"));
            txtnumafiliado.setText(rs7.getString("numero_afiliado"));
            txtnombreafiliado.setText(rs7.getString("nombre_afiliado"));
        } catch (Exception e) {
            cursor2();
            JOptionPane.showMessageDialog(null, e);
        }*/
        String sql = "SELECT\n"
                + "*\n"
                + "FROM\n"
                + "vista_ordenes_detalle\n"
                + "WHERE id_orden=" + valor;//id_practicasnbu FROM obrasocial_tiene_practicasnbu  WHERE id_obrasocial
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Registros[0] = String.valueOf(i);
                i = i + 1;
                Registros[1] = rs.getString("cod_practica");
                Registros[2] = rs.getString("nombre_practica");
                Registros[3] = rs.getString("total");
                Registros[4] = rs.getString("cod_practica_fac");
                Registros[5] = rs.getString("id_practicasnbu");
                id_obra_social = rs.getInt("id_obrasocial");
                txtdocumento.setText(rs.getString("dni_afiliado"));
                txtnumafiliado.setText(rs.getString("numero_afiliado"));
                txtnombreafiliado.setText(rs.getString("nombre_afiliado"));
                txtobrasocial.setText((rs.getString("codigo_obrasocial") + " - " + rs.getString("razonsocial_obrasocial")));
                model.addRow(Registros);

            }
            /*
            try {

                /////////////////////////////////////////////////////
                Statement st8 = cn.createStatement();
                ResultSet rs8 = st8.executeQuery("SELECT id_obrasocial,razonsocial_obrasocial,codigo_obrasocial FROM obrasocial WHERE id_obrasocial=" + id_obra_social);
                rs8.next();
                txtobrasocial.setText((rs8.getString("codigo_obrasocial") + " - " + rs8.getString("razonsocial_obrasocial")));

            } catch (Exception e) {
                cursor2();
                JOptionPane.showMessageDialog(null, e);
            }
             */
            borrarpractica();
            cargarpracticaconobra();
            if (id_obra_social == 58) {
                txtfecha.setEnabled(false);
                txtfecha.setEditable(false);
                jLabel10.setEnabled(false);
                jLabel21.setVisible(true);
                txtDiaOrden.setVisible(true);
            } else {
                txtfecha.setEnabled(true);
                txtfecha.setEditable(true);
                jLabel10.setEnabled(true);
                jLabel21.setVisible(false);
                txtDiaOrden.setVisible(false);
            }
            txtobrasocial.setEnabled(true);
            txtobrasocial.setEditable(true);
            tablapracticas.setModel(model);
            //  tablapracticas.setAutoCreateRowSorter(true);
            /////ajustar ancho de columna///////
            /////////////////////////////////////////////////////////////
            tablapracticas.getColumnModel().getColumn(4).setMaxWidth(0);
            tablapracticas.getColumnModel().getColumn(4).setMinWidth(0);
            tablapracticas.getColumnModel().getColumn(4).setPreferredWidth(0);
            /////////////////////////////////////////////////////////////
            tablapracticas.getColumnModel().getColumn(5).setMaxWidth(0);
            tablapracticas.getColumnModel().getColumn(5).setMinWidth(0);
            tablapracticas.getColumnModel().getColumn(5).setPreferredWidth(0);
            ////////////////////////////////////////////////////////////////////////
            tablapracticas.getColumnModel().getColumn(0).setPreferredWidth(10);
            tablapracticas.getColumnModel().getColumn(1).setPreferredWidth(10);
            tablapracticas.getColumnModel().getColumn(2).setPreferredWidth(300);
            tablapracticas.getColumnModel().getColumn(3).setPreferredWidth(10);
            /////////////////////////////////////////////////////////////*/
            alinear();
            tablapracticas.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
            tablapracticas.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
            tablapracticas.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
            //////////////////////////////////////////////////////////////////
            cargartotalpracticas();
            cursor2();
        } catch (SQLException ex) {
            cursor2();
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    void cargatotales() {
        /* double total = 0.00;
        String periodo_total;

        DecimalFormat df = new DecimalFormat("0.00");
        ConexionMariaDB mysql = new ConexionMariaDB();
        Connection cn = mysql.Conectar();
        //AQUI SE SUMAN LOS VALORES DE CADA FILA PARA COLOCARLO EN EL CAMPO DE TOTAL
        periodo_total = txtaño1.getText() + txtmes1.getText();

        String sql = "SELECT sum(detalle_ordenes.precio_practica)\n"
                + "FROM ordenes\n"
                + "INNER JOIN detalle_ordenes ON detalle_ordenes.id_orden=ordenes.id_orden\n"
                + "WHERE ordenes.periodo=" + periodo_total + " and ordenes.estado_orden!=0 and detalle_ordenes.estado=0 and ordenes.id_colegiados=" + Login.id_usuario;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                total = rs.getDouble(1);
            }
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        txttotal.setText((String.valueOf(Redondear(total))));*/
        double total = 0.00, sumatoria = 0.0;
        /// System.out.println("Formularios.MainL.cargatotales()");
        DecimalFormat df = new DecimalFormat("0.00");
        //AQUI SE SUMAN LOS VALORES DE CADA FILA PARA COLOCARLO EN EL CAMPO DE TOTAL
        int totalRow = tablaordenes.getRowCount();
        totalRow -= 1;
        for (int i = 0; i <= (totalRow); i++) {
            // System.out.println("Formularios.MainL.cargatotales() 2");
            if (!tablaordenes.getValueAt(i, 10).toString().equals("ANULADA")) {
                // System.out.println("Formularios.MainL.cargatotales() 3");
                String x = tablaordenes.getValueAt(i, 9).toString();
                sumatoria = Double.valueOf(x);
                total = total + sumatoria;
            }
        }
        ///   System.out.println("Formularios.MainL.cargatotales() 5");
        //txttotalordenes.setText((String.valueOf(totalRow + 1)));
        if (estadologinadmin == true) {
            txttotal.setText((String.valueOf(Redondear(total))));
            /// System.out.println("Formularios.MainL.cargatotales() 6");
        } else {
            txttotal.setText("-----");
        }
    }

    /*void cargatotales_obra_social() {
        /* double total = 0.00;
        //String periodo_total;

        DecimalFormat df = new DecimalFormat("0.00");
        ConexionMariaDB mysql = new ConexionMariaDB();
        Connection cn = mysql.Conectar();
        //AQUI SE SUMAN LOS VALORES DE CADA FILA PARA COLOCARLO EN EL CAMPO DE TOTAL
        //periodo_total = txtaño1.getText() + txtmes1.getText();

        String sql = "SELECT sum(detalle_ordenes.precio_practica)\n"
                + "FROM ordenes\n"
                + "INNER JOIN detalle_ordenes ON detalle_ordenes.id_orden=ordenes.id_orden\n"
                + "WHERE ordenes.periodo=" + periododjj + " and ordenes.estado_orden!=0 and detalle_ordenes.estado=0 and ordenes.id_colegiados=" + Login.id_usuario + " and ordenes.id_obrasocial=" + idobraimprime;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                total = rs.getDouble(1);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        txttotal.setText((String.valueOf(Redondear(total))));
   

        double total = 0.00, sumatoria = 0.0;

        DecimalFormat df = new DecimalFormat("0.00");
        //AQUI SE SUMAN LOS VALORES DE CADA FILA PARA COLOCARLO EN EL CAMPO DE TOTAL
        int totalRow = tablaordenes.getRowCount();
        totalRow -= 1;
        for (int i = 0; i <= (totalRow); i++) {
            if (!tablaordenes.getValueAt(i, 10).toString().equals("ANULADA")) {
                String x = tablaordenes.getValueAt(i, 9).toString();
                sumatoria = Double.valueOf(x);
                total = total + sumatoria;
            }

        }
        //txttotalordenes.setText((String.valueOf(totalRow + 1)));

        txttotal.setText((String.valueOf(Redondear(total))));

    }*/
    void cargartotalpracticas() {
        double total = 0.00, sumatoria = 0.0;

        DecimalFormat df = new DecimalFormat("0.00");
        //AQUI SE SUMAN LOS VALORES DE CADA FILA PARA COLOCARLO EN EL CAMPO DE TOTAL
        int totalRow = tablapracticas.getRowCount();
        totalRow -= 1;
        for (int i = 0; i <= (totalRow); i++) {

            String x = tablapracticas.getValueAt(i, 3).toString();
            sumatoria = Double.valueOf(x);
            total = total + sumatoria;

        }
        //txttotalordenes.setText((String.valueOf(totalRow + 1)));

        txttotal1.setText((String.valueOf(Redondear(total))));

    }

    public double Redondearentero(double numero) {
        return Math.round(numero);
    }

    public double Redondear(double numero) {
        return Math.rint(numero * 100) / 100;
    }

    public double Redondearcentavos(double numero) {
        return Math.rint(numero * 100) % 100;
    }

    public double Redondearpesos(double numero) {
        return Math.rint(numero);
    }

    void dobleclick() {
        tablaordenes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Detalle_Practicas.num_orden = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 6).toString();
                    Detalle_Practicas.id_orden = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 0).toString();
                    Detalle_Practicas.afiliado = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 4).toString();
                    Detalle_Practicas.obrasocial = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString();
                    Detalle_Practicas.fecha = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 7).toString();
                    Detalle_Practicas.total = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 9).toString();
                    Detalle_Practicas.dni = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 11).toString();
                    Detalle_Practicas.observacion = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 12).toString();
                    new Detalle_Practicas(null, true).setVisible(true);
                }
            }
        });
    }

    private void tablaordenesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaordenesKeyPressed
        DefaultTableModel temp = (DefaultTableModel) tablapracticas.getModel();
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            tablaordenes.transferFocus();
            evt.consume();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tablaordenes.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "No seleccionó ninguna fila...");
            } else {
                /* ConexionMariaDB cc = new ConexionMariaDB();
                Connection cn = cc.Conectar();
                String id_orden = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 0).toString();
                try {
                    String sSQL3 = "update ordenes set dni_afiliado=?, numero_afiliado=?, numero_orden=?, fecha_orden=? where id_orden=" + id_orden;
                    PreparedStatement pst = cn.prepareStatement(sSQL3);
                    pst.setString(1, tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 11).toString());
                    pst.setString(2, tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 3).toString());
                    pst.setString(3, tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 6).toString());
                    pst.setString(4, tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 7).toString());
                    int n4 = pst.executeUpdate();
                    if (n4 > 0) {
                        JOptionPane.showMessageDialog(null, "Orden Modificada...");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }*/

                Detalle_Practicas.num_orden = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 6).toString();
                Detalle_Practicas.id_orden = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 0).toString();
                Detalle_Practicas.afiliado = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 4).toString();
                Detalle_Practicas.obrasocial = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString();
                Detalle_Practicas.fecha = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 7).toString();
                Detalle_Practicas.total = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 9).toString();
                Detalle_Practicas.dni = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 11).toString();
                Detalle_Practicas.observacion = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 12).toString();
                new Detalle_Practicas(null, true).setVisible(true);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_F1) {

            jTabbedPane2.setSelectedIndex(0);

        }

        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();

        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);

        }
    }//GEN-LAST:event_tablaordenesKeyPressed

    private void btncancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar1ActionPerformed
        txtmes1.setText("");
        txtaño1.setText("");
        borrartablaordenes();
        progreso.setValue(0);
        txttotal.setText("");
        txttotalordenes.setText("0");
        cargarperiodo();
        txtmes1.requestFocus();
    }//GEN-LAST:event_btncancelar1ActionPerformed

    private void btnsalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnsalir1ActionPerformed

    private void btnimprimirdjjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirdjjActionPerformed
///        txtaño1.setText("");
        ////   txtmes1.setText("");
        btncancelar1.doClick();
        new Periodo(this, true).setVisible(true);
        if (!periododjj.equals("") || !Periodo.mes.equals("") || !Periodo.año.equals("")) {
            new ImprimirObraSocial(this, true).setVisible(true);
            if (!ObraSocial.equals("")) {

                ///////////////////////1//////////2///////////3//////////////4//////////////5/              6
                String[] titulos = {"Orden", "Periodo", "Obra Social", "N° Afiliado", "Nombre Afiliado", "Matricula Prescripcion", "N° Orden Prescripcion", "Fecha Orden", "Colegiado", "Total", "Estado"};//estos seran los titulos de la tabla.

                Object[][] datos = {}; //en esta matriz bidimensional cargaremos los datos
                //  model2 = new DefaultTableModel(datos, titulos);
                model1 = new DefaultTableModel(datos, titulos) {
                    ////Celdas no editables////////
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tablaordenes.setModel(model1);
                borrartablaordenes();

                iniciarSplash();
                hilo2 = new MainL.Hiloobrasocial(progreso);
                hilo2.start();
                hilo2 = null;
                btnimprimirdjj.requestFocus();
                ////////////////////////////////////////////////////////////////////////
                cursor2();
                borrartablaordenes();
                txttotal.setText("0.0");
                /// txtobrasocial1.setText("");
                txtmes1.requestFocus();
            }
        }
    }//GEN-LAST:event_btnimprimirdjjActionPerformed

    private void btnobrasocialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnobrasocialesActionPerformed
        new Tabla_ObrasSociales(null, true).setVisible(true);
    }//GEN-LAST:event_btnobrasocialesActionPerformed

    private void btnnomencladorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnomencladorActionPerformed
        new Tabla_practicas().setVisible(true);
    }//GEN-LAST:event_btnnomencladorActionPerformed

    private void btnsalir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir2ActionPerformed
        try {
            Desktop.getDesktop().browse(new URI("http://www.cobituc.org.ar"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido cargar la página");
        }
    }//GEN-LAST:event_btnsalir2ActionPerformed

    void descargarpdf() {
        String ipddjj = "http://ddjj.cobituc.info/";
        String url = ipddjj + "djj/" + periododjj + "-" + matricula_colegiado + ".pdf";
        //String url = "http://" + ConexionMariaDB.ip.substring(14, 26) + "/validacion/" + periododjj + "-" + matricula_colegiado + ".pdf";
        //dirección url del recurso a descargar
        String name = "ddjj" + periododjj + "-" + matricula_colegiado + ".pdf";
        //Directorio destino para las descargas
        String folder = "C:\\Descargas-CBT\\";
        //////////gordo puto
        //Crea el directorio de destino en caso de que no exista
        try {
            File dir = new File(folder);

            if (!dir.exists()) {
                if (!dir.mkdir()) {
                    return; // no se pudo crear la carpeta de destino
                }
            }
            File file = new File(folder + name);
            URLConnection conn = new URL(url).openConnection();
            conn.connect();
            System.out.println("\nempezando descarga: \n");
            System.out.println(">> URL: " + url);
            System.out.println(">> Nombre: " + name);
            System.out.println(">> tamaño: " + conn.getContentLength() + " bytes");
            InputStream in = conn.getInputStream();
            OutputStream out = new FileOutputStream(file);
            int b = 0;
            while (b != -1) {
                b = in.read();
                if (b != -1) {
                    out.write(b);
                }
            }
            out.close();
            in.close();
            JOptionPane.showMessageDialog(null, "Su declaración Jurada se descargó exitosamente...");
        } catch (MalformedURLException e) {
            System.out.println("la url: " + url + " no es valida!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Su declaración Jurada esta en cola de espera, intente nuevamente...");
        }

    }

    void descargarpdfvalidacion() {

        String ipddjj = "http://ddjj.cobituc.info/";
        String url = ipddjj + "validacion/" + periododjj + "-" + matricula_colegiado + ".pdf";
        //String url = "http://" + ipddjj.substring(13, 28) + "/validacion/" + periododjj + "-" + matricula_colegiado + ".pdf";
        //dirección url del recurso a descargar+ periodo + "-" + matricula + "-" + cod_obra +
        String name = periododjj + "-" + matricula_colegiado + ".pdf";
        //Directorio destino para las descargas
        String folder = "C:\\Descargas-CBT\\";
        //////////gordo puto
        //Crea el directorio de destino en caso de que no exista
        try {
            File dir = new File(folder);

            if (!dir.exists()) {
                if (!dir.mkdir()) {
                    return; // no se pudo crear la carpeta de destino
                }
            }
            File file = new File(folder + name);
            URLConnection conn = new URL(url).openConnection();
            conn.connect();
            System.out.println("\nempezando descarga: \n");
            System.out.println(">> URL: " + url);
            System.out.println(">> Nombre: " + name);
            System.out.println(">> tamaño: " + conn.getContentLength() + " bytes");
            InputStream in = conn.getInputStream();
            OutputStream out = new FileOutputStream(file);
            int b = 0;
            while (b != -1) {
                b = in.read();
                if (b != -1) {
                    out.write(b);
                }
            }
            out.close();
            in.close();
            JOptionPane.showMessageDialog(null, "Su comprobantes se descargaron exitosamente...");
        } catch (MalformedURLException e) {
            System.out.println("la url: " + url + " no es valida!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Sus comprobantes esta en cola de espera, intente nuevamente...");
        }

    }

    private void btnimprimirobraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirobraActionPerformed
        ConexionMariaDB mysql = new ConexionMariaDB();
        Connection cn = mysql.Conectar();
        int i = 1, bandera = 0;
        int i2 = 4;
        String mes = "";

        new Periodo(this, true).setVisible(true);
        if (!periododjj.equals("") || !Periodo.mes.equals("") || !Periodo.año.equals("")) {
            while (i2 < 6) {
                mes = mes + String.valueOf(periododjj).charAt(i2);
                i2++;
            }

            try {
                String sSQL = "SELECT periodo FROM periodos ";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sSQL);
                rs.next();
                if (rs.getInt("periodo") <= Integer.valueOf(periododjj)) {
                    String sSQL2 = "UPDATE colegiados SET periodos=? WHERE id_colegiados=" + id_usuario;
                    PreparedStatement pst = cn.prepareStatement(sSQL2);
                    if (mes.equals("12")) {
                        pst.setInt(1, Integer.valueOf(periododjj) + 89);
                    } else {
                        pst.setInt(1, Integer.valueOf(periododjj) + 1);
                    }
                    int n = pst.executeUpdate();
                    if (n > 0) {
                        JOptionPane.showMessageDialog(null, "El período se cerró con exito...");
                    }
                    bandera = 0;

                } else {
                    JOptionPane.showMessageDialog(null, "El período ya fué finalizado...");
                    bandera = 1;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
            ////////////////////////////////////////////////////////////
            if (bandera == 0) {
                new EsperaProceso(this, true).setVisible(true);
            }
            descargarpdf();
            descargarpdfvalidacion();
        }
    }//GEN-LAST:event_btnimprimirobraActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        if (isNumeric(txtaño1.getText()) && isNumeric(txtmes1.getText())) {
            borrartabla();
            iniciarSplash();
            hilo = new HiloOrdenes(progreso);
            hilo.start();
            hilo = null;
            txtordenes.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Debe completar todos los datos...");
        }
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void txtpracticaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpracticaKeyReleased

        if (!isNumeric(txtpractica.getText())) {
            if (evt.getKeyCode() == KeyEvent.VK_ADD) {
                ///evt.consume();
                txtpractica.setText("");
                btnaceptar.doClick();
            }
        }

    }//GEN-LAST:event_txtpracticaKeyReleased

    private void btnimportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimportarActionPerformed
        if (!txtaño3.getText().equals("") && !txtmes3.getText().equals("") && !txtmes3.getText().equals("  ") && !txtaño3.getText().equals("    ")) {//  
            progreso.setValue(0);
            JOptionPane.showMessageDialog(null, "Limite de practicas por orden - 60 - ");
            new importar(this, true).setVisible(true);
            if (Importar == 1) {
                jLabel19.setText(url);
                leer_archivo();
                cargatotalesordenes();
            }
            if (Importar == 2) {
                jLabel19.setText(url);
                try {
                    CrearTabla(archivo);
                } catch (IOException ex) {
                    Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
                }
//                myModel = new DefaultTableModel(filas, columna);
                tablaordenes1.setModel(myModel);
                ///////////////////////////////////////////////////////////////////////////////
                tablaordenes1.getColumnModel().getColumn(0).setMaxWidth(0);
                tablaordenes1.getColumnModel().getColumn(0).setMinWidth(0);
                tablaordenes1.getColumnModel().getColumn(0).setPreferredWidth(0);
                int i = 10;
                while (i < 110) {
                    tablaordenes1.getColumnModel().getColumn(i).setMaxWidth(0);
                    tablaordenes1.getColumnModel().getColumn(i).setMinWidth(0);
                    tablaordenes1.getColumnModel().getColumn(i).setPreferredWidth(0);
                    i++;
                }
                cargatotalesordenes();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe especificar el periodo...");
        }
    }//GEN-LAST:event_btnimportarActionPerformed

    public void CrearTabla(File file) throws IOException {
        Workbook workbook = null;

        try {
            workbook = Workbook.getWorkbook(file);
            Sheet sheet = workbook.getSheet(0);
//            columna.clear();
//            for (int i = 0; i < sheet.getColumns(); i++) {
//                Cell cell1 = sheet.getCell(i, 0);
//                columna.add(cell1.getContents());
//            }
            filas.clear();
            for (int j = 1; j < sheet.getRows(); j++) {
                Vector d = new Vector();
                for (int i = 0; i < sheet.getColumns(); i++) {
                    Cell cell = sheet.getCell(i, j);
                    d.add(cell.getContents());
                }
                d.add("\n");
                filas.add(d);
            }
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    public void cargatotalesordenes() {
        int totalRow = tablaordenes1.getRowCount(), contador = 0;
        totalRow -= 1;
        for (int i = 0; i <= (totalRow); i++) {
            contador++;
        }
        txttotalordenes1.setText((String.valueOf(contador)));
    }

    public void cargatotalesordenesfacturacion() {
        /*   int totalRow = tablaordenes.getRowCount(), contador = 0;
         totalRow -= 1;
         for (int i = 0; i <= (totalRow); i++) {
         if (tablaordenes.getValueAt(i, 10).toString().equals("OK")) {
         contador++;
         }
         }
         txttotalordenes.setText((String.valueOf(contador)));*/

        int totalRow = tablaordenes.getRowCount(), contador = 0, contador2 = 0, contador3 = 0;
        totalRow -= 1;
        for (int i = 0; i <= (totalRow); i++) {
            if (tablaordenes.getValueAt(i, 10).toString().equals("OK")) {
                contador++;
            }
            if (tablaordenes.getValueAt(i, 10).toString().equals("ANULADA")) {
                contador2++;
            }
            if (tablaordenes.getValueAt(i, 10).toString().equals("OBSERVADA") && tablaordenes.getValueAt(i, 10).toString().equals("AUDITORIA")) {
                contador3++;
            }
        }
        txttotalordenes.setText((String.valueOf(contador)));
        txttotalanuladas.setText((String.valueOf(contador2)));
        txttotalobservadas.setText((String.valueOf(contador3)));

    }

    private void tablaordenes1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaordenes1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {

            jTabbedPane2.setSelectedIndex(0);

        }

        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {

            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);

        }
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            if (tablaordenes1.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "No seleccionó ninguna fila...");
            } else {
                //  if(){
                myModel.removeRow(tablaordenes1.getSelectedRow());
                //  }else{
//                <Mensaje><EncabezadoMensaje><VersionMsj>1.0</VersionMsj><NroReferenciaCancel>000098351394</NroReferenciaCancel><TipoTransaccion>04A</TipoTransaccion><IdMsj>3354</IdMsj><InicioTrx><FechaTrx>20091005</FechaTrx><HoraTrx>193020</HoraTrx></InicioTrx><Financiador><CodigoFinanciador>11</CodigoFinanciador><CuitFinanciador>30546741253</CuitFinanciador></Financiador><Prestador><CuitPrestador>30708402911</CuitPrestador></Prestador></EncabezadoMensaje><EncabezadoAtencion><Efector/><Prescriptor/><Credencial><NumeroCredencial>60671956201</NumeroCredencial></Credencial><Atencion><FechaAtencion>20091001</FechaAtencion></Atencion></EncabezadoAtencion></Mensaje>
                // }
            }
        }
    }//GEN-LAST:event_tablaordenes1KeyPressed

    void deshabilitar() {
        jTabbedPane2.enable(false);
    }

    void habilitar() {
        jTabbedPane2.enable(true);
    }

    int fecha_compara(String fecha) {
        int bandera_ok = 2, año_1 = 0;
        System.out.println("va a ingresar a año:" + Integer.valueOf(txtaño3.getText()));
        System.out.println("va a ingresar a año:" + Integer.valueOf(fecha.substring(6, 10)));
        año_1 = Integer.valueOf(txtaño3.getText());
        if (año_1 == Integer.valueOf(fecha.substring(6, 10))
                || (año_1 - 1) == Integer.valueOf(fecha.substring(6, 10))) {
            System.out.println("va a ingresar a mes:");
            if (Integer.valueOf(fecha.substring(0, 2)) <= 31 && Integer.valueOf(fecha.substring(0, 2)) >= 1) {
                System.out.println("mes:");

                if (Integer.valueOf(fecha.substring(3, 5)) <= 12 && Integer.valueOf(fecha.substring(3, 5)) >= 1) {
                    System.out.println("entra a mes:");

                    if (Integer.valueOf(fecha.substring(3, 5)) == 1 || Integer.valueOf(fecha.substring(3, 5)) == 3 || Integer.valueOf(fecha.substring(3, 5)) == 5 || Integer.valueOf(fecha.substring(3, 5)) == 7 || Integer.valueOf(fecha.substring(3, 5)) == 8 || Integer.valueOf(fecha.substring(3, 5)) == 10 || Integer.valueOf(fecha.substring(3, 5)) == 12) {
                        bandera_ok = 0;
                    } else {
                        if (Integer.valueOf(fecha.substring(3, 5)) == 4 || Integer.valueOf(fecha.substring(3, 5)) == 6 || Integer.valueOf(fecha.substring(3, 5)) == 9 || Integer.valueOf(fecha.substring(3, 5)) == 11) {
                            if (Integer.valueOf(fecha.substring(0, 2)) <= 30) {
                                System.out.println("ingresa a 30 dias:");

                                bandera_ok = 0;
                            }
                        } else {
                            if (Integer.valueOf(fecha.substring(3, 5)) == 2) {
                                if (Integer.valueOf(fecha.substring(6, 10)) % 4 == 0) {
                                    if (Integer.valueOf(fecha.substring(0, 2)) <= 29) {
                                        bandera_ok = 0;
                                    }
                                } else {
                                    if (Integer.valueOf(fecha.substring(0, 2)) <= 28) {
                                        bandera_ok = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        return bandera_ok;

    }

    public class HiloOrdenesImportar extends Thread {

        JProgressBar progreso3;

        public HiloOrdenesImportar(JProgressBar progreso3) {
            super();
            this.progreso3 = progreso3;

        }

        public void run() {
            ConexionMariaDB mysql = new ConexionMariaDB();
            Connection cn = mysql.Conectar();
            Connection cn2 = mysql.Conectar();
            int cantidad = 0, contador_errores = 0, fila_error = 0;
            String errores = "", fecha_orden = "";
            String[] errores_practicas = new String[100];
            ////////////////////////////////////////////
            int indice = 1; //indice contara los numeros primos que vamos encontrando    
            DefaultTableModel temp = (DefaultTableModel) MainL.myModel;
            int i2 = 0, nbu, error_cod;

            int p = 0, i4 = 0, bandera_orden;
            int n4 = temp.getRowCount();
            progreso3.setMaximum(n4);

            progreso3.setValue(1);
            //cantidad = 1000 / n4;

            if (n4 != 0) {
                btnaceptar2.setEnabled(false);
                btncancelar2.setEnabled(false);
                btnsalir3.setEnabled(false);
                btnimportar.setEnabled(false);
                txtaño3.setEnabled(false);
                txtmes3.setEnabled(false);
                cargarorden();
                bandera_orden = 0;
                if (Redondearentero(n4 * 5 / 200) != 0) {
                    JOptionPane.showMessageDialog(null, "El proceso durará aproximadamente " + Redondearentero(n4 * 5 / 200) + " minutos...");
                } else {
                    JOptionPane.showMessageDialog(null, "El proceso durará unos segundos...");
                }
                while (i4 < n4) {
                    if (periodo_colegiado <= Integer.valueOf(periodo)) {
                        try {
                            String sSQL4 = "SELECT estado FROM periodos ";
                            Statement st4 = cn.createStatement();
                            ResultSet rs4 = st4.executeQuery(sSQL4);
                            rs4.next();
                            if (rs4.getBoolean("estado") == true) {
                                if (temp.getRowCount() != 0) {
                                    int k = 0;
                                    while (k < errores_practicas.length) {
                                        errores_practicas[k] = "";
                                        k++;
                                    }
                                    double total = 0.0, totalordenes = 0.0;
                                    String num_afiliado, precio, nom_afiliado, dni_afiliado, matricula_presc, mes, año, num_orden, coseguro;
                                    String sSQL = "", ssQL = "", nombre_practca, cod_practica = "";
                                    int n, n2 = 0, n3, i, j = 0, bandera = 0, obra_social, bandera_errores = 0;
                                    ////////////////////////////////
                                    System.out.println("------------------------------------------------------------------");
                                    if (!temp.getValueAt(i4, 2).toString().equals("")) {
                                        obra_social = Integer.valueOf(temp.getValueAt(i4, 2).toString());
                                    } else {
                                        obra_social = 0;
                                    }
                                    System.out.println(obra_social);
                                    nom_afiliado = temp.getValueAt(i4, 3).toString();
                                    System.out.println(nom_afiliado);
                                    num_afiliado = temp.getValueAt(i4, 4).toString();
                                    System.out.println("numero Afiliado: " + num_afiliado);
                                    if (!temp.getValueAt(i4, 5).toString().equals("")) {
                                        num_orden = temp.getValueAt(i4, 5).toString();
                                        System.out.println(num_orden);
                                    } else {
                                        num_orden = " ";
                                        bandera = 5;
                                    }
                                    fecha_orden = temp.getValueAt(i4, 6).toString();
                                    System.out.println(fecha_orden);

                                    if (!temp.getValueAt(i4, 7).toString().equals("")) {
                                        matricula_presc = temp.getValueAt(i4, 7).toString();
                                    } else {
                                        matricula_presc = "0";
                                    }
                                    System.out.println(matricula_presc);
                                    ////////////////////////////////////////////////////
                                    if (!temp.getValueAt(i4, 8).toString().equals("")) {
                                        dni_afiliado = temp.getValueAt(i4, 8).toString();
                                    } else {
                                        dni_afiliado = "11111111";
                                    }
                                    System.out.println(dni_afiliado);
                                    if (!temp.getValueAt(i4, 9).toString().equals("")) {
                                        coseguro = temp.getValueAt(i4, 9).toString();
                                    } else {
                                        coseguro = "0.0";
                                    }
                                    System.out.println(coseguro);
                                    /////////////////////////////////////
                                    i = 0;

                                    bandera = fecha_compara(fecha_orden);

                                    System.out.println(bandera);

                                    if (dni_afiliado.length() > 8) {
                                        bandera = 3;
                                    }
                                    if (num_afiliado.equals("")) {
                                        bandera = 6;
                                    }

                                    if (bandera == 0) {
                                        if (obra_social != 13800 && obra_social != 3101 && obra_social != 10700 && obra_social != 50015 && obra_social != 9000 && obra_social != 1806 && obra_social != 1805 && obra_social != 9000 && obra_social != 1807
                                                && obra_social != 2601 && obra_social != 40813 && obra_social != 512 && obra_social != 37701) {

                                            String sSQL3 = "SELECT id_obrasocial, obrasocial.añonbu FROM obrasocial inner join nbu on obrasocial.añonbu = nbu.id_nbu WHERE Int_codigo_obrasocial=" + obra_social + " and estado_obrasocial=1";
                                            Statement st3 = cn.createStatement();
                                            ResultSet rs3 = st3.executeQuery(sSQL3);
                                            try {
                                                // if (rs3.next()) {
                                                ///System.out.println("Formularios.MainL.HiloOrdenesImportar.run()" + errores_practicas);
                                                ///errores_practicas = null;
                                                rs3.next();
                                                id_obra_social = rs3.getInt("id_obrasocial");
                                                i = 0;
                                                nbu = rs3.getInt("añonbu");
                                                j = 10;
                                                n2 = 0;
                                                cantidad = 0;

                                                while (j <= 110) {
                                                    if (Importar == 1) {
                                                        if (temp.getValueAt(i4, j) != null) {
                                                            int cod = Integer.valueOf(temp.getValueAt(i4, j).toString()) + 660000;
                                                            System.out.println(cod_practica);
                                                            System.out.println(cod);
                                                            System.out.println(id_obra_social);
                                                            System.out.println(nbu);
                                                            String sSQL5 = "SELECT codigo_practica FROM obrasocial_tiene_practicasnbu WHERE (id_obrasocial=" + id_obra_social + " AND codigo_practica=" + cod + " )";
                                                            Statement st5 = cn.createStatement();
                                                            ResultSet rs5 = st5.executeQuery(sSQL5);
                                                            try {
                                                                rs5.next();
                                                                error_cod = rs5.getInt(1);
                                                                System.out.println("error en codigo: " + cod);
                                                                cod_practica = cod_practica + cod;
                                                                ///System.out.println("paso 4");
                                                                totalordenes = totalordenes + total;
                                                                ///System.out.println("paso 5");
                                                                cantidad++;
                                                            } catch (Exception e) {

                                                                errores_practicas[i] = temp.getValueAt(i4, j).toString();
                                                                bandera_errores = 1;
                                                                System.out.println("error 1: practica" + errores_practicas[i] + " " + e);
                                                                contador_errores++;
                                                                i++;
                                                            }

                                                        } else {
                                                            break;
                                                        }
                                                    }
                                                    j++;
                                                    n2++;
                                                }

                                                /////////////////////////////////////////////                                                    
                                                try {

                                                    //////////////////////////////////////////////////////////////////////////////
                                                    CallableStatement SP_cargar_orden = null;
                                                    //System.out.println("e " + 1);
                                                    SP_cargar_orden = cn2.prepareCall("{CALL cargar_orden2 (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                                                    ///System.out.println("e " + 2);
                                                    SP_cargar_orden.setString(1, MainL.año + MainL.mes);
                                                    ///System.out.println("e " + 3);
                                                    SP_cargar_orden.setString(2, nom_afiliado);
                                                    /// System.out.println("e " + 4);
                                                    SP_cargar_orden.setString(3, dni_afiliado);
                                                    ///System.out.println("e " + 5);
                                                    SP_cargar_orden.setString(4, num_afiliado);
                                                    /// System.out.println("e " + 6);
                                                    SP_cargar_orden.setString(5, matricula_presc);
                                                    /// System.out.println("e " + 7);
                                                    SP_cargar_orden.setString(6, num_orden);
                                                    ///System.out.println("e " + 8);
                                                    SP_cargar_orden.setString(7, fecha_orden);
                                                    ///System.out.println("e " + 9);
                                                    SP_cargar_orden.setDouble(8, 0.00);
                                                    ///System.out.println("e " + 10);
                                                    SP_cargar_orden.setString(9, "1");
                                                    ///System.out.println("e " + 11);
                                                    SP_cargar_orden.setString(10, fecha);
                                                    ///System.out.println("e " + 12);
                                                    SP_cargar_orden.setString(11, hora);
                                                    ///System.out.println("e " + 13);
                                                    SP_cargar_orden.setString(12, ip2);
                                                    ///System.out.println("e " + 14);
                                                    SP_cargar_orden.setInt(13, id_obra_social);
                                                    //System.out.println("e " + 15);
                                                    SP_cargar_orden.setInt(14, id_usuario);
                                                    //System.out.println("e " + 16);
                                                    SP_cargar_orden.setString(15, null);
                                                    //System.out.println("e " + 17);
                                                    SP_cargar_orden.setInt(16, cantidad);
                                                    //System.out.println("e " + 18);
                                                    SP_cargar_orden.setString(17, cod_practica);
                                                    //System.out.println("e " + 19);
                                                    SP_cargar_orden.registerOutParameter(18, java.sql.Types.INTEGER);
                                                    //System.out.println("e " + 20);
                                                    SP_cargar_orden.setString(19, coseguro);
                                                    //System.out.println("e " + 21);
                                                    SP_cargar_orden.setString(20, "");
                                                    //System.out.println("e " + 22);
                                                    SP_cargar_orden.setInt(21, tipo_orden);
                                                    ///System.out.println("e " + 23);
                                                    SP_cargar_orden.setString(22, observacion);
                                                    //System.out.println("e " + 24);
                                                    SP_cargar_orden.setString(23, "1");
                                                    //System.out.println("e " + 25);
                                                    SP_cargar_orden.setString(24, "0");
                                                    // System.out.println("e " + 26);
                                                    SP_cargar_orden.execute();
                                                    //System.out.println("e " + 27);

                                                    id_orden = SP_cargar_orden.getInt(18);
                                                    //System.out.println("e " + 28);
                                                    ///////////////////////////////////////////////////////////////////////
                                                    if (bandera_errores == 1) {
                                                        int i3 = 0;
                                                        while (i3 < errores_practicas.length) {
                                                            if (!errores_practicas[i3].equals("")) {
                                                                System.out.println("contador de errores practicas " + i3);
                                                                errores = errores + "Orden: " + id_orden + " Practica Inexistente:" + errores_practicas[i3] + "\r\n";
                                                                System.out.println(" Practica Inexistente:" + errores_practicas[i3]);
                                                            }
                                                            i3++;
                                                        }
                                                        bandera_errores = 0;
                                                    }
                                                    temp.removeRow(i4);
                                                    i4--;
                                                    n4--;
                                                    cargatotalesordenes();
                                                    ///////////////////////////////////////////////////////////
                                                } catch (Exception e) {
                                                    System.out.println("error 3: " + e);
                                                    if (e.getMessage().indexOf("numero_afiliado") != -1) {
                                                        errores = errores + "Fila: " + (i4 + 1) + " Numero de afiliado erroneo:" + num_afiliado + "\r\n";
                                                        contador_errores++;
                                                    }

                                                    if (e.getMessage().indexOf("fecha_orden") != -1) {
                                                        errores = errores + "Fila: " + (i4 + 1) + " Fecha de orden erroneo:" + fecha_orden + "\r\n";
                                                        contador_errores++;
                                                    }
                                                    if (e.getMessage().indexOf("dni_afiliado") != -1) {
                                                        errores = errores + "Fila: " + (i4 + 1) + " numero de documento erroneo:" + dni_afiliado + "\r\n";
                                                        contador_errores++;
                                                    }
                                                    if (e.getMessage().indexOf("matricula_prescripcion") != -1) {//
                                                        errores = errores + "Fila: " + (i4 + 1) + " numero de matricula de Prescripción erroneo:" + matricula_presc + "\r\n";
                                                        contador_errores++;
                                                    }
                                                }
                                                //}
                                            } catch (Exception e) {
                                                System.out.println("---->error 4: " + e);
                                                ////errores + "Fila: " + fila_error +" Columna: "+j+ " del archivo Practica Inexistente:" ++ "\r\n" 
                                                errores = errores + "Fila: " + (i4 + 1) + " Obra Social Inexistente:" + obra_social + "\r\n";
                                                contador_errores++;
                                            }
                                        } else {
                                            errores = errores + "Fila: " + (i4 + 1) + " Obra Social Online:" + obra_social + "\r\n";
                                            contador_errores++;
                                        }
                                    }
                                    if (bandera == 1) {
                                        errores = errores + "Fila: " + (i4 + 1) + " Numero de afiliado erroneo:" + num_afiliado + "\r\n";
                                        contador_errores++;
                                    }
                                    if (bandera == 2) {
                                        errores = errores + "Fila: " + (i4 + 1) + " Fecha de orden erroneo:" + fecha_orden + "\r\n";
                                        contador_errores++;
                                    }
                                    if (bandera == 3) {
                                        errores = errores + "Fila: " + (i4 + 1) + " numero de documento erroneo:" + dni_afiliado + "\r\n";
                                        contador_errores++;
                                    }
                                    if (bandera == 4) {
                                        errores = errores + "Fila: " + (i4 + 1) + " Orden de SUBSIDIO erronea:" + num_orden + "\r\n";
                                        contador_errores++;
                                    }
                                    if (bandera == 5) {
                                        errores = errores + "Fila: " + (i4 + 1) + " Orden de SUBSIDIO erronea:" + num_orden + "\r\n";
                                        contador_errores++;
                                    }
                                    if (bandera == 6) {
                                        errores = errores + "Fila: " + (i4 + 1) + " Numero de Afiliado vacio:" + num_afiliado + "\r\n";
                                        contador_errores++;
                                    }
                                    System.out.println("------------------------------------------------------------------");
                                } else {
                                    JOptionPane.showMessageDialog(null, "No hay practicas en la tabla...");
                                    i4 = n4;
                                }
                            }
                        } catch (Exception e) {
                            errores = errores + "Fila: " + (i4 + 1) + " Verificar :" + fecha_orden + "\r\n";
                            contador_errores++;
                            System.out.println("error 5: " + e);
                            //JOptionPane.showMessageDialog(null, "Error en la base de datos");
                        }
                        bandera_orden = 1;
                    }
                    progreso3.setValue(indice); //aumentamos la barra 
                    indice++;
                    i4++;
                }

                System.out.println("Todos los errores: " + errores);

                if (bandera_orden == 1) {

                    JOptionPane.showMessageDialog(null, "Proceso realizado con exito...\r\nPor favor Verifique que todas las cargas sean correctas antes de cerrar el periodo");
                    cargatotalesordenes();
                    ///////////////////////////////////////////////////
                    try {
                        StringBuffer numeros = null;
                        for (int i = 0; i < contador_errores; i++) //recorro las filas
                        {
                            numeros = new StringBuffer(errores);
                            EscribeTxt("Transferencia.txt", numeros);
                        }
                        Desktop.getDesktop().open(new File(ruta + "Transferencia.txt"));

                    } catch (Exception e) {
                        System.out.println("error 6: " + e);
                    }
                    ///////////////////////////////////////////////////                    
                    try {
                        ///Ver el excel        
                        int cantidadfila = tablaordenes1.getRowCount();
                        int i = 0, j = 0;
                        String linea = "";

                        System.out.println("cantidad de fila: " + cantidadfila);
                        System.out.println("elemento: " + String.valueOf(tablaordenes1.getModel().getValueAt(j, i)));
                        File archivo = new File(ruta + "OrdenesConError-.txt");
                        /////////// Verifico si existe el Archivo y si existe lo elimino
                        if (archivo.exists()) {
                            archivo.delete();
                        }
                        FileWriter escribir = new FileWriter(archivo, true);

                        while (cantidadfila > i) {
                            j = 0;
                            linea = "";
                            while (!String.valueOf(tablaordenes1.getModel().getValueAt(i, j)).equals("null")) {
//                                System.out.println("cantidad elemento: " + String.valueOf(tablaordenes1.getModel().getValueAt(i, j)));
                                linea = linea + String.valueOf(tablaordenes1.getModel().getValueAt(i, j)) + ";";
                                j++;
                            }
//                            linea=linea+"\r\n";
                            escribir.write(linea + "\r\n");
                            i++;
                        }
                        escribir.close();
                        Desktop.getDesktop().open(new File(ruta + "OrdenesConError-.txt"));

//                        List<JTable> tb = new ArrayList<JTable>();
//                        tb.add(tablaordenes1);
//                        export_excel excelExporter = new export_excel(tb, new File(ruta + "Errores Transferencia" + ".xls"));
//                        if (excelExporter.export()) {
//                            // JOptionPane.showMessageDialog(null, "TABLAS EXPORTADOS CON EXITOS!");
//                        }
                    } catch (Exception ex) {
                        System.out.println("error 7: " + ex);
                        ex.printStackTrace();
                    }
//                    llama_excel();
                    ///////////////////////////////////////////////////
                } else {
                    JOptionPane.showMessageDialog(null, "Período finalizado. Debe verificar que Período desea cargar");
                }
            }

            // progreso3.setValue(100);
            btnaceptar2.setEnabled(
                    true);
            btncancelar2.setEnabled(
                    true);
            btnsalir3.setEnabled(
                    true);
            btnimportar.setEnabled(
                    true);
            txtaño3.setEnabled(
                    true);
            txtmes3.setEnabled(
                    true);
        }

        public void pausa(int mlSeg) {
            try {
                // pausa para el splash
                Thread.sleep(mlSeg);
            } catch (Exception e) {
            }

        }

    }

    public static void EscribeTxt(String ruta_archivo, StringBuffer numeros) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(ruta + ruta_archivo);
            pw = new PrintWriter(fichero);
            pw.println(numeros + "\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }


    private void btnaceptar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptar2ActionPerformed
        if (!txtaño3.getText().equals("") && !txtmes3.getText().equals("") && !jLabel19.getText().equals("")) {
            int cantidad;
            periodo = txtaño3.getText() + txtmes3.getText();
            mes = txtmes3.getText();
            año = txtaño3.getText();
            //new EsperaImportar(this, true).setVisible(true);
            ////////////////////////////////////////////
            int indice = 1; //indice contara los numeros primos que vamos encontrando    
            DefaultTableModel temp = (DefaultTableModel) tablaordenes1.getModel();
            ConexionMariaDB mysql = new ConexionMariaDB();
            Connection cn = mysql.Conectar();
            String sSQL2 = "SELECT usuario_laboratorio,periodos FROM colegiados WHERE usuario_laboratorio=" + "'" + Login.nombre_usuario + "'";
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sSQL2);
                while (rs.next()) {
                    if (Login.nombre_usuario.equals(rs.getString("usuario_laboratorio"))) {
                        periodo_colegiado = rs.getInt("periodos");
                        break;
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
            iniciarSplash2();
            hilo4 = new HiloOrdenesImportar(progreso3);
            hilo4.start();
            hilo4 = null;

        } else {
            JOptionPane.showMessageDialog(null, "Debe completar todos los campos...");
        }
        //habilitar();
    }//GEN-LAST:event_btnaceptar2ActionPerformed

    private void btncancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar2ActionPerformed
        progreso3.setValue(0);
        txtmes.setEnabled(true);
        txtaño.setEnabled(true);
        txtmes.setEditable(true);
        txtaño.setEditable(true);
        borrartablaimportar();
        txtmes.requestFocus();
        jLabel19.setText("");
        cargatotalesordenes();
    }//GEN-LAST:event_btncancelar2ActionPerformed

    private void btnsalir3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnsalir3ActionPerformed

    private void PracticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PracticasActionPerformed
        Detalle_txt.afiliado = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 3).toString();
        Detalle_txt.obrasocial = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 2).toString();
        Detalle_txt.fecha = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 1).toString();
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 9) != null) {
            Detalle_txt.p1 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 9).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 10) != null) {
            Detalle_txt.p2 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 10).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 11) != null) {
            Detalle_txt.p3 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 11).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 12) != null) {
            Detalle_txt.p4 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 12).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 13) != null) {
            Detalle_txt.p5 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 13).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 14) != null) {
            Detalle_txt.p6 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 14).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 15) != null) {
            Detalle_txt.p7 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 15).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 16) != null) {
            Detalle_txt.p8 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 16).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 17) != null) {
            Detalle_txt.p9 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 17).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 18) != null) {
            Detalle_txt.p10 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 18).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 19) != null) {
            Detalle_txt.p11 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 19).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 20) != null) {
            Detalle_txt.p12 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 20).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 21) != null) {
            Detalle_txt.p13 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 21).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 22) != null) {
            Detalle_txt.p14 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 22).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 23) != null) {
            Detalle_txt.p15 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 23).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 24) != null) {
            Detalle_txt.p16 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 24).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 25) != null) {
            Detalle_txt.p17 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 25).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 26) != null) {
            Detalle_txt.p18 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 26).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 27) != null) {
            Detalle_txt.p19 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 27).toString();
        }
        if (tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 28) != null) {
            Detalle_txt.p20 = tablaordenes1.getValueAt(tablaordenes1.getSelectedRow(), 28).toString();
        }
        new Detalle_txt(null, true).setVisible(true);
    }//GEN-LAST:event_PracticasActionPerformed

    private void txtmesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (isNumeric(txtmes.getText())) {
                if (Integer.valueOf(txtmes.getText()) >= 1 && Integer.valueOf(txtmes.getText()) <= 12) {
                    txtaño.setEnabled(true);
                    txtaño.setEditable(true);
                    txtmes.transferFocus();
                    txtmes.setEnabled(true);
                } else {
                    txtmes.setText("");
                }
            } else {
                txtmes.requestFocus();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_F1) {

            jTabbedPane2.setSelectedIndex(0);

        }

        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            jTabbedPane2.setSelectedIndex(1);
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {

            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);

        }
    }//GEN-LAST:event_txtmesKeyPressed

    boolean isNumeric(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private void txtañoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtañoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (isNumeric(txtaño.getText())) {

                ConexionMariaDB mysql = new ConexionMariaDB();
                Connection cn = mysql.Conectar();
                periodo = txtaño.getText() + txtmes.getText();
                int i = 0;

                try {
                    String sSQL = "SELECT estado,periodo FROM periodos ";
                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sSQL);
                    rs.next();
                    if (rs.getBoolean("estado") == true && rs.getInt("periodo") <= Integer.valueOf(periodo)) {
                        i = 0;
                    } else {
                        JOptionPane.showMessageDialog(null, "El período esta Inhabilitado...");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                    JOptionPane.showMessageDialog(null, "Error en la base de datos");
                }

                if (periodo_colegiado > Integer.valueOf(periodo)) {
                    i = 1;////periodo cerrado
                }
                if (periodo_colegiado < Integer.valueOf(periodo)) {
                    i = 2;////debe cerrar periodo vigente
                }
                if (i == 0) {
                    txtmes.setEditable(false);
                    txtaño.setEditable(false);
                    txtobrasocial.setEnabled(true);
                    txtobrasocial.setEditable(true);
                    jLabel5.setEnabled(true);
                    txtaño.transferFocus();
                    btnaceptar2.setEnabled(true);
                }
                if (i == 1) {
                    JOptionPane.showMessageDialog(null, "El periodo ya fue finalizado...");
                    txtmes.setEnabled(true);
                    txtaño.setEnabled(true);
                    txtmes.setEditable(true);
                    txtaño.setEditable(true);
                    txtobrasocial.setEditable(false);
                    txtmes.requestFocus();
                }
                if (i == 2) {
                    JOptionPane.showMessageDialog(null, "Está por ingresar un período posterior al vigente...");
                    txtmes.setEditable(false);
                    txtaño.setEditable(false);
                    txtobrasocial.setEnabled(true);
                    txtobrasocial.setEditable(true);
                    jLabel5.setEnabled(true);
                    txtaño.transferFocus();
                }
            } else {
                txtaño.requestFocus();
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            jTabbedPane2.setSelectedIndex(0);
        }

        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {
            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {
            jTabbedPane2.setSelectedIndex(3);
        }
    }//GEN-LAST:event_txtañoKeyPressed

    private void txtnumordenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumordenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            System.out.println(String.valueOf(Integer.valueOf(txtaño.getText().substring(2)) - 1));

            if (!txtnumorden.getText().equals("")) {
                if ("1800 - SUBSIDIO DE SALUD - IPSSPT".equals(txtobrasocial.getText())
                        || "1801 - SUBSIDIO DE SALUD - MATERNO INFANTIL".equals(txtobrasocial.getText())
                        || "1802 - SUBSIDIO DE SALUD - SEGURO ESCOLAR".equals(txtobrasocial.getText())
                        || "1803 - SUBSIDIO DE SALUD - RECIPROCIDAD".equals(txtobrasocial.getText())
                        || "1804 - SUBSIDIO DE SALUD - INTERNADO".equals(txtobrasocial.getText())
                        || "1810 - SUBSIDIO DE SALUD - PRODIASS-PLAN PREVENCION".equals(txtobrasocial.getText())
                        || "1815 - SUBSIDIO DE SALUD - REFACTURACION".equals(txtobrasocial.getText())) {
                    System.out.println("ss 1");
                    long ordnumero = Long.valueOf(txtnumorden.getText());
                    long resto3 = 0, resto2, resto = ordnumero;
                    long total = 0, i = 0, d, digito;
                    long totalsobre11, totalmod11, orddigitoverificador;
                    resto3 = resto / 100;
                    digito = resto - (resto3 * 100);
                    resto = resto3;
                    do {
                        i++;
                        resto2 = resto / 10;
                        d = resto - (resto2 * 10);
                        total = total + (d * (i + 1));
                        resto = resto2;
                    } while (resto > 0);
                    totalsobre11 = total / 11;
                    totalmod11 = total - (totalsobre11 * 11);
                    orddigitoverificador = 11 - totalmod11;
                    if (orddigitoverificador == digito) {

                        int orden = Integer.parseInt(txtnumorden.getText().substring(0, txtnumorden.getText().length() - 2));
                        Ordenes ordenValida = new Ordenes();

                        if (ordenValida.validaOrden(orden)) {

                            JOptionPane.showMessageDialog(null, "Orden duplicada");
                            ordenValida.guardaRegistroSS(orden);
                        } else {

                            txtfecha.setEnabled(true);
                            txtfecha.setEditable(true);
                            jLabel10.setEnabled(true);
                            jLabel21.setVisible(false);
                            txtDiaOrden.setVisible(false);
                            txtfecha.select(0, 0);
                            txtfecha.requestFocus();

                        }
                    } else {
                        if (orddigitoverificador == 11) {

                            System.out.println("nro de orden: " + txtnumorden.getText().substring(0, txtnumorden.getText().length() - 2));
                            int orden = Integer.parseInt(txtnumorden.getText().substring(0, txtnumorden.getText().length() - 2));
                            Ordenes ordenValida = new Ordenes();

                            if (ordenValida.validaOrden(orden)) {
                                JOptionPane.showMessageDialog(null, "Orden duplicada");
                                ordenValida.guardaRegistroSS(orden);

                            } else {

                                txtfecha.setEnabled(true);
                                txtfecha.setEditable(true);
                                jLabel10.setEnabled(true);
                                jLabel21.setVisible(false);
                                txtDiaOrden.setVisible(false);
                                txtfecha.select(0, 0);
                                txtfecha.requestFocus();
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Numero de orden incorrecto...");
                        }
                    }
                } else {
                    if (id_obra_social != 58) {
                        System.out.println("pami 0");
                        txtfecha.setEnabled(true);
                        txtfecha.setEditable(true);
                        jLabel10.setEnabled(true);
                        jLabel21.setVisible(false);
                        txtDiaOrden.setVisible(false);
                        txtfecha.select(0, 0);
                        txtfecha.requestFocus();
                    } else {
                        if (!txtnumorden.getText().equals("")) {
                            if ((txtnumorden.getText().substring(0, 4).equals("33" + txtaño.getText().substring(2)) || txtnumorden.getText().substring(0, 4).equals("33" + (Integer.valueOf(txtaño.getText().substring(2)) - 1))) && txtnumorden.getText().length() == 13 && cbotipo.getSelectedIndex() == 0) {
                                System.out.println("pami 1");
                                txtfecha.setEnabled(false);
                                txtfecha.setEditable(false);
                                jLabel10.setEnabled(false);
                                jLabel21.setVisible(true);
                                txtDiaOrden.setVisible(true);
                                txtDiaOrden.requestFocus();

                            } else if ((txtnumorden.getText().substring(2, 4).equals(txtaño.getText().substring(2))
                                    || txtnumorden.getText().substring(2, 4).equals(String.valueOf(Integer.valueOf(txtaño.getText().substring(2)) - 1))) && txtnumorden.getText().length() == 11 && cbotipo.getSelectedIndex() == 1) {
                                System.out.println("pami 2");
                                txtfecha.setEnabled(false);
                                txtfecha.setEditable(false);
                                jLabel10.setEnabled(false);
                                jLabel21.setVisible(true);
                                txtDiaOrden.setVisible(true);
                                txtDiaOrden.requestFocus();
                            } else if (cbotipo.getSelectedIndex() == 2) {
                                System.out.println("pami 3");
                                txtfecha.setEnabled(false);
                                txtfecha.setEditable(false);
                                jLabel10.setEnabled(false);
                                jLabel21.setVisible(true);
                                txtDiaOrden.setVisible(true);
                                txtDiaOrden.requestFocus();
                            } else {
                                JOptionPane.showMessageDialog(null, "Número de orden incorrecto");
                            }
                        }
                    }

                }
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            jTabbedPane2.setSelectedIndex(0);
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F3) {
            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);

        }
    }//GEN-LAST:event_txtnumordenKeyPressed

    private void txtfechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfechaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtfecha.getText().equals("")) {
                if (!txtfecha.equals("  /  /    ")) {
                    if (Integer.valueOf(txtfecha.getText().substring(0, 2)) <= 31 && Integer.valueOf(txtfecha.getText().substring(0, 2)) >= 1) {
                        if (Integer.valueOf(txtfecha.getText().substring(3, 5)) <= 12 && Integer.valueOf(txtfecha.getText().substring(3, 5)) >= 1) {
                            if (Integer.valueOf(txtfecha.getText().substring(3, 5)) == 1 || Integer.valueOf(txtfecha.getText().substring(3, 5)) == 3 || Integer.valueOf(txtfecha.getText().substring(3, 5)) == 5 || Integer.valueOf(txtfecha.getText().substring(3, 5)) == 7 || Integer.valueOf(txtfecha.getText().substring(3, 5)) == 8 || Integer.valueOf(txtfecha.getText().substring(3, 5)) == 10 || Integer.valueOf(txtfecha.getText().substring(3, 5)) == 12) {
                                jLabel12.setEnabled(true);
                                txtpractica.setEnabled(true);
                                txtpractica.setEditable(true);
                                if (obra.equals("1806 - SUBSIDIO DE SALUD - AUTORIZACION - ONLINE")) {
                                    txtpractica.setEditable(false);
                                    btnaceptar.requestFocus();
                                } else {
                                    txtpractica.setEditable(true);
                                    chkcoseguro.requestFocus();
                                }
                            } else {
                                if (Integer.valueOf(txtfecha.getText().substring(3, 5)) == 4 || Integer.valueOf(txtfecha.getText().substring(3, 5)) == 6 || Integer.valueOf(txtfecha.getText().substring(3, 5)) == 9 || Integer.valueOf(txtfecha.getText().substring(3, 5)) == 11) {
                                    if (Integer.valueOf(txtfecha.getText().substring(0, 2)) <= 30) {
                                        jLabel12.setEnabled(true);
                                        txtpractica.setEnabled(true);
                                        txtpractica.setEditable(true);
                                        if (obra.equals("1806 - SUBSIDIO DE SALUD - AUTORIZACION - ONLINE")) {
                                            txtpractica.setEditable(false);
                                            btnaceptar.requestFocus();
                                        } else {
                                            txtpractica.setEditable(true);
                                            chkcoseguro.requestFocus();
                                        }
                                    }
                                } else {
                                    if (Integer.valueOf(txtfecha.getText().substring(3, 5)) == 2) {
                                        if (Integer.valueOf(txtfecha.getText().substring(6, 10)) % 4 == 0) {
                                            if (Integer.valueOf(txtfecha.getText().substring(0, 2)) <= 29) {
                                                jLabel12.setEnabled(true);
                                                txtpractica.setEnabled(true);
                                                txtpractica.setEditable(true);
                                                chkcoseguro.requestFocus();
                                                if (obra.equals("1806 - SUBSIDIO DE SALUD - AUTORIZACION - ONLINE")) {
                                                    txtpractica.setEditable(false);
                                                    chkcoseguro.setEnabled(false);
                                                    txtcoseguro.setEnabled(false);
                                                    btnaceptar.requestFocus();
                                                } else {
                                                    txtpractica.setEditable(true);
                                                    chkcoseguro.requestFocus();
                                                }
                                            }
                                        } else {
                                            if (Integer.valueOf(txtfecha.getText().substring(0, 2)) <= 28) {
                                                jLabel12.setEnabled(true);
                                                txtpractica.setEnabled(true);
                                                txtpractica.setEditable(true);
                                                if (obra.equals("1806 - SUBSIDIO DE SALUD - AUTORIZACION - ONLINE")) {
                                                    txtpractica.setEditable(false);
                                                    btnaceptar.requestFocus();
                                                } else {
                                                    txtpractica.setEditable(true);
                                                    chkcoseguro.requestFocus();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            jTabbedPane2.setSelectedIndex(0);
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {

            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);

        }
    }//GEN-LAST:event_txtfechaKeyPressed

    private void btnobrasocialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnobrasocialActionPerformed

        borrartabla();
        txtdocumento.setText("");
        txtnombreafiliado.setText("");
        txtnumafiliado.setText("");
        txtmatricula.setText("");
        txtfecha.setText("");
        txtnumorden.setText("");
        txtfecha.setEditable(false);
        txtdocumento.setEditable(false);
        txtnombreafiliado.setEditable(false);
        txtnumafiliado.setEditable(false);
        txtmatricula.setEditable(false);
        txtnumorden.setEditable(false);
        txtpractica.setEditable(false);
        txtobrasocial.setEnabled(true);
        txtobrasocial.setEditable(true);
        txtpractica.setEditable(false);
        txtmes.setEditable(false);
        banderamodifica = 0;
        txtaño.setEditable(false);
        txttotal1.setText("");
        txtobrasocial.setText("");
        txtobrasocial.requestFocus();
        txtcoseguro.setText("0.00");
        txtcoseguro.setEditable(false);
        //jLabel24.setEnabled(false);
        txtfechacoseguro.setText("");
        txtfechacoseguro.setEditable(false);
        chkcoseguro.setSelected(false);
        //----------------------------------------------------------------------------------------------------
        cbotipo.setVisible(false);
        txtDiaOrden.setVisible(false);
        txtDiaOrden.setVisible(false);
        jLabel21.setVisible(false);
        //------------------------------------------------------------------------------------------------------
        contadorPracticas = 0;
        tipo_orden = 1;
        cargarperiodo();
    }//GEN-LAST:event_btnobrasocialActionPerformed

    private void btnpacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpacienteActionPerformed
        DefaultTableModel temp = (DefaultTableModel) tablapracticas.getModel();
        temp.setRowCount(0);
        tablapracticas.setModel(temp);
        if (!txtobrasocial.getText().equals("")) {
            if (obra.equals("3100 - OSDE") || obra.equals("3101 - OSDE  ( RESPONSABLES INSCRIPTOS)")) {
                new OsdeAfiliado(this, true).setVisible(true);
                if (habilitado.equals("OK")) {
                    limpiar_variables();
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
                        limpiar_variables();
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
                            limpiar_variables();
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
                                limpiar_variables();
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
                                    limpiar_variables();
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
                                        limpiar_variables();
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
                                        txtdocumento.setText("");
                                        txtdocumento.setEditable(false);
                                        txtnombreafiliado.setText("");
                                        txtnumafiliado.setText("");
                                        txtmatricula.setText("");
                                        txtdocumento.requestFocus();
                                        habilitarpanel1();
                                    } else {
                                        if (obra.equals("512 - MEDIFE - ONLINE OBLIGATORIO PRE PAGA C.M.C.  S.A.") || obra.equals("513 - MEDIFE - ONLINE VOLUNTARIO PRE PAGA C.M.C.  S.A.")) {
                                            limpiar_variables();
                                            new MedifeAfiliado(this, true).setVisible(true);
                                            if (MedifeAfiliado.habilitado.equals("OK")) {
                                                txtdocumento.setText(MedifeAfiliado.dni);
                                                txtdocumento.setEditable(false);
                                                txtnombreafiliado.setText(MedifeAfiliado.nombreafiliado);
                                                txtnumafiliado.setText(MedifeAfiliado.Codigo_afiliado);
                                                if (MedifeAfiliado.plan.equals("GRAV^VOLUNTARIO")) {//GRAV^VOLUNTARIO
                                                    tipo_orden = 0;
                                                } else {
                                                    tipo_orden = 1;
                                                }
                                                System.out.println("tipo_orden Medife:" + tipo_orden);
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
                                                    limpiar_variables();
                                                    txtdocumento.setText(JerarquicosAfiliado.dni);
                                                    txtdocumento.setEditable(false);
                                                    txtnombreafiliado.setText(JerarquicosAfiliado.nombreafiliado);
                                                    txtnumafiliado.setText(JerarquicosAfiliado.Codigo_afiliado);
                                                    txtmatricula.setText("");
                                                    txtmatricula.requestFocus();
                                                    habilitarpanel1();
                                                }
                                            } else {
                                                if (obra.equals("40600 - OSPE- OBRA SOCIAL DE PETROLEROS")) {
                                                    new OspeAfiliado(this, true).setVisible(true);
                                                    if (OspeAfiliado.habilitado.equals("OK")) {
                                                        limpiar_variables();
                                                        txtdocumento.setText(OspeAfiliado.dni);
                                                        txtdocumento.setEditable(false);
                                                        txtfecha.setEditable(false);
                                                        txtnumorden.setEditable(false);
                                                        txtnombreafiliado.setText(OspeAfiliado.nombreafiliado);
                                                        txtnumafiliado.setText(OspeAfiliado.Codigo_afiliado);
                                                        txtmatricula.requestFocus();
                                                        habilitarpanel1();
                                                    } else {
                                                        txtdocumento.setEditable(false);
                                                        txtnombreafiliado.setEditable(false);
                                                        txtnumafiliado.setEditable(false);
                                                    }
                                                }
                                                if (obra.equals("2700 - UNT - Accion  Social de la  UNT")) {
                                                    new AsuntAfiliado(this, true).setVisible(true);
                                                    if (AsuntAfiliado.habilitado.equals("OK")) {
                                                        limpiar_variables();
                                                        txtdocumento.setText(AsuntAfiliado.dni);
                                                        txtdocumento.setEditable(false);
                                                        txtfecha.setEditable(false);
                                                        txtnumorden.setEditable(false);
                                                        txtnombreafiliado.setText(AsuntAfiliado.nombreafiliado);
                                                        txtnumafiliado.setText(AsuntAfiliado.Codigo_afiliado);
                                                        txtmatricula.requestFocus();
                                                        habilitarpanel1();
                                                    } else {
                                                        txtdocumento.setEditable(false);
                                                        txtnombreafiliado.setEditable(false);
                                                        txtnumafiliado.setEditable(false);
                                                    }
                                                } else {
                                                    borrartabla();
                                                    txtdocumento.setText("");
                                                    txtnombreafiliado.setText("");
                                                    txtnumafiliado.setText("");
                                                    txtmatricula.setText("");
                                                    txtfecha.setText("");
                                                    txtnumorden.setText("");
                                                    banderamodifica = 0;
                                                    txtdocumento.setEnabled(true);
                                                    txttotal1.setText("");
                                                    txtnombreafiliado.setEnabled(false);
                                                    txtcoseguro.setText("0.00");
                                                    txtcoseguro.setEnabled(false);
                                                    jLabel24.setEnabled(false);
                                                    txtfechacoseguro.setText("");
                                                    txtfechacoseguro.setEnabled(false);
                                                    chkcoseguro.setSelected(false);
                                                    jLabel25.setEnabled(false);
                                                    if (!obra.equals("10070 - SWISS MEDICAL GROUP S.A. - ONLINE") && !obra.equals("3100 - OSDE") && !obra.equals("9000 - ASOCIACION MUTUAL SANCOR") && !obra.equals("512 - MEDIFE - ONLINE OBLIGATORIO PRE PAGA C.M.C.  S.A.") && !obra.equals("513 - MEDIFE - ONLINE VOLUNTARIO PRE PAGA C.M.C.  S.A.") && !obra.equals("3102 - OSDE - OFFLINE") && !obra.equals("40600 - OSPE- OBRA SOCIAL DE PETROLEROS")) {
                                                        tipo_orden = 1;
                                                    }
                                                    txtdocumento.requestFocus();
                                                    cargarperiodo();
                                                }
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
            txtobrasocial.setEnabled(true);
            txtobrasocial.requestFocus();
            JOptionPane.showMessageDialog(null, "Debe ingresar una obra social...");
        }

    }//GEN-LAST:event_btnpacienteActionPerformed

    private void txtmes3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmes3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (isNumeric(txtmes3.getText())) {
                if (Integer.valueOf(txtmes3.getText()) >= 1 && Integer.valueOf(txtmes3.getText()) <= 12) {
                    txtaño3.setEnabled(true);
                    txtaño3.setEditable(true);
                    txtmes3.transferFocus();
                } else {
                    txtmes3.setText("");
                }
            } else {
                txtmes3.requestFocus();
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_F1) {

            jTabbedPane2.setSelectedIndex(0);

        }

        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {

            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);

        }
    }//GEN-LAST:event_txtmes3KeyPressed

    private void txtaño3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaño3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (isNumeric(txtaño3.getText())) {//if (isNumeric(txtaño1.getText()) && isNumeric(txtmes1.getText())) {
                jButton4.transferFocus();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_F1) {

            jTabbedPane2.setSelectedIndex(0);

        }

        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {

            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);

        }
    }//GEN-LAST:event_txtaño3KeyPressed

    private void txtmes1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmes1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (isNumeric(txtmes1.getText())) {
                if (Integer.valueOf(txtmes1.getText()) >= 1 && Integer.valueOf(txtmes1.getText()) <= 12) {
                    txtaño1.setEnabled(true);
                    txtaño1.setEditable(true);
                    txtmes1.transferFocus();
                } else {
                    txtmes1.setText("");
                }
            } else {
                txtmes1.requestFocus();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_F1) {

            jTabbedPane2.setSelectedIndex(0);

        }

        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {

            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);

        }
    }//GEN-LAST:event_txtmes1KeyPressed

    private void txtaño1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaño1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtaño1.getText().equals("")) {
                txtaño1.transferFocus();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_F1) {

            jTabbedPane2.setSelectedIndex(0);

        }

        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {

            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);
        }
    }//GEN-LAST:event_txtaño1KeyPressed

    private void btnbuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnbuscarKeyPressed

        /*   if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (isNumeric(txtaño1.getText()) && isNumeric(txtmes1.getText())) {
                borrartabla();
                iniciarSplash();
                hilo = new HiloOrdenes(progreso);
                hilo.start();
                hilo = null;
            } else {
                JOptionPane.showMessageDialog(null, "Debe completar todos los datos...");
            }
        }*/
        if (evt.getKeyCode() == KeyEvent.VK_F1) {

            jTabbedPane2.setSelectedIndex(0);

        }

        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {

            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);

        }
    }//GEN-LAST:event_btnbuscarKeyPressed

    void filtrarordenes(String valor) {
        //estos seran los titulos de la tabla.
        String[] Titulo = {"Orden", "Periodo", "Obra Social", "N° Afiliado", "Nombre Afiliado", "Matricula Prescripcion", "N° Orden Prescripcion", "Fecha Orden", "Colegiado", "Total", "Estado", "Dni", "Obervacion"};
        String[] Registros = new String[13];
        String sql = "SELECT id_orden,periodo,id_obrasocial,numero_afiliado,nombre_afiliado,matricula_prescripcion,numero_orden,fecha_orden,id_colegiados,total,estado_orden, observacion, dni_afiliado FROM ordenes WHERE CONCAT(id_orden, ' ',numero_orden, ' ',numero_afiliado ,' ',dni_afiliado,' ',nombre_afiliado)"
                + "LIKE '%" + valor + "%'";
        model = new DefaultTableModel(null, Titulo) {
            ////Celdas no editables////////
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        ConexionMariaDB cc = new ConexionMariaDB();
        Connection cn = cc.Conectar();

        try {
            /////////////////////////////////////////////////////
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                if (id_usuario == rs.getInt("id_colegiados") && rs.getString("periodo").equals(txtaño1.getText() + txtmes1.getText())) {
                    Registros[0] = rs.getString("id_orden");
                    Registros[1] = rs.getString("periodo");
                    /////////////////////////////////////////////////////
                    Statement st2 = cn.createStatement();
                    String sql2 = "SELECT id_obrasocial,razonsocial_obrasocial FROM obrasocial WHERE id_obrasocial=" + rs.getString("id_obrasocial");
                    ResultSet rs2 = st2.executeQuery(sql2);
                    while (rs2.next()) {
                        if (rs.getString("id_obrasocial").equals(rs2.getString("id_obrasocial")) && rs.getString("periodo").equals(txtaño1.getText() + txtmes1.getText())) {
                            Registros[2] = rs2.getString("razonsocial_obrasocial");
                        }
                    }
                    /////////////////////////////////////////////////////
                    Registros[3] = rs.getString("numero_afiliado");
                    Registros[4] = rs.getString("nombre_afiliado");
                    Registros[5] = rs.getString("matricula_prescripcion");
                    Registros[6] = rs.getString("numero_orden");
                    Registros[7] = rs.getString("fecha_orden");
                    Registros[8] = rs.getString("id_colegiados");
                    Registros[9] = rs.getString("total");
                    String estado = "";
                    if (rs.getInt("estado_orden") == 1) {
                        estado = "OK";
                    } else if (rs.getInt("estado_orden") == 3) {
                        estado = "AUDITORIA";
                    } else {
                        estado = "ANULADA";
                    }
                    Registros[10] = estado;
                    Registros[11] = rs.getString(13);
                    if (rs.getString(12) != null) {
                        Registros[12] = rs.getString(12);
                    } else {
                        Registros[12] = "";
                    }
                    model.addRow(Registros);

                    tablaordenes.setModel(model);
                    /////////////////////////////////////////////////////////////
                    //    tablaordenes.getColumnModel().getColumn(0).setMaxWidth(0);
                    //  tablaordenes.getColumnModel().getColumn(0).setMinWidth(0);
                    //tablaordenes.getColumnModel().getColumn(0).setPreferredWidth(0);
                    /////////////////////////////////////////////////////////////////
                    tablaordenes.getColumnModel().getColumn(1).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(1).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(1).setPreferredWidth(0);
                    /////////////////////////////////////////////////////////////////
                    tablaordenes.getColumnModel().getColumn(8).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(8).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(8).setPreferredWidth(0);
                    /////////////////////////////////////////////////////////////
                    tablaordenes.getColumnModel().getColumn(11).setMaxWidth(0);
                    tablaordenes.getColumnModel().getColumn(11).setMinWidth(0);
                    tablaordenes.getColumnModel().getColumn(11).setPreferredWidth(0);
                    /////////////////////////////////////////////////////////////
                    alinear();
                    tablaordenes.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(4).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(5).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(6).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(7).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(8).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(9).setCellRenderer(alinearCentro);
                    tablaordenes.getColumnModel().getColumn(10).setCellRenderer(alinearCentro);
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
        if (tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 10).toString().equals("OK") || tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 10).toString().equals("AUDITORIA")) {
            if (tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("BOREAL")
                    || tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("SWISS MEDICAL GROUP S.A. - ONLINE")
                    || tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("OSDE")
                    || tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("ASOCIACION MUTUAL SANCOR")
                    || tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("SUBSIDIO DE SALUD - ONLINE")
                    || tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("SUBSIDIO DE SALUD - AUTORIZACION - ONLINE")
                    || tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("JERARQUICOS SALUD - EMP. BNA - ONLINE")
                    || tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("MEDIFE - ONLINE OBLIGATORIO PRE PAGA C.M.C.  S.A.")
                    || tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("MEDIFE - ONLINE VOLUNTARIO PRE PAGA C.M.C.  S.A.")
                    || tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("IOSFA")) {
                JOptionPane.showMessageDialog(null, "Las ordenes cargadas ONLINE deben ser anuladas...");
            } else {
                hilo3 = new MainL.HiloModificaOrdenes(progreso);
                hilo3.start();
                hilo3 = null;
                banderamodifica = 1;
            }
        } else {
            JOptionPane.showMessageDialog(null, "La orden está anulada. No puede ser modificada...");
            banderamodifica = 0;
        }
    }//GEN-LAST:event_ModificarActionPerformed

    private void AnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnularActionPerformed
        int i = 0;
        String observacion_anulacion = "";

        mensajeanulacion = "";
        respuestaanulacion = "";
        try {
            ConexionMariaDB mysql = new ConexionMariaDB();
            Connection cn = mysql.Conectar();
            String sSQL = "SELECT estado,periodo FROM periodos ";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            rs.next();

            int periodo_anula = Integer.valueOf(tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 1).toString()),
                    bandera_anulacion = 0;
            if (rs.getBoolean("estado") == true && rs.getInt("periodo") <= Integer.valueOf(periodo_anula)) {
                i = 0;

                //////////////////////////////////////////////////////////////
                if (tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 10).toString().equals("OK") || tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 10).toString().equals("AUDITORIA")) {

                    id_orden = Integer.valueOf(tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 0).toString());
                    String cod_afiliado = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 3).toString();
                    String fecha = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 7).toString();

                    System.out.println("codigo Afiliado: " + cod_afiliado);
                    System.out.println("fecha Afiliado: " + fecha);

                    /// String num_orden = "5690075";
                    String num_orden = tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 6).toString();
                    try {
                        cursor();

                        /////////OSDE////////////////////////////////////////////////////
                        if (tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("OSDE") || tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("OSDE  ( RESPONSABLES INSCRIPTOS)")) {
                            System.out.println("Anulacion Osde");
                            System.out.println("codigo Afiliado osde: " + cod_afiliado);
                            System.out.println("fecha Afiliado osde : " + fecha);
                            System.out.println("fecha numero de orden osde : " + num_orden);

                            mensajeanulacion = "<Mensaje><EncabezadoMensaje><VersionMsj>1.0</VersionMsj><NroReferenciaCancel>" + num_orden + "</NroReferenciaCancel><TipoTransaccion>04A</TipoTransaccion><IdMsj>" + hora + "</IdMsj><InicioTrx><FechaTrx>" + fechaMySql + "</FechaTrx><HoraTrx>" + hora + "</HoraTrx></InicioTrx><Financiador><CodigoFinanciador>11</CodigoFinanciador><CuitFinanciador>30546741253</CuitFinanciador></Financiador><Prestador><CuitPrestador>" + cuit + "</CuitPrestador></Prestador></EncabezadoMensaje><EncabezadoAtencion><Efector/><Prescriptor/><Credencial><NumeroCredencial>" + cod_afiliado + "</NumeroCredencial></Credencial><Atencion><FechaAtencion>" + fecha + "</FechaAtencion></Atencion></EncabezadoAtencion></Mensaje>";
                            //mensajeanulacion = "<Mensaje><EncabezadoMensaje><VersionMsj>1.0</VersionMsj><NroReferenciaCancel>236029525</NroReferenciaCancel><TipoTransaccion>04A</TipoTransaccion><IdMsj>" + hora + "</IdMsj><InicioTrx><FechaTrx>" + fechaosde + "</FechaTrx><HoraTrx>" + hora + "</HoraTrx></InicioTrx><Financiador><CodigoFinanciador>11</CodigoFinanciador><CuitFinanciador>30546741253</CuitFinanciador></Financiador><Prestador><CuitPrestador>" + cuit + "</CuitPrestador></Prestador></EncabezadoMensaje><EncabezadoAtencion><Efector/><Prescriptor/><Credencial><NumeroCredencial>62684577601</NumeroCredencial></Credencial><Atencion><FechaAtencion>" + fecha + "</FechaAtencion></Atencion></EncabezadoAtencion></Mensaje>";

                            HttpOsdeAnulacion http = new HttpOsdeAnulacion();
                            System.out.println("Testing 3 - Send Http GET request");
                            try {
                                http.sendGet();
                            } catch (Exception ex) {
                                cursor2();
                                JOptionPane.showMessageDialog(null, ex);
                                bandera_anulacion = 1;
                                observacion_anulacion = ex.toString();
                            }
                            System.out.println("2 anulacion");
                            int pos = respuestaanulacion.indexOf("<NroReferencia>");
                            int pos2 = respuestaanulacion.indexOf("</NroReferencia>");
                            cursor2();
                            JOptionPane.showMessageDialog(null, "Numero de Anulación:" + respuestaanulacion.substring(pos + 18, pos2));
                            System.out.println("2--");

                        }
                        /////SWISS MEDICAL GROUP S.A.
                        if (tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("SWISS MEDICAL GROUP S.A. - ONLINE")) {
                            System.out.println("Anulacion sw");
                            int respuesta = 0;
                            try {
                                apiSwLogin postL = new apiSwLogin();
                                String token = postL.sendPost();
                                if (!token.equals("0")) {
                                    apiSwAnulacion post = new apiSwAnulacion();
                                    respuesta = post.sendPost(num_orden, cod_afiliado, "Bearer " + token);
                                    cursor2();
                                    if (respuesta > 0) {
                                        JOptionPane.showMessageDialog(null, "Numero de Anulación:" + respuesta);
                                        System.out.println("3---");
                                    } else {
                                        bandera_anulacion = 1;
                                        JOptionPane.showMessageDialog(null, "Error de comunicación");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error al intentar loguearse con Swiss Medical");
                                }

                            } catch (Exception ex) {
                                cursor2();
                                JOptionPane.showMessageDialog(null, ex);
                                bandera_anulacion = 1;
                                observacion_anulacion = ex.toString();
                            }

                        }
                        ////////////////////////BOREAL//////////////
                        if (tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("BOREAL")) {
                            //////////////////////////////////////////////////////////////
                            System.out.println("4");
                            String emisor = "CBT" + completarceros(matricula_colegiado, 9);
                            Contraseña_Boreal contraseña = new Contraseña_Boreal();
                            String clave = contraseña.Boreal_Contraseña();
                            /////////////////////////////////////////////////////////////////////////////////
                            mensajepractica = "<Boreal><Mensaje><Canal>ID</Canal><SitioEmisor>" + emisor + "</SitioEmisor><Receptor><Nombre>BOREAL</Nombre><ID>222023</ID><Tipo>IIN</Tipo></Receptor><MsgTipo><Tipo>ZQA</Tipo><Evento>Z04</Evento><Estructura>ZQA_Z02</Estructura></MsgTipo></Mensaje><Seguridad><Usuario>cobitucws</Usuario><Clave>" + clave + "</Clave></Seguridad><Prestador><PrestadorId>" + cuit + "</PrestadorId><PrestadorTipoIdent>CU</PrestadorTipoIdent></Prestador><Autorizacion><AutCod></AutCod><AutEstadoId></AutEstadoId><AutObs></AutObs><AutCodAnulacion>" + num_orden + "</AutCodAnulacion></Autorizacion></Boreal>";
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////            
                            System.out.println("Testing 1 - Send Http GET request");
                            System.out.println(mensajepractica);
                            ///////////////////////////////////////////////////////////////////////////////////////////////
                            ClienteBoreal.WsBorealExecute servicio = new ClienteBoreal.WsBorealExecute();
                            servicio.setIngresoxml(mensajepractica);
                            respuestapractica = execute(servicio).getEgresoxml();
                            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
                            System.out.println("Testing 2 - Get Http GET request");
                            System.out.println(respuestapractica);
                            ////////////////////////////////////////////////////////////////////////////////////////////////
                            int pos = respuestapractica.indexOf("<AutEstadoId>");
                            int pos2 = respuestapractica.indexOf("</AutEstadoId>");
                            /// JOptionPane.showMessageDialog(null, respuestapractica.substring(pos + 13, pos2));
                            if (respuestapractica.substring(pos + 13, pos2).equals("B000")) {
                                int pos3 = respuestapractica.indexOf("<AutCod>");
                                int pos4 = respuestapractica.indexOf("</AutCod>");
                                num_orden = respuestapractica.substring(pos3 + 8, pos4);
                                cursor2();
                                JOptionPane.showMessageDialog(null, "Numero de Anulación:" + num_orden);
                                ///tablaordenes.setValueAt("ANULADA", tablaordenes.getSelectedRow(), 10);
                                // cargatotales();
                                ////cargatotalesordenesfacturacion();
                            }
                            if (respuestapractica.substring(pos + 13, pos2).equals("M054")) {
                                habilitado = "ERROR";
                                int pos3 = respuestapractica.indexOf("<AutObs>");
                                int pos4 = respuestapractica.indexOf("</AutObs>");
                                cursor2();
                                JOptionPane.showMessageDialog(null, respuestapractica.substring(pos3 + 8, pos4));
                                observacion_anulacion = respuestapractica.substring(pos3 + 8, pos4);
                                bandera_anulacion = 1;
//                              bandera_boreal = 0;
                            }
                            System.out.println("4----");
                            //hilo91.stop();
                        }
                        //////////////////////SANCOR
                        if (tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("ASOCIACION MUTUAL SANCOR")) {
                            System.out.println("5");
                            //////////////////////////////////////////////////////////
                            ClienteSancor.PAWESSAV2ANULACION servicio = new ClienteSancor.PAWESSAV2ANULACION();
                            servicio.setModo("P");
                            servicio.setEntidad(8999);
                            servicio.setNroautorizacion(Integer.valueOf(num_orden));
                            servicio.setUsuario("WSRVSSA");
                            servicio.setClave("15WSSA08");
                            PAWESSAV2ANULACIONResponse anulacion_sancor = anulacion(servicio);
                            //System.out.println(anulacion_sancor);
                            if (anulacion_sancor.getCodigorespuesta() == 35) {
                                habilitado = "AUTORIZADO";
                                num_orden = String.valueOf(anulacion_sancor.getNroordenrta());
                                txtnumorden.setText(num_orden);
                                cursor2();
                                JOptionPane.showMessageDialog(null, "Numero de Anulación:" + num_orden);
                                ///////////////////////    tablaordenes.setValueAt("ANULADA", tablaordenes.getSelectedRow(), 10);
                                ///  cargatotales();
                                /// cargatotalesordenesfacturacion();
                            } else {
                                habilitado = "ERROR";
                                cursor2();
                                JOptionPane.showMessageDialog(null, anulacion(servicio).getDescripcionrespuesta());
                                bandera_anulacion = 1;
                                observacion_anulacion = anulacion(servicio).getDescripcionrespuesta();
                            }
                            System.out.println("5-----");
                            //hilo91.stop();
                        }
                        /////////////////////////////////SUBSIDIOasd
                        if (tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("SUBSIDIO DE SALUD - ONLINE")) {
                            System.out.println("6");
                            //////////////// http://186.122.150.144/ServiciosIpsstBioq/aordendevolver.aspx?wsdl //////////////////////////////////////////
                            ClienteIPSST5.OrdenDevolverExecute servicio = new ClienteIPSST5.OrdenDevolverExecute();
                            servicio.setAficuil(tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 3).toString());
                            servicio.setOrdtipo("B");
                            servicio.setPrestador(Integer.valueOf(Login.matricula_colegiado));
                            servicio.setUsuario(2);//
                            servicio.setToken("iq12Ii35o");
                            servicio.setOrdnumero(Integer.valueOf(num_orden));
                            OrdenDevolverExecuteResponse respuesta_devolucion = execute_2(servicio);
                            if (respuesta_devolucion.getEstadoactual().equals("DEVUELTA")) {
                                habilitado = "AUTORIZADO";
                                cursor2();
                                JOptionPane.showMessageDialog(null, "Orden anulala del servidor de Subsidio");
                            } else {
                                habilitado = "ERROR";
                                cursor2();
                                JOptionPane.showMessageDialog(null, respuesta_devolucion.getMotivo());
                                bandera_anulacion = 1;
                                observacion_anulacion = respuesta_devolucion.getMotivo();
                            }
                            System.out.println("6------");
                        }
                        ///////////////////subsidio 1806//////////////////////////////////////////////////////
                        if (tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("SUBSIDIO DE SALUD - AUTORIZACION - ONLINE")) {
                            System.out.println("7");
                            //////////////// http://186.122.150.144/ServiciosIpsstBioq/aordendevolver.aspx?wsdl //////////////////////////////////////////
                            ClienteIPSST6.OrdenValidadaAnularExecute servicio = new ClienteIPSST6.OrdenValidadaAnularExecute();
                            servicio.setAficuil(tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 3).toString());
                            servicio.setPrestador(Login.matricula_colegiado);
                            servicio.setUsuario(2);//
                            servicio.setToken("iq12Ii35o");
                            servicio.setOrdnumero(Integer.valueOf(num_orden));

                            OrdenValidadaAnularExecuteResponse respuesta_devuelve = execute_3(servicio);
                            //System.out.println(anulacion_sancor);
                            if (respuesta_devuelve.getOrdenanuladada() == 1) {
                                habilitado = "AUTORIZADO";
                                cursor2();
                                JOptionPane.showMessageDialog(null, "Orden anulala del servidor de Subsidio");
                                //  cargatotales();
                                //// cargatotalesordenesfacturacion();
                            } else {
                                habilitado = "ERROR";
                                cursor2();
                                JOptionPane.showMessageDialog(null, respuesta_devuelve.getMotivo());
                                bandera_anulacion = 1;
                                observacion_anulacion = respuesta_devuelve.getMotivo();
                            }
                            ///hilo91.stop();
                            System.out.println("7------");
                        }
                        if (tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("MEDIFE - ONLINE OBLIGATORIO PRE PAGA C.M.C.  S.A.") || tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("MEDIFE - ONLINE VOLUNTARIO PRE PAGA C.M.C.  S.A.")) {                            //////MEDIFE ANULACION
                            System.out.println("Anulacion medife");
                            System.out.println("9----");
                            TripleDes tpDatos = new TripleDes();
                            String codigo_respuesta = "";
                            String Anulacion = "MSH|^~\\&|TRIA0100M|TRIA00007526|MEDIFE|MEDIFE^222222^IIN|" + fechahora_medife + "||ZQA^Z04^ZQA_Z02|" + codigo_seguridad_medife + "|P|2.4|||NE|AL|ARG\r\n"
                                    + "ZAU||" + num_orden + "\r\n"
                                    + "PRD|PS^Prestador Solicitante||^^^T||||30522483881^CU|\r\n"
                                    + "PRD|EF^Efector||^^^T||||" + cuit + "^CU&M&C|\r\n"
                                    + "PID|||" + tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 3).toString() + "^^^MEDIFE^HC^MEDIFE||UNKNOWN";
                            String clave = "IA007526";
                            String usuario = "IA007526";
                            String tipo = "SI";
                            String llave = "1234567890123456ABCDEFGH";

                            String pszMsg = tpDatos.EncriptarStr(Anulacion, llave);

                            try { // Call Web Service Operation
                                WebServiceIA service2 = new WebServiceIA();
                                WebServiceIASoap port2 = service2.getWebServiceIASoap();
                                // TODO initialize WS operation arguments here
                                String pszUser = tpDatos.EncriptarStr(usuario, llave);
                                String pszPwd = tpDatos.EncriptarStr(clave, llave);
                                String pszMsgType = tpDatos.EncriptarStr(tipo, llave);

                                // TODO process result here
                                String result = port2.enviar(pszMsg, pszUser, pszPwd, pszMsgType);
                                System.out.println("Respuesta = " + result);
                                ///busco la respuesta
                                i = result.indexOf("ZAU");
                                int pipe = 0;
                                while (i < result.indexOf("PRD")) {
                                    if (pipe == 3) {
                                        mensaje = mensaje + result.charAt(i);
                                    }
                                    if (result.charAt(i) == '|') {
                                        pipe++;
                                    }
                                    i++;
                                }
                                ////busco numero de respuesta
                                i = result.indexOf("ZAU");
                                pipe = 0;
                                while (i < result.indexOf("PRD")) {
                                    if (pipe == 2) {
                                        num_orden = num_orden + result.charAt(i);
                                    }
                                    if (result.charAt(i) == '|') {
                                        pipe++;
                                    }
                                    i++;
                                }
                                mensaje = mensaje.replace("^", " ");
                                codigo_respuesta = mensaje.substring(0, 4);
                                System.out.println(num_orden + " " + mensaje);
                            } catch (Exception ex) {
                                cursor2();
                                JOptionPane.showMessageDialog(null, ex);
                            }
                            if (codigo_respuesta.equals("B000") || codigo_respuesta.equals("B001")) {
                                JOptionPane.showMessageDialog(null, "La orden fue anulada del servidor de MEDIFE");
                            } else {
                                cursor2();
                                JOptionPane.showMessageDialog(null, mensaje);
                                bandera_anulacion = 1;

                                observacion_anulacion = mensaje;
                            }
                        }//("37700 - JERARQUICOS SALUD - EMPLEADOS BANCOS NACIONAL")("37701 - JERARQUICOS SALUD - EMP. BNA - ONLINE")
                        if (tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("JERARQUICOS SALUD - EMP. BNA - ONLINE")) {

                            ObjectFactory factory = new ObjectFactory();

                            ClienteJerarquicos.CriterioAnulacionConsumoWeb autorizacion = new ClienteJerarquicos.CriterioAnulacionConsumoWeb();
                            autorizacion.setIdConvenio(451);
                            autorizacion.setIdTransaccion(Integer.valueOf(num_orden));

                            JAXBElement<CriterioAnulacionConsumoWeb> criterio = factory.createSolicitudAnulacionConsumoWebCriterioAnulacionConsumoWeb(autorizacion);

                            ClienteJerarquicos.SolicitudAnulacionConsumoWeb solicitud = new ClienteJerarquicos.SolicitudAnulacionConsumoWeb();
                            solicitud.setCriterioAnulacionConsumoWeb(criterio);

                            ClienteJerarquicos.Servicio service = new ClienteJerarquicos.Servicio();

                            ClienteJerarquicos.IServicioPublico port = service.getBasicHttpBindingIServicioPublico();

                            ClienteJerarquicos.RespuestaBase result = port.anularAutorizacionConsumo(solicitud);
                            String respuesta = result.getDTOSerializado().getValue();
                            System.out.println("Anulacion jerarquicos " + respuesta);
                            int pos_ok = respuesta.indexOf("\"Nombre\":\"EXITO\"");
                            if (pos_ok > 0) {
                                cursor2();
                                JOptionPane.showMessageDialog(null, "La orden fue anulada del servidor de Jerarquicos Salud");
                            } else {
                                cursor2();
                                JOptionPane.showMessageDialog(null, respuesta);
                                bandera_anulacion = 1;
                                observacion_anulacion = mensaje;
                            }
                        }
                        ////|| obra.equals("40600 - OSPE- OBRA SOCIAL DE PETROLEROS")
                        if (tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("OSPE- OBRA SOCIAL DE PETROLEROS")) {

                            try {
                                ExecuteFileTransactionSL mensaje = new ExecuteFileTransactionSL();
                                mensaje.setPos("0000");

                                String xml = "<Mensaje>\n"
                                        + "     <EncabezadoMensaje>\n"
                                        + "		<VersionMsj>ACT20</VersionMsj>\n"
                                        + "		<NroReferenciaCancel>" + num_orden + "</NroReferenciaCancel>\n"
                                        + "		<TipoMsj>OL</TipoMsj>\n"
                                        + "		<TipoTransaccion>04A</TipoTransaccion>\n"
                                        + "		<IdMsj/>\n"
                                        + "		<InicioTrx>\n"
                                        + "             <FechaTrx>" + fechaMySql + "</FechaTrx>\n"
                                        + "             <HoraTrx>" + hora + "</HoraTrx>\n"
                                        + "		</InicioTrx>\n"
                                        + "		<Terminal>\n"
                                        + "            <TipoTerminal>PC</TipoTerminal>\n"
                                        + "            <NumeroTerminal>21000037</NumeroTerminal>\n"
                                        + "		</Terminal>\n"
                                        + "		<Validador/>\n"
                                        + "		<Financiador>\n"
                                        + "            <CodigoFinanciador>OSPE</CodigoFinanciador>\n"
                                        + "		</Financiador>\n"
                                        + "		<Prestador>\n"
                                        + "            <CuitPrestador>30522483881</CuitPrestador>\n"
                                        + "            <RazonSocial>Colegio de Bioquimicos de Tucuman</RazonSocial>\n"
                                        + "		</Prestador>\n"
                                        + "	</EncabezadoMensaje>\n"
                                        + "	<EncabezadoAtencion>\n"
                                        + "		<FechaAtencion>" + invertir(fecha) + "</FechaAtencion>\n"
                                        + "	</EncabezadoAtencion>\n"
                                        + "</Mensaje>";
                                mensaje.setFileContent(xml);
                                System.out.println("Send:" + mensaje.getPos() + " " + mensaje.getFileContent());
                                String resultado = null;
                                try {
                                    WSActiviaC servicio = new WSActiviaC();
                                    WSActiviaCSoap port = servicio.getWSActiviaCSoap();
                                    resultado = port.executeFileTransactionSL(mensaje.getPos(), mensaje.getFileContent());
                                    System.out.println("resultado:" + resultado);
                                    //Generate XML
                                    try {
                                        FileWriter archivo3 = new FileWriter("C:/Facturacion Laboratorios/respuesta.xml");
                                        archivo3.write(resultado);
                                        archivo3.close();
                                        System.out.println("");
                                        ReadXMLFile respuestaOspe2 = new ReadXMLFile();

                                        System.out.println("Anulacion ospe");

                                        if (respuestaOspe2.ReadXMLOspe04A().getCodigo().equals("00")) {
                                            cursor2();
                                            JOptionPane.showMessageDialog(null, "La orden fue anulada del servidor de OSPE OBRA SOCIAL DE PETROLEROS. N° de anulación: " + respuestaOspe2.ReadXMLOspe04A().getNroReferencia() + "\n" + " Mensaje WS: " + respuestaOspe2.ReadXMLOspe04A().getCodigo() + " " + respuestaOspe2.ReadXMLOspe04A().getRespuesta());
                                        } else {
                                            cursor2();
                                            JOptionPane.showMessageDialog(null, "N° Ref: " + respuestaOspe2.ReadXMLOspe04A().getNroReferencia() + "\n" + " Mensaje WS: " + respuestaOspe2.ReadXMLOspe04A().getCodigo() + " " + respuestaOspe2.ReadXMLOspe04A().getRespuesta());
                                            bandera_anulacion = 1;
                                            observacion_anulacion = respuestaOspe2.ReadXMLOspe04A().getRespuesta() + "  " + respuestaOspe2.ReadXMLOspe04A().getMensaje();
                                        }

                                    } catch (Exception er) {
                                        System.out.println("error al generar archivo " + er);
                                    }
                                } catch (Exception e) {
                                    System.out.println("error al conectarse con servidor " + resultado);
                                }
                            } catch (Exception e) {
                                System.out.println("e" + e);
                            }

                        }

                        /////UNT - Accion  Social de la  UNT
                        if (tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().equals("UNT - Accion  Social de la  UNT")) {
                            System.out.println("Anulacion asunt");                            
                            try {
                                JSONObject jsonConsulta = new JSONObject();
                                jsonConsulta.put("servicio", 3);
                                jsonConsulta.put("codigo", num_orden);
                                JSONObject json3 = conexionWsdl(jsonConsulta);
                                System.out.println("json respuesta anulacion orden: " + json3.toString());
                                if (json3.getBoolean("resultado") == true) {
                                    cursor2();
                                    JOptionPane.showMessageDialog(null, "Anulación correcta del servidor de Asunt");
                                } else {
                                    cursor2();
                                    JOptionPane.showMessageDialog(null, "Error al intentar loguearse con Asunt");
                                    JOptionPane.showMessageDialog(null, json3.getString("mensaje"));
                                }
                            } catch (Exception ex) {
                                cursor2();
                                JOptionPane.showMessageDialog(null, ex);
                                bandera_anulacion = 1;
                                observacion_anulacion = ex.toString();
                            }

                        }

                        cursor();
                        cargatotales();
                        tablaordenes.setValueAt("ANULADA", tablaordenes.getSelectedRow(), 10);
                        cargatotalesordenesfacturacion();
                        //     }
                        System.out.println("1-");
                        //  cursor2();
                        String sSQL2 = null;

                        if (bandera_anulacion == 0) {

                            sSQL2 = "UPDATE ordenes SET estado_orden=? WHERE id_orden=" + id_orden;
                            PreparedStatement pst = cn.prepareStatement(sSQL2);
                            pst.setInt(1, 0);

                            int n = pst.executeUpdate();
                            System.out.println("1");

                            if (n > 0) {
                                cursor2();
                                JOptionPane.showMessageDialog(null, "La orden fué anulada en nuestro servidor...");
                            }
                        } else {
                            cursor2();
                            JOptionPane.showMessageDialog(null, "La orden no pudo ser anulada");
                            cursor();
                            sSQL2 = "UPDATE ordenes SET observacion=? WHERE id_orden=" + id_orden;
                            PreparedStatement pst = cn.prepareStatement(sSQL2);
                            pst.setString(1, observacion_anulacion);
                            System.out.println("Anulacion");
                            int n = pst.executeUpdate();
                        }
                    } catch (Exception e) {
                        cursor2();
                        JOptionPane.showMessageDialog(null, e);
                    }

                    ///////////////////////////////////////////////////////////////////////////////////////////////
                } else {
                    cursor2();
                    JOptionPane.showMessageDialog(null, "La orden ya está anulada...");
                    banderamodifica = 0;
                }
                ///////////////////////////////////////////////////////////////
            } else {
                JOptionPane.showMessageDialog(null, "El período esta Inhabilitado...");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "Error en la base de datos");
        }
    }//GEN-LAST:event_AnularActionPerformed

    private void jTabbedPane2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            jTabbedPane2.setSelectedIndex(0);
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            jTabbedPane2.setSelectedIndex(1);
        }
        if (evt.getKeyCode() == KeyEvent.VK_F3) {
            jTabbedPane2.setSelectedIndex(2);
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {
            jTabbedPane2.setSelectedIndex(3);
        }
    }//GEN-LAST:event_jTabbedPane2KeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            jTabbedPane2.setSelectedIndex(0);
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            jTabbedPane2.setSelectedIndex(1);
        }
        if (evt.getKeyCode() == KeyEvent.VK_F3) {
            jTabbedPane2.setSelectedIndex(2);
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {
            jTabbedPane2.setSelectedIndex(3);
        }
    }//GEN-LAST:event_formKeyPressed

    int comprobar_medico(int matricula) {
        ConexionMariaDB mysql = new ConexionMariaDB();
        Connection cn = mysql.Conectar();
        int banderaControl = 0;
        try {
            String controlOrden = "select matricula,observacion from medicos_baja where id_obrasocial=" + id_obra_social + " and matricula=" + matricula + " and tipo_estado=0";
            Statement stControl = cn.createStatement();
            ResultSet rsControl = stControl.executeQuery(controlOrden);
            if (rsControl.next()) {
                if (!rsControl.wasNull()) {

                    banderaControl = 1;
                    JOptionPane.showMessageDialog(null, rsControl.getString(2));
                } else {
                    banderaControl = 0;
                }
            } else {
                banderaControl = 0;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " " + ex);
            banderaControl = 0;
        }
        return banderaControl;
    }

    private void txtmatriculaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmatriculaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {

            if (!txtmatricula.getText().equals("")) {
                System.out.println(obra);
                if (obra.equals("50015 - BOREAL")
                        || obra.equals("10070 - SWISS MEDICAL GROUP S.A. - ONLINE")
                        || obra.equals("3100 - OSDE")
                        || obra.equals("3101 - OSDE  ( RESPONSABLES INSCRIPTOS)")
                        || obra.equals("9000 - ASOCIACION MUTUAL SANCOR")
                        || obra.equals("1805 - SUBSIDIO DE SALUD - ONLINE")
                        || obra.equals("512 - MEDIFE - ONLINE OBLIGATORIO PRE PAGA C.M.C.  S.A.")
                        || obra.equals("513 - MEDIFE - ONLINE VOLUNTARIO PRE PAGA C.M.C.  S.A.")
                        || obra.equals("37701 - JERARQUICOS SALUD - EMP. BNA - ONLINE")
                        || obra.equals("40600 - OSPE- OBRA SOCIAL DE PETROLEROS")
                        || obra.equals("2700 - UNT - Accion  Social de la  UNT")) {

                    System.out.println("online");
                    txtnumorden.setEditable(false);
                    txtfechacoseguro.setEditable(false);
                    txtpractica.setEnabled(true);
                    txtpractica.setEditable(true);
                    txtpractica.requestFocus();
                    jLabel12.setEnabled(true);
                } else if (id_obra_social == 58) {
                    txtnumorden.setEnabled(true);
                    txtnumorden.setEditable(true);
                    if (cbotipo.getSelectedIndex() == 0) {
                        txtnumorden.setText("33" + txtaño.getText().substring(2));
                    } else if (cbotipo.getSelectedIndex() == 1) {
                        txtnumorden.setText(txtmes.getText() + txtaño.getText().substring(2));
                    } else if (cbotipo.getSelectedIndex() == 2) {
                        txtnumorden.setText("");
                    }
                    txtmatricula.transferFocus();

                } else {
                    txtnumorden.setEnabled(true);
                    txtnumorden.setEditable(true);
                    txtmatricula.transferFocus();
                }
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            jTabbedPane2.setSelectedIndex(0);
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {
            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);
        }
    }//GEN-LAST:event_txtmatriculaKeyPressed

    private void txtmatriculaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmatriculaKeyReleased
        if (!isNumeric(txtmatricula.getText())) {
            if (evt.getKeyCode() == KeyEvent.VK_MULTIPLY) {
                if (obra.equals("3100 - OSDE") || obra.equals("3101 - OSDE  ( RESPONSABLES INSCRIPTOS)")) {
                    limpiar_variables();
                    new OsdeAfiliado(this, true).setVisible(true);
                    if (habilitado.equals("OK")) {
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
                        limpiar_variables();
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
                            limpiar_variables();
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
                                limpiar_variables();
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
                                    limpiar_variables();
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
                                        limpiar_variables();
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
                                            limpiar_variables();
                                            new osdeOffline(this, true).setVisible(true);
                                            txtpractica.setText("");
                                            txtdocumento.setText("");
                                            txtdocumento.setEditable(false);
                                            txtnombreafiliado.setText("");
                                            txtnumafiliado.setText("");
                                            txtmatricula.setText("");
                                            txtdocumento.requestFocus();
                                            habilitarpanel1();
                                        } else {
                                            if (obra.equals("512 - MEDIFE - ONLINE OBLIGATORIO PRE PAGA C.M.C.  S.A.")) {
                                                limpiar_variables();
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
                                                    limpiar_variables();
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
                                                    if (obra.equals("40600 - OSPE- OBRA SOCIAL DE PETROLEROS")) {
                                                        new OspeAfiliado(this, true).setVisible(true);
                                                        if (OspeAfiliado.habilitado.equals("OK")) {
                                                            txtdocumento.setText(OspeAfiliado.dni);
                                                            txtdocumento.setEditable(false);
                                                            txtfecha.setEditable(false);
                                                            txtnumorden.setEditable(false);
                                                            txtnombreafiliado.setText(OspeAfiliado.nombreafiliado);
                                                            txtnumafiliado.setText(OspeAfiliado.Codigo_afiliado);
                                                            txtmatricula.requestFocus();
                                                            habilitarpanel1();
                                                        }
                                                    } else {
                                                        limpiar_variables();
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
                }
            } else {
                if (evt.getKeyCode() == KeyEvent.VK_SUBTRACT) {
                    txtpractica.setText("");
                    btnborrar.doClick();
                } else {
                    if (evt.getKeyCode() == KeyEvent.VK_DIVIDE) {

                        txtpractica.setText("");
                        btnobrasocial.doClick();

                    } else {
                        txtmatricula.setText("");
                    }
                }
            }

        }
    }//GEN-LAST:event_txtmatriculaKeyReleased

    private void txtobrasocialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtobrasocialKeyPressed

        /*if ((evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) && (txtobrasocial.getText().equals(""))) {

            txtmes.setEnabled(true);
            txtaño.setEnabled(true);

            txtmes.setEditable(true);
            txtaño.setEditable(true);

            txtobrasocial.setEditable(false);
            jLabel5.setEnabled(true);
            txtaño.requestFocus();

        }*/
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            jTabbedPane2.setSelectedIndex(0);
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {

            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);
        }
    }//GEN-LAST:event_txtobrasocialKeyPressed

    private void txtnombreafiliadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreafiliadoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtnombreafiliado.getText().equals("")) {
                txtnumafiliado.setEnabled(true);
                txtnumafiliado.setEditable(true);
                txtnombreafiliado.transferFocus();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            jTabbedPane2.setSelectedIndex(0);
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {

            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {
            jTabbedPane2.setSelectedIndex(3);
        }
    }//GEN-LAST:event_txtnombreafiliadoKeyPressed

    private void OrdenesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OrdenesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            jTabbedPane2.setSelectedIndex(0);
        }

        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();

        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {

            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);

        }
    }//GEN-LAST:event_OrdenesKeyPressed

    private void jPanel7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel7KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {

            jTabbedPane2.setSelectedIndex(0);

        }

        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {

            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);

        }
    }//GEN-LAST:event_jPanel7KeyPressed

    private void btnimportarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnimportarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {

            jTabbedPane2.setSelectedIndex(0);

        }

        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {

            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);

        }
    }//GEN-LAST:event_btnimportarKeyPressed

    private void txtordenesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtordenesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {

            jTabbedPane2.setSelectedIndex(0);

        }

        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {
            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);

        }


    }//GEN-LAST:event_txtordenesKeyPressed

    private void FacturacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FacturacionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {

            jTabbedPane2.setSelectedIndex(0);

        }

        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {

            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);

        }
    }//GEN-LAST:event_FacturacionKeyPressed

    private void UtilitariosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UtilitariosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {

            jTabbedPane2.setSelectedIndex(0);

        }

        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {

            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);

        }
    }//GEN-LAST:event_UtilitariosKeyPressed

    private void btnobrasocialesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnobrasocialesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {

            jTabbedPane2.setSelectedIndex(0);

        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {

            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);

        }
    }//GEN-LAST:event_btnobrasocialesKeyPressed

    private void btnnomencladorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnnomencladorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {

            jTabbedPane2.setSelectedIndex(0);

        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {

            habilitacionPanelDos();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);

        }
    }//GEN-LAST:event_btnnomencladorKeyPressed

    private void txtobrasocialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtobrasocialActionPerformed
        borrarpractica();
        int band = 0;
        obra = txtobrasocial.getText();
        if (!obra.equals("") || !txtmes.equals("") || !txtaño.equals("")) {

            if (!obra.equals("13800 - INSSJYP - PAMI  -AUGL 1 (AMB)") && !obra.equals("13700 - INSSJYP -PAMI- EN TRANSITO")) {

                int i = 0;
                while (i < contadorobrasocial) {

                    if (obra.equals(obrasocial[i])) {
                        //----------------------------------------------------------------------------------------------------
                        cbotipo.setVisible(false);
                        txtDiaOrden.setVisible(false);
                        txtDiaOrden.setVisible(false);
                        jLabel21.setVisible(false);
                        //------------------------------------------------------------------------------------------------------
                        if (obra.equals("50015 - BOREAL")
                                || obra.equals("10070 - SWISS MEDICAL GROUP S.A. - ONLINE")
                                || obra.equals("3100 - OSDE")
                                || obra.equals("3101 - OSDE  ( RESPONSABLES INSCRIPTOS)")
                                || obra.equals("9000 - ASOCIACION MUTUAL SANCOR")
                                || obra.equals("1805 - SUBSIDIO DE SALUD - ONLINE")
                                || obra.equals("1806 - SUBSIDIO DE SALUD - AUTORIZACION - ONLINE")
                                || obra.equals("3102 - OSDE - OFFLINE")
                                || obra.equals("512 - MEDIFE - ONLINE OBLIGATORIO PRE PAGA C.M.C.  S.A.")
                                || obra.equals("37701 - JERARQUICOS SALUD - EMP. BNA - ONLINE")
                                || obra.equals("513 - MEDIFE - ONLINE VOLUNTARIO PRE PAGA C.M.C.  S.A.")
                                || obra.equals("510 - MEDIFE - OBLIGATORIO- PRE PAGA C.M.C.  S.A.")
                                || obra.equals("511 - MEDIFE - VOLUNTARIO- PRE PAGA C.M.C.  S.A.")
                                || obra.equals("40600 - OSPE- OBRA SOCIAL DE PETROLEROS")
                                || obra.equals("2700 - UNT - Accion  Social de la  UNT")) {
                            //|| obra.equals("37701 - JERARQUICOS SALUD - EMP. BNA - ONLINE")) {//SUBSIDIO DE SALUD - AUTORIZACION - ONLINE
                            ///////////////////////////////////////////////////////////////////////////////////////////////

                            if (obra.equals("3100 - OSDE") || obra.equals("3101 - OSDE  ( RESPONSABLES INSCRIPTOS)")) {
                                new OsdeAfiliado(this, true).setVisible(true);
                                if (OsdeAfiliado.habilitado.equals("OK")) {
                                    txtdocumento.setText("11111111");
                                    txtdocumento.setEditable(false);
                                    txtnombreafiliado.setText(OsdeAfiliado.nombreafiliado);
                                    txtnumafiliado.setText(OsdeAfiliado.Codigo_afiliado);
                                    txtmatricula.requestFocus();
                                    txtfecha.setEditable(false);
                                    txtnumorden.setEditable(false);
                                    habilitarpanel1();
                                    id_obra_social = idobrasocial[i];
                                    band = 2;
                                    contadorobra = i;
                                    break;

                                } else {
                                    txtdocumento.setEditable(false);
                                    txtnombreafiliado.setEditable(false);
                                    txtnumafiliado.setEditable(false);
                                }
                            }

                            if (obra.equals("3102 - OSDE - OFFLINE")) {
                                new osdeOffline(this, true).setVisible(true);
                                habilitarpanel1();
                                id_obra_social = idobrasocial[i];
                                band = 3;
                                contadorobra = i;
                                break;
                            } else {
                                txtdocumento.setEditable(false);
                                txtnombreafiliado.setEditable(false);
                                txtnumafiliado.setEditable(false);
                            }

                            if (obra.equals("50015 - BOREAL")) {
                                new BorealAfiliado(this, true).setVisible(true);
                                if (BorealAfiliado.habilitado.equals("OK")) {
                                    txtdocumento.setText(BorealAfiliado.dni);
                                    txtdocumento.setEditable(false);
                                    txtfecha.setEditable(false);
                                    txtnombreafiliado.setText(BorealAfiliado.nombreafiliado);
                                    txtnumafiliado.setText(BorealAfiliado.Codigo_afiliado);
                                    txtmatricula.requestFocus();
                                    txtnumorden.setEditable(false);
                                    habilitarpanel1();
                                    id_obra_social = idobrasocial[i];
                                    band = 2;
                                    contadorobra = i;
                                    break;

                                } else {
                                    txtdocumento.setEditable(false);
                                    txtnombreafiliado.setEditable(false);
                                    txtnumafiliado.setEditable(false);
                                }
                            }
                            ////////////"9000 - ASOCIACION MUTUAL SANCOR"/////////////////////////////////////////////////////
                            if (obra.equals("9000 - ASOCIACION MUTUAL SANCOR")) {
                                new SancorAfiliado(this, true).setVisible(true);
                                if (SancorAfiliado.habilitado.equals("OK")) {
                                    txtdocumento.setText(SancorAfiliado.dni);
                                    txtdocumento.setEditable(false);
                                    txtnombreafiliado.setText(SancorAfiliado.nombreafiliado);
                                    txtnumafiliado.setText(SancorAfiliado.Codigo_afiliado);
                                    txtmatricula.requestFocus();
                                    txtfecha.setEditable(false);
                                    habilitarpanel1();
                                    id_obra_social = idobrasocial[i];
                                    band = 2;
                                    contadorobra = i;
                                    break;

                                } else {
                                    txtdocumento.setEditable(false);
                                    txtnombreafiliado.setEditable(false);
                                    txtnumafiliado.setEditable(false);
                                }
                            }
                            //
                            ///////////////////////////////////////////////////////////////////////////////
                            if (obra.equals("1805 - SUBSIDIO DE SALUD - ONLINE")) {
                                contadorPracticas = 0;
                                new SubsidioAfiliado(this, true).setVisible(true);
                                if (SubsidioAfiliado.habilitado.equals("OK")) {
                                    txtnumorden.setEditable(false);
                                    txtdocumento.setText(SubsidioAfiliado.dni);
                                    txtdocumento.setEditable(false);
                                    txtnombreafiliado.setText(SubsidioAfiliado.nombreafiliado);
                                    txtnumafiliado.setText(SubsidioAfiliado.Codigo_afiliado);
                                    txtmatricula.requestFocus();
                                    habilitarpanel1();
                                    txtnumorden.setEditable(false);
                                    txtfecha.setEditable(false);
                                    txtpractica.requestFocus();
                                    id_obra_social = idobrasocial[i];
                                    band = 2;
                                    contadorobra = i;
                                    break;
                                } else {
                                    txtdocumento.setEditable(false);
                                    txtnombreafiliado.setEditable(false);
                                    txtnumafiliado.setEditable(false);
                                }

                            }
                            ///////////////////////////////////////////////////////////////////////////////////////
                            if (obra.equals("1806 - SUBSIDIO DE SALUD - AUTORIZACION - ONLINE")) {
                                new SubsidioAfiliado(this, true).setVisible(true);
                                if (SubsidioAfiliado.habilitado.equals("OK")) {
                                    txtnumorden.setEditable(false);
                                    txtfecha.setEditable(false);
                                    txtdocumento.setText(SubsidioAfiliado.dni);
                                    txtdocumento.setEditable(false);
                                    txtnombreafiliado.setText(SubsidioAfiliado.nombreafiliado);
                                    txtnumafiliado.setText(SubsidioAfiliado.Codigo_afiliado);
                                    txtmatricula.requestFocus();
                                    habilitarpanel1();
                                    txtnumorden.setEditable(false);
                                    txtpractica.requestFocus();
                                    id_obra_social = idobrasocial[i];
                                    band = 2;
                                    contadorobra = i;
                                    break;

                                } else {
                                    txtdocumento.setEditable(false);
                                    txtnombreafiliado.setEditable(false);
                                    txtnumafiliado.setEditable(false);
                                }

                            }
                            ///////////////////////////////////////////////////////////////////////////////
                            if (obra.equals("10070 - SWISS MEDICAL GROUP S.A. - ONLINE")) {
                                new SwissAfiliado(this, true).setVisible(true);
                                if (SwissAfiliado.habilitado.equals("OK")) {
                                    txtdocumento.setText("11111111");
                                    txtdocumento.setEditable(false);
                                    txtfecha.setEditable(false);
                                    txtnombreafiliado.setText(SwissAfiliado.nombreafiliado);
                                    txtnumafiliado.setText(SwissAfiliado.Codigo_afiliado);
                                    txtmatricula.requestFocus();
                                    habilitarpanel1();
                                    id_obra_social = idobrasocial[i];
                                    band = 2;
                                    contadorobra = i;
                                    break;

                                } else {
                                    txtdocumento.setEditable(false);
                                    txtnombreafiliado.setEditable(false);
                                    txtnumafiliado.setEditable(false);
                                }
                            }
                            if (obra.equals("512 - MEDIFE - ONLINE OBLIGATORIO PRE PAGA C.M.C.  S.A.") || obra.equals("510 - MEDIFE - OBLIGATORIO- PRE PAGA C.M.C.  S.A.")) {
                                idObraSocialOnline = idobrasocial[i];
                                new MedifeAfiliado(this, true).setVisible(true);
                                if (MedifeAfiliado.habilitado.equals("OK")) {
                                    txtdocumento.setText(MedifeAfiliado.dni);
                                    txtdocumento.setEditable(false);
                                    txtnombreafiliado.setText(MedifeAfiliado.nombreafiliado);
                                    txtnumafiliado.setText(MedifeAfiliado.Codigo_afiliado);
                                    if (MedifeAfiliado.plan.equals("GRAV^VOLUNTARIO")) {//GRAV^VOLUNTARIO
                                        tipo_orden = 0;
                                    } else {
                                        tipo_orden = 1;
                                    }
                                    System.out.println("tipo_orden Medife:" + tipo_orden);
                                    txtmatricula.requestFocus();
                                    txtfecha.setEditable(false);
                                    txtnumorden.setEditable(false);
                                    habilitarpanel1();
                                    id_obra_social = idobrasocial[i];
                                    band = 2;
                                    contadorobra = i;
                                    break;

                                } else {
                                    txtdocumento.setEditable(false);
                                    txtnombreafiliado.setEditable(false);
                                    txtnumafiliado.setEditable(false);
                                }
                            }
                            if (obra.equals("513 - MEDIFE - ONLINE VOLUNTARIO PRE PAGA C.M.C.  S.A.") || obra.equals("511 - MEDIFE - VOLUNTARIO- PRE PAGA C.M.C.  S.A.")) {
                                idObraSocialOnline = idobrasocial[i];
                                new MedifeAfiliado(this, true).setVisible(true);
                                if (MedifeAfiliado.habilitado.equals("OK")) {
                                    txtdocumento.setText(MedifeAfiliado.dni);
                                    txtdocumento.setEditable(false);
                                    txtnombreafiliado.setText(MedifeAfiliado.nombreafiliado);
                                    txtnumafiliado.setText(MedifeAfiliado.Codigo_afiliado);
                                    if (MedifeAfiliado.plan.equals("GRAV^VOLUNTARIO")) {//GRAV^VOLUNTARIO
                                        tipo_orden = 0;
                                    } else {
                                        tipo_orden = 1;
                                    }
                                    System.out.println("tipo_orden Medife:" + tipo_orden);
                                    txtmatricula.requestFocus();
                                    txtfecha.setEditable(false);
                                    txtnumorden.setEditable(false);
                                    habilitarpanel1();
                                    id_obra_social = idobrasocial[i];
                                    band = 2;
                                    contadorobra = i;
                                    break;

                                } else {
                                    txtdocumento.setEditable(false);
                                    txtnombreafiliado.setEditable(false);
                                    txtnumafiliado.setEditable(false);
                                }
                            }
                            if (obra.equals("37701 - JERARQUICOS SALUD - EMP. BNA - ONLINE")) {
                                try {
                                    new JerarquicosAfiliado(this, true).setVisible(true);
                                } catch (DatatypeConfigurationException ex) {
                                    Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (JerarquicosAfiliado.habilitado.equals("OK")) {
                                    txtdocumento.setText(JerarquicosAfiliado.dni);
                                    txtdocumento.setEditable(false);
                                    txtfecha.setEditable(false);
                                    txtnumorden.setEditable(false);
                                    txtnombreafiliado.setText(JerarquicosAfiliado.nombreafiliado);
                                    txtnumafiliado.setText(JerarquicosAfiliado.Codigo_afiliado);
                                    txtmatricula.requestFocus();
                                    habilitarpanel1();
                                    id_obra_social = idobrasocial[i];
                                    band = 2;
                                    contadorobra = i;
                                    break;

                                } else {
                                    txtdocumento.setEditable(false);
                                    txtnombreafiliado.setEditable(false);
                                    txtnumafiliado.setEditable(false);
                                }
                            }
                            if (obra.equals("40600 - OSPE- OBRA SOCIAL DE PETROLEROS")) {
                                new OspeAfiliado(this, true).setVisible(true);
                                if (OspeAfiliado.habilitado.equals("OK")) {
                                    txtdocumento.setText(OspeAfiliado.dni);
                                    txtdocumento.setEditable(false);
                                    txtfecha.setEditable(false);
                                    txtnumorden.setEditable(false);
                                    txtnombreafiliado.setText(OspeAfiliado.nombreafiliado);
                                    txtnumafiliado.setText(OspeAfiliado.Codigo_afiliado);
                                    txtmatricula.requestFocus();
                                    habilitarpanel1();
                                    id_obra_social = idobrasocial[i];
                                    band = 2;
                                    contadorobra = i;
                                    break;

                                } else {
                                    txtdocumento.setEditable(false);
                                    txtnombreafiliado.setEditable(false);
                                    txtnumafiliado.setEditable(false);
                                }
                            }
                            if (obra.equals("2700 - UNT - Accion  Social de la  UNT")) {
                                new AsuntAfiliado(this, true).setVisible(true);
                                if (AsuntAfiliado.habilitado.equals("OK")) {
                                    txtdocumento.setText(AsuntAfiliado.dni);
                                    txtdocumento.setEditable(false);
                                    txtfecha.setEditable(false);
                                    txtnumorden.setEditable(false);
                                    txtnombreafiliado.setText(AsuntAfiliado.nombreafiliado);
                                    txtnumafiliado.setText(AsuntAfiliado.Codigo_afiliado);
                                    txtmatricula.requestFocus();
                                    habilitarpanel1();
                                    id_obra_social = idobrasocial[i];
                                    band = 2;
                                    contadorobra = i;
                                    break;
                                } else {
                                    txtdocumento.setEditable(false);
                                    txtnombreafiliado.setEditable(false);
                                    txtnumafiliado.setEditable(false);
                                }
                            }
                        } else {
                            habilitarpanel1();
                            id_obra_social = idobrasocial[i];
                            band = 1;
                            contadorobra = i;
                            break;
                        }
                    }
                    i++;
                }
                if (band == 0) {
                    ///JOptionPane.showMessageDialog(null, "La obra social no se encuentra en nuestra base de datosssss...");
                    txtobrasocial.requestFocus();
                }
                if (band == 1) {
                    if (banderamodifica == 0) {
                        txtobrasocial.setEditable(false);
                        cargarpracticaconobra();
                        txtobrasocial.transferFocus();
                    } else {
                        txtobrasocial.setEditable(false);
                        cargarpracticaconobra();
                        txtpractica.requestFocus();
                        borrartabla();
                    }

                }
                if (band == 2) {
                    if (banderamodifica == 0) {
                        txtobrasocial.setEditable(false);
                        cargarpracticaconobra();

                        txtmatricula.requestFocus();
                    } else {
                        txtobrasocial.setEditable(false);
                        cargarpracticaconobra();
                        txtpractica.requestFocus();
                        borrartabla();
                    }

                }

                if (band == 3) {
                    if (banderamodifica == 0) {
                        txtobrasocial.setEditable(false);
                        cargarpracticaconobra();

                    } else {
                        txtobrasocial.setEditable(false);
                        cargarpracticaconobra();
                        borrartabla();
                    }
                    txtdocumento.requestFocus();
                }

            } else {
                if (validacion_pami == true) {
                    int i = 0;
                    while (i < contadorobrasocial) {
                        if (obra.equals(obrasocial[i])) {
                            habilitarpanel1();
                            id_obra_social = idobrasocial[i];
                            band = 1;
                            contadorobra = i;
                            break;
                        }
                        i++;
                    }
                    if (band == 0) {
                        JOptionPane.showMessageDialog(null, "La obra social no se encuentra en nuestra base de datos...");
                        txtobrasocial.requestFocus();
                    } else {
                        //--------------------------------------------------------------------------------------------
                        cbotipo.setVisible(true);
                        txtDiaOrden.setVisible(true);
                        txtobrasocial.setEditable(false);
                        txtDiaOrden.setVisible(true);
                        jLabel21.setVisible(true);
                        cargarpracticaconobra();
                        cbotipo.requestFocus();
                        //--------------------------------------------------------------------------------------------
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No tiene permiso para facturar PAMI en estos momentos");
                    txtobrasocial.setText("");
                }
            }
            if (id_obra_social == 108) {
                cargarPracticas(id_obra_social);
            }
        }
        cargarperiodo();
    }//GEN-LAST:event_txtobrasocialActionPerformed

    private void btnsalir4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir4ActionPerformed
        try {
            Desktop.getDesktop().browse(new URI("C:/Descargas-CBT/"));
            //dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido cargar la ruta");
        }
    }//GEN-LAST:event_btnsalir4ActionPerformed

    private void txtfechacoseguroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfechacoseguroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtfechacoseguro.transferFocus();
        }
    }//GEN-LAST:event_txtfechacoseguroKeyPressed

    private void chkcoseguroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkcoseguroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chkcoseguro.isSelected()) {

                if (!txtobrasocial.getText().equals("40813 - IOSFA")) {
                    txtcoseguro.setEnabled(true);
                    jLabel24.setEnabled(true);
                    txtfechacoseguro.setEnabled(true);
                    jLabel25.setEnabled(true);
                    chkcoseguro.transferFocus();
                } else {
                    txtpractica.requestFocus();
                }

            } else {
                if (txtpractica.isEditable()) {
                    txtcoseguro.setEnabled(false);
                    jLabel24.setEnabled(false);
                    txtfechacoseguro.setEnabled(false);
                    jLabel25.setEnabled(false);
                    txtpractica.requestFocus();
                } else {
                    txtcoseguro.setEnabled(false);
                    jLabel24.setEnabled(false);
                    txtfechacoseguro.setEnabled(false);
                    jLabel25.setEnabled(false);
                    btnaceptar.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_chkcoseguroKeyPressed

    private void chkcoseguroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkcoseguroActionPerformed
        if (chkcoseguro.isSelected()) {
            if (!txtobrasocial.getText().equals("40813 - IOSFA")) {
                txtcoseguro.setEnabled(true);
                txtcoseguro.setEditable(true);
                txtcoseguro.selectAll();
                txtfechacoseguro.setText(fecha);
                jLabel24.setEnabled(true);
                txtfechacoseguro.setEnabled(true);
                jLabel25.setEnabled(true);
                chkcoseguro.transferFocus();
            } else {
                txtpractica.requestFocus();
            }
        } else {
            txtcoseguro.setEnabled(false);
            jLabel24.setEnabled(false);
            txtfechacoseguro.setEnabled(false);
            jLabel25.setEnabled(false);
            txtpractica.requestFocus();
        }
    }//GEN-LAST:event_chkcoseguroActionPerformed

    private void txtcoseguroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcoseguroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtcoseguro.transferFocus();
        }
    }//GEN-LAST:event_txtcoseguroKeyPressed

    private void btncancelar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar3ActionPerformed
        new Periodo(this, true).setVisible(true);
        if (!periododjj.equals("") || !Periodo.mes.equals("") || !Periodo.año.equals("")) {
            new Resumen_OS(this, true).setVisible(true);
        }
    }//GEN-LAST:event_btncancelar3ActionPerformed

    private void HabilitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HabilitaActionPerformed
        habilitartabla();
    }//GEN-LAST:event_HabilitaActionPerformed

    private void txtnumafiliadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumafiliadoKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {

            int ban = 0;
            if (id_obra_social != 58) {
                if (!txtnumafiliado.getText().equals("")) {
                    ban = 1;
                }
                if (ban == 1) {
                    txtmatricula.requestFocus();
                } else {
                    txtnumafiliado.requestFocus();
                }
            } else {
                if (!txtnumafiliado.getText().equals("") && txtnumafiliado.getText().length() == 14) {
                    ban = 1;
                }
                if (ban == 1) {
                    txtmatricula.setEnabled(true);
                    txtmatricula.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Número de afiliado inválido. Recuerde que son 14 dígitos");
                    txtnumafiliado.requestFocus();
                }
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_F1) {

            jTabbedPane2.setSelectedIndex(0);

        }

        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {

            habilitacionPanelDos();
        }
    }//GEN-LAST:event_txtnumafiliadoKeyPressed

    private void txtordenesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtordenesKeyReleased
        sorter = new TableRowSorter(model_tabla_facturacion);
        if (!txtordenes.getText().equals("")) {
            sorter.setRowFilter(RowFilter.regexFilter(".*" + txtordenes.getText() + ".*"));
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(".*.*"));
        }
        tablaordenes.setRowSorter(sorter);
    }//GEN-LAST:event_txtordenesKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Desktop.getDesktop().browse(new URI("http://www.cobituc.org.ar/2017/09/04/sistema-de-asistencia-remota-cbt/"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido cargar la página");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtcoseguroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcoseguroActionPerformed
        txtfechacoseguro.setEditable(true);
        txtfechacoseguro.select(0, 0);
    }//GEN-LAST:event_txtcoseguroActionPerformed

    private void txtordenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtordenesActionPerformed

    }//GEN-LAST:event_txtordenesActionPerformed

    private void txtmatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmatriculaActionPerformed

    private void btnsalir5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir5ActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnsalir5ActionPerformed

    private void ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImprimirActionPerformed
        ConexionMariaDB cc = new ConexionMariaDB();
        Connection cn = cc.Conectar();
        ////////////////Previsualizacion///////////////////////////
        JFrame viewer = new JFrame();
        viewer.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        viewer.setSize(800, 600);
        viewer.setLocationRelativeTo(null);
        JasperViewer jv = null;
        ///////////////////////////////////////////////////////////
        Map parametros = new HashMap();
        parametros.put("id_orden", Integer.parseInt(tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 0).toString().trim()));
        String nombre_jasper = "";
        int b = 0;
        if (tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().toString().equals("SUBSIDIO DE SALUD - ONLINE")) {
            nombre_jasper = "Comprobante_subsidio_1";
            b = 1;
        }
        if (tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().toString().equals("BOREAL")) {
            nombre_jasper = "Comprobante_boreal_1";
            b = 1;
        }
//        if (tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().toString().equals("IOSFA")) {
//            nombre_jasper = "Comprobante_iosfa";
//            b = 1;
//        }
        if (tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().toString().equals("DASUTeN  - Direc. Accion Social  U.Tec")) {
            nombre_jasper = "Comprobante_coseguro";
            b = 1;
        }
        if (b == 0) {
            nombre_jasper = "Comprobante";
        }
        if (tablaordenes.getValueAt(tablaordenes.getSelectedRow(), 2).toString().toString().equals("OSPE")) {
            nombre_jasper = "Comprobante_Ospe";
            b = 1;
        }
        try {
            JasperReport report_comprobante = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/" + nombre_jasper + ".jasper"));
            JasperPrint jPrint_comprobante = JasperFillManager.fillReport(report_comprobante, parametros, cn);
            //JasperExportManager.exportReportToPdfFile(jPrint_validacion, "C:\\Descargas-CBT\\" + periodo + "-" + txtcolegiado.getText() + "-validacion-.pdf");
            //JasperPrintManager.printReport(jPrint_comprobante, true);
            jv = new JasperViewer(jPrint_comprobante, false);
            viewer.getContentPane().add(jv.getContentPane());
            viewer.setVisible(true);

        } catch (JRException ex) {
            System.err.println("Error iReport: " + ex.getMessage());
        }
    }//GEN-LAST:event_ImprimirActionPerformed

    private void btnnbuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnbuActionPerformed
        new Tabla_NBU(null, true).setVisible(true);

    }//GEN-LAST:event_btnnbuActionPerformed

    private void btnnbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnnbuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnbuKeyPressed

    private void txtnumordenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnumordenActionPerformed

        System.out.println("enter");


    }//GEN-LAST:event_txtnumordenActionPerformed

    private void txtDiaOrdenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiaOrdenKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {

            if (!txtDiaOrden.getText().equals("")) {
                if (isNumeric(txtDiaOrden.getText()) && txtDiaOrden.getText().length() == 2) {
                    int mes = Integer.valueOf(txtmes.getText()), dia = Integer.valueOf(txtDiaOrden.getText());
                    if (dia > 0) {

                        switch (mes) {
                            case 1:
                            case 3:
                            case 5:
                            case 7:
                            case 8:
                            case 10:
                            case 12:
                                if (dia <= 31) {
                                    txtpractica.requestFocus();
                                    jLabel12.setEnabled(true);
                                    txtpractica.setEnabled(true);
                                    txtpractica.setEditable(true);
                                    tablapracticas.setEnabled(true);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Verifique la fecha");
                                }
                                break;
                            case 4:
                            case 6:
                            case 9:
                            case 11:
                                if (dia <= 30) {
                                    txtpractica.requestFocus();
                                    jLabel12.setEnabled(true);
                                    txtpractica.setEnabled(true);
                                    txtpractica.setEditable(true);
                                    tablapracticas.setEnabled(true);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Verifique la fecha");
                                }
                                break;
                            default:
                                if (Integer.valueOf(txtaño.getText()) % 4 == 0) {
                                    if (dia <= 29) {
                                        jLabel12.setEnabled(true);
                                        txtpractica.requestFocus();
                                        txtpractica.setEnabled(true);
                                        txtpractica.setEditable(true);
                                        tablapracticas.setEnabled(true);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Verifique la fecha");
                                    }
                                } else {
                                    if (dia <= 28) {
                                        jLabel12.setEnabled(true);
                                        txtpractica.requestFocus();
                                        txtpractica.setEnabled(true);
                                        txtpractica.setEditable(true);
                                        tablapracticas.setEnabled(true);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Verifique la fecha");
                                    }
                                }
                                break;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Verifique la fecha");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Verifique la fecha");
                }
            }

        }
    }//GEN-LAST:event_txtDiaOrdenKeyPressed

    private void txtDiaOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaOrdenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaOrdenActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new Acercade(null, true).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        new Inicio().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
        new Inicio().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        dispose();
        new Inicio().setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dispose();
        new Inicio().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (!txtobrasocial.getText().equals("")) {
            nombre_obrasocial = txtobrasocial.getText();
            new Detalle_Obrasocial(null, true).setVisible(true);
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void cbotipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotipoActionPerformed

    }//GEN-LAST:event_cbotipoActionPerformed

    private void cbotipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbotipoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtdocumento.requestFocus();
        }
    }//GEN-LAST:event_cbotipoKeyPressed

    private void btnPrensaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrensaActionPerformed

        try {
            Desktop.getDesktop().browse(new URI("http://prensanet.osppt.org.ar:8080/PrensaNet/servlet/com.prensanet.login"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido cargar la página");
        }

    }//GEN-LAST:event_btnPrensaActionPerformed

    private void btnSubsidioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubsidioActionPerformed

        try {
            Desktop.getDesktop().browse(new URI("https://validaciones.ipsst.gov.ar/"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido cargar la página");
        }

    }//GEN-LAST:event_btnSubsidioActionPerformed

    private void btnIosfaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIosfaActionPerformed

        try {
            Desktop.getDesktop().browse(new URI("http://validador.iosfa.gob.ar:5080/Login/Login.aspx"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido cargar la página");
        }

    }//GEN-LAST:event_btnIosfaActionPerformed

    private void txtdocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdocumentoActionPerformed
        txtdocumento.transferFocus();
        int band = 0, band2 = 0;
        ConexionMariaDB mysql = new ConexionMariaDB();
        Connection cn = mysql.Conectar();
        String dni = txtdocumento.getText();
        if (!dni.equals("")) {
            if (!dni.equals("11111111") && !dni.equals("22222222") && !dni.equals("33333333") && !dni.equals("44444444") && !dni.equals("55555555") && !dni.equals("66666666") && !dni.equals("77777777") && !dni.equals("88888888") && !dni.equals("99999999") && !dni.equals("00000000") && dni.length() >= 4) {
                int i = 0, obrasocial = 0;

                if (id_obra_social == 11 || id_obra_social == 12 || id_obra_social == 13 || id_obra_social == 14 || id_obra_social == 15 || id_obra_social == 90) {
                    obrasocial = 11;
                } else {
                    obrasocial = id_obra_social;
                }
                //String sSQL = "SELECT nombre_afiliado,dni_afiliado,numero_afiliado FROM afiliados WHERE (dni_afiliado=" + dni + "  AND id_obra_social=" + id_obra_social + ") OR (numero_afiliado=" + dni + "  AND id_obra_social=" + id_obra_social + ") ";
                if (id_obra_social == 58) {

                    txtdocumento.transferFocus();

                    try {

                        String sSQL = "SELECT nombre_afiliado,dni_afiliado,numero_afiliado FROM afiliados WHERE (dni_afiliado=" + dni + "  AND id_obra_social=" + 58 + ") OR (numero_afiliado=" + dni + "  AND id_obra_social=" + 58 + ") ";
                        Statement st = cn.createStatement();
                        ResultSet rs = st.executeQuery(sSQL);
                        if (rs.next()) {
                            txtnombreafiliado.setText(rs.getString("nombre_afiliado"));
                            txtnumafiliado.setText(rs.getString("numero_afiliado"));
                            txtdocumento.setText(rs.getString("dni_afiliado"));
                            txtnombreafiliado.setEnabled(true);
                            txtnombreafiliado.setEditable(false);
                            txtnumafiliado.setEnabled(true);
                            txtnumafiliado.requestFocus();
                            band = 1;
                        }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                    if (band == 0) {
                        try {
                            String sSQL = "SELECT dni_persona,apellido_persona,nombre_persona FROM personas WHERE dni_persona=" + dni;
                            Statement st = cn.createStatement();
                            ResultSet rs = st.executeQuery(sSQL);
                            if (rs.next()) {
                                documento_afiliado = dni;
                                nombre_afiliado = rs.getString("apellido_persona") + " " + rs.getString("nombre_persona");
                                numero_afiliado = "";
                                band2 = 1;
                                txtdocumento.setText(documento_afiliado);
                                txtnombreafiliado.setEnabled(true);
                                txtnombreafiliado.setEditable(false);
                                txtnombreafiliado.setText(nombre_afiliado);
                                txtnumafiliado.setText(numero_afiliado);
                                txtnumafiliado.setEnabled(true);
                                txtnumafiliado.requestFocus();
                            }
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                        if (band2 == 0) {
                            txtdocumento.setEnabled(true);
                            txtnombreafiliado.setEditable(true);
                            txtnombreafiliado.setEnabled(true);
                            txtnumafiliado.setEnabled(true);
                            txtnombreafiliado.requestFocus();
                        }
                    }

                } else {

                    try {
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
                }

            } else {
                if (id_obra_social != 58) {
                    txtdocumento.setEnabled(true);
                    txtdocumento.setEditable(true);
                    txtnombreafiliado.setEnabled(true);
                    txtnombreafiliado.setEditable(true);
                    txtnumafiliado.setText("");
                    txtnumafiliado.setEnabled(true);
                    txtnumafiliado.setEditable(true);
                    jLabel18.setEnabled(true);
                    txtnombreafiliado.requestFocus();
                } else {
                    txtdocumento.setText("");
                    txtdocumento.requestFocus();
                    JOptionPane.showMessageDialog(null, "Debe ingresar un número de documento valido");
                }
            }
        }
    }//GEN-LAST:event_txtdocumentoActionPerformed

    private void txtdocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdocumentoKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtnombreafiliado.setEnabled(true);
            txtnombreafiliado.setEditable(false);
            /*  if (obra.equals("1800 - SUBSIDIO DE SALUD - IPSSPT") || obra.equals("1801 - SUBSIDIO DE SALUD - MATERNO INFANTIL")
                    || obra.equals("1803 - SUBSIDIO DE SALUD - RECIPROCIDAD") || obra.equals("1804 - SUBSIDIO DE SALUD - INTERNADO")
                    || obra.equals("1810 - SUBSIDIO DE SALUD - PRODIASS-PLAN PREVENCION")) {
                try {
                    txtnumafiliado.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##-######-##")));
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            } else {
                txtnumafiliado.setFormatterFactory(new DefaultFormatterFactory());
            }*/
        }
        /*if ((evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) && (txtdocumento.getText().equals(""))) {
            txtobrasocial.setEnabled(true);
            txtobrasocial.setText("");
            txtobrasocial.setEditable(true);
            txtobrasocial.requestFocus();
            txtdocumento.setText("");
            deshabilitarpanel1();
        }*/
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            jTabbedPane2.setSelectedIndex(0);
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            habilitacionPanelUno();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {
            habilitacionPanelDos();

        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {

            jTabbedPane2.setSelectedIndex(3);
        }
    }//GEN-LAST:event_txtdocumentoKeyPressed

    private void txtdocumentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdocumentoKeyReleased
        if (txtdocumento.getText().equals("")) {
            txtnombreafiliado.setText("");
            txtnumafiliado.setText("");
            txtdocumento.requestFocus();
        }
    }//GEN-LAST:event_txtdocumentoKeyReleased

    private void txtdocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdocumentoKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtdocumentoKeyTyped

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            Desktop.getDesktop().browse(new URI("http://www.cobituc.org.ar/sistemainformacion/"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido cargar la página");
        }

    }//GEN-LAST:event_jButton8ActionPerformed

    private void txtaño3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaño3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtaño3ActionPerformed

    private void formatoArchivoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formatoArchivoBotonActionPerformed
        try {
            Desktop.getDesktop().open(new File("C:\\Facturacion Laboratorios\\FTO.PDF"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo");
        }

    }//GEN-LAST:event_formatoArchivoBotonActionPerformed

    private void btnSubsidio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubsidio1ActionPerformed
        try {
            Desktop.getDesktop().browse(new URI("https://prestadores.pami.org.ar/result.php?c=6-2&vm=2"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido cargar la página");
        }
    }//GEN-LAST:event_btnSubsidio1ActionPerformed

    private void jPanel7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel7KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel7KeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Anular;
    private javax.swing.JPanel Facturacion;
    private javax.swing.JMenuItem Habilita;
    private javax.swing.JMenuItem Imprimir;
    private javax.swing.JMenuItem Modificar;
    private javax.swing.JPanel Ordenes;
    private javax.swing.JMenuItem Practicas;
    private javax.swing.JPanel Utilitarios;
    private javax.swing.JButton btnIosfa;
    private javax.swing.JButton btnPrensa;
    private javax.swing.JButton btnSubsidio;
    private javax.swing.JButton btnSubsidio1;
    private javax.swing.JButton btnaceptar;
    private javax.swing.JButton btnaceptar2;
    private javax.swing.JButton btnborrar;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncancelar1;
    private javax.swing.JButton btncancelar2;
    private javax.swing.JButton btncancelar3;
    private javax.swing.JButton btnimportar;
    private javax.swing.JButton btnimprimirdjj;
    private javax.swing.JButton btnimprimirobra;
    private javax.swing.JButton btnnbu;
    private javax.swing.JButton btnnomenclador;
    private javax.swing.JButton btnobrasocial;
    private javax.swing.JButton btnobrasociales;
    private javax.swing.JButton btnpaciente;
    private javax.swing.JButton btnsalir;
    private javax.swing.JButton btnsalir1;
    private javax.swing.JButton btnsalir2;
    private javax.swing.JButton btnsalir3;
    private javax.swing.JButton btnsalir4;
    private javax.swing.JButton btnsalir5;
    private javax.swing.JComboBox<String> cbotipo;
    private javax.swing.JCheckBox chkcoseguro;
    private javax.swing.JButton formatoArchivoBoton;
    private javax.swing.ButtonGroup grupoBoton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblcolegiado;
    private javax.swing.JLabel lblcolegiado1;
    private javax.swing.JLabel lblcolegiado2;
    private javax.swing.JLabel lblcolegiado3;
    private javax.swing.JProgressBar progreso;
    private javax.swing.JProgressBar progreso3;
    private javax.swing.JTable tablaordenes;
    private javax.swing.JTable tablaordenes1;
    private javax.swing.JTable tablapracticas;
    private javax.swing.JTextField txtDiaOrden;
    private javax.swing.JFormattedTextField txtaño;
    private javax.swing.JFormattedTextField txtaño1;
    private javax.swing.JFormattedTextField txtaño3;
    private javax.swing.JTextField txtcoseguro;
    private javax.swing.JTextField txtdocumento;
    private javax.swing.JFormattedTextField txtfecha;
    private javax.swing.JFormattedTextField txtfechacoseguro;
    private javax.swing.JTextField txtmatricula;
    private javax.swing.JFormattedTextField txtmes;
    private javax.swing.JFormattedTextField txtmes1;
    private javax.swing.JFormattedTextField txtmes3;
    private javax.swing.JTextField txtnombreafiliado;
    private javax.swing.JTextPane txtnovedad;
    private javax.swing.JTextField txtnumafiliado;
    private javax.swing.JFormattedTextField txtnumorden;
    private javax.swing.JTextField txtobrasocial;
    private javax.swing.JTextField txtordenes;
    private javax.swing.JTextField txtpractica;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txttotal1;
    private javax.swing.JTextField txttotalanuladas;
    private javax.swing.JTextField txttotalobservadas;
    private javax.swing.JTextField txttotalordenes;
    private javax.swing.JTextField txttotalordenes1;
    // End of variables declaration//GEN-END:variables

    private static WsBorealExecuteResponse execute(ClienteBoreal.WsBorealExecute parameters) {
        ClienteBoreal.WsBoreal service = new ClienteBoreal.WsBoreal();
        ClienteBoreal.WsBorealSoapPort port = service.getWsBorealSoapPort();
        return port.execute(parameters);
    }

    private static PAWESSAV2AUTORIZACIONResponse autorizacion(ClienteSancor.PAWESSAV2AUTORIZACION parameters) {
        ClienteSancor.PAWESSAV2 service = new ClienteSancor.PAWESSAV2();
        ClienteSancor.PAWESSAV2SoapPort port = service.getPAWESSAV2SoapPort();
        return port.autorizacion(parameters);
    }

    private static PAWESSAV2ANULACIONResponse anulacion(ClienteSancor.PAWESSAV2ANULACION parameters) {
        ClienteSancor.PAWESSAV2 service = new ClienteSancor.PAWESSAV2();
        ClienteSancor.PAWESSAV2SoapPort port = service.getPAWESSAV2SoapPort();
        return port.anulacion(parameters);
    }

    private static OrdenAutorizarCHEQUEARFACTIBILIDADResponse chequearfactibilidad(ClienteIPSST3.OrdenAutorizarCHEQUEARFACTIBILIDAD parameters) {
        ClienteIPSST3.OrdenAutorizar service = new ClienteIPSST3.OrdenAutorizar();
        ClienteIPSST3.OrdenAutorizarSoapPort port = service.getOrdenAutorizarSoapPort();
        return port.chequearfactibilidad(parameters);
    }

    private static OrdenAutorizarEMITIRResponse emitir(ClienteIPSST3.OrdenAutorizarEMITIR parameters) {
        ClienteIPSST3.OrdenAutorizar service = new ClienteIPSST3.OrdenAutorizar();
        ClienteIPSST3.OrdenAutorizarSoapPort port = service.getOrdenAutorizarSoapPort();
        return port.emitir(parameters);
    }

    private static OrdenValidarExecuteResponse execute_1(ClienteIPSST4.OrdenValidarExecute parameters) {
        ClienteIPSST4.OrdenValidar service = new ClienteIPSST4.OrdenValidar();
        ClienteIPSST4.OrdenValidarSoapPort port = service.getOrdenValidarSoapPort();
        return port.execute(parameters);
    }

    private static OrdenDevolverExecuteResponse execute_2(ClienteIPSST5.OrdenDevolverExecute parameters) {
        ClienteIPSST5.OrdenDevolver service = new ClienteIPSST5.OrdenDevolver();
        ClienteIPSST5.OrdenDevolverSoapPort port = service.getOrdenDevolverSoapPort();
        return port.execute(parameters);
    }

    private static OrdenValidadaAnularExecuteResponse execute_3(ClienteIPSST6.OrdenValidadaAnularExecute parameters) {
        ClienteIPSST6.OrdenValidadaAnular service = new ClienteIPSST6.OrdenValidadaAnular();
        ClienteIPSST6.OrdenValidadaAnularSoapPort port = service.getOrdenValidadaAnularSoapPort();
        return port.execute(parameters);
    }

}
