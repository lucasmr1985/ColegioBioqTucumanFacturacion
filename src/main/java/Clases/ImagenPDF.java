package Clases;

import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleGraphics2DExporterOutput;
import net.sf.jasperreports.export.SimpleGraphics2DReportConfiguration;

public class ImagenPDF {

    private static final Float ZOOM_2X = Float.valueOf(2);

    public void createPdf(JasperPrint jPrint, String file) throws IOException, DocumentException, JRException {
        Document document = new Document(PageSize.A4, 10, 10, 10, 10);
        Image imagen;

        PdfWriter.getInstance(document, new FileOutputStream(file+".pdf"));

        document.open();

        int pag = 0;
        while (pag < jPrint.getPages().size()) {
            File outputfile = new File(file  + pag + ".png");
            BufferedImage pageImage = new BufferedImage((int) (jPrint.getPageWidth()*ZOOM_2X + 10),
                    (int) (jPrint.getPageHeight()*ZOOM_2X+ 10), BufferedImage.TYPE_INT_RGB);
            JRGraphics2DExporter exporter = new JRGraphics2DExporter();
            exporter.setExporterInput(new SimpleExporterInput(jPrint));
            SimpleGraphics2DExporterOutput output = new SimpleGraphics2DExporterOutput();
            output.setGraphics2D((java.awt.Graphics2D) pageImage.getGraphics());
            exporter.setExporterOutput(output);
            SimpleGraphics2DReportConfiguration config = new SimpleGraphics2DReportConfiguration();
            config.setZoomRatio(ZOOM_2X);
            config.setPageIndex(pag);
            exporter.setConfiguration(config);
            exporter.exportReport();
            ImageIO.write(pageImage, "png", outputfile);
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
