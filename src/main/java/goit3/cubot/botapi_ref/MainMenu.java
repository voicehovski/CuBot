package goit3.cubot.botapi_ref;

public class MainMenu extends ButtonMenu {
    public static final String GET_INFO = "Get info";
    public static final String SETTINGS = "Settings";

    public MainMenu() {
        super(new ButtonAttributes [] {
                new ButtonAttributes(GET_INFO, GET_INFO),
                new ButtonAttributes(SETTINGS, SETTINGS)
        });
    }
}
