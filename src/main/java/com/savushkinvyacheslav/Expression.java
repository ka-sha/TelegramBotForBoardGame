package com.savushkinvyacheslav;

import java.util.regex.*;

public class Expression {

    private final String expression;
    private static final String diceSetRegex = "(((([1-9]*)[d])([468]|10|12|20|100)([+]?))+([+]\\\\d+)?)";

    public Expression(String expression) {
        this.expression = expression;
    }

    public boolean isValidForCalculateProbability() {
        Pattern calculateProbabilityRegex = Pattern.compile("^" + diceSetRegex + "(>=\\\\d+)$");
        Matcher matcher = calculateProbabilityRegex.matcher(expression);
        return matcher.find() && noContainPlusOnLeftPartEnd();
    }

    public boolean isValidForCompare() {
        Pattern compareExpressionRegex = Pattern.compile("^" + diceSetRegex + "\\p{Blank}" + diceSetRegex + "$");
        Matcher matcher = compareExpressionRegex.matcher(expression);
        return matcher.find() && noContainPlusOnLeftPartEnd();
    }

    private boolean noContainPlusOnLeftPartEnd() {
        return !expression.contains("+>");
    }

    public CheckExpression[] getCheckExpressionsForComparing() {
        String[] strExpressions = expression.split("\\p{Blank}");
        Expression e1 = new Expression(strExpressions[0]);
        Expression e2 = new Expression(strExpressions[1]);
        return new CheckExpression[]{e1.getCheckExpressionForCalculateProbability(),
                e2.getCheckExpressionForCalculateProbability()};
    }

    public CheckExpression getCheckExpressionForCalculateProbability() {
        DiceSet diceSet;
        int modifier;
        int difficultyOfCheck = parseDifficultyOfCheck();
        if (hasModifier()) {
            diceSet = parseDiceSet("+");
            modifier = parseModifier();
        } else {
            diceSet = parseDiceSet(">");
            modifier = 0;
        }

        return new CheckExpression(diceSet, modifier, difficultyOfCheck);
    }

    private int parseDifficultyOfCheck() {
        int startOfDigit = expression.indexOf("=") + 1;
        String strDigit = expression.substring(startOfDigit);
        return Integer.parseInt(strDigit);
    }

    private boolean hasModifier() {
        return !lastTerm().contains("d");
    }

    private String lastTerm() {
        int lastPlus = expression.lastIndexOf("+");
        int aboveSymbolIndex = expression.indexOf(">");
        return expression.substring(lastPlus + 1, aboveSymbolIndex);
    }

    private DiceSet parseDiceSet(String lastSymbol) {
        int lastSymbolIndex = expression.lastIndexOf(lastSymbol);
        String strDiceSet = expression.substring(0, lastSymbolIndex);
        return new DiceSet(strDiceSet.split("[+]"));
    }

    private int parseModifier() {
        String modifier = lastTerm();
        return Integer.parseInt(modifier);
    }
}
