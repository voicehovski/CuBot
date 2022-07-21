package goit3.cubot.banks.privatbank;

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
import java.util.Optional;
import java.util.stream.Collectors;

public class PrivatBankService extends Bank {
    //https://api.privatbank.ua/#p24/exchange
    private static final String URL = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";

    public CurrencyInfo getCurrencyByCode(Currency valute) {
        List<CurrencyInfo> exchangeList = getCurrencyList();

        Optional<CurrencyInfo> exchange = exchangeList.stream()
                .filter(ex -> ex.getCode().equalsIgnoreCase(valute.name()))
                .findFirst();

        return exchange.orElseThrow(() -> new PrivateBankException("Don`t supported currency for PrivatBank: "
                + valute.name()));
    }

    @Override
    public List<CurrencyInfo> getCurrencyList() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();
        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new PrivateBankException("Error for request to : " + request.uri(), e);
        }
        if (response.statusCode() == 200) {
            List<Exchange> exchanges = new Gson().fromJson(response.body(), new TypeToken<List<Exchange>>() {
            }.getType());

            return exchanges.stream().map(s -> (CurrencyInfo) s).collect(Collectors.toList());
        } else {
            throw new PrivateBankException("Error for getting data from: " + System.lineSeparator()
                    + response.uri() + System.lineSeparator()
                    + "Http status code " + response.statusCode());
        }

    }

}

