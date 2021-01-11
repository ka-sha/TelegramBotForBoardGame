package com.savushkinvyacheslav.commands;

import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class HelpCommand extends ServiceCommand {
    private static final String ANSWER = BOT_SKILLS + "\n\n" +
            "Syntax for first type of command (to calculate probability of a successful dice roll check):\n\n" +
            "dicesYouRoll[+modifier]>=difficultyOfCheck\n" +
            "Where:\n" +
            "dicesYouRoll - set of dices you roll. Each dice has the form like this: dx, where x - number of dice faces. " +
            "Bot supports dices with following faces: 4, 6, 8, 10, 12, 20, 100. All dices listed separated by plus (+) symbol. " +
            "If your dice set contains 2+ identical dices you may unite it by typing that: ydx, where y - amount of identical dices.\n" +
            "modifier - natural number that you need to add to your check. If it's 0 you may not type it.\n" +
            "difficultyOfCheck - natural number that determines the difficulty of the check.\n\n" +
            "Syntax for second type of command (to compare 2 dice sets):\n\n" +
            "dicesYouRoll[+modifier] dicesYouRoll[+modifier]\n" +
            "diceYouRoll - see above\n" +
            "modifier - see above\n";

    public HelpCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        sendMessage(absSender, chat.getId(), ANSWER);
    }
}
