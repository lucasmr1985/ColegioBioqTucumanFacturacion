package Formularios;

import Clases.ConexionMariaDB;
import static Clases.Escape.funcionescape;
import Clases.solomayusculas;
import java.awt.print.PrinterException;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;

public class Tabla_NBU extends javax.swing.JFrame {

    int id_obrasocial;
    DefaultTableModel model;
    DefaultTableModel model2;
    DefaultTableCellRenderer alinearCentro, alinearDerecha, alinearIzquierda;

    public Tabla_NBU(java.awt.Frame parent, boolean modal) {

        initComponents();
        this.setTitle("Nomenclador Bioquimico Unico");
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargartabla("2012");
        txtpracticanbu.setDocument(new solomayusculas());
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
        //codigo_practica	Determinacion	unidadbioquimica_practica	abreviatura_practica	urgencia_practica	referencia_practica	fercuencia_practica

        String[] Titulo = {"Codigo", "Determinación", "Unidad Bioq", "Abreviatura", "Urgencia", "Referencia", "Frecuencia"};
        String[] Registros = new String[7];
        String sql = "Select  codigo_practica,determinacion_practica,round(CAST(unidadbioquimica_practica as double),2) as unidadbioquimica_practica,abreviatura_practica,urgencia_practica,referencia_practica,fercuencia_practica\n"
                + "from practicasnbu\n"
                + "where añonbu_practicas=" + valor;
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
                Registros[0] = rs.getString("codigo_practica");
                Registros[1] = rs.getString("determinacion_practica");
                Registros[2] = rs.getString("unidadbioquimica_practica");
                Registros[3] = rs.getString("abreviatura_practica");
                Registros[4] = rs.getString("urgencia_practica");
                Registros[5] = rs.getString("referencia_practica");
                Registros[6] = rs.getString("fercuencia_practica");
                model.addRow(Registros);
            }
            tabla_practicas_nbu.setModel(model);
            tabla_practicas_nbu.setAutoCreateRowSorter(true);
            alinear();
            tabla_practicas_nbu.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
            tabla_practicas_nbu.getColumnModel().getColumn(1).setCellRenderer(alinearIzquierda);
            tabla_practicas_nbu.getColumnModel().getColumn(2).setCellRenderer(alinearDerecha);
            tabla_practicas_nbu.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
            tabla_practicas_nbu.getColumnModel().getColumn(4).setCellRenderer(alinearCentro);
            tabla_practicas_nbu.getColumnModel().getColumn(5).setCellRenderer(alinearCentro);
            tabla_practicas_nbu.getColumnModel().getColumn(6).setCellRenderer(alinearCentro);

            tabla_practicas_nbu.getColumnModel().getColumn(1).setPreferredWidth(200);
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
        tabla_practicas_nbu = new javax.swing.JTable();
        progreso = new javax.swing.JProgressBar();
        jLabel6 = new javax.swing.JLabel();
        btnimprimir = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        cboaño = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        txtpracticanbu = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NBU", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        tabla_practicas_nbu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tabla_practicas_nbu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_practicas_nbu.setOpaque(false);
        tabla_practicas_nbu.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla_practicas_nbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_practicas_nbuKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_practicas_nbu);

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

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Año:");

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

        cboaño.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cboaño.setForeground(new java.awt.Color(0, 102, 204));
        cboaño.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2010", "2012", "2016", "1380", " " }));
        cboaño.setSelectedIndex(1);
        cboaño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboañoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel1.setText("Nomenclador Bioquimico Unico");

        txtpracticanbu.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtpracticanbu.setForeground(new java.awt.Color(0, 102, 204));
        txtpracticanbu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpracticanbuActionPerformed(evt);
            }
        });
        txtpracticanbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpracticanbuKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Practica NBU:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboaño, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtpracticanbu, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnimprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtpracticanbu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(cboaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnimprimir)
                    .addComponent(btncancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabla_practicas_nbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_practicas_nbuKeyPressed

    }//GEN-LAST:event_tabla_practicas_nbuKeyPressed


    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        ConexionMariaDB cc = new ConexionMariaDB();
        Connection cn = cc.Conectar();
        ////////////////Previsualizacion///////////////////////////

        JFrame viewer = new JFrame();
        viewer.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        viewer.setSize(800, 600);
        viewer.setLocationRelativeTo(null);
        JasperViewer jv = null;
        ///////////////////////////////////////////////////////////
        Map parametros = new HashMap();
        parametros.put("añonbu", cboaño.getSelectedItem().toString());

        try {
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Planilla_NBU.jasper"));
            JasperPrint jPrint = JasperFillManager.fillReport(report, parametros, cn);
            //JasperExportManager.exportReportToPdfFile(jPrint_validacion, "C:\\Descargas-CBT\\" + periodo + "-" + txtcolegiado.getText() + "-validacion-.pdf");
            //JasperPrintManager.printReport(jPrint_comprobante, true);
           

            jv = new JasperViewer(jPrint);
            viewer.getContentPane().add(jv.getContentPane());
            viewer.setVisible(true);
           ////////////////////Export XLSX 
            int dialogButton = JOptionPane.YES_NO_OPTION;
           JOptionPane.showConfirmDialog(null, "Desea exportar el nomenclador en Excel","",dialogButton);
           if(dialogButton==JOptionPane.YES_OPTION){
            File destFile = new File("C:\\Descargas-CBT\\NBU-"+cboaño.getSelectedItem().toString()+".xlsx");
            JRXlsxExporter exportarxls = new JRXlsxExporter();
            exportarxls.setExporterInput(new SimpleExporterInput(jPrint));
            exportarxls.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setOnePagePerSheet(false);
            configuration.setRemoveEmptySpaceBetweenRows(true);
            exportarxls.setConfiguration(configuration);
            exportarxls.exportReport();
              JOptionPane.showMessageDialog(null,"Planilla generada en : C:\\Descargas-CBT\\NBU-"+cboaño.getSelectedItem().toString()+".xlsx");
           }

            //////////////////////////////
        } catch (JRException ex) {
            System.err.println("Error iReport: " + ex.getMessage());
        }

    }//GEN-LAST:event_btnimprimirActionPerformed

    private void cboañoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboañoActionPerformed
        String año = "";
        año = cboaño.getSelectedItem().toString();
        cargartabla(año);
    }//GEN-LAST:event_cboañoActionPerformed

    private void txtpracticanbuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpracticanbuActionPerformed
    }//GEN-LAST:event_txtpracticanbuActionPerformed

    private void txtpracticanbuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpracticanbuKeyReleased
        TableRowSorter sorter = new TableRowSorter(model);
        sorter.setRowFilter(RowFilter.regexFilter(".*" + txtpracticanbu.getText() + ".*"));
        tabla_practicas_nbu.setRowSorter(sorter);
    }//GEN-LAST:event_txtpracticanbuKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JComboBox cboaño;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JProgressBar progreso;
    private javax.swing.JTable tabla_practicas_nbu;
    private javax.swing.JTextField txtpracticanbu;
    // End of variables declaration//GEN-END:variables
}
