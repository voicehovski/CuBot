package com.goit.cubot.bot_api.bot_buttons;

import com.goit.cubot.bot_api.TelegramBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;

public class TimeNotificationsMenu extends TelegramBot {
    public static final String VALID_TIME = "Час, обраний вами %d:00";
    public static final String INVALID_TIME = "Час, обраний вами %d:00, недоступний. Будь ласка, оберіть зі списку";
    public static final String TURN_OFF_NOTIFICATION = "Вимкнути сповіщення";

    public void getKeyboard(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String chatMessageId = message.getChatId().toString();

        try {
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            List<KeyboardRow> keyboard = new ArrayList<>();
            KeyboardRow row1 = new KeyboardRow();
            KeyboardRow row2 = new KeyboardRow();
            KeyboardRow row3 = new KeyboardRow();
            KeyboardRow row4 = new KeyboardRow();

            // first row
            row1.add("9");
            row1.add("10");
            row1.add("11");

            // second row
            row2.add("12");
            row2.add("13");
            row2.add("14");

            // third row
            row3.add("15");
            row3.add("16");
            row3.add("17");

            // fourth row
            row4.add("18");
            row4.add(TURN_OFF_NOTIFICATION);

            keyboard.add(row1);
            keyboard.add(row2);
            keyboard.add(row3);
            keyboard.add(row4);
            keyboardMarkup.setKeyboard(keyboard);

            // Create a message object
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatMessageId);
            sendMessage.setReplyMarkup(keyboardMarkup);
            sendMessage.setText("Оберіть час оповіщення");
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.getStackTrace();
        }
    }

    public static boolean isDigit(String userText) throws NumberFormatException {
        try {
            Integer.parseInt(userText);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isTimeAvailable(int userTime) {
        return userTime >= 9 && userTime <= 18;
    }
}
