package com.savushkinvyacheslav;

import org.telegram.telegrambots.*;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class BotInitializer {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new CalculatorOfProbabilityBot());
        }
        catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
