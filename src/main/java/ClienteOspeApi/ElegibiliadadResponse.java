package ClienteOspeApi;

import ClienteSwissMedicalApi.*;

public class ElegibiliadadResponse {

    private String ApellidoBeneficiario;
    private String CondicionIVA;
    private String HistoriaClinica;
    private String NombreBeneficiario;
    private String NumeroAfiliado;
    private String NumeroDocumentoBeneficiario;
    private String PlanBeneficiario;
    private String TipoDocumentoBeneficiario;

    public ElegibiliadadResponse() {
    }

   
    // Getter Methods 
    public String getApellidoBeneficiario() {
        return ApellidoBeneficiario;
    }

    public void setApellidoBeneficiario(String ApellidoBeneficiario) {
        this.ApellidoBeneficiario = ApellidoBeneficiario;
    }

    public String getCondicionIVA() {
        return CondicionIVA;
    }

    public void setCondicionIVA(String CondicionIVA) {
        this.CondicionIVA = CondicionIVA;
    }

    public String getHistoriaClinica() {
        return HistoriaClinica;
    }

    public void setHistoriaClinica(String HistoriaClinica) {
        this.HistoriaClinica = HistoriaClinica;
    }

    public String getNombreBeneficiario() {
        return NombreBeneficiario;
    }

    public void setNombreBeneficiario(String NombreBeneficiario) {
        this.NombreBeneficiario = NombreBeneficiario;
    }

    public String getNumeroAfiliado() {
        return NumeroAfiliado;
    }

    public void setNumeroAfiliado(String NumeroAfiliado) {
        this.NumeroAfiliado = NumeroAfiliado;
    }

    public String getNumeroDocumentoBeneficiario() {
        return NumeroDocumentoBeneficiario;
    }

    public void setNumeroDocumentoBeneficiario(String NumeroDocumentoBeneficiario) {
        this.NumeroDocumentoBeneficiario = NumeroDocumentoBeneficiario;
    }

    public String getPlanBeneficiario() {
        return PlanBeneficiario;
    }

    public void setPlanBeneficiario(String PlanBeneficiario) {
        this.PlanBeneficiario = PlanBeneficiario;
    }

    public String getTipoDocumentoBeneficiario() {
        return TipoDocumentoBeneficiario;
    }

    public void setTipoDocumentoBeneficiario(String TipoDocumentoBeneficiario) {
        this.TipoDocumentoBeneficiario = TipoDocumentoBeneficiario;
    }

    public void muestraRespuesta() {
        System.out.println("ApellidoBeneficiario=" + this.getApellidoBeneficiario());
        System.out.println("CondicionIVA=" + this.getCondicionIVA());
        System.out.println("HistoriaClinica=" + this.getHistoriaClinica());
        System.out.println("NombreBeneficiario=" + this.getNombreBeneficiario());
        System.out.println("NumeroAfiliado=" + this.getNumeroAfiliado());
        System.out.println("NumeroDocumentoBeneficiario=" + this.getNumeroDocumentoBeneficiario());
        System.out.println("PlanBeneficiario=" + this.getPlanBeneficiario());
        System.out.println("TipoDocumentoBeneficiario=" + this.getTipoDocumentoBeneficiario());        
    }
}