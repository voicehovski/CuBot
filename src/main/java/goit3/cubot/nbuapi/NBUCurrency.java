package goit3.cubot.nbuapi;

import goit3.cubot.CurrencyInfo;

class NBUCurrency implements CurrencyInfo {
    // Код
    private int r030;
    // Наименование
    private String txt;
    // Курс
    private double rate;
    // Международный трехбуквенный код
    private String cc;
    private String exchangedate;

    public int getR030() {
        return r030;
    }

    public String getTxt() {
        return txt;
    }

    public double getRate() {
        return rate;
    }

    public String getCc() {
        return cc;
    }

    public String getExchangedate() {
        return exchangedate;
    }

    @Override
    public String toString() {
        return "NBUCurrency{" +
                "r030=" + r030 +
                ", txt='" + txt + '\'' +
                ", rate=" + rate +
                ", cc='" + cc + '\'' +
                ", exchangedate='" + exchangedate + '\'' +
                '}';
    }

    @Override
    public String getCode() {
        return cc;
    }

    @Override
    public double getBuy() {
        return rate;
    }

    @Override
    public double getSale() {
        return rate;
    }

    @Override
    public long getDateAndTime() {
        return System.currentTimeMillis();
    }
}