package Clases;

import ClienteNobis.RespuestaInsertarAutorizacionAmb;
import ClienteOspe.practicaXMLospe;
import ClienteOspe.respuestaXMLospe;
import ClienteNobis.respuestaXMLNobis;
import ClienteOsde.Beneficiario;
import ClienteOsde.Credencial;
import ClienteOsde.DetalleProcedimientos;
import ClienteOsde.RespuestaXMLOsde;
import ClienteOsde.Rta;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.xml.XMLConstants;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXMLFile {

    private static final String FILENAME = "C:/Facturacion Laboratorios/respuesta.xml";
    private static ArrayList<practicaXMLospe> listaPracticas = new ArrayList();

    public String ReadXML(String xml, String valor) {
        String respuesta = "";
        //Se crea un SAXBuilder para poder parsear el archivo
        SAXBuilder builder = new SAXBuilder();
        //  File xmlFile = new File("uno.xml");
        
        
        File xmlFile = new File(xml);
        try {
            //Se crea el documento a traves del archivo
            Document document = (Document) builder.build(xmlFile);
            
            //Se obtiene la raiz 'tables'
            Element rootNode = document.getRootElement();

            //Se obtiene la lista de hijos de la raiz 'tables'
            List list = rootNode.getChildren("Practica");
            //Se recorre la lista de hijos de 'tables'
            for (int i = list.size() - 1; i < list.size(); i++) {
                //Se obtiene el elemento 'tabla'
                Element tabla = (Element) list.get(i);

                //Se obtiene el atributo 'nombre' que esta en el tag 'tabla'
                //String nombreTabla = tabla.getAttributeValue("1");
                String nombreTabla = String.valueOf(i);
                System.out.println("Tabla: " + nombreTabla);

                //Se obtiene la lista de hijos del tag 'tabla'
                ///List lista_campos = tabla.getChildren();
                System.out.println("\tNombre\t\tValor");

                //Se recorre la lista de campos
                for (int j = 0; j < list.size(); j++) {
                    //Se obtiene el elemento 'campo'
                    Element campo = (Element) list.get(j);

                    //Se obtienen los valores que estan entre los tags '<campo></campo>'
                    //Se obtiene el valor que esta entre los tags '<nombre></nombre>'
                    String nombre = campo.getChildTextTrim("PracticaId");

                    //Se obtiene el valor que esta entre los tags '<tipo></tipo>'
                    //String tipo = campo.getChildTextTrim("PracticaDes");
                    //Se obtiene el valor que esta entre los tags '<valor></valor>'
                    String coseguro = campo.getChildTextTrim("PracticaCoseguro");

                    System.out.println("\t" + nombre + "\t\t" + coseguro);
                }

            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }

        return respuesta;
    }

    public respuestaXMLospe ReadXMLOspe01A() {
        respuestaXMLospe respuestaOspe = new respuestaXMLospe();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            org.w3c.dom.Document doc = db.parse(new File(FILENAME));

            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            // get <EncabezadoMensaje>
            NodeList EncabezadoMensaje = doc.getDocumentElement().getElementsByTagName("EncabezadoMensaje");
            if (EncabezadoMensaje.getLength() != 0) {
                for (int temp = 0; temp < EncabezadoMensaje.getLength(); temp++) {

                    Node node = EncabezadoMensaje.item(temp);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                        // get respuesta                    
                        String TipoMsj = element.getElementsByTagName("TipoMsj").item(0).getTextContent();
                        String TipoTransaccion = element.getElementsByTagName("TipoTransaccion").item(0).getTextContent();
                        System.out.println("Current Element :" + node.getNodeName());
                        System.out.println("TipoMsj : " + TipoMsj);
                        System.out.println("TipoTransaccion : " + TipoTransaccion);

                        org.w3c.dom.Element rta = (org.w3c.dom.Element) element.getElementsByTagName("Rta").item(0);
                        String CodRtaGeneral = rta.getElementsByTagName("CodRtaGeneral").item(0).getTextContent();
                        String DescripcionRtaGeneral = rta.getElementsByTagName("DescripcionRtaGeneral").item(0).getTextContent();
                        String MensajeDisplay = rta.getElementsByTagName("MensajeDisplay").item(0).getTextContent() + " - " + rta.getElementsByTagName("MensajePrinter").item(0).getTextContent();

                        System.out.println("CodRtaGeneral : " + CodRtaGeneral);
                        System.out.println("DescripcionRtaGeneral : " + DescripcionRtaGeneral);
                        System.out.println("MensajeDisplay : " + MensajeDisplay);

                        org.w3c.dom.Element rtaAdicional = (org.w3c.dom.Element) element.getElementsByTagName("RtaAdicional").item(0);
                        String CodigoRtaAdicional = rtaAdicional.getElementsByTagName("CodigoRtaAdicional").item(0).getTextContent();
                        System.out.println("CodigoRtaAdicional : " + CodigoRtaAdicional);

                        String NroReferencia = element.getElementsByTagName("NroReferencia").item(0).getTextContent();
                        System.out.println("NroReferencia : " + NroReferencia);

                        respuestaOspe.setRespuesta(DescripcionRtaGeneral);
                        respuestaOspe.setCodigo(CodRtaGeneral);
                        respuestaOspe.setMensaje(MensajeDisplay + " - " + CodRtaGeneral + ": " + CodigoRtaAdicional);
                        respuestaOspe.setNroReferencia(NroReferencia);
                    } else {
                        respuestaOspe.setRespuesta("Error al leer el XML Encabezado Mensaje");
                        respuestaOspe.setCodigo("ReadXML: " + 76);
                        respuestaOspe.setMensaje("");
                        respuestaOspe.setNroReferencia("0");
                    }
                }
                if (respuestaOspe.getCodigo().equals("00")) {
                    // get <EncabezadoAtencion>
                    NodeList EncabezadoAtencion = doc.getDocumentElement().getElementsByTagName("EncabezadoAtencion");
                    for (int temp = 0; temp < EncabezadoAtencion.getLength(); temp++) {

                        Node node = EncabezadoAtencion.item(temp);

                        if (node.getNodeType() == Node.ELEMENT_NODE) {

                            org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                            // get respuesta                    
                            org.w3c.dom.Element rtaAdicional = (org.w3c.dom.Element) element.getElementsByTagName("RtaAdicional").item(0);
                            String CodigoRtaAdicional = rtaAdicional.getElementsByTagName("CodigoRtaAdicional").item(0).getTextContent();
                            System.out.println("CodigoRtaAdicional : " + CodigoRtaAdicional);
                            // get Beneficiario                    
                            org.w3c.dom.Element Beneficiario = (org.w3c.dom.Element) element.getElementsByTagName("Beneficiario").item(0);
                            String NombreBeneficiario = Beneficiario.getElementsByTagName("NombreBeneficiario").item(0).getTextContent();
                            String FechaNacimiento = Beneficiario.getElementsByTagName("FechaNacimiento").item(0).getTextContent();

                            System.out.println("NombreBeneficiario : " + NombreBeneficiario);
                            System.out.println("FechaNacimiento : " + FechaNacimiento);
                            respuestaOspe.setRespuestaAdicional(CodigoRtaAdicional);
                            respuestaOspe.setAfiliado(NombreBeneficiario);
                            respuestaOspe.setDni("11111111");
                        } else {
                            respuestaOspe.setRespuestaAdicional("Error al cargar Encabezado Atencion");
                            respuestaOspe.setAfiliado("");
                            respuestaOspe.setDni("");
                        }
                    }
                }
            } else {
                respuestaOspe.setRespuesta("Error al leer el XML");
                respuestaOspe.setCodigo("ReadXML: " + 102);
                respuestaOspe.setMensaje("");
                respuestaOspe.setAfiliado("");
                respuestaOspe.setDni("");
            }
            System.out.println("fin");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            respuestaOspe.setRespuesta("Error al leer el XML");
            respuestaOspe.setCodigo("ReadXML: " + 111);
            respuestaOspe.setMensaje("");
            respuestaOspe.setAfiliado("");
            respuestaOspe.setDni("");
        }

        return respuestaOspe;
    }

    public respuestaXMLospe ReadXMLOspe02A() {
        respuestaXMLospe respuestaOspe = new respuestaXMLospe();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        listaPracticas.removeAll(listaPracticas);
        try {
            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            org.w3c.dom.Document doc = db.parse(new File(FILENAME));

            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            // get <EncabezadoMensaje>
            NodeList EncabezadoMensaje = doc.getDocumentElement().getElementsByTagName("EncabezadoMensaje");
            if (EncabezadoMensaje.getLength() != 0) {
                for (int temp = 0; temp < EncabezadoMensaje.getLength(); temp++) {

                    Node node = EncabezadoMensaje.item(temp);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                        // get respuesta                    
                        String TipoMsj = element.getElementsByTagName("TipoMsj").item(0).getTextContent();
                        String TipoTransaccion = element.getElementsByTagName("TipoTransaccion").item(0).getTextContent();
                        System.out.println("Current Element :" + node.getNodeName());
                        System.out.println("TipoMsj : " + TipoMsj);
                        System.out.println("TipoTransaccion : " + TipoTransaccion);

                        org.w3c.dom.Element rta = (org.w3c.dom.Element) element.getElementsByTagName("Rta").item(0);
                        String CodRtaGeneral = rta.getElementsByTagName("CodRtaGeneral").item(0).getTextContent();
                        String DescripcionRtaGeneral = rta.getElementsByTagName("DescripcionRtaGeneral").item(0).getTextContent();
                        String MensajeDisplay = rta.getElementsByTagName("MensajeDisplay").item(0).getTextContent();

                        System.out.println("CodRtaGeneral : " + CodRtaGeneral);
                        System.out.println("DescripcionRtaGeneral : " + DescripcionRtaGeneral);
                        System.out.println("MensajeDisplay : " + MensajeDisplay);

                        org.w3c.dom.Element rtaAdicional = (org.w3c.dom.Element) element.getElementsByTagName("RtaAdicional").item(0);
                        String CodigoRtaAdicional = rtaAdicional.getElementsByTagName("CodigoRtaAdicional").item(0).getTextContent();
                        System.out.println("CodigoRtaAdicional : " + CodigoRtaAdicional);

                        String NroReferencia = element.getElementsByTagName("NroReferencia").item(0).getTextContent();
                        System.out.println("NroReferencia : " + NroReferencia);

                        respuestaOspe.setRespuesta(DescripcionRtaGeneral);
                        respuestaOspe.setCodigo(CodRtaGeneral);
                        respuestaOspe.setMensaje(MensajeDisplay + " " + CodRtaGeneral + ": " + CodigoRtaAdicional);
                        respuestaOspe.setNroReferencia(NroReferencia);
                    } else {
                        respuestaOspe.setRespuesta("Error al leer el XML Encabezado Mensaje");
                        respuestaOspe.setCodigo("ReadXML: " + 76);
                        respuestaOspe.setMensaje("");
                        respuestaOspe.setNroReferencia("0");
                    }
                }
                // get <EncabezadoAtencion>
                NodeList EncabezadoAtencion = doc.getDocumentElement().getElementsByTagName("EncabezadoAtencion");
                System.out.println("EncabezadoAtencion.getLength():" + EncabezadoAtencion.getLength());
                for (int temp = 0; temp < EncabezadoAtencion.getLength(); temp++) {

                    Node node = EncabezadoAtencion.item(temp);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        org.w3c.dom.Element element = (org.w3c.dom.Element) node;

                        // get Credencial                    
                        org.w3c.dom.Element Credencial = (org.w3c.dom.Element) element.getElementsByTagName("Credencial").item(0);
                        String CondicionIVA = Credencial.getElementsByTagName("CondicionIVA").item(0).getTextContent();
                        System.out.println("CondicionIVA : " + CondicionIVA);

                        // get respuesta                    
                        org.w3c.dom.Element rtaAdicional = (org.w3c.dom.Element) element.getElementsByTagName("RtaAdicional").item(0);
                        String CodigoRtaAdicional = rtaAdicional.getElementsByTagName("CodigoRtaAdicional").item(0).getTextContent();
                        System.out.println("CodigoRtaAdicional : " + CodigoRtaAdicional);
                        // get Beneficiario                    
                        org.w3c.dom.Element Beneficiario = (org.w3c.dom.Element) element.getElementsByTagName("Beneficiario").item(0);
                        String NombreBeneficiario = Beneficiario.getElementsByTagName("NombreBeneficiario").item(0).getTextContent();
                        String FechaNacimiento = Beneficiario.getElementsByTagName("FechaNacimiento").item(0).getTextContent();

                        System.out.println("NombreBeneficiario : " + NombreBeneficiario);
                        System.out.println("FechaNacimiento : " + FechaNacimiento);
                        respuestaOspe.setRespuestaAdicional(CodigoRtaAdicional);
                        respuestaOspe.setAfiliado(NombreBeneficiario);
                        respuestaOspe.setDni("11111111");
                    } else {
                        respuestaOspe.setRespuestaAdicional("Error al cargar Encabezado Atencion");
                        respuestaOspe.setAfiliado("");
                        respuestaOspe.setDni("");
                    }
                }
                // get <DetalleProcedimientos>
                NodeList DetalleProcedimientos = doc.getDocumentElement().getElementsByTagName("DetalleProcedimientos");
                System.out.println("DetalleProcedimientos.getLength():" + DetalleProcedimientos.getLength());
                for (int temp = 0; temp < DetalleProcedimientos.getLength(); temp++) {

                    Node node = DetalleProcedimientos.item(temp);
                    practicaXMLospe Practica = new practicaXMLospe();
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println("----------------------------------------");
                        org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                        // get practicas    
                        String CodPrestacion = element.getElementsByTagName("CodPrestacion").item(0).getTextContent();
                        System.out.println("CodPrestacion : " + CodPrestacion);

                        String TipoPrestacion = element.getElementsByTagName("TipoPrestacion").item(0).getTextContent();
                        System.out.println("TipoPrestacion : " + TipoPrestacion);

                        String ArancelPrestacion = element.getElementsByTagName("ArancelPrestacion").item(0).getTextContent();
                        System.out.println("ArancelPrestacion : " + ArancelPrestacion);

                        String CantidadSolicitada = element.getElementsByTagName("CantidadSolicitada").item(0).getTextContent();
                        System.out.println("CantidadSolicitada : " + CantidadSolicitada);

                        String CantidadAprobada = element.getElementsByTagName("CantidadAprobada").item(0).getTextContent();
                        System.out.println("CantidadAprobada : " + CantidadAprobada);

                        String CodRta = element.getElementsByTagName("CodRta").item(0).getTextContent();
                        System.out.println("CodRta : " + CodRta);

                        String MensajeRta = element.getElementsByTagName("MensajeRta").item(0).getTextContent();
                        System.out.println("MensajeRta : " + MensajeRta);

                        String ImporteACargoAfiliado = "0.0";
                        System.out.println("element.getElementsByTagName(ImporteACargoAfiliado).getLength(): " + element.getElementsByTagName("ImporteACargoAfiliado").getLength());
                        if (element.getElementsByTagName("ImporteACargoAfiliado").getLength() != 0) {
                            ImporteACargoAfiliado = element.getElementsByTagName("ImporteACargoAfiliado").item(0).getTextContent();
                        }
                        System.out.println("ImporteACargoAfiliado : " + ImporteACargoAfiliado);

                        Practica.setCodPrestacion(CodPrestacion);
                        Practica.setTipoPrestacion(TipoPrestacion);
                        Practica.setArancelPrestacion(ArancelPrestacion);
                        Practica.setCantidadSolicitada(CantidadSolicitada);
                        Practica.setCantidadAprobada(CantidadAprobada);
                        Practica.setCodRta(CodRta);
                        Practica.setMensajeRta(MensajeRta);
                        Practica.setImporteACargoAfiliado(ImporteACargoAfiliado);
                        System.out.println("setea practica " + temp);
                        listaPracticas.add(Practica);
                        System.out.println("agrega practica " + temp + " a lista practica");
                        System.out.println("----------------------------------------");
                    } else {
                        respuestaOspe.setRespuestaAdicional("Error al cargar Detalle Procedimientos");
                    }
                }
                respuestaOspe.setPracticas(listaPracticas);
                System.out.println("agrega practicas a lista practica en respuestaOspe");
            } else {
                respuestaOspe.setRespuesta("Error al leer el XML");
                respuestaOspe.setCodigo("ReadXML: " + 102);
                respuestaOspe.setMensaje("");
                respuestaOspe.setAfiliado("");
                respuestaOspe.setDni("");
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            respuestaOspe.setRespuesta("Error al leer el XML");
            respuestaOspe.setCodigo("ReadXML: " + 111);
            respuestaOspe.setMensaje("");
            respuestaOspe.setAfiliado("");
            respuestaOspe.setDni("");
        }

        return respuestaOspe;
    }

    public respuestaXMLospe ReadXMLOspe04A() {
        respuestaXMLospe respuestaOspe = new respuestaXMLospe();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            org.w3c.dom.Document doc = db.parse(new File(FILENAME));

            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            // get <EncabezadoMensaje>
            NodeList EncabezadoMensaje = doc.getDocumentElement().getElementsByTagName("EncabezadoMensaje");
            if (EncabezadoMensaje.getLength() != 0) {
                for (int temp = 0; temp < EncabezadoMensaje.getLength(); temp++) {

                    Node node = EncabezadoMensaje.item(temp);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                        // get respuesta                    
                        String TipoMsj = element.getElementsByTagName("TipoMsj").item(0).getTextContent();
                        String TipoTransaccion = element.getElementsByTagName("TipoTransaccion").item(0).getTextContent();
                        System.out.println("Current Element :" + node.getNodeName());
                        System.out.println("TipoMsj : " + TipoMsj);
                        System.out.println("TipoTransaccion : " + TipoTransaccion);

                        org.w3c.dom.Element rta = (org.w3c.dom.Element) element.getElementsByTagName("Rta").item(0);
                        String CodRtaGeneral = rta.getElementsByTagName("CodRtaGeneral").item(0).getTextContent();
                        String DescripcionRtaGeneral = rta.getElementsByTagName("DescripcionRtaGeneral").item(0).getTextContent();
                        String MensajeDisplay = rta.getElementsByTagName("MensajeDisplay").item(0).getTextContent();

                        System.out.println("CodRtaGeneral : " + CodRtaGeneral);
                        System.out.println("DescripcionRtaGeneral : " + DescripcionRtaGeneral);
                        System.out.println("MensajeDisplay : " + MensajeDisplay);

                        org.w3c.dom.Element rtaAdicional = (org.w3c.dom.Element) element.getElementsByTagName("RtaAdicional").item(0);
                        String CodigoRtaAdicional = rtaAdicional.getElementsByTagName("CodigoRtaAdicional").item(0).getTextContent();
                        System.out.println("CodigoRtaAdicional : " + CodigoRtaAdicional);

                        String NroReferencia = element.getElementsByTagName("NroReferencia").item(0).getTextContent();
                        System.out.println("NroReferencia : " + NroReferencia);

                        respuestaOspe.setRespuesta(DescripcionRtaGeneral);
                        respuestaOspe.setCodigo(CodRtaGeneral);
                        respuestaOspe.setMensaje(MensajeDisplay + " " + CodRtaGeneral + ": " + CodigoRtaAdicional);
                        respuestaOspe.setNroReferencia(NroReferencia);

                    } else {
                        respuestaOspe.setRespuesta("Error al leer el XML Encabezado Mensaje");
                        respuestaOspe.setCodigo("ReadXML: " + 433);
                        respuestaOspe.setMensaje("");
                        respuestaOspe.setNroReferencia("0");
                    }
                }
                // get <EncabezadoAtencion>
                NodeList EncabezadoAtencion = doc.getDocumentElement().getElementsByTagName("EncabezadoAtencion");
                for (int temp = 0; temp < EncabezadoAtencion.getLength(); temp++) {

                    Node node = EncabezadoAtencion.item(temp);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                        // get respuesta                    
                        org.w3c.dom.Element rtaAdicional = (org.w3c.dom.Element) element.getElementsByTagName("RtaAdicional").item(0);
                        String CodigoRtaAdicional = rtaAdicional.getElementsByTagName("CodigoRtaAdicional").item(0).getTextContent();
                        System.out.println("CodigoRtaAdicional : " + CodigoRtaAdicional);
                    } else {
                        respuestaOspe.setRespuestaAdicional("Error al cargar Encabezado Atencion");
                        respuestaOspe.setAfiliado("");
                        respuestaOspe.setDni("");
                    }
                }
            } else {
                respuestaOspe.setRespuesta("Error al leer el XML");
                respuestaOspe.setCodigo("ReadXML: " + 102);
                respuestaOspe.setMensaje("");
                respuestaOspe.setAfiliado("");
                respuestaOspe.setDni("");
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            respuestaOspe.setRespuesta("Error al leer el XML");
            respuestaOspe.setCodigo("ReadXML: " + 111);
            respuestaOspe.setMensaje("");
            respuestaOspe.setAfiliado("");
            respuestaOspe.setDni("");
        }

        return respuestaOspe;
    }

       
    public respuestaXMLNobis ReadXMLNobisConsultarAfiliado() {
        respuestaXMLNobis respuestaNobis = new respuestaXMLNobis();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();
            
            org.w3c.dom.Document doc = db.parse(new File(FILENAME));

            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            // get <EncabezadoMensaje>
            NodeList ConsultaAfiliado = doc.getDocumentElement().getElementsByTagName("ConsultaAfiliado");
            if (ConsultaAfiliado.getLength() != 0) {
                // get <ConsultaAfiliado>
                //   NodeList ConsultaAfiliado = doc.getDocumentElement().getElementsByTagName("ConsultaAfiliado");
                //   for (int temp = 0; temp < ConsultaAfiliado.getLength(); temp++) {

                Node node = ConsultaAfiliado.item(0);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    // get mensaje                    
                    String Mensaje = element.getElementsByTagName("Mensaje").item(0).getTextContent();
                    System.out.println("Mensaje : " + Mensaje);
                    // get Beneficiario                    
                    String Afiliado = element.getElementsByTagName("Afiliado").item(0).getTextContent();
                    String Numero_Afi = element.getElementsByTagName("Numero_Afi").item(0).getTextContent();
                    String Nro_Doc = element.getElementsByTagName("Nro_Doc").item(0).getTextContent();
                    String Estado = element.getElementsByTagName("Estado").item(0).getTextContent();

                    System.out.println("Afiliado : " + Afiliado);
                    System.out.println("Numero_Afi : " + Numero_Afi);
                    System.out.println("Nro_Doc : " + Nro_Doc);
                    System.out.println("Estado : " + Estado);

                    respuestaNobis.setAfiliado(Afiliado);
                    respuestaNobis.setDni(Nro_Doc);
                    respuestaNobis.setEstado(Estado);
                    respuestaNobis.setMensaje(Mensaje);
                    respuestaNobis.setNumeroAfi(Numero_Afi);
                } else {
                    respuestaNobis.setMensaje("Error al cargar Encabezado Atención");
                    respuestaNobis.setAfiliado("");
                    respuestaNobis.setDni("");
                    respuestaNobis.setNumeroAfi("");
                    respuestaNobis.setEstado("Error al cargar Encabezado Atención");
                }
                //  }
            } else {
                respuestaNobis.setMensaje("Error al leer Document Element");
                respuestaNobis.setAfiliado("");
                respuestaNobis.setNumeroAfi("");
                respuestaNobis.setDni("");
                respuestaNobis.setEstado("Error al leer Document Element");
            }
            System.out.println("fin");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            respuestaNobis.setMensaje("Error al leer el XML");
            respuestaNobis.setAfiliado("");
            respuestaNobis.setNumeroAfi("");
            respuestaNobis.setDni("");
            respuestaNobis.setEstado("Error al leer el XML");
        }

        return respuestaNobis;
    }

 

    public respuestaXMLNobis ReadXMLNobisInsertarAutorizacionAmb() throws JAXBException, ParserConfigurationException, SAXException, IOException {
        respuestaXMLNobis respuestaNobis = new respuestaXMLNobis();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // optional, but recommended
        // process XML securely, avoid attacks like XML External Entities (XXE)
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        // parse XML file
        DocumentBuilder db = dbf.newDocumentBuilder();

        org.w3c.dom.Document doc = db.parse(new File(FILENAME));

        doc.getDocumentElement().normalize();

        System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
        System.out.println("------");

        // get <EncabezadoMensaje>
        NodeList InsertarOrdenAmb = doc.getDocumentElement().getElementsByTagName("Autorizacion");

        if (InsertarOrdenAmb.getLength() != 0) {
            Node node = InsertarOrdenAmb.item(0);
            String Mensaje = "";
            String Cod = "";
            String Num = "";
            String Cose_Neto = "";
            String Cose_IVA = "";
            String Cose_Total = "";
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                // get mensaje       
                if (element.getElementsByTagName("Mensaje").getLength() != 0) {
                    Mensaje = element.getElementsByTagName("Mensaje").item(0).getTextContent();
                }
                System.out.println("Mensaje : " + Mensaje);
                // get autorizacion                    
                String Estado = element.getElementsByTagName("Estado").item(0).getTextContent();

                System.out.println("Estado : " + Estado);

                if (element.getElementsByTagName("Cod").getLength() != 0) {
                    Cod = element.getElementsByTagName("Cod").item(0).getTextContent();
                }
                System.out.println("Cod : " + Cod);

                if (element.getElementsByTagName("Num").getLength() != 0) {
                    Num = element.getElementsByTagName("Num").item(0).getTextContent();
                }
                System.out.println("Num : " + Num);

                if (element.getElementsByTagName("Cose_Neto").getLength() != 0) {
                    Cose_Neto = element.getElementsByTagName("Cose_Neto").item(0).getTextContent();
                }
                System.out.println("Cose_Neto : " + Cose_Neto);

                if (element.getElementsByTagName("Cose_IVA").getLength() != 0) {
                    Cose_IVA = element.getElementsByTagName("Cose_IVA").item(0).getTextContent();
                }
                System.out.println("Cose_IVA : " + Cose_IVA);

                if (element.getElementsByTagName("Cose_Total").getLength() != 0) {
                    Cose_Total = element.getElementsByTagName("Cose_Total").item(0).getTextContent();
                }
                System.out.println("Cose_Total : " + Cose_Total);

                respuestaNobis.setMensaje(Mensaje);
                respuestaNobis.setEstado(Estado);
                respuestaNobis.setCod(Cod);
                respuestaNobis.setNum(Num);

            } else {
                respuestaNobis.setMensaje("Error al cargar Encabezado Atención");
                respuestaNobis.setAfiliado("");
                respuestaNobis.setDni("");
                respuestaNobis.setNumeroAfi("");
                respuestaNobis.setEstado("Error al cargar Encabezado Atención");
            }
            //  }
        } else {
            respuestaNobis.setMensaje("Error al leer Document Element");
            respuestaNobis.setAfiliado("");
            respuestaNobis.setNumeroAfi("");
            respuestaNobis.setDni("");
            respuestaNobis.setEstado("Error al leer Document Element");
        }
        System.out.println("fin");

        return respuestaNobis;
    }

    public respuestaXMLNobis ReadXMLNobisAnulacionOrden() {
        respuestaXMLNobis respuestaNobis = new respuestaXMLNobis();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            org.w3c.dom.Document doc = db.parse(new File(FILENAME));

            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            // get <EncabezadoMensaje>
            NodeList AnularOrden = doc.getDocumentElement().getElementsByTagName("AnularOrden");
            if (AnularOrden.getLength() != 0) {

                Node node = AnularOrden.item(0);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    // get mensaje                    
                    String Mensaje = element.getElementsByTagName("Mensaje").item(0).getTextContent();
                    System.out.println("Mensaje : " + Mensaje);
                    // get Beneficiario                    

                    String Estado = element.getElementsByTagName("Estado").item(0).getTextContent();

                    System.out.println("Estado : " + Estado);

                    respuestaNobis.setEstado(Estado);
                    respuestaNobis.setMensaje(Mensaje);
                } else {
                    respuestaNobis.setMensaje("Error al cargar Encabezado Atención");
                    respuestaNobis.setAfiliado("");
                    respuestaNobis.setDni("");
                    respuestaNobis.setNumeroAfi("");
                    respuestaNobis.setEstado("Error al cargar Encabezado Atención");
                }
            } else {
                respuestaNobis.setMensaje("Error al leer Document Element");
                respuestaNobis.setAfiliado("");
                respuestaNobis.setNumeroAfi("");
                respuestaNobis.setDni("");
                respuestaNobis.setEstado("Error al leer Document Element");
            }
            System.out.println("fin");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            respuestaNobis.setMensaje("Error al leer el XML");
            respuestaNobis.setAfiliado("");
            respuestaNobis.setNumeroAfi("");
            respuestaNobis.setDni("");
            respuestaNobis.setEstado("Error al leer el XML");
        }

        return respuestaNobis;
    }

    
    
    //ambulatorio
    public RespuestaXMLOsde ReadXMLOsde02L() throws JAXBException, ParserConfigurationException, SAXException, IOException {
        RespuestaXMLOsde respuestaOsde = new RespuestaXMLOsde();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // optional, but recommended
        // process XML securely, avoid attacks like XML External Entities (XXE)
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        // parse XML file
        DocumentBuilder db = dbf.newDocumentBuilder();

        org.w3c.dom.Document doc = db.parse(new File(FILENAME));

        doc.getDocumentElement().normalize();

        System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
        System.out.println("------");

        // get <EncabezadoMensaje>
        NodeList EncabezadoMensaje = doc.getDocumentElement().getElementsByTagName("EncabezadoMensaje");

        if (EncabezadoMensaje.getLength() != 0) {
            Node node = EncabezadoMensaje.item(0);
            String CodRtaGeneral = "";
            String MensajeDisplay = "";
            String TipoTransaccion = "";
            String NroReferencia = "";
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                
                
                // get Rta       
                org.w3c.dom.Element rta = (org.w3c.dom.Element) element.getElementsByTagName("Rta").item(0);
                CodRtaGeneral = rta.getElementsByTagName("CodRtaGeneral").item(0).getTextContent();
                MensajeDisplay = rta.getElementsByTagName("MensajeDisplay").item(0).getTextContent();
                
                Rta respuesta = new Rta();
                respuesta.setCodRtaGeneral(CodRtaGeneral);
                respuesta.setMensajeDisplay(MensajeDisplay);
                respuestaOsde.setRta(respuesta);
                
                // get TipoTransaccion
                TipoTransaccion = element.getElementsByTagName("TipoTransaccion").item(0).getTextContent();
                
                respuestaOsde.setTipoTransaccion(TipoTransaccion);
                
                // get NroReferencia
                NroReferencia = element.getElementsByTagName("NroReferencia").item(0).getTextContent();
                
                respuestaOsde.setNroReferencia(NroReferencia);
                                
            } else {
                System.out.println("error node EncabezadoMensaje" + node.toString());
                JOptionPane.showMessageDialog(null, "Error al cargar nodo del encabezado");
            }
            
            //  }
        } else {
            System.out.println("error EncabezadoMensaje" + EncabezadoMensaje.toString());
            JOptionPane.showMessageDialog(null, "Error al cargar el encabezado");
        }
        
        // get <DetalleProcedimientos>
        NodeList DetalleProcedimientos = doc.getDocumentElement().getElementsByTagName("DetalleProcedimientos");

        if (DetalleProcedimientos.getLength() != 0) {
            Node node = DetalleProcedimientos.item(0);
            String NroItem = "";
            String CodPrestacion = "";
            String TipoPrestacion = "";
            String ArancelPrestacion = "";
            String CantidadSolicitada = "";
            String CantidadAprobada = "";
            String DescripcionPrestacion = "";
            String CodRta = "";
            String MensajeRta = "";
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                
                // get NroItem       
                NroItem = element.getElementsByTagName("NroItem").item(0).getTextContent();
                // get CodPrestacion       
                CodPrestacion = element.getElementsByTagName("CodPrestacion").item(0).getTextContent();
                // get TipoPrestacion       
                TipoPrestacion = element.getElementsByTagName("TipoPrestacion").item(0).getTextContent();
                // get ArancelPrestacion       
                ArancelPrestacion = element.getElementsByTagName("ArancelPrestacion").item(0).getTextContent();
                // get CantidadSolicitada       
                CantidadSolicitada = element.getElementsByTagName("CantidadSolicitada").item(0).getTextContent();
                // get CantidadAprobada       
                CantidadAprobada = element.getElementsByTagName("CantidadAprobada").item(0).getTextContent();
                // get DescripcionPrestacion       
                DescripcionPrestacion = element.getElementsByTagName("DescripcionPrestacion").item(0).getTextContent();
                // get CodRta       
                CodRta = element.getElementsByTagName("CodRta").item(0).getTextContent();
                // get MensajeRta       
                MensajeRta = element.getElementsByTagName("MensajeRta").item(0).getTextContent();
                
                DetalleProcedimientos detalle = new DetalleProcedimientos();
                
                detalle.setNroItem(NroItem);
                detalle.setCodPrestacion(CodPrestacion);
                detalle.setTipoPrestacion(TipoPrestacion);
                detalle.setArancelPrestacion(ArancelPrestacion);
                detalle.setCantidadSolicitada(CantidadSolicitada);
                detalle.setCantidadAprobada(CantidadAprobada);
                detalle.setDescripcionPrestacion(DescripcionPrestacion);
                detalle.setCodRta(CodRta);
                detalle.setMensajeRta(MensajeRta);
                
                respuestaOsde.setDetalleProcedimientos(detalle);
                
                                
            } else {
                System.out.println("error node DetalleProcedimientos" + node.toString());
                JOptionPane.showMessageDialog(null, "Error al cargar nodo del procedimiento");
            }
            
            //  }
        } else {
            System.out.println("error DetalleProcedimientos" + DetalleProcedimientos.toString());
            JOptionPane.showMessageDialog(null, "Error al cargar el procedimiento");
        }
        
        System.out.println("fin osde");

        return respuestaOsde;
    }
    
    //beneficiario
    public RespuestaXMLOsde ReadXMLOsde01A() throws JAXBException, ParserConfigurationException, SAXException, IOException {
        RespuestaXMLOsde respuestaOsde = new RespuestaXMLOsde();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // optional, but recommended
        // process XML securely, avoid attacks like XML External Entities (XXE)
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        // parse XML file
        DocumentBuilder db = dbf.newDocumentBuilder();

        org.w3c.dom.Document doc = db.parse(new File(FILENAME));

        doc.getDocumentElement().normalize();

        System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
        System.out.println("------");

        // get <EncabezadoMensaje>
        NodeList EncabezadoMensaje = doc.getDocumentElement().getElementsByTagName("EncabezadoMensaje");

        if (EncabezadoMensaje.getLength() != 0) {
            Node node = EncabezadoMensaje.item(0);
            String CodRtaGeneral = "";
            String MensajeDisplay = "";
            String TipoTransaccion = "";
            String NroReferencia = "";
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                
                
                // get Rta       
                org.w3c.dom.Element rta = (org.w3c.dom.Element) element.getElementsByTagName("Rta").item(0);
                CodRtaGeneral = rta.getElementsByTagName("CodRtaGeneral").item(0).getTextContent();
                MensajeDisplay = rta.getElementsByTagName("MensajeDisplay").item(0).getTextContent();
                
                Rta respuesta = new Rta();
                respuesta.setCodRtaGeneral(CodRtaGeneral);
                respuesta.setMensajeDisplay(MensajeDisplay);
                respuestaOsde.setRta(respuesta);
                
                // get TipoTransaccion
                TipoTransaccion = element.getElementsByTagName("TipoTransaccion").item(0).getTextContent();
                
                respuestaOsde.setTipoTransaccion(TipoTransaccion);
                
                // get NroReferencia
                NroReferencia = element.getElementsByTagName("NroReferencia").item(0).getTextContent();
                
                respuestaOsde.setNroReferencia(NroReferencia);
                                
            } else {
                System.out.println("error node EncabezadoMensaje" + node.toString());
                JOptionPane.showMessageDialog(null, "Error al cargar nodo del Encabezado Mensaje");      
            }
            
            //  }
        } else {
            System.out.println("error EncabezadoMensaje" + EncabezadoMensaje.toString());
            JOptionPane.showMessageDialog(null, "Error al cargar el Encabezado Mensaje");      
        }
        
        // get <Beneficiario>
        NodeList Beneficiario = doc.getDocumentElement().getElementsByTagName("Beneficiario");

        if (Beneficiario.getLength() != 0) {
            Node node = Beneficiario.item(0);
            String ApellidoBeneficiario = "";
            String NombreBeneficiario = "";
            String Sexo = "";
            String FechaNacimiento = "";
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                
                // get NroItem       
                ApellidoBeneficiario = element.getElementsByTagName("ApellidoBeneficiario").item(0).getTextContent();
                // get CodPrestacion       
                NombreBeneficiario = element.getElementsByTagName("NombreBeneficiario").item(0).getTextContent();
                // get TipoPrestacion       
                Sexo = element.getElementsByTagName("Sexo").item(0).getTextContent();
                // get ArancelPrestacion       
                FechaNacimiento = element.getElementsByTagName("FechaNacimiento").item(0).getTextContent();
                
                
                Beneficiario afiliado = new Beneficiario();
                
                afiliado.setApellidoBeneficiario(ApellidoBeneficiario);
                afiliado.setNombreBeneficiario(NombreBeneficiario);
                afiliado.setSexo(Sexo);
                afiliado.setFechaNacimiento(FechaNacimiento);
                                
                respuestaOsde.setBeneficiario(afiliado);
                
                                
            } else {
                System.out.println("error node Beneficiario" + node.toString());
                JOptionPane.showMessageDialog(null, "Error al cargar nodo del Beneficiario");      
            }
            
            //  }
        } else {
            System.out.println("error Beneficiario" + Beneficiario.toString());
            JOptionPane.showMessageDialog(null, "Error al cargar el Beneficiario");      
        }
        
        // get <Credencial>
        NodeList Credencial = doc.getDocumentElement().getElementsByTagName("Credencial");

        if (Credencial.getLength() != 0) {
            Node node = Credencial.item(0);
            String NumeroCredencial = "";
            String Track = "";
            String VersionCredencial = "";
            String PlanCredencial = "";
            String CondicionIVA = "";
            
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                
                // get NumeroCredencial       
                NumeroCredencial = element.getElementsByTagName("NumeroCredencial").item(0).getTextContent();
                // get Track       
                ///Track = element.getElementsByTagName("Track").item(0).getTextContent();
                // get VersionCredencial       
                VersionCredencial = element.getElementsByTagName("VersionCredencial").item(0).getTextContent();
                // get PlanCredencial       
                PlanCredencial = element.getElementsByTagName("PlanCredencial").item(0).getTextContent();
                // get CondicionIVA       
                CondicionIVA = element.getElementsByTagName("CondicionIVA").item(0).getTextContent();
                
                Credencial credencial = new Credencial();
                
                credencial.setNumeroCredencial(NumeroCredencial);
                credencial.setTrack(Track);
                credencial.setVersionCredencial(VersionCredencial);
                credencial.setPlanCredencial(PlanCredencial);
                credencial.setCondicionIVA(CondicionIVA);
                
                respuestaOsde.setCredencial(credencial);
                
                                
            } else {
                System.out.println("error node Beneficiario" + node.toString());
                JOptionPane.showMessageDialog(null, "Error al cargar nodo del Beneficiario");      
            }
            
            //  }
        } else {
            System.out.println("error Beneficiario" + Beneficiario.toString());
            JOptionPane.showMessageDialog(null, "Error al cargar el Beneficiario");      
        }
        
        System.out.println("fin osde");

        return respuestaOsde;
    }
    
    //anulacion
    public RespuestaXMLOsde ReadXMLOsde04A() throws JAXBException, ParserConfigurationException, SAXException, IOException {
        RespuestaXMLOsde respuestaOsde = new RespuestaXMLOsde();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // optional, but recommended
        // process XML securely, avoid attacks like XML External Entities (XXE)
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        // parse XML file
        DocumentBuilder db = dbf.newDocumentBuilder();

        org.w3c.dom.Document doc = db.parse(new File(FILENAME));

        doc.getDocumentElement().normalize();

        System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
        System.out.println("------");

        // get <EncabezadoMensaje>
        NodeList EncabezadoMensaje = doc.getDocumentElement().getElementsByTagName("EncabezadoMensaje");

        if (EncabezadoMensaje.getLength() != 0) {
            Node node = EncabezadoMensaje.item(0);
            String CodRtaGeneral = "";
            String MensajeDisplay = "";
            String TipoTransaccion = "";
            String NroReferencia = "";
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                
                
                // get Rta       
                org.w3c.dom.Element rta = (org.w3c.dom.Element) element.getElementsByTagName("Rta").item(0);
                CodRtaGeneral = rta.getElementsByTagName("CodRtaGeneral").item(0).getTextContent();
                MensajeDisplay = rta.getElementsByTagName("MensajeDisplay").item(0).getTextContent();
                
                Rta respuesta = new Rta();
                respuesta.setCodRtaGeneral(CodRtaGeneral);
                respuesta.setMensajeDisplay(MensajeDisplay);
                respuestaOsde.setRta(respuesta);
                
                // get TipoTransaccion
                TipoTransaccion = element.getElementsByTagName("TipoTransaccion").item(0).getTextContent();
                
                respuestaOsde.setTipoTransaccion(TipoTransaccion);
                
                // get NroReferencia
                NroReferencia = element.getElementsByTagName("NroReferencia").item(0).getTextContent();
                
                respuestaOsde.setNroReferencia(NroReferencia);
                                
            } else {
                System.out.println("error node EncabezadoMensaje" + node.toString());
                JOptionPane.showMessageDialog(null, "Error al cargar nodo del Encabezado Mensaje");      
            }
            
            //  }
        } else {
            System.out.println("error EncabezadoMensaje" + EncabezadoMensaje.toString());
            JOptionPane.showMessageDialog(null, "Error al cargar el Encabezado Mensaje");      
        }
        
        System.out.println("fin osde");

        return respuestaOsde;
    }
}
