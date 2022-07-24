package goit3.cubot.nbuapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import goit3.cubot.Bank;
import goit3.cubot.Currency;
import goit3.cubot.CurrencyInfo;
import goit3.cubot.exceptions.BadServerResponceException;
import goit3.cubot.exceptions.NetworkProblemException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

/*
 * See API details here https://bank.gov.ua/ua/open-data/api-dev
 * */
public class NBU extends Bank {
    private static final String CURRENCY_BY_NAME = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=";
    private static final String CURRENCY_LIST_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?&json";

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
        StringBuffer jsonString = getJsonString();
        java.lang.reflect.Type listElementType = new TypeToken<List<NBUCurrency>>() {
        }.getType();
        Gson gson = new Gson();
        List<NBUCurrency> currencyList = gson.fromJson(jsonString.toString(), listElementType);

        return currencyList.stream().map(cl -> (CurrencyInfo) cl).collect(Collectors.toList());
    }

    @Override
    public CurrencyInfo getCurrencyByCode(Currency currencyCode) {
        HttpURLConnection connection = createConnection(CURRENCY_BY_NAME + currencyCode + "&json");

        int responseCode;
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException ioe) {
            throw new NetworkProblemException();
        }

        StringBuffer response;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            response = getResponseAsString(connection);
        } else {
            throw new BadServerResponceException("Bank has returned error code ", String.valueOf(responseCode));
        }

        connection.disconnect();

        return parseResponse(response);
    }

    private StringBuffer getJsonString() {
        HttpURLConnection connection = createConnection(CURRENCY_LIST_URL);

        int responseCode;
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException ioe) {
            throw new NetworkProblemException();
        }

        StringBuffer response;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            response = getResponseAsString(connection);
        } else {
            throw new BadServerResponceException("Bank has returned error code ", String.valueOf(responseCode));
        }

        connection.disconnect();

        return response;
    }

    private HttpURLConnection createConnection(String urlString) {
        HttpURLConnection connection;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
        } catch (IOException ioe) {
            throw new NetworkProblemException();
        }
        return connection;
    }

    private StringBuffer getResponseAsString(HttpURLConnection connection) {
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
            throw new NetworkProblemException();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ioe) {
                throw new NetworkProblemException();
            }
        }
        return response;
    }
}