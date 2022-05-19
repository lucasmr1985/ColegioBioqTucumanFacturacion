package Formularios;

import Clases.Contraseña_Boreal;
import static Formularios.Login.cuit;
import static Formularios.Login.matricula_colegiado;
import java.awt.Cursor;
import javax.swing.JOptionPane;
import ClienteBoreal.WsBorealExecuteResponse;
import java.awt.event.KeyEvent;

public class BorealAfiliado extends javax.swing.JDialog {

    public static String habilitado = "", nombreafiliado = "", dni = "", Codigo_afiliado = "";
    public static int tipo_credencial=0;
    String idmsj = "", hora = "", fechaosde = "", pasaporte = "", mensaje = "", respuesta = "";
    
    public BorealAfiliado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Boreal");
        this.setLocationRelativeTo(null);
        this.setSize(260, 200);
        afiliadochek.setSelected(true);
        panelafiliado.setVisible(true);
        paneldni.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        panelafiliado = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtafiliado = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        paneldni = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtdni = new javax.swing.JFormattedTextField();
        afiliadochek = new javax.swing.JRadioButton();
        dnicheck = new javax.swing.JRadioButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelafiliado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese el numero de afiliado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Numero:");

        txtafiliado.setForeground(new java.awt.Color(0, 102, 204));
        try {
            txtafiliado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########/#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtafiliado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtafiliado.setNextFocusableComponent(jButton1);
        txtafiliado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtafiliadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelafiliadoLayout = new javax.swing.GroupLayout(panelafiliado);
        panelafiliado.setLayout(panelafiliadoLayout);
        panelafiliadoLayout.setHorizontalGroup(
            panelafiliadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelafiliadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelafiliadoLayout.setVerticalGroup(
            panelafiliadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelafiliadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelafiliadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728951 - electricity lightning.png"))); // NOI18N
        jButton1.setText("Validar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        paneldni.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese el numero de DNI", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Numero:");

        txtdni.setForeground(new java.awt.Color(0, 102, 204));
        try {
            txtdni.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtdni.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtdni.setNextFocusableComponent(jButton1);
        txtdni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdniActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneldniLayout = new javax.swing.GroupLayout(paneldni);
        paneldni.setLayout(paneldniLayout);
        paneldniLayout.setHorizontalGroup(
            paneldniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneldniLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paneldniLayout.setVerticalGroup(
            paneldniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneldniLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneldniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonGroup1.add(afiliadochek);
        afiliadochek.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        afiliadochek.setText("N° Afiliado");
        afiliadochek.setNextFocusableComponent(txtafiliado);
        afiliadochek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                afiliadochekActionPerformed(evt);
            }
        });
        afiliadochek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                afiliadochekKeyPressed(evt);
            }
        });

        buttonGroup1.add(dnicheck);
        dnicheck.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        dnicheck.setText("N° DNI");
        dnicheck.setNextFocusableComponent(txtdni);
        dnicheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dnicheckActionPerformed(evt);
            }
        });
        dnicheck.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dnicheckKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(afiliadochek)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dnicheck))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(paneldni, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelafiliado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(afiliadochek)
                    .addComponent(dnicheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(paneldni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cursor();
        String mensajepractica = "", respuestapractica;
        //////////////////////////////////////////////////////////////        
        String emisor = "CBT" + completarceros(matricula_colegiado, 9);
///            String clave = "2667773645730937";

        Contraseña_Boreal contraseña = new Contraseña_Boreal();
        String clave = contraseña.Boreal_Contraseña();
        /////////////////////////////////////////////////////////////////////////////////        
        if (afiliadochek.isSelected()) {
            mensajepractica = "<Boreal><Mensaje><Canal>ID</Canal><SitioEmisor>" + emisor + "</SitioEmisor><Receptor><Nombre>BOREAL</Nombre><ID>222023</ID><Tipo>IIN</Tipo></Receptor><MsgTipo><Tipo>ZQI</Tipo><Evento>Z01</Evento><Estructura>ZQI_Z01</Estructura></MsgTipo></Mensaje><Seguridad><Usuario>cobitucws</Usuario><Clave>" + clave + "</Clave></Seguridad><Prestador><PrestadorId>30522483881</PrestadorId><PrestadorTipoIdent>CU</PrestadorTipoIdent></Prestador><Afiliado><AfiliadoNroCredencial>" + txtafiliado.getText().substring(0, 8) + "</AfiliadoNroCredencial><AfiliadoGf>" + txtafiliado.getText().substring(9, 10) + "</AfiliadoGf><TipoIdentificador>HC</TipoIdentificador></Afiliado></Boreal>";
        }
        if (dnicheck.isSelected()) {
            mensajepractica = "<Boreal><Mensaje><Canal>ID</Canal><SitioEmisor>" + emisor + "</SitioEmisor><Receptor><Nombre>BOREAL</Nombre><ID>222023</ID><Tipo>IIN</Tipo></Receptor><MsgTipo><Tipo>ZQI</Tipo><Evento>Z01</Evento><Estructura>ZQI_Z01</Estructura></MsgTipo></Mensaje><Seguridad><Usuario>cobitucws</Usuario><Clave>" + clave + "</Clave></Seguridad><Prestador><PrestadorId>30522483881</PrestadorId><PrestadorTipoIdent>CU</PrestadorTipoIdent></Prestador><Afiliado><AfiliadoNroCredencial>" + txtdni.getText() + "</AfiliadoNroCredencial><AfiliadoGf></AfiliadoGf><TipoIdentificador>DU</TipoIdentificador></Afiliado></Boreal>";
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////            
        System.out.println("Testing 1 - Send Http GET request");
        System.out.println(mensajepractica);
        ///////////////////////////////////////////////////////////////////////////////////////////////
        ClienteBoreal.WsBorealExecute servicio = new ClienteBoreal.WsBorealExecute();
        servicio.setIngresoxml(mensajepractica);
        respuestapractica = execute(servicio).getEgresoxml();
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Testing 2 - Get Http GET request");
        System.out.println(respuestapractica);
        ////////////////////////////////////////////////////////////////////////////////////////////////
        int pos = respuestapractica.indexOf("<AutEstadoId>");
        int pos2 = respuestapractica.indexOf("</AutEstadoId>");
        /// JOptionPane.showMessageDialog(null, respuestapractica.substring(pos + 13, pos2));
        if (respuestapractica.substring(pos + 13, pos2).equals("B000")) {
          //  JOptionPane.showMessageDialog(null, "Afiliado habilitado");
            nombreafiliado = respuestapractica.substring(respuestapractica.indexOf("<AfiliadoNombre>") + 16, respuestapractica.indexOf("</AfiliadoNombre>"));
            if (afiliadochek.isSelected()) {
                Codigo_afiliado = txtafiliado.getText();
                dni = "11111111";
                tipo_credencial=0;
            }
            if (dnicheck.isSelected()) {
                Codigo_afiliado = txtdni.getText();
                dni = txtdni.getText();
                tipo_credencial=1;
            }
            habilitado = "OK";
            this.dispose();
        } else {
            habilitado = "ERROR";
            int pos3 = respuestapractica.indexOf("<AutObs>");
            int pos4 = respuestapractica.indexOf("</AutObs>");
            JOptionPane.showMessageDialog(null, respuestapractica.substring(pos3 + 8, pos4));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtafiliadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtafiliadoActionPerformed
        txtafiliado.transferFocus();
    }//GEN-LAST:event_txtafiliadoActionPerformed

    private void txtdniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdniActionPerformed
        txtdni.transferFocus();
    }//GEN-LAST:event_txtdniActionPerformed

    private void afiliadochekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_afiliadochekActionPerformed
        this.setSize(260, 200);
        panelafiliado.setVisible(true);
        paneldni.setVisible(false);
        afiliadochek.transferFocus();
    }//GEN-LAST:event_afiliadochekActionPerformed

    private void dnicheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dnicheckActionPerformed
        this.setSize(260, 200);
        panelafiliado.setVisible(false);
        paneldni.setVisible(true);
        dnicheck.transferFocus();
    }//GEN-LAST:event_dnicheckActionPerformed

    private void afiliadochekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_afiliadochekKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            afiliadochek.transferFocus();
        }
    }//GEN-LAST:event_afiliadochekKeyPressed

    private void dnicheckKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dnicheckKeyPressed
     if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
             dnicheck.transferFocus();
        }
    }//GEN-LAST:event_dnicheckKeyPressed
    private static WsBorealExecuteResponse execute(ClienteBoreal.WsBorealExecute parameters) {
        ClienteBoreal.WsBoreal service = new ClienteBoreal.WsBoreal();
        ClienteBoreal.WsBorealSoapPort port = service.getWsBorealSoapPort();
        return port.execute(parameters);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton afiliadochek;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton dnicheck;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel panelafiliado;
    private javax.swing.JPanel paneldni;
    private javax.swing.JFormattedTextField txtafiliado;
    private javax.swing.JFormattedTextField txtdni;
    // End of variables declaration//GEN-END:variables
}
