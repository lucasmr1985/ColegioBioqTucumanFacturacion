package Formularios;

import Clases.ConexionMariaDB;
import java.awt.Cursor;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Resumen_OS extends javax.swing.JDialog {

    DefaultTableModel model;
    DefaultTableCellRenderer alinearCentro, alinearDerecha, alinearIzquierda;
    public static double coseguro;

    public Resumen_OS(java.awt.Frame parent, boolean modal) {

        super(parent, modal);
        initComponents();
        this.setTitle("Resumen de OS");
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        this.setLocationRelativeTo(null);
        cargardatostabla_resumen("");
        cargatotalesordenesfacturacion();
    }

    void cargardatostabla_resumen(String valor) {
        cursor();
        String[] Titulo = {"Cod O.S:", "Nombre de Obra Social", "Periodo", "", "Importe", "Cant. Prac.", "Cant. Ord."};
        String[] Registros = new String[7];

        String sql = "SELECT count(detalle_ordenes.cod_practica),count(DISTINCT(ordenes.id_orden)),sum(detalle_ordenes.precio_practica),obrasocial.id_obrasocial,obrasocial.razonsocial_obrasocial,obrasocial.codigo_obrasocial,obrasocial.importeunidaddegasto_obrasocial,obrasocial.importeunidaddearancel_obrasocial \n"
                + "FROM ordenes \n"
                + "INNER JOIN colegiados ON colegiados.id_colegiados=ordenes.id_colegiados \n"
                + "INNER JOIN detalle_ordenes ON detalle_ordenes.id_orden=ordenes.id_orden \n"
                + "INNER JOIN obrasocial ON obrasocial.id_obrasocial = ordenes.id_obrasocial \n"
                + "WHERE ordenes.periodo=" + MainL.periododjj + "\n"
                + " and colegiados.id_colegiados=" + Login.id_usuario + " AND detalle_ordenes.estado=0 AND ordenes.estado_orden!=0 group by(obrasocial.codigo_obrasocial) order by (obrasocial.int_codigo_obrasocial)";

        model = new DefaultTableModel(null, Titulo) {
            ////Celdas no editables////////
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        ConexionMariaDB cc = new ConexionMariaDB();
        Connection cn = cc.Conectar();
        coseguro = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Registros[0] = rs.getString("obrasocial.codigo_obrasocial");
                Registros[1] = rs.getString("obrasocial.razonsocial_obrasocial");
                Registros[2] = MainL.periododjj;
                Registros[3] = "";
                Registros[4] = rs.getString("sum(detalle_ordenes.precio_practica)");
                Registros[5] = rs.getString("count(detalle_ordenes.cod_practica)");
                Registros[6] = rs.getString("count(DISTINCT(ordenes.id_orden))");

                model.addRow(Registros);
            }
//            txtCoseguro.setText(coseguro+"");
            tabla_resumen_OS.setModel(model);
            tabla_resumen_OS.setAutoCreateRowSorter(true);
            /////
            alinear();
            tabla_resumen_OS.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
            tabla_resumen_OS.getColumnModel().getColumn(1).setCellRenderer(alinearIzquierda);
            tabla_resumen_OS.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
            tabla_resumen_OS.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
            tabla_resumen_OS.getColumnModel().getColumn(4).setCellRenderer(alinearDerecha);
            tabla_resumen_OS.getColumnModel().getColumn(5).setCellRenderer(alinearCentro);
            tabla_resumen_OS.getColumnModel().getColumn(6).setCellRenderer(alinearCentro);
            tabla_resumen_OS.getColumnModel().getColumn(0).setPreferredWidth(60);
            tabla_resumen_OS.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabla_resumen_OS.getColumnModel().getColumn(2).setPreferredWidth(60);
            tabla_resumen_OS.getColumnModel().getColumn(3).setPreferredWidth(60);
            tabla_resumen_OS.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabla_resumen_OS.getColumnModel().getColumn(5).setPreferredWidth(60);
            tabla_resumen_OS.getColumnModel().getColumn(6).setPreferredWidth(60);
            cn.close();
        } catch (SQLException ex) {
            cursor2();
            JOptionPane.showMessageDialog(null, ex);

        }
        cursor2();
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

    void alinear() {
        alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_resumen_OS = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnimprimir = new javax.swing.JButton();
        txtpracticas = new javax.swing.JTextField();
        txtordenes = new javax.swing.JTextField();
        txttotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Total Ordenes:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Total: $");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Total de Practicas:");

        jLabel15.setFont(new java.awt.Font("Bauhaus 93", 1, 60)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 204, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cbt2.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setText("Resumen de Obras Sociales ");

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

        txtpracticas.setEditable(false);
        txtpracticas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtpracticas.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtpracticas.setBorder(null);
        txtpracticas.setOpaque(false);

        txtordenes.setEditable(false);
        txtordenes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtordenes.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtordenes.setBorder(null);
        txtordenes.setOpaque(false);

        txttotal.setEditable(false);
        txttotal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txttotal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txttotal.setBorder(null);
        txttotal.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnimprimir)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtpracticas, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtordenes, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(184, 184, 184)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtpracticas)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(btnimprimir))
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addComponent(txttotal)
                    .addComponent(txtordenes, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    public void cargatotalesordenesfacturacion() {
        int totalRow = tabla_resumen_OS.getRowCount(), contador2 = 0, contador = 1;
        totalRow -= 1;
        int i;
        double contador3 = 0;
        for (i = 0; i <= (totalRow); i++) {
            contador3 = contador3 + Double.valueOf(tabla_resumen_OS.getValueAt(i, 4).toString());
            contador = contador + Integer.valueOf(tabla_resumen_OS.getValueAt(i, 5).toString());
            contador2 = contador2 + Integer.valueOf(tabla_resumen_OS.getValueAt(i, 6).toString());
        }//String[] Titulo = {"Cod O.S:", "Nombre de Obra Social", "Periodo", "U. Hnrs", "Importe", "Cant. Prac.", "Cant. Ord."};
        txtpracticas.setText((String.valueOf(contador)));
        txtordenes.setText((String.valueOf(contador2)));
        txttotal.setText((String.valueOf(contador3)));
    }

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        try {
            //Mensaje de encabezado
            MessageFormat encabezado = new MessageFormat("Resumen de Obra Sociales del periodo " + MainL.periododjj);
            //Mensaje en el pie de pagina
            MessageFormat pie = new MessageFormat("Total de Practicas:" + txtpracticas.getText() + "      Total Ordenes:" + txtordenes.getText() + "          Total: $" + txttotal.getText());
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_resumen_OS;
    private javax.swing.JTextField txtordenes;
    private javax.swing.JTextField txtpracticas;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
