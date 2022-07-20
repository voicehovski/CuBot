package goit3.cubot.nbuapi;

import goit3.cubot.Currency;

import java.io.IOException;

public class NBUTest {

    public void test () {
        NBU request = new NBU();
        double usdRate;
        double eurRate;
        try {
            usdRate = request.getNBUCurrenciesRate(Currency.USD.name());
            eurRate = request.getNBUCurrenciesRate(Currency.EUR.name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Курс в НБУ: USD/UAH" + System.lineSeparator() + usdRate);
        System.out.println("Курс в НБУ: EUR/UAH" + System.lineSeparator() + eurRate);
    }
}
