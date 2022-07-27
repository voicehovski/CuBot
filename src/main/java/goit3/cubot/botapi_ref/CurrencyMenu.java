package goit3.cubot.botapi_ref;

import goit3.cubot.Currency;

import java.util.List;

public class CurrencyMenu extends ButtonMenu {
    public static final String MENU = "Select currencies";
    public static final String USD = "USD";
    public static final String EUR = "EUR";

    public CurrencyMenu(List<Currency> selected) {
        super(new ButtonAttributes [] {
                new ButtonAttributes(USD, USD),
                new ButtonAttributes(EUR, EUR)
        });
        for (ButtonAttributes ba : buttons) {
            for (Currency currency : selected) {
                if (ba.eq(currency.toString())) {
                    ba.setSelected(true);
                }
            }
        }
    }
}