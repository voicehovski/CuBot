package com.goit.cubot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

import java.util.*;

public class InMemoryListRepository extends Repository{
    private final static String ABSOLUT_PATH = ".\\src\\main\\resources\\users.json";
    private static Map<Long, UserSettings> userSettingsMap = new HashMap<>();


    public static void writeFile() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(userSettingsMap);

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(ABSOLUT_PATH))){
            printWriter.write(json);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void writeFile(List<UserSettings> userSettings) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(userSettings);

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(ABSOLUT_PATH))){
            printWriter.write(json);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static List<UserSettings> readFile() throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(ABSOLUT_PATH));
        Gson gson = new Gson();
        HashMap<Long, UserSettings> json = gson.fromJson(bufferedReader, HashMap.class);
        Collection<UserSettings> collection = json.values();
        List<UserSettings> userSettingsList = new ArrayList<>(collection);
        return userSettingsList;
    }

}


//chatId = update.getMessage().getChatId();