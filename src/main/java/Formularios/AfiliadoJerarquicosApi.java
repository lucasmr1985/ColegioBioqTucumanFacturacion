package Formularios;

import Clases.ConexionMariaDB;
import ClienteJerarquicos.JerarquicosApiException;
import ClienteJerarquicos.JerarquicosAuthClient;
import ClienteJerarquicos.SocioClient;
import ClienteJerarquicos.SocioResponse;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static Formularios.Login.id_usuario;

/**
 * Prueba de la nueva integración "API External Web Service" de Jerárquicos
 * Salud (OAuth2 + REST), en paralelo a la integración SOAP existente en
 * {@link AfiliadoJerarquicos}.
 *
 * Al validar: pide/renueva el access_token con {@link JerarquicosAuthClient},
 * lo registra en tokens_ws, y valida al socio con
 * {@link SocioClient#validarCoberturaPorSocio}.
 */
public class AfiliadoJerarquicosApi extends javax.swing.JDialog {

    private static final int ID_OBRASOCIAL_JERARQUICOS = 37701;

    public static String habilitado = "", nombreafiliado = "", dni = "", Codigo_afiliado = "";
    public static int NumeroSocio, NumeroOrden;

    String fecha = "", hora = "";

    public AfiliadoJerarquicosApi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Jerárquicos (API)");
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargarfecha();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtafiliado = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        txtToken = new javax.swing.JTextField();
        btnValidar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese el numero de socio y el Token", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Numero:");

        txtafiliado.setForeground(new java.awt.Color(0, 102, 204));
        try {
            txtafiliado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("######/##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtafiliado.setToolTipText("Ingresar el numero sin la barra (XXXXXXXXX)");
        txtafiliado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtafiliado.setNextFocusableComponent(txtToken);
        txtafiliado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtafiliadoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Token:");

        txtToken.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtToken.setForeground(new java.awt.Color(0, 102, 204));
        txtToken.setToolTipText("Cadena alfanumérica de 6 caracteres que el socio le dicta al profesional (reemplaza su firma)");
        txtToken.setNextFocusableComponent(btnValidar);
        txtToken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTokenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtToken, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtToken, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnValidar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnValidar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void cargarfecha() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HHmmss");
        Date currentDate = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);
        fecha = formatoFecha.format(currentDate);
        hora = formatoHora.format(currentDate);
    }

    void cursor() {
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        this.pack();
    }

    void cursor2() {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.pack();
    }

    /**
     * Registra en tokens_ws el access_token generado, junto con la obra
     * social y el colegiado que lo pidió (mismo patrón que
     * {@code AfiliadoSancorHL7.insertToken}).
     */
    int insertToken(String token, boolean permiso, String mensaje) {
        int insert = 0;
        String SQL = "INSERT INTO tokens_ws(token, tiene_permiso,mensaje,id_obrasocial,id_colegiado,fecha,hora)"
                + "VALUES(?,?,?,?,?,?,?)";

        ConexionMariaDB maria = new ConexionMariaDB();
        Connection cn = maria.Conectar();
        try {
            PreparedStatement st = cn.prepareStatement(SQL);
            st.setString(1, token);
            st.setBoolean(2, permiso);
            st.setString(3, mensaje);
            st.setInt(4, ID_OBRASOCIAL_JERARQUICOS);
            st.setInt(5, id_usuario);
            st.setString(6, fecha);
            st.setString(7, hora);
            insert = st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos...");
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        System.out.println("insertToken tokens_ws: " + insert);
        return insert;
    }

    private void btnValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarActionPerformed
        if (txtafiliado.getText().length() != 9) {
            JOptionPane.showMessageDialog(null, "Número de afiliado erroneo...");
            return;
        }

        cursor();
        try {
            NumeroSocio = Integer.valueOf(txtafiliado.getText().substring(0, 6));
            NumeroOrden = Integer.valueOf(txtafiliado.getText().substring(7, 9));
            System.out.println("socio " + NumeroSocio + " orden " + NumeroOrden);

            JerarquicosAuthClient authClient = new JerarquicosAuthClient();
            String accessToken = authClient.obtenerAccessToken();
            System.out.println("access_token obtenido: " + accessToken);

            insertToken(accessToken, true, "Token generado OK");

            String fechaReferencia = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            //String fechaReferencia = new SimpleDateFormat("2026-08-06").format(new Date());
            String tokenSocio = txtToken.getText(); // cadena de 6 caracteres que dicta el socio, no confundir con el access_token OAuth2
            SocioClient socioClient = new SocioClient();
            SocioResponse respuesta = socioClient.validarCoberturaPorSocio(fechaReferencia, NumeroSocio, NumeroOrden, tokenSocio, accessToken);

            if (respuesta.isValido()) {
                String apellido = respuesta.getApellido() == null ? "" : respuesta.getApellido().trim();
                String nombre = respuesta.getNombre() == null ? "" : respuesta.getNombre().trim();
                nombreafiliado = (apellido + " " + nombre).trim();
                dni = respuesta.getNumeroDocumento();
                Codigo_afiliado = txtafiliado.getText();
                if (respuesta.getPlanSocioVigente() != null) {
                    MainL.plan = respuesta.getPlanSocioVigente().getPlanId();
                }
                habilitado = "OK";
                System.out.println(nombreafiliado);
                System.out.println(dni);
                System.out.println(MainL.plan);
                cursor2();
                JOptionPane.showMessageDialog(null, "Afiliado habilitado: " + nombreafiliado);
                this.dispose();
            } else {
                habilitado = "ERROR";
                cursor2();
                JOptionPane.showMessageDialog(null, "El socio no es válido para la fecha de referencia.");
                txtafiliado.requestFocus();
            }

        } catch (JerarquicosApiException ex) {
            habilitado = "ERROR";
            Logger.getLogger(AfiliadoJerarquicosApi.class.getName()).log(Level.SEVERE, null, ex);
            cursor2();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (Exception ex) {
            habilitado = "ERROR";
            Logger.getLogger(AfiliadoJerarquicosApi.class.getName()).log(Level.SEVERE, null, ex);
            cursor2();
            JOptionPane.showMessageDialog(null, "Error de conexión con el servidor de Jerárquicos Salud (API)");
        }
    }//GEN-LAST:event_btnValidarActionPerformed

    private void txtafiliadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtafiliadoActionPerformed
        txtafiliado.transferFocus();
    }//GEN-LAST:event_txtafiliadoActionPerformed

    private void txtTokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTokenActionPerformed
        txtToken.transferFocus();
    }//GEN-LAST:event_txtTokenActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnValidar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JFormattedTextField txtafiliado;
    private javax.swing.JTextField txtToken;
    // End of variables declaration//GEN-END:variables

}
