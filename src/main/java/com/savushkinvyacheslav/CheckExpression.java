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

    public DiceSet getDiceSet() {
        return diceSet;
    }

    public void setDiceSet(DiceSet diceSet) {
        this.diceSet = diceSet;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public int getDifficultyOfCheck() {
        return difficultyOfCheck;
    }

    public void setDifficultyOfCheck(int difficultyOfCheck) {
        this.difficultyOfCheck = difficultyOfCheck;
    }
}
