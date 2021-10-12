package ClienteSwissMedicalApi;

public class Login {

    private String apiKey;
    private String usrLoginName;
    private String password;
    private String cuit;
    private Device device;

    

    public Login() {
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUsrLoginName() {
        return usrLoginName;
    }

    public void setUsrLoginName(String usrLoginName) {
        this.usrLoginName = usrLoginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
