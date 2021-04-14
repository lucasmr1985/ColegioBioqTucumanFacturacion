package Formularios;

import static Formularios.Login.cuit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class SwissAfiliado extends javax.swing.JDialog {

    String hora = "", fechaosde = "", pasaporte = "", mensaje = "", respuesta = "";
    public static String habilitado = "", nombreafiliado = "", Codigo_afiliado = "",CSC_SW="";

    public SwissAfiliado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Swiss Medical");
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
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        java.util.Date currentDate = new java.util.Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);
        fechaosde = formato.format(currentDate);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtafiliado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbotipo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txtcsc = new javax.swing.JTextField();
        btnaceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese el numero de afiliado de Sw", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Numero:");

        txtafiliado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtafiliado.setForeground(new java.awt.Color(0, 102, 204));
        txtafiliado.setText("800006");
        txtafiliado.setToolTipText("");
        txtafiliado.setSelectionStart(0);
        txtafiliado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtafiliadoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Tipo:");

        cbotipo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbotipo.setForeground(new java.awt.Color(0, 102, 204));
        cbotipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ambulatorio", "Internación" }));
        cbotipo.setNextFocusableComponent(txtcsc);
        cbotipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbotipoActionPerformed(evt);
            }
        });
        cbotipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbotipoKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("CSC:");

        txtcsc.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtcsc.setForeground(new java.awt.Color(0, 102, 204));
        txtcsc.setToolTipText("código de seguridad de la tarjeta del afiliado (XXX)");
        txtcsc.setNextFocusableComponent(btnaceptar);
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
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtafiliado)
                    .addComponent(cbotipo, 0, 172, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtcsc, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(jLabel2)
                    .addComponent(cbotipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtcsc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnaceptar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnaceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728951 - electricity lightning.png"))); // NOI18N
        btnaceptar.setText("Validar");
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
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
                        .addComponent(btnaceptar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnaceptar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtafiliadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtafiliadoActionPerformed
        txtafiliado.transferFocus();
    }//GEN-LAST:event_txtafiliadoActionPerformed

    private void cbotipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotipoActionPerformed
        if (cbotipo.getSelectedItem().toString().equals("Ambulatorio")) {
            MainL.tipo_orden = 01;
        } else {
            MainL.tipo_orden = 03;
        }
    }//GEN-LAST:event_cbotipoActionPerformed

    private void cbotipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbotipoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbotipo.transferFocus();
        }
    }//GEN-LAST:event_cbotipoKeyPressed

    public class HttpURLConnectionExample {

        private final String USER_AGENT = "Mozilla/5.0";

        // HTTP GET request
        public void sendGet() throws Exception {
            ///http://ws.itcsoluciones.com:38080/jSitelServlet/Do?
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

        }
    }

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        cargarfecha();
        respuesta = "";
        String numero_afiliado = txtafiliado.getText();
        CSC_SW = txtcsc.getText();
        if (numero_afiliado.length() > 19) {
            numero_afiliado = numero_afiliado.substring(numero_afiliado.length() - 11, numero_afiliado.length());
        }
        System.out.println("numero_afiliado " + numero_afiliado);
        mensaje = "<Mensaje><EncabezadoMensaje><VersionMsj>1.0</VersionMsj><TipoTransaccion>01A</TipoTransaccion><IdMsj>" + hora + "</IdMsj><InicioTrx><FechaTrx>" + fechaosde + "</FechaTrx><HoraTrx>" + hora + "</HoraTrx></InicioTrx><Terminal><TipoTerminal>PC</TipoTerminal><NumeroTerminal>1</NumeroTerminal></Terminal><Financiador><CodigoFinanciador>26</CodigoFinanciador><CuitFinanciador>30654855168</CuitFinanciador></Financiador><Prestador><CuitPrestador>" + cuit + "</CuitPrestador></Prestador></EncabezadoMensaje><EncabezadoAtencion><Efector/><Prescriptor/><Credencial><NumeroCredencial>" + numero_afiliado + "</NumeroCredencial><VersionCredencial>" + CSC_SW + "</VersionCredencial></Credencial><Preautorizacion/><Documentacion/><Atencion/><Diagnostico/><CodFinalizacionTratamiento/><MensajeParaFinanciador/></EncabezadoAtencion><DetalleProcedimientos/></Mensaje>";
        // mensaje = "<Mensaje><EncabezadoMensaje><VersionMsj>1.0</VersionMsj><NroReferenciaCancel>000098351394</NroReferenciaCancel><TipoTransaccion>04A</TipoTransaccion><IdMsj>3354</IdMsj><InicioTrx><FechaTrx>20091005</FechaTrx><HoraTrx>193020</HoraTrx></InicioTrx><Financiador><CodigoFinanciador>11</CodigoFinanciador><CuitFinanciador>30546741253</CuitFinanciador></Financiador><Prestador><CuitPrestador>30708402911</CuitPrestador></Prestador>"+Login.cuit+"</EncabezadoMensaje><EncabezadoAtencion><Efector/><Prescriptor/><Credencial><NumeroCredencial>60671956201</NumeroCredencial></Credencial><Atencion><FechaAtencion>20091001</FechaAtencion></Atencion></EncabezadoAtencion></Mensaje>";

        HttpURLConnectionExample http = new HttpURLConnectionExample();

        System.out.println("Testing 1 - Send Http GET request");
        try {
            http.sendGet();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al conectarse al servidor de Swiss Medical");
            Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
        }
        int pos = respuesta.indexOf("MensajeDisplay");
        int pos2 = respuesta.indexOf("</MensajeDisplay");

        int pos3 = respuesta.indexOf("Nombre:");
        int pos4 = respuesta.indexOf("Plan:");
        if (respuesta.substring(pos + 15, pos + 17).equals("OK")) {
            habilitado = "OK";
            //JOptionPane.showMessageDialog(null, "El Afiliado esta habilitado");
            nombreafiliado = respuesta.substring(pos3 + 8, pos3 + 38);
            int pos0 = respuesta.indexOf("Afiliado : ") + 11;
            int pos1 = pos0 + 19;
            System.out.println(respuesta.substring(pos0, pos1));
            Codigo_afiliado = respuesta.substring(pos0, pos1);
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, respuesta.substring(pos + 15, pos2));
            txtafiliado.requestFocus();
        }
    }//GEN-LAST:event_btnaceptarActionPerformed

    private void txtcscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcscActionPerformed
        txtcsc.transferFocus();
    }//GEN-LAST:event_txtcscActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceptar;
    private javax.swing.JComboBox cbotipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtafiliado;
    private javax.swing.JTextField txtcsc;
    // End of variables declaration//GEN-END:variables
}
