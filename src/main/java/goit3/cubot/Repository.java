package goit3.cubot;

import java.util.*;

public abstract class Repository {
    public void add(String chat_id, UserSettings setting) {
        try {
            List<UserSettings> userSettingsList = getData (  );
            UserSettings us = find ( userSettingsList, chat_id);
            if (us != null){
                throw new RuntimeException ("Attempt to write existed user settings");
            }
            userSettingsList.add(setting);
            storeData(userSettingsList);
        } catch (Exception e){
            throw new RuntimeException ("Problems while adding to repository");
        }

    }

    public void delete (String chat_id) {
        try {
            List<UserSettings> userSettingsList = getData (  );
            userSettingsList.remove(chat_id);
            storeData(userSettingsList);
        } catch (Exception e){
            throw new RuntimeException ("Problems while deleting from repository");
        }
    }

    public UserSettings getSetting (String chat_id) {
        List<UserSettings> userSettingsList = null;
        try {
            userSettingsList = getData (  );
        } catch (Exception e) {
            throw new RuntimeException ("Problems while getting from repository");
        }
        return find ( userSettingsList, chat_id);
    }

    private UserSettings find ( List<UserSettings> list, String id ) {
        for (UserSettings us : list) {
            if (us .getChatId().equals(id)) {
                return us;
            }
        }
        return null;
    }

    protected abstract List<UserSettings> getData () throws Exception;

    protected abstract void storeData (List<UserSettings> data) throws Exception;
}
