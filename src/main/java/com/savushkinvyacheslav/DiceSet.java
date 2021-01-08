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

            currDiceValues = new Integer[maxDiceValues.length];
            Arrays.fill(currDiceValues, 1);
        }
    }

    public Integer aboveOrEquals(Integer boarder) {
        int result = 0;
        if (goodSet(boarder))
            result++;

        while (nextSetExist())
            if (goodSet(boarder))
                result++;

        Arrays.fill(currDiceValues, 1);

        return result;
    }

    public Double probabilityInPercents(int positiveExperiments) {
        int allCombinations = 1;
        for (Integer mdv : maxDiceValues)
            allCombinations *= mdv;

        return (double) positiveExperiments / allCombinations * 100;
    }

    public int minSumDiceValues() {
        return currDiceValues.length;
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
        //TODO: override this method
        return super.toString();
    }
}
