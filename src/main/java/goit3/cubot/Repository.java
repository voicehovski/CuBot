import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<UserSettings> userSettings = new ArrayList<>();

    public boolean contains(int chat_id){
        if (userSettings.contains(chat_id)) return true;
        else return false;
    };

    public void add(int chat_id, UserSettings setting){
        userSettings.add(new UserSettings(chat_id,setting));
    };

    public void delete(int chat_id){
        userSettings.remove(chat_id);
    };

    public UserSettings getSetting(int chat_id){
        return userSettings.get(chat_id);
    };
}
