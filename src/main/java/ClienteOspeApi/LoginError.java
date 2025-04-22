package ClienteOspeApi;

import ClienteSwissMedicalApi.*;

public class LoginError {

    private int responseTime;
    private Data data;

    public class Data {

        int errNumber;
        String errMessage;
        String errInfo;

        public Data() {
        }

        public int getErrNumber() {
            return errNumber;
        }

        public void setErrNumber(int errNumber) {
            this.errNumber = errNumber;
        }

        public String getErrMessage() {
            return errMessage;
        }

        public void setErrMessage(String errMessage) {
            this.errMessage = errMessage;
        }

        public String getErrInfo() {
            return errInfo;
        }

        public void setErrInfo(String errInfo) {
            this.errInfo = errInfo;
        }
    }

    public LoginError() {
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void mostrarError() {
        System.out.println("responseTime " + this.getResponseTime());
        System.out.println("Error " + this.getData().getErrInfo());
        System.out.println("Mensaje Error " + this.getData().getErrMessage());
        System.out.println("Número Error " + this.getData().getErrNumber());

    }

}
