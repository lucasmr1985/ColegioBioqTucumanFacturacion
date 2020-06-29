

package Clases;

public class camposobrasocial {
    String codigo, nombre, periodo, gastos, honorarios,importe,pacientes,ordenes;

    public camposobrasocial(String codigo, String nombre, String periodo, String gastos, String honorarios, String importe, String pacientes, String ordenes) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.periodo = periodo;
        this.gastos = gastos;
        this.honorarios = honorarios;
        this.importe = importe;
        this.pacientes = pacientes;
        this.ordenes = ordenes;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPeriodo() {
        return periodo;
    }

    public String getGastos() {
        return gastos;
    }

    public String getHonorarios() {
        return honorarios;
    }

    public String getImporte() {
        return importe;
    }

    public String getPacientes() {
        return pacientes;
    }

    public String getOrdenes() {
        return ordenes;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public void setGastos(String gastos) {
        this.gastos = gastos;
    }

    public void setHonorarios(String honorarios) {
        this.honorarios = honorarios;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public void setPacientes(String pacientes) {
        this.pacientes = pacientes;
    }

    public void setOrdenes(String ordenes) {
        this.ordenes = ordenes;
    }
    
}
