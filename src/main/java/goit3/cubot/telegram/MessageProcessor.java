package goit3.cubot.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MessageProcessor extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "goit_2022_bot";
    }

    @Override
    public String getBotToken() {
        return "5550058796:AAElAWvwt7AyIM2y4nSaZOl1rFH0iZE9Tqk";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();  // String
            Long chatId = update.getMessage().getChatId();

            // SendMessage message = createMessage ( messageText, chatId );
            // SendMessage message = createKeyboard(new String [] {"Ok","Yes","Done","Cancel","No"}, 3);
            SendMessage message = new SendMessage();
            message.setReplyMarkup(createInlineKeyboard(new String [] {"Ok","Yes","Done","Cancel","No"}, 3));
            message.setText("Keyboard");
            message.setChatId(chatId);


            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.hasCallbackQuery()) {
            update.getCallbackQuery();  // CallbackQuery
            update.getCallbackQuery().getMessage(); // Message
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
            update.getCallbackQuery().getData();    // String
            Long chatId = update.getCallbackQuery().getMessage().getChatId(); // long

            EditMessageReplyMarkup editedMessage = new EditMessageReplyMarkup();
            editedMessage.setChatId(chatId);
            editedMessage.setMessageId(messageId);
            editedMessage.setReplyMarkup(createInlineKeyboard(new String [] {"Yes","Yes","Yes","No","No"}, 3));

            try {
                execute(editedMessage); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private SendMessage createMessage ( String msg, Long chatId ) {
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.setChatId(chatId.toString());
        message.setText(msg);
        return message;
    }

    private SendMessage createKeyboard (String [] names, int rowSize) {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = null;
        for (int i = 0; i < names .length; i++) {
            if (i % rowSize == 0) {
                row = new KeyboardRow();
                rowList.add (row);
            }
            row.add(names[i]);
        }
        markup.setKeyboard(rowList);

        SendMessage keyboard = new SendMessage();
        keyboard.setReplyMarkup(markup);
        return keyboard;
    }
    private InlineKeyboardMarkup createInlineKeyboard(String[] names, int rowSize) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        for (int i = 0; i < names .length; i++) {
            if (i % rowSize == 0) {
                row = new ArrayList<>();
                keyboard.add(row);
            }
            InlineKeyboardButton button = new InlineKeyboardButton(names[i]);
            //.setText(names[i])
            button.setCallbackData(names[i]);
            row.add(button);
        }
        markup.setKeyboard(keyboard);
        return markup;
    }

    private void processMessage (String command, long chatId) {
        Command c = null;
        switch (command) {
            case "/main":
                c = new MainCommand(chatId);
                break;
            case "/settings":
                c = new SettingsCommand(chatId);
                break;
            default:
                throw new RuntimeException("Unsupported command " + command);
        }
    }
    private void processCallback (String command, String argument, long chatId) {
        /*
        * SaveCurrency
        * CurrencySaved
        *
        * Time
        *
        * Digits
        *
        * Bank
        *
        * Unschedule
        *
        * --
        *
        * Start
        * Main
        * Settings
        * SelectBank
        * SelectCurrency
        * SelectDigitCount
        * SelectScheduleTime
        *
        * Info
        * */
        Queue<Command> commandQueue = new LinkedList<>();
        switch (command) {
            case "/save_bank":
                if (new SaveBankCommand (argument, chatId).execute()){
                    new BankSavedCommand (argument, chatId);
                } else {
                    throw new RuntimeException("Can`t save bank setting " + argument);
                }
                break;
            case "/save_digits":
                commandQueue.add(new SaveDigitsCommand(argument, chatId));
                commandQueue.add(new DigitsSavedCommand(argument, chatId));
                break;
            default:
                throw new RuntimeException("Unsupported command " + command);
        }
    }
}
