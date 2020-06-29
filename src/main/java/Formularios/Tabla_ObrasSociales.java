package Formularios;

import Clases.ConexionMariaDB;
import static Clases.Escape.funcionescape;
import static Clases.HiloInicio.contadorobrasocial;
import static Clases.HiloInicio.obrasocial;
import Clases.solomayusculas;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Tabla_ObrasSociales extends javax.swing.JFrame {

    int id_obrasocial;
    DefaultTableModel model;
    DefaultTableModel model2;
    HiloObra hilo;
    DefaultTableCellRenderer alinearCentro, alinearDerecha, alinearIzquierda;

    public Tabla_ObrasSociales(java.awt.Frame parent, boolean modal) {
     
        initComponents();
        this.setTitle("Obras Sociales");
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargartabla("");
        txtobrasocial.setDocument(new solomayusculas());
         }

    void cargarobrasocial() {
        TextAutoCompleter textAutoAcompleter = new TextAutoCompleter(txtobrasocial);

        int i = 0;

        // Recorro y cargo las obras sociales
        while (i < contadorobrasocial) {
            textAutoAcompleter.addItem(obrasocial[i]);

            i++;
        }
        //textAutoAcompleter.setMode(-1); // prefijo, viene por defecto
        textAutoAcompleter.setMode(0); // infijo     

        // textAutoAcompleter.setMode(1); // sufijo
        //textAutoAcompleter.setCaseSensitive(true); // Sensible a mayúsculas
        textAutoAcompleter.setCaseSensitive(false); //No sensible a mayúsculas        

    }

    void alinear() {
        alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
    }

    void cargartabla(String valor) {
        String[] Titulo = {"Codigo", "Codigo Facturación", "Nombre", "Arancel", "Año NBU"};
        String[] Registros = new String[5];
        String sql = "SELECT LPAD(codigo_obrasocial,5,' ') as codigo_obrasocial,LPAD(codigofacturacion_obrasocial,5,' ') as codigofacturacion_obrasocial, razonsocial_obrasocial,"
                + " round(importeunidaddearancel_obrasocial,2), nbu.añonbu FROM obrasocial Inner join nbu on nbu.id_nbu=obrasocial.añonbu  where estado_obrasocial=1 order by codigo_obrasocial ";
        model = new DefaultTableModel(null, Titulo) {
            ////Celdas no editables////////
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        ConexionMariaDB cc = new ConexionMariaDB();
        Connection cn = cc.Conectar();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Registros[0] = rs.getString("codigo_obrasocial");
                Registros[1] = rs.getString("codigofacturacion_obrasocial");
                Registros[2] = rs.getString("razonsocial_obrasocial");
                Registros[3] = rs.getString("round(importeunidaddearancel_obrasocial,2)");
                Registros[4] = rs.getString("añonbu");
                model.addRow(Registros);
            }
            obras.setModel(model);
            obras.setAutoCreateRowSorter(true);            
            alinear();
            obras.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
            obras.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
            obras.getColumnModel().getColumn(2).setCellRenderer(alinearIzquierda);
            obras.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
            obras.getColumnModel().getColumn(4).setCellRenderer(alinearCentro);
            
            obras.getColumnModel().getColumn(2).setPreferredWidth(200);
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        obras = new javax.swing.JTable();
        progreso = new javax.swing.JProgressBar();
        txtobrasocial = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnimprimir = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Obras Sociales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        obras.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        obras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        obras.setOpaque(false);
        obras.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        obras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                obrasKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(obras);

        progreso.setFont(new java.awt.Font("Tahoma", 0, 6)); // NOI18N
        progreso.setForeground(new java.awt.Color(100, 100, 100));
        progreso.setMaximum(1300);
        progreso.setString("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                    .addComponent(progreso, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(progreso, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtobrasocial.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtobrasocial.setForeground(new java.awt.Color(0, 102, 204));
        txtobrasocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtobrasocialActionPerformed(evt);
            }
        });
        txtobrasocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtobrasocialKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Obras Sociales:");

        btnimprimir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728959 - announcement flyer news newspaper .png"))); // NOI18N
        btnimprimir.setMnemonic('i');
        btnimprimir.setText("Imprimir");
        btnimprimir.setToolTipText("[Alt + i]");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        btncancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728935 - exit left logout.png"))); // NOI18N
        btncancelar.setMnemonic('s');
        btncancelar.setText("Salir");
        btncancelar.setToolTipText("[Alt + s]");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtobrasocial, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnimprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtobrasocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnimprimir)
                    .addComponent(btncancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void obrasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_obrasKeyPressed
        DefaultTableModel temp = (DefaultTableModel) obras.getModel();
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            obras.transferFocus();
            evt.consume();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (obras.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "No seleccionó ninguna fila...");
            } else {
                txtobrasocial.requestFocus();
            }
        }
    }//GEN-LAST:event_obrasKeyPressed

    public class HiloObra extends Thread {

        JProgressBar progreso;

        public HiloObra(JProgressBar progreso1) {
            super();
            this.progreso = progreso1;
        }

        public void run() {

            String[] titulos = {"Codigo", "Codigo Facturación", "Nombre", "Arancel", "Año NBU"};
            String[] datos = new String[5];
            model2 = new DefaultTableModel(null, titulos) {
                ////Celdas no editables////////
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            ConexionMariaDB cc = new ConexionMariaDB();
            Connection cn = cc.Conectar();
            try {
                Statement St = cn.createStatement();
                ResultSet Rs = St.executeQuery("SELECT obrasocial.codigo_obrasocial, obrasocial.codigofacturacion_obrasocial,obrasocial.razonsocial_obrasocial,round(obrasocial.importeunidaddearancel_obrasocial,2), obrasocial.añonbu FROM obrasocial  WHERE (obrasocial.id_obrasocial=" + id_obrasocial + ")");
                while (Rs.next()) {
                    datos[0] = Rs.getString(1);
                    datos[1] = Rs.getString(2);
                    datos[2] = Rs.getString(3);
                    datos[3] = Rs.getString(4);
                    datos[4] = Rs.getString(5);
                    model2.addRow(datos);
                }
                obras.setModel(model2);
                 alinear();
                obras.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
                obras.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
                obras.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
                obras.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
                obras.getColumnModel().getColumn(4).setCellRenderer(alinearCentro);
                progreso.setValue(1500);
                cn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);

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

    private void txtobrasocialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtobrasocialActionPerformed
        cargartabla(txtobrasocial.getText());        
    }//GEN-LAST:event_txtobrasocialActionPerformed

    private void progresoStateChanged(javax.swing.event.ChangeEvent evt) {
        if (progreso.getValue() == 100) {
            this.dispose();
        }
    }

    public javax.swing.JProgressBar getjProgressBar1() {
        return progreso;
    }

    public void iniciarSplash() {
        this.getjProgressBar1().setBorderPainted(false);
        this.getjProgressBar1().setForeground(new Color(100, 100, 100, 100));
        this.getjProgressBar1().setStringPainted(true);
    }

    private void txtobrasocialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtobrasocialKeyReleased
        TableRowSorter sorter = new TableRowSorter(model);
        sorter.setRowFilter(RowFilter.regexFilter(".*" + txtobrasocial.getText() + ".*"));
        obras.setRowSorter(sorter);
    }//GEN-LAST:event_txtobrasocialKeyReleased

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        try {
            //Mensaje de encabezado
            MessageFormat encabezado = new MessageFormat("Obras Sociales");
            //Mensaje en el pie de pagina
            MessageFormat pie = new MessageFormat("");
            //Imprimir JTable
            obras.print(JTable.PrintMode.FIT_WIDTH, encabezado, pie);
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btnimprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable obras;
    private javax.swing.JProgressBar progreso;
    private javax.swing.JTextField txtobrasocial;
    // End of variables declaration//GEN-END:variables
}
