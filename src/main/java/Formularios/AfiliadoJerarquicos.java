package Formularios;

import ClienteJerarquicos.CriterioElegibilidadSocioServiciosSalud;
import ClienteJerarquicos.ObjectFactory;
import ClienteJerarquicos.RespuestaBase;
import ClienteJerarquicos.SolicitudElegibilidadSocioServiciosSalud;
import java.awt.Cursor;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import jakarta.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class AfiliadoJerarquicos extends javax.swing.JDialog {

    public static String habilitado = "", nombreafiliado = "", dni = "", Codigo_afiliado = "";
    public static int NumeroSocio, NumeroOrden;
    String idmsj = "", fecha = "", pasaporte = "", mensaje = "", respuesta = "";
    XMLGregorianCalendar date2;

    public AfiliadoJerarquicos(java.awt.Frame parent, boolean modal) throws DatatypeConfigurationException {
        super(parent, modal);
        initComponents();
        this.setTitle("Jerárquicos");
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargarfecha();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtafiliado = new javax.swing.JFormattedTextField();
        btnValidar = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese el numero de afiliado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(153, 153, 153))); // NOI18N

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
        txtafiliado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtafiliadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtafiliado, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    void cargarfecha() throws DatatypeConfigurationException {
        SimpleDateFormat formatoTiempo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        java.util.Date currentDate1 = new java.util.Date();
        GregorianCalendar calendar1 = new GregorianCalendar();
        calendar1.setTime(currentDate1);
        fecha = formatoTiempo.format(currentDate1);
        //JOptionPane.showMessageDialog(null, fecha);

        GregorianCalendar c = new GregorianCalendar();
        c.setTime(currentDate1);
        date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        System.out.println(date2);
    }

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


    private void btnValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarActionPerformed
        try { // Call Web Service Operation
            //999999/99
            if (txtafiliado.getText().length() == 9) {
                NumeroSocio = Integer.valueOf(txtafiliado.getText().substring(0, 6));
                NumeroOrden = Integer.valueOf(txtafiliado.getText().substring(7, 9));
                System.out.println("socio " + NumeroSocio + " " + "Orden " + NumeroOrden);
                ObjectFactory factory = new ObjectFactory();
                JAXBElement<Integer> idDni = factory.createCriterioElegibilidadSocioServiciosSaludIdTipoDocumento(null);
                JAXBElement<Integer> Dni = factory.createCriterioElegibilidadSocioServiciosSaludNumeroDocumento(null);
                JAXBElement<Integer> Socio = factory.createCriterioElegibilidadSocioServiciosSaludNumeroSocio(NumeroSocio);
                JAXBElement<Integer> Orden = factory.createCriterioElegibilidadSocioServiciosSaludOrdenSocio(NumeroOrden);
                JAXBElement<String> Track = factory.createCriterioElegibilidadSocioServiciosSaludTrack(null);

                CriterioElegibilidadSocioServiciosSalud datos = new CriterioElegibilidadSocioServiciosSalud();
                datos.setFechaDeReferencia(date2);
                datos.setIdTipoDocumento(idDni);
                datos.setNumeroDocumento(Dni);
                datos.setNumeroSocio(Socio);
                datos.setOrdenSocio(Orden);
                datos.setTrack(Track);

                JAXBElement<CriterioElegibilidadSocioServiciosSalud> criterio = factory.createSolicitudElegibilidadSocioServiciosSaludCriterioElegibilidadSocioServiciosSalud(datos);

                ClienteJerarquicos.SolicitudElegibilidadSocioServiciosSalud solicitud = new SolicitudElegibilidadSocioServiciosSalud();
                solicitud.setCriterioElegibilidadSocioServiciosSalud(criterio);

                ClienteJerarquicos.Servicio service = new ClienteJerarquicos.Servicio();
                ClienteJerarquicos.IServicioPublico port = service.getBasicHttpBindingIServicioPublico();
                ClienteJerarquicos.RespuestaBase result = port.determinarElegibilidadSocioServiciosSalud(solicitud);

                JAXBElement<RespuestaBase> respuestajxe = factory.createDeterminarElegibilidadSocioServiciosSaludResponseDeterminarElegibilidadSocioServiciosSaludResult(result);

                ClienteJerarquicos.DeterminarElegibilidadSocioServiciosSaludResponse resultado = new ClienteJerarquicos.DeterminarElegibilidadSocioServiciosSaludResponse();
                resultado.setDeterminarElegibilidadSocioServiciosSaludResult(respuestajxe);
                JAXBElement<String> respuestaString = result.getDTOSerializado();
                String respuesta = resultado.getDeterminarElegibilidadSocioServiciosSaludResult().getValue().getResultado().value();
                System.out.println("Result2 = " + respuesta); // 0 = Fallo | 1 = Exito => type Enum
                if (respuestaString.getValue() != null) {
                    //Respuesta = {"$id":"1","$type":"ServiciosJs.DTO.Socio.ElegibilidadSocioServiciosSaludDTO, ServiciosJs.DTO","Apellido":"Curis                              ","EsAutonomo":false,"EstadoCivil":{"$id":"2","$type":"ServiciosJs.DTO.EstadoCivil.EstadoCivilDTO, ServiciosJs.DTO","Descripcion":"Soltero/a           ","Id":2},"FechaNacimiento":"1973-04-03T00:00:00","FechaReferencia":"2018-11-06T00:00:00-03:00","IdSocio":257663,"Nombre":"Sandra Romina                      ","Numero":87029,"NumeroDocumento":"23238109","Orden":0,"PlanVigente":{"$id":"3","$type":"ServiciosJs.DTO.PlanPorSocio.PlanPorSocioVigenteDTO, ServiciosJs.DTO","PlanEsAutonomo":false,"PlanId":4,"PlanNombre":"PMI 2000            ","PlanPorcentajeReconoceFarmacia":60.0},"ProgramasVigentes":{"$id":"4","$type":"System.Collections.Generic.List`1[[ServiciosJs.DTO.ProgramaSocio.ProgramaSocioVigenteDTO, ServiciosJs.DTO]], mscorlib","$values":[{"$id":"5","$type":"ServiciosJs.DTO.ProgramaSocio.ProgramaSocioVigenteDTO, ServiciosJs.DTO","FechaFin":"2019-02-20T00:00:00","FechaInicio":"2014-02-20T00:00:00","Id":81307,"IdPrograma":25,"ProgramaDescripcion":"Trastornos de la Fertilidad"}]},"Sexo":{"$id":"6","$type":"ServiciosJs.DTO.Sexo.SexoDTO, ServiciosJs.DTO","Descripcion":"Femenino","Id":2},"TipoDocumento":{"$id":"7","$type":"ServiciosJs.DTO.TipoDocumento.TipoDocumentoPlanoDTO, ServiciosJs.DTO","Descripcion":"DNI  ","Id":1},"Valido":true}
                    System.out.println("Respuesta = " + respuestaString.getValue()); // type string/                
                   // JOptionPane.showMessageDialog(null, "Afiliado habilitado");
                    int pos1 = respuestaString.getValue().indexOf("Apellido") + 11;
                    int pos2 = respuestaString.getValue().indexOf("EsAutonomo");
                    int pos3 = respuestaString.getValue().indexOf("Nombre") + 9;
                    int pos4 = respuestaString.getValue().indexOf("Numero");
                    int pos5 = respuestaString.getValue().indexOf("NumeroDocumento") + 18;
                    int pos6 = respuestaString.getValue().indexOf("Orden");
                    int pos7 = respuestaString.getValue().indexOf("PlanId") + 8;
                    int pos8 = respuestaString.getValue().indexOf(",\"PlanNombre");//"PlanEsAutonomo":false,"PlanId":4,"PlanNombre":
                    MainL.plan= Integer.valueOf(respuestaString.getValue().substring(pos7, pos8));
                    nombreafiliado = respuestaString.getValue().substring(pos1, pos2).replace("\"", "").replace(",", "") + " " + respuestaString.getValue().substring(pos3, pos4).replace("\"", "").replace(",", "");//"Apellido": + "Nombre":
                    dni = respuestaString.getValue().substring(pos5, pos6);//"NumeroDocumento":
                    dni = dni.replace("\"", "").replace(",", "");
                    Codigo_afiliado = txtafiliado.getText();
                    habilitado = "OK";
                    System.out.println(nombreafiliado);
                    System.out.println(dni);
                    System.out.println(MainL.plan);                    
                    this.dispose();
                } else {
                    habilitado = "ERROR";
                    System.out.println("Respuesta = " + respuestaString.getValue()); // type string
                    JOptionPane.showMessageDialog(null, "Error al querer validar al afiliado");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Número de afiliado erroneo...");
            }
        } catch (Exception ex) {
            Logger.getLogger(AfiliadoJerarquicos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error de conexión con el servidor de Jerarquicos Salud");
        }
    }//GEN-LAST:event_btnValidarActionPerformed

    public int getNumeroSocio() {
        return NumeroSocio;
    }

    @XmlElement
    public void setNumeroSocio(int NumeroSocio) {
        this.NumeroSocio = NumeroSocio;
    }

    private void txtafiliadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtafiliadoActionPerformed
        txtafiliado.transferFocus();
    }//GEN-LAST:event_txtafiliadoActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnValidar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JFormattedTextField txtafiliado;
    // End of variables declaration//GEN-END:variables

}
