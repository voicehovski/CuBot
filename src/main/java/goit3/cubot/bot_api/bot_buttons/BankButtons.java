package goit3.cubot.bot_api.bot_buttons;

import goit3.cubot.bot_api.TelegramBot;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankButtons extends TelegramBot {

    public List<List<InlineKeyboardButton>> bankList() {
        List<List<InlineKeyboardButton>> bankButtons = new ArrayList<>();

        bankButtons.add(Arrays.asList(InlineKeyboardButton.builder()
                .text("НБУ")
                .callbackData("НБУ")
                .build()));
        bankButtons.add(Arrays.asList(InlineKeyboardButton.builder()
                .text("ПриватБанк")
                .callbackData("ПриватБанк")
                .build()));
        bankButtons.add(Arrays.asList(InlineKeyboardButton.builder()
                .text("Монобанк")
                .callbackData("Монобанк")
                .build()));

        return bankButtons;
    }
}
