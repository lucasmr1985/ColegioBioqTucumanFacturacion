package Formularios;

import static Clases.HiloInicio.contadorobrasocial;
import static Clases.HiloInicio.obrasocial;
import static Clases.HiloInicio.idobrasocial;
import com.mxrck.autocompleter.TextAutoCompleter;
import javax.swing.ImageIcon;

public class ImprimirObraSocial extends javax.swing.JDialog {

    public ImprimirObraSocial(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarobrasocial();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logocbt.png")).getImage());
        this.setTitle("Imprimir");
        this.setLocationRelativeTo(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                MainL.ObraSocial = "";
            }
        });
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtobrasocial = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resumen de una Obra Social", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Obra Social:");

        txtobrasocial.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtobrasocial.setForeground(new java.awt.Color(0, 102, 204));
        txtobrasocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtobrasocialActionPerformed(evt);
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
                .addComponent(txtobrasocial, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtobrasocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtobrasocialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtobrasocialActionPerformed
        String cod = "", nom = "", cadena = txtobrasocial.getText();
        int i=0,k = 0, j = 1,id_obra_social=0;
        ///cod/// 0000 - aaaaaaaaa
        while (k < cadena.length()) {
            if (String.valueOf(cadena.charAt(k)).equals("-")) {
                j = k + 2;
                k = cadena.length();
            } else {
                cod = cod + cadena.charAt(k);
            }
            k++;
        }
        ///nom/// 0000 - aaaaaaaaa
        while (j < cadena.length()) {
            nom = nom + cadena.charAt(j);
            j++;
        }
        while (i < contadorobrasocial) {
            if (cadena.equals(obrasocial[i])) {               
                id_obra_social = idobrasocial[i];                
                break;
            }
            i++;
        }
        MainL.ObraSocial = nom;
        MainL.CodObra=cod;
        MainL.idobraimprime=id_obra_social;
        dispose();
    }//GEN-LAST:event_txtobrasocialActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtobrasocial;
    // End of variables declaration//GEN-END:variables
}
