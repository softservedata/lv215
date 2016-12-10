/* MyMath 1.0 12/09/2016 */
package lesson01;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple utility class for help to resolve my home tasks:)
 * 
 * @version 1.0 09 December 2016
 * 
 * @author Petro Zelyonka
 * 
 * @since 1.8
 */
public class MyMath {

    /**
     * Return count of digits in natural number. Resolving home task 86a.
     * 
     * @param naturalNumber
     *            a natural number to count digits
     * @return count of digits in natural number
     * @throws IllegalArgumentException
     *             if number is not natural
     */
    public static int getCountOfDigitsInNaturalNumber(int naturalNumber) throws IllegalArgumentException {
	if (naturalNumber < 0)
	    throw new IllegalArgumentException("Method work only for natural numbers");
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
    public static int getSumOfDigitsInNaturalNumber(int naturalNumber) throws IllegalArgumentException {
	if (naturalNumber < 0)
	    throw new IllegalArgumentException("Method work only for natural numbers");
	int sum = 0;
	while (naturalNumber > 0) {
	    sum += naturalNumber % 10;
	    naturalNumber /= 10;
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
    public static int getFirstDigitInNaturalNumber(int naturalNumber) throws IllegalArgumentException {
	if (naturalNumber < 0)
	    throw new IllegalArgumentException("Method work only for natural numbers");
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
    public static int getAlternatingSumOfDigitsInNaturalNumber(int naturalNumber) throws IllegalArgumentException {
	if (naturalNumber < 0)
	    throw new IllegalArgumentException("Method work only for natural numbers");
	String numberString = String.valueOf(naturalNumber);
	int sum = 0;
	int signValue = 1;
	for (int i = 0; i < numberString.length(); i++) {
	    sum += Integer.parseInt(numberString.substring(i, i + 1)) * signValue;
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
    public static List<Integer> getPerfectNumbers(int naturalNumber) throws IllegalArgumentException {
	if (naturalNumber < 0)
	    throw new IllegalArgumentException("Method work only for natural numbers");
	List<Integer> perfectNumbers = new ArrayList<>();
	for (int i = naturalNumber - 1; i > 0; i--) {
	    if (isPerfectNumber(i)) {
		perfectNumbers.add(i);
	    }
	}
	return perfectNumbers;
    }

    /**
     * Checks whether a given number is perfect. Helps to resolve home task 330.
     * 
     * @param naturalNumber
     *            a natural number to check
     * @return true if number is perfect
     * @throws IllegalArgumentException
     *             if number is not natural
     */
    public static boolean isPerfectNumber(int naturalNumber) throws IllegalArgumentException {
	if (naturalNumber < 0)
	    throw new IllegalArgumentException("Method work only for natural numbers");
	int sum = 0;
	for (int i = naturalNumber - 1; i > 0; i--) {
	    if (naturalNumber % i == 0) {
		sum += i;
	    }
	}
	return sum == naturalNumber;
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
    public static int getCountDivIntoFiveNotDivIntoSeven(int[] sequence) {
	int count = 0;
	for (int i = 0; i < sequence.length; i++) {
	    if (sequence[i] % 5 == 0 && sequence[i] % 7 != 0) {
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
    public static int getSumDivIntoFiveNotDivIntoSeven(int[] sequence) {
	int sum = 0;
	for (int i = 0; i < sequence.length; i++) {
	    if (sequence[i] % 5 == 0 && sequence[i] % 7 != 0) {
		sum += sequence[i];
	    }
	}
	return sum;
    }

}
