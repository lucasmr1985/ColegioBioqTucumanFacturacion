package Formularios;

import Clases.TripleDes;
import static ClienteAsunt.AsuntConexionWsdl.conexionWsdl;
import ClienteMedife.WebServiceIA;
import ClienteMedife.WebServiceIASoap;
import static Formularios.Login.cuit;
import static Formularios.Login.matricula_colegiado;
import static Formularios.MainL.idObraSocialOnline;
import java.awt.Cursor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

public class AfiliadoAsunt extends javax.swing.JDialog {

    public static String habilitado = "", nombreafiliado = "", dni = "", Codigo_afiliado = "", plan = "";

    String idmsj = "", hora = "", fechahora = "", codigo_seguridad = "", mensaje = "", respuesta = "";

    public AfiliadoAsunt(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Asunt");
        this.setLocationRelativeTo(null);
        cargarfecha();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtafiliado = new javax.swing.JFormattedTextField();
        btnaceptar = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese el numero de afiliado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Número:");

        txtafiliado.setForeground(new java.awt.Color(0, 102, 204));
        txtafiliado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##############"))));
        txtafiliado.setToolTipText("Completar con los 15 digitos");
        txtafiliado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtafiliado.setNextFocusableComponent(btnaceptar);
        txtafiliado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtafiliadoActionPerformed(evt);
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
                .addComponent(txtafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnaceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        /* this.btnCursor02.setCursor(new Cursor(Cursor.HAND_CURSOR));
         this.btnCursor03.setCursor(new Cursor(Cursor.MOVE_CURSOR));
         this.btnCursor04.setCursor(new Cursor(Cursor.TEXT_CURSOR));*/

        //this.add(this.btnimprimir1);
        /* this.add(this.btnCursor02);
         this.add(this.btnCursor03);
         this.add(this.btnCursor04);*/
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        this.pack();
    }

    void cursor2() {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        /* this.btnCursor02.setCursor(new Cursor(Cursor.HAND_CURSOR));
         this.btnCursor03.setCursor(new Cursor(Cursor.MOVE_CURSOR));
         this.btnCursor04.setCursor(new Cursor(Cursor.TEXT_CURSOR));*/

        //this.add(this.btnimprimir1);
        /* this.add(this.btnCursor02);
         this.add(this.btnCursor03);
         this.add(this.btnCursor04);*/
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.pack();
    }

    void cargarfecha() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat formato2 = new SimpleDateFormat("yyMMddHHmmss");
        SimpleDateFormat formato3 = new SimpleDateFormat("yyyyMMdd");
        Date currentDate = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);

        fechahora = formato.format(currentDate);
        codigo_seguridad = formato2.format(currentDate) + formato3.format(currentDate);
        System.out.println(fechahora);
        System.out.println(codigo_seguridad);
    }

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        
        cursor();
        JSONObject jsonConsulta = new JSONObject();
        jsonConsulta.put("servicio", 1);
        jsonConsulta.put("tipo", "DNI");
        jsonConsulta.put("dni", txtafiliado.getText());
        JSONObject json = null;
        try {
            json = conexionWsdl(jsonConsulta);
            cursor2();
            System.out.println("json respuesta consulta afiliado: "+json.toString());
            if (json.getBoolean("resultado") == true ) {
                habilitado = "OK";
                nombreafiliado = json.getString("nombre");
                dni = json.getString("dni");
                Codigo_afiliado=json.getString("dni");
                JOptionPane.showMessageDialog(null, "Afiliado habilitado");
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Por Favor Verifique el Numero ingresado. "+json.getString("nombre"));
                txtafiliado.requestFocus();
            }
        } catch (IOException ex) {
            cursor2();
            Logger.getLogger(AfiliadoAsunt.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al intentar conectarse con servidor Asunt "+ex);
        } catch (ParserConfigurationException ex) {
            cursor2();
            Logger.getLogger(AfiliadoAsunt.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al intentar conectarse con servidor Asunt "+ex);
        } catch (SAXException ex) {
            cursor2();
            Logger.getLogger(AfiliadoAsunt.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al intentar conectarse con servidor Asunt "+ex);
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        mensaje = "";
//        habilitado = "";
//        nombreafiliado = "";
//        dni = "";
//        Codigo_afiliado = "";
//        plan = "";
//       
//        TripleDes tpDatos = new TripleDes();
//        Codigo_afiliado = txtafiliado.getText();
//        ////////////////////////////////////////////////////////////////////////////yyyyMMddHHMMss                  yyMMddHHMMssyyyyMMdd
//        String Mensaje = "MSH|^~\\&|TRIA0100M|TRIA00007526|MEDIFE|MEDIFE^222222^IIN|" + fechahora + "||ZQI^Z01^ZQI_Z01|" + codigo_seguridad + "|P|2.4|||NE|AL|ARG\r\n"
//                + "PRD|PS^Prestador Solicitante||^^^T||||30522483881^CU|\r\n"
//                + "PID|||" + Codigo_afiliado + "^^^MEDIFE^HC^MEDIFE||UNKNOWN";
//        String clave = "IA007526";
//        String usuario = "IA007526";
//        String tipo = "SI";
//        String llave = "1234567890123456ABCDEFGH";
//        String pszMsg = tpDatos.EncriptarStr(Mensaje, llave);
//        try { // Call Web Service Operation
//            WebServiceIA service = new WebServiceIA();
//            WebServiceIASoap port = service.getWebServiceIASoap();
//            // TODO initialize WS operation arguments here
//            String pszUser = tpDatos.EncriptarStr(usuario, llave);
//            String pszPwd = tpDatos.EncriptarStr(clave, llave);
//            String pszMsgType = tpDatos.EncriptarStr(tipo, llave);
//            // TODO process result here
//            String result = port.enviar(pszMsg, pszUser, pszPwd, pszMsgType);
//            System.out.println("Respuesta = " + result);
//            int i;
//            int pipe = 0, tilde = 0, acento = 0;
//            int bandera_dni = 0, bandera_afiliado = 0;
//            ///busco la respuesta
//            i = result.indexOf("ZAU");
//            pipe = 0;
//            while (i < result.indexOf("PRD")) {
//                if (pipe == 3) {
//                    mensaje = mensaje + result.charAt(i);
//                }
//                if (result.charAt(i) == '|') {
//                    pipe++;
//                }
//                i++;
//            }
//            String codigo_respuesta = mensaje.substring(0, 4);
//            mensaje = mensaje.replace("^", " ");
//            System.out.println(dni);
//            System.out.println(nombreafiliado);
//            System.out.println(plan);
//
//            System.out.println(codigo_respuesta);
//
//            if (codigo_respuesta.equals("B000") || codigo_respuesta.equals("B001")) {
//                habilitado = "OK";
//                //buscar dni, apellido y nombre del afiliado 
//                i = result.indexOf("PID");
//                pipe = 0;
//                while (i < result.indexOf("IN1")) {
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
//                        if (pipe == 5) {//busco apellido y nombre
//                            int j = i;
//                            while (j < result.indexOf("IN1")) {
//                                if (result.charAt(j) != '|') {
//                                    nombreafiliado = nombreafiliado + result.charAt(j);
//                                } else {
//                                    j = result.indexOf("IN1");
//                                    bandera_afiliado = 1;
//                                }
//                                j++;
//                            }
//                        }
//                    }
//                    if (result.charAt(i) == '|') {
//                        pipe++;
//                    }
//                    i++;
//                }
//                nombreafiliado = nombreafiliado.replace("^", " ");
//                //////////busco el plan/////////////////////////////////////////////
//                i = result.indexOf("ZIN|");                
//                pipe = 0;
//                while (i < (result.indexOf("NTE|") - 1)) {
//                    System.out.println("char:"+result.charAt(i));
//                    if (pipe == 2) {
//                        plan = plan + result.charAt(i);
//                    }
//                    if (result.charAt(i) == '|') {
//                        pipe++;
//                    }
//                    i++;
//                }//GRAV^VOLUNTARIO ////NTE
//                System.out.println("plan:" + plan);
//                if (idObraSocialOnline == 107 || idObraSocialOnline == 4 ) {
//                    if (plan.equals("EXNT^OBLIGATORIO")) {
//                        JOptionPane.showMessageDialog(null, mensaje);
//                        this.dispose();
//                    } else {
//                        habilitado = "!OK";
//                        JOptionPane.showMessageDialog(null, "El paciente no posee plan OBLIGATORIO, debe ingresarlo en el código de la obra social 513");
//                        this.dispose();
//                    }
//                } else {//GRAV^VOLUNTARIO
//                    if (plan.equals("GRAV^VOLUNTARIO")) {
//                        JOptionPane.showMessageDialog(null, mensaje);
//                        this.dispose();
//                    } else {
//                        habilitado = "!OK";
//                        JOptionPane.showMessageDialog(null, "El paciente no posee plan VOLUNTARIO, debe ingresarlo en el código de la obra social 512");
//                        this.dispose();
//                    }
//                }
//
//            } else {
//       
//                if (codigo_respuesta.equals("M001") || codigo_respuesta.equals("M003")) {
//                    JOptionPane.showMessageDialog(null, "AFILIADO INEXISTENTE - Por Favor Verifique el Numero ingresado");
//                }
//                if (codigo_respuesta.equals("M004")) {
//                    JOptionPane.showMessageDialog(null, "-AFILIADO DADO DE BAJA-");
//                }
//                 if (codigo_respuesta.equals("M005")) {
//                    JOptionPane.showMessageDialog(null, "-AFILIADO MOROSO-");
//                }
//                 
//
//                habilitado = "!OK";
//            }
//            cursor2();
//        } catch (Exception ex) {
//            cursor2();
//            JOptionPane.showMessageDialog(null, ex);
//        }

        /*
        IN1|1|BRONCE|222222
ZIN|Y|GRAV^VOLUNTARIO
NTE|1||F.A.P.: 2021/4/26
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        mensaje = "";
//        habilitado = "";
//        nombreafiliado = "";
//        dni = "";
//        Codigo_afiliado = "";
//        plan = "";
//       
//        TripleDes tpDatos = new TripleDes();
//        Codigo_afiliado = txtafiliado.getText();
//        ////////////////////////////////////////////////////////////////////////////yyyyMMddHHMMss                  yyMMddHHMMssyyyyMMdd
//        String Mensaje = "MSH|^~\\&|TRIA0100M|TRIA00007526|MEDIFE|MEDIFE^222222^IIN|" + fechahora + "||ZQI^Z01^ZQI_Z01|" + codigo_seguridad + "|P|2.4|||NE|AL|ARG\r\n"
//                + "PRD|PS^Prestador Solicitante||^^^T||||30522483881^CU|\r\n"
//                + "PID|||" + Codigo_afiliado + "^^^MEDIFE^HC^MEDIFE||UNKNOWN";
//        String clave = "IA007526";
//        String usuario = "IA007526";
//        String tipo = "SI";
//        String llave = "1234567890123456ABCDEFGH";
//        String pszMsg = tpDatos.EncriptarStr(Mensaje, llave);
//        try { // Call Web Service Operation
//            WebServiceIA service = new WebServiceIA();
//            WebServiceIASoap port = service.getWebServiceIASoap();
//            // TODO initialize WS operation arguments here
//            String pszUser = tpDatos.EncriptarStr(usuario, llave);
//            String pszPwd = tpDatos.EncriptarStr(clave, llave);
//            String pszMsgType = tpDatos.EncriptarStr(tipo, llave);
//            // TODO process result here
//            String result = port.enviar(pszMsg, pszUser, pszPwd, pszMsgType);
//            System.out.println("Respuesta = " + result);
//            int i;
//            int pipe = 0, tilde = 0, acento = 0;
//            int bandera_dni = 0, bandera_afiliado = 0;
//            ///busco la respuesta
//            i = result.indexOf("ZAU");
//            pipe = 0;
//            while (i < result.indexOf("PRD")) {
//                if (pipe == 3) {
//                    mensaje = mensaje + result.charAt(i);
//                }
//                if (result.charAt(i) == '|') {
//                    pipe++;
//                }
//                i++;
//            }
//            String codigo_respuesta = mensaje.substring(0, 4);
//            mensaje = mensaje.replace("^", " ");
//            System.out.println(dni);
//            System.out.println(nombreafiliado);
//            System.out.println(plan);
//
//            System.out.println(codigo_respuesta);
//
//            if (codigo_respuesta.equals("B000") || codigo_respuesta.equals("B001")) {
//                habilitado = "OK";
//                //buscar dni, apellido y nombre del afiliado 
//                i = result.indexOf("PID");
//                pipe = 0;
//                while (i < result.indexOf("IN1")) {
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
//                        if (pipe == 5) {//busco apellido y nombre
//                            int j = i;
//                            while (j < result.indexOf("IN1")) {
//                                if (result.charAt(j) != '|') {
//                                    nombreafiliado = nombreafiliado + result.charAt(j);
//                                } else {
//                                    j = result.indexOf("IN1");
//                                    bandera_afiliado = 1;
//                                }
//                                j++;
//                            }
//                        }
//                    }
//                    if (result.charAt(i) == '|') {
//                        pipe++;
//                    }
//                    i++;
//                }
//                nombreafiliado = nombreafiliado.replace("^", " ");
//                //////////busco el plan/////////////////////////////////////////////
//                i = result.indexOf("ZIN|");                
//                pipe = 0;
//                while (i < (result.indexOf("NTE|") - 1)) {
//                    System.out.println("char:"+result.charAt(i));
//                    if (pipe == 2) {
//                        plan = plan + result.charAt(i);
//                    }
//                    if (result.charAt(i) == '|') {
//                        pipe++;
//                    }
//                    i++;
//                }//GRAV^VOLUNTARIO ////NTE
//                System.out.println("plan:" + plan);
//                if (idObraSocialOnline == 107 || idObraSocialOnline == 4 ) {
//                    if (plan.equals("EXNT^OBLIGATORIO")) {
//                        JOptionPane.showMessageDialog(null, mensaje);
//                        this.dispose();
//                    } else {
//                        habilitado = "!OK";
//                        JOptionPane.showMessageDialog(null, "El paciente no posee plan OBLIGATORIO, debe ingresarlo en el código de la obra social 513");
//                        this.dispose();
//                    }
//                } else {//GRAV^VOLUNTARIO
//                    if (plan.equals("GRAV^VOLUNTARIO")) {
//                        JOptionPane.showMessageDialog(null, mensaje);
//                        this.dispose();
//                    } else {
//                        habilitado = "!OK";
//                        JOptionPane.showMessageDialog(null, "El paciente no posee plan VOLUNTARIO, debe ingresarlo en el código de la obra social 512");
//                        this.dispose();
//                    }
//                }
//
//            } else {
//       
//                if (codigo_respuesta.equals("M001") || codigo_respuesta.equals("M003")) {
//                    JOptionPane.showMessageDialog(null, "AFILIADO INEXISTENTE - Por Favor Verifique el Numero ingresado");
//                }
//                if (codigo_respuesta.equals("M004")) {
//                    JOptionPane.showMessageDialog(null, "-AFILIADO DADO DE BAJA-");
//                }
//                 if (codigo_respuesta.equals("M005")) {
//                    JOptionPane.showMessageDialog(null, "-AFILIADO MOROSO-");
//                }
//                 
//
//                habilitado = "!OK";
//            }
//            cursor2();
//        } catch (Exception ex) {
//            cursor2();
//            JOptionPane.showMessageDialog(null, ex);
//        }

        /*
        IN1|1|BRONCE|222222
ZIN|Y|GRAV^VOLUNTARIO
NTE|1||F.A.P.: 2021/4/26
         */
    }//GEN-LAST:event_btnaceptarActionPerformed

    private void txtafiliadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtafiliadoActionPerformed
        txtafiliado.transferFocus();
    }//GEN-LAST:event_txtafiliadoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JFormattedTextField txtafiliado;
    // End of variables declaration//GEN-END:variables
}
