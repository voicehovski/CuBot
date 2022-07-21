package goit3.cubot.bot_api.bot_buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainMenu {
    public List<List<InlineKeyboardButton>> mainMenuButtons() {
        List<List<InlineKeyboardButton>> menuButtons = new ArrayList<>();

        menuButtons.add(Arrays.asList(InlineKeyboardButton.builder()
                .text("Інфо")
                .callbackData("Інфо")
                .build()));
        menuButtons.add(Arrays.asList(InlineKeyboardButton.builder()
                .text("Налаштування")
                .callbackData("Налаштування")
                .build()));

        return menuButtons;
    }
}
