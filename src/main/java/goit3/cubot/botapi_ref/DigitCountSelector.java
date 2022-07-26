package goit3.cubot.botapi_ref;

public class DigitCountSelector extends Selector {

    public DigitCountSelector () {
        super(
                new ButtonAttributes [] {
                        new ButtonAttributes("2", "2", true),
                        new ButtonAttributes("3", "3"),
                        new ButtonAttributes("4", "4")
                });
    }
}
