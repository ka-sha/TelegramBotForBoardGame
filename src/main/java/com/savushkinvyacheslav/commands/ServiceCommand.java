package com.savushkinvyacheslav.commands;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class ServiceCommand extends BotCommand {
    protected static final String BOT_SKILLS = "The bot can:\n" +
            "1) calculate the probability of a successful dice roll\n" +
            "2) compare 2 dice sets to determine the best one for success";

    public ServiceCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    protected void sendMessage(AbsSender absSender, Long chatId, String answer) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText(answer);
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            System.out.println("god damn son. I know that not good handling of exception. May be some time I will add logging.");
        }
    }
}
