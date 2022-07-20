package goit3.cubot.nbuapi;

import goit3.cubot.Bank;
import goit3.cubot.Currency;
import goit3.cubot.CurrencyInfo;


public class NBUTest {
    public static void main(String[] args) {
        test ();
    }
    public static void test () {
        Bank bankService = new NBU();
        double usdRate = 0;
        try {
            usdRate = bankService.getCurrencyByCode(Currency.USD).getSale();
            usdRate = bankService.getCurrencyByCode(Currency.USD).getBuy();
        } catch (RuntimeException e) {
            e .printStackTrace();
        }

        System.out.println("Курс в НБУ: USD/UAH" + System.lineSeparator() + "Покупка: " + usdRate);
        System.out.println("Курс в НБУ: USD/UAH" + System.lineSeparator() + "Продажа: " + usdRate);
    }
}
