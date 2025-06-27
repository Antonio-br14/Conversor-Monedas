package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Model.Moneda;
import Model.RespuestaCambio;

public class ConsultarCambio {
    public static Moneda obtenerMoneda(String desde, String hacia, double cantidad) {

        String apiKey = "45d6c182b637a76d852d845e";

        try {
            URI uriExchange = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + desde);

            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(uriExchange).build();
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            RespuestaCambio datosCambio = gson.fromJson(response.body(), RespuestaCambio.class);

            Double tasa = datosCambio.conversion_rates.get(hacia);

            if (tasa == null) {
                System.out.println("La moneda destino '" + hacia + "' no fue encontrada en la API.");
                return null;
            }

            double resultado = cantidad * tasa;

            return new Moneda(desde, hacia, cantidad, tasa, resultado);

        } catch (Exception e) {
            System.out.println("Error al obtener datos de la API: " + e.getMessage());
            return null;
        }
    }
}
