/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteJerarquicos;

public class PlanSocioVigente {

    private int planId;
    private String planNombre;
    private double planPorcentajeReconoceFarmacia;
    private String fechaAlta;
    private boolean esPmo;

    public PlanSocioVigente() {
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanNombre() {
        return planNombre;
    }

    public void setPlanNombre(String planNombre) {
        this.planNombre = planNombre;
    }

    public double getPlanPorcentajeReconoceFarmacia() {
        return planPorcentajeReconoceFarmacia;
    }

    public void setPlanPorcentajeReconoceFarmacia(double planPorcentajeReconoceFarmacia) {
        this.planPorcentajeReconoceFarmacia = planPorcentajeReconoceFarmacia;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean isEsPmo() {
        return esPmo;
    }

    public void setEsPmo(boolean esPmo) {
        this.esPmo = esPmo;
    }
}
