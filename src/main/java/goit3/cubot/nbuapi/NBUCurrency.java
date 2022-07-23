package goit3.cubot.nbuapi;

class NBUCurrency {
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
}