package goit3.cubot.botapi_ref;

public class ButtonAttributes {
    public boolean defaultOption;
    public String title;
    public String callbackData;

    public ButtonAttributes(String title, String callbackData, boolean defaultOption) {
        this.defaultOption = defaultOption;
        this.title = title;
        this.callbackData = callbackData;
    }

    public ButtonAttributes(String title, String callbackData) {
        this(title, callbackData,false);
    }
}
