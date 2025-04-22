package ClienteSancor;


public class LoginResponse {

    private String token;
    private boolean tienePermiso;
    private String mensaje;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isTienePermiso() {
        return tienePermiso;
    }

    public void setTienePermiso(boolean tienePermiso) {
        this.tienePermiso = tienePermiso;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
   
    public void muestraRespuesta(){
    
        System.out.println("Token=" + this.getToken());
        System.out.println("tienePermiso=" + this.isTienePermiso());
        System.out.println("mensaje=" + this.getMensaje());
    
    }
    
    
}
