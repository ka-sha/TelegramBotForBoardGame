package com.savushkinvyacheslav;

import org.junit.*;
import static org.junit.Assert.*;

public class CalculatorOfProbabilityBotTest {
    @Test
    public void oneDiceCorrectInputTest() {
        assertEquals("100", CalculatorOfProbabilityBot.parseAndCount("d4>=1"));
        assertEquals("100", CalculatorOfProbabilityBot.parseAndCount("d6>=1"));
        assertEquals("100", CalculatorOfProbabilityBot.parseAndCount("d8>=1"));
        assertEquals("100", CalculatorOfProbabilityBot.parseAndCount("d10>=1"));
        assertEquals("100", CalculatorOfProbabilityBot.parseAndCount("d12>=1"));

        assertEquals("75", CalculatorOfProbabilityBot.parseAndCount("d4>=2"));
        assertEquals("83,33", CalculatorOfProbabilityBot.parseAndCount("d6>=2"));
        assertEquals("87,5", CalculatorOfProbabilityBot.parseAndCount("d8>=2"));
        assertEquals("90", CalculatorOfProbabilityBot.parseAndCount("d10>=2"));
        assertEquals("91,67", CalculatorOfProbabilityBot.parseAndCount("d12>=2"));

        assertEquals("25", CalculatorOfProbabilityBot.parseAndCount("d4>=4"));
        assertEquals("50", CalculatorOfProbabilityBot.parseAndCount("d6>=4"));
        assertEquals("62,5", CalculatorOfProbabilityBot.parseAndCount("d8>=4"));
        assertEquals("70", CalculatorOfProbabilityBot.parseAndCount("d10>=4"));
        assertEquals("75", CalculatorOfProbabilityBot.parseAndCount("d12>=4"));

        assertEquals("16,67", CalculatorOfProbabilityBot.parseAndCount("d6>=6"));
        assertEquals("37,5", CalculatorOfProbabilityBot.parseAndCount("d8>=6"));
        assertEquals("50", CalculatorOfProbabilityBot.parseAndCount("d10>=6"));
        assertEquals("58,33", CalculatorOfProbabilityBot.parseAndCount("d12>=6"));

        assertEquals("12,5", CalculatorOfProbabilityBot.parseAndCount("d8>=8"));
        assertEquals("30", CalculatorOfProbabilityBot.parseAndCount("d10>=8"));
        assertEquals("41,67", CalculatorOfProbabilityBot.parseAndCount("d12>=8"));

        assertEquals("10", CalculatorOfProbabilityBot.parseAndCount("d10>=10"));
        assertEquals("25", CalculatorOfProbabilityBot.parseAndCount("d12>=10"));

        assertEquals("8,33", CalculatorOfProbabilityBot.parseAndCount("d12>=12"));
    }

    @Test
    public void ZeroDifficultyOfCheckTest() {
        assertEquals("100", CalculatorOfProbabilityBot.parseAndCount("d4>=0"));
        assertEquals("100", CalculatorOfProbabilityBot.parseAndCount("d6>=0"));
        assertEquals("100", CalculatorOfProbabilityBot.parseAndCount("d8>=0"));
        assertEquals("100", CalculatorOfProbabilityBot.parseAndCount("d10>=0"));
        assertEquals("100", CalculatorOfProbabilityBot.parseAndCount("d12>=0"));
    }

    @Test
    public void veryDifficultDifficultyOfCheckTest() {
        assertEquals("0", CalculatorOfProbabilityBot.parseAndCount("d4>=5"));
        assertEquals("0", CalculatorOfProbabilityBot.parseAndCount("d6>=7"));
        assertEquals("0", CalculatorOfProbabilityBot.parseAndCount("d8>=9"));
        assertEquals("0", CalculatorOfProbabilityBot.parseAndCount("d10>=11"));
        assertEquals("0", CalculatorOfProbabilityBot.parseAndCount("d12>=13"));
    }
}
