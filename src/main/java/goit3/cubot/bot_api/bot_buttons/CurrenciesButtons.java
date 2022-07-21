package goit3.cubot.bot_api.bot_buttons;

import goit3.cubot.Currency;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CurrenciesButtons {

    public List<List<InlineKeyboardButton>> getCurrency() {
        List<List<InlineKeyboardButton>> currenciesButtons = new ArrayList<>();
//                Currency currentCurrency = currencyService.getCurrency(message.getChatId());
        for (Currency currency : Currency.values()) {
            currenciesButtons.add(Arrays.asList(InlineKeyboardButton.builder()
//                            .text(getCurrencyButton(currentCurrency, currency))
                    .text(currency.name())
                    .callbackData(currency.name())
                    .build()));
        }
        return currenciesButtons;
    }
}
