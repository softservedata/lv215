package lesson01;

import java.util.ArrayList;
import java.util.List;

public class MyMath {

    // task 86a
    public static int getCountOfDigitsInNaturalNumber(int naturalNumber)
	    throws IllegalArgumentException {
	if (naturalNumber < 0)
	    throw new IllegalArgumentException(
		    "Method work only for natural numbers");
	return String.valueOf(naturalNumber).length();
    }

    // task 86b
    public static int getSumOfDigitsInNaturalNumber(int naturalNumber)
	    throws IllegalArgumentException {
	if (naturalNumber < 0)
	    throw new IllegalArgumentException(
		    "Method work only for natural numbers");
	int sum = 0;
	while (naturalNumber > 0) {
	    sum += naturalNumber % 10;
	    naturalNumber /= 10;
	}
	return sum;
    }

    // task 86c
    public static int getFirstDigitInNaturalNumber(int naturalNumber)
	    throws IllegalArgumentException {
	if (naturalNumber < 0)
	    throw new IllegalArgumentException(
		    "Method work only for natural numbers");
	return Integer.parseInt(String.valueOf(naturalNumber).substring(0, 1));
    }

    // task 86d
    public static int getAlternatingSumOfDigitsInNaturalNumber(
	    int naturalNumber) throws IllegalArgumentException {
	if (naturalNumber < 0)
	    throw new IllegalArgumentException(
		    "Method work only for natural numbers");
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

    // task 330

    public static List<Integer> getPerfectNumbers(int naturalNumber)
	    throws IllegalArgumentException {
	if (naturalNumber < 0)
	    throw new IllegalArgumentException(
		    "Method work only for natural numbers");
	List<Integer> perfectNumbers = new ArrayList<>();
	for (int i = naturalNumber - 1; i > 0; i--) {
	    if (checkPerfectNumber(i)) {
		perfectNumbers.add(i);
	    }
	}
	return perfectNumbers;
    }

    public static boolean checkPerfectNumber(int naturalNumber)
	    throws IllegalArgumentException {
	if (naturalNumber < 0)
	    throw new IllegalArgumentException(
		    "Method work only for natural numbers");
	int sum = 0;
	for (int i = naturalNumber - 1; i > 0; i--) {
	    if (naturalNumber % i == 0) {
		sum += i;
	    }
	}
	return sum == naturalNumber;
    }

    // task 182
    public static int[] getCountAndSumDiv5NotDiv7(int[] sequence) {
	int[] result = new int[2];
	for (int i = 0; i < sequence.length; i++) {
	    if (sequence[i] % 5 == 0 && sequence[i] % 7 != 0) {
		result[0]++; // count
		result[1] += sequence[i]; //sum
	    }
	}
	return result;
    }

}
