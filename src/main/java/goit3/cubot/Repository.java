package goit3.cubot;
import java.io.*;
import java.util.*;

public class Repository {
    private static Map<Long, UserSettings> userSettingsMap = new HashMap<>();
    private final static String ABSOLUT_PATH = ".\\src\\main\\resources\\users.json";


    public static void add(int chat_id, UserSettings setting) throws Exception {

        List<UserSettings> userSettings = InMemoryListRepository.readFile();
        if (!userSettings.contains(chat_id)){
            userSettings.add( setting);
            InMemoryListRepository.writeFile(userSettings);
        }
        else {
            System.out.println("Oops...");
        }

    }


    public static void delete(long chat_id) throws IOException {
        List<UserSettings> userSettings = InMemoryListRepository.readFile();
        if (userSettings.contains(chat_id)){
            userSettings.remove(chat_id);
            InMemoryListRepository.writeFile(userSettings);
        }
        else {
            System.out.println("Oops...");
        }
    }

    public static UserSettings getSetting(int chat_id) throws FileNotFoundException {
        List<UserSettings> userSettings = InMemoryListRepository.readFile();
        if (userSettings.contains(chat_id)) return userSettings.get(chat_id);
        else return null;
    }
}