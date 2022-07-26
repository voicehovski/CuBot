package goit3.cubot;

import java.util.List;

public interface Bank {
    List<CurrencyInfo> getCurrencyList();
    CurrencyInfo getCurrencyByCode(Currency currencyCode);


}