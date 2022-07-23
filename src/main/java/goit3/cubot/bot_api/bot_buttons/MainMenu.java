package goit3.cubot.bot_api.bot_buttons;

import goit3.cubot.bot_api.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainMenu extends TelegramBot {
    public static final String INFO = "Інфо";
    public static final String SETTINGS = "Налаштування";

    public void mainMenuButtons(Message message) {
        String chatMessageId = message.getChatId().toString();
        List<List<InlineKeyboardButton>> menuButtons = new ArrayList<>();

        menuButtons.add(Arrays.asList(InlineKeyboardButton.builder()
                .text(INFO)
                .callbackData(INFO)
                .build()));
        menuButtons.add(Arrays.asList(InlineKeyboardButton.builder()
                .text(SETTINGS)
                .callbackData(SETTINGS)
                .build()));
        try {
            execute(SendMessage.builder()
                    .text("Вітаю! Цей бот допоможе Вам дізнатися актуальний курс валют")
                    .chatId(chatMessageId)
                    .replyMarkup(InlineKeyboardMarkup.builder().keyboard(menuButtons).build())
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
