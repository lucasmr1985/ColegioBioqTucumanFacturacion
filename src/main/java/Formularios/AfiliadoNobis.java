/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Clases.ReadXMLFile;
import ClienteNobis.ConsultarAfiliado;
import ClienteNobis.TipoNomenclador;
import ClienteNobis.TipoPrestadores;
import ClienteNobis.WSGecrosNet;
import ClienteNobis.WSGecrosNetSoap;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.jdom2.Document;

/**
 *
 * @author Lucas Robles
 */
public class AfiliadoNobis extends javax.swing.JDialog {

    String hora = "", fechasw = "";
    public static String habilitado = "", nombreafiliado = "", Codigo_afiliado = "",dni="", respuesta = "";;

    public AfiliadoNobis(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Nobis");
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        this.setLocationRelativeTo(null);
        cargarfecha();
    }

    void cargarfecha() {

        //SimpleDateFormat formatoTiempo = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat formatoTiempo = new SimpleDateFormat("HHmmss");
        java.util.Date currentDate1 = new java.util.Date();
        GregorianCalendar calendar1 = new GregorianCalendar();
        calendar1.setTime(currentDate1);
        hora = formatoTiempo.format(currentDate1);
        /////////////////////////////////////
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date currentDate = new java.util.Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);
        fechasw = formato.format(currentDate);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtafiliado = new javax.swing.JTextField();
        btnValidar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese el numero de afiliado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Numero:");

        txtafiliado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtafiliado.setForeground(new java.awt.Color(0, 102, 204));
        txtafiliado.setToolTipText("XXXXXXXXXXXXX");
        txtafiliado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtafiliadoActionPerformed(evt);
            }
        });
        txtafiliado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtafiliadoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtafiliado, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btnValidar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnValidar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728951 - electricity lightning.png"))); // NOI18N
        btnValidar.setText("Validar");
        btnValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnValidar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnValidar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtafiliadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtafiliadoActionPerformed
        txtafiliado.transferFocus();
    }//GEN-LAST:event_txtafiliadoActionPerformed

    private void btnValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarActionPerformed
        cargarfecha();
        respuesta = "";
        String numero_afiliado = txtafiliado.getText();
        System.out.println("numero_afiliado " + numero_afiliado);

        Document doc = new Document();
        try {
            ConsultarAfiliado mensaje = new ConsultarAfiliado();
            mensaje.setPUsuario("CBTUCUMAN");
            mensaje.setPClave("CBTucuman22");

            String xml = "<Afiliado>\n"
                    + "			<TipoDoc>2</TipoDoc>\n"
                    + "			<NroDoc>"+numero_afiliado+"</NroDoc>\n"
                    + "			<NumeroAfiliado></NumeroAfiliado>\n"
                    + "			<Fecha>"+fechasw+"</Fecha>\n"
                    + "		</Afiliado>";
            mensaje.setPXml(xml);
            System.out.println("Send:" +mensaje.getPClave() +" "+mensaje.getPUsuario()+" " + mensaje.getPXml());
            String resultado = null;
            try {
                WSGecrosNet servicio = new WSGecrosNet();
                WSGecrosNetSoap port = servicio.getWSGecrosNetSoap();
                resultado = port.consultarAfiliado(mensaje.getPUsuario(), mensaje.getPClave(), mensaje.getPXml());
                System.out.println("resultado:" + resultado);
                //Generate XML
                try {
                    FileWriter archivo = new FileWriter("C:/Facturacion Laboratorios/respuesta.xml");
                    archivo.write(resultado);
                    archivo.close();
                    System.out.println(".....");
                    ReadXMLFile respuestaNobis = new ReadXMLFile();                    
                    System.out.println("-----");
                    ///43564951
                    System.out.println("respuestaNobis.ReadXMLNobisConsultarAfiliado().getEstado(): "+respuestaNobis.ReadXMLNobisConsultarAfiliado().getEstado());
                    if (!respuestaNobis.ReadXMLNobisConsultarAfiliado().getEstado().equals("Afiliado inexistente")) {
                        dni = respuestaNobis.ReadXMLNobisConsultarAfiliado().getDni();
                        nombreafiliado = respuestaNobis.ReadXMLNobisConsultarAfiliado().getAfiliado();
                        Codigo_afiliado = respuestaNobis.ReadXMLNobisConsultarAfiliado().getNumeroAfi();
                        habilitado = "OK";
                        JOptionPane.showMessageDialog(null, "Mensaje WS: " + "El Afiliado está habilitado. " + "\n" + respuestaNobis.ReadXMLNobisConsultarAfiliado().getEstado() +" "+  respuestaNobis.ReadXMLNobisConsultarAfiliado().getMensaje());
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null,  "Mensaje WS: " + "El Afiliado no está habilitado. " + "\n" + respuestaNobis.ReadXMLNobisConsultarAfiliado().getEstado() +" "+ respuestaNobis.ReadXMLNobisConsultarAfiliado().getMensaje());
                    }
                } catch (Exception er) {
                    JOptionPane.showMessageDialog(null, "error al generar archivo " + er);
                    System.out.println("error al generar archivo " + er);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error al conectarse con servidor " + resultado);
                System.out.println("error al conectarse con servidor " + resultado);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error " + e);
            System.out.println("e" + e);
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        try {
//            TipoPrestadores mensaje = new TipoPrestadores();
//            mensaje.setPUsuario("CBTUCUMAN");
//            mensaje.setPClave("CBTucuman22");
//            System.out.println("Send:" +mensaje.getPClave() +" "+mensaje.getPUsuario());
//            String resultado = null;
//            try {
//                WSGecrosNet servicio = new WSGecrosNet();
//                WSGecrosNetSoap port = servicio.getWSGecrosNetSoap();
//                resultado = port.tipoPrestadores(mensaje.getPUsuario(), mensaje.getPClave());
//                System.out.println("resultado:" + resultado);
//                
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "error al conectarse con servidor " + resultado);
//                System.out.println("error al conectarse con servidor " + resultado);
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "error " + e);
//            System.out.println("e" + e);
//        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
    }//GEN-LAST:event_btnValidarActionPerformed

    private void txtafiliadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtafiliadoKeyPressed
      //  btnValidar.requestFocus();
    }//GEN-LAST:event_txtafiliadoKeyPressed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnValidar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtafiliado;
    // End of variables declaration//GEN-END:variables
}
