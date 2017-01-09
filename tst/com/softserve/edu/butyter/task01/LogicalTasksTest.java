package com.softserve.edu.butyter.task01;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test class for testing all methods of LogicalTasks.class.
 * 
 * @version 1.0 18 Dec 2016
 * @author Oleksandr Butyter
 *
 */
public class LogicalTasksTest {

	/**
	 * Data provider method for testReverseNumber.
	 * 
	 * @return object of input data and expected results
	 */
	@DataProvider(name = "reverseNumber")
	public Object[][] dpReverseNumber() {
		return new Object[][] { { 0, 0 }, { 2147483647, 7463847412L }, { 120, 21 } };
	}

	/**
	 * Test for reverseNumber method.
	 * 
	 * @param number
	 *            integer value of input data from reverseNumber data provider
	 * @param expected
	 *            list of integer value of results data from reverseNumber data
	 *            provider
	 */
	@Test(dataProvider = "reverseNumber")
	public void testReverseNumber(final int number, final long expected) {
		long actual = LogicalTasks.reverseNumber(number);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * Data provider method for testIsPrime.
	 * 
	 * @return object of input data and expected results
	 */
	@DataProvider(name = "prime")
	public Object[][] dpPrimeNumbers() {
		return new Object[][] { { 0, false }, { 1, false }, { 2, true }, { 22, false }, { 2147483647, true } };
	}

	/**
	 * Test for isPrime method.
	 * 
	 * @param number
	 *            integer value of input data from prime data provider
	 * @param expected
	 *            boolean value of results data from prime data provider
	 */
	@Test(dataProvider = "prime")
	public void testIsPrime(final int number, final boolean expected) {
		boolean actual = LogicalTasks.isPrime(number);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * Data provider method for testGetMersennePrimes.
	 * 
	 * @return object of input data and expected results
	 */
	@DataProvider(name = "mersennePrimes")
	public Object[][] dpMersennePrimes() {
		return new Object[][] { { 0, Arrays.asList() },
				{ 2147483647, Arrays.asList(3, 7, 31, 127, 8191, 131071, 524287) } };
	}

	/**
	 * Test for getMersennePrimes method.
	 * 
	 * @param number
	 *            integer value of input data from mersennePrimes data provider
	 * @param expected
	 *            list of integer value of results data from mersennePrimes data
	 *            provider
	 */
	@Test(dataProvider = "mersennePrimes")
	public void testGetMersennePrimes(final int number, final List<Integer> expected) {
		List<Integer> actual = LogicalTasks.getMersennePrimes(number);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * Data provider method for testGetNaturalDividers.
	 * 
	 * @return object of input data and expected results
	 */
	@DataProvider(name = "naturalDividers")
	public Object[][] dpNaturalDividers() {
		return new Object[][] { { 0, Arrays.asList() }, { 1, Arrays.asList(1) },
				{ 12345, Arrays.asList(1, 3, 5, 15, 823, 2469, 4115, 12345) }, 
				{ 2, Arrays.asList(1, 2) },
				{ 25, Arrays.asList(1, 5, 25) } };
	}

	/**
	 * Test for getNaturalDividers method.
	 * 
	 * @param number
	 *            integer value of input data from naturalDividers data provider
	 * @param expected
	 *            list of integer value of results data from naturalDividers
	 *            data provider
	 */
	@Test(dataProvider = "naturalDividers")
	public void testGetNaturalDividers(final int number, final List<Integer> expected) {
		List<Integer> actual = LogicalTasks.getNaturalDividers(number);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * Test for reverseNumber method which expects IllegalArgumentException.
	 * 
	 * @param number
	 *            integer value of illegal argument
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	@Parameters(value = "number")
	public void testReverseNumberException(final int number) {
		LogicalTasks.reverseNumber(number);
	}

	/**
	 * Test for isPrime method which expects IllegalArgumentException.
	 * 
	 * @param number
	 *            integer value of illegal argument
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	@Parameters(value = "number")
	public void testIsPrimeException(final int number) {
		LogicalTasks.isPrime(number);
	}

	/**
	 * Test for getMersennePrimes method which expects IllegalArgumentException.
	 * 
	 * @param number
	 *            integer value of illegal argument
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	@Parameters(value = "number")
	public void testGetMersennePrimesException(final int number) {
		LogicalTasks.getMersennePrimes(number);
	}

	/**
	 * Test for getNaturalDividers method which expects
	 * IllegalArgumentException.
	 * 
	 * @param number
	 *            integer value of illegal argument
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	@Parameters(value = "number")
	public void testGetNaturalDividersException(final int number) {
		LogicalTasks.getNaturalDividers(number);
	}

}
