package ClienteOspeApi;

import ClienteSwissMedicalApi.*;

public class Login {

    private String apiKey;
    private String ClaveOspeonline;
    private String CuitPrestador;
    private Terminal device;

    public Login() {
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getClaveOspeonline() {
        return ClaveOspeonline;
    }

    public void setClaveOspeonline(String ClaveOspeonline) {
        this.ClaveOspeonline = ClaveOspeonline;
    }

    public String getCuitPrestador() {
        return CuitPrestador;
    }

    public void setCuitPrestador(String CuitPrestador) {
        this.CuitPrestador = CuitPrestador;
    }

    public Terminal getDevice() {
        return device;
    }

    public void setDevice(Terminal device) {
        this.device = device;
    }
    
}
