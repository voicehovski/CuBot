package goit3.cubot;

import java.util.*;

public abstract class Repository {
    public void add(UserSettings settings) {
        try {
            List<UserSettings> userSettingsList = getData (  );
            int index = findIndex( userSettingsList, settings.getChatId());
            if (index != -1){
                userSettingsList.set(index, settings);
            } else {
                userSettingsList.add(settings);
            }
            storeData(userSettingsList);
        } catch (Exception e){
            throw new RuntimeException ("Problems while adding to repository");
        }

    }

    public void delete (String chatId) {
        try {
            List<UserSettings> userSettingsList = getData (  );
            int index = findIndex( userSettingsList, chatId);
            if (index != -1) {
                userSettingsList.remove(index);
                storeData(userSettingsList);
            }
        } catch (Exception e){
            throw new RuntimeException ("Problems while deleting from repository");
        }
    }

    public UserSettings getSettings(String chat_id) {
        List<UserSettings> userSettingsList = null;
        try {
            userSettingsList = getData (  );
        } catch (Exception e) {
            throw new RuntimeException ("Problems while getting from repository");
        }
        int index = findIndex( userSettingsList, chat_id);
        return index == -1 ? null : userSettingsList.get(index);

    }

    private int findIndex(List<UserSettings> list, String id ) {
        for (int i = 0; i < list .size(); i++) {
            if (list.get(i).getChatId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    protected abstract List<UserSettings> getData () throws Exception;

    protected abstract void storeData (List<UserSettings> data) throws Exception;

}
