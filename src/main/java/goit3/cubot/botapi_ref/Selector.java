package goit3.cubot.botapi_ref;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Selector {
    protected ButtonAttributes [] buttons;

    public Selector ( ButtonAttributes [] buttonAttributes ) {
        buttons = buttonAttributes;
    }

    public SendMessage buildMessage ( /*ButtonAttributes [] buttons*/ ) {
        String header = "";
        long chatMessageId = 0L;

        List<List<InlineKeyboardButton>> bankButtons = new ArrayList<>();
        for ( ButtonAttributes buttonAttributes : buttons ) {
            bankButtons.add(createButton(buttonAttributes.title, buttonAttributes.callbackData));
        }

        return SendMessage.builder()
                    .text(header)
                    .chatId(chatMessageId)
                    .replyMarkup(InlineKeyboardMarkup.builder().keyboard(bankButtons).build())
                    .build();
    }

    public List<InlineKeyboardButton> createButton (String text, String callbackData ) {
        return Arrays.asList(InlineKeyboardButton.builder()
                .text(text)
                .callbackData(callbackData)
                .build());
    }
}
