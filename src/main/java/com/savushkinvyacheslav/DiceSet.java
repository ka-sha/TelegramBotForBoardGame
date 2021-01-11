package com.savushkinvyacheslav;

import java.util.*;

public class DiceSet {
    private Integer[] maxDiceValues;
    private Integer[] currDiceValues;

    public DiceSet(String[] diceSets) {
        List<Integer> maxDiceValuesList = new ArrayList<>();

        for (int i = 0; i < diceSets.length; i++) {
            if (diceSets[i].startsWith("d"))
                diceSets[i] = diceSets[i].replaceFirst("d", "1d");

            String[] amountAndEdges = diceSets[i].split("d");
            for (int j = 0; j < Integer.parseInt(amountAndEdges[0]); j++)
                maxDiceValuesList.add(Integer.parseInt(amountAndEdges[1]));

            maxDiceValues = new Integer[maxDiceValuesList.size()];
            maxDiceValues = maxDiceValuesList.toArray(maxDiceValues);
            Arrays.sort(maxDiceValues);

            currDiceValues = new Integer[maxDiceValues.length];
            Arrays.fill(currDiceValues, 1);
        }
    }

    public Double probabilityAboveOrEqualsInPercents(int border) {
        return (double) aboveOrEquals(border) / countAllCombinations() * 100;
    }

    private Integer aboveOrEquals(int border) {
        int result = 0;
        if (goodSet(border))
            result++;

        while (nextSetExist())
            if (goodSet(border))
                result++;

        Arrays.fill(currDiceValues, 1);

        return result;
    }

    private int countAllCombinations() {
        int result = 1;
        for (int mvd : maxDiceValues)
            result *= mvd;
        return result;
    }

    public int maxSumDiceValues() {
        int result = 0;

        for (int mdv : maxDiceValues)
            result += mdv;

        return result;
    }

    private boolean goodSet(int boarder) {
        int res = 0;
        for (Integer cdv : currDiceValues) {
            res += cdv;
            if (res >= boarder)
                return true;
        }
        return false;
    }

    private boolean nextSetExist() {
        int i = currDiceValues.length - 1;
        while (i >= 0 && currDiceValues[i].equals(maxDiceValues[i]))
            i--;
        if (i < 0)
            return false;
        if (currDiceValues[i] >= maxDiceValues[i])
            i--;
        currDiceValues[i]++;
        if (i == currDiceValues.length - 1)
            return true;
        for (int j = i + 1; j < currDiceValues.length; j++)
            currDiceValues[j] = 1;
        return true;
    }

    @Override
    public String toString() {
        int amountOfDices = 1;
        int edges = maxDiceValues[0];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < maxDiceValues.length - 1; i++) {
            if (maxDiceValues[i].equals(maxDiceValues[i + 1]))
                amountOfDices++;
            else {
                result.append(buildPartOfDiceSet(amountOfDices, edges)).append("+");
                amountOfDices = 1;
                edges = maxDiceValues[i + 1];
            }
        }
        result.append(buildPartOfDiceSet(amountOfDices, edges));

        return result.toString();
    }

    private String buildPartOfDiceSet(int amountOfDices, int edges) {
        return (amountOfDices == 1) ? "d" + edges : amountOfDices + "d" + edges;
    }
}
