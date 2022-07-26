package com.goit.cubot.bot_api.currency_logic;

import com.goit.cubot.Currency;


import java.util.HashMap;
import java.util.Map;

public class HashMapCurrencyBotService implements BotService {
    private final Map<Long, Currency> currentCurrency = new HashMap<>();

    public HashMapCurrencyBotService() {
    }

    @Override
    public Currency getCurrency(long chatId) {
        return currentCurrency.getOrDefault(chatId, Currency.USD);
    }

    @Override
    public void setCurrency(long chatId, Currency currency) {
        currentCurrency.put(chatId, currency);
    }
}