package com.savushkinvyacheslav;

import java.util.regex.*;

public class ParserOfCheckExpression {

    private static final String diceSetRegex = "(((([1-9]*)[d])([468]|10|12|20|100)([+]?))+([+]\\\\d+)?)";

    public static boolean isValidForCalculateProbability(String expression) {
        Pattern calculateProbabilityRegex = Pattern.compile("^" + diceSetRegex + "(>=\\\\d+)$");
        Matcher matcher = calculateProbabilityRegex.matcher(expression);
        return matcher.find() && noContainPlusOnEnd(expression);
    }

    public static boolean isValidForCompareExpressions(String expression) {
        Pattern compareExpressionRegex = Pattern.compile("^" + diceSetRegex + "\\p{Blank}" + diceSetRegex + "$");
        Matcher matcher = compareExpressionRegex.matcher(expression);
        return matcher.find() && noContainPlusOnEnd(expression);
    }

    private static boolean noContainPlusOnEnd(String expression) {
        return !expression.contains("+>");
    }

    public static CheckExpression getCheckExpressionForCalculateProbability(String expression) {
        DiceSet diceSet;
        int modifier;
        int difficultyOfCheck = parseDifficultyOfCheck(expression);
        if (hasModifier(expression)) {
            diceSet = parseDiceSet(expression, "+");
            modifier = parseModifier(expression);
        } else {
            diceSet = parseDiceSet(expression, ">");
            modifier = 0;
        }

        return new CheckExpression(diceSet, modifier, difficultyOfCheck);
    }

    private static int parseDifficultyOfCheck(String expression) {
        int startOfDigit = expression.indexOf("=") + 1;
        String strDigit = expression.substring(startOfDigit);
        return Integer.parseInt(strDigit);
    }

    private static boolean hasModifier(String expression) {
        return !lastTerm(expression).contains("d");
    }

    private static String lastTerm(String expression) {
        int lastPlus = expression.lastIndexOf("+");
        int aboveSymbolIndex = expression.indexOf(">");
        return expression.substring(lastPlus + 1, aboveSymbolIndex);
    }

    private static DiceSet parseDiceSet(String expression, String lastSymbol) {
        int lastPlus = expression.lastIndexOf(lastSymbol);
        String strDiceSet = expression.substring(0, lastPlus);
        return new DiceSet(strDiceSet.split("[+]"));
    }

    private static int parseModifier(String expression) {
        String modifier = lastTerm(expression);
        return Integer.parseInt(modifier);
    }
}
