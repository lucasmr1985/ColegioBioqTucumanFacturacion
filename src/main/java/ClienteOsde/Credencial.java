/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteOsde;

/**
 *
 * @author Lucas Robles
 */
public class Credencial {
    private String NumeroCredencial;
    private String Track;
    private String VersionCredencial;
    private String CondicionIVA;
    private String PlanCredencial;

    public Credencial() {
    }

    public String getNumeroCredencial() {
        return NumeroCredencial;
    }

    public void setNumeroCredencial(String NumeroCredencial) {
        this.NumeroCredencial = NumeroCredencial;
    }

    public String getTrack() {
        return Track;
    }

    public void setTrack(String Track) {
        this.Track = Track;
    }

    public String getVersionCredencial() {
        return VersionCredencial;
    }

    public void setVersionCredencial(String VersionCredencial) {
        this.VersionCredencial = VersionCredencial;
    }

    public String getCondicionIVA() {
        return CondicionIVA;
    }

    public void setCondicionIVA(String CondicionIVA) {
        this.CondicionIVA = CondicionIVA;
    }
    
     public String getPlanCredencial() {
        return PlanCredencial;
    }

    public void setPlanCredencial(String PlanCredencial) {
        this.PlanCredencial = PlanCredencial;
    }
}
