package goit3.cubot.bot_api.bot_buttons;

import goit3.cubot.Currency;
import goit3.cubot.bot_api.currency_logic.BotService;
import goit3.cubot.bot_api.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CurrenciesButtons extends TelegramBot {
    private final BotService currencyBotService = BotService.getInstance();

    public void getCurrenciesList(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String chatMessageId = message.getChatId().toString();

        List<List<InlineKeyboardButton>> currenciesButtons = new ArrayList<>();
        for (Currency currency : Currency.values()) {
            currenciesButtons.add(Arrays.asList(InlineKeyboardButton.builder()
                    .text(currency.name())
                    .callbackData(currency.name())
                    .build()));
        }
        try {
            execute(SendMessage.builder()
                    .text("Оберіть валюту")
                    .replyMarkup(InlineKeyboardMarkup.builder().keyboard(currenciesButtons).build())
                    .chatId(chatMessageId)
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void getCurrentCurrency(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        Long chatId = message.getChatId();
        String chatMessageId = chatId.toString();
        Currency newCurrency = Currency.valueOf(callbackQuery.getData());
        currencyBotService.setCurrency(chatId, newCurrency);

        List<List<InlineKeyboardButton>> currenciesButtons = new ArrayList<>();
        Currency currentCurrency = currencyBotService.getCurrency(chatId);
        for (Currency currency : Currency.values()) {
            currenciesButtons.add(Arrays.asList(
                    InlineKeyboardButton.builder()
                            .text(getCurrencyButton(currentCurrency, currency))
                            .callbackData(currency.name())
                            .build()));
        }
        try {
            execute(EditMessageReplyMarkup.builder()
                    .chatId(chatMessageId)
                    .messageId(message.getMessageId())
                    .replyMarkup(InlineKeyboardMarkup.builder().keyboard(currenciesButtons).build())
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    protected String getCurrencyButton(Currency saved, Currency current) {
        return saved == current ? "✅ " + current : current.name();
    }
}
