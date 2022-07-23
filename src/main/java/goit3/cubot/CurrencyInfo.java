package goit3.cubot;

public interface CurrencyInfo {
    /**
     *
     * @return Return international currency code like USD or GBP
     */
    public String getCode ();
    public double getBuy ();
    public double getSale ();
    public long getDateAndTime ();
}
