/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;

public class ImagenPDF {

    private static final Float ZOOM_2X = Float.valueOf(2);

    public void createPdf(JasperPrint jPrint, String file) throws IOException, DocumentException, JRException {
        Document document = new Document(PageSize.A4, 10, 10, 10, 10);
        Image imagen;

        PdfWriter.getInstance(document, new FileOutputStream(file+".pdf"));

        document.open();
        ////////////////////////////////////////////////////////////////////

        //Creamos una cantidad significativa de paginas para probar el encabezado
        //////////////////////////                               
        int pag = 0;
        while (pag < jPrint.getPages().size()) {
            File outputfile = new File(file  + pag + ".png");            
            BufferedImage pageImage = new BufferedImage((int) (jPrint.getPageWidth()*ZOOM_2X + 10),
                    (int) (jPrint.getPageHeight()*ZOOM_2X+ 10), BufferedImage.TYPE_INT_RGB);
            JRGraphics2DExporter exporter = new JRGraphics2DExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jPrint);
            exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, pageImage.getGraphics());
            exporter.setParameter(JRGraphics2DExporterParameter.ZOOM_RATIO, ZOOM_2X);
            exporter.setParameter(JRExporterParameter.PAGE_INDEX, pag);
            exporter.exportReport();
            ImageIO.write(pageImage, "png", outputfile);
            ///////////////////////////////////////////////////////////
            imagen = Image.getInstance(file  + pag + ".png");
            imagen.scaleToFit(jPrint.getPageWidth(),jPrint.getPageHeight());
            imagen.setAlignment(Element.ALIGN_CENTER);
            document.add(imagen);
            document.newPage();
            pag++;
            if(outputfile.exists()){
                outputfile.delete();
            }            
        }
        document.close();
    }

}
