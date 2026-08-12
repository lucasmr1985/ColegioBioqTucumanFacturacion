package Formularios;

import Clases.ConexionMariaDB;
import Clases.TripleDes;
import ClientePrevencion.WebServiceIA;
import ClientePrevencion.WebServiceIASoap;
import static Formularios.MainL.idObraSocialOnline;
import com.google.gson.Gson;
import java.awt.Cursor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.client.Entity;
import static Formularios.Login.id_usuario;
import com.google.gson.JsonObject;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ws.rs.core.MediaType;
import java.nio.charset.StandardCharsets;

public class AfiliadoPrevencion extends javax.swing.JDialog {

    public static String habilitado = "", nombreafiliado = "", dni = "", Codigo_afiliado = "", numero_afiliado = "", plan = "", csc = "", numeroPreautorizacion = "";

    String idmsj = "", hora = "", fechahora = "", codigo_seguridad = "", fecha = "", mensaje = "", respuesta = "", token = "",tokenWs="";

    public AfiliadoPrevencion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Prevención Salud");
        this.setLocationRelativeTo(null);
        cargarfecha();
        dni = "";
        csc = "";
        Codigo_afiliado = "";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtafiliado = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtToken = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbotipo = new javax.swing.JComboBox();
        btnaceptar = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese el número de Dni y Token", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Número:");

        txtafiliado.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtafiliado.setNextFocusableComponent(cbotipo);
        txtafiliado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtafiliadoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("CSC:");

        txtToken.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtToken.setNextFocusableComponent(btnaceptar);
        txtToken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTokenActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Tipo:");

        cbotipo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbotipo.setForeground(new java.awt.Color(0, 102, 204));
        cbotipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ambulatorio", "Internación" }));
        cbotipo.setNextFocusableComponent(txtToken);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtafiliado, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(txtToken, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbotipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
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
                    .addComponent(jLabel4)
                    .addComponent(cbotipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtToken, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnaceptar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    String completarceros(String v, int d) {
        String ceros = "";
        if (v.length() < d) {
            for (int i = v.length(); i < d; i++) {
                ceros += "0";
            }
            v = ceros + v;
        }
        return v;
    }

    void cursor() {
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        this.pack();
    }

    void cursor2() {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.pack();
    }

    void cargarfecha() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat formato2 = new SimpleDateFormat("yyMMddHHmmss");
        SimpleDateFormat formato3 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formato4 = new SimpleDateFormat("HHmmss");
        Date currentDate = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);

        fechahora = formato.format(currentDate);
        fecha = formato3.format(currentDate);
        hora = formato4.format(currentDate);
        codigo_seguridad = formato2.format(currentDate) + formato3.format(currentDate);
        System.out.println(fechahora);
        System.out.println(codigo_seguridad);
    }

    int insertToken(String token, String mensaje) {
        int insert = 0;
        /////////insert token
        System.out.println("fecha token:" + fecha);
        String SQL = "INSERT INTO tokens_ws(token, tiene_permiso,mensaje,id_obrasocial,id_colegiado,fecha,hora)"
                + "VALUES(?,?,?,?,?,?,?)";

        ConexionMariaDB maria = new ConexionMariaDB();
        Connection cn = maria.Conectar();
        try {
            PreparedStatement st = cn.prepareStatement(SQL);
            st.setString(1, token);
            st.setBoolean(2, false);
            st.setString(3, mensaje);
            st.setInt(4, 87);//id prevencion online
            st.setInt(5, id_usuario);//id colegiado
            st.setString(6, fecha);
            st.setString(7, hora);
            insert = st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos...");
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        System.out.println("insert:" + insert);
        return insert;
    }

    public class apiPrevencionLogin {

        private final String USER_AGENT = "Mozilla/5.0";

        // HTTP post request
        public void sendPost() throws Exception {
            String resJson = "";
            TripleDes tpDatos = new TripleDes();
            String uriLogin = "https://api.traditum.com/api/login";
            Gson gsonRespuesta = new Gson();
            try {
                System.out.println("Login token");
                try {
                    System.out.println("/////////////////////////////// LOGIN   /////////////////////////////////////////////////////////////////////////////////////////////////////");
                    
                    //Creamos el cliente de conexión al API Restful
                    Client client = ClientBuilder.newClient();

                    //Creamos el target lo cuál es nuestra URL junto con el nombre del método a llamar
                    WebTarget targetLogin = client.target(uriLogin);

                    ClientePrevencionSaludHL7.Login req = new ClientePrevencionSaludHL7.Login();
                    req.setUsername("IA007526");
                    req.setPassword("IA007526");
                    

                    //Invocation.Builder solicitud = targetLogin.request();
                    String basic = Base64.getEncoder().encodeToString((req.getUsername() + ":" +req.getPassword()).getBytes(StandardCharsets.UTF_8));

                    //Creamos nuestra solicitud que realizará el request                    
                    Invocation.Builder solicitud = targetLogin
                            .request(MediaType.APPLICATION_JSON_TYPE)
                            .accept(MediaType.APPLICATION_JSON_TYPE)
                            .header("Authorization", "Basic " + basic);
                    
                    //Enviamos nuestro json vía post al API Restful
                    Response post = solicitud.post(Entity.text(""));

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
                            ClientePrevencionSaludHL7.LoginResponse respuestaLogin = gsonRespuesta.fromJson(resJson, ClientePrevencionSaludHL7.LoginResponse.class);
                            respuestaLogin.muestraRespuesta();
                            insertToken(respuestaLogin.getAccess_token(), respuestaLogin.getExpires_in());

                            ////////////////////////////////////////////////////////////////////////////
                            String Mensaje = "MSH|^~\\&|TRIA0100M|TRIA00007526|PREV_SALUD|PREV_SALUD^001679^IIN|" + fechahora + "||ZQI^Z01^ZQI_Z01|" + codigo_seguridad + "|P|2.4|||NE|AL|ARG\r\n"
                                    + "PRD|PS^Prestador Solicitante||^^^T||||30522483881^CU|\r\n"
                                    + "PID|||" + dni + "^^" + csc + "^PREV_SALUD^DU^PREV_SALUD||UNKNOWN";
                            //+ "PID|||98902017^^^PREV_SALUD^HC||UNKNOWN";
                            //+ "PID|||" + Codigo_afiliado + "^^" + csc + "^PREV_SALUD^HC||UNKNOWN";

                            String clave = "IA007526";
                            String usuario = "IA007526";
                            String tipo = "SI";
                            String llave = "1234567890123456ABCDEFGH";//
                            String pszMsg = tpDatos.EncriptarStr(Mensaje, llave);
                            try { // Call Web Service Operation
                                WebServiceIA service = new WebServiceIA();
                                WebServiceIASoap port = service.getWebServiceIASoap();
                                // TODO initialize WS operation arguments here
                                String pszUser = tpDatos.EncriptarStr(usuario, llave);
                                String pszPwd = tpDatos.EncriptarStr(clave, llave);
                                String pszMsgType = tpDatos.EncriptarStr(tipo, llave);
                                // TODO process result here
                                String result = port.enviar(pszMsg, pszUser, pszPwd, pszMsgType);
                                System.out.println("Respuesta = " + result);
                                int i;
                                int pipe = 0, tilde = 0, acento = 0;
                                int bandera_dni = 0, bandera_afiliado = 0, bandera_afiliado_dni = 0;
                                ///busco la respuesta
                                i = result.indexOf("ZAU");
                                pipe = 0;
                                while (i < result.indexOf("PRD")) {
                                    if (pipe == 3) {
                                        mensaje = mensaje + result.charAt(i);
                                    }
                                    if (result.charAt(i) == '|') {
                                        pipe++;
                                    }
                                    i++;
                                }
                                String codigo_respuesta = mensaje.substring(0, 4);
                                mensaje = mensaje.replace("^", " ");
                                System.out.println(dni);
                                System.out.println(nombreafiliado);
                                System.out.println(plan);

                                System.out.println(codigo_respuesta);

                                if (codigo_respuesta.equals("B000") || codigo_respuesta.equals("B001")) {
                                    habilitado = "OK";
                                    //buscar dni, apellido y nombre del afiliado 
                                    i = result.indexOf("PID");
                                    pipe = 0;
                                    while (i < result.indexOf("IN1")) {
//                    if (bandera_dni == 0) {
//                        if (pipe == 3) {//busco el dni
//                            int j = i;
//                            while (j < result.indexOf("IN1")) {
//                                if (result.charAt(j) == '~') {
//                                    int k = j + 1;
//                                    tilde++;
//                                    while (k < result.indexOf("IN1")) {
//                                        if (result.charAt(k) == '^') {
//                                            k = result.indexOf("IN1");
//                                            j = result.indexOf("IN1");
//                                            bandera_dni = 1;
//                                        } else {
//                                            dni = dni + result.charAt(k);
//                                        }
//                                        k++;
//                                    }
//                                }
//                                j++;
//                            }
//                        }
//                    }
//                    if (bandera_afiliado == 0) {
//                        
//                        if (pipe == 5) {//busco apellido y nombre                            
//                            int j = i;
//                            System.out.println("result:"+result);
//                            while (j < result.indexOf("IN1")) {
//                                System.out.println("apellido:"+j+" - "+result.charAt(j));
//                                if (result.charAt(j) != '|') {
//                                    nombreafiliado = nombreafiliado + result.charAt(j);
//                                } else {
//                                    System.out.println("IN1|");
//                                    j = result.indexOf("IN1");
//                                    bandera_afiliado = 1;
//                                }
//                                j++;
//                            }
//                        }
//                    }
                                        if (bandera_afiliado == 0) {
                                            if (pipe == 5) {//busco apellido y nombre   
                                                // System.out.println("busco apellido y nombre");
                                                int j = i;
                                                while (j < result.indexOf("IN1|")) {
                                                    // System.out.println("System.out.println(IN1|):"+result.indexOf("IN1|"));
                                                    if (result.charAt(j) != '|') {
                                                        //System.out.println("result.charAt(j):"+result.charAt(j));
                                                        nombreafiliado = nombreafiliado + result.charAt(j);
                                                    } else {

                                                        j = result.indexOf("IN1");

                                                    }
                                                    j++;
                                                }
                                                bandera_afiliado = 1;
                                            }
                                        }
                                        if (bandera_afiliado_dni == 0) {
                                            if (pipe == 3) {//busco num af
                                                int j = i;
                                                while (j < result.indexOf("IN1")) {
                                                    if (tilde == 0) {
                                                        Codigo_afiliado = Codigo_afiliado + result.charAt(j);
                                                    } else {
                                                        if (tilde == 1) {
                                                            Codigo_afiliado = Codigo_afiliado + result.charAt(j);
                                                        } else {
                                                            j = result.indexOf("IN1");
                                                            bandera_afiliado_dni = 1;
                                                        }
                                                    }
                                                    if (result.charAt(j) == '^') {
                                                        tilde++;
                                                    }
                                                    j++;
                                                }
                                            }
                                        }
                                        if (result.charAt(i) == '|') {
                                            pipe++;
                                        }
                                        i++;
                                        //System.out.println("pipe:"+pipe);
                                        // System.out.println("i:"+i);
                                    }
                                    nombreafiliado = nombreafiliado.replace("^", " ");
                                    //////////busco el plan/////////////////////////////////////////////
                                    i = result.indexOf("ZIN|");
                                    System.out.println("zin" + i);
                                    System.out.println("fin " + result.length());
                                    pipe = 0;
                                    if (result.indexOf("NTE|") > 0) {
                                        while (i < (result.indexOf("NTE|") - 1)) {
                                            System.out.println("char:" + result.charAt(i));
                                            if (pipe == 2) {
                                                plan = plan + result.charAt(i);
                                            }
                                            if (result.charAt(i) == '|') {
                                                pipe++;
                                            }
                                            i++;
                                        }
                                    } else {
                                        while (i < (result.length() - 1)) {
                                            System.out.println("char:" + result.charAt(i));
                                            if (pipe == 2) {
                                                plan = plan + result.charAt(i);
                                            }
                                            if (result.charAt(i) == '|') {
                                                pipe++;
                                            }
                                            i++;
                                        }
                                    }
                                    System.out.println("plan:" + plan);
                                    System.out.println("nombreafiliado: " + nombreafiliado);
                                    System.out.println("dni:" + dni);
                                    numero_afiliado = Codigo_afiliado;
                                    numero_afiliado = numero_afiliado.replace("^", "");
                                    System.out.println("numero_afiliado:" + numero_afiliado);
                                    System.out.println("Codigo_afiliado:" + Codigo_afiliado);
                                    JOptionPane.showMessageDialog(null, mensaje);
                                    JOptionPane.showMessageDialog(null, "El token expira en "+Integer.valueOf(respuestaLogin.getExpires_in())/60+" minutos");
                                    tokenWs=respuestaLogin.getAccess_token();
//                                    int opcion = JOptionPane.showOptionDialog(null, "Posee una orden PreAutorizada?", "Sancor PreAutorización", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
//                                            new Object[]{"Si", "No", "Cancelar"}, "No"); // dinde quieres que se posicione el selector
//
//                                    if (opcion == 0) {//si
//                                        new PrevencionPreAutorizacion(null, true).setVisible(true);
//                                        dispose();
//                                    } else {
//                                        dispose();
//                                    }
                                    dispose();

                                } else {
                                    if (codigo_respuesta.equals("M000")) {
                                        JOptionPane.showMessageDialog(null, "Los datos ingresados no corresponden a un afiliado activo");
                                    }
                                    if (codigo_respuesta.equals("M001") || codigo_respuesta.equals("M003")) {
                                        JOptionPane.showMessageDialog(null, "AFILIADO INEXISTENTE - Por Favor Verifique el Numero ingresado");
                                    }
                                    if (codigo_respuesta.equals("M004")) {
                                        JOptionPane.showMessageDialog(null, "-AFILIADO DADO DE BAJA-");
                                    }
                                    if (codigo_respuesta.equals("M005")) {
                                        JOptionPane.showMessageDialog(null, "-AFILIADO MOROSO-");
                                    }

                                    habilitado = "!OK";
                                }
                                cursor2();
                            } catch (Exception ex) {
                                cursor2();
                                JOptionPane.showMessageDialog(null, "Error al intetar validar el paciente. Intente nuevamente.");
                                System.out.println("Excpecion Prevencion:" + ex);
                            }
                            break;
                        default:
                            cursor2();
                            System.out.println("/////////////////////////////// Respuesta Login  error  /////////////////////////////////////////////////////////////////////////////////////////////////////");
                            resJson = "Error";
                            System.out.println("Error " + post.getStatusInfo().toString() + ": " + post.getStatus());
                            JOptionPane.showMessageDialog(null, "Error al conectarse al servidor de Sancor: " + post.getStatusInfo().toString() + ": " + post.getStatus());
                            break;
                    }
                } catch (Exception e) {
                    cursor2();
                    //En caso de un error en la solicitud, llenaremos res con la exceptión para verificar que sucedió
                    resJson = e.toString();
                }

            } catch (Exception ex) {
                cursor2();
                JOptionPane.showMessageDialog(null, "Error al intetar validar el paciente. Intente nuevamente.");
                System.out.println("Excpecion Prevencion:" + ex);
            }
        }
    }


    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        apiPrevencionLogin post = new apiPrevencionLogin();
        mensaje = "";
        habilitado = "";
        nombreafiliado = "";
        Codigo_afiliado = "";

        try {
            if (txtafiliado.getText().length() > 0 && txtafiliado.getText().length()==8) {
                //Codigo_afiliado = txtafiliado.getText().substring(0, 7)+"^"+txtafiliado.getText().substring(7, 9);
                dni = txtafiliado.getText();
                csc = txtToken.getText();
                System.out.println("Codigo_afiliado:" + Codigo_afiliado);
                System.out.println("dni:" + dni);
                System.out.println("csc:" + csc);
                post.sendPost();
            } else {
                JOptionPane.showMessageDialog(null, "Completar con el dni del paciente, sin puntos.(XXXXXXXX)");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al conectarse al servidor de Sancor");
            Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnaceptarActionPerformed


    private void txtafiliadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtafiliadoActionPerformed
        txtafiliado.transferFocus();
    }//GEN-LAST:event_txtafiliadoActionPerformed

    private void txtTokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTokenActionPerformed
        txtToken.transferFocus();
    }//GEN-LAST:event_txtTokenActionPerformed

    private void cbotipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotipoActionPerformed
        if(cbotipo.getSelectedItem().toString().equals("Ambulatorio")){
            MainL.tipo_orden=1;
        }else{
            MainL.tipo_orden=3;
        }
    }//GEN-LAST:event_cbotipoActionPerformed

    private void cbotipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbotipoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbotipo.transferFocus();
        }
    }//GEN-LAST:event_cbotipoKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceptar;
    private javax.swing.JComboBox cbotipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtToken;
    private javax.swing.JTextField txtafiliado;
    // End of variables declaration//GEN-END:variables
}
