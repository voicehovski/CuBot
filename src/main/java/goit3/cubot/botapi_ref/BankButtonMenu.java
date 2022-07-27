package goit3.cubot.botapi_ref;

public class BankButtonMenu extends ButtonMenu {

    public static final String MENU = "Select bank";

    public static final String NBU = "nbu";
    public static final String PRIVATBANK = "privatbank";
    public static final String MONOBANK = "monobank";

    public BankButtonMenu() {
        super(new ButtonAttributes [] {
                        new ButtonAttributes(PRIVATBANK, PRIVATBANK, true),
                        new ButtonAttributes(MONOBANK, MONOBANK),
                        new ButtonAttributes(NBU, NBU)
                });
    }
    public BankButtonMenu(String selectedBankName) {
        super(new ButtonAttributes [] {
                new ButtonAttributes(PRIVATBANK, PRIVATBANK),
                new ButtonAttributes(MONOBANK, MONOBANK),
                new ButtonAttributes(NBU, NBU)
        }, selectedBankName);

    }
}
