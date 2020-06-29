package Formularios;

import Clases.ConexionMariaDB;
import static Formularios.Login.id_usuario;
import static Formularios.Login.periodo_colegiado;
import static Formularios.MainL.periodo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.table.DefaultTableModel;

public class EsperaImportar extends javax.swing.JDialog {

    HiloOrdenes hilo;
    private int minutos = 1;
    int id_orden,id_obra_social;

    public EsperaImportar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        hilo = new HiloOrdenes(progreso);
        hilo.start();
        hilo = null;
        this.setTitle("Aviso");
        this.setLocationRelativeTo(null);

    }

    void cargarorden() {
        String sSQL = "";
        String numero = "";
        ConexionMariaDB mysql = new ConexionMariaDB();
        Connection cn = mysql.Conectar();

        sSQL = "SELECT MAX(id_orden) AS id_orden FROM ordenes1";

        //“SELECT MAX(id) AS id FROM tabla”
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            rs.last();
            if (rs.getInt("id_orden") != 0) {
                id_orden = rs.getInt("id_orden") + 1;
            } else {
                id_orden = 1;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
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
            ConexionMariaDB mysql = new ConexionMariaDB();
            Connection cn = mysql.Conectar();
            int cantidad;
            ////////////////////////////////////////////
            int indice = 1; //indice contara los numeros primos que vamos encontrando    
            DefaultTableModel temp = (DefaultTableModel) MainL.myModel;
            int i2 = 0, nbu;
            
            int p = 0, i4 = 0, bandera_orden;
            int n4 = temp.getRowCount();
            progreso.setValue(2);
            cantidad = 100 / n4;
            if (n4 != 0) {
                cargarorden();
                bandera_orden = 0;
                while (i4 < n4) {

                    if (periodo_colegiado <= Integer.valueOf(periodo)) {
                        try {
                            String sSQL4 = "SELECT estado FROM periodos ";
                            Statement st4 = cn.createStatement();
                            ResultSet rs4 = st4.executeQuery(sSQL4);
                            rs4.next();
                            if (rs4.getBoolean("estado") == true) {
                                if (temp.getRowCount() != 0) {

                                    double total = 0.0, totalordenes = 0.0;
                                    String num_afiliado, precio, nom_afiliado, dni_afiliado, fecha_orden, matricula_presc, mes, año, num_orden, cod_practca;
                                    String sSQL = "", ssQL = "", nombre_practca;
                                    int n, n2, n3, i, j, bandera = 0, obra_social;
                                    num_afiliado = temp.getValueAt(i4, 5).toString();
                                    nom_afiliado = temp.getValueAt(i4, 3).toString();
                                    dni_afiliado = temp.getValueAt(i4, 8).toString();
                                    fecha_orden = temp.getValueAt(i4, 6).toString();
                                    matricula_presc = "0";
                                    
                                    num_orden = temp.getValueAt(i4, 4).toString();
                                    /////////////////////////////////////
                                    i = 0;
                                    while (i < num_afiliado.length()) {
                                        if (String.valueOf(num_afiliado.charAt(i)).equals("-") && String.valueOf(num_afiliado.charAt(i + 1)).equals("-") || String.valueOf(num_afiliado.charAt(i)).equals("/")) {
                                            bandera = 1;
                                            i = num_afiliado.length();
                                        }
                                        i++;
                                    }

                                    if (bandera == 0) {
                                        obra_social = Integer.valueOf(temp.getValueAt(i4, 2).toString());
                                        if (obra_social != 3100 && obra_social != 3101) {
                                            ////////////////////////ordenes/////////////////////////
                                            sSQL = "INSERT INTO ordenes1 (periodo,id_obrasocial,nombre_afiliado,dni_afiliado,numero_afiliado,"
                                                    + "matricula_prescripcion,numero_orden,"
                                                    + "fecha_orden, id_colegiados,total)"
                                                    + "VALUES(?,?,?,?,?,?,?,?,?,?)";
                                            try {
                                                /////////////////////////////////////////////////////////////////
                                                String sSQL3 = "SELECT id_obrasocial,añonbu FROM obrasocial WHERE codigo_obrasocial=" + obra_social;
                                                Statement st3 = cn.createStatement();
                                                ResultSet rs3 = st3.executeQuery(sSQL3);
                                                rs3.next();
                                                id_obra_social = rs3.getInt("id_obrasocial");
                                                ////////////////////////////////////////////////////////////
                                                PreparedStatement pst = cn.prepareStatement(sSQL);
                                                pst.setString(1, MainL.año + MainL.mes);
                                                pst.setInt(2, id_obra_social);
                                                pst.setString(3, nom_afiliado);
                                                pst.setString(4, dni_afiliado);
                                                pst.setString(5, num_afiliado);
                                                pst.setString(6, matricula_presc);
                                                pst.setString(7, num_orden);
                                                pst.setString(8, fecha_orden);
                                                pst.setInt(9, id_usuario);
                                                pst.setDouble(10, Redondear(totalordenes));
                                                n = pst.executeUpdate();
                                                if (n > 0) {
                                                    //////////////////////////////////////////////////detalle de orden////////////////////
                                                    try {
                                                        cargarorden();
                                                        i = 1;

                                                        nbu = rs3.getInt("añonbu");
                                                        j = 9;
                                                        while (j <= 28) {

                                                            if (temp.getValueAt(i4, j) != null) {
                                                                //JOptionPane.showMessageDialog(null, Integer.valueOf(tablaordenes1.getValueAt(i4, j).toString()) + 66000);
                                                                ////////////////////////////////////////////////////////////
                                                                int cod = Integer.valueOf(temp.getValueAt(i4, j).toString()) + 660000;
                                                                String sSQL5 = "SELECT codigo_practica,preciototal,añonbu,determinacion FROM obrasocial_tiene_practicasnbu WHERE (id_obrasocial=" + id_obra_social + "  AND añonbu=" + nbu + "  AND codigo_practica=" + cod + " )";
                                                                Statement st5 = cn.createStatement();                                                               ///(colegiados.id_colegiados=" + id_usuario + "  AND ordenes.periodo=" + periodo2 + ")");
                                                                ResultSet rs5 = st5.executeQuery(sSQL5);
                                                                //JOptionPane.showMessageDialog(null, rs5.getString("codigo_practica"));
                                                                rs5.next();
                                                                    //if (id_obra_social==rs5.getInt("id_obrasocial")&& rs5.getInt("añonbu") == nbu && Integer.valueOf(tablaordenes1.getValueAt(i4, j).toString()) + 660000 == rs5.getInt("codigo_practica")) {
                                                                //                   if (rs5.getInt("añonbu") == nbu && Integer.valueOf(tablaordenes1.getValueAt(i4, j).toString()) + 660000 == rs5.getInt("codigo_practica")) {

                                                                ///////////////////////////////inserta en la tabla detalles////////////////////////////////////////////////////////////
                                                                String SQL = "INSERT INTO detalle_ordenes1 (id_orden, cod_practica,nombre_practica,precio_practica)"
                                                                        + "VALUES(?,?,?,?)";

                                                                PreparedStatement st = cn.prepareStatement(SQL);
                                                                cod_practca = rs5.getString("codigo_practica");
                                                                nombre_practca = rs5.getString("determinacion");
                                                                total = Double.valueOf(rs5.getString("preciototal"));
                                                                totalordenes = totalordenes + total;
                                                                st.setInt(1, id_orden - 1);
                                                                st.setString(2, cod_practca);
                                                                st.setString(3, nombre_practca);
                                                                st.setDouble(4, Redondear(total));
                                                                n3 = st.executeUpdate();
                                                                if (n3 > 0) {
                                                                }
                                                                  //  }

                                                                ////////////////////////////////////////////////////////
                                                                String sSQL7;
                                                                sSQL7 = "UPDATE ordenes1 SET total=?  WHERE id_orden=" + (id_orden - 1);

                                                                PreparedStatement pst2 = cn.prepareStatement(sSQL7);
                                                                pst2.setDouble(1, Redondear(totalordenes));
                                                                int n5 = pst2.executeUpdate();
                                                                if (n5 > 0) {
                                                                    // JOptionPane.showMessageDialog(null, totalordenes);
                                                                }
                                                                ////////////////////////////////////////////////////////
                                                            }
                                                            j++;

                                                        }
                                                    } catch (Exception e) {
                                                        //JOptionPane.showMessageDialog(null, e);
                                                    }
                                                    ///////////////////////////////////////////////////////////////////////
                                                    temp.removeRow(i4);
                                                    i4--;
                                                    n4--;
                                                   // Main.totalRow--;
                                                }
                                            } catch (SQLException ex) {

                                                //  JOptionPane.showMessageDialog(null, ex);
                                            }
                                        }
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(null, "No hay practicas en la tabla...");
                                    i4 = n4;
                                   // jLabel16.setText("");
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "El período esta Inhabilitado...");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                            JOptionPane.showMessageDialog(null, "Error en la base de datos");
                        }
                        bandera_orden = 1;
                    }
                 
                    progreso.setValue(indice); //aumentamos la barra 
                    //porcentaje.setText(((indice * 100) / cantidad) + "%");
                    indice = indice + cantidad;
                    i4++;
                }
                if (bandera_orden == 1) {
                    JOptionPane.showMessageDialog(null, "Proceso realizado con exito...");
                    //jLabel19.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Periodo finalizado...");
                }
            }

              
            dispose();
        }

        public void pausa(int mlSeg) {
            try {
                // pausa para el splash
                Thread.sleep(mlSeg);
            } catch (Exception e) {
            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        progreso = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/loading (1).gif"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Espere un momento por favor...");

        progreso.setFont(new java.awt.Font("Tahoma", 0, 6)); // NOI18N
        progreso.setForeground(new java.awt.Color(100, 100, 100));
        progreso.setString("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progreso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 431, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(progreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar progreso;
    // End of variables declaration//GEN-END:variables
}
