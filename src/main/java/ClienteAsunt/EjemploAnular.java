/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;
import ClienteAsunt.AnularResultado;
import ClienteAsunt.AsuntApiException;
import ClienteAsunt.AsuntValidadorClient;
import java.io.IOException;

public class EjemploAnular {

    public static void main(String[] args) throws IOException {
        String token = "cfbd3b060ceca558377b76ff3f38f5d3d9153164";

        AsuntValidadorClient client = new AsuntValidadorClient(token);

        try {
            AnularResultado resultado = client.anular("5485808");

            if (resultado.isExito()) {
                System.out.println("Estado: " + resultado.getRespuestaOk().getEstado());
                System.out.println("N° autorización: " + resultado.getRespuestaOk().getN_autorizacion());
                System.out.println("N° anulación: " + resultado.getRespuestaOk().getN_anulacion());
            } else {
                System.out.println("Error: " + resultado.getRespuestaError().getError());
                System.out.println("Detalle: " + resultado.getRespuestaError().getErrores());
            }

        } catch (AsuntApiException e) {
            System.out.println("Error ASUNT: " + e.getMessage());
        }
    }
}