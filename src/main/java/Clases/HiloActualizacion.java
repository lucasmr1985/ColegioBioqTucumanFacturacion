package Clases;


import static Clases.HiloInicio.link_descarga;
import static Clases.HiloInicio.version;
import static Clases.HiloInicio.version_actual;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class HiloActualizacion extends Thread {

    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    JProgressBar actualizacion;
    Logger logger = Logger.getLogger("MyLog");
    FileHandler fh;

    public HiloActualizacion(JProgressBar progreso1) {
        super();
        this.actualizacion = progreso1;
    }



    public void run() {
        int i = 0;
        
        while (i < 1) {
            actualizacion.setValue(i);
            i++;
            pausa(60);
        }

        
        String url =link_descarga;
        //dirección url del recurso a descargar
        String name = "Facturacion.exe";
        //Directorio destino para las descargas
        String folder = "C:\\Facturacion Laboratorios\\";
        //////////gordo puto
        //Crea el directorio de destino en caso de que no exista

        while (i < 3) {
            actualizacion.setValue(i);
            i++;
            pausa(60);
        }
        try {
            File dir = new File(folder);

            if (!dir.exists()) {
                if (!dir.mkdir()) {
                    return; // no se pudo crear la carpeta de destino
                }
            }
            while (i < 5) {
                actualizacion.setValue(i);
                i++;
                pausa(60);
            }
            File file = new File(folder + name);
            URLConnection conn = new URL(url).openConnection();
            conn.connect();
            while (i < 10) {
                actualizacion.setValue(i);
                i++;
                pausa(60);
            }

            System.out.println("\nempezando descarga: \n");
            System.out.println(">> URL: " + url);
            System.out.println(">> Nombre: " + name);
            System.out.println(">> tamaño: " + conn.getContentLength() + " bytes");
            int tamaño = conn.getContentLength();
            InputStream in = conn.getInputStream();
            OutputStream out = new FileOutputStream(file);

            ////////////////////////////
            int CHUNK_SIZE = 1024 * 8;
            byte[] chunk = new byte[CHUNK_SIZE];
            int bytesLeidos = 0;

            /////////////////////////////
            //     int b = 0;
            while (i < 15) {
                actualizacion.setValue(i);
                i++;
                pausa(60);
            }

            //////////prueba
            while ((bytesLeidos = in.read(chunk)) > 0) {
                actualizacion.setValue(i);
                i = i + 4000;
                //   System.out.println(">> tamaño leido: " + bytesLeidos + " bytes");
                out.write(chunk, 0, bytesLeidos);
            }
            actualizacion.setValue(100000000);
            out.close();
            in.close();
            ///////////////

            JOptionPane.showMessageDialog(null,"Se descargó correctamente, a continuación el programa se cerrará.\n \tPor lo que deberá volver a ingresar");
            System.exit(0);
            
            //rutaEXE = la ruta donde se encuentra el archivo .exe
            //rutaArc= la ruta donde se encuentra el archivo que desea abrir con el .exe
        } catch (MalformedURLException e) {
            System.out.println("la url: no es valida!");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "La url no es valida!");
            System.exit(0);
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
