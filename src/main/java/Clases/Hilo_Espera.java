package Clases;

import java.awt.Color;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Hilo_Espera implements Runnable{
    String texto;
    JPanel panel;
    JDialog panelDialogo;    
    JLabel etiqueta;
    String path = "/Imagenes/loadingCico.gif";
    URL url = this.getClass().getResource(path);  
    ImageIcon icon = new ImageIcon(url); 
    
    public Hilo_Espera(String s){
        
    }

    public void run(){
        panelDialogo = new JDialog();
        panelDialogo.setModal(true);
        //etiqueta.setOpaque(true);
        panelDialogo.setSize(208,155);
        panelDialogo.setUndecorated(true);
        panelDialogo.setBackground(new Color(0,0,0,0));
        panelDialogo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        etiqueta = new JLabel();
        
        
        etiqueta.setIcon(icon);
        
        panelDialogo.setLocationRelativeTo(null);
        panelDialogo.setTitle("Procesando datos...");
        
        panelDialogo.add(etiqueta,new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,-1,-1));
        
        panelDialogo.setVisible(true);
        panelDialogo.toFront();
        panelDialogo.getContentPane();        
    }
    public void stop(){
        panelDialogo.dispose();
    }
}