package goit3.cubot;

import goit3.cubot.nbuapi.NBU;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class AppLauncher {
    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        } catch (TelegramApiException tae) {
            tae.printStackTrace();
        }
    }
}
