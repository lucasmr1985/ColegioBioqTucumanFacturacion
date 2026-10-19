/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteJerarquicos;

import java.util.List;

/**
 * Respuesta de GET Socio/ValidarCobertura y GET Socio/ValidarCoberturaConvenio.
 */
public class SocioResponse {

    private String apellido;
    private String nombre;
    private EstadoCivil estadoCivil;
    private String fechaNacimiento;
    private String fechaReferencia;
    private int idPersona;
    private int numeroSocio;
    private int ordenSocio;
    private String numeroDocumento;
    private PlanSocioVigente planSocioVigente;
    private Sexo sexo;
    private TipoDocumento tipoDocumento;
    private boolean valido;
    private List<Object> programaSocio;
    private boolean esAutonomo;

    public SocioResponse() {
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaReferencia() {
        return fechaReferencia;
    }

    public void setFechaReferencia(String fechaReferencia) {
        this.fechaReferencia = fechaReferencia;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(int numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public int getOrdenSocio() {
        return ordenSocio;
    }

    public void setOrdenSocio(int ordenSocio) {
        this.ordenSocio = ordenSocio;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public PlanSocioVigente getPlanSocioVigente() {
        return planSocioVigente;
    }

    public void setPlanSocioVigente(PlanSocioVigente planSocioVigente) {
        this.planSocioVigente = planSocioVigente;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public List<Object> getProgramaSocio() {
        return programaSocio;
    }

    public void setProgramaSocio(List<Object> programaSocio) {
        this.programaSocio = programaSocio;
    }

    public boolean isEsAutonomo() {
        return esAutonomo;
    }

    public void setEsAutonomo(boolean esAutonomo) {
        this.esAutonomo = esAutonomo;
    }
}
