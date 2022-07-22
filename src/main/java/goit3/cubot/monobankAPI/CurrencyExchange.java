package goit3.cubot.monobankAPI;

import goit3.cubot.CurrencyInfo;

public class CurrencyExchange implements CurrencyInfo {
    private final int currencyCodeA;
    private final int currencyCodeB;
    private final long date;
    private final float rateBuy;
    private final float rateSell;

    public CurrencyExchange(int currencyCodeA, int currencyCodeB, long date, float rateBuy, float rateSell) {
        this.currencyCodeA = currencyCodeA;
        this.currencyCodeB = currencyCodeB;
        this.date = date;
        this.rateBuy = rateBuy;
        this.rateSell = rateSell;
    }

    @Override
    public String toString() {
        return "CurrencyExchange{" +
                "currencyCodeA=" + currencyCodeA +
                ", currencyCodeB=" + currencyCodeB +
                ", date=" + date +
                ", rateBuy=" + rateBuy +
                ", rateSell=" + rateSell +
                '}';
    }

    @Override
    public String getCode() {
        return getCurrencyNameByISOCode(currencyCodeA);
    }

    @Override
    public double getBuy() {
        return rateBuy;
    }

    @Override
    public double getSale() {
        return rateSell;
    }

    @Override
    public long getDateAndTime() {
        return date;
    }

    public String getCurrencyNameByISOCode(int code) {
        for (java.util.Currency currency : java.util.Currency.getAvailableCurrencies()) {
            if (currency.getNumericCode() == code) {
                return currency.toString();
            }
        }
        throw new IllegalArgumentException("Unkown currency code: " + code);
    }
}