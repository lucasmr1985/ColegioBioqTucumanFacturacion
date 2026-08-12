/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;

import java.util.List;
import java.util.Map;

public class ApiErrorResponse {

    private String error;
    private Map<String, List<String>> errores;

    public ApiErrorResponse() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Map<String, List<String>> getErrores() {
        return errores;
    }

    public void setErrores(Map<String, List<String>> errores) {
        this.errores = errores;
    }
}
