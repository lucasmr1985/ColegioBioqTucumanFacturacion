package Formularios;

import ClienteIPSST1.AfiliadoPuedeConsumirExecuteResponse;
import ClienteIPSST2.AfiliadoDatosTraerExecuteResponse;
import static Formularios.Login.matricula_colegiado;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class AfiliadoSubsidio extends javax.swing.JDialog {

    public static String habilitado = "", nombreafiliado = "", dni = "", Codigo_afiliado = "";

    public AfiliadoSubsidio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Subsidio de Salud");
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        this.setLocationRelativeTo(null);
    }

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

    void aplicarCambios() {
        habilitado = ""; nombreafiliado = "";  dni = "";  Codigo_afiliado = "";
        cursor();
        ///////////////////////////////////////////////////////////////////////////////////////////////
        ClienteIPSST1.AfiliadoPuedeConsumirExecute servicio = new ClienteIPSST1.AfiliadoPuedeConsumirExecute();
        servicio.setAficuil(txtCuil.getText());
        servicio.setUsuario(2);//
        servicio.setToken("iq12Ii35o");
        servicio.setPrestador(matricula_colegiado);
        AfiliadoPuedeConsumirExecuteResponse respuesta_execute = execute(servicio);

        if (respuesta_execute.getPuedeconsumir() == 1) {
            //JOptionPane.showMessageDialog(null, "Afiliado habilitado");

            ClienteIPSST2.AfiliadoDatosTraerExecute datos = new ClienteIPSST2.AfiliadoDatosTraerExecute();
            datos.setAficuil(txtCuil.getText());
            datos.setUsuario(2);//
            datos.setToken("iq12Ii35o");
            datos.setPrestador(matricula_colegiado);
            ClienteIPSST2.AfiliadoDatosTraerExecuteResponse respuesta = new ClienteIPSST2.AfiliadoDatosTraerExecuteResponse();
            respuesta = execute_1(datos);
            System.out.println("Rechazo:" + respuesta.getMotivorechazo());
            if (respuesta.getMotivorechazo().equals("")) {
                nombreafiliado = respuesta.getAfiliado().getSDTAfiliadoSDTAfiliadoItem().get(0).getAfiApellidoNombre();
                Codigo_afiliado = respuesta.getAfiliado().getSDTAfiliadoSDTAfiliadoItem().get(0).getAfiCUIIL();
                dni = respuesta.getAfiliado().getSDTAfiliadoSDTAfiliadoItem().get(0).getAfiDocumento();
                habilitado = "OK";
                this.dispose();
            } else {
                cursor2();
                JOptionPane.showMessageDialog(null, respuesta.getMotivorechazo());
            }
        } else {
            cursor2();
            habilitado = "ERROR";
            JOptionPane.showMessageDialog(null, respuesta_execute.getRazonnopuede());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        cboGenero = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCuil = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese el numero de afiliado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Cuil:");

        txtDni.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtDni.setForeground(new java.awt.Color(0, 102, 204));
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDniKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDniKeyReleased(evt);
            }
        });

        cboGenero.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cboGenero.setForeground(new java.awt.Color(0, 102, 204));
        cboGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino" }));
        cboGenero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboGeneroKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Género:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("DNI:");

        txtCuil.setForeground(new java.awt.Color(0, 102, 204));
        try {
            txtCuil.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-########-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCuil.setCaretColor(new java.awt.Color(0, 102, 102));
        txtCuil.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtCuil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboGenero, 0, 102, Short.MAX_VALUE)
                    .addComponent(txtDni)
                    .addComponent(txtCuil))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCuil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        aplicarCambios();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtDniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyReleased

        if (txtDni.getText().length() == 8) {
            int d1, d2, d3, d4, d5, d6, d7, d8, sumaMasc, dni, resto, resto23, digitoVerificador, digitoGenero;
            dni = Integer.valueOf(txtDni.getText());
            d8 = dni % 10;
            dni = dni / 10;
            d7 = dni % 10;
            dni = dni / 10;
            d6 = dni % 10;
            dni = dni / 10;
            d5 = dni % 10;
            dni = dni / 10;
            d4 = dni % 10;
            dni = dni / 10;
            d3 = dni % 10;
            dni = dni / 10;
            d2 = dni % 10;
            dni = dni / 10;
            d1 = dni % 10;
            if (cboGenero.getSelectedItem().toString().equals("Masculino")) {
                sumaMasc = (d8 * 2) + (d7 * 3) + (d6 * 4) + (d5 * 5) + (d4 * 6) + (d3 * 7) + (d2 * 2) + (d1 * 3) + 10;
                resto = sumaMasc % 11;
            } else {
                sumaMasc = (d8 * 2) + (d7 * 3) + (d6 * 4) + (d5 * 5) + (d4 * 6) + (d3 * 7) + (d2 * 2) + (d1 * 3) + 10;
                resto = (sumaMasc + 28) % 11;
            }

            if (resto == 0) {
                digitoVerificador = 0;
            } else {
                digitoVerificador = 11 - resto;
            }
            if (resto == 1) {
                digitoGenero = 23;
                resto23 = (sumaMasc + 12) % 11;
                if (resto23 == 0) {
                    digitoVerificador = 0;
                } else {
                    digitoVerificador = 11 - resto23;
                }
            } else if (cboGenero.getSelectedItem().toString().equals("Masculino")) {
                digitoGenero = 20;
            } else {
                digitoGenero = 27;
            }
            txtCuil.setText(digitoGenero + "-" + txtDni.getText() + "-" + digitoVerificador);
        }

    }//GEN-LAST:event_txtDniKeyReleased

    private void txtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCuil.requestFocus();
            txtCuil.selectAll();
        }

    }//GEN-LAST:event_txtDniKeyPressed

    private void txtCuilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuilActionPerformed

        aplicarCambios();

    }//GEN-LAST:event_txtCuilActionPerformed

    private void cboGeneroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboGeneroKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDni.requestFocus();
        }

    }//GEN-LAST:event_cboGeneroKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboGenero;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JFormattedTextField txtCuil;
    private javax.swing.JTextField txtDni;
    // End of variables declaration//GEN-END:variables

    private static AfiliadoPuedeConsumirExecuteResponse execute(ClienteIPSST1.AfiliadoPuedeConsumirExecute parameters) {
        ClienteIPSST1.AfiliadoPuedeConsumir service = new ClienteIPSST1.AfiliadoPuedeConsumir();
        ClienteIPSST1.AfiliadoPuedeConsumirSoapPort port = service.getAfiliadoPuedeConsumirSoapPort();
        return port.execute(parameters);
    }

    private static AfiliadoDatosTraerExecuteResponse execute_1(ClienteIPSST2.AfiliadoDatosTraerExecute parameters) {
        ClienteIPSST2.AfiliadoDatosTraer service = new ClienteIPSST2.AfiliadoDatosTraer();
        ClienteIPSST2.AfiliadoDatosTraerSoapPort port = service.getAfiliadoDatosTraerSoapPort();
        return port.execute(parameters);
    }

}
