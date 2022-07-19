// Мейн класс для теста

package goit3.cubot.nbuapi;

import java.io.IOException;

class Main {
    private static final String DOLLAR = "USD";
    private static final String EURO = "EUR";

    public static void main(String[] args) throws IOException {
        NBU request = new NBU();
        double usdRate = request.getNBUCurrenciesRate(DOLLAR);
        double eurRate = request.getNBUCurrenciesRate(EURO);

        System.out.println("Курс в НБУ: USD/UAH" + System.lineSeparator() + usdRate);
        System.out.println("Курс в НБУ: EUR/UAH" + System.lineSeparator() + eurRate);
    }
}