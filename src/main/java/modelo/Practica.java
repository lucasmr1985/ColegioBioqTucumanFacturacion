
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;


public class Practica {
    
    private int id;
    private int codigo;
    private int codigoFacturacion;
    private String determinacion;
    private String instrucciones;
    private double precio1;
    private double precio2;
    private double precio3;
    private double precio4;
    private int tiempoProcesamiento;
    private double precioTotal;
    private int idDetalleORden;
    private int tipoPractica;

    public Practica(int id, int codigo, int codigoFacturacion, String determinacion, String instrucciones, double precio1, double precio2, double precio3, double precio4, int tiempoProcesamiento, double precioTotal) {
        this.id = id;
        this.codigo = codigo;
        this.codigoFacturacion = codigoFacturacion;
        this.determinacion = determinacion;
        this.instrucciones = instrucciones;
        this.precio1 = precio1;
        this.precio2 = precio2;
        this.precio3 = precio3;
        this.precio4 = precio4;
        this.tiempoProcesamiento = tiempoProcesamiento;
        this.precioTotal = precioTotal;
    }

    public Practica(int codigo, int codigoFacturacion, String determinacion, double precioTotal, int idDetalleORden) {
        this.codigo = codigo;
        this.codigoFacturacion = codigoFacturacion;
        this.determinacion = determinacion;
        this.precioTotal = precioTotal;
        this.idDetalleORden = idDetalleORden;
    }

    
    public Practica(int codigo, int codigoFacturacion, String determinacion, double precioTotal) {
        this.codigo = codigo;
        this.codigoFacturacion = codigoFacturacion;
        this.determinacion = determinacion;
        this.precioTotal = precioTotal;
    }
    
    public Practica(int codigo, int codigoFacturacion, String determinacion, int tipoPractica, double precioTotal) {
        this.codigo = codigo;
        this.codigoFacturacion = codigoFacturacion;
        this.determinacion = determinacion;
        this.precioTotal = precioTotal;
        this.tipoPractica = tipoPractica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoFacturacion() {
        return codigoFacturacion;
    }

    public void setCodigoFacturacion(int codigoFacturacion) {
        this.codigoFacturacion = codigoFacturacion;
    }        

    public String getDeterminacion() {
        return determinacion;
    }

    public void setDeterminacion(String determinacion) {
        this.determinacion = determinacion;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public double getPrecio1() {
        return precio1;
    }

    public void setPrecio1(double precio1) {
        this.precio1 = precio1;
    }

    public double getPrecio2() {
        return precio2;
    }

    public void setPrecio2(double precio2) {
        this.precio2 = precio2;
    }

    public double getPrecio3() {
        return precio3;
    }

    public void setPrecio3(double precio3) {
        this.precio3 = precio3;
    }

    public double getPrecio4() {
        return precio4;
    }

    public void setPrecio4(double precio4) {
        this.precio4 = precio4;
    }

    public int getTiempoProcesamiento() {
        return tiempoProcesamiento;
    }

    public void setTiempoProcesamiento(int tiempoProcesamiento) {
        this.tiempoProcesamiento = tiempoProcesamiento;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getTipoPractica() {
        return tipoPractica;
    }

    public void setTipoPractica(int tipoPractica) {
        this.tipoPractica = tipoPractica;
    }
    
    

    @Override
    public String toString() {
        return codigo + " - " + determinacion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.codigo;
        hash = 73 * hash + Objects.hashCode(this.determinacion);
        return hash;
    }

    public int getIdDetalleORden() {
        return idDetalleORden;
    }

    public void setIdDetalleORden(int idDetalleORden) {
        this.idDetalleORden = idDetalleORden;
    }    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Practica other = (Practica) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!Objects.equals(this.determinacion, other.determinacion)) {
            return false;
        }
        return true;
    }
        
    
    public static void cargarPracticas(Connection connection, ArrayList<Practica> lista, int id_obrasocial) {
        
         try {
            Statement instruccion = connection.createStatement();
            ResultSet rs;
            int i=0;
            rs = instruccion.executeQuery("SELECT codigo_practica,preciototal,determinacion, codigo_fac_practicas_obrasocial, tipo_practica FROM obrasocial_tiene_practicasnbu  WHERE id_obrasocial=" + id_obrasocial);
            while (rs.next()) {
                
                lista.add(new Practica(rs.getInt("codigo_practica"), rs.getInt("codigo_fac_practicas_obrasocial"), rs.getString("determinacion"), rs.getDouble("preciototal")));
                               
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static Practica buscarPractica(String nombrePractica, ArrayList<Practica> lista) {
        Practica resultado = null;
        
        for (Practica practica : lista) {
            if ((practica.getCodigo() + " - " + practica.getDeterminacion()).equals(nombrePractica)) {
                resultado = practica;
                break;
            }
        }
        return resultado;
    }
    
    
    
    public static boolean buscarPracticaBool(String cadenaPractica, ArrayList<Practica> lista) {
        boolean resultado = false;
        for (Practica practica : lista) {
            if ((practica.getCodigo() + " - " + practica.getDeterminacion()).equals(cadenaPractica)) {
                resultado = true;
            }
        }
        return resultado;
    }
    
    public static void cargarPracticasDeOrden(Connection connection, ArrayList<Practica> lista, int idOrden) {
        
         try {
            Statement instruccion = connection.createStatement();
            ResultSet rs;
            rs = instruccion.executeQuery("SELECT cod_practica, nombre_practica, precio_practica, cod_practica_fac, id_detalle FROM vista_detalle_ordenes_pami WHERE id_orden=" + idOrden);
            while (rs.next()) {
                
                lista.add(new Practica(rs.getInt("cod_practica"), rs.getInt("cod_practica_fac"), rs.getString("nombre_practica"), rs.getDouble("precio_practica"), rs.getInt("id_detalle")));
            
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void cargarPracticasIosfa(Connection connection, ArrayList<Practica> lista, int id_obrasocial) {
        
         try {
            Statement instruccion = connection.createStatement();
            ResultSet rs;
            int i=0;
            rs = instruccion.executeQuery("SELECT codigo_practica,preciototal,determinacion, codigo_fac_practicas_obrasocial, tipo_practica FROM obrasocial_tiene_practicasnbu  WHERE id_obrasocial=" + id_obrasocial);
            while (rs.next()) {
                
                lista.add(new Practica(rs.getInt("codigo_practica"), rs.getInt("codigo_fac_practicas_obrasocial"), rs.getString("determinacion"), rs.getInt("tipo_practica"), rs.getDouble("preciototal")));
                               
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}