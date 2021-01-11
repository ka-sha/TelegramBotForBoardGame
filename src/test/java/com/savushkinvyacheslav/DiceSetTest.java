package com.savushkinvyacheslav;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiceSetTest {
    @Test
    public void toStringOneDiceTest() {
        assertEquals("d4", new DiceSet(new String[]{"d4"}).toString());
        assertEquals("d6", new DiceSet(new String[]{"d6"}).toString());
        assertEquals("d8", new DiceSet(new String[]{"d8"}).toString());
        assertEquals("d10", new DiceSet(new String[]{"d10"}).toString());
        assertEquals("d12", new DiceSet(new String[]{"d12"}).toString());
    }

    @Test
    public void toStringSeveralDicesWithSameEdgesTest() {
        assertEquals("2d4", new DiceSet(new String[]{"2d4"}).toString());
        assertEquals("5d4", new DiceSet(new String[]{"5d4"}).toString());
        assertEquals("8d4", new DiceSet(new String[]{"8d4"}).toString());
        assertEquals("11d4", new DiceSet(new String[]{"11d4"}).toString());

        assertEquals("3d6", new DiceSet(new String[]{"3d6"}).toString());
        assertEquals("4d6", new DiceSet(new String[]{"4d6"}).toString());
        assertEquals("6d6", new DiceSet(new String[]{"6d6"}).toString());
        assertEquals("7d6", new DiceSet(new String[]{"7d6"}).toString());

        assertEquals("d8", new DiceSet(new String[]{"1d8"}).toString());
        assertEquals("9d8", new DiceSet(new String[]{"9d8"}).toString());
        assertEquals("10d8", new DiceSet(new String[]{"10d8"}).toString());
        assertEquals("12d8", new DiceSet(new String[]{"12d8"}).toString());

        assertEquals("13d10", new DiceSet(new String[]{"13d10"}).toString());
        assertEquals("14d10", new DiceSet(new String[]{"14d10"}).toString());
        assertEquals("15d10", new DiceSet(new String[]{"15d10"}).toString());
        assertEquals("16d10", new DiceSet(new String[]{"16d10"}).toString());

        assertEquals("33d12", new DiceSet(new String[]{"33d12"}).toString());
        assertEquals("46d12", new DiceSet(new String[]{"46d12"}).toString());
        assertEquals("87d12", new DiceSet(new String[]{"87d12"}).toString());
        assertEquals("91d12", new DiceSet(new String[]{"91d12"}).toString());
    }

    @Test
    public void toStringVariousSeveralDicesTest() {
        assertEquals("d6+d4", new DiceSet(new String[]{"d4", "d6"}).toString());
        assertEquals("d8+d6+d4", new DiceSet(new String[]{"d4", "d6", "d8"}).toString());
        assertEquals("d10+d8+d6+d4", new DiceSet(new String[]{"d4", "d6", "d8", "d10"}).toString());
        assertEquals("d12+d10+d8+d6+d4", new DiceSet(new String[]{"d4", "d6", "d8", "d10", "d12"}).toString());

        assertEquals("d12+d10+d8+d6+4d4", new DiceSet(new String[]{"4d4", "d6", "d8", "d10", "d12"}).toString());
        assertEquals("d12+d10+d8+4d6+d4", new DiceSet(new String[]{"d4", "4d6", "d8", "d10", "d12"}).toString());
        assertEquals("d12+d10+4d8+d6+d4", new DiceSet(new String[]{"d4", "d6", "4d8", "d10", "d12"}).toString());
        assertEquals("d12+4d10+d8+d6+d4", new DiceSet(new String[]{"d4", "d6", "d8", "4d10", "d12"}).toString());
        assertEquals("4d12+d10+d8+d6+d4", new DiceSet(new String[]{"d4", "d6", "d8", "d10", "4d12"}).toString());

        assertEquals("d12+d10+d8+6d6+6d4", new DiceSet(new String[]{"6d4", "6d6", "d8", "d10", "d12"}).toString());
        assertEquals("d12+d10+6d8+d6+6d4", new DiceSet(new String[]{"6d4", "d6", "6d8", "d10", "d12"}).toString());
        assertEquals("d12+6d10+d8+d6+6d4", new DiceSet(new String[]{"6d4", "d6", "d8", "6d10", "d12"}).toString());
        assertEquals("6d12+d10+d8+d6+6d4", new DiceSet(new String[]{"6d4", "d6", "d8", "d10", "6d12"}).toString());
        assertEquals("d12+d10+3d8+3d6+d4", new DiceSet(new String[]{"d4", "3d6", "3d8", "d10", "d12"}).toString());
        assertEquals("d12+3d10+d8+3d6+d4", new DiceSet(new String[]{"d4", "3d6", "d8", "3d10", "d12"}).toString());
        assertEquals("3d12+d10+d8+3d6+d4", new DiceSet(new String[]{"d4", "3d6", "d8", "d10", "3d12"}).toString());
        assertEquals("d12+5d10+5d8+d6+d4", new DiceSet(new String[]{"d4", "d6", "5d8", "5d10", "d12"}).toString());
        assertEquals("5d12+d10+5d8+d6+d4", new DiceSet(new String[]{"d4", "d6", "5d8", "d10", "5d12"}).toString());
        assertEquals("8d12+8d10+d8+d6+d4", new DiceSet(new String[]{"d4", "d6", "d8", "8d10", "8d12"}).toString());
    }

    @Test
    public void toStringSameDicesSeparateTest() {
        assertEquals("2d12+5d8+2d4", new DiceSet(new String[]{"d12", "d4", "d12", "d4", "5d8"}).toString());
    }
}
