/* MyMath 1.0 12/09/2016 */
package com.softserve.edu.zelyonka.task01;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple utility class for help to resolve my home tasks.
 *
 * @version 1.0 09 December 2016
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */

public final class MyMath {

    /**
     * Value of ten for usage in methods.
     */
    private static final int TEN_VALUE = 10;

    /**
     * Value of seven for usage in methods.
     */
    private static final int SEVEN_VALUE = 7;

    /**
     * Value of five for usage in methods.
     */
    private static final int FIVE_VALUE = 5;

    /**
     * List of perfect numbers in integer range.
     */
    private static final int[] PERFECT_NUMBERS_IN_INT_RANGE = {6, 28, 496, 8128,
            33550336};

    /**
     * Private constructor to prevent creating class examples.
     *
     */
    private MyMath() {
    }

    /**
     * Return count of digits in natural number. Resolving home task 86a.
     *
     * @param naturalNumber
     *            a natural number to count digits
     * @return count of digits in natural number
     * @throws IllegalArgumentException
     *             if number is not natural
     */
    public static int getCountOfDigitsInNaturalNumber(final int naturalNumber)
            throws IllegalArgumentException {
        checkNatural(naturalNumber);
        return String.valueOf(naturalNumber).length();
    }

    /**
     * Return sum of digits in natural number. Resolving home task 86b.
     *
     * @param naturalNumber
     *            a natural number to sum digits
     * @return sum of digits in natural number
     * @throws IllegalArgumentException
     *             if number is not natural
     */
    public static int getSumOfDigitsInNaturalNumber(final int naturalNumber)
            throws IllegalArgumentException {
        checkNatural(naturalNumber);
        int sum = 0;
        int internalNumber = naturalNumber;
        while (internalNumber > 0) {
            sum += internalNumber % TEN_VALUE;
            internalNumber /= TEN_VALUE;
        }
        return sum;
    }

    /**
     * Return first digit in natural number. Resolving home task 86c.
     *
     * @param naturalNumber
     *            a natural number to get first digit
     * @return first digit in natural number
     * @throws IllegalArgumentException
     *             if number is not natural
     */
    public static int getFirstDigitInNaturalNumber(final int naturalNumber)
            throws IllegalArgumentException {
        checkNatural(naturalNumber);
        return Integer.parseInt(String.valueOf(naturalNumber).substring(0, 1));
    }

    /**
     * Return alternating sum of digits in natural number. Resolving home task
     * 86d.
     *
     * @param naturalNumber
     *            a natural number to get alternating sum of digits
     * @return alternating sum of digits in natural number
     * @throws IllegalArgumentException
     *             if number is not natural
     */
    public static int getAlternatingSumOfDigitsInNaturalNumber(
            final int naturalNumber) throws IllegalArgumentException {
        checkNatural(naturalNumber);
        String numberString = String.valueOf(naturalNumber);
        int sum = 0;
        int signValue = 1;
        for (int i = 0; i < numberString.length(); i++) {
            sum += Integer.parseInt(numberString.substring(i, i + 1))
                    * signValue;
            signValue *= -1;
        }
        return sum;
    }

    /**
     * Returns a list of perfect numbers that are less than a given natural
     * number. Resolving home task 330.
     *
     * @param naturalNumber
     *            a natural number to get a list of perfect numbers
     * @return a list of perfect numbers that are less than a given natural
     *         number
     * @throws IllegalArgumentException
     *             if number is not natural
     */
    public static List<Integer> getPerfectNumbers(final int naturalNumber)
            throws IllegalArgumentException {
        checkNatural(naturalNumber);
        List<Integer> perfectNumbers = new ArrayList<>();
        for (int perfectNumber : PERFECT_NUMBERS_IN_INT_RANGE) {
            if (perfectNumber < naturalNumber) {
                perfectNumbers.add(perfectNumber);
            }
        }
        return perfectNumbers;
    }

    /**
     * Checks whether a given number is perfect.
     *
     * @param naturalNumber
     *            a natural number to check
     * @return true if number is perfect
     * @throws IllegalArgumentException
     *             if number is not natural
     */
    public static boolean isPerfectNumber(final int naturalNumber)
            throws IllegalArgumentException {
        checkNatural(naturalNumber);
        for (int perfectNumber : PERFECT_NUMBERS_IN_INT_RANGE) {
            if (perfectNumber == naturalNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the count of sequence members that are divided into five and not
     * divided into seven. Resolving home task 182.
     *
     * @param sequence
     *            a sequence of integer
     * @return the count of sequence members that are divided into five and not
     *         divided into seven
     */
    public static int getCountDivIntoFiveNotDivIntoSeven(final int[] sequence) {
        int count = 0;
        for (int i = 0; i < sequence.length; i++) {
            if (sequence[i] % FIVE_VALUE == 0
                    && sequence[i] % SEVEN_VALUE != 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the sum of sequence members that are divided into five and not
     * divided into seven. Resolving home task 182.
     *
     * @param sequence
     *            a sequence of integer
     * @return the sum of sequence members that are divided into five and not
     *         divided into seven
     */
    public static int getSumDivIntoFiveNotDivIntoSeven(final int[] sequence) {
        int sum = 0;
        for (int i = 0; i < sequence.length; i++) {
            if (sequence[i] % FIVE_VALUE == 0
                    && sequence[i] % SEVEN_VALUE != 0) {
                sum += sequence[i];
            }
        }
        return sum;
    }

    /**
     * Checks whether a given number is natural.
     *
     * @param naturalNumber
     *            integer number to check
     *
     * @throws IllegalArgumentException
     *             if number is not natural
     */
    private static void checkNatural(final int naturalNumber)
            throws IllegalArgumentException {
        if (naturalNumber < 0) {
            throw new IllegalArgumentException(
                    "Method work only for natural numbers");
        }
    }

}
