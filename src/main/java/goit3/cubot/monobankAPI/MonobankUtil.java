package goit3.cubot.monobankAPI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import goit3.cubot.Bank;
import goit3.cubot.Currency;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class MonobankUtil extends Bank {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    private static final String MONOLINK = "https://api.monobank.ua/bank/currency";

    @Override
    public String getCurrencyExchange(Currency valute) {
        List<CurrencyExchange> list = getMonobankCurrencyInfo();
        Optional<CurrencyExchange> result = list.stream()
                .filter(e -> getCurrencyByCode(e.getCurrencyCodeA()).toString().equals(valute.name()))
                .findFirst();
        return result.toString();
    }

    private List<CurrencyExchange> getMonobankCurrencyInfo() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(MONOLINK))
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.err.println("monobank API is not available");
        }
        return GSON.fromJson(Objects.requireNonNull(response).body(), new TypeToken<List<CurrencyExchange>>() {
        }.getType());
    }

    private java.util.Currency getCurrencyByCode(int code) {
        for(java.util.Currency currency : java.util.Currency.getAvailableCurrencies()) {
            if(currency.getNumericCode() == code) {
                return currency;
            }
        }
        throw new IllegalArgumentException("Unkown currency code: " + code);
    }
}