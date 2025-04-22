package ClienteOspeApi;

import ClienteSwissMedicalApi.*;

public class LoginResponse {

    private String token;
    private String exp;
    private ModelEspecifico modelEspecifico;
    private String seguridad;

    public class ModelEspecifico {

        private String cuitPrestador;
        private int codigoPrestador;
        private String tipoPrestadorDesc;
        private String tipoPrestador;
        private int numeroLegajo;

        public ModelEspecifico() {
        }

        // Getter Methods 
        public String getCuitPrestador() {
            return cuitPrestador;
        }

        public float getCodigoPrestador() {
            return codigoPrestador;
        }

        public String getTipoPrestadorDesc() {
            return tipoPrestadorDesc;
        }

        public String getTipoPrestador() {
            return tipoPrestador;
        }

        public float getNumeroLegajo() {
            return numeroLegajo;
        }

        // Setter Methods 
        public void setCuitPrestador(String cuitPrestador) {
            this.cuitPrestador = cuitPrestador;
        }

        public void setCodigoPrestador(int codigoPrestador) {
            this.codigoPrestador = codigoPrestador;
        }

        public void setTipoPrestadorDesc(String tipoPrestadorDesc) {
            this.tipoPrestadorDesc = tipoPrestadorDesc;
        }

        public void setTipoPrestador(String tipoPrestador) {
            this.tipoPrestador = tipoPrestador;
        }

        public void setNumeroLegajo(int numeroLegajo) {
            this.numeroLegajo = numeroLegajo;
        }

    }

    // Getter Methods 
    public String getToken() {
        return token;
    }

    public String getExp() {
        return exp;
    }

    public ModelEspecifico getModelEspecifico() {
        return modelEspecifico;
    }

    public String getSeguridad() {
        return seguridad;
    }

    // Setter Methods 
    public void setToken(String token) {
        this.token = token;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public void setModelEspecifico(ModelEspecifico modelEspecifico) {
        this.modelEspecifico = modelEspecifico;
    }

    public void setSeguridad(String seguridad) {
        this.seguridad = seguridad;
    }

    public void muestraRespuesta(){
    
        System.out.println("Token=" + this.getToken());
        System.out.println("exp=" + this.getExp());
        System.out.println("seguridad=" + this.getSeguridad());
        System.out.println("CuitPrestador=" + this.getModelEspecifico().getCuitPrestador());
        System.out.println("tipoPrestador=" + this.getModelEspecifico().getTipoPrestador());
        System.out.println("codigoPrestador=" + this.getModelEspecifico().getCodigoPrestador());
        System.out.println("tipoPrestadorDesc=" + this.getModelEspecifico().getTipoPrestadorDesc());
        System.out.println("numeroLegajo=" + this.getModelEspecifico().getNumeroLegajo());
    
    }
    
    
}
