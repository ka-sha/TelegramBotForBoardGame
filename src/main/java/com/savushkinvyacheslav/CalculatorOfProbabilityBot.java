package com.savushkinvyacheslav;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.text.DecimalFormat;
import java.util.regex.*;

public class CalculatorOfProbabilityBot extends TelegramLongPollingBot {

    private static final String MANUAL = "In order for the bot to calculate, " +
            "you need to send it a message in the following format:" +
            "\n\ndicesYouRoll+\\[-]modifier>=difficultyOfCheck" +
            "\n\nWhere:" +
            "\ndicesYouRoll - set of dices you roll. Each dice has the form like this: dx, where x - number of dice faces. " +
            "Bot supports dices with following faces: 4, 6, 8, 10, 12, 20, 100. All dices listed separated by plus (+) symbol. " +
            "If your dice set contains 2+ identical dices you may unite it by typing that: ydx, where y - amount of identical dices. " +
            "\nmodifier - Integer number that you need to add to your check. If it's positive - type \"+\" before modifier, " +
            "if it's negative - type \"-\" before modifier, if it's 0 you may not type it at all." +
            "\ndifficultyOfCheck - natural number that determines the difficulty of the check.";

    private static final String START_MESSAGE = "Hello, this bot calculates the probability of a successful dice roll check" +
            "\nTo succeed at the check, the result of your dice roll and modifiers " +
            "must be greater than or equal to the difficulty of the check.\n" + MANUAL;

    private static final String HELP_MESSAGE = MANUAL +
            "\nSome examples of right message format:" +
            "\nd4>=2" +
            "\nd4+d12>=15" +
            "\n4d4+4d10>=45";

    private static final String INCORRECT_DATA_MESSAGE = "Incorrect message entered" +
            "\nType /help for more information";

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            String text = message.getText();
            String chatId = message.getChatId().toString();

            if (text.equals("/start")) {
                sendMsg(chatId, START_MESSAGE);
                return;
            }

            if (text.equals("/help")) {
                sendMsg(chatId, HELP_MESSAGE);
                return;
            }

            Pattern pattern = Pattern.compile("^((([1-9]*)[d])([468]|10|12|20|100)([+]?))+(|([+-])(\\d+))([>][=]\\d+)$");
            Matcher matcher = pattern.matcher(text);

            sendMsg(chatId, matcher.find() ?
                    parseAndCount(text) + " %" :
                    INCORRECT_DATA_MESSAGE);
        }
    }

    private void sendMsg(String chatId, String answer) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String parseAndCount(String expression) {
        String[] partsOfExpression = expression.split(">=");
        int boarder = Integer.parseInt(partsOfExpression[1]);
        int modifier = 1;
        String lastSlagaemoe;
        String preDiceSet;

        if (partsOfExpression[0].contains("-")) {
            modifier = -1;
            lastSlagaemoe = partsOfExpression[0].substring(partsOfExpression[0].lastIndexOf("-") + 1);
        }
        else
            lastSlagaemoe = partsOfExpression[0].substring(partsOfExpression[0].lastIndexOf("+") + 1);

        if (!lastSlagaemoe.contains("d")) {
            modifier *= Integer.parseInt(lastSlagaemoe);
            if (modifier >= 0)
                preDiceSet = partsOfExpression[0].substring(0, partsOfExpression[0].lastIndexOf("+"));
            else
                preDiceSet = partsOfExpression[0].substring(0, partsOfExpression[0].lastIndexOf("-"));
        }
        else
            preDiceSet = partsOfExpression[0];

        DiceSet diceSet = new DiceSet(preDiceSet.split("[+]"));

        double result = diceSet.probabilityInPercents(diceSet.aboveOrEquals(boarder - modifier));
        return new DecimalFormat("#.##").format(result);
    }

    @Override
    public String getBotUsername() {
        return "calculatorOfProbabilityBot";
    }

    @Override
    public String getBotToken() {
        return "1473964699:AAE1zrBZgaBWkuFG42iwxFhs-G8McLexjR8";
    }
}