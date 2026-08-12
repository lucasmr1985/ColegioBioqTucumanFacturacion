/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;

import java.util.ArrayList;
import java.util.List;

public class AutorizarRequest {

    private String tipo_identificacion_afiliado;
    private String identificacion_afiliado;
    private String nomenclador;
    private String cuit_efector;
    private List<PracticaRequest> practicas;
    private String matricula_profesional;

    public AutorizarRequest() {
        this.practicas = new ArrayList<PracticaRequest>();
    }

    public String getCuit_efector() {
        return cuit_efector;
    }

    public void setCuit_efector(String cuit_efector) {
        this.cuit_efector = cuit_efector;
    }

    public String getTipo_identificacion_afiliado() {
        return tipo_identificacion_afiliado;
    }

    public void setTipo_identificacion_afiliado(String tipo_identificacion_afiliado) {
        this.tipo_identificacion_afiliado = tipo_identificacion_afiliado;
    }

    public String getIdentificacion_afiliado() {
        return identificacion_afiliado;
    }

    public void setIdentificacion_afiliado(String identificacion_afiliado) {
        this.identificacion_afiliado = identificacion_afiliado;
    }

    public String getNomenclador() {
        return nomenclador;
    }

    public void setNomenclador(String nomenclador) {
        this.nomenclador = nomenclador;
    }

    public List<PracticaRequest> getPracticas() {
        return practicas;
    }

    public void setPracticas(List<PracticaRequest> practicas) {
        this.practicas = practicas;
    }

    public String getMatricula_profesional() {
        return matricula_profesional;
    }

    public void setMatricula_profesional(String matricula_profesional) {
        this.matricula_profesional = matricula_profesional;
    }

    public void addPractica(PracticaRequest practica) {
        if (this.practicas == null) {
            this.practicas = new ArrayList<PracticaRequest>();
        }
        this.practicas.add(practica);
    }
}