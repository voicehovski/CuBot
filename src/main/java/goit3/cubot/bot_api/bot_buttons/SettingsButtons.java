package goit3.cubot.bot_api.bot_buttons;

import goit3.cubot.bot_api.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static goit3.cubot.bot_api.bot_buttons.MainMenu.*;

public class SettingsButtons extends TelegramBot {
    public static final String DIGITS_AFTER_COMMA = "знаки після коми";
    public static final String BANK = "банк";
    public static final String TIME_NOTIFICATION = "час";
    public static final String CURRENCIES = "валюти";

    public void getSettings(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String chatMessageId = message.getChatId().toString();

        List<List<InlineKeyboardButton>> settingsButton = new ArrayList<>();

        settingsButton.add(Arrays.asList(InlineKeyboardButton.builder()
                .text("Кількість знаків після коми")
                .callbackData(DIGITS_AFTER_COMMA)
                .build()));
        settingsButton.add(Arrays.asList(InlineKeyboardButton.builder()
                .text("Банк")
                .callbackData(BANK)
                .build()));
        settingsButton.add(Arrays.asList(InlineKeyboardButton.builder()
                .text("Валюти")
                .callbackData(CURRENCIES)
                .build()));
        settingsButton.add(Arrays.asList(InlineKeyboardButton.builder()
                .text("Час оповіщень")
                .callbackData(TIME_NOTIFICATION)
                .build()));

        try {
            execute(SendMessage.builder()
                    .text(SETTINGS)
                    .replyMarkup(InlineKeyboardMarkup.builder().keyboard(settingsButton).build())
                    .chatId(chatMessageId)
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}
