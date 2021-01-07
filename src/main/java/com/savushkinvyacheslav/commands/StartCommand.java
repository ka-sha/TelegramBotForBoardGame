package com.savushkinvyacheslav.commands;

import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class StartCommand extends ServiceCommand {

    //TODO: rewrite ANSWER
    private static final String ANSWER = "Hello, this bot calculates the probability of a successful dice roll check" +
            "\nTo succeed at the check, the result of your dice roll and modifiers " +
            "must be greater than or equal to the difficulty of the check.\n" +
            "In order for the bot to calculate, " +
            "you need to send it a message in the following format:" +
            "\n\ndicesYouRoll+\\[-]modifier>=difficultyOfCheck" +
            "\n\nWhere:" +
            "\ndicesYouRoll - set of dices you roll. Each dice has the form like this: dx, where x - number of dice faces. " +
            "Bot supports dices with following faces: 4, 6, 8, 10, 12, 20, 100. All dices listed separated by plus (+) symbol. " +
            "If your dice set contains 2+ identical dices you may unite it by typing that: ydx, where y - amount of identical dices. " +
            "\nmodifier - Integer number that you need to add to your check. If it's positive - type \"+\" before modifier, " +
            "if it's negative - type \"-\" before modifier, if it's 0 you may not type it at all." +
            "\ndifficultyOfCheck - natural number that determines the difficulty of the check.";

    public StartCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        sendMessage(absSender, chat.getId(), ANSWER);
    }
}
