package Formularios;

import static Formularios.MainL.ipLocal;
import static Formularios.MainL.hostLocal;
import com.google.gson.Gson;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import ClienteSwissMedicalApi.Login;
import ClienteSwissMedicalApi.Device;
import ClienteSwissMedicalApi.ElegibiliadadResponse;
import ClienteSwissMedicalApi.Elegibilidad;
import jakarta.ws.rs.client.Entity;
import ClienteSwissMedicalApi.LoginResponse;
import ClienteSwissMedicalApi.LoginError;
import static Formularios.Login.id_usuario;

public class AfiliadoSwiss extends javax.swing.JDialog {

    String hora = "", fechasw = "";
    public static String habilitado = "", nombreafiliado = "", Codigo_afiliado = "", CSC_SW = "", apiKey = "";

    public AfiliadoSwiss(java.awt.Frame parent, boolean modal) {
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
        fechasw = formato.format(currentDate);
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese el numero de afiliado de Sw", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N

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

    public class apiSwElegibilidad {

        private final String USER_AGENT = "Mozilla/5.0";

        // HTTP post request
        public void sendPost() throws Exception {
            String resJson = "";
            //String uriLogin ="https://mobilepre.swissmedical.com.ar/pre/api-smg/v0/auth-login";
            String uriLogin = "https://mobile.swissmedical.com.ar/pre/api-smg/v0/auth-login";
            //String uriElegibilidad = "https://mobilepre.swissmedical.com.ar/pre/api-smg/v3.0/prestadores/hl7/elegibilidad";
            String uriElegibilidad = "https://mobile.swissmedical.com.ar/pre/api-smg/v1.0/prestadores/hl7/elegibilidad";
            String jsonString;
            Gson gsonEnvio = new Gson();
            Gson gsonRespuesta = new Gson();
            Gson gsonError = new Gson();
            try {
                System.out.println("/////////////////////////////// LOGIN   /////////////////////////////////////////////////////////////////////////////////////////////////////");
                //Creamos el cliente de conexión al API Restful
                Client client = ClientBuilder.newClient();

                //Creamos el target lo cuál es nuestra URL junto con el nombre del método a llamar
                WebTarget targetLogin = client.target(uriLogin);

                //Creamos nuestra solicitud que realizará el request
                Invocation.Builder solicitud = targetLogin.request();

                Login req = new Login();
                req.setApiKey("06715fdb87c4adb2c176");
                req.setUsrLoginName("hl7ApiUser");
                req.setPassword("Swiss1234");
                req.setCuit("30522483881");
                Device dev = new Device();
                dev.setMessagingid(id_usuario+"B"+hora);
                dev.setDeviceid(ipLocal);
                dev.setDevicename(hostLocal);
                dev.setBloqueado(0);
                dev.setRecordar(0);
                req.setDevice(dev);

                //Convertimos el objeto req a un json
                jsonString = gsonEnvio.toJson(req);
                System.out.println(jsonString);

                //Enviamos nuestro json vía post al API Restful
                Response post = solicitud.post(Entity.json(jsonString));

                //Recibimos la respuesta y la leemos en una clase de tipo String, en caso de que el json sea tipo json y no string, debemos usar la clase de tipo JsonObject.class en lugar de String.class
                String responseJson = post.readEntity(String.class);
                resJson = responseJson;

                //Imprimimos el status de la solicitud
                System.out.println("Estatus: " + post.getStatus());

                switch (post.getStatus()) {
                    case 200:
                        resJson = responseJson;
                        System.out.println("/////////////////////////////// Respuesta Login   /////////////////////////////////////////////////////////////////////////////////////////////////////");
                        //Imprimimos la respuesta del API Restful
                        System.out.println(resJson);
                        LoginResponse respuestaLogin = gsonRespuesta.fromJson(resJson, LoginResponse.class);
                        respuestaLogin.muestraRespuesta();
                        System.out.println("/////////////////////////////// Elegibilidad   /////////////////////////////////////////////////////////////////////////////////////////////////////");
                        WebTarget targetElegibilidad = client.target(uriElegibilidad);

                        //Creamos nuestra solicitud que realizará el request
                        Invocation.Builder solicitudElegibilidad = targetElegibilidad.request();

                        //Creamos y llenamos nuestro objeto BaseReq con los datos que solicita el API
                        Elegibilidad afiliado = new Elegibilidad();
                        afiliado.setCreden(Codigo_afiliado);
                        afiliado.setAlta(fechasw);
                        afiliado.setFecdif(fechasw);
                        afiliado.setCodPrestador("57594");
                        afiliado.setTermId("SMIA00000001");

                        //Convertimos el objeto req a un json
                        jsonString = gsonEnvio.toJson(afiliado);
                        System.out.println(jsonString);

                        //Enviamos nuestro json vía post al API Restful
                        Object appToken = "Bearer " + respuestaLogin.getToken();
                        Object Type = "application/json";
                        Object Accept = "application/json";

                        solicitudElegibilidad.header("Content-Type", Type).head();
                        solicitudElegibilidad.header("Accept", Accept).head();
                        solicitudElegibilidad.header("Authorization", appToken).head();

                        Response postE = solicitudElegibilidad.post(Entity.json(jsonString));

                        //Recibimos la respuesta y la leemos en una clase de tipo String, en caso de que el json sea tipo json y no string, debemos usar la clase de tipo JsonObject.class en lugar de String.class
                        String responseJsonElegibilidad = postE.readEntity(String.class);
                        resJson = responseJsonElegibilidad;

                        //Imprimimos el status de la solicitud
                        System.out.println("Estatus elegibilidad: " + postE.getStatus());

                        switch (postE.getStatus()) {
                            case 200:
                                resJson = responseJsonElegibilidad;
                                
                                System.out.println("/////////////////////////////// Respuesta Elegibilidad   /////////////////////////////////////////////////////////////////////////////////////////////////////");
                                //Imprimimos la respuesta del API Restful
                                System.out.println(resJson);
                                ElegibiliadadResponse respuestaElegibilidad = gsonRespuesta.fromJson(resJson, ElegibiliadadResponse.class);
                                if(respuestaElegibilidad.getRechaCabecera()==0){
                                    JOptionPane.showMessageDialog(null, "Afiliado habilitado");
                                    nombreafiliado = respuestaElegibilidad.getApeNom();   
                                    apiKey = "Bearer " +respuestaLogin.getToken();
                                    habilitado = "OK";
                                    dispose();
                                }else{
                                    JOptionPane.showMessageDialog(null, respuestaElegibilidad.getRechaCabecera() +" - " + respuestaElegibilidad.getRechaCabeDeno());
                                    txtafiliado.requestFocus();
                                    habilitado = "Error";
                                }                                
                                respuestaElegibilidad.muestraRespuesta();
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                break;
                            default:
                                resJson = "Error";
                                habilitado = "Error";
                                System.out.println("Error de elegibilidad");
                                JOptionPane.showMessageDialog(null, "Error al conectarse al servidor de Swiss Medical: "+postE.getStatusInfo().toString()+": "+postE.getStatus());
                                break;
                        }

                        break;
                    default:
                        System.out.println("/////////////////////////////// Respuesta Login  error  /////////////////////////////////////////////////////////////////////////////////////////////////////");
                        resJson = "Error";     
                        System.out.println("Error "+post.getStatusInfo().toString()+": "+post.getStatus());
                        JOptionPane.showMessageDialog(null, "Error al conectarse al servidor de Swiss Medical: "+post.getStatusInfo().toString()+": "+post.getStatus());
                        break;

                }

            } catch (Exception e) {
                //En caso de un error en la solicitud, llenaremos res con la exceptión para verificar que sucedió
                resJson = e.toString();
            }
        }
    }

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        CSC_SW = txtcsc.getText();
        Codigo_afiliado = txtafiliado.getText();
        apiSwElegibilidad post = new apiSwElegibilidad();

        System.out.println("Testing 1 - Send Http post request");
        try {
            post.sendPost();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al conectarse al servidor de Swiss Medical");
            Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
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
