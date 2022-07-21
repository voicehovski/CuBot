package goit3.cubot.bot_api;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;

public class TimeNotifications extends TelegramBot {

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

            row1.add("9");
            row1.add("10");
            row1.add("11");

            row2.add("12");
            row2.add("13");
            row2.add("14");

            row3.add("15");
            row3.add("16");
            row3.add("17");

            row4.add("18");
            row4.add("Вимкнути сповіщення");

            keyboard.add(row1);
            keyboard.add(row2);
            keyboard.add(row3);
            keyboard.add(row4);
            keyboardMarkup.setKeyboard(keyboard);

            // Create a message object
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatMessageId);
            sendMessage.setReplyMarkup(keyboardMarkup);
            sendMessage.setText("Оберіть час сповіщення");
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.getStackTrace();
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

    @Override
    public void onUpdateReceived(Update update) {

    }
}
