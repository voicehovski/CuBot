// Мейн класс для теста

package goit3.cubot.nbuapi;

import java.io.IOException;

class Main {
    private static final String DOLLAR = "USD";
    private static final String EURO = "EUR";

    public static void main(String[] args) throws IOException {
        NBUHttpRequest request = new NBUHttpRequest();
        String usdRate = request.getNBUCurrenciesRate(DOLLAR, 3);
        String eurRate = request.getNBUCurrenciesRate(EURO, 3);

        System.out.println("Курс в НБУ: USD/UAH\n" + usdRate);
        System.out.println("Курс в НБУ: EUR/UAH\n" + eurRate);
    }
}