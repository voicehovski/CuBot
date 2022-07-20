package goit3.cubot.banks.privatbank;

import goit3.cubot.Currency;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PrivatBankServiceTest {
    private static PrivatBankService bankService;

    @org.junit.jupiter.api.BeforeAll
    static void setUp() {
        bankService = new PrivatBankService();
    }

    @org.junit.jupiter.api.Test
    void getCurrencyExchange() {
        assertNotEquals("В вибраному банку немає даних для обраної валюти: " + Currency.USD,
                bankService.getCurrencyExchange(Currency.USD), "Incorrect value for " + Currency.USD);
    }
}