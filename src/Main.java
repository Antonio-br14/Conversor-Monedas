import java.util.Scanner;

import Model.Moneda;
import service.ConsultarCambio;

public class Main {
    public static void main(String[] args) throws Exception {
        
        Scanner lectura = new Scanner(System.in);

        while (true) {
            System.out.println("=== Conversor de monedas===");
            System.out.println("¿Cual es la moneda de origen que quiere cambiar?\n digite su codigo (ej: COP)");
            String desde = lectura.nextLine().toUpperCase();

            System.out.println("Moneda destino (ej: USD)");
            String hacia = lectura.nextLine().toUpperCase();

            System.out.println("Cantidad a convertir: ");
            double cantidad = lectura.nextDouble();
            lectura.nextLine();

            Moneda conversion = ConsultarCambio.obtenerMoneda(desde,hacia,cantidad);

            if (conversion != null) {
                System.out.printf("Tasa actual: 1 %s = %.4f %s%n", desde, conversion.tasa(), hacia.toUpperCase());
                System.out.printf("%.2f %s = %.2f %s%n", cantidad, desde, conversion.resultado(), hacia.toUpperCase());
            } else {
                System.out.println("No se pudo realizar la conversión.");
            }

            System.out.print("¿Deseas hacer otra conversión? (s/n): ");
            String respuesta = lectura.nextLine();
            if (!respuesta.equalsIgnoreCase("s")) {
                System.out.println("Gracias por usar el conversor de monedas.");
                break;
            }
        }

        lectura.close();
    }
}
