package goit3.cubot.nbuapi;

import com.google.gson.Gson;
import goit3.cubot.Bank;
import goit3.cubot.Currency;
import goit3.cubot.CurrencyInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
* See API details here https://bank.gov.ua/ua/open-data/api-dev
* */
public class NBU extends Bank {
    private static final String CURRENCY_BY_NAME = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=";

    public CurrencyInfo parseResponse(StringBuffer response) {
        String toCurrency = String.valueOf(response).substring(1, response.length() - 1);
        Gson gson = new Gson();
        NBUCurrency currencyObj = gson.fromJson(toCurrency, NBUCurrency.class);

        return new CurrencyInfo() {

            @Override
            public String getCode() {
                return currencyObj.getCc();
            }

            @Override
            public double getBuy() {
                return currencyObj.getRate();
            }

            @Override
            public double getSale() {
                return currencyObj.getRate();
            }

            @Override
            public long getDateAndTime() {
                return System.currentTimeMillis();
            }
        };
    }

    @Override
    public List<CurrencyInfo> getCurrencyList() {
        return null;
    }

    @Override
    public CurrencyInfo getCurrencyByCode(Currency currencyCode) {
        HttpURLConnection connection = createConnection(CURRENCY_BY_NAME + currencyCode + "&json");

        int responseCode;
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException ioe) {
            throw new RuntimeException("Can`t get response code");
        }

        StringBuffer response;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            response = getResponseAsString(connection);
        } else {
            throw new RuntimeException("Bank has returned error code " + responseCode);
        }

        return parseResponse(response);
    }

    private HttpURLConnection createConnection (String urlString) {
        HttpURLConnection connection;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
        } catch (ProtocolException e) {
            throw new RuntimeException("Incorrect protocol");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Incorrect URL");
        } catch (IOException e) {
            throw new RuntimeException("Can`t create network connection");
        }
        return connection;
    }

    private StringBuffer getResponseAsString (HttpURLConnection connection) {
        BufferedReader in = null;
        StringBuffer response = new StringBuffer();
        try {
            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        } catch (IOException ioe) {
            throw new RuntimeException("Can`t read from network");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ioe) {
                throw new RuntimeException("Can`t close network stream");
            }
        }
        return response;
    }
}