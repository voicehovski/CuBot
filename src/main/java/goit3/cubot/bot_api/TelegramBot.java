package goit3.cubot.bot_api;

import goit3.cubot.Bot;
import goit3.cubot.Currency;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TelegramBot extends Bot {
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
        String action = callbackQuery.getData();
        System.out.println(action);

        switch (action) {
            case "Інфо":
                execute(SendMessage.builder()
                        .text("ПриватБанк UAH/USD\n30\n32")
                        .chatId(message.getChatId().toString())
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
                        .chatId(message.getChatId().toString())
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
                        .chatId(message.getChatId().toString())
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
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(bankButtons).build())
                        .build());
                break;
            case "TIME":
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
                        .chatId(message.getChatId().toString())
                        .build());
                break;
        }
    }

    private void handleMessage(Message message) throws TelegramApiException {

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
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                        .build());

            } else {
                execute(SendMessage.builder()
                        .text("Будь ласка, оберіть фунцкію зі списку")
                        .chatId(message.getChatId().toString())
                        .build());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "java_core_6_bot";
    }

    @Override
    public String getBotToken() {
        return "5272943909:AAF-YA8RaWrmUuIS87VN0GGCvHKGl8yWmLE";
    }
}
