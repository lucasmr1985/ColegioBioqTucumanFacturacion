package Formularios;

import Clases.ConexionMariaDB;
import static Formularios.Login.id_usuario;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ConfirmaClave extends javax.swing.JFrame {

    public ConfirmaClave() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        this.setTitle("Contraseña");
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtusuarios = new javax.swing.JTextField();
        txtcontraseña = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        txtconfirma = new javax.swing.JPasswordField();
        jLabel_verifi = new javax.swing.JLabel();
        btncancelar = new javax.swing.JButton();
        btnaceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(153, 153, 153));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Contraseña:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Usuario:");

        txtusuarios.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtusuarios.setForeground(new java.awt.Color(0, 102, 255));
        txtusuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtusuariosKeyPressed(evt);
            }
        });

        txtcontraseña.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtcontraseña.setForeground(new java.awt.Color(0, 102, 255));
        txtcontraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcontraseñaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Confirmar Contraseña:");

        txtconfirma.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtconfirma.setForeground(new java.awt.Color(0, 102, 255));
        txtconfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtconfirmaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtconfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtusuarios)
                            .addComponent(txtcontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_verifi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_verifi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtusuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtcontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txtconfirma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );

        btncancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728935 - exit left logout.png"))); // NOI18N
        btncancelar.setMnemonic('l');
        btncancelar.setText("Cancelar");
        btncancelar.setToolTipText("[b]");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        btnaceptar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnaceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728934 - enter login right.png"))); // NOI18N
        btnaceptar.setMnemonic('l');
        btnaceptar.setText("Aceptar");
        btnaceptar.setToolTipText("[b]");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnaceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btncancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncancelar)
                    .addComponent(btnaceptar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        this.dispose();
        new MainL().setVisible(true);
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        ConexionMariaDB maria = new ConexionMariaDB();
        Connection cn = maria.Conectar();
        if (!txtcontraseña.getText().equals(txtconfirma.getText())) {
            JOptionPane.showMessageDialog(null, "La contraseñas no son iguales, ingreselas nuevamente..");
            txtconfirma.setText("");
            txtcontraseña.setText("");
            txtconfirma.requestFocus();
        } else {
            if (!txtcontraseña.getText().equals("") && !txtusuarios.getText().equals("")) {

                try {
                    String sSQL2 = "UPDATE colegiados SET contraseña_laboratorio=?,usuario_laboratorio=?,estado_login=? WHERE id_colegiados=" + id_usuario;
                    PreparedStatement pst = cn.prepareStatement(sSQL2);
                    pst.setString(1, txtcontraseña.getText());
                    pst.setString(2, txtusuarios.getText());
                    pst.setBoolean(3, true);
                    int n = pst.executeUpdate();
                    if (n > 0) {
                        JOptionPane.showMessageDialog(null, "La contraseña y el usuario se modificó con exito...");
                        this.dispose();
                        new MainL().setVisible(true);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No completó todos los datos...");
            }
        }
    }//GEN-LAST:event_btnaceptarActionPerformed

    private void txtconfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtconfirmaActionPerformed
        ConexionMariaDB maria = new ConexionMariaDB();
        Connection cn = maria.Conectar();
        if (!txtcontraseña.getText().equals(txtconfirma.getText())) {
            JOptionPane.showMessageDialog(null, "La contraseñas no son iguales, ingreselas nuevamente..");
            txtconfirma.setText("");
            txtcontraseña.setText("");
            txtconfirma.requestFocus();
        } else {
            if (!txtcontraseña.getText().equals("") && !txtusuarios.getText().equals("")) {
                try {

                    String sSQL2 = "UPDATE colegiados SET contraseña_laboratorio=?,usuario_laboratorio=?,estado_login=? WHERE id_colegiados=" + id_usuario;
                    PreparedStatement pst = cn.prepareStatement(sSQL2);
                    pst.setString(1, txtcontraseña.getText());
                    pst.setString(2, txtusuarios.getText());
                    pst.setBoolean(3, true);
                    int n = pst.executeUpdate();
                    if (n > 0) {
                        JOptionPane.showMessageDialog(null, "La contraseña y el usuario se modificó con exito...");
                        this.dispose();
                        new MainL().setVisible(true);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No completó todos los datos...");
            }
        }
    }//GEN-LAST:event_txtconfirmaActionPerformed

    private void txtcontraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcontraseñaActionPerformed
        txtcontraseña.transferFocus();
    }//GEN-LAST:event_txtcontraseñaActionPerformed

    private void txtusuariosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusuariosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Icon ok, error;
            ok = new ImageIcon(getClass().getResource("/Imagenes/checked.png"));
            error = new ImageIcon(getClass().getResource("/Imagenes/cancel.png"));
            ConexionMariaDB maria = new ConexionMariaDB();
            Connection cn = maria.Conectar();
            try {
                Statement St = cn.createStatement();
                ResultSet rs = St.executeQuery("Select usuario_laboratorio\n"
                        + "FROM colegiados\n"
                        + "where usuario_laboratorio='" + txtusuarios.getText() + "'");
                if (rs.next()) {
                    jLabel_verifi.setIcon(error);
                    ///setText("El Usuario ya existe");
                    txtusuarios.requestFocus();
                    txtcontraseña.enable(false);
                    txtconfirma.enable(false);
                } else {
                    jLabel_verifi.setIcon(ok);

                    txtcontraseña.enable(true);
                    txtconfirma.enable(true);
                    txtconfirma.enable(false);
                    txtusuarios.transferFocus();

                }
                cn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }//GEN-LAST:event_txtusuariosKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceptar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_verifi;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtconfirma;
    private javax.swing.JPasswordField txtcontraseña;
    private javax.swing.JTextField txtusuarios;
    // End of variables declaration//GEN-END:variables
}
