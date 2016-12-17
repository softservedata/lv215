package com.softserve.edu.zelyonka.task01;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test case for testing MyMath.class.
 *
 * @version 1.0 09 December 2016
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class MyMathTest {

    /**
     * test for method getCountOfDigitsInNaturalNumber.
     */
    @Test
    public void testGetCountOfDigitsInNaturalNumber() {
        Assert.assertEquals(1, MyMath.getCountOfDigitsInNaturalNumber(0));
        Assert.assertEquals(3, MyMath.getCountOfDigitsInNaturalNumber(689));
        Assert.assertEquals(10,
                MyMath.getCountOfDigitsInNaturalNumber(Integer.MAX_VALUE));
        Assert.assertNotEquals(5, MyMath.getCountOfDigitsInNaturalNumber(158));
    }

    /**
     * test for method getCountOfDigitsInNaturalNumber. IllegalArgumentException
     * expected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetCountOfDigitsInNaturalNumberException1() {
        MyMath.getCountOfDigitsInNaturalNumber(Integer.MIN_VALUE);
    }

    /**
     * test for method getCountOfDigitsInNaturalNumber. IllegalArgumentException
     * expected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetCountOfDigitsInNaturalNumberException2() {
        MyMath.getCountOfDigitsInNaturalNumber(-1);
    }

    /**
     * test for method getSumOfDigitsInNaturalNumber.
     */
    @Test
    public void testGetSumOfDigitsInNaturalNumber() {
        Assert.assertEquals(0, MyMath.getSumOfDigitsInNaturalNumber(0));
        Assert.assertEquals(23, MyMath.getSumOfDigitsInNaturalNumber(689));
        Assert.assertEquals(46,
                MyMath.getSumOfDigitsInNaturalNumber(Integer.MAX_VALUE));
        Assert.assertNotEquals(12, MyMath.getSumOfDigitsInNaturalNumber(158));
    }

    /**
     * test for method getSumOfDigitsInNaturalNumber. IllegalArgumentException
     * expected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetSumOfDigitsInNaturalNumberException1() {
        MyMath.getSumOfDigitsInNaturalNumber(-1);
    }

    /**
     * test for method getSumOfDigitsInNaturalNumber. IllegalArgumentException
     * expected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetSumOfDigitsInNaturalNumberException2() {
        MyMath.getSumOfDigitsInNaturalNumber(Integer.MIN_VALUE);
    }

    /**
     * test for method getFirstDigitInNaturalNumber.
     */
    @Test
    public void testGetFirstDigitInNaturalNumber() {
        Assert.assertEquals(0, MyMath.getFirstDigitInNaturalNumber(0));
        Assert.assertEquals(6, MyMath.getFirstDigitInNaturalNumber(689));
        Assert.assertEquals(2,
                MyMath.getFirstDigitInNaturalNumber(Integer.MAX_VALUE));
        Assert.assertNotEquals(3, MyMath.getFirstDigitInNaturalNumber(158));
    }

    /**
     * test for method getFirstDigitInNaturalNumber. IllegalArgumentException
     * expected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetFirstDigitInNaturalNumberException1() {
        MyMath.getFirstDigitInNaturalNumber(-1);
    }

    /**
     * test for method getFirstDigitInNaturalNumber. IllegalArgumentException
     * expected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetFirstDigitInNaturalNumberException2() {
        MyMath.getFirstDigitInNaturalNumber(Integer.MIN_VALUE);
    }

    /**
     * test for method getAlternatingSumOfDigitsInNaturalNumber.
     */
    @Test
    public void testGetAlternatingSumOfDigitsInNaturalNumber() {
        Assert.assertEquals(0,
                MyMath.getAlternatingSumOfDigitsInNaturalNumber(0));
        Assert.assertEquals(7,
                MyMath.getAlternatingSumOfDigitsInNaturalNumber(689));
        Assert.assertEquals(-12, MyMath
                .getAlternatingSumOfDigitsInNaturalNumber(Integer.MAX_VALUE));
        Assert.assertNotEquals(3,
                MyMath.getAlternatingSumOfDigitsInNaturalNumber(158));
    }

    /**
     * test for method getAlternatingSumOfDigitsInNaturalNumber.
     * IllegalArgumentException expected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetAlternatingSumOfDigitsInNaturalNumberException1() {
        MyMath.getAlternatingSumOfDigitsInNaturalNumber(-1);
    }

    /**
     * test for method getAlternatingSumOfDigitsInNaturalNumber.
     * IllegalArgumentException expected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetAlternatingSumOfDigitsInNaturalNumberException2() {
        MyMath.getAlternatingSumOfDigitsInNaturalNumber(Integer.MIN_VALUE);
    }

    /**
     * test for method getPerfectNumbers.
     */
    @Test
    public void testGetPerfectNumbers() {
        List<Integer> perfectNumbers = MyMath
                .getPerfectNumbers(Integer.MAX_VALUE);
        Assert.assertEquals(5, perfectNumbers.size());
        Assert.assertEquals(6, perfectNumbers.get(0).intValue());
        Assert.assertEquals(28, perfectNumbers.get(1).intValue());
        Assert.assertEquals(496, perfectNumbers.get(2).intValue());
        Assert.assertEquals(8128, perfectNumbers.get(3).intValue());
        Assert.assertEquals(33550336, perfectNumbers.get(4).intValue());
        perfectNumbers = MyMath.getPerfectNumbers(10000);
        Assert.assertEquals(4, perfectNumbers.size());
        Assert.assertEquals(6, perfectNumbers.get(0).intValue());
        Assert.assertEquals(28, perfectNumbers.get(1).intValue());
        Assert.assertEquals(496, perfectNumbers.get(2).intValue());
        Assert.assertEquals(8128, perfectNumbers.get(3).intValue());
        perfectNumbers = MyMath.getPerfectNumbers(100);
        Assert.assertEquals(2, perfectNumbers.size());
        Assert.assertEquals(6, perfectNumbers.get(0).intValue());
        Assert.assertEquals(28, perfectNumbers.get(1).intValue());
        perfectNumbers = MyMath.getPerfectNumbers(0);
        Assert.assertEquals(0, perfectNumbers.size());
    }

    /**
     * test for method getPerfectNumbers. IllegalArgumentException expected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetPerfectNumbersException1() {
        MyMath.getPerfectNumbers(-1);
    }

    /**
     * test for method getPerfectNumbers. IllegalArgumentException expected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetPerfectNumbersException2() {
        MyMath.getPerfectNumbers(Integer.MIN_VALUE);
    }

    /**
     * test for method isPerfectNumber.
     */
    @Test
    public void testIsPerfectNumber() {
        Assert.assertTrue(MyMath.isPerfectNumber(6));
        Assert.assertTrue(MyMath.isPerfectNumber(28));
        Assert.assertTrue(MyMath.isPerfectNumber(496));
        Assert.assertTrue(MyMath.isPerfectNumber(8128));
        Assert.assertTrue(MyMath.isPerfectNumber(33550336));
        Assert.assertFalse(MyMath.isPerfectNumber(125));
        Assert.assertFalse(MyMath.isPerfectNumber(0));
        Assert.assertFalse(MyMath.isPerfectNumber(Integer.MAX_VALUE));
    }

    /**
     * test for method isPerfectNumber. IllegalArgumentException expected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIsPerfectNumberException1() {
        MyMath.isPerfectNumber(-1);
    }

    /**
     * test for method isPerfectNumber. IllegalArgumentException expected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIsPerfectNumberException2() {
        MyMath.isPerfectNumber(Integer.MIN_VALUE);
    }

    /**
     * test for method getCountDivIntoFiveNotDivIntoSeven.
     */
    @Test
    public void testGetCountDivIntoFiveNotDivIntoSeven() {
        int[] testSequence1 = {25, 20, 35, 140, 26, 39};
        Assert.assertEquals(2,
                MyMath.getCountDivIntoFiveNotDivIntoSeven(testSequence1));
        int[] testSequence2 = {-45, -58, -25, 0, 0};
        Assert.assertEquals(2,
                MyMath.getCountDivIntoFiveNotDivIntoSeven(testSequence2));
        int[] testSequence3 = {};
        Assert.assertEquals(0,
                MyMath.getCountDivIntoFiveNotDivIntoSeven(testSequence3));
        int[] testSequence4 = {Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
        Assert.assertEquals(0,
                MyMath.getCountDivIntoFiveNotDivIntoSeven(testSequence4));
    }

    /**
     * test for method getSumDivIntoFiveNotDivIntoSeven.
     */
    @Test
    public void testGetSumDivIntoFiveNotDivIntoSeven() {
        int[] testSequence1 = {25, 20, 35, 140, 26, 39};
        Assert.assertEquals(45,
                MyMath.getSumDivIntoFiveNotDivIntoSeven(testSequence1));
        int[] testSequence2 = {-45, -58, -25, 0, 0};
        Assert.assertEquals(-70,
                MyMath.getSumDivIntoFiveNotDivIntoSeven(testSequence2));
        int[] testSequence3 = {};
        Assert.assertEquals(0,
                MyMath.getSumDivIntoFiveNotDivIntoSeven(testSequence3));
        int[] testSequence4 = {Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
        Assert.assertEquals(0,
                MyMath.getSumDivIntoFiveNotDivIntoSeven(testSequence4));
    }
}
