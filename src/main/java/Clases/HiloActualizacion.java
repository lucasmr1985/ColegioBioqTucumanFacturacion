package Clases;


import static Clases.HiloInicio.link_descarga;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipFile;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 * Actualización automática de la aplicación.
 *
 * Con el runtime de Java embebido, la app se distribuye como una carpeta:
 *
 *     Facturacion\
 *     ├── Facturacion.exe      (lanzador nativo - NO se toca)
 *     ├── runtime\             (Java embebido - NO se toca)
 *     └── app\
 *         └── Facturacion.jar  (el programa - SOLO ESTO se actualiza)
 *
 * Por eso la actualización sigue siendo "reemplazar un solo archivo": el jar.
 *
 * En Windows no se puede sobreescribir un archivo en uso, así que:
 *   1) se descarga el jar nuevo a un temporal (Facturacion.jar.new),
 *   2) se verifica que esté completo y sea un jar válido,
 *   3) se lanza un pequeño .cmd externo y se cierra la app,
 *   4) el .cmd espera a que el jar se libere, respalda el actual (.bak),
 *      pone el nuevo en su lugar y vuelve a abrir la app sola.
 * Si algo falla, restaura el backup: el usuario nunca queda sin sistema.
 */
public class HiloActualizacion extends Thread {

    JProgressBar actualizacion;

    public HiloActualizacion(JProgressBar progreso1) {
        super();
        this.actualizacion = progreso1;
    }

    @Override
    public void run() {
        final int MAX = 100000000; // valor máximo de la barra (ver DescargarActualizacion)
        actualizacion.setValue(0);

        // URL del jar nuevo (viene de la base, campo linkdescarga)
        String url = link_descarga;
        if (url == null || url.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay un link de actualización configurado.");
            return;
        }

        // 1) Ubicar el jar que se está ejecutando (dónde estamos instalados)
        File jarActual = ubicarJarEnEjecucion();
        if (jarActual == null || !jarActual.getName().toLowerCase().endsWith(".jar")) {
            // Corriendo desde el IDE (no desde el jar empaquetado): no se puede autoactualizar
            JOptionPane.showMessageDialog(null,
                    "La actualización automática solo funciona sobre la aplicación instalada.");
            return;
        }
        File appDir = jarActual.getParentFile();                 // ...\Facturacion\app
        File installRoot = (appDir != null) ? appDir.getParentFile() : null; // ...\Facturacion
        if (installRoot == null) {
            installRoot = appDir;
        }
        File jarNuevo = new File(appDir, "Facturacion.jar.new");
        File exe = new File(installRoot, "Facturacion.exe");

        try {
            // 2) Descargar el jar nuevo al temporal, mostrando progreso real
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setInstanceFollowRedirects(true);
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(30000);
            conn.connect();

            int total = conn.getContentLength();
            System.out.println("\nempezando descarga:");
            System.out.println(">> URL: " + url);
            System.out.println(">> tamaño: " + total + " bytes");

            long descargado = 0;
            byte[] chunk = new byte[8 * 1024];
            try (InputStream in = conn.getInputStream();
                 OutputStream out = new BufferedOutputStream(new FileOutputStream(jarNuevo))) {
                int leidos;
                while ((leidos = in.read(chunk)) > 0) {
                    out.write(chunk, 0, leidos);
                    descargado += leidos;
                    if (total > 0) {
                        int v = (int) (descargado * (long) MAX / total);
                        if (v > MAX) {
                            v = MAX;
                        }
                        actualizacion.setValue(v);
                    }
                }
            }

            // 3) Verificar la descarga: tamaño completo + jar válido (abre como ZIP)
            if (total > 0 && descargado != total) {
                borrar(jarNuevo);
                JOptionPane.showMessageDialog(null,
                        "La descarga quedó incompleta. Se reintentará la próxima vez.");
                return;
            }
            if (!esJarValido(jarNuevo)) {
                borrar(jarNuevo);
                JOptionPane.showMessageDialog(null,
                        "El archivo descargado no es válido. Se reintentará la próxima vez.");
                return;
            }

            // 4) Preparar el helper que hace el reemplazo con la app cerrada y la relanza
            File cmd = crearScriptActualizador(jarActual, jarNuevo, exe);

            actualizacion.setValue(MAX);
            JOptionPane.showMessageDialog(null,
                    "La actualización se descargó correctamente.\n"
                    + "El programa se cerrará y se abrirá solo, actualizado.");

            // Lanzar el .cmd de forma independiente (sobrevive al cierre de la app)
            new ProcessBuilder("cmd.exe", "/c", "start", "", "/min",
                    cmd.getAbsolutePath()).start();

            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
            borrar(jarNuevo);
            JOptionPane.showMessageDialog(null,
                    "No se pudo completar la actualización. Se reintentará la próxima vez.");
        }
    }

    /** Ubica el .jar desde el que se está ejecutando esta clase. */
    private File ubicarJarEnEjecucion() {
        try {
            return new File(HiloActualizacion.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI());
        } catch (Exception e) {
            return null;
        }
    }

    /** Verifica que el archivo se pueda abrir como jar/zip (no está corrupto). */
    private boolean esJarValido(File f) {
        if (f == null || !f.exists() || f.length() == 0) {
            return false;
        }
        try (ZipFile zf = new ZipFile(f)) {
            return zf.size() > 0;
        } catch (IOException e) {
            return false;
        }
    }

    private void borrar(File f) {
        try {
            if (f != null && f.exists()) {
                f.delete();
            }
        } catch (Exception ignored) {
        }
    }

    /**
     * Genera un .cmd que espera a que el jar se libere, respalda el actual,
     * coloca el nuevo y vuelve a abrir la app. Si el reemplazo falla, restaura
     * el backup para no dejar la instalación rota.
     */
    private File crearScriptActualizador(File jarActual, File jarNuevo, File exe) throws IOException {
        File bak = new File(jarActual.getParentFile(), jarActual.getName() + ".bak");
        File cmd = File.createTempFile("actualizar_facturacion", ".cmd");

        String contenido =
            "@echo off\r\n" +
            "setlocal enabledelayedexpansion\r\n" +
            "set \"JAR=" + jarActual.getAbsolutePath() + "\"\r\n" +
            "set \"NEW=" + jarNuevo.getAbsolutePath() + "\"\r\n" +
            "set \"BAK=" + bak.getAbsolutePath() + "\"\r\n" +
            "set \"EXE=" + exe.getAbsolutePath() + "\"\r\n" +
            "set /a tries=0\r\n" +
            ":retry\r\n" +
            "if not exist \"%NEW%\" goto launch\r\n" +
            "if exist \"%BAK%\" del /q \"%BAK%\" 2>nul\r\n" +
            "if exist \"%JAR%\" move /y \"%JAR%\" \"%BAK%\" >nul 2>nul\r\n" +
            "if exist \"%JAR%\" (\r\n" +
            "  set /a tries+=1\r\n" +
            "  if !tries! GEQ 30 goto fail\r\n" +
            "  ping -n 2 127.0.0.1 >nul\r\n" +
            "  goto retry\r\n" +
            ")\r\n" +
            "move /y \"%NEW%\" \"%JAR%\" >nul 2>nul\r\n" +
            "if not exist \"%JAR%\" goto restore\r\n" +
            ":launch\r\n" +
            "start \"\" \"%EXE%\"\r\n" +
            "(goto) 2>nul & del \"%~f0\"\r\n" +
            "exit\r\n" +
            ":restore\r\n" +
            "if exist \"%BAK%\" move /y \"%BAK%\" \"%JAR%\" >nul 2>nul\r\n" +
            "start \"\" \"%EXE%\"\r\n" +
            "(goto) 2>nul & del \"%~f0\"\r\n" +
            "exit\r\n" +
            ":fail\r\n" +
            "if not exist \"%JAR%\" if exist \"%BAK%\" move /y \"%BAK%\" \"%JAR%\" >nul 2>nul\r\n" +
            "start \"\" \"%EXE%\"\r\n" +
            "(goto) 2>nul & del \"%~f0\"\r\n" +
            "exit\r\n";

        try (PrintWriter pw = new PrintWriter(cmd, "Cp1252")) {
            pw.print(contenido);
        }
        return cmd;
    }
}
