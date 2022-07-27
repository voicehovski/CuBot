package goit3.cubot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import goit3.cubot.botapi_ref.BankButtonMenu;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class JsonFileRepository extends Repository {

    private final static String ABSOLUT_PATH = ".\\src\\main\\resources\\users.json";

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

        //Collection<UserSettings> collection = new Gson().fromJson(bufferedReader, Collection.class);
        //List<UserSettings> userSettingsList = new ArrayList<>(collection);

        Type listElementType = new TypeToken<List<UserSettings>>(){} .getType (  );
        Gson gson = new Gson();
        return gson .fromJson ( bufferedReader, listElementType );
    }


    @Override
    protected List<UserSettings> getData() throws Exception {
        return readFile(  );
    }

    @Override
    protected void storeData(List<UserSettings> data) throws Exception {
        writeFile(data);
    }

    public static void main(String[] args) {
        Repository r = new JsonFileRepository();
        UserSettings us1 = UserSettings.createDefault("100");
        UserSettings us2 = new UserSettings(
                "101",
                3,
                BankButtonMenu.NBU,
                9,
                Arrays.asList(Currency.EUR, Currency.USD)
        );
        UserSettings us3 = new UserSettings(
                "103",
                4,
                BankButtonMenu.MONOBANK,
                18,
                Arrays.asList(Currency.EUR)
        );
        UserSettings us4 = new UserSettings(
                "100",
                2,
                BankButtonMenu.MONOBANK,
                12,
                Arrays.asList(Currency.EUR)
        );

        try {
            //r .storeData ( Arrays.asList(us1, us2, us3) );
           //List<UserSettings> readed = r.getData ();
            //System.out.println(readed);

            r .add (us1);
            r .add (us2);
            System.out.println(r.getData());
            System.out.println(r.getSettings("101"));
            r.delete("101");
            System.out.println(r.getSettings("101"));
            r .add(us4);
            System.out.println(r.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}