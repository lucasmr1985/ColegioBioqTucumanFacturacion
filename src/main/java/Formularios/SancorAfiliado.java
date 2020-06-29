package Formularios;

import static Formularios.Login.cuit;
import java.awt.Cursor;
import javax.swing.JOptionPane;
import ClienteSancor.PAWESSAV2ELEGIBILIDADResponse;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class SancorAfiliado extends javax.swing.JDialog {

    public static String habilitado = "", nombreafiliado = "", dni = "", Codigo_afiliado = "";

    String idmsj = "", hora = "", fechaosde = "", pasaporte = "", mensaje = "", respuesta = "";

    public SancorAfiliado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Sancor");
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbotipo = new javax.swing.JComboBox();
        txtafiliado = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese el numero de afiliado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Numero:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Tipo:");

        cbotipo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbotipo.setForeground(new java.awt.Color(0, 102, 204));
        cbotipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ambulatorio", "Internación" }));
        cbotipo.setNextFocusableComponent(jButton1);
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

        txtafiliado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtafiliado.setForeground(new java.awt.Color(0, 102, 204));
        txtafiliado.setToolTipText("Ingresar los 9 numero sin la barra (XXXXXXXXX)");
        txtafiliado.setNextFocusableComponent(cbotipo);
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbotipo, 0, 117, Short.MAX_VALUE)
                    .addComponent(txtafiliado, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
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
                    .addComponent(jLabel3)
                    .addComponent(cbotipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
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
        String mensajepractica = "";
        //////////////////////////////////////////////////////////////        
        ClienteSancor.PAWESSAV2ELEGIBILIDAD servicio = new ClienteSancor.PAWESSAV2ELEGIBILIDAD();
        servicio.setModo("P");
        servicio.setEntidad(8999);
        long efector = Long.valueOf(cuit);
        servicio.setTiponroefector("CU");
        servicio.setNroefector(efector);
        servicio.setFormaidafiliado("AS");
        String numero_afiliado = txtafiliado.getText();
        if (numero_afiliado.length() > 9) {
            numero_afiliado = numero_afiliado.substring(8, 17);
        }
        System.out.println("numero_afiliado " + numero_afiliado);
        servicio.setAfiliado(Integer.valueOf(numero_afiliado));
        servicio.setUsuario("WSRVSSA");
        servicio.setClave("15WSSA08");
        mensajepractica = "<ext:PAWE_SSA_V2.ELEGIBILIDAD><ext:Modo>" + servicio.getModo() + "</ext:Modo><ext:Entidad>" + servicio.getEntidad() + "</ext:Entidad><ext:Tiponroefector>" + servicio.getTiponroefector() + "</ext:Tiponroefector><ext:Nroefector>" + servicio.getNroefector() + "</ext:Nroefector><ext:Formaidafiliado>" + servicio.getFormaidafiliado() + "</ext:Formaidafiliado><ext:Afiliado>" + servicio.getAfiliado() + "</ext:Afiliado><ext:Usuario>" + servicio.getUsuario() + "</ext:Usuario><ext:Clave>" + servicio.getClave() + "</ext:Clave></ext:PAWE_SSA_V2.ELEGIBILIDAD>";
        elegibilidad(servicio);
        System.out.println("Testing 1 - Send Http GET request");
        System.out.println(mensajepractica);        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Testing 2 - Get Http GET request");
        System.out.println(elegibilidad(servicio).getDescripcionrespuesta());
        if (elegibilidad(servicio).getCodigorespuesta()==0) {
            ///JOptionPane.showMessageDialog(null, "Afiliado habilitado");
            nombreafiliado = elegibilidad(servicio).getApellido()+" "+elegibilidad(servicio).getNombre();
            Codigo_afiliado = numero_afiliado;
            dni = String.valueOf(elegibilidad(servicio).getNrodocumento());
            habilitado = "OK";
            this.dispose();
        } else {
            habilitado = "ERROR";
            JOptionPane.showMessageDialog(null, elegibilidad(servicio).getDescripcionrespuesta());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void txtafiliadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtafiliadoKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtafiliado.transferFocus();
        }
    }//GEN-LAST:event_txtafiliadoKeyPressed

    /**
     * @par0o0pam args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbotipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtafiliado;
    // End of variables declaration//GEN-END:variables

    private static PAWESSAV2ELEGIBILIDADResponse elegibilidad(ClienteSancor.PAWESSAV2ELEGIBILIDAD parameters) {
        ClienteSancor.PAWESSAV2 service = new ClienteSancor.PAWESSAV2();
        ClienteSancor.PAWESSAV2SoapPort port = service.getPAWESSAV2SoapPort();
        return port.elegibilidad(parameters);
    }
}
