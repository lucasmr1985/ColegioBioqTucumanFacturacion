/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;
import ClienteAsunt.AsuntApiException;
import ClienteAsunt.AsuntValidadorClient;
import ClienteAsunt.ReporteResultado;
import java.io.IOException;

public class EjemploReporte {

    public static void main(String[] args) throws IOException {
        String token = "TU_TOKEN";

        AsuntValidadorClient client = new AsuntValidadorClient(token);

        try {
            ReporteResultado resultado = client.reporte("5470240");

            if (resultado.isExito()) {
                System.out.println("Link: " + resultado.getRespuestaOk().getLink());
            } else {
                System.out.println("Error: " + resultado.getRespuestaError().getError());
                System.out.println("Detalle: " + resultado.getRespuestaError().getErrores());
            }

        } catch (AsuntApiException e) {
            System.out.println("Error ASUNT: " + e.getMessage());
        }
    }
}
