package com.savushkinvyacheslav.commands;

import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class StartCommand extends ServiceCommand {
    private static final String ANSWER = BOT_SKILLS + "\n" +
            "Example for 1: d12+3d8+10>=16\n" +
            "Example for 2: d10+d4+2 d12+d6+3\n\n" +
            "For more information type /help";

    public StartCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        sendMessage(absSender, chat.getId(), ANSWER);
    }
}
