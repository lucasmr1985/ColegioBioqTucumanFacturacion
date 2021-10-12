
package ClienteSwissMedicalApi;

public class Device {

        private String messagingid;
        private String deviceid;
        private String devicename;
        private int bloqueado;
        private int recordar;

        public Device() {
        }

        public String getMessagingid() {
            return messagingid;
        }

        public void setMessagingid(String messagingid) {
            this.messagingid = messagingid;
        }

        public String getDeviceid() {
            return deviceid;
        }

        public void setDeviceid(String deviceid) {
            this.deviceid = deviceid;
        }

        public String getDevicename() {
            return devicename;
        }

        public void setDevicename(String devicename) {
            this.devicename = devicename;
        }

        public int getBloqueado() {
            return bloqueado;
        }

        public void setBloqueado(int bloqueado) {
            this.bloqueado = bloqueado;
        }

        public int getRecordar() {
            return recordar;
        }

        public void setRecordar(int recordar) {
            this.recordar = recordar;
        }
    }
