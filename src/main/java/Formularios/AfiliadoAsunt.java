package Formularios;

import Clases.ConexionMariaDB;
import ClienteAsunt.AsuntApiException;
import ClienteAsunt.AsuntValidadorClient;
import ClienteAsunt.AtencionPracticaResponse;
import ClienteAsunt.AutorizacionItemResponse;
import ClienteAsunt.AutorizarRequest;
import ClienteAsunt.AutorizarResponse;
import ClienteAsunt.ConsultarRequest;
import ClienteAsunt.ConsultarResultado;
import ClienteAsunt.ElegibilidadRequest;
import ClienteAsunt.ElegibilidadResponse;
import ClienteAsunt.ElegibilidadResultado;
import ClienteAsunt.PracticaRequest;
import java.awt.Cursor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AfiliadoAsunt extends javax.swing.JDialog {

    public static String habilitado = "", nombreafiliado = "", dni = "", Codigo_afiliado = "", plan = "", tipo = "", numeroPreautorizacion = "";
    
    String idmsj = "", hora = "", fechahora = "", codigo_seguridad = "", mensaje = "", respuesta = "", token = "";
    
    public static List<AtencionPracticaResponse> practicasAsunt;


    public AfiliadoAsunt(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Asunt");
        this.setLocationRelativeTo(null);
        cargarfecha();
        traeToken();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtafiliado = new javax.swing.JTextField();
        cbotipo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        btnaceptar = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese el numero de DNI", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Número:");

        txtafiliado.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtafiliado.setForeground(new java.awt.Color(0, 102, 204));
        txtafiliado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtafiliadoActionPerformed(evt);
            }
        });

        cbotipo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbotipo.setForeground(new java.awt.Color(0, 102, 204));
        cbotipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DNI", "LE", "LC", "NUMA" }));
        cbotipo.setNextFocusableComponent(txtafiliado);
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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Tipo:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbotipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtafiliado, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbotipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        btnaceptar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnaceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/32/728951 - electricity lightning.png"))); // NOI18N
        btnaceptar.setText("Validar");
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnaceptar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnaceptar)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    String completarceros(String v, int d) {
        String ceros = "";
        if (v.length() < d) {
            for (int i = v.length(); i < d; i++) {
                ceros += "0";
            }
            v = ceros + v;
        }
        return v;
    }

    void cursor() {
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        this.pack();
    }

    void cursor2() {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.pack();
    }

    void cargarfecha() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat formato2 = new SimpleDateFormat("yyMMddHHmmss");
        SimpleDateFormat formato3 = new SimpleDateFormat("yyyyMMdd");
        Date currentDate = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);

        fechahora = formato.format(currentDate);
        codigo_seguridad = formato2.format(currentDate) + formato3.format(currentDate);
        System.out.println(fechahora);
        System.out.println(codigo_seguridad);
    }

    boolean traeToken() {

        /////////Trae token
        String sql = "SELECT token FROM tokens_ws WHERE tiene_permiso=1 and id_obrasocial=21";
        boolean tiene_permiso = false;
        ConexionMariaDB maria = new ConexionMariaDB();
        Connection cn = maria.Conectar();
        Statement Select = null;
        try {
            Select = cn.createStatement();
            ResultSet rs = Select.executeQuery(sql);
            while (rs.next()) {
                token = rs.getString("token");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos...");
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                if (Select != null) {
                    Select.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return tiene_permiso;
    }

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        cursor();
        tipo = cbotipo.getSelectedItem().toString();
        dni = txtafiliado.getText();

        AsuntValidadorClient client = new AsuntValidadorClient(token);

        ElegibilidadRequest request = new ElegibilidadRequest();
        request.setTipo_identificacion_afiliado(tipo);
        request.setIdentificacion_afiliado(dni);

        try {
            ElegibilidadResultado resultado = client.elegibilidad(request);

            if (resultado.isExito()) {
                System.out.println("Nombre: " + resultado.getRespuestaOk().getData().getNombre());
                System.out.println("Documento: " + resultado.getRespuestaOk().getData().getDocumento());
                System.out.println("Credencial: " + resultado.getRespuestaOk().getData().getCredencial());
                System.out.println("Plan: " + resultado.getRespuestaOk().getData().getPlan());
                System.out.println("tipo: " + tipo);
                nombreafiliado = resultado.getRespuestaOk().getData().getNombre();
                dni = resultado.getRespuestaOk().getData().getDocumento();
                Codigo_afiliado = resultado.getRespuestaOk().getData().getCredencial();
                habilitado = "OK";
                JOptionPane.showMessageDialog(null, "Paciente: habilitado\nNombre: " + resultado.getRespuestaOk().getData().getNombre() + "\n"
                        + "Documento: " + resultado.getRespuestaOk().getData().getDocumento() + "\n"
                        + "Plan: " + resultado.getRespuestaOk().getData().getPlan());
                int opcion = JOptionPane.showOptionDialog(null, "Posee una orden PreAutorizada?", "Asunt PreAutorización", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                        new Object[]{"Si", "No", "Cancelar"}, "No"); // dinde quieres que se posicione el selector
                ////preautorizacion
                if (opcion == 0) {//si                   
                    new AsuntPreAutorizacion(null, true).setVisible(true);
                    System.out.println("numeroPreautorizacion:"+numeroPreautorizacion);
                    System.out.println("Login.cuit:"+Login.cuit);
                    ConsultarRequest preautorizacion = new ConsultarRequest();
                    preautorizacion.setTipo_identificacion(tipo);
                    preautorizacion.setIdentificacion_afiliado(txtafiliado.getText());
                    preautorizacion.setN_autorizacion(numeroPreautorizacion);
                    preautorizacion.setCuit_efector(Login.cuit);

                    try {
                        ConsultarResultado consulta = client.consultar(preautorizacion);
                        if (consulta.isExito()) {//no entra                                
                            System.out.println("N° autorización: " + consulta.getRespuestaOk().getN_autorizacion());
                            System.out.println("Afiliado: " + consulta.getRespuestaOk().getNombre_afiliado());
                            System.out.println("Prestador: " + consulta.getRespuestaOk().getNombre_prestador());
                            System.out.println("practicas:" + consulta.getRespuestaOk().getPracticas().size());
                            practicasAsunt = consulta.getRespuestaOk().getPracticas();
                            JOptionPane.showMessageDialog(null, "N° autorización: " + consulta.getRespuestaOk().getN_autorizacion() + "\nPrestador: " + consulta.getRespuestaOk().getNombre_prestador() + "\nAfiliado: " + consulta.getRespuestaOk().getNombre_afiliado());
                            habilitado = "OK";
                            dispose();
                        } else {
                            cursor2();
                            System.out.println("Mensaje error 352: " + consulta.getRespuestaError().getMensaje());
                            habilitado = "NO";
                            JOptionPane.showMessageDialog(null, "Mensaje error: " + consulta.getRespuestaError().getMensaje());
                        }
                    } catch (IOException ex) {
                        cursor2();
                        Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (AsuntApiException ex) {
                        cursor2();
                        Logger.getLogger(MainL.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    dispose();
                }
            } else {
                cursor2();
                habilitado = "ERROR";
                System.out.println("Mensaje: " + resultado.getRespuestaError().getMensaje());
                JOptionPane.showMessageDialog(null, "Mensaje: " + resultado.getRespuestaError().getMensaje());
            }

        } catch (AsuntApiException e) {
            System.out.println("Error ASUNT: " + e.getMessage());
            cursor2();
        } catch (Exception e) {
            cursor2();
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnaceptarActionPerformed

    private void txtafiliadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtafiliadoActionPerformed
        txtafiliado.transferFocus();
    }//GEN-LAST:event_txtafiliadoActionPerformed

    private void cbotipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotipoActionPerformed

    }//GEN-LAST:event_cbotipoActionPerformed

    private void cbotipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbotipoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbotipo.transferFocus();
        }
    }//GEN-LAST:event_cbotipoKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceptar;
    private javax.swing.JComboBox cbotipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtafiliado;
    // End of variables declaration//GEN-END:variables
}
