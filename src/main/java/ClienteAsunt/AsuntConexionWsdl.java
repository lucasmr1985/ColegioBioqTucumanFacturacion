
package ClienteAsunt;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author CBT-COMPUTOS-05
 */
public class AsuntConexionWsdl {

    public static final String RESULTADO = "resultado";
    public static final String NIVEL = "nivel";
    public static final String ANULACION_TIME_OUT = "timeout";
    public static final int ELEGIBILIDAD_AFILIADO = 1;
    public static final int AUTORIZACION_PRACTICA = 2;
    public static final int ANULACION_AUTORIZACION = 3;
    public static final int CONSULTA_AUTORIZACION = 4;
    public static final int LISTA_AFILIADOS = 5;
    public static final String JWT = "JWT";

    public static JSONObject conexionWsdl(JSONObject jsonConsulta) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {

        JSONObject resultadoJson = new JSONObject();
        String responseString = "";
        String outputString = "";

        URLConnection urlConnection = null;
        HttpURLConnection httpConn = null;

        URL url = new URL("http://autogestion.asunt.org.ar/WSP-pruebas/wsp?wsdl");
        urlConnection = (HttpURLConnection) url.openConnection();
        //urlConnection = url.openConnection();
        httpConn = (HttpURLConnection) urlConnection;
        //httpConn.setConnectTimeout(confJson.getInt("timeout"));
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        String xmlInput = null;

        System.out.println("conexion si");

        switch (jsonConsulta.getInt("servicio")) {
            case ELEGIBILIDAD_AFILIADO:
                xmlInput = getAfiliadoPorTipoDniXML(jsonConsulta);
                break;
            case AUTORIZACION_PRACTICA:
                xmlInput = getGenerarOrdenXML(jsonConsulta);
                break;
            case ANULACION_AUTORIZACION:
                xmlInput = anularOrdenPorNroOrdenXML(jsonConsulta);
                break;
        }

        try {
            byte[] buffer = new byte[xmlInput.length()];
            buffer = xmlInput.getBytes();
            bout.write(buffer);
            byte[] b = bout.toByteArray();
            String SOAPAction = "false";
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConn.setRequestProperty("username", "prestadorbioqwsp");
            httpConn.setRequestProperty("password", "123456");
            httpConn.setRequestProperty("false", SOAPAction);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setConnectTimeout(5000);
            OutputStream out = httpConn.getOutputStream();
            System.out.println("out: " + out.toString());
            out.write(b);
            out.close();
        } catch (java.net.SocketTimeoutException e) {
            System.out.println("e 84 " + e);
        } catch (java.io.IOException e) {
            System.out.println("e 86 " + e);
        }

        InputStreamReader isr = null;
        if (httpConn.getResponseCode() == 200) {
            isr = new InputStreamReader(httpConn.getInputStream());
        } else {
            isr = new InputStreamReader(httpConn.getErrorStream());
        }

        BufferedReader in = new BufferedReader(isr);

        while ((responseString = in.readLine()) != null) {
            outputString = outputString + responseString;
        }
        System.out.println("outputString: " + outputString);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document = null;
        builder = factory.newDocumentBuilder();
        document = builder.parse(new InputSource(new StringReader(outputString)));
        switch (jsonConsulta.getInt("servicio")) {
            case ELEGIBILIDAD_AFILIADO:
                resultadoJson = getElegibilidadAfiliado(document);
                break;
            case AUTORIZACION_PRACTICA:
                resultadoJson = getOrdenGenerada(document);
                break;
            case ANULACION_AUTORIZACION:
                resultadoJson = anularOrdenPorNroOrden(document);
                break;
        }
        return resultadoJson;

    }

    protected static String getAfiliadoPorTipoDniXML(JSONObject jsonData) throws JSONException {
        String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://server.ws.integral.com.ar/\">\n"
                + "   <soapenv:Header/>\n"
                + "   <soapenv:Body>\n"
                + "      <ser:validarAfiliadoPorTipoNumeroDocumento>\n"
                + "         <tipo>" + jsonData.getString("tipo") + "</tipo>\n"
                + "         <nroDocumento>" + jsonData.getString("dni") + "</nroDocumento>\n"
                + "      </ser:validarAfiliadoPorTipoNumeroDocumento>\n"
                + "   </soapenv:Body>\n"
                + "</soapenv:Envelope>";
        return xmlInput;
    }

    protected static String anularOrdenPorNroOrdenXML(JSONObject jsonData) throws JSONException {
        String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://server.ws.integral.com.ar/\">\n"
                + "   <soapenv:Header/>\n"
                + "   <soapenv:Body>\n"
                + "      <ser:anularOrdenPorNroBono>\n"
                + "         <nroBono>" + jsonData.getString("codigo") + "</nroBono>\n"
                + "      </ser:anularOrdenPorNroBono>\n"
                + "   </soapenv:Body>\n"
                + "</soapenv:Envelope>";
        return xmlInput;
    }

    protected static String getGenerarOrdenXML(JSONObject jsonData) throws JSONException {
        String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://server.ws.integral.com.ar/\">\n"
                + "   <soapenv:Header/>\n"
                + "   <soapenv:Body>\n"
                + "      <ser:generarOrden>\n"
                + "         <fecha>" + jsonData.getString("fecha") + "</fecha>\n"
                + "         <nroDocumento>" + jsonData.getString("dni") + "</nroDocumento>\n"
                + "         <codEfector>" + jsonData.getString("cuit") + "</codEfector>\n"
                + "         <codPractica>" + jsonData.getString("practica") + "</codPractica>\n"
                + "         <observacion>" + jsonData.getString("observacion") + "</observacion>\n"
                + "         <diagnostico>" + jsonData.getString("diagnostico") + "</diagnostico>\n"
                + "         <tipodoc>" + jsonData.getString("tipo") + "</tipodoc>\n"
                + "         <codConvenioEfector>" + jsonData.get("convenio") + "</codConvenioEfector>\n"
                + "      </ser:generarOrden>\n"
                + "   </soapenv:Body>\n"
                + "</soapenv:Envelope>";
        return xmlInput;
    }

    protected static JSONObject getElegibilidadAfiliado(Document document) throws JSONException {
        NodeList nodeReturn = null;
        JSONObject resultadoJson = new JSONObject();
        nodeReturn = document.getElementsByTagName("return");
        if (nodeReturn.getLength() > 0) {
            //for(int indice = 0; indice < nodeReturn.getLength(); indice++){
            NodeList nodeNombre = document.getElementsByTagName("nombreApellido");
            NodeList nodeDocumento = document.getElementsByTagName("nroDocumento");
            NodeList nodeEdad = document.getElementsByTagName("edad");
            NodeList nodeSexo = document.getElementsByTagName("sexo");
            resultadoJson
                    .put("resultado", true)
                    .put("nombre", quitarCaracteres(nodeNombre.item(0).getTextContent()))
                    .put("dni", nodeDocumento.item(0).getTextContent().trim())
                    .put("edad", nodeEdad.item(0).getTextContent())
                    .put("sexo", nodeSexo.item(0).getTextContent());
            //}
        } else {
            NodeList nodeError = document.getElementsByTagName("faultMessage");
            resultadoJson
                    .put("resultado", false)
                    .put("nombre", quitarCaracteres(nodeError.item(0).getTextContent()))
                    .put("dni", 0)
                    .put("edad", 0)
                    .put("sexo", "");
        }
        return resultadoJson;
    }

    protected static JSONObject getOrdenGenerada(Document document) throws JSONException {
        NodeList nodeReturn = null;
        JSONObject resultadoJson = new JSONObject();
        nodeReturn = document.getElementsByTagName("return");
        String error = "Error en el servicio de la Obra Social";
        if (nodeReturn.getLength() > 0) {
            NodeList nodeCodigo = document.getElementsByTagName("codigo");
            NodeList nodeNroBono = document.getElementsByTagName("nroBono");
            NodeList nodeValor = document.getElementsByTagName("value");
            resultadoJson
                    .put("resultado", true)
                    .put("codigo", nodeCodigo.item(0).getTextContent())
                    .put("coseguro", nodeValor.item(0).getTextContent().replaceAll(" ", ""))
                    .put("nroautorizacion", nodeNroBono.item(0).getTextContent());
        } else {

            NodeList nodeError = document.getElementsByTagName("faultstring");
            error = quitarCaracteres(nodeError.item(0).getTextContent());

            resultadoJson
                    .put("resultado", false)
                    .put("mensaje", error);
        }
        return resultadoJson;
    }

    protected static JSONObject anularOrdenPorNroOrden(Document document) throws JSONException {
        NodeList nodeAlulado = null;
        JSONObject resultadoJson = new JSONObject();
        String error = "Error en el servicio de la Obra Social";
        boolean contieneResultado = false;
        nodeAlulado = document.getElementsByTagName("return");
        contieneResultado = true;
        if (contieneResultado) {
            resultadoJson
                    .put("resultado", true)
                    .put("anulado", Boolean.valueOf(nodeAlulado.item(0).getTextContent()));
        } else {
            NodeList nodeError = document.getElementsByTagName("faultstring");
            error = quitarCaracteres(nodeError.item(0).getTextContent());
            resultadoJson
                    .put("resultado", false)
                    .put("mensaje", error)
                    .put("dni", 0)
                    .put("codigo", 0)
                    .put("edad", 0)
                    .put("nroautorizacion", "-")
                    .put("sexo", "");
        }
        return resultadoJson;
    }

    public static String quitarCaracteres(String cadena) {
        return cadena.trim()
                .replaceAll("Ã³", "o")
                .replaceAll("Error: 7 -", "")
                .replaceAll("</br>", "")
                .replaceAll("Ã­", "i");
    }
}
