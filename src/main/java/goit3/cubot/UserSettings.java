package goit3.cubot;

public class UserSettings {
    private int chatId;
    private int roundDigit;
    private Bank bank;
    private int reminderTime;
    private List<Сurrency> currencies;

    public UserSettings(){
        chatId = 0;
        roundDigit = 2;
        //  bank = privatBank
        reminderTime = 9;
        currencies.add(Сurrency.USD);
    }

//    public UserSettings(int chatId, int roundDigit, Bank bank,int reminderTime, List<Currency> currencies){
//        this.chatId = chatId;
//        this.roundDigit = roundDigit;
//        this.bank = bank;
//        this.reminderTime = reminderTime;
//        this.currencies = currencies;
//    }

    public UserSettings(int chatId, UserSettings settings){
        this.chatId = chatId;
        this.reminderTime = settings.reminderTime;
        this.currencies = settings.currencies;
        this.roundDigit = settings.roundDigit;
        this.bank = settings.bank;
    }

//    UserSettings(){
//        this.chatId = getChatId();
//        this.roundDigit = getRoundDigit();
//        this.bank = getBank();
//        this.reminderTime = getReminderTime();
//        this.currencies = getCurrencies();
//    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
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

    public List<Сurrency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Сurrency> currencies) {
        this.currencies = currencies;
    }
}
