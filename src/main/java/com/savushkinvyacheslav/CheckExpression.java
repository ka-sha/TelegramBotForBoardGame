package com.savushkinvyacheslav;

public class CheckExpression {
    private DiceSet diceSet;
    private int modifier;
    private int difficultyOfCheck;

    public CheckExpression(DiceSet diceSet, int modifier, int difficultyOfCheck) {
        this.diceSet = diceSet;
        this.modifier = modifier;
        this.difficultyOfCheck = difficultyOfCheck;
    }

    public double calculateProbability() {
        return diceSet.probabilityAboveOrEqualsInPercents(difficultyOfCheck - modifier);
    }

    @Override
    public String toString() {
        return diceSet.toString() + "+" +  concatModifier() + ">=" + difficultyOfCheck;
    }

    private String concatModifier() {
        return (modifier == 0) ? "" :
                "+" + modifier + ">=";
    }

    public DiceSet getDiceSet() {
        return diceSet;
    }

    public int getModifier() {
        return modifier;
    }

    public void setDifficultyOfCheck(int difficultyOfCheck) {
        this.difficultyOfCheck = difficultyOfCheck;
    }
}
