package Formularios;

import java.awt.Cursor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import ClienteSancorPro.HL7V24;
import ClienteSancorPro.HL7V24Service;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AfiliadoSancorHL7 extends javax.swing.JDialog {

    public static String habilitado = "", nombreafiliado = "", dni = "", Codigo_afiliado = "", numero_afiliado = "", plan = "", csc = "", numeroPreautorizacion = "";

    String idmsj = "", hora = "", fechahora = "", codigo_seguridad = "", fecha = "", mensaje = "", respuesta = "", token = "";

    public AfiliadoSancorHL7(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Sancor");
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
        btnaceptar = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese el número de Dni y Token", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Número:");

        txtafiliado.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtafiliado.setNextFocusableComponent(txtToken);
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
                    .addComponent(txtafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtToken, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jLabel3)
                    .addComponent(txtToken, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        apiSancorElegibilidad post = new apiSancorElegibilidad();
        mensaje="";
        Codigo_afiliado="";
        nombreafiliado="";
        numeroPreautorizacion="";
        numero_afiliado="";
        System.out.println("Send Http post request");
        try {
            if (txtafiliado.getText().length() > 0) {
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

    public class apiSancorElegibilidad {

        private final String USER_AGENT = "Mozilla/5.0";

        // HTTP post request (el autorizador de Sancor ya no requiere login/token)
        public void sendPost() throws Exception {
            cursor();

            String Mensaje = "MSH|^~\\&|SANCOR_SALUD|SANCOR_SALUD|SANCOR_SALUD|SANCOR_SALUD^604940^IIN|" + fechahora + "||ZQI^Z01^ZQI_Z01|" + codigo_seguridad + "|D|2.4|||NE|AL|ARG\r\n"
                    + "PRD|PS^Prestador Solicitante||^^^T||||30522483881^CU|\r\n"
                    //+ "PID|||"+Codigo_afiliado+"^^SANCOR_SALUD^HC||UNKNOW";
                    // + "PID|||"+Codigo_afiliado+"^^"+csc+"^SANCOR_SALUD^HC||UNKNOW";                        
                    //+ "PID|||"+Codigo_afiliado+"^^"+ csc + "^SANCOR_SALUD^HC^SANCOR_SALUD||";
                    //  + "PID|||0815867^00^^SANCOR_SALUD^HC||UNKNOW";//ale 31454672   
                    // + "PID|||0121297^00^^SANCOR_SALUD^HC||UNKNOW";//prueba
                    + "PID|||" + dni + "^^" + csc + "^SANCOR_SALUD^DU^SANCOR_SALUD||UNKNOWN";
            //+ "PID|||22606^^9999^SANCOR_SALUD^DU^SANCOR_SALUD||";
            try { // Call Web Service Operation
                HL7V24Service service = new HL7V24Service();
                HL7V24 port = service.getHL7V24Port();
                String result = port.message(8, Mensaje);
                System.out.println("Envío= "+Mensaje);
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
                //String codigo_respuesta = mensaje.substring(0, 2);
                String codigo_respuesta = mensaje.substring(0, 4);
                mensaje = mensaje.replace("^", " ");

                System.out.println(codigo_respuesta);
                System.out.println(mensaje);
                //if (codigo_respuesta.equals("-1")) {
                if (codigo_respuesta.equals("B000") || codigo_respuesta.equals("B001")) {
                    habilitado = "OK";
                    //buscar dni, apellido y nombre del afiliado 
                    i = result.indexOf("PID");
                    pipe = 0;
                    while (i < result.indexOf("IN1")) {
//                            if (bandera_dni == 0) {
//                                if (pipe == 2) {//busco el dni
//                                    int j = i;
//                                    while (j < result.indexOf("IN1")) {
//                                        if (result.charAt(j) == '^') {
//                                            j = result.indexOf("IN1");
//                                            bandera_dni = 1;
//                                        } else {
//                                            dni = dni + result.charAt(j);
//                                        }
//                                        j++;
//                                    }
//                                }
//                            }
                        if (bandera_afiliado == 0) {
                            if (pipe == 5) {//busco apellido y nombre
                                int j = i;
                                while (j < result.indexOf("IN1")) {
                                    if (result.charAt(j) != '|') {
                                        nombreafiliado = nombreafiliado + result.charAt(j);
                                    } else {
                                        j = result.indexOf("IN1");
                                        bandera_afiliado = 1;
                                    }
                                    j++;
                                }
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
                    }
                    nombreafiliado = nombreafiliado.replace("^", " ");
                    //////////busco el plan/////////////////////////////////////////////
                    i = result.indexOf("IN1|");
                    pipe = 0;
                    if (result.indexOf("IN1|") > 0) {
                        while (i < (result.indexOf("ZIN|") - 1)) {
                            if (pipe == 2) {
                                plan = plan + result.charAt(i);
                            }
                            if (result.charAt(i) == '|') {
                                pipe++;
                            }
                            i++;
                        }
                    }
                    plan = plan.replace("|", "");
                    System.out.println("plan:" + plan);
                    System.out.println("nombreafiliado: " + nombreafiliado);
                    System.out.println("dni:" + dni);
                    numero_afiliado = Codigo_afiliado;
                    numero_afiliado = numero_afiliado.replace("^", "");
                    System.out.println("numero_afiliado:" + numero_afiliado);
                    System.out.println("Codigo_afiliado:" + Codigo_afiliado);
                    JOptionPane.showMessageDialog(null, mensaje);
                    int opcion = JOptionPane.showOptionDialog(null, "Posee una orden PreAutorizada?", "Sancor PreAutorización", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                            new Object[]{"Si", "No", "Cancelar"}, "No"); // dinde quieres que se posicione el selector

                    if (opcion == 0) {
                        new SancorPreAutorizacion(null, true).setVisible(true);
                        dispose();
                    } else {
                        dispose();
                    }
                    dispose();
                } else {
                    habilitado = "!OK";
                    JOptionPane.showMessageDialog(null, mensaje);
                }
                cursor2();
            } catch (Exception ex) {
                cursor2();
                JOptionPane.showMessageDialog(null, ex);
                System.out.println("error sancor: " + ex);
            }

        }
    }

    private void txtafiliadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtafiliadoActionPerformed
        txtafiliado.transferFocus();
    }//GEN-LAST:event_txtafiliadoActionPerformed

    private void txtTokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTokenActionPerformed
        txtToken.transferFocus();
    }//GEN-LAST:event_txtTokenActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtToken;
    private javax.swing.JTextField txtafiliado;
    // End of variables declaration//GEN-END:variables
}
