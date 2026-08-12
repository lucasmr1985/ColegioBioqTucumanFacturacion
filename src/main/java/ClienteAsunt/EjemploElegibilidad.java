package ClienteAsunt;

import ClienteAsunt.AsuntApiException;
import ClienteAsunt.AsuntValidadorClient;
import ClienteAsunt.ElegibilidadRequest;
import ClienteAsunt.ElegibilidadResultado;
import java.io.IOException;

public class EjemploElegibilidad {
    

    public static void main(String[] args) throws IOException {
        String token = "cfbd3b060ceca558377b76ff3f38f5d3d9153164";

        AsuntValidadorClient client = new AsuntValidadorClient(token);

        ElegibilidadRequest request = new ElegibilidadRequest();
        request.setTipo_identificacion_afiliado("DNI");
        request.setIdentificacion_afiliado("12345678");

        try {
            ElegibilidadResultado resultado = client.elegibilidad(request);

            if (resultado.isExito()) {
                System.out.println("Nombre: " + resultado.getRespuestaOk().getData().getNombre());
                System.out.println("Documento: " + resultado.getRespuestaOk().getData().getDocumento());
                System.out.println("Credencial: " + resultado.getRespuestaOk().getData().getCredencial());
                System.out.println("Plan: " + resultado.getRespuestaOk().getData().getPlan());
            } else {
                System.out.println("Mensaje: " + resultado.getRespuestaError().getMensaje());
            }

        } catch (AsuntApiException e) {
            System.out.println("Error ASUNT: " + e.getMessage());
        }
    }
}