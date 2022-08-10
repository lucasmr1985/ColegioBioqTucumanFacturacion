package ClienteOsde;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 *
 * @author Lucas Robles
 */
public class OsdeConexionWsdl {

    private final String USER_AGENT = "Mozilla/5.0";

    // HTTP GET request
    public String sendGet(String mensaje) throws IOException {
        BufferedReader in = null;
        String respuestapractica = "";
        try {
            String urlString = "http://ws.itcsoluciones.com:48080/jSitelServlet/Do?" + "pas=" + URLEncoder.encode("bda221f8-a7e3-11e4-b085-000c29a675b5", "UTF-8") + "&msj=" + URLEncoder.encode(mensaje, "UTF-8");

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

            con.disconnect();

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:/Facturacion Laboratorios/respuesta.xml"), "utf-8"));
            out.write(respuestapractica);
            out.close();
        } catch (IOException e) {
            System.out.println("Error " + e);
            respuestapractica = e.getMessage();
        } finally {
            in.close();

        }
        return respuestapractica;
    }
}
