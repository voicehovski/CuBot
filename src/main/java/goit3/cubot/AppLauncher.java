package goit3.cubot;

import goit3.cubot.telegram.MessageProcessor;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class AppLauncher {
    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            // запуск бота
            telegramBotsApi.registerBot(new MessageProcessor());
        } catch (TelegramApiException tae) {
            tae.printStackTrace();
        }
    }
}
