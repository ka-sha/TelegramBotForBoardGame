package com.savushkinvyacheslav;

import com.savushkinvyacheslav.commands.*;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CalculatorOfProbabilityBot extends TelegramLongPollingCommandBot {

    public CalculatorOfProbabilityBot() {
        super();
        register(new StartCommand("start", "Start"));
        register(new HelpCommand("help", "Help"));
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Message message = update.getMessage();

        if (isValidMessageAnswer(message)) {
            Long chatId = message.getChatId();
            String text = message.getText();
            String answer = AnswerFormatter.getAnswer(text);
            sendAnswer(chatId, answer);
        }
    }

    private boolean isValidMessageAnswer(Message message) {
        return message != null && message.hasText();
    }

    private void sendAnswer(Long chatId, String answer) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(answer);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println("future log");
        }
    }

    @Override
    public String getBotUsername() {
        return "calculatorOfProbabilityBot";
    }

    @Override
    public String getBotToken() {
        return "1473964699:AAGKpHK6YeeUgb32YEVcTpZxCYvw7vvYVJU";
    }
}