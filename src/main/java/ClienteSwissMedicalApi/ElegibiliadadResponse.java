package ClienteSwissMedicalApi;

public class ElegibiliadadResponse {

    private String planCodi;
    private String apeNom;
    private String transacAlta;
    private String pmi;
    private float edad;
    private float transac;
    private int rechaCabecera;
    private String icdDeno;
    private String gravado;
    private String rechaCabeDeno;
    private String sexo;
    private String leyimp;

    // Getter Methods 
    public String getPlanCodi() {
        return planCodi;
    }

    public String getApeNom() {
        return apeNom;
    }

    public String getTransacAlta() {
        return transacAlta;
    }

    public String getPmi() {
        return pmi;
    }

    public float getEdad() {
        return edad;
    }

    public float getTransac() {
        return transac;
    }

    public float getRechaCabecera() {
        return rechaCabecera;
    }

    public String getIcdDeno() {
        return icdDeno;
    }

    public String getGravado() {
        return gravado;
    }

    public String getRechaCabeDeno() {
        return rechaCabeDeno;
    }

    public String getSexo() {
        return sexo;
    }

    public String getLeyimp() {
        return leyimp;
    }

    // Setter Methods 
    public void setPlanCodi(String planCodi) {
        this.planCodi = planCodi;
    }

    public void setApeNom(String apeNom) {
        this.apeNom = apeNom;
    }

    public void setTransacAlta(String transacAlta) {
        this.transacAlta = transacAlta;
    }

    public void setPmi(String pmi) {
        this.pmi = pmi;
    }

    public void setEdad(float edad) {
        this.edad = edad;
    }

    public void setTransac(float transac) {
        this.transac = transac;
    }

    public void setRechaCabecera(int rechaCabecera) {
        this.rechaCabecera = rechaCabecera;
    }

    public void setIcdDeno(String icdDeno) {
        this.icdDeno = icdDeno;
    }

    public void setGravado(String gravado) {
        this.gravado = gravado;
    }

    public void setRechaCabeDeno(String rechaCabeDeno) {
        this.rechaCabeDeno = rechaCabeDeno;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setLeyimp(String leyimp) {
        this.leyimp = leyimp;
    }
    
    public void muestraRespuesta(){    
        System.out.println("planCodi=" + this.getPlanCodi());
        System.out.println("apeNom=" + this.getApeNom());
        System.out.println("pmi=" + this.getPmi());
        System.out.println("edad=" + this.getEdad());
        System.out.println("transac=" + this.getTransac());
        System.out.println("rechaCabecera=" + this.getRechaCabecera());
        System.out.println("icdDeno=" + this.getIcdDeno());
        System.out.println("gravado=" + this.getGravado());
        System.out.println("rechaCabeDeno=" + this.getRechaCabeDeno());
        System.out.println("sexo=" + this.getSexo());
        System.out.println("leyimp=" + this.getLeyimp());        
    }
}
