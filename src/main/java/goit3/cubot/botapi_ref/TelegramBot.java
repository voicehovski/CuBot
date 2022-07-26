package goit3.cubot.botapi_ref;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
    public static final String BANK = "bank";
    public static final String DIGIT_COUNT = "currency";
    public static final String BANK_SELECTED = "bank selected";

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return null;
    }

    @Override
    public void onUpdateReceived(Update update) {
        CallbackQuery callbackQuery = update .getCallbackQuery();
        Message message = callbackQuery.getMessage();
        String chatMessageId = message.getChatId().toString();
        String action = callbackQuery.getData();

        Selector selector = null;

        switch ( action ) {
            case BANK:
                selector = new BankSelector();
                break;
            case BANK_SELECTED:
                selector = new BankSelector();
                break;
            case DIGIT_COUNT:
                selector = new DigitCountSelector();
                break;
            default:
                System.out.println("Undefined");
        }

        try {
            execute ( selector.buildMessage() );
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void handle (  ) {

    }
}
