package Clases;

import Formularios.Login;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class HiloInicio extends Thread {

    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    JProgressBar progreso;
    public static String[] obrasocial = new String[500];
    public static int[] idobrasocial = new int[500];
    public static String[] nombreobrasocial = new String[500];
    public static String[] numafiliado = new String[500000];
    public static String[] nomafiliado = new String[500000];
    public static String[] dniafiliado = new String[500000];
    public static Double[] arancel = new Double[500];
    public static String[] practicaconobra = new String[150000];
    public static String[] analisis = new String[50000];
    public static ArrayList<Clases.MedicosAutorizados> listaMedicos;
    public static String novedad = "", version = "", aviso = "", link = "", link_descarga = "";
    public static String version_actual = "2113";
    public static int[] idobra = new int[150000];
    public static String[] precio_practica = new String[150000];
    public static int contadorpractica = 0;
    public static int contadoranalisis = 0;
    public static int contadorobrasocial = 0, bandera_inicio = 0, periodo_backup = 0, estado_aviso = 0;
    public static int contadorafiliado = 0;
    ///////////////////////////////////////////////////////////////////
    public static ArrayList<ObrasSociales> listaOS;

    public HiloInicio(JProgressBar progreso1) {
        super();
        this.progreso = progreso1;
    }

    public void run() {

        int i = 0;
        while (i < 30) {
            progreso.setValue(i);
            i++;
            pausa(10);
        }
        cargarnovedad();
        cargarperiodobackup();
        while (i < 50) {
            progreso.setValue(i);
            i++;
            //pausa(1);
        }
        cargaractualizacion();
        cargarMedicos();
        while (i <= 70) {
            progreso.setValue(i);
            i++;
            //pausa(1);
        }
        cargarobrasosial();
        CargarOsInformacionFactura();
        while (i <= 100) {
            progreso.setValue(i);
            i++;
            //pausa(1);
        }
        new Login().setVisible(true);
    }

    void CargarOsInformacionFactura() {
        try {
            ConexionMariaDB maria = new ConexionMariaDB();
            Connection cn = maria.Conectar();
            listaOS = new ArrayList<ObrasSociales>();
            ObrasSociales.CargarObraSocialInformacionFactura(cn, listaOS);
            cn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar al servidor... Verifique su conexión a internet");
        }
    }

    public static Date aDate(String strFecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(strFecha);
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        return fecha;
    }

    void cargarMedicos() {

        ConexionMariaDB conexion = new ConexionMariaDB();
        conexion.EstablecerConexion();
        listaMedicos = new ArrayList<Clases.MedicosAutorizados>();
        Clases.MedicosAutorizados.cargarMedicosAutorizados(conexion.getConnection(), listaMedicos);
        conexion.cerrarConexion();

    }

    void cargarnovedad() {
        ConexionMariaDB maria = new ConexionMariaDB();
        Connection cn = maria.Conectar();
        String sSQL = "SELECT titulo_novedades,fecha_publicacion FROM novedades_web where matricula_destinatario=0 order by id_novedades desc";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                if (novedad.equals("")) {
                    novedad = novedad + (rs.getString("fecha_publicacion") + " - " + rs.getString("titulo_novedades") + ". Visite nuestro sitio web cobituc.org.ar");
                } else {
                    novedad = novedad + "\r\n" + "\r\n" + (rs.getString("fecha_publicacion") + " - " + rs.getString("titulo_novedades") + ". Visite nuestro sitio web cobituc.org.ar");
                }

            }
            cn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar al servidor... Verifique su conexión a internet");
        }
    }

    void cargaractualizacion() {
        ConexionMariaDB maria = new ConexionMariaDB();
        Connection cn = maria.Conectar();
        String sSQL = "SELECT version FROM actulaizacion ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            if (rs.next()) {
                version = (rs.getString("version"));
            }
            cn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "No se pudo conectar al servidor... Verifique su conexión a internet");
        }
    }

    void cargarperiodobackup() {
        ConexionMariaDB maria = new ConexionMariaDB();
        Connection cn = maria.Conectar();
        try {
            String sSQL = "SELECT periodo_backup,estado_avisos, aviso, link, linkdescarga FROM periodos ";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            rs.next();
            periodo_backup = rs.getInt(1);
            estado_aviso = rs.getInt(2);
            aviso = rs.getString(3);
            link = rs.getString(4);
            link_descarga = rs.getString(5);
            cn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos");
        }
    }

    void cargaranalisis() {

        ConexionMariaDB maria = new ConexionMariaDB();
        Connection cn = maria.Conectar();
        String sSQL2 = "SELECT codigo_practica,id_practicasnbu,determinacion_practica FROM practicasnbu ";

        try {
            Statement st2 = cn.createStatement();
            ResultSet rs2 = st2.executeQuery(sSQL2);
            // Recorro y cargo las obras sociales
            while (rs2.next()) {
                analisis[contadoranalisis] = (rs2.getString("codigo_practica") + " - " + rs2.getString("determinacion_practica"));
                contadoranalisis++;
            }
            cn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    void cargarobrasosial() {

        ConexionMariaDB mysql = new ConexionMariaDB();
        Connection cn = mysql.Conectar();
        idobrasocial = new int[500];
        nombreobrasocial = new String[500];
        arancel = new Double[500];
        obrasocial = new String[500];
        contadorobrasocial = 0;
        String sSQL = "SELECT id_obrasocial,razonsocial_obrasocial,importeunidaddearancel_obrasocial,codigo_obrasocial,añonbu FROM obrasocial where estado_obrasocial=1";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            // Recorro y cargo las obras sociales
            while (rs.next()) {
                idobrasocial[contadorobrasocial] = rs.getInt("id_obrasocial");
                nombreobrasocial[contadorobrasocial] = (rs.getString("razonsocial_obrasocial"));
                arancel[contadorobrasocial] = (rs.getDouble("importeunidaddearancel_obrasocial"));
                obrasocial[contadorobrasocial] = (rs.getString("codigo_obrasocial") + " - " + rs.getString("razonsocial_obrasocial"));
                contadorobrasocial++;
            }
            cn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar al servidor... Verifique su conexión a internet");
        }
    }

    public static int fechasDiferenciaEnDias(Date fechaInicial, Date fechaFinal) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(fechaInicial);
        try {
            fechaInicial = df.parse(fechaInicioString);
        } catch (ParseException ex) {
        }

        String fechaFinalString = df.format(fechaFinal);
        try {
            fechaFinal = df.parse(fechaFinalString);
        } catch (ParseException ex) {
        }
        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        return ((int) dias);
    }

    public void pausa(int mlSeg) {
        try {
            // pausa para el splash
            Thread.sleep(mlSeg);
        } catch (Exception e) {
        }

    }
}
