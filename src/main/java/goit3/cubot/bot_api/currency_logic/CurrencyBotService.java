package goit3.cubot.bot_api.currency_logic;

import goit3.cubot.Currency;

public interface CurrencyBotService {

    static CurrencyBotService getInstance() {
        return new HashMapCurrencyBotService();
    }

    Currency getCurrency(long chatId);

    void setCurrency(long chatId, Currency currency);
}
