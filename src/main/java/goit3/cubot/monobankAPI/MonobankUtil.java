package goit3.cubot.monobankAPI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import goit3.cubot.Bank;
import goit3.cubot.Currency;
import goit3.cubot.CurrencyInfo;

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
    private static final String APILINK = "https://api.monobank.ua/bank/currency";

    @Override
    public List<CurrencyInfo> getCurrencyList() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(APILINK))
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

    @Override
    public CurrencyInfo getCurrencyByCode(Currency currencyCode) {
        Optional<CurrencyInfo> result = getCurrencyList().stream()
                .filter(x -> x.getCode().equals(currencyCode.name()))
                .findFirst();
        return result.orElseThrow(RuntimeException::new);
    }
}