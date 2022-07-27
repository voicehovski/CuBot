package goit3.cubot;

import goit3.cubot.botapi_ref.BankButtonMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserSettings {
    private String chatId;
    private int roundDigit;
    private String bankName;
    private int reminderTime;
    private List<Currency> currencies = new ArrayList<>();

    public static UserSettings createDefault (String chatId) {
        return new UserSettings(
                chatId,
                2,
                BankButtonMenu.PRIVATBANK,
                9,
                Arrays.asList(Currency.USD)
                );
    }

    public UserSettings(String chatId, int roundDigit, String bankName, int reminderTime, List<Currency> currencies){
        this.chatId = chatId;
        this.roundDigit = roundDigit;
        this.bankName = bankName;
        this.reminderTime = reminderTime;
        this.currencies .addAll(currencies);
    }

    public UserSettings(String chatId, UserSettings settings){
        this.chatId = chatId;
        this.reminderTime = settings.reminderTime;
        this.currencies = settings.currencies;
        this.roundDigit = settings.roundDigit;
        this.bankName = settings.bankName;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public int getRoundDigit() {
        return roundDigit;
    }

    public void setRoundDigit(int roundDigit) {
        this.roundDigit = roundDigit;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(int reminderTime) {
        this.reminderTime = reminderTime;
    }

    public void turnOffReminder () {
        // todo Implement
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public void toggleCurrency (Currency currency) {
        if(this.currencies.contains(currency)){
            this.currencies.remove(currency);
        } else {
            this.currencies.add(currency);
        }
    }

}
