package lesson01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    private static BufferedReader reader = new BufferedReader(
	    new InputStreamReader(System.in));

    public static int getNaturalNumber() {
	while (true) {
	    writeMessage("Введіть натуральне число:");
	    try {
		int number = Integer.parseInt(reader.readLine());
		if (number < 0) {
		    throw new NumberFormatException();
		}
		return number;
	    } catch (NumberFormatException | IOException e) {
		writeMessage("Некоректне число.");
		continue;
	    }
	}

    }

    public static void writeMessage(String message) {
	System.out.println(message);
    }

    public static int[] getIntegerSequence() {
	writeMessage("Довжина послідовності");
	int sequenceLength = getNaturalNumber();
	int[] sequence = new int[sequenceLength];
	writeMessage("Введіть члени послідовності");
	for (int i = 1; i <= sequence.length;) {
	    writeMessage("a" + i + "=?");
	    try {
		sequence[i - 1] = Integer.parseInt(reader.readLine());
	    } catch (NumberFormatException | IOException e) {
		writeMessage("некоректне число");
		continue;
	    }
	    i++;
	}
	return sequence;
    }

}
