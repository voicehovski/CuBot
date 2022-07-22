package goit3.cubot.pbapi;

import goit3.cubot.CurrencyInfo;

import java.util.Objects;

class Exchange implements CurrencyInfo {
    public String ccy;
    public String base_ccy;
    public double buy;
    public double sale;
    public long dateTime;

    public Exchange() {
        dateTime = System.currentTimeMillis();
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getBase_ccy() {
        return base_ccy;
    }

    public void setBase_ccy(String base_ccy) {
        this.base_ccy = base_ccy;
    }

    @Override
    public String getCode() {
        return ccy;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public long getDateAndTime() {
        return dateTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exchange exchange = (Exchange) o;
        return ccy.equals(exchange.ccy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ccy);
    }

    @Override
    public String toString() {
        return "PrivatBank: " +
                ccy + '/' + base_ccy +
                System.lineSeparator() +
                "Buy: " + buy +
                System.lineSeparator() +
                "Sale: " + sale;
    }
}
