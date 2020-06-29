package Formularios;

import Clases.ConexionMariaDB;
import static Clases.Escape.funcionescape;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Detalle_txt extends javax.swing.JDialog {

    DefaultTableModel model;
    public static String id_orden, afiliado, obrasocial, fecha, total;
    public static String p1 = "0", p2 = "0", p3 = "0", p4 = "0", p5 = "0", p6 = "0", p7 = "0", p8 = "0", p9 = "0", p10 = "0", p11 = "0", p12 = "0", p13 = "0", p14 = "0", p15 = "0", p16 = "0", p17 = "0", p18 = "0", p19 = "0", p20 = "0";
    DefaultTableCellRenderer alinearCentro, alinearDerecha, alinearIzquierda;

    public Detalle_txt(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        this.setTitle("Detalle");
        this.setLocationRelativeTo(null);
        cargartabla();
        txtafiliado.setText(afiliado);

        txtfecha.setText(fecha);
       // txttotal.setText(total);
        // cargartotales();
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
       
        txttotal.setText(String.valueOf(total1));
    }

    public double Redondear(double numero) {
        return Math.rint(numero * 100) / 100;
    }

    void cargartabla() {
        String[] Titulo = {"Cod", "Practica", "Precio"};
        String[] Registros = new String[3];

        String sql = "SELECT id_obrasocial,id_practicasnbu,codigo_practica,preciototal FROM obrasocial_tiene_practicasnbu";
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
            int i = 1;
            String sSQL3 = "SELECT id_obrasocial,codigo_obrasocial,razonsocial_obrasocial FROM obrasocial WHERE codigo_obrasocial=" + obrasocial;
            Statement st3 = cn.createStatement();
            ResultSet rs3 = st3.executeQuery(sSQL3);
            rs3.next();
            while (rs.next()) {

                if (rs3.getString("id_obrasocial").equals(rs.getString("id_obrasocial"))) {

                    txtobra.setText(rs3.getString("razonsocial_obrasocial"));
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p1) + 660000 && !p1.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p2) + 660000 && !p2.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p3) + 660000 && !p3.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p4) + 660000 && !p4.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p5) + 660000 && !p5.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p6) + 660000 && !p6.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p7) + 660000 && !p7.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p8) + 660000 && !p8.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p9) + 660000 && !p9.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p10) + 660000 && !p10.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p11) + 660000 && !p11.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p12) + 660000 && !p12.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p13) + 660000 && !p13.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p14) + 660000 && !p14.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p15) + 660000 && !p15.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p16) + 660000 && !p16.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p17) + 660000 && !p17.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p18) + 660000 && !p18.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p19) + 660000 && !p19.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }
                    if (rs.getInt("codigo_practica") == Integer.valueOf(p20) + 660000 && !p20.equals("0")) {
                        Registros[0] = rs.getString("codigo_practica");
                        ////////////////////////////////////////////////////////////
                        String sSQL4 = "SELECT id_practicasnbu,determinacion_practica FROM practicasnbu WHERE id_practicasnbu=" + rs.getInt("id_practicasnbu");
                        Statement st4 = cn.createStatement();
                        ResultSet rs4 = st4.executeQuery(sSQL4);
                        rs4.next();
                        Registros[1] = rs4.getString("determinacion_practica");
                        /////////////////////////////////////////////////////////
                        Registros[2] = rs.getString("preciototal");
                        model.addRow(Registros);
                    }

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
            cargartotales();
            p1 = "0";
            p2 = "0";
            p3 = "0";
            p4 = "0";
            p5 = "0";
            p6 = "0";
            p7 = "0";
            p8 = "0";
            p9 = "0";
            p10 = "0";
            p11 = "0";
            p12 = "0";
            p13 = "0";
            p14 = "0";
            p15 = "0";
            p16 = "0";
            p17 = "0";
            p18 = "0";
            p19 = "0";
            p20 = "0";
            cn.close();
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
        jLabel2 = new javax.swing.JLabel();
        txtafiliado = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtobra = new javax.swing.JTextField();
        txttotal = new javax.swing.JTextField();

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
        tablapracticas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablapracticasKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablapracticas);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Afiliado:");

        txtafiliado.setEditable(false);
        txtafiliado.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtafiliado.setForeground(new java.awt.Color(0, 51, 102));
        txtafiliado.setBorder(null);
        txtafiliado.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Total:");

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

        txttotal.setEditable(false);
        txttotal.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txttotal.setForeground(new java.awt.Color(0, 51, 102));
        txttotal.setBorder(null);
        txttotal.setOpaque(false);

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
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtobra))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 133, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtobra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablapracticasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablapracticasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            if (tablapracticas.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "No seleccionó ninguna fila...");
            } else {
                model.removeRow(tablapracticas.getSelectedRow());
            }
        }
    }//GEN-LAST:event_tablapracticasKeyPressed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablapracticas;
    private javax.swing.JTextField txtafiliado;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtobra;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
