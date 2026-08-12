package Formularios;

import Clases.ConexionMariaDB;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class AfiliadoOsseg extends javax.swing.JDialog {

    public static String habilitado = "", tipoPlan = "";
    public static int[] idPlan = new int[500];
    public static int[] estadoCoseguro = new int[500];
    public static String[] plan = new String[500];
    public static String[] planMensaje = new String[500];
    public static int contadorIdPlan = 0, tipoCoseguro = 0;

    public AfiliadoOsseg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        traePlanes();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbotipo = new javax.swing.JComboBox();
        btnValidar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese el tipo de plan del afiliado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Plan:");

        cbotipo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbotipo.setForeground(new java.awt.Color(0, 102, 204));
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
                .addComponent(jLabel2)
                .addGap(25, 25, 25)
                .addComponent(cbotipo, 0, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbotipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnValidar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnValidar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728951 - electricity lightning.png"))); // NOI18N
        btnValidar.setText("Validar");
        btnValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidarActionPerformed(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(btnValidar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnValidar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void traePlanes() {
        ////////////////////////////////////////////////////////////////////////
        ConexionMariaDB mysql = new ConexionMariaDB();
        Connection cn = mysql.Conectar();
        String sSQL8 = "SELECT * from obrasocial_planes where id_obrasocial=2 and estado=1";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL8);
            while (rs.next()) {
                //System.out.println("plan"+rs.getString("plan") + " - " +rs.getString("observacion"));
                plan[contadorIdPlan] = rs.getString("plan");
                idPlan[contadorIdPlan] = rs.getInt("id");
                estadoCoseguro[contadorIdPlan] = rs.getInt("coseguro");
                planMensaje[contadorIdPlan] = rs.getString("observacion");
                cbotipo.addItem(plan[contadorIdPlan]);
                contadorIdPlan++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse al servidor");
            JOptionPane.showMessageDialog(null, e);
        }
        /////////////////////////////////////////////////////////////////////
    }


    private void btnValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarActionPerformed
        int i = 0;
        String tomoPlan = cbotipo.getSelectedItem().toString();
        int idplan = 0;
        String mensajeCoseguro = "";
        while (i < contadorIdPlan) {
            if (tomoPlan.equals(plan[i])) {
                idplan = idPlan[i];
                mensajeCoseguro = planMensaje[i];
                tipoCoseguro = estadoCoseguro[i];
                JOptionPane.showMessageDialog(null, "Los pacientes de este plan " + mensajeCoseguro);
                System.out.println("idplan: " + idplan);
                System.out.println("Plan: " + tomoPlan);
                System.out.println("mensajeCoseguro: " + mensajeCoseguro);
                System.out.println("tipoCoseguro: " + tipoCoseguro);
                habilitado = "OK";
                break;
            }
            i++;
        }
        dispose();

    }//GEN-LAST:event_btnValidarActionPerformed

    private void cbotipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotipoActionPerformed


    }//GEN-LAST:event_cbotipoActionPerformed

    private void cbotipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbotipoKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnValidar.requestFocus();
        }

    }//GEN-LAST:event_cbotipoKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnValidar;
    private javax.swing.JComboBox cbotipo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
