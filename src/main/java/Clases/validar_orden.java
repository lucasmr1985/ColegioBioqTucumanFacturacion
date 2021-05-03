
package Clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import static java.sql.Types.INTEGER;
import javax.swing.JOptionPane;

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
        int numeroOrden=0;
       
        if (!numero_afiliado.equals("") && !dni_afiliado.equals("") && !numero_afiliado.equals("")) {
            if (cantidad_practicas != 0) {
                try {
                    CallableStatement SP_cargar_orden = null;
                    SP_cargar_orden = cn.prepareCall("{CALL cargar_prueba (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    SP_cargar_orden.setInt(1, periodo);//_periodo
                    SP_cargar_orden.setString(2, nombre_afiliado);//_nombre
                    SP_cargar_orden.setString(3, dni_afiliado);//_dni
                    SP_cargar_orden.setString(4, numero_afiliado);//_numero_afiliado
                    SP_cargar_orden.setInt(5, matricula_presc);//_matricula
                    SP_cargar_orden.setString(6, numero_orden);//_numero_orden
                    SP_cargar_orden.setString(7, fecha_orden);//_fecha_realizacion
                    SP_cargar_orden.setDouble(8, Redondear(total_orden));//_total
                    SP_cargar_orden.setInt(9, estado);//_estado
                    SP_cargar_orden.setString(10, fecha_carga);//_fecha
                    SP_cargar_orden.setString(11, hora_carga);//_hora
                    SP_cargar_orden.setString(12, ip);//_ip
                    SP_cargar_orden.setInt(13, id_obrasocial);//_id_obrasocial
                    SP_cargar_orden.setInt(14, id_usuario);//_id_colegiado
                    SP_cargar_orden.setString(15, null);//_id_validador
                    SP_cargar_orden.setInt(16, cantidad_practicas);//_cantidad
                    System.out.println("practicas " + practicas);
                    SP_cargar_orden.setString(17, practicas);//_codigo_nbu
                    SP_cargar_orden.registerOutParameter("_transaccion",INTEGER);//_transaccion
                    SP_cargar_orden.setDouble(19, coseguro);//_coseguro
                    SP_cargar_orden.setString(20, fecha_coseguro);//_fecha_coseguro
                    SP_cargar_orden.setInt(21, tipo_orden);//_tipo
                    SP_cargar_orden.setString(22, observacion);//_observacion
                    SP_cargar_orden.setString(23, plan_ss);//_codigo_plan
                    SP_cargar_orden.setString(24, coseguro_ss);//_coseguro_practicas
                    SP_cargar_orden.setString(25, fechaDate);//_fechaDate
                    boolean respuesta = SP_cargar_orden.execute();
                    numeroOrden = SP_cargar_orden.getInt("_transaccion");
                    
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
            return numeroOrden;
        } else {
            return 0;
        }
    }
    /*
   IN _periodo INT (6),
IN _nombre VARCHAR (100),
IN _dni VARCHAR (40),
IN _numero_afiliado VARCHAR (40),
IN _matricula VARCHAR (40),
IN _numero_orden VARCHAR (40),
IN _fecha_realizacion VARCHAR(10),
IN _total VARCHAR (40),
IN _estado INT (1),
IN _fecha VARCHAR (10),
IN _hora VARCHAR (8),
IN _ip VARCHAR (40),
IN _id_obrasocial INT (11),
IN _id_colegiado INT (11),
IN _id_validador INT (11),
IN _cantidad INT (2),
IN _codigo_nbu VARCHAR (9000),
OUT _transaccion INT(11),
IN _coseguro VARCHAR (20),
IN _fecha_coseguro VARCHAR (10),
IN _tipo INT (1),
IN _observacion VARCHAR (500),
IN _codigo_plan VARCHAR (255),
IN _coseguro_practicas VARCHAR (255),
IN _fechaDate DATE)
    
    */
    

    public double Redondear(double numero) {
        return Math.rint(numero * 100) / 100;
    }
}
