package com.softserve.edu.butyter.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01 {

	public static void main(String[] args) {
		int number = inputNaturalNumber();
		System.out.println("Reversed number (Task 88b) - " + LogicalTasks.reverseNumber(number));
		System.out.println("Mersenne primes (Task 559) - " + LogicalTasks.getMersennePrimes(number));
		System.out.println("Natural dividers (Task 224) - " + LogicalTasks.getNaturalDividers(number));
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