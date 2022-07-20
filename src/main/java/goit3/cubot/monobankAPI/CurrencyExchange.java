package goit3.cubot.monobankAPI;

public class CurrencyExchange {
    int currencyCodeA;
    int currencyCodeB;
    int dateUnix;
    float rateBuy;
    float rateSell;

    public CurrencyExchange(int currencyCodeA, int currencyCodeB, int dateUnix, float rateBuy, float rateSell) {
        this.currencyCodeA = currencyCodeA;
        this.currencyCodeB = currencyCodeB;
        this.dateUnix = dateUnix;
        this.rateBuy = rateBuy;
        this.rateSell = rateSell;
    }

    public int getCurrencyCodeA() {
        return currencyCodeA;
    }

    public int getCurrencyCodeB() {
        return currencyCodeB;
    }

    public int getDateUnix() {
        return dateUnix;
    }

    public float getRateBuy() {
        return rateBuy;
    }

    public float getRateSell() {
        return rateSell;
    }

    @Override
    public String toString() {
        return "CurrencyExchange{" +
                "currencyCodeA=" + currencyCodeA +
                ", currencyCodeB=" + currencyCodeB +
                ", dateUnix=" + dateUnix +
                ", rateBuy=" + rateBuy +
                ", rateSell=" + rateSell +
                '}';
    }
}