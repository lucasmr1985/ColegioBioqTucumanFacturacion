/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class validar_orden {

    public int valida(
            int periodo,
            String nombre_afiliado,
            String dni_afiliado,
            String numero_afiliado,
            int matricula_presc,
            String numero_orden,
            String fecha_orden,
            double total_orden,
            String fecha_carga,
            String hora_carga,
            String ip,
            int id_obrasocial,
            int id_usuario,
            int cantidad_practicas,
            String practicas,
            double coseguro,
            String fecha_coseguro,
            int tipo_orden,
            String observacion,
            String plan_ss,
            String coseguro_ss,
            int estado,
            String fechaDate) {
        ///////
        ConexionMariaDB mysql = new ConexionMariaDB();
        Connection cn = mysql.Conectar();
        int bandera = 0;
       
        if (!numero_afiliado.equals("") && !dni_afiliado.equals("") && !numero_afiliado.equals("")) {
            if (cantidad_practicas != 0) {
                try {
                    CallableStatement SP_cargar_orden = null;
                    SP_cargar_orden = cn.prepareCall("{CALL cargar_prueba (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    SP_cargar_orden.setInt(1, periodo);
                    SP_cargar_orden.setString(2, nombre_afiliado);
                    SP_cargar_orden.setString(3, dni_afiliado);
                    SP_cargar_orden.setString(4, numero_afiliado);
                    SP_cargar_orden.setInt(5, matricula_presc);
                    SP_cargar_orden.setString(6, numero_orden);
                    SP_cargar_orden.setString(7, fecha_orden);
                    SP_cargar_orden.setDouble(8, Redondear(total_orden));
                    SP_cargar_orden.setInt(9, estado);
                    SP_cargar_orden.setString(10, fecha_carga);
                    SP_cargar_orden.setString(11, hora_carga);
                    SP_cargar_orden.setString(12, ip);
                    SP_cargar_orden.setInt(13, id_obrasocial);
                    SP_cargar_orden.setInt(14, id_usuario);
                    SP_cargar_orden.setString(15, null);
                    SP_cargar_orden.setInt(16, cantidad_practicas);
                    System.out.println("practicas " + practicas);
                    SP_cargar_orden.setString(17, practicas);
                    SP_cargar_orden.setString(18, "@_transaccion");
                    SP_cargar_orden.setDouble(19, coseguro);
                    SP_cargar_orden.setString(20, fecha_coseguro);
                    SP_cargar_orden.setInt(21, tipo_orden);
                    SP_cargar_orden.setString(22, observacion);
                    SP_cargar_orden.setString(23, plan_ss);
                    SP_cargar_orden.setString(24, coseguro_ss);
                    SP_cargar_orden.setString(25, fechaDate);
                    boolean respuesta = SP_cargar_orden.execute();
                    if (respuesta == true) {
                        bandera = 1;
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo grabar la orden en nuestro servidor");
                        bandera = 0;
                    }
                    cn.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                    JOptionPane.showMessageDialog(null, "No se pudo Grabar la orden en nuestro servidor");
                    bandera = 0;
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay practicas en la tabla...");
                bandera = 0;
            }
        }
        if (bandera == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public double Redondear(double numero) {
        return Math.rint(numero * 100) / 100;
    }
}
