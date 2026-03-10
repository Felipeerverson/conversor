import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {

            String apiKey = "0059b3e738f708b8f17af7af";
            String url_str = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";

            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            Gson gson = new Gson();

            Map jsonMap = gson.fromJson(
                    new InputStreamReader(request.getInputStream()),
                    Map.class
            );

            Map rates = (Map) jsonMap.get("conversion_rates");

            double brl = (double) rates.get("BRL");
            double eur = (double) rates.get("EUR");

            System.out.println("===== CONVERSOR DE MOEDAS =====");
            System.out.println("1 - Dólar para Real");
            System.out.println("2 - Real para Dólar");
            System.out.println("3 - Euro para Dólar");
            System.out.println("4 - Dólar para Euro");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            System.out.print("Digite o valor: ");
            double valor = scanner.nextDouble();

            switch (opcao) {

                case 1:
                    System.out.println("Resultado: " + (valor * brl) + " BRL");
                    break;

                case 2:
                    System.out.println("Resultado: " + (valor / brl) + " USD");
                    break;

                case 3:
                    System.out.println("Resultado: " + (valor / eur) + " USD");
                    break;

                case 4:
                    System.out.println("Resultado: " + (valor * eur) + " EUR");
                    break;

                default:
                    System.out.println("Opção inválida");
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Erro ao acessar API: " + e.getMessage());
        }
    }
}