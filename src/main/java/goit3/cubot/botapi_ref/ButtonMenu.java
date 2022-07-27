package goit3.cubot.botapi_ref;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ButtonMenu {
    protected ButtonAttributes [] buttons;

    public ButtonMenu(ButtonAttributes [] buttonAttributes ) {
        buttons = buttonAttributes;
    }

    public ButtonMenu(ButtonAttributes [] buttonAttributes, String selectedButtonName ) {
        buttons = buttonAttributes;
        for (ButtonAttributes ba : buttons) {
            if (ba.eq(selectedButtonName)){
                ba .setSelected(true);
                break;
            }
        }
    }
    public ButtonMenu(ButtonAttributes [] buttonAttributes, List<String> selectedNames ) {
        buttons = buttonAttributes;
        for (ButtonAttributes ba : buttons) {
            for (String name : selectedNames) {
                if (ba.eq(name)) {
                    ba.setSelected(true);
                }
            }
        }
    }

    // todo Реализовать setSelected для один/несколько, множественный/единичный. Возможно в конструкторе

    public SendMessage buildMessage ( String header, String chatMessageId ) {

        List<List<InlineKeyboardButton>> bankButtons = new ArrayList<>();
        for ( ButtonAttributes buttonAttributes : buttons ) {
            bankButtons.add(createButton(buttonAttributes.getFormattedTitle(), buttonAttributes.callbackData));
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
