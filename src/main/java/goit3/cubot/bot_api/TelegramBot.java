package goit3.cubot.bot_api;

import goit3.cubot.Bot;
import goit3.cubot.bot_api.bot_buttons.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static goit3.cubot.bot_api.bot_buttons.BankButtons.*;
import static goit3.cubot.bot_api.bot_buttons.DigitsAfterComma.*;
import static goit3.cubot.bot_api.bot_buttons.MainMenu.*;
import static goit3.cubot.bot_api.bot_buttons.SettingsButtons.*;
import static goit3.cubot.bot_api.bot_buttons.TimeNotificationsMenu.*;

public class TelegramBot extends Bot {
    public static final String BOT_NAME = "java_core_6_bot";
    public static final String BOT_TOKEN = "5272943909:AAF-YA8RaWrmUuIS87VN0GGCvHKGl8yWmLE";

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasCallbackQuery()) {
            try {
                handleCallback(update.getCallbackQuery());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else if (update.hasMessage()) {
            try {
                handleMessage(update.getMessage());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handleCallback(CallbackQuery callbackQuery) throws TelegramApiException {
        Message message = callbackQuery.getMessage();
        String chatMessageId = message.getChatId().toString();
        String action = callbackQuery.getData();
        System.out.println(action);

        switch (action) {
            case INFO:
                execute(SendMessage.builder()
                        // вказати дані користувача
//                        .text(String.format(
//                                "%s %s\n%d\n%d",
//                                User.getBank, User.getCurrency, User.getBank.getBuy, User.getBank.getSell))
                        .text("ПриватБанк UAH/USD\n30\n32")
                        .chatId(chatMessageId)
                        .build());
                break;
            case SETTINGS:
                SettingsButtons settingsButtons = new SettingsButtons();
                settingsButtons.getSettings(callbackQuery);
                break;
            case DIGITS_AFTER_COMMA:
                DigitsAfterComma digitsAfterComma = new DigitsAfterComma();
                digitsAfterComma.getDigits(callbackQuery);
                break;
            case TWO_DIGITS:
            case THREE_DIGITS:
            case FOUR_DIGITS:
                DigitsAfterComma setDigit = new DigitsAfterComma();
                setDigit.getCurrentDigit(callbackQuery);
                break;
            case BANK:
                BankButtons bankButtons = new BankButtons();
                bankButtons.bankList(callbackQuery);
                break;
            case NBU:
            case PRIVATBANK:
            case MONOBANK:
                BankButtons setBank = new BankButtons();
                setBank.getCurrentBank(callbackQuery);
                break;
            case TIME_NOTIFICATION:
                TimeNotificationsMenu notificationTime = new TimeNotificationsMenu();
                notificationTime.getKeyboard(callbackQuery);
                break;
            case CURRENCIES:
                CurrenciesButtons currenciesButtons = new CurrenciesButtons();
                currenciesButtons.getCurrenciesList(callbackQuery);
                break;
            case "USD":
            case "EUR":
               CurrenciesButtons currencyButton = new CurrenciesButtons();
               currencyButton.getCurrentCurrency(callbackQuery);
                break;
        }
    }

    public void handleMessage(Message message) throws TelegramApiException {
        String chatMessageId = message.getChatId().toString();

        if (message.hasText()) {
            String userText = message.getText();
            System.out.println(userText);
            if (userText.equals("/start")) {
                MainMenu mainMenu = new MainMenu();
                mainMenu.mainMenuButtons(message);
            } else if (isDigit(userText)) {
                int userTime = Integer.parseInt(userText);
                if (isTimeAvailable(userTime)) {
                    execute(SendMessage.builder()
                            .text(String.format(VALID_TIME, userTime))
                            .chatId(chatMessageId)
                            .build());
                } else {
                    execute(SendMessage.builder()
                            .text(String.format(INVALID_TIME, userTime))
                            .chatId(chatMessageId)
                            .build());
                }
            } else if (userText.equals(TURN_OFF_NOTIFICATION)) {
                execute(SendMessage.builder()
                        .text("Оповіщення вимкнені")
                        .chatId(chatMessageId)
                        .build());
            } else {
                execute(SendMessage.builder()
                        .text("Будь ласка, оберіть фунцкію зі списку")
                        .chatId(chatMessageId)
                        .build());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}