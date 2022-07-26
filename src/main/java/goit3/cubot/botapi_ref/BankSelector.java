package goit3.cubot.botapi_ref;

public class BankSelector extends Selector {

    public static final String NBU = "nbu";
    public static final String PRIVATBANK = "privatbank";
    public static final String MONOBANK = "monobank";

    public BankSelector () {
        super(new ButtonAttributes [] {
                        new ButtonAttributes(PRIVATBANK, PRIVATBANK, true),
                        new ButtonAttributes(MONOBANK, MONOBANK),
                        new ButtonAttributes(NBU, NBU)
                });
    }
    public BankSelector (String bankName) {
        super(new ButtonAttributes [] {
                new ButtonAttributes(PRIVATBANK, PRIVATBANK, true),
                new ButtonAttributes(MONOBANK, MONOBANK),
                new ButtonAttributes(NBU, NBU)
        });
    }
}
