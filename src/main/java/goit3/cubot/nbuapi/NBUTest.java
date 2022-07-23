package goit3.cubot.nbuapi;

import goit3.cubot.Bank;
import goit3.cubot.Currency;

import java.io.IOException;

public class NBUTest {
    public static void main(String[] args) {
        test ();
    }
    public static void test () {
        Bank bankService = new NBU();
        double usdRate = 0;
        double eurRate = 0;
        try {
            usdRate = bankService.getCurrencyByCode(Currency.USD).getSale ();
            eurRate = bankService.getCurrencyByCode(Currency.EUR).getBuy();
        } catch (RuntimeException e) {
            e .printStackTrace();
        }

        System.out.println("Курс в НБУ: USD/UAH" + System.lineSeparator() + usdRate);
        System.out.println("Курс в НБУ: EUR/UAH" + System.lineSeparator() + eurRate);
    }
}
