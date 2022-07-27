package goit3.cubot.botapi_ref;

public class DigitCountMenu extends ButtonMenu {

    public static final String MENU = "Digit count";

    public DigitCountMenu() {
        super(
                new ButtonAttributes [] {
                        new ButtonAttributes("2", "2", true),
                        new ButtonAttributes("3", "3"),
                        new ButtonAttributes("4", "4")
                });
    }
}
