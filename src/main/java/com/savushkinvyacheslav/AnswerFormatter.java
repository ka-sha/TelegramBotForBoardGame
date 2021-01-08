package com.savushkinvyacheslav;

public class AnswerFormatter {

    private static final String INCORRECT_DATA_MESSAGE = "Incorrect message entered\n" +
            "Type /help for more information";

    public static String getAnswer(String messageText) {
        Expression expression = new Expression(messageText);
        if (expression.isValidForCalculateProbability()) {
            CheckExpression probabilityCheckExpression =
                    expression.getCheckExpressionForCalculateProbability();
            Double probability = probabilityCheckExpression.calculateProbability();
            return formatDouble(probability);
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
        CheckExpression ex1 = expressionPair[0];
        CheckExpression ex2 = expressionPair[1];
        StringBuilder table = new StringBuilder(
                String.format("| %-3s | %.8s, %% | %.8s, %% |\n", ">=", ex1, ex2));
        int n = Math.max(ex1.getDiceSet().maxSumDiceValues(), ex2.getDiceSet().maxSumDiceValues());
        int difficulty = 1;
        while (difficulty <= n) {
            double probability1 = ex1.calculateProbability();
            double probability2 = ex2.calculateProbability();
            if (notEqualToHundred(probability1, probability2)) {
                String pos1 = formatDouble(probability1);
                String pos2 = formatDouble(probability2);
                table.append(String.format("| %-3s | %11s | %11s |\n", "" + difficulty, pos1, pos2));
            }
            difficulty++;
            ex1.setDifficultyOfCheck(difficulty);
            ex2.setDifficultyOfCheck(difficulty);
        }
        return table.toString();
    }

    private static boolean notEqualToHundred(double x, double y) {
        return x != 100 && y != 100;
    }
}
