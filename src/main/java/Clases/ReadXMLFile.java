package Clases;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ReadXMLFile {

    public static void main(String[] args) {
        //Se crea un SAXBuilder para poder parsear el archivo
        SAXBuilder builder = new SAXBuilder();
        //  File xmlFile = new File("uno.xml");
        File xmlFile = new File("practicas.xml");
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
                    String valor = campo.getChildTextTrim("PracticaCoseguro");

                    System.out.println("\t" + nombre + "\t\t" + valor);
                }

            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
    }

}
