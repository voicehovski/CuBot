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

public class BankButtons extends TelegramBot {
    public static final String NBU = "НБУ";
    public static final String PRIVATBANK = "ПриватБанк";
    public static final String MONOBANK = "Монобанк";

    public void bankList(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String chatMessageId = message.getChatId().toString();
        List<List<InlineKeyboardButton>> bankButtons = new ArrayList<>();

        bankButtons.add(Arrays.asList(InlineKeyboardButton.builder()
                .text(NBU)
                .callbackData(NBU)
                .build()));
        bankButtons.add(Arrays.asList(InlineKeyboardButton.builder()
                .text(PRIVATBANK)
                .callbackData(PRIVATBANK)
                .build()));
        bankButtons.add(Arrays.asList(InlineKeyboardButton.builder()
                .text(MONOBANK)
                .callbackData(MONOBANK)
                .build()));

        try {
            execute(SendMessage.builder()
                    .text("Оберіть банк")
                    .chatId(chatMessageId)
                    .replyMarkup(InlineKeyboardMarkup.builder().keyboard(bankButtons).build())
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
