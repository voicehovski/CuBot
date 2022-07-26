package goit3.cubot;

import java.io.Serializable;
import java.util.List;


public class UserSettings implements Serializable {
    private long chatId;
    private int roundDigit;
    private Bank bank;
    private int reminderTime;
    private List<Currency> currencies;

    public UserSettings(){
        roundDigit = 2;
        bank = new PrivatBankService();
        reminderTime = 9;
        currencies.add(Currency.USD);
    }

    public UserSettings(long chatId, int reminderTime, Bank bank, int roundDigit, List<Currency> currencies){
        this.bank = bank;
        this.chatId = chatId;
        this.currencies = currencies;
        this.reminderTime = reminderTime;
        this.roundDigit = roundDigit;
    }

    public UserSettings(long chatId, UserSettings settings){
        this.chatId = chatId;
        this.reminderTime = settings.reminderTime;
        this.currencies = settings.currencies;
        this.roundDigit = settings.roundDigit;
        this.bank = settings.bank;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public int getRoundDigit() {
        return roundDigit;
    }

    public void setRoundDigit(int roundDigit) {
        this.roundDigit = roundDigit;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public int getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(int reminderTime) {
        this.reminderTime = reminderTime;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public String toString() {
        return "UserSettings{" +
                "chatId=" + chatId +
                ", roundDigit=" + roundDigit +
                ", bank=" + bank +
                ", reminderTime=" + reminderTime +
                ", currencies=" + currencies +
                '}';
    }
}
