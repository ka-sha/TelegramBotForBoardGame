package com.savushkinvyacheslav;

public class AnswerFormatter {

    private static final String INCORRECT_DATA_MESSAGE = "Incorrect message entered\n" +
            "Type /help for more information";

    public static String getAnswer(String messageText) {
        if (ParserOfCheckExpression.isValidForCalculateProbability(messageText)) {
            CheckExpression probabilityCheckExpression =
                    ParserOfCheckExpression.getCheckExpressionForCalculateProbability(messageText);
        } else if (ParserOfCheckExpression.isValidForCompareExpressions(messageText)) {
            return "some string";
        }

        return INCORRECT_DATA_MESSAGE;
    }
}
