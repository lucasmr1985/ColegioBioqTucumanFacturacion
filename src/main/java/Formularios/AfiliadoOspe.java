package Formularios;

import Clases.ReadXMLFile;
import ClienteOspe.ExecuteFileTransactionSL;
import ClienteOspe.WSActiviaC;
import ClienteOspe.WSActiviaCSoap;
import static Formularios.Login.cuit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.EOFException;
import org.jdom2.Document;

public class AfiliadoOspe extends javax.swing.JDialog {

    public static String habilitado = "", nombreafiliado = "", dni = "", Codigo_afiliado = "", CSC_OS = "";

    String hora = "", fecha = "", pasaporte = "", mensaje = "", respuesta = "";

    public AfiliadoOspe(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        this.setTitle("Ospe");
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
        /////////////////////////////////////////////////////////////////
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        java.util.Date currentDate = new java.util.Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);
        fecha = formato.format(currentDate);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtafiliado = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtcsc = new javax.swing.JTextField();
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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("CSC:");

        txtcsc.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtcsc.setForeground(new java.awt.Color(0, 102, 204));
        txtcsc.setToolTipText("código de seguridad de la tarjeta del afiliado (XXX)");
        txtcsc.setNextFocusableComponent(btnValidar);
        txtcsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcscActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtcsc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 93, Short.MAX_VALUE))
                    .addComponent(txtafiliado))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcsc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
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
                .addContainerGap())
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
        CSC_OS = txtcsc.getText();       
        System.out.println("numero_afiliado " + numero_afiliado);

        Document doc = new Document();
        try {
            ExecuteFileTransactionSL mensaje = new ExecuteFileTransactionSL();
            mensaje.setPos("0000");

            String xml = "<Mensaje>\n"
                    + "    <EncabezadoMensaje>\n"
                    + "        <VersionMsj>ACT20</VersionMsj>\n"
                    + "        <TipoMsj>OL</TipoMsj>\n"
                    + "        <TipoTransaccion>01A</TipoTransaccion>\n"
                    + "        <IdMsj/>\n"
                    + "        <InicioTrx>\n"
                    + "            <FechaTrx>" + fecha + "</FechaTrx>\n"
                    + "             <HoraTrx>" + hora + "</HoraTrx>\n"
                    + "        </InicioTrx>\n"
                    + "        <Terminal>\n"
                    + "            <TipoTerminal>PC</TipoTerminal>\n"
                    + "            <NumeroTerminal>21000037</NumeroTerminal>\n"
                    + "        </Terminal>\n"
                    + "        <Financiador>\n"
                    + "            <CodigoFinanciador>OSPE</CodigoFinanciador>\n"
                    + "        </Financiador>\n"
                    + "        <Prestador>\n"
                    + "            <CuitPrestador>30522483881</CuitPrestador>\n"
                    + "            <RazonSocial>Colegio de Bioquimicos de Tucuman</RazonSocial>\n"
                    + "        </Prestador>\n"
                    + "    </EncabezadoMensaje>\n"
                    + "    <EncabezadoAtencion>\n"
                    + "        <Credencial>\n"
                    + "            <NumeroCredencial>" + numero_afiliado + "</NumeroCredencial>\n"
                    + "            <ModoIngreso>M</ModoIngreso>\n"
                    + "            <CodigoSeguridad>" + CSC_OS + "</CodigoSeguridad>\n"
                    + "        </Credencial>\n"
                    + "    </EncabezadoAtencion>\n"
                    + "</Mensaje>";
            mensaje.setFileContent(xml);
            System.out.println("Send:" + mensaje.getPos() + " " + mensaje.getFileContent());
            String resultado = null;
            try {
                WSActiviaC servicio = new WSActiviaC();
                WSActiviaCSoap port = servicio.getWSActiviaCSoap();
                resultado = port.executeFileTransactionSL(mensaje.getPos(), mensaje.getFileContent());
                System.out.println("resultado:" + resultado);
                //Generate XML
                try {
                    FileWriter archivo = new FileWriter("C:/Facturacion Laboratorios/respuesta.xml");
                    archivo.write(resultado);
                    archivo.close();
                    System.out.println(".....");
                    ReadXMLFile respuestaOspe = new ReadXMLFile();
                    respuestaOspe.ReadXMLOspe01A();
                    System.out.println("-----");
                    if (respuestaOspe.ReadXMLOspe01A().getCodigo().equals("00")) {
                        dni = respuestaOspe.ReadXMLOspe01A().getDni();
                        nombreafiliado = respuestaOspe.ReadXMLOspe01A().getAfiliado();
                        Codigo_afiliado = numero_afiliado;
                        habilitado="OK";
                        JOptionPane.showMessageDialog(null, "El Afiliado está habilitado. N° de ref: "+respuestaOspe.ReadXMLOspe01A().getNroReferencia()+"\n"+"Mensaje WS: "+respuestaOspe.ReadXMLOspe01A().getRespuesta()+" - "+respuestaOspe.ReadXMLOspe01A().getMensaje());
                        dispose();
                    }else{                        
                        JOptionPane.showMessageDialog(null, "N° de ref: "+respuestaOspe.ReadXMLOspe01A().getNroReferencia() + "\n"+"Mensaje WS: "+ respuestaOspe.ReadXMLOspe01A().getRespuesta() + " "+ respuestaOspe.ReadXMLOspe01A().getMensaje());
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
    }//GEN-LAST:event_btnValidarActionPerformed

    private void txtcscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcscActionPerformed
        txtcsc.transferFocus();
    }//GEN-LAST:event_txtcscActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnValidar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtafiliado;
    private javax.swing.JTextField txtcsc;
    // End of variables declaration//GEN-END:variables
}
