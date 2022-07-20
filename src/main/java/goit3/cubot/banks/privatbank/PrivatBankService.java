package goit3.cubot.banks.privatbank;

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

import static java.lang.System.err;

public class PrivatBankService extends Bank {
    @Override
    public String getCurrencyExchange(Currency valute) {
        List<Exchange> exchangeList = getExchanges();

        Optional<Exchange> exchange = exchangeList.stream()
                .filter(ex -> ex.ccy.equalsIgnoreCase(valute.name()))
                .findFirst();

        if (exchange.isEmpty()) {
            return "В вибраному банку немає даних для обраної валюти: " + valute.name();
        }

        return "Курс в Приватбанк " + exchange.get().ccy + '/' + exchange.get().base_ccy +
                System.lineSeparator() +
                "Покупка: " + exchange.get().buy +
                System.lineSeparator() +
                "Продажа: " + exchange.get().sale;
    }

    private List<Exchange> getExchanges() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11"))
                .GET()
                .build();
        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            err.println("Privatbank connection error");
            throw new RuntimeException(e);
        }
        return new Gson().fromJson(response.body(), new TypeToken<List<Exchange>>() {
        }.getType());
    }
}

class Exchange {
    public String ccy;
    public String base_ccy;
    public double buy;
    public double sale;

    public Exchange() {
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getBase_ccy() {
        return base_ccy;
    }

    public void setBase_ccy(String base_ccy) {
        this.base_ccy = base_ccy;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exchange exchange = (Exchange) o;
        return ccy.equals(exchange.ccy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ccy);
    }

    @Override
    public String toString() {
        return "Курс в Приватбанк " +
                ccy + '/' + base_ccy +
                System.lineSeparator() +
                "Покупка: " + buy +
                System.lineSeparator() +
                "Продажа: " + sale;
    }
}
