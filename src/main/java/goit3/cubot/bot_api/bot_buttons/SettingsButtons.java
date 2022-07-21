package goit3.cubot.bot_api.bot_buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsButtons {
    public List<List<InlineKeyboardButton>> getSettings() {
        List<List<InlineKeyboardButton>> settingsButton = new ArrayList<>();

        settingsButton.add(Arrays.asList(InlineKeyboardButton.builder()
                .text("Кількість знаків після коми")
                .callbackData("DIGITS")
                .build()));
        settingsButton.add(Arrays.asList(InlineKeyboardButton.builder()
                .text("Банк")
                .callbackData("BANK")
                .build()));
        settingsButton.add(Arrays.asList(InlineKeyboardButton.builder()
                .text("Валюти")
                .callbackData("CURRENCIES")
                .build()));
        settingsButton.add(Arrays.asList(InlineKeyboardButton.builder()
                .text("Час оповіщень")
                .callbackData("TIME")
                .build()));

        return settingsButton;
    }
}
