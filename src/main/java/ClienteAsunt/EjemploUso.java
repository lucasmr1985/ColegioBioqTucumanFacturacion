/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteAsunt;

import ClienteAsunt.AsuntValidadorClient;
import ClienteAsunt.AutorizarRequest;
import ClienteAsunt.AutorizarResponse;
import ClienteAsunt.AutorizacionItemResponse;
import ClienteAsunt.PracticaRequest;
import ClienteAsunt.AsuntApiException;
import java.io.IOException;

public class EjemploUso {

    public static void main(String[] args) throws IOException {
        String token = "cfbd3b060ceca558377b76ff3f38f5d3d9153164";

        AsuntValidadorClient client = new AsuntValidadorClient(token);

        AutorizarRequest request = new AutorizarRequest();
        request.setTipo_identificacion_afiliado("DNI");
        request.setIdentificacion_afiliado("12345668");
        request.setNomenclador("1003");
        request.setMatricula_profesional("1234");

        request.addPractica(new PracticaRequest("660001", 1));
        request.addPractica(new PracticaRequest("660475", 1));

        try {
            AutorizarResponse response = client.autorizar(request);

            System.out.println("Estado: " + response.getData().getEstado());
            System.out.println("Afiliado: " + response.getData().getTipo_identificacion_afiliado() + " "
                    + response.getData().getIdentificacion_afiliado());
            System.out.println("Plan: " + response.getData().getPlan());
            System.out.println("N° Autorización: " + response.getData().getN_autorizacion());
            System.out.println("Total copago: " + response.getData().getTotal_copago());

            if (response.getData().getAutorizaciones() != null) {
                for (AutorizacionItemResponse item : response.getData().getAutorizaciones()) {
                    System.out.println("--------------------------------------");
                    System.out.println("Estado práctica: " + item.getEstado_practica());
                    System.out.println("Código práctica: " + item.getCodigo_practica());
                    System.out.println("Nombre práctica: " + item.getNombre_practica());
                    System.out.println("Copago: " + item.getCopago());
                    System.out.println("Cantidad solicitada: " + item.getCantidad_solicitada());
                    System.out.println("Cantidad aprobada: " + item.getCantidad_aprobada());
                }
            }

        } catch (AsuntApiException e) {
            System.err.println("Error de ASUNT: " + e.getMessage());

            if (e.getErrorResponse() != null && e.getErrorResponse().getErrores() != null) {
                System.err.println("Detalle errores: " + e.getErrorResponse().getErrores());
            }

        }
    }
    
    
}