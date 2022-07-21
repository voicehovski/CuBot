package goit3.cubot.bot_api.bot_buttons;

import goit3.cubot.bot_api.TelegramBot;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DigitsAfterComma extends TelegramBot {
    public List<List<InlineKeyboardButton>> getDigits() {
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

        return digitsButtons;
    }
}
