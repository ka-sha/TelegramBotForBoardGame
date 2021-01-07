package com.savushkinvyacheslav;

public class AnswerFormatter {

    private static final String INCORRECT_DATA_MESSAGE = "Incorrect message entered" +
            "\nType /help for more information";

    public static String getAnswer(String messageText) {
        if (ParserOfCheckExpression.isValidForCalculating(messageText)) {
            return "some string";
        } else
            return INCORRECT_DATA_MESSAGE;
    }
}
