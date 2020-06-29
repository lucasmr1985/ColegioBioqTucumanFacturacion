package Formularios;

import Clases.ConexionMariaDB;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Resumen_Validadores extends javax.swing.JDialog {

    DefaultTableModel model;

    public Resumen_Validadores(java.awt.Frame parent, boolean modal) {

        super(parent, modal);
        initComponents();
        this.setTitle("Resumen de PAMI");
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        this.setLocationRelativeTo(null);
        cargardatostabla_resumen("");
    }

    void cargardatostabla_resumen(String valor) {

        String[] Titulo = {"Matricula", "Bioquimico", "Periodo", "Practicas", "Ordenes", "Practicas Internado", "Ordenes Internado", "Arancel", "Total", "Importe",};
        String[] Registros = new String[10];
        int Practicas_internados = 0, ordenes_internados = 0, ordenes_practicas = 0, Total = 0, bandera = 1;
        model = new DefaultTableModel(null, Titulo) {

            ////Celdas no editables////////
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String sql1 = "SELECT  arancel From arancel_validadores where estado=0 and descripcion='VALIDACION'";
        ConexionMariaDB cc = new ConexionMariaDB();
        Connection cn = cc.Conectar();

        try {
            Statement st1 = cn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);
            rs1.next();

            String sql = "SELECT  colegiados.matricula_colegiado,colegiados.nombre_colegiado,count(detalle_ordenes.cod_practica),count(DISTINCT(ordenes.id_orden)),count(DISTINCT(ordenes.id_orden))*" + rs1.getString("arancel") + "\n"
                    + " ,obrasocial.razonsocial_obrasocial,obrasocial.codigo_obrasocial,obrasocial.importeunidaddearancel_obrasocial \n"
                    + "FROM ordenes\n"
                    + "INNER JOIN colegiados ON colegiados.id_colegiados=ordenes.id_colegiados\n"
                    + "INNER JOIN detalle_ordenes ON detalle_ordenes.id_orden=ordenes.id_orden\n"
                    + "INNER JOIN obrasocial ON obrasocial.id_obrasocial = ordenes.id_obrasocial \n"
                    + "WHERE ordenes.periodo=" + MainL.periododjj + "\n"
                    + "and detalle_ordenes.estado=0 and\n"
                    + "ordenes.id_colegiados=" + Login.id_usuario + "\n"
                    + "group by(colegiados.matricula_colegiado) order by(CAST(colegiados.matricula_colegiado as UNSIGNED)),obrasocial.int_codigo_obrasocial";

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            String sql2 = "SELECT  colegiados.matricula_colegiado,colegiados.nombre_colegiado,count(detalle_ordenes.cod_practica),count(DISTINCT(ordenes.id_orden)),\n"
                    + "round(count(detalle_ordenes.cod_practica)/4)\n"
                    + "FROM ordenes\n"
                    + "INNER JOIN colegiados ON colegiados.id_colegiados=ordenes.id_colegiados\n"
                    + "INNER JOIN detalle_ordenes ON detalle_ordenes.id_orden=ordenes.id_orden\n"
                    + "INNER JOIN obrasocial ON obrasocial.id_obrasocial = ordenes.id_obrasocial \n"
                    + "WHERE ordenes.periodo=" + MainL.periododjj + "\n"
                    + " and detalle_ordenes.estado=0 and\n"
                    + "ordenes.id_colegiados=" + Login.id_usuario + " and SUBSTRING(ordenes.numero_orden,1,1)='I'\n"
                    + "group by(colegiados.matricula_colegiado) order by(CAST(colegiados.matricula_colegiado as UNSIGNED))";

            Statement st2 = cn.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);

            if (!rs2.next()) {
                bandera = 0;
            }
            while (rs.next()) {
                if (bandera == 1) {
                    if (rs2.getString("colegiados.matricula_colegiado").equals(rs.getString("colegiados.matricula_colegiado"))) {

                        Practicas_internados = rs2.getInt("count(detalle_ordenes.cod_practica)");
                        ordenes_internados = rs2.getInt("count(DISTINCT(ordenes.id_orden))");
                        ordenes_practicas = rs2.getInt("round(count(detalle_ordenes.cod_practica)/4)");
                        Total = ordenes_practicas - (ordenes_internados) + rs.getInt("count(DISTINCT(ordenes.id_orden))");

                        if (rs2.isLast()) {
                            bandera = 0;
                        } else {
                            rs2.next();
                        }
                    }
                }

                Registros[0] = rs.getString("colegiados.matricula_colegiado");
                Registros[1] = rs.getString("colegiados.nombre_colegiado");
                Registros[2] = MainL.periododjj;
                Registros[3] = rs.getString("count(detalle_ordenes.cod_practica)");
                Registros[4] = rs.getString("count(DISTINCT(ordenes.id_orden))");
                Registros[5] = String.valueOf(Practicas_internados);
                Registros[6] = String.valueOf(ordenes_internados);
                Registros[7] = rs.getString("obrasocial.importeunidaddearancel_obrasocial");
                if (Total == 0) {
                    Registros[8] = rs.getString("count(DISTINCT(ordenes.id_orden))");
                    Registros[9] = rs.getString(5);
                } else {
                    //JOptionPane.showMessageDialog(null, rs1.getDouble("arancel"));
                    Registros[8] = String.valueOf(Total);
                    Registros[9] = String.valueOf(Total * rs1.getDouble("arancel"));

                }

                Practicas_internados = 0;
                ordenes_internados = 0;
                ordenes_practicas = 0;
                Total = 0;
               
                model.addRow(Registros);
            }
            tabla_resumen_OS.setModel(model);
            tabla_resumen_OS.setAutoCreateRowSorter(true);
            cargatotalesordenesfacturacion();
        } catch (SQLException ex) {
            Logger.getLogger(Resumen_Validadores.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargatotalesordenesfacturacion() {
        int totalRow = tabla_resumen_OS.getRowCount(), contador2 = 0, contador = 1;
        totalRow -= 1;
        int i;
        double contador3 = 0;
        for (i = 0; i <= (totalRow); i++) {
            contador2 = contador2 + Integer.valueOf(tabla_resumen_OS.getValueAt(i, 8).toString());
            contador3 = contador3 + Double.valueOf(tabla_resumen_OS.getValueAt(i, 9).toString());
        }
        txtpracticas.setText((String.valueOf(i)));
        txtordenes.setText((String.valueOf(contador2)));
        txttotal.setText((String.valueOf(contador3)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_resumen_OS = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        txtpracticas = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtordenes = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        btnimprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen"));
        jPanel2.setForeground(new java.awt.Color(102, 102, 102));

        tabla_resumen_OS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tabla_resumen_OS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_resumen_OS.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tabla_resumen_OS);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Resumen de Validacion - PAMI ");

        jLabel15.setFont(new java.awt.Font("Bauhaus 93", 1, 60)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 204, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cbt2.png"))); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728935 - exit left logout.png"))); // NOI18N
        jButton1.setMnemonic('s');
        jButton1.setText("Salir");
        jButton1.setToolTipText("Alt + s");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Total de Bioquimicos:");

        txtpracticas.setEditable(false);
        txtpracticas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtpracticas.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtpracticas.setBorder(null);
        txtpracticas.setOpaque(false);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Total Ordenes:");

        txtordenes.setEditable(false);
        txtordenes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtordenes.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtordenes.setBorder(null);
        txtordenes.setOpaque(false);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Total: $");

        txttotal.setEditable(false);
        txttotal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txttotal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txttotal.setBorder(null);
        txttotal.setOpaque(false);

        btnimprimir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728959 - announcement flyer news newspaper .png"))); // NOI18N
        btnimprimir.setMnemonic('i');
        btnimprimir.setText("Imprimir");
        btnimprimir.setToolTipText("Alt + i");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnimprimir)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtpracticas, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtordenes, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnimprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtordenes)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtpracticas)
                    .addComponent(txttotal))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        try {
            //Mensaje de encabezado
            MessageFormat encabezado = new MessageFormat("Resumen de Validacion - PAMI " + MainL.periododjj);
            //Mensaje en el pie de pagina
            MessageFormat pie = new MessageFormat("Total de Bioquimicos:" + txtpracticas.getText() + "      Total Ordenes:" + txtordenes.getText() + "          Total: $" + txttotal.getText());
            //Imprimir JTable
            tabla_resumen_OS.print(JTable.PrintMode.FIT_WIDTH, encabezado, pie);
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btnimprimirActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnimprimir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_resumen_OS;
    private javax.swing.JTextField txtordenes;
    private javax.swing.JTextField txtpracticas;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
