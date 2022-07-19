package goit3.cubot.nbuapi;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class NBU {
    private static final String CURRENCY_BY_NAME = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=";

    public double getNBUCurrenciesRate(String currency) throws IOException {
        URL url = new URL(CURRENCY_BY_NAME + currency + "&json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        int responseCode = connection.getResponseCode();

        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } else {
            System.out.println("GET request not worked");
        }

        return parseResponse(response);
    }

    public double parseResponse(StringBuffer response) {
        String toCurrency = String.valueOf(response).substring(1, response.length() - 1);
        Gson gson = new Gson();
        NBUCurrency currencyObj = gson.fromJson(toCurrency, NBUCurrency.class);

        return currencyObj.getRate();
    }
}