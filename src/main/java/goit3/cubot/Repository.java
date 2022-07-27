package goit3.cubot;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<UserSettings> userSettings = new ArrayList<>();

    public boolean contains(int chat_id){
        // if (userSettings.contains(chat_id)) return true;
        //else return false;
        return userSettings.contains(chat_id);
    }

    public void add(String chat_id, UserSettings setting){
        /*
        * Считать json из файла
        * Преобразовать в список объектов UserSettings
        * Проверить, есть ли объект с таким chat_id, если есть, выбросить исключение
        * Добавить новый объект в список
        * Сохранить список в файл
        * */
        userSettings.add(new UserSettings(chat_id,setting));
    }

    public void delete(int chat_id){
        userSettings.remove(chat_id);
    }

    public UserSettings getSetting(int chat_id){
        return userSettings.get(chat_id);
    }
}
