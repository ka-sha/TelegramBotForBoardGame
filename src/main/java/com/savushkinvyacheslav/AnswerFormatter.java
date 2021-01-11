package com.savushkinvyacheslav;

public class AnswerFormatter {

    private static final String INCORRECT_DATA_MESSAGE = "Incorrect message entered\n" +
            "Type /help for more information";
    private static final String PERCENT_SYMBOL = " %";

    public static String getAnswer(String messageText) {
        Expression expression = new Expression(messageText);
        if (expression.isValidForCalculateProbability()) {
            CheckExpression probabilityCheckExpression =
                    expression.getCheckExpressionForCalculateProbability();
            Double probability = probabilityCheckExpression.calculateProbability();
            return formatDouble(probability) + PERCENT_SYMBOL;
        } else if (expression.isValidForCompare()) {
            CheckExpression[] expressions = expression.getCheckExpressionsForComparing();
            return makeComparingTable(expressions);
        }

        return INCORRECT_DATA_MESSAGE;
    }

    private static String formatDouble(Double d) {
        return String.format("%.2f", d);
    }

    private static String makeComparingTable(CheckExpression[] expressionPair) {
        CheckExpression ce1 = expressionPair[0];
        CheckExpression ce2 = expressionPair[1];
        StringBuilder table = new StringBuilder(
                String.format("| %-3s | %.8s, %% | %.8s, %% |\n",
                        ">=",
                        formatDiceSetPlusModifier(ce1),
                        formatDiceSetPlusModifier(ce2)));
        int n = Math.max(ce1.getDiceSet().maxSumDiceValues(), ce2.getDiceSet().maxSumDiceValues());
        int difficulty = 1;
        while (difficulty <= n) {
            double probability1 = ce1.calculateProbability();
            double probability2 = ce2.calculateProbability();
            if (notEqualToHundred(probability1, probability2)) {
                String pos1 = formatDouble(probability1);
                String pos2 = formatDouble(probability2);
                table.append(String.format("| %-3s | %11s | %11s |\n", "" + difficulty, pos1, pos2));
            }
            difficulty++;
            ce1.setDifficultyOfCheck(difficulty);
            ce2.setDifficultyOfCheck(difficulty);
        }
        return table.toString();
    }

    private static String formatDiceSetPlusModifier(CheckExpression ce) {
        int modifier = ce.getModifier();
        DiceSet ds = ce.getDiceSet();
        return (modifier == 0) ? ds.toString() : ds.toString() + "+" + modifier;
    }

    private static boolean notEqualToHundred(double x, double y) {
        return x != 100 && y != 100;
    }
}
