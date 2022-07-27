package goit3.cubot.botapi_ref;

public class ButtonAttributes {
    public boolean isSelected;
    public String title;
    public String callbackData;

    public ButtonAttributes(String title, String callbackData, boolean isSelected) {
        this.isSelected = isSelected;
        this.title = title;
        this.callbackData = callbackData;
    }

    public ButtonAttributes(String title, String callbackData) {
        this(title, callbackData,false);
    }

    public void setSelected ( boolean value ) {
        isSelected = value;
        // todo Здесь (и в конструкторе) добавлять галочку
    }

    public boolean eq ( String callbackData ) {    // todo Может лучше по callbackData ?
        return this.callbackData.equals(callbackData);
    }

    public String getFormattedTitle() {  // getFormattedTitle ?
        return isSelected ? "* " + title : title;
    }
}
