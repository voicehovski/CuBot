package goit3.cubot.telegram;

public class UserSettingsSaver implements TgMessageEventhandler {
    @Override
    public void handle(TgMessageEvent event) {
        /*
        * Можно, кстати, сделать разные обработчики для разных настроек, да и евенты разные
        * Получить тип события
        * В зависимости от типа, обновить соответствующую настройку значением из события
        * */
    }
}
