package goit3.cubot.banks.privatbank;

import goit3.cubot.Currency;
import goit3.cubot.CurrencyInfo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrivatBankServiceTest {
    private static PrivatBankService bankService;

    @org.junit.jupiter.api.BeforeAll
    static void setUp() {
        bankService = new PrivatBankService();
    }

    @org.junit.jupiter.api.Test
    void getCurrencyExchange() {
        CurrencyInfo currencyInfo = bankService.getCurrencyByCode(Currency.USD);
        List<CurrencyInfo> currencyInfoList = bankService.getCurrencyList();

        assertInstanceOf(CurrencyInfo.class, currencyInfo);
        assertEquals(Currency.USD.name(), currencyInfo.getCode());
        assertNotEquals(0.0, currencyInfo.getBuy());
        assertNotEquals(0.0, currencyInfo.getSale());
        assertNotEquals(0, currencyInfoList.size());

    }
}