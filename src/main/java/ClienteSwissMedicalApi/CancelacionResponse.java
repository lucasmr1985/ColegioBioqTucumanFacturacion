package ClienteSwissMedicalApi;

import java.util.ArrayList;

public class CancelacionResponse {

    private Cabecera cabecera;
    private ArrayList<Object> detalle = new ArrayList<Object>();

    // Getter Methods 
    public Cabecera getCabecera() {
        return cabecera;
    }

    public void setCabecera(Cabecera cabecera) {
        this.cabecera = cabecera;
    }

    public ArrayList<Object> getDetalle() {
        return detalle;
    }

    public void setDetalle(ArrayList<Object> detalle) {
        this.detalle = detalle;
    }

    public class Cabecera {

        private String transacAlta;
        private String transac;
        private String rechaCabecera;
        private String rechaCabeDeno;
        private String apeNom;
        private String gravado;
        private String planCodi;
        private String pmi;
        private String sexo;
        private String edad;
        private String leyimp;

        // Getter Methods 
        public String getTransacAlta() {
            return transacAlta;
        }

        public String getTransac() {
            return transac;
        }

        public String getRechaCabecera() {
            return rechaCabecera;
        }

        public String getRechaCabeDeno() {
            return rechaCabeDeno;
        }

        public String getApeNom() {
            return apeNom;
        }

        public String getGravado() {
            return gravado;
        }

        public String getPlanCodi() {
            return planCodi;
        }

        public String getPmi() {
            return pmi;
        }

        public String getSexo() {
            return sexo;
        }

        public String getEdad() {
            return edad;
        }

        public String getLeyimp() {
            return leyimp;
        }

        // Setter Methods 
        public void setTransacAlta(String transacAlta) {
            this.transacAlta = transacAlta;
        }

        public void setTransac(String transac) {
            this.transac = transac;
        }

        public void setRechaCabecera(String rechaCabecera) {
            this.rechaCabecera = rechaCabecera;
        }

        public void setRechaCabeDeno(String rechaCabeDeno) {
            this.rechaCabeDeno = rechaCabeDeno;
        }

        public void setApeNom(String apeNom) {
            this.apeNom = apeNom;
        }

        public void setGravado(String gravado) {
            this.gravado = gravado;
        }

        public void setPlanCodi(String planCodi) {
            this.planCodi = planCodi;
        }

        public void setPmi(String pmi) {
            this.pmi = pmi;
        }

        public void setSexo(String sexo) {
            this.sexo = sexo;
        }

        public void setEdad(String edad) {
            this.edad = edad;
        }

        public void setLeyimp(String leyimp) {
            this.leyimp = leyimp;
        }
    }
    
    public void muestraRespuesta() {
        System.out.println("transacAlta=" + this.cabecera.getTransacAlta());
        System.out.println("transac=" + this.cabecera.getTransac());
        System.out.println("rechaCabecera=" + this.cabecera.getRechaCabecera());
        System.out.println("rechaCabeDeno=" + this.cabecera.getRechaCabeDeno());
        System.out.println("apeNom=" + this.cabecera.getApeNom());
        System.out.println("gravado=" + this.cabecera.getGravado());
        System.out.println("planCodi=" + this.cabecera.getPlanCodi());
        System.out.println("pmi=" + this.cabecera.getPmi());
        System.out.println("sexo=" + this.cabecera.getSexo());
        System.out.println("edad=" + this.cabecera.getEdad());
        System.out.println("leyimp=" + this.cabecera.getLeyimp());       
        System.out.println("detalle=" + this.getDetalle());
    }
    
}
