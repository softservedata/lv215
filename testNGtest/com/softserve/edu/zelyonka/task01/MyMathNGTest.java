package com.softserve.edu.zelyonka.task01;

import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test case for testing MyMath.class.
 *
 * @version 1.0 17 December 2016
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class MyMathNGTest {

    /**
     * illegal arguments data provider for testing methods which works with
     * natural numbers.
     *
     * @return not natural numbers for methods exceptions call
     */
    @DataProvider
    public Object[][] notNaturalNumbersDataProvider() {
        return new Object[][] {{-1}, {-36}, {Integer.MIN_VALUE}};
    }

    /**
     * data provider for testing method getCountOfDigitsInNaturalNumber.
     *
     * @return natural number and correct count of its digits
     */
    @DataProvider
    public Object[][] countOfDigitsDataProvider() {
        return new Object[][] {{0, 1}, {5, 1}, {36, 2}, {689, 3},
                {Integer.MAX_VALUE, 10}};
    }

    /**
     * test for method getCountOfDigitsInNaturalNumber.
     *
     * @param numberToTest
     *            number to test
     * @param expectedResult
     *            expected result
     *
     */
    @Test(dataProvider = "countOfDigitsDataProvider")
    public void getCountOfDigitsInNaturalNumber(final int numberToTest,
            final int expectedResult) {
        AssertJUnit.assertEquals(expectedResult,
                MyMath.getCountOfDigitsInNaturalNumber(numberToTest));
    }

    /**
     * test for method getCountOfDigitsInNaturalNumber. IllegalArgumentException
     * expected.
     *
     * @param notNaturalNumber
     *            not natural number
     */
    @Test(dataProvider = "notNaturalNumbersDataProvider",
            expectedExceptions = IllegalArgumentException.class)
    public void getCountOfDigitsInNaturalNumberException(
            final int notNaturalNumber) {
        MyMath.getCountOfDigitsInNaturalNumber(notNaturalNumber);
    }

    /**
     * data provider for testing method getSumOfDigitsInNaturalNumber.
     *
     * @return natural number and correct sum of its digits
     */
    @DataProvider
    public Object[][] sumOfDigitsDataProvider() {
        return new Object[][] {{0, 0}, {5, 5}, {36, 9}, {689, 23},
                {Integer.MAX_VALUE, 46}};
    }

    /**
     * test for method getSumOfDigitsInNaturalNumber.
     *
     * @param numberToTest
     *            number to test
     *
     * @param expectedResult
     *            expected result
     */
    @Test(dataProvider = "sumOfDigitsDataProvider")
    public void getSumOfDigitsInNaturalNumber(final int numberToTest,
            final int expectedResult) {
        AssertJUnit.assertEquals(expectedResult,
                MyMath.getSumOfDigitsInNaturalNumber(numberToTest));
    }

    /**
     * test for method getSumOfDigitsInNaturalNumber. IllegalArgumentException
     * expected.
     *
     * @param notNaturalNumber
     *            not natural number
     */
    @Test(dataProvider = "notNaturalNumbersDataProvider",
            expectedExceptions = IllegalArgumentException.class)
    public void getSumOfDigitsInNaturalNumberException(
            final int notNaturalNumber) {
        MyMath.getSumOfDigitsInNaturalNumber(notNaturalNumber);
    }

    /**
     * data provider for testing method getFirstDigitInNaturalNumber.
     *
     * @return natural number and its correct first digit
     */
    @DataProvider
    public Object[][] firstDigitDataProvider() {
        return new Object[][] {{0, 0}, {5, 5}, {36, 3}, {689, 6},
                {Integer.MAX_VALUE, 2}};
    }

    /**
     * test for method getFirstDigitInNaturalNumber.
     *
     * @param numberToTest
     *            number to test
     *
     * @param expectedResult
     *            expected result
     */
    @Test(dataProvider = "firstDigitDataProvider")
    public void getFirstDigitInNaturalNumber(final int numberToTest,
            final int expectedResult) {
        AssertJUnit.assertEquals(expectedResult,
                MyMath.getFirstDigitInNaturalNumber(numberToTest));
    }

    /**
     * test for method getFirstDigitInNaturalNumber. IllegalArgumentException
     * expected.
     *
     * @param notNaturalNumber
     *            not natural number
     */
    @Test(dataProvider = "notNaturalNumbersDataProvider",
            expectedExceptions = IllegalArgumentException.class)
    public void getFirstDigitInNaturalNumberException(
            final int notNaturalNumber) {
        MyMath.getFirstDigitInNaturalNumber(notNaturalNumber);
    }

    /**
     * data provider for testing method
     * getAlternatingSumOfDigitsInNaturalNumber.
     *
     * @return natural number and its correct alternating sum of digits
     */
    @DataProvider
    public Object[][] alternatingSumOfDigitsDataProvider() {
        return new Object[][] {{0, 0}, {5, 5}, {36, -3}, {689, 7},
                {Integer.MAX_VALUE, -12}};
    }

    /**
     * test for method getAlternatingSumOfDigitsInNaturalNumber.
     *
     * @param numberToTest
     *            number to test
     * @param expectedResult
     *            expected result
     */
    @Test(dataProvider = "alternatingSumOfDigitsDataProvider")
    public void getAlternatingSumOfDigitsInNaturalNumber(final int numberToTest,
            final int expectedResult) {
        AssertJUnit.assertEquals(expectedResult,
                MyMath.getAlternatingSumOfDigitsInNaturalNumber(numberToTest));
    }

    /**
     * test for method getAlternatingSumOfDigitsInNaturalNumber.
     * IllegalArgumentException expected.
     *
     * @param notNaturalNumber
     *            not natural number
     */
    @Test(dataProvider = "notNaturalNumbersDataProvider",
            expectedExceptions = IllegalArgumentException.class)
    public void getAlternatingSumOfDigitsInNaturalNumberException(
            final int notNaturalNumber) {
        MyMath.getAlternatingSumOfDigitsInNaturalNumber(notNaturalNumber);
    }

    /**
     * test for method getPerfectNumbers.
     */
    @Test
    public void getPerfectNumbers() {
        List<Integer> perfectNumbers = MyMath
                .getPerfectNumbers(Integer.MAX_VALUE);
        AssertJUnit.assertEquals(5, perfectNumbers.size());
        AssertJUnit.assertEquals(6, perfectNumbers.get(0).intValue());
        AssertJUnit.assertEquals(28, perfectNumbers.get(1).intValue());
        AssertJUnit.assertEquals(496, perfectNumbers.get(2).intValue());
        AssertJUnit.assertEquals(8128, perfectNumbers.get(3).intValue());
        AssertJUnit.assertEquals(33550336, perfectNumbers.get(4).intValue());
        perfectNumbers = MyMath.getPerfectNumbers(10000);
        AssertJUnit.assertEquals(4, perfectNumbers.size());
        AssertJUnit.assertEquals(6, perfectNumbers.get(0).intValue());
        AssertJUnit.assertEquals(28, perfectNumbers.get(1).intValue());
        AssertJUnit.assertEquals(496, perfectNumbers.get(2).intValue());
        AssertJUnit.assertEquals(8128, perfectNumbers.get(3).intValue());
        perfectNumbers = MyMath.getPerfectNumbers(100);
        AssertJUnit.assertEquals(2, perfectNumbers.size());
        AssertJUnit.assertEquals(6, perfectNumbers.get(0).intValue());
        AssertJUnit.assertEquals(28, perfectNumbers.get(1).intValue());
        perfectNumbers = MyMath.getPerfectNumbers(0);
        AssertJUnit.assertEquals(0, perfectNumbers.size());
    }

    /**
     * test for method getPerfectNumbers. IllegalArgumentException expected.
     *
     * @param notNaturalNumber
     *            not natural number
     */
    @Test(dataProvider = "notNaturalNumbersDataProvider",
            expectedExceptions = IllegalArgumentException.class)
    public void getPerfectNumbersException(final int notNaturalNumber) {
        MyMath.getPerfectNumbers(notNaturalNumber);
    }

    /**
     * perfect numbers data provider for testing method isPerfectNumber.
     *
     * @return perfect number in integer range
     */
    @DataProvider
    public Object[][] perfectNumbersDataProvider() {
        return new Object[][] {{6}, {28}, {496}, {8128}, {33550336}};
    }

    /**
     * test #1 for method isPerfectNumber.
     *
     * @param perfectNumber
     *            perfect number
     */
    @Test(dataProvider = "perfectNumbersDataProvider")
    public void isPerfectNumber(final int perfectNumber) {
        AssertJUnit.assertTrue(MyMath.isPerfectNumber(perfectNumber));
    }

    /**
     * non-perfect numbers data provider for testing method isPerfectNumber.
     *
     * @return non-perfect number in integer range
     */
    @DataProvider
    public Object[][] nonPerfectNumbersDataProvider() {
        return new Object[][] {{125}, {0}, {Integer.MAX_VALUE}, {5555}, {53}};
    }

    /**
     * test #2 for method isPerfectNumber.
     *
     * @param nonPerfectNumber
     *            non-perfect number
     */
    @Test(dataProvider = "nonPerfectNumbersDataProvider")
    public void isNotPerfectNumber(final int nonPerfectNumber) {
        AssertJUnit.assertFalse(MyMath.isPerfectNumber(nonPerfectNumber));
    }

    /**
     * test for method isPerfectNumber. IllegalArgumentException expected.
     *
     * @param notNaturalNumber
     *            not natural number
     */
    @Test(dataProvider = "notNaturalNumbersDataProvider",
            expectedExceptions = IllegalArgumentException.class)
    public void isPerfectNumberException(final int notNaturalNumber) {
        MyMath.isPerfectNumber(notNaturalNumber);
    }

    /**
     * test sequence data provider for testing methods
     * getCountDivIntoFiveNotDivIntoSeven and getSumDivIntoFiveNotDivIntoSeven.
     *
     * @return test sequences of integer values and their correct sum and count.
     */
    @DataProvider
    public Object[][] testSequenceDataProvider() {
        return new Object[][] {{new int[] {25, 20, 35, 140, 26, 39}, 2, 45},
                {new int[] {-45, -58, -25, 0, 0}, 2, -70}, {new int[] {}, 0, 0},
                {new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE, 0}, 0, 0}};
    }

    /**
     * test for method getCountDivIntoFiveNotDivIntoSeven.
     *
     * @param testSequence
     *            sequence to test
     *
     * @param count
     *            count of elements of sequence which can be divided into five
     *            and can not be divided by seven
     * @param sum
     *            sum of elements of sequence which can be divided into five and
     *            can not be divided by seven
     */
    @Test(dataProvider = "testSequenceDataProvider")
    public void getCountDivIntoFiveNotDivIntoSeven(final int[] testSequence,
            final int count, final int sum) {
        AssertJUnit.assertEquals(count,
                MyMath.getCountDivIntoFiveNotDivIntoSeven(testSequence));
    }

    /**
     * test for method getSumDivIntoFiveNotDivIntoSeven.
     *
     * @param testSequence
     *            sequence to test
     *
     * @param count
     *            count of elements of sequence which can be divided into five
     *            and can not be divided by seven
     * @param sum
     *            sum of elements of sequence which can be divided into five and
     *            can not be divided by seven
     */
    @Test(dataProvider = "testSequenceDataProvider")
    public void getSumDivIntoFiveNotDivIntoSeven(final int[] testSequence,
            final int count, final int sum) {
        AssertJUnit.assertEquals(sum,
                MyMath.getSumDivIntoFiveNotDivIntoSeven(testSequence));
    }
}
