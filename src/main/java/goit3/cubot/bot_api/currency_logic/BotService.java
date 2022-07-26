package com.goit.cubot.bot_api.currency_logic;

import com.goit.cubot.Currency;


public interface BotService {

    static BotService getInstance() {
        return new HashMapCurrencyBotService();
    }

    Currency getCurrency(long chatId);

    void setCurrency(long chatId, Currency currency);
}
