package com.goit.cubot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

import java.util.*;

public class InMemoryListRepository extends Repository{
    private final static String ABSOLUT_PATH = ".\\src\\main\\resources\\users.json";
    private static List<UserSettings> userSettings = new ArrayList<>();



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
        Collection<UserSettings> collection = new Gson().fromJson(bufferedReader, Collection.class);
        List<UserSettings> userSettingsList = new ArrayList<>(collection);
        return userSettingsList;
    }

}


//chatId = update.getMessage().getChatId();