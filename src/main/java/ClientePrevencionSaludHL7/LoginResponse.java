
package ClientePrevencionSaludHL7;

/**
 *
 * @author Lucas Robles
 */
public class LoginResponse {

    private String access_token;
    private String expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
    
     public void muestraRespuesta(){
    
        System.out.println("access_token=" + this.getAccess_token());
        System.out.println("expires_in=" + this.getExpires_in());
    
    }

}
