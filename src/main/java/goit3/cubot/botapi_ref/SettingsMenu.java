package goit3.cubot.botapi_ref;

public class SettingsMenu extends ButtonMenu {

    public static final String MENU = "Settings";

    public SettingsMenu() {
        super(new ButtonAttributes [] {
                new ButtonAttributes(BankButtonMenu.MENU, BankButtonMenu.MENU),
                new ButtonAttributes(CurrencyMenu.MENU, CurrencyMenu.MENU),
                new ButtonAttributes(DigitCountMenu.MENU, DigitCountMenu.MENU),
                new ButtonAttributes(ScheduleMenu.MENU, ScheduleMenu.MENU)
        });
    }
}