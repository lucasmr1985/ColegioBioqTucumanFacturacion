package Controlador;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class HttpURLConnectionExample {

        private final String USER_AGENT = "Mozilla/5.0";

        // HTTP GET request
        public String sendGet(String mensaje) throws Exception {
            String respuesta;
            String urlString = "http://ws.itcsoluciones.com:48080/jSitelServlet/Do?pas=" + URLEncoder.encode("bda221f8-a7e3-11e4-b085-000c29a675b5", "UTF-8") + "&msj=" + URLEncoder.encode(mensaje, "UTF-8");

            URL obj = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + urlString);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
            respuesta = response.toString();
            return respuesta;
        }
    }
