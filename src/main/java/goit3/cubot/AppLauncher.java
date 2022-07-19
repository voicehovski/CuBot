package goit3.cubot;

import goit3.cubot.nbuapi.NBU;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class AppLauncher {
    public static void main(String[] args) {
        try {

            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        } catch (TelegramApiException tae) {
            tae.printStackTrace();
        }

        NBU request = new NBU();
        double usdRate = request.getNBUCurrenciesRate(DOLLAR);
        double eurRate = request.getNBUCurrenciesRate(EURO);

        System.out.println("Курс в НБУ: USD/UAH" + System.lineSeparator() + usdRate);
        System.out.println("Курс в НБУ: EUR/UAH" + System.lineSeparator() + eurRate);
    }
}
