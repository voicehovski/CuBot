package goit3.cubot;

import goit3.cubot.bot_api.TelegramBot;
import goit3.cubot.nbuapi.NBU;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class AppLauncher {
    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            // запуск бота
//            telegramBotsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException tae) {
            tae.printStackTrace();
        }

        NBU request = new NBU();
        double usdRate;
        double eurRate;
        try {
            usdRate = request.getNBUCurrenciesRate(Currency.USD.name());
            eurRate = request.getNBUCurrenciesRate(Currency.EUR.name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Курс в НБУ: USD/UAH" + System.lineSeparator() + usdRate);
        System.out.println("Курс в НБУ: EUR/UAH" + System.lineSeparator() + eurRate);
    }
}
