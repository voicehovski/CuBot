package goit3.cubot.monobankAPI;

public class CurrencyExchange {
    private int currencyCodeA;
    private int currencyCodeB;
    private long date;
    private float rateBuy;
    private float rateSell;

    public CurrencyExchange(int currencyCodeA, int currencyCodeB, long date, float rateBuy, float rateSell) {
        this.currencyCodeA = currencyCodeA;
        this.currencyCodeB = currencyCodeB;
        this.date = date;
        this.rateBuy = rateBuy;
        this.rateSell = rateSell;
    }

    public int getCurrencyCodeA() {
        return currencyCodeA;
    }

    public int getCurrencyCodeB() {
        return currencyCodeB;
    }

    public long getDate() {
        return date;
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
                ", date=" + date +
                ", rateBuy=" + rateBuy +
                ", rateSell=" + rateSell +
                '}';
    }
}