package lesson01;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class MyMathTest {

    @Test
    public void getCountOfDigitsInNaturalNumberTest() {
	assertEquals(3, MyMath.getCountOfDigitsInNaturalNumber(689));
	assertEquals(9, MyMath.getCountOfDigitsInNaturalNumber(891568974));
	assertEquals(1, MyMath.getCountOfDigitsInNaturalNumber(0));
	assertEquals(1, MyMath.getCountOfDigitsInNaturalNumber(9));
	assertNotEquals(5, MyMath.getCountOfDigitsInNaturalNumber(158));

    }

    @Test(expected = IllegalArgumentException.class)
    public void getCountOfDigitsInNaturalNumberExceptionTest() {
	MyMath.getCountOfDigitsInNaturalNumber(-9);
    }

    @Test
    public void getSumOfDigitsInNaturalNumberTest() {
	assertEquals(23, MyMath.getSumOfDigitsInNaturalNumber(689));
	assertEquals(57, MyMath.getSumOfDigitsInNaturalNumber(891568974));
	assertEquals(0, MyMath.getSumOfDigitsInNaturalNumber(0));
	assertEquals(9, MyMath.getSumOfDigitsInNaturalNumber(9));
	assertNotEquals(12, MyMath.getSumOfDigitsInNaturalNumber(158));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSumOfDigitsInNaturalNumberExceptionTest() {
	MyMath.getSumOfDigitsInNaturalNumber(-9);
    }

    @Test
    public void getFirstDigitInNaturalNumberTest() {
	assertEquals(6, MyMath.getFirstDigitInNaturalNumber(689));
	assertEquals(8, MyMath.getFirstDigitInNaturalNumber(891568974));
	assertEquals(0, MyMath.getFirstDigitInNaturalNumber(0));
	assertEquals(9, MyMath.getFirstDigitInNaturalNumber(9));
	assertNotEquals(3, MyMath.getFirstDigitInNaturalNumber(158));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getFirstDigitInNaturalNumberExceptionTest() {
	MyMath.getFirstDigitInNaturalNumber(-9);
    }

    @Test
    public void getAlternatingSumOfDigitsInNaturalNumberTest() {
	assertEquals(7, MyMath.getAlternatingSumOfDigitsInNaturalNumber(689));
	assertEquals(-1,
		MyMath.getAlternatingSumOfDigitsInNaturalNumber(891568974));
	assertEquals(0, MyMath.getAlternatingSumOfDigitsInNaturalNumber(0));
	assertEquals(9, MyMath.getAlternatingSumOfDigitsInNaturalNumber(9));
	assertNotEquals(3,
		MyMath.getAlternatingSumOfDigitsInNaturalNumber(158));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAlternatingSumOfDigitsInNaturalNumberExceptionTest() {
	MyMath.getAlternatingSumOfDigitsInNaturalNumber(-9);
    }

    @Test
    public void getPerfectNumbersTest() {
	List<Integer> perfectNumbers = MyMath.getPerfectNumbers(10000);
	assertEquals(4, perfectNumbers.size());
	assertEquals(6, perfectNumbers.get(3).intValue());
	assertEquals(28, perfectNumbers.get(2).intValue());
	assertEquals(496, perfectNumbers.get(1).intValue());
	assertEquals(8128, perfectNumbers.get(0).intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPerfectNumbersExceptionTest() {
	MyMath.getPerfectNumbers(-9);
    }

    @Test
    public void checkPerfectNumberTest() {
	assertTrue(MyMath.checkPerfectNumber(6));
	assertTrue(MyMath.checkPerfectNumber(33550336));
	assertFalse(MyMath.checkPerfectNumber(125));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkPerfectNumberExceptionTest() {
	MyMath.checkPerfectNumber(-9);
    }

    @Test
    public void getCountAndSumDiv5NotDiv7Test() {
	int[] testSequence = { 25, 20, 35, 140, 26, 39 };
	int[] result = MyMath.getCountAndSumDiv5NotDiv7(testSequence);
	assertEquals(2, result[0]);
	assertEquals(45, result[1]);
    }

}
