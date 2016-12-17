/* ConsoleHelper 1.0 12/09/2016 */

package com.softserve.edu.zelyonka.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A simple utility class for help to work with console IO operations.
 *
 * @version 1.0 09 December 2016
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */

public final class ConsoleHelper {

    /**
     * Private constructor to prevent creating class examples.
     *
     */
    private ConsoleHelper() {
    }

    /**
     * Reader example for input operations.
     */
    private static BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    /**
     * Providing help to get a natural number from console.
     *
     * @return an integer value of natural number
     */
    public static int getNaturalNumber() {
        while (true) {
            writeMessage("Enter a natural number:");
            try {
                int number = Integer.parseInt(reader.readLine());
                if (number < 0) {
                    throw new NumberFormatException();
                }
                return number;
            } catch (NumberFormatException | IOException e) {
                writeMessage("Incorrect number. Must be positive integer. "
                        + "Please reenter!");
                continue;
            }
        }

    }

    /**
     * Providing help to print a message to console.
     *
     * @param message
     *            a message text to print
     */
    public static void writeMessage(final String message) {
        System.out.println(message);
    }

    /**
     * Providing help to get an integer sequence from console.
     *
     * @return an integer array of sequence members
     */
    public static int[] getIntegerSequence() {
        writeMessage("Enter a sequence length:");
        int sequenceLength = getNaturalNumber();
        int[] sequence = new int[sequenceLength];
        writeMessage("Enter a sequence members:");
        for (int i = 1; i <= sequence.length;) {
            writeMessage("a" + i + "=?");
            try {
                sequence[i - 1] = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                writeMessage("Incorrect number. Please reenter!");
                continue;
            }
            i++;
        }
        return sequence;
    }

}
