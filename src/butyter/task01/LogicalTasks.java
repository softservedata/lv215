/*
 * LogicalTasks
 * 1.0
 * 11 Dec 2016
 * Copyright (c) Oleksandr Butyter
 */
package butyter.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class of resolving logical tasks
 * 
 * @version 1.0 11 Dec 2016
 * @author Oleksandr Butyter
 *
 */
public class LogicalTasks {

	/**
	 * Method returns reversed natural number (Task 88b)
	 * 
	 * @param number
	 *            natural number
	 * @return the integer value of reversed natural number
	 */
	public static int reverseNumber(int number) {
		int reversedNumber = 0;

		while (number / 10 != 0) {
			reversedNumber = reversedNumber * 10 + number % 10;
			number /= 10;
		}

		reversedNumber = reversedNumber * 10 + number;
		return reversedNumber;
	}

	/**
	 * Method checks if a number is prime
	 * 
	 * @param number
	 *            natural number to be checked
	 * @return true if this number is prime, false otherwise.
	 */
	public static boolean isPrime(int number) {
		boolean isPrime = true;
		
		for (int i = 2; i < (int) (Math.sqrt(number) + 1); i++) {
			if (number % i == 0) {
				isPrime = false;
				break;
			}
		}
		return isPrime;
	}

	/**
	 * Method returns all Mersenne primes which are smaller than an input
	 * natural number (559)
	 * 
	 * @param number
	 *            natural number
	 * @return the list of integer values of all Mersenne primes which are
	 *         smaller than an input natural number
	 */
	public static List<Integer> getMersennePrimes(int number) {
		List<Integer> listMersennePrimes = new ArrayList<>();
		int mersennePrime = 0;
		
		for (int p = 2; p <= Math.log(number + 1) / Math.log(2); p++) {
			if (isPrime(p)) {
				mersennePrime = (int) Math.pow(2, p) - 1;
				if (isPrime(mersennePrime)) {
					listMersennePrimes.add(mersennePrime);
				}
			}
		}
		return listMersennePrimes;
	}

	/**
	 * Method returns all natural dividers of an input natural number (224)
	 * 
	 * @param number
	 *            natural number
	 * @return the list of integer values of all natural dividers of an input
	 *         natural number. If input number is 0, returns empty list.
	 */
	public static List<Integer> getNaturalDividers(int number) {
		List<Integer> listNaturalDivider = new ArrayList<>();
		int k = 1;
		
		if (number == 0) {
			return listNaturalDivider;
		}		
		listNaturalDivider.add(1);
		if (number == 1) {
			return listNaturalDivider;
		}

		for (int i = 2; i < number / k; i++) {
			if (number % i == 0) {
				listNaturalDivider.add(i);
				if (i != number / i) {
					listNaturalDivider.add(number / i);
				}
				k = i;
			}
		}
		listNaturalDivider.add(number);
		Collections.sort(listNaturalDivider);
		return listNaturalDivider;
	}

	/**
	 * Method checks if input number is natural number. If not, makes input data
	 * again
	 * 
	 * @return the value of integer of natural number. If input data is
	 *         incorrect, shows appropriate message
	 */
	public static int inputNaturalNumber() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("Please, enter natural number: ");
			try {
				int number = Integer.parseInt(br.readLine());
				if (number < 0) {
					throw new NumberFormatException();
				}
				return number;
			} catch (NumberFormatException | IOException e) {
				System.out.println("Wrong input data!!! You had to input natural number!");
				continue;
			}
		}
	}
}
