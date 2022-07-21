package goit3.cubot.bot_api;

import goit3.cubot.Bot;
import goit3.cubot.Currency;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            case "Інфо":
                execute(SendMessage.builder()
                        .text("ПриватБанк UAH/USD\n30\n32")
                        .chatId(chatMessageId)
                        .build());
                break;
            case "Налаштування":
                List<List<InlineKeyboardButton>> settingsButtons = new ArrayList<>();
                for (Settings settings : Settings.values()) {
                    settingsButtons.add(Arrays.asList(InlineKeyboardButton.builder()
                            .text(settings.name())
                            .callbackData(settings.name())
                            .build()));
                }
                execute(SendMessage.builder()
                        .text("Налаштування")
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(settingsButtons).build())
                        .chatId(chatMessageId)
                        .build());
                break;
            case "DIGITS":
                List<List<InlineKeyboardButton>> digitsButtons = new ArrayList<>();

                digitsButtons.add(Arrays.asList(InlineKeyboardButton.builder()
                        .text("2")
                        .callbackData("2")
                        .build()));
                digitsButtons.add(Arrays.asList(InlineKeyboardButton.builder()
                        .text("3")
                        .callbackData("3")
                        .build()));
                digitsButtons.add(Arrays.asList(InlineKeyboardButton.builder()
                        .text("4")
                        .callbackData("4")
                        .build()));

                execute(SendMessage.builder()
                        .text("Оберіть кількість знаків після коми")
                        .chatId(chatMessageId)
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(digitsButtons).build())
                        .build());
                break;
            case "BANK":
                List<List<InlineKeyboardButton>> bankButtons = new ArrayList<>();

                bankButtons.add(Arrays.asList(InlineKeyboardButton.builder()
                        .text("НБУ")
                        .callbackData("НБУ")
                        .build()));
                bankButtons.add(Arrays.asList(InlineKeyboardButton.builder()
                        .text("ПриватБанк")
                        .callbackData("ПриватБанк")
                        .build()));
                bankButtons.add(Arrays.asList(InlineKeyboardButton.builder()
                        .text("Монобанк")
                        .callbackData("Монобанк")
                        .build()));

                execute(SendMessage.builder()
                        .text("Оберіть банк")
                        .chatId(chatMessageId)
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(bankButtons).build())
                        .build());
                break;
            case "TIME":
                TimeNotifications time = new TimeNotifications();
                       time.getKeyboard(callbackQuery);
                break;

            case "CURRENCIES":
                List<List<InlineKeyboardButton>> currenciesButtons = new ArrayList<>();
//                Currency currentCurrency = currencyService.getCurrency(message.getChatId());
                for (Currency currency : Currency.values()) {
                    currenciesButtons.add(Arrays.asList(InlineKeyboardButton.builder()
//                            .text(getCurrencyButton(currentCurrency, currency))
                            .text(currency.name())
                            .callbackData(currency.name())
                            .build()));
                }
                execute(SendMessage.builder()
                        .text("Оберіть валюту")
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(currenciesButtons).build())
                        .chatId(chatMessageId)
                        .build());
                break;
        }
    }

    private void handleMessage(Message message) throws TelegramApiException {
        String chatMessageId = message.getChatId().toString();

        if (message.hasText()) {
            System.out.println(message.getText());
            if (message.getText().equals("/start")) {
                List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

                buttons.add(Arrays.asList(InlineKeyboardButton.builder()
                        .text("Інфо")
                        .callbackData("Інфо")
                        .build()));
                buttons.add(Arrays.asList(InlineKeyboardButton.builder()
                        .text("Налаштування")
                        .callbackData("Налаштування")
                        .build()));

                execute(SendMessage.builder()
                        .text("Вітаю! Цей бот допоможе Вам дізнатися актуальний курс валют")
                        .chatId(chatMessageId)
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                        .build());

            } else {
                execute(SendMessage.builder()
                        .text("Будь ласка, оберіть фунцкію зі списку")
                        .chatId(chatMessageId)
                        .build());
            }
        }
    }

    public void setButtons(SendMessage sendMessage) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("Ку"));

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(new KeyboardButton("Допомога"));

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
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
