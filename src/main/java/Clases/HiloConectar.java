

package Clases;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JProgressBar;


public class HiloConectar extends Thread {

    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    JProgressBar progreso;

    public HiloConectar(JProgressBar progreso1) {
        super();
        this.progreso = progreso1;
    }

    public void run() {
        for (int i = 1; (i <= 100); i++) {
            progreso.setValue(i);
            pausa(60);
        }
        //new Main().setVisible(true);
        
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
