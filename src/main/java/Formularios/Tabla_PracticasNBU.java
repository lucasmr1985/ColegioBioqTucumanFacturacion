package Formularios;

import Clases.ConexionMariaDB;
import static Clases.Escape.funcionescape;
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

public class Tabla_PracticasNBU extends javax.swing.JFrame {

    int id_obrasocial, id_practica;
    int mod = 0;
    String detereminacion = "";
    String[] obrasocial = new String[1500];
    public static int[] idobrasocial = new int[500];
    int contadorobrasocial = 0;
    DefaultTableModel model;
    DefaultTableCellRenderer alinearCentro, alinearDerecha, alinearIzquierda;
    DefaultTableModel model2;
    HiloObra hilo;
    HiloPracticas hilo2;
    int contadorj = 0;
    TextAutoCompleter textAutoAcompleter;
    public static String ObraSocial;
    String[] practica = new String[2000];
    String[] preciopractica = new String[2000];

    public Tabla_PracticasNBU(java.awt.Frame parent, boolean modal) {
        initComponents();
        this.setTitle("Prácticas");
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        this.setLocationRelativeTo(null);
        cargarobrasocial();
        textAutoAcompleter = new TextAutoCompleter(txtnbu);

    }

    void alinear() {
        alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
    }

    ///////////////////////////////////////HILO Obra Social///////////////////////////////////////////////
    public class HiloObra extends Thread {

        JProgressBar progreso;

        public HiloObra(JProgressBar progreso1) {
            super();
            this.progreso = progreso1;
        }

        public void run() {

            String[] titulos = {"Práctica", "Cod Fact OS", "Determinación", "U. B.", "Precio Total", "Año NBU"};//estos seran los titulos de la tabla.            
            String[] datos = new String[6];
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
                ResultSet Rs = St.executeQuery("SELECT obrasocial_tiene_practicasnbu.codigo_practica, obrasocial_tiene_practicasnbu.codigo_fac_practicas_obrasocial,practicasnbu.determinacion_practica,obrasocial_tiene_practicasnbu.unidaddebioquimica_practica,round(obrasocial_tiene_practicasnbu.preciototal,2),obrasocial_tiene_practicasnbu.añonbu FROM obrasocial_tiene_practicasnbu INNER JOIN practicasnbu ON obrasocial_tiene_practicasnbu.id_practicasnbu = practicasnbu.id_practicasnbu INNER JOIN obrasocial ON obrasocial.id_obrasocial = obrasocial_tiene_practicasnbu.id_obrasocial WHERE obrasocial_tiene_practicasnbu.id_obrasocial=" + id_obrasocial);
                while (Rs.next()) {
                    datos[0] = Rs.getString(1);
                    datos[1] = Rs.getString(2);
                    datos[2] = Rs.getString(3);
                    datos[3] = Rs.getString(4);
                    datos[4] = Rs.getString(5);
                    datos[5] = Rs.getString(6);
                    model2.addRow(datos);
                }
                practicas.setModel(model2);
                /////////////////////////////////////////////////////////////
                alinear();
                practicas.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
                practicas.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
                practicas.getColumnModel().getColumn(2).setCellRenderer(alinearIzquierda);
                practicas.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
                practicas.getColumnModel().getColumn(4).setCellRenderer(alinearCentro);
                practicas.getColumnModel().getColumn(5).setCellRenderer(alinearCentro);
                progreso.setValue(1500);
                practicas.getColumnModel().getColumn(2).setPreferredWidth(200);
                cn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);

            }

        }

        public void pausa(int mlSeg) {
            try {
                // pausa para el splash
                Thread.sleep(mlSeg);
            } catch (InterruptedException e) {
            }

        }

    }

    ///////////////////////////////////////HILO practicas///////////////////////////////////////////////
    public class HiloPracticas extends Thread {

        JProgressBar progreso;

        public HiloPracticas(JProgressBar progreso1) {
            super();
            this.progreso = progreso1;
        }

        @Override
        public void run() {

            String[] titulos = {"Código", "Cod. Fact.", "Determinación", "U. B.", "Precio Total", "Año NBU"};//estos seran los titulos de la tabla.            
            String[] datos = new String[6];
            model2 = new DefaultTableModel(null, titulos) {
                ////Celdas no editables////////
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            ConexionMariaDB cc = new ConexionMariaDB();
            Connection cn = cc.Conectar();
            try {
                Statement St = cn.createStatement();
                ResultSet Rs = St.executeQuery("SELECT obrasocial_tiene_practicasnbu.codigo_practica, obrasocial_tiene_practicasnbu.codigo_fac_practicas_obrasocial, practicasnbu.determinacion_practica, obrasocial_tiene_practicasnbu.unidaddebioquimica_practica, round(obrasocial_tiene_practicasnbu.preciototal,2), obrasocial_tiene_practicasnbu.añonbu FROM obrasocial_tiene_practicasnbu INNER JOIN practicasnbu ON obrasocial_tiene_practicasnbu.id_practicasnbu = practicasnbu.id_practicasnbu INNER JOIN obrasocial ON obrasocial.id_obrasocial = obrasocial_tiene_practicasnbu.id_obrasocial WHERE (obrasocial_tiene_practicasnbu.id_obrasocial=" + id_obrasocial + "  AND obrasocial_tiene_practicasnbu.codigo_practica=" + detereminacion + " ) ");
                while (Rs.next()) {
                    datos[0] = Rs.getString(1);
                    datos[1] = Rs.getString(2);
                    datos[2] = Rs.getString(3);
                    datos[3] = Rs.getString(4);
                    datos[4] = Rs.getString(5);
                    datos[5] = Rs.getString(6);
                    model2.addRow(datos);
                }
                practicas.setModel(model2);
                /////////////////////////////////////////////////////////////
                alinear();
                practicas.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
                practicas.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
                practicas.getColumnModel().getColumn(2).setCellRenderer(alinearIzquierda);
                practicas.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
                practicas.getColumnModel().getColumn(4).setCellRenderer(alinearCentro);
                practicas.getColumnModel().getColumn(5).setCellRenderer(alinearCentro);
                progreso.setValue(1500);
                txtnbu.requestFocus();
                practicas.getColumnModel().getColumn(2).setPreferredWidth(200);
                cn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }

        public void pausa(int mlSeg) {
            try {
                Thread.sleep(mlSeg);
            } catch (InterruptedException e) {
            }

        }

    }

    void borrarpractica() {
        textAutoAcompleter.removeAllItems();
    }

    void cargarpracticaconobra() {
        int i = 0;
        practica = new String[500000];
        preciopractica = new String[500000];
        contadorj = 0;
        ConexionMariaDB cc = new ConexionMariaDB();
        Connection cn = cc.Conectar();
        try {
            Statement St = cn.createStatement();
            ResultSet Rs = St.executeQuery("SELECT codigo_practica,preciototal,determinacion FROM obrasocial_tiene_practicasnbu  WHERE id_obrasocial=" + id_obrasocial);
            while (Rs.next()) {
                practica[contadorj] = (Rs.getString(1) + " - " + Rs.getString(3));
                textAutoAcompleter.addItem(practica[contadorj]);
                preciopractica[contadorj] = Rs.getString(2);
                contadorj++;
            }
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        textAutoAcompleter.setMode(0); // infijo
        // textAutoAcompleter.setMode(1); // sufijo
        //textAutoAcompleter.setCaseSensitive(true); // Sensible a mayúsculas
        textAutoAcompleter.setCaseSensitive(false);
    }
/////////////////////////////////////////////////////////////////////////////

    void cargarobrasocial() {

        ConexionMariaDB mysql = new ConexionMariaDB();
        Connection cn = mysql.Conectar();

        String sSQL = "SELECT id_obrasocial,razonsocial_obrasocial,codigo_obrasocial FROM obrasocial where estado_obrasocial=1 ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            // Recorro y cargo las obras sociales
            while (rs.next()) {
                idobrasocial[contadorobrasocial] = rs.getInt("id_obrasocial");

                obrasocial[contadorobrasocial] = (rs.getString("codigo_obrasocial") + " - " + rs.getString("razonsocial_obrasocial"));
                contadorobrasocial++;
            }
            cn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar al servidor... Verifique su conexión a internet");
        }
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

/////////////////////////////////////////////////////////////////////////////
    void filtrarobrasocial(String valor) {
        String[] Titulo = {"Código", "Cod. Fact.", "Determinación", "U. B.", "Precio Total", "Año NBU"};

        String[] Registros = new String[6];
        String sql = "SELECT id_obrasocial,codigo_practica,codigo_fac_practicas_obrasocial,determinacion,unidaddebioquimica_practica,preciototal,añonbu FROM obrasocial_tiene_practicasnbu ";
        model = new DefaultTableModel(null, Titulo) {
            ////Celdas no editables////////
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        ConexionMariaDB cc = new ConexionMariaDB();
        Connection cn = cc.Conectar();
        try {
            /////////////////////////////////////////////////////
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                if (id_obrasocial == rs.getInt("id_obrasocial")) {
                    Registros[0] = rs.getString("codigo_practica");
                    Registros[1] = rs.getString("codigo_fac_practicas_obrasocial");
                    Registros[2] = rs.getString("determinacion");
                    Registros[3] = rs.getString("unidaddebioquimica_practica");
                    Registros[4] = rs.getString("preciototal");
                    Registros[5] = rs.getString("añonbu");
                    model.addRow(Registros);
                }
                practicas.setModel(model);
                alinear();
                practicas.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
                practicas.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
                practicas.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
                practicas.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
                practicas.getColumnModel().getColumn(4).setCellRenderer(alinearCentro);
                practicas.getColumnModel().getColumn(5).setCellRenderer(alinearCentro);
                practicas.getColumnModel().getColumn(2).setPreferredWidth(200);

            }
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
        practicas = new javax.swing.JTable();
        progreso = new javax.swing.JProgressBar();
        jLabel5 = new javax.swing.JLabel();
        txtobrasocial = new javax.swing.JTextField();
        txtnbu = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnimprimir = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Practicas NBU", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        practicas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        practicas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        practicas.setOpaque(false);
        practicas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        practicas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                practicasKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(practicas);

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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                    .addComponent(progreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progreso, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Obra Social:");

        txtobrasocial.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtobrasocial.setForeground(new java.awt.Color(0, 102, 204));
        txtobrasocial.setNextFocusableComponent(txtnbu);
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

        txtnbu.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnbu.setForeground(new java.awt.Color(0, 102, 204));
        txtnbu.setNextFocusableComponent(practicas);
        txtnbu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnbuActionPerformed(evt);
            }
        });
        txtnbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnbuKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Practicas NBU:");

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
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnbu, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtobrasocial, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnimprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtobrasocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnbu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnimprimir)
                    .addComponent(btncancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void txtobrasocialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtobrasocialActionPerformed
        txtnbu.setText("");
        borrarpractica();
        int band = 0;
        String obra = txtobrasocial.getText();
        if (!obra.equals("")) {
            int i = 0;
            while (i < contadorobrasocial) {
                if (obra.equals(obrasocial[i])) {
                    id_obrasocial = idobrasocial[i];
                    band = 1;

                    break;

                }
                i++;
            }
            if (band == 0) {
                JOptionPane.showMessageDialog(null, "La obra social no se encuentra en nuestra base de datos...");
                txtobrasocial.requestFocus();
            } else {
                iniciarSplash();
                hilo = new HiloObra(progreso);
                hilo.start();
                hilo = null;
                cargarpracticaconobra();
                txtobrasocial.transferFocus();
            }
        }
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
        //[77,239,38]
        this.getjProgressBar1().setStringPainted(true);
    }

    void borrartabla() {
        DefaultTableModel temp = (DefaultTableModel) practicas.getModel();
        int a = temp.getRowCount() - 1;  //Índices van de 0 a n-1
        for (int i = a; i >= 0; i--) {
            temp.removeRow(i);
        }
    }

    private void txtobrasocialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtobrasocialKeyReleased
        if (txtobrasocial.getText().equals("")) {
            txtobrasocial.requestFocus();
            borrartabla();
        }
    }//GEN-LAST:event_txtobrasocialKeyReleased

    private void practicasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_practicasKeyPressed
        DefaultTableModel temp = (DefaultTableModel) practicas.getModel();
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            practicas.transferFocus();
            evt.consume();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (practicas.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "No seleccionó ninguna fila...");
            } else {
                /* Detalle_nbu.codigo_fac_practicas_obrasocial = practicas.getValueAt(practicas.getSelectedRow(), 1).toString();
                 Detalle_nbu.unidaddebioquimica_practica = practicas.getValueAt(practicas.getSelectedRow(), 2).toString();
                 Detalle_nbu.preciofijo = practicas.getValueAt(practicas.getSelectedRow(), 4).toString();
                 Detalle_nbu.preciototal = practicas.getValueAt(practicas.getSelectedRow(), 5).toString();
                 new Detalle_nbu(null, true).setVisible(true);*/
                ///cargartabla("");
                txtnbu.requestFocus();
            }
        }
    }//GEN-LAST:event_practicasKeyPressed

    private void txtnbuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnbuActionPerformed
        /*int band = 0, i = 0, j = 0;
        String obra = txtnbu.getText(), cod = "", nom = "";
        while (i < obra.length()) {
            if (String.valueOf(obra.charAt(i)).equals("-")) {
                j = i + 2;
                i = obra.length();
            } else {
                cod = cod + obra.charAt(i);
            }
            i++;
        }
        ///nom/// 0000 - aaaaaaaaa
        while (j < obra.length()) {
            nom = nom + obra.charAt(j);
            j++;
        }
        if (!obra.equals("")) {
            int i2 = 0;
            while (i2 < contadorj) {
                if (obra.equals(practica[i2])) {
                    detereminacion = cod;
                    band = 1;
                }
                i2++;
            }
            if (band == 0) {
                JOptionPane.showMessageDialog(null, "La Practica no se encuentra en nuestra base de datos...");
                txtnbu.requestFocus();
            } else {
                iniciarSplash();
                hilo2 = new HiloPracticas(progreso);
                hilo2.start();
                hilo2 = null;
                txtnbu.requestFocus();
            }
        }*/


    }//GEN-LAST:event_txtnbuActionPerformed

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        try {
            //Mensaje de encabezado
            MessageFormat encabezado = new MessageFormat("Obra Social -" + txtobrasocial.getText());
            //Mensaje en el pie de pagina
            MessageFormat pie = new MessageFormat("");
            //Imprimir JTable
            practicas.print(JTable.PrintMode.FIT_WIDTH, encabezado, pie);
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btnimprimirActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void txtnbuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnbuKeyReleased
        TableRowSorter sorter = new TableRowSorter(model);
        sorter.setRowFilter(RowFilter.regexFilter(".*" + txtnbu.getText() + ".*"));
        practicas.setRowSorter(sorter);        // TODO add your handling code here:
    }//GEN-LAST:event_txtnbuKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable practicas;
    private javax.swing.JProgressBar progreso;
    private javax.swing.JTextField txtnbu;
    private javax.swing.JTextField txtobrasocial;
    // End of variables declaration//GEN-END:variables
}
