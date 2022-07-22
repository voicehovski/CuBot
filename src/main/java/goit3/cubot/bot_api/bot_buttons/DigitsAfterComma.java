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

public class DigitsAfterComma extends TelegramBot {
    public void getDigits(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String chatMessageId = message.getChatId().toString();
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

        try {
            execute(SendMessage.builder()
                    .text("Оберіть кількість знаків після коми")
                    .chatId(chatMessageId)
                    .replyMarkup(InlineKeyboardMarkup.builder().keyboard(digitsButtons).build())
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
