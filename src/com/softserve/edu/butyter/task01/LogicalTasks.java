/*
 * LogicalTasks.
 * 1.0
 * 11 Dec 2016
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.butyter.task01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class of resolving logical tasks.
 * 
 * @version 1.0 11 Dec 2016
 * @author Oleksandr Butyter
 *
 */
public final class LogicalTasks {
    /**
     * Constant for method reverseNumber.
     */
    private static final int TEN = 10;

    /**
     * Constructor to avoid instantiate instance.
     */
    private LogicalTasks() {
    }

    /**
     * Method returns reversed natural number (Task 88b).
     * 
     * @param number natural number
     * @return the integer value of reversed natural number
     * @throws IllegalArgumentException if input parameter is illegal
     */
    public static long reverseNumber(final int number)
            throws IllegalArgumentException {
        if (number >= 0) {
            int tempNumber = number;
            long reversedNumber = 0;

            while (tempNumber / TEN != 0) {
                reversedNumber = reversedNumber * TEN + tempNumber % TEN;
                tempNumber /= TEN;
            }

            reversedNumber = reversedNumber * TEN + tempNumber;
            return reversedNumber;
        } else {
            throw new IllegalArgumentException("Number < 0 !");
        }
    }

    /**
     * Method checks if a number is prime.
     * 
     * @param number natural number to be checked
     * @return true if this number is prime, false otherwise.
     * @throws IllegalArgumentException if input parameter is illegal
     */
    public static boolean isPrime(final int number)
            throws IllegalArgumentException {
        if (number >= 0) {
            boolean isPrime = false;
            if ((number == 0) || (number == 1)) {
                return isPrime;
            }
            isPrime = true;
            for (int i = 2; i < (int) (Math.sqrt(number) + 1); i++) {
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            return isPrime;
        } else {
            throw new IllegalArgumentException("Number < 0 !");
        }
    }

    /**
     * Method returns all Mersenne primes which are smaller than an input
     * natural number (559).
     * 
     * @param number natural number
     * @return the list of integer values of all Mersenne primes which are
     *         smaller than an input natural number
     * @throws IllegalArgumentException if input parameter is illegal
     */
    public static List<Integer> getMersennePrimes(final int number)
            throws IllegalArgumentException {
        if (number >= 0) {
            List<Integer> listMersennePrimes = new ArrayList<>();
            int mersennePrime = 0;
            for (int p = 2; p < Math.log(number + 1L) / Math.log(2); p++) {
                if (isPrime(p)) {
                    mersennePrime = (int) Math.pow(2, p) - 1;
                    if (isPrime((int) mersennePrime)) {
                        listMersennePrimes.add(mersennePrime);
                    }
                }
            }
            return listMersennePrimes;
        } else {
            throw new IllegalArgumentException("Number < 0 !");
        }
    }

    /**
     * Method returns all natural dividers of an input natural number (224).
     * 
     * @param number natural number
     * @return the list of integer values of all natural dividers of an input
     *         natural number. If input number is 0, returns empty list.
     * @throws IllegalArgumentException if input parameter is illegal
     */
    public static List<Integer> getNaturalDividers(final int number)
            throws IllegalArgumentException {
        if (number >= 0) {
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
        } else {
            throw new IllegalArgumentException("Number < 0 !");
        }
    }

}