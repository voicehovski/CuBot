package goit3.cubot.botapi_ref;

public class ScheduleMenu extends ButtonMenu {

    public static final String MENU = "Shedule";

    public static final String TURN_OFF_NOTIFICATION = "Turn off";

    public ScheduleMenu() {
        super(new ButtonAttributes [] {
                new ButtonAttributes("9", "9"),
                new ButtonAttributes("10", "10"),
                new ButtonAttributes("18", "18"),
                new ButtonAttributes(TURN_OFF_NOTIFICATION, TURN_OFF_NOTIFICATION)
        });
    }
}