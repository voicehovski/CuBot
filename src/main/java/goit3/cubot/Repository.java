package goit3.cubot;
import java.io.*;
import java.util.*;


public class Repository {
    private static List<UserSettings> userSettingsList = new ArrayList<>();
    private final static String ABSOLUT_PATH = ".\\CuBot\\src\\main\\java\\goit3\\cubot\\users.json";


    public static void add(int chat_id, UserSettings setting) throws Exception {
        List<UserSettings> userSettings = InMemoryListRepository.readFile();
        try {
            if (!userSettings.contains(chat_id)){
                userSettingsList.add(setting);
                writeFile(userSettingsList);
            }
        }catch (Exception e){
            System.out.println("User with " + chat_id + " id already exists");
        }

    }


    public static void delete(long chat_id) throws IOException {
        try {
            userSettingsList.remove(chat_id);
            writeFile(userSettingsList);

        }catch (Exception e){
            System.out.println("User with " + chat_id + " id doesn't exist");
        }

    }

    public static UserSettings getSetting(int chat_id) {
        return userSettingsList.get(chat_id);
    }
}
