package goit3.cubot.telegram;

public interface TgMessageEvent {
    public int getEventType ();
    public String getEventValue ();
}
