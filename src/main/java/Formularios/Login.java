package Formularios;

import Clases.ConexionMariaDB;
import static Clases.HiloInicio.estado_aviso;
import static Clases.HiloInicio.version;
import static Clases.HiloInicio.version_actual;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    public static String usuario;
    public static String contraseña;
    public static int contadorobrasocial = 0;
    public static int contadorafiliado = 0, datos = 0;
    public static int contadorpractica = 0;
    public static int id_usuario, periodo_colegiado;
    public static String nombre_usuario, sociedad;
    public static String contraseña_usuario, matricula_colegiado, nombre_colegiado, cuit;
    public String msj;
    public static boolean validacion_pami;
    boolean estadologin = false;

    public Login() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        this.setTitle("Login");
        this.setLocationRelativeTo(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnaceptar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtusuarios = new javax.swing.JTextField();
        txtcontraseña = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(null);
        setMinimumSize(null);
        setPreferredSize(new java.awt.Dimension(278, 200));
        setResizable(false);

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(153, 153, 153));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Contraseña:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Usuario:");

        txtusuarios.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtusuarios.setForeground(new java.awt.Color(0, 102, 204));
        txtusuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuariosActionPerformed(evt);
            }
        });

        txtcontraseña.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtcontraseña.setForeground(new java.awt.Color(0, 102, 204));
        txtcontraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcontraseñaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtusuarios)
                    .addComponent(txtcontraseña))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtusuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtcontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnaceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btncancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnaceptar)
                    .addComponent(btncancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        // Selecciono SQL la tabla empleados y todos sus atributos
        ConexionMariaDB mysql = new ConexionMariaDB();
        Connection cn = mysql.Conectar();
        int i = 0;
        String sSQL = "SELECT usuario_laboratorio,nombre_colegiado,cuil_colegiado,contraseña_laboratorio,periodos,id_colegiados,matricula_colegiado, estado_login,validacion_pami,actualizacion_datos,essociedad FROM colegiados WHERE usuario_laboratorio= '" + txtusuarios.getText() + "' and estado_colegiado='ACTIVOF'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            // Recorro y me fijo donde coinciden usuario y contraseña
            while (rs.next()) {
                if (txtusuarios.getText().equals(rs.getString("usuario_laboratorio"))) {
                    if (txtcontraseña.getText().equals(rs.getString("contraseña_laboratorio"))) {
                        id_usuario = rs.getInt("id_colegiados");
                        ///cargarversion();
                        if (version_actual.equals(version)) {
                            this.dispose();
                            i = 0;
                            // Cargo las variables para usarlos en el Main
                            //10
                            nombre_usuario = rs.getString("usuario_laboratorio");
                            contraseña_usuario = rs.getString("contraseña_laboratorio");
                            matricula_colegiado = rs.getString("matricula_colegiado");
                            nombre_colegiado = rs.getString("nombre_colegiado");
                            periodo_colegiado = rs.getInt("periodos");
                            cuit = rs.getString("cuil_colegiado");
                            estadologin = rs.getBoolean("estado_login");
                            validacion_pami = rs.getBoolean("validacion_pami");
                            sociedad = rs.getString("essociedad");
                            datos = rs.getInt(10);
                            if (datos == 1) {
                                if (estadologin == false) {
                                    new ConfirmaClave().setVisible(true);
                                } else {

                                    if (estado_aviso == 1) {
                                        new Aviso(this, true).setVisible(true);
                                        new MainL().setVisible(true);
                                    } else {
                                        new MainL().setVisible(true);
                                    }
                                }
                            }else{
                                 JOptionPane.showMessageDialog(null, "Usted no completó los datos de actualización requeridos, comunicarsé con el depto de computos, 4330508 opción 3");
                            }

                            break;
                        } else {
                            new Actualizacion(this, true).setVisible(true);
                        }
                    } else {
                        i = 2;
                    }
                } else {
                    i = 1;
                }
            }
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "Nombre de Usuario incorrecto...");
            }
            if (i == 2) {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta...");
            }
            cn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnaceptarActionPerformed

    void cargarversion() {
        ConexionMariaDB mysql = new ConexionMariaDB();
        Connection cn = mysql.Conectar();
        try {
            String sSQL2 = "UPDATE colegiados SET version_programa=? WHERE id_colegiados=" + id_usuario;
            PreparedStatement pst = cn.prepareStatement(sSQL2);
            pst.setInt(1, Integer.valueOf(172));
            pst.executeUpdate();
            cn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void txtusuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuariosActionPerformed
        txtusuarios.transferFocus();
    }//GEN-LAST:event_txtusuariosActionPerformed

    private void txtcontraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcontraseñaActionPerformed
        // Selecciono SQL la tabla empleados y todos sus atributos
        ConexionMariaDB mysql = new ConexionMariaDB();
        Connection cn = mysql.Conectar();
        int i = 0;
        String sSQL = "SELECT usuario_laboratorio,nombre_colegiado,cuil_colegiado,contraseña_laboratorio,periodos,id_colegiados,matricula_colegiado, estado_login,validacion_pami, actualizacion_datos ,essociedad FROM colegiados WHERE usuario_laboratorio= '" + txtusuarios.getText() + "' and estado_colegiado='ACTIVOF'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            // Recorro y me fijo donde coinciden usuario y contraseña
            while (rs.next()) {
                if (txtusuarios.getText().equals(rs.getString("usuario_laboratorio"))) {
                    if (txtcontraseña.getText().equals(rs.getString("contraseña_laboratorio"))) {
                        id_usuario = rs.getInt("id_colegiados");
                        //cargarversion();
                        if (version_actual.equals(version)) {
                            this.dispose();
                            i = 0;
                            // Cargo las variables para usarlos en el Main

                            nombre_usuario = rs.getString("usuario_laboratorio");
                            contraseña_usuario = rs.getString("contraseña_laboratorio");
                            matricula_colegiado = rs.getString("matricula_colegiado");
                            nombre_colegiado = rs.getString("nombre_colegiado");
                            periodo_colegiado = rs.getInt("periodos");
                            cuit = rs.getString("cuil_colegiado");
                            estadologin = rs.getBoolean("estado_login");
                            validacion_pami = rs.getBoolean("validacion_pami");
                            sociedad = rs.getString("essociedad");
                            datos = rs.getInt(10);
                            if (datos == 1) {
                                if (estadologin == false) {
                                    new ConfirmaClave().setVisible(true);
                                } else {

                                    if (estado_aviso == 1) {
                                        new Aviso(this, true).setVisible(true);
                                        new MainL().setVisible(true);
                                    } else {
                                        new MainL().setVisible(true);
                                    }

                                }
                            }else{
                                 JOptionPane.showMessageDialog(null, "Usted no completó los datos de actualización requeridos, comunicarsé con el depto de computos, 4330805 opción 3");
                            }
                            break;
                        } else {
                            new Actualizacion(this, true).setVisible(true);
                        }
                    } else {
                        i = 2;
                    }
                } else {
                    i = 1;
                }
            }
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "Nombre de Usuario incorrecto...");
            }
            if (i == 2) {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta...");
            }
            cn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtcontraseñaActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceptar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField txtcontraseña;
    private javax.swing.JTextField txtusuarios;
    // End of variables declaration//GEN-END:variables
}
