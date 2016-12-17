package com.softserve.edu.zelyonka.task01;

/**
 * A class for testing purpose.
 *
 * @version 1.0 09 December 2016
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public final class Main {

    /**
     * Private constructor to prevent creating class examples.
     *
     */
    private Main() {
    }

    /**
     * Method for testing code.
     *
     * @param args
     *            arguments from console
     */
    public static void main(final String[] args) {
        int number = ConsoleHelper.getNaturalNumber();
        // 86
        ConsoleHelper.writeMessage("Count of digits in number: "
                + MyMath.getCountOfDigitsInNaturalNumber(number));
        ConsoleHelper.writeMessage("First digit in number: "
                + MyMath.getFirstDigitInNaturalNumber(number));
        ConsoleHelper.writeMessage("Sum of digits in number: "
                + MyMath.getSumOfDigitsInNaturalNumber(number));
        ConsoleHelper.writeMessage("Alternating sum of digits in number: "
                + MyMath.getAlternatingSumOfDigitsInNaturalNumber(number));
        // 330
        ConsoleHelper
                .writeMessage("Perfect numbers which is lesser than number:");
        MyMath.getPerfectNumbers(number).forEach(System.out::println);
        // 182
        int[] sequence = ConsoleHelper.getIntegerSequence();
        ConsoleHelper.writeMessage(
                "Count of sequence members that are divided into five "
                        + "and not divided into seven "
                        + MyMath.getCountDivIntoFiveNotDivIntoSeven(sequence));
        ConsoleHelper.writeMessage(
                "Sum of sequence members that are divided into five "
                        + "and not divided into seven "
                        + MyMath.getSumDivIntoFiveNotDivIntoSeven(sequence));
    }

}
