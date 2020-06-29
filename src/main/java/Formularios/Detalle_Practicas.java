package Formularios;

import Clases.ConexionMariaDB;
import Clases.ConexionMariaDBBackup;
import static Clases.Escape.funcionescape;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Detalle_Practicas extends javax.swing.JDialog {

    DefaultTableModel model;
    public static String id_orden, afiliado, obrasocial, fecha, total, num_orden, dni, observacion, cod_obra_soc;
    Double coseguro = 0.00;
    HiloOrdenes hilo;
    DefaultTableCellRenderer alinearCentro, alinearDerecha, alinearIzquierda;

    public Detalle_Practicas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        this.setTitle("Detalle de prácticas");
        this.setLocationRelativeTo(null);
        ///  cargartabla(id_orden);
        txtorden.setText(num_orden);
        txtafiliado.setText(afiliado);
        txtobra.setText(obrasocial);
        txtfecha.setText(fecha);
        ///txttotal.setText(total);
        txtdni.setText(dni);
        txtobservaciones.setText("Observaciones: " + observacion);
        // detalle();
        hilo = new HiloOrdenes(null);
        hilo.start();
        hilo = null;
        funcionescape(this);
        this.setResizable(false);
    }

    void cargartotales() {
        double total1 = 0.00, desc, sumatoria;

        //AQUI SE SUMAN LOS VALORES DE LA COLUMNAS
        int totalRow = tablapracticas.getRowCount();
        totalRow -= 1;
        for (int i = 0; i <= (totalRow); i++) {
            String x = tablapracticas.getValueAt(i, 2).toString();
            sumatoria = Double.valueOf(x).doubleValue();
            total1 = total1 + sumatoria;
        }

        txttotal.setText(String.valueOf(Redondear(total1)));
    }

    public double Redondear(double numero) {
        return Math.rint(numero * 100) / 100;
    }

    public class HiloOrdenes extends Thread {

        JProgressBar progreso;

        public HiloOrdenes(JProgressBar progreso1) {
            super();
            this.progreso = progreso1;
        }

        public void run() {
            cursor();
            String[] Titulo = {"Cod", "Practica", "Precio", "Coseguro"};
            String[] Registros = new String[4];

            String sql = "SELECT cod_practica,nombre_practica,precio_practica, CAST(coseguro as DECIMAL(9,2)) FROM detalle_ordenes Where id_orden=" + id_orden + " AND estado=" + 0;
            model = new DefaultTableModel(null, Titulo) {
                ////Celdas no editables////////
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            if (Integer.valueOf(MainL.periodo) >= 201711) {
                ConexionMariaDB maria = new ConexionMariaDB();
                Connection cn = maria.Conectar();
                try {
                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sql);

                    while (rs.next()) {
                        Registros[0] = rs.getString(1);
                        Registros[1] = rs.getString(2);
                        Registros[2] = rs.getString(3);
                        Registros[3] = rs.getString(4);
                        model.addRow(Registros);
                        coseguro = rs.getDouble(4) + coseguro;
                    }
                    txtcoseguro.setText(String.valueOf(coseguro));
                    tablapracticas.setModel(model);
                    //  tablapracticas.setAutoCreateRowSorter(true);
                    /////ajustar ancho de columna///////
                    tablapracticas.getColumnModel().getColumn(0).setPreferredWidth(50);
                    tablapracticas.getColumnModel().getColumn(1).setPreferredWidth(100);
                    tablapracticas.getColumnModel().getColumn(2).setPreferredWidth(30);
                    tablapracticas.getColumnModel().getColumn(3).setPreferredWidth(30);

                    ///////////////////////////////////////////////////////////////////
                    alinear();
                    tablapracticas.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
                    tablapracticas.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
                    tablapracticas.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
                    tablapracticas.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
                    //////////////////////////////////////////////////////////////////
                    cursor2();
                    cn.close();
                    cargartotales();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            } else {
                ConexionMariaDBBackup cc = new ConexionMariaDBBackup();
                Connection cn2 = cc.Conectar();
                try {
                    Statement st = cn2.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while (rs.next()) {
                        Registros[0] = rs.getString(1);
                        Registros[1] = rs.getString(2);
                        Registros[2] = rs.getString(3);
                        Registros[3] = rs.getString(4);
                        model.addRow(Registros);

                    }
                    tablapracticas.setModel(model);
                    //  tablapracticas.setAutoCreateRowSorter(true);
                    /////ajustar ancho de columna///////
                    tablapracticas.getColumnModel().getColumn(0).setPreferredWidth(50);
                    tablapracticas.getColumnModel().getColumn(1).setPreferredWidth(100);
                    tablapracticas.getColumnModel().getColumn(2).setPreferredWidth(30);
                    tablapracticas.getColumnModel().getColumn(3).setPreferredWidth(30);

                    ///////////////////////////////////////////////////////////////////
                    alinear();
                    tablapracticas.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
                    tablapracticas.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
                    tablapracticas.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
                    tablapracticas.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
                    //////////////////////////////////////////////////////////////////

                    cursor2();
                    cn2.close();
                    cargartotales();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }

        }

        public void pausa(int mlSeg) {
            try {
                // pausa para el splash
                Thread.sleep(mlSeg);
            } catch (Exception e) {
            }

        }

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

    void cargartabla(String valor) {
        String[] Titulo = {"Cod", "Practica", "Precio"};
        String[] Registros = new String[3];
        //String sql = "SELECT * FROM detalle_ordenes Where id_orden=" + valor + " AND estado=" + 0;
        String sql = "SELECT id_orden,cod_practica,nombre_practica,precio_practica FROM detalle_ordenes WHERE id_orden=" + valor + " AND estado=" + 0;
        model = new DefaultTableModel(null, Titulo) {
            ////Celdas no editables////////
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        ConexionMariaDB maria = new ConexionMariaDB();
        Connection cn = maria.Conectar();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                if (valor.equals(rs.getString("id_orden"))) {
                    Registros[0] = rs.getString("cod_practica");
                    Registros[1] = rs.getString("nombre_practica");
                    Registros[2] = rs.getString("precio_practica");
                    model.addRow(Registros);
                }
            }
            tablapracticas.setModel(model);
            //  tablapracticas.setAutoCreateRowSorter(true);
            /////ajustar ancho de columna///////
            tablapracticas.getColumnModel().getColumn(0).setPreferredWidth(50);
            tablapracticas.getColumnModel().getColumn(1).setPreferredWidth(100);
            tablapracticas.getColumnModel().getColumn(2).setPreferredWidth(50);

            /////////////////////////////////////////////////////////////*/
            alinear();
            tablapracticas.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
            tablapracticas.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
            tablapracticas.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
            //////////////////////////////////////////////////////////////////
            cn.close();
            cargartotales();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
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
        tablapracticas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtorden = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtafiliado = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtobra = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtobservaciones = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtcoseguro = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Practicas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N
        jPanel1.setToolTipText("");

        tablapracticas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablapracticas);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("N° de Orden: ");

        txtorden.setEditable(false);
        txtorden.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtorden.setForeground(new java.awt.Color(0, 51, 102));
        txtorden.setBorder(null);
        txtorden.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Afiliado:");

        txtafiliado.setEditable(false);
        txtafiliado.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtafiliado.setForeground(new java.awt.Color(0, 51, 102));
        txtafiliado.setBorder(null);
        txtafiliado.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Total:");

        txttotal.setEditable(false);
        txttotal.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txttotal.setForeground(new java.awt.Color(0, 51, 102));
        txttotal.setBorder(null);
        txttotal.setOpaque(false);
        txttotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttotalActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("$");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Fecha:");

        txtfecha.setEditable(false);
        txtfecha.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtfecha.setForeground(new java.awt.Color(0, 51, 102));
        txtfecha.setBorder(null);
        txtfecha.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Obra Social:");

        txtobra.setEditable(false);
        txtobra.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtobra.setForeground(new java.awt.Color(0, 51, 102));
        txtobra.setBorder(null);
        txtobra.setOpaque(false);

        txtobservaciones.setEditable(false);
        txtobservaciones.setColumns(20);
        txtobservaciones.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtobservaciones.setRows(5);
        jScrollPane2.setViewportView(txtobservaciones);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Documento:");

        txtdni.setEditable(false);
        txtdni.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtdni.setForeground(new java.awt.Color(0, 51, 102));
        txtdni.setBorder(null);
        txtdni.setOpaque(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Coseguro");

        txtcoseguro.setEditable(false);
        txtcoseguro.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtcoseguro.setForeground(new java.awt.Color(0, 51, 102));
        txtcoseguro.setBorder(null);
        txtcoseguro.setOpaque(false);
        txtcoseguro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcoseguroActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("$");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcoseguro, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtobra))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtorden, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtobra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtorden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel9)
                        .addComponent(txtcoseguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txttotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalActionPerformed

    private void txtcoseguroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcoseguroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcoseguroActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablapracticas;
    private javax.swing.JTextField txtafiliado;
    private javax.swing.JTextField txtcoseguro;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtobra;
    private javax.swing.JTextArea txtobservaciones;
    private javax.swing.JTextField txtorden;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
