package goit3.cubot.botapi_ref;

import goit3.cubot.Currency;
import goit3.cubot.UserSettings;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Arrays;

public class TelegramBot extends TelegramLongPollingBot {
    public static final String BANK_SELECTED = "bank selected";

    @Override
    public String getBotUsername() {
        return "test998_goit_3_bot";
    }

    @Override
    public String getBotToken() {
        return "5506749029:AAH3aE3HW3kR7dQ0euEsMOjy8ng83vU5HSE";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = null;
        String chatMessageId = null;
        ButtonMenu buttonMenu = null;
        UserSettings userSettings = null;

        if (update.hasMessage()) {
            message = update .getMessage();
            if (message.hasText()) {
                String userText = message.getText();
                chatMessageId = message.getChatId().toString();
                //System.out.println(userText);
                if (userText.equals("/start")) {
                    buttonMenu = new MainMenu();
                    userSettings = UserSettings.createDefault(chatMessageId);
                }
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update .getCallbackQuery();
            message = callbackQuery.getMessage();
            chatMessageId = message.getChatId().toString();
            String action = callbackQuery.getData();

            // Надо читать из репо и возвращать дефолт только в случае отсутствия
            if (userSettings == null) {
                userSettings = UserSettings.createDefault(chatMessageId);
            }

            switch ( action ) {
                case MainMenu.GET_INFO:
                    // Здесь выводить в соответвии с настройками и эта же функция будет по таймеру
                    break;
                case MainMenu.SETTINGS:
                    buttonMenu = new SettingsMenu();
                    break;
                case BankButtonMenu.MENU:
                    buttonMenu = new BankButtonMenu(userSettings.getBankName());
                    break;
                case BankButtonMenu.PRIVATBANK:
                case BankButtonMenu.MONOBANK:
                case BankButtonMenu.NBU:
                    userSettings .setBankName (action);
                    buttonMenu = new BankButtonMenu(userSettings.getBankName());
                    break;
                case DigitCountMenu.MENU:
                    buttonMenu = new DigitCountMenu();
                    break;
                case CurrencyMenu.MENU:
                    buttonMenu = new CurrencyMenu(userSettings.getCurrencies());
                    break;
                case CurrencyMenu.EUR:
                case CurrencyMenu.USD:
                    userSettings.toggleCurrency(Currency.valueOf(action));
                    buttonMenu = new CurrencyMenu(userSettings.getCurrencies());
                    break;
                case ScheduleMenu.MENU:
                    buttonMenu = new ScheduleMenu();
                    break;

                default:
                    System.out.println("Undefined");
            }
        }

        try {
            execute ( buttonMenu.buildMessage("Initial hallo (Привет!)", chatMessageId) );
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            TelegramBot bot = new goit3.cubot.botapi_ref.TelegramBot();
            BotSession session = telegramBotsApi.registerBot(bot);
            System.out.println("Bot ends");
            //session .stop();
        } catch ( TelegramApiException tae ) {
            tae .printStackTrace();
        }
    }
}
