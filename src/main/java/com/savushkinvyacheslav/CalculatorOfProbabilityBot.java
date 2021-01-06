package com.savushkinvyacheslav;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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

            String diceSetRegex = "(((([1-9]*)[d])([468]|10|12|20|100)([+]?))+([+]\\d+)?)";
            Pattern CalculateProbabilityPattern = Pattern.compile("^" + diceSetRegex + "(>=\\d+)$");
            Pattern CompareTwoDiceSetsPattern = Pattern.compile("^" + diceSetRegex + "\\p{Blank}" + diceSetRegex + "$");
            Matcher matcher = CalculateProbabilityPattern.matcher(text);

            if (!text.contains("+>") && matcher.find()) {
                sendMsg(chatId, parseAndCount(text) + " %");
                return;
            }
            matcher = CompareTwoDiceSetsPattern.matcher(text);
            if (!text.contains("+>") && matcher.find())
                sendMsg(chatId, makeComparingTable(text));
            else
                sendMsg(chatId, INCORRECT_DATA_MESSAGE);
        }
    }

    private void sendMsg(String chatId, String answer) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static String parseAndCount(String expression) {
        String[] partsOfExpression = expression.split(">=");
        int boarder = Integer.parseInt(partsOfExpression[1]);
        int modifier = parseModifier(partsOfExpression[0]);
        String preDiceSet = prepareStringDiceSet(partsOfExpression[0], modifier == 0);

        DiceSet diceSet = new DiceSet(preDiceSet.split("[+]"));

        double result = diceSet.probabilityInPercents(diceSet.aboveOrEquals(boarder - modifier));
        return new DecimalFormat("#.##").format(result);
    }

    private String makeComparingTable(String expression) {
        String[] expressions = expression.split("\\p{Blank}");
        if (expressions.length != 2)
            return INCORRECT_DATA_MESSAGE;

        return createComparingTable(expressions[0], expressions[1]);
    }

    private static int parseModifier(String expression) {
        String lastTerm = expression.substring(expression.lastIndexOf("+") + 1);
        if (lastTerm.contains("d"))
            return 0;
        else
            return Integer.parseInt(lastTerm);
    }

    private static String prepareStringDiceSet(String expression, boolean zeroModifier) {
        return zeroModifier ? expression :
                expression.substring(0, expression.lastIndexOf("+"));
    }

    private String createComparingTable(String expression1, String expression2) {
        int modifier1 = parseModifier(expression1);
        int modifier2 = parseModifier(expression2);
        String stringDiceSet1 = prepareStringDiceSet(expression1, modifier1 == 0);
        String stringDiceSet2 = prepareStringDiceSet(expression2, modifier2 == 0);
        DiceSet ds1 = new DiceSet(stringDiceSet1.split("[+]"));
        DiceSet ds2 = new DiceSet(stringDiceSet2.split("[+]"));

        StringBuilder resultTable = new StringBuilder(String.format("| %-3s | %.8s, %% | %.8s, %% |\n", ">=", expression1, expression2));
        int difficultyOfCheck = Math.min(ds1.minSumDiceValues() + modifier1, ds2.minSumDiceValues() + modifier2);
        int n = Math.max(ds1.maxSumDiceValues(), ds2.maxSumDiceValues());

        while (difficultyOfCheck <= n) {
            double probabilityOfSuccess1 = ds1.probabilityInPercents(ds1.aboveOrEquals(difficultyOfCheck - modifier1));
            double probabilityOfSuccess2 = ds2.probabilityInPercents(ds2.aboveOrEquals(difficultyOfCheck - modifier2));
            String pos1 = String.format("%.2f", probabilityOfSuccess1);
            String pos2 = String.format("%.2f", probabilityOfSuccess2);
            resultTable.append(String.format("| %-3s | %11s | %11s |\n", "" + difficultyOfCheck, pos1, pos2));
            difficultyOfCheck++;
        }

        return resultTable.toString();
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