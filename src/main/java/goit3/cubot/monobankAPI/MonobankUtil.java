package goit3.cubot.monobankAPI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Currency;
import java.util.List;
import java.util.Set;

public class MonobankUtil {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    private static final String MONOLINK = "https://api.monobank.ua/bank/currency";

    public List<CurrencyExchange> getAllCurrencyExchange() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(MONOLINK))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), new TypeToken<List<CurrencyExchange>>() {
        }.getType());
    }

    private Currency getCurrencyInstance(int numericCode) {
        Set<Currency> currencies = Currency.getAvailableCurrencies();
        for (Currency currency : currencies) {
            if (currency.getNumericCode() == numericCode) {
                System.out.println(currency.toString());
                return currency;
            }
        }
        throw new IllegalArgumentException("Currency with numeric code " + numericCode + " not found");
    }
}