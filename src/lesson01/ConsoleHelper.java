package lesson01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    private static BufferedReader reader = new BufferedReader(
	    new InputStreamReader(System.in));

    public static int getNaturalNumber() {
	while (true) {
	    writeMessage("������ ���������� �����:");
	    try {
		int number = Integer.parseInt(reader.readLine());
		if (number < 0) {
		    throw new NumberFormatException();
		}
		return number;
	    } catch (NumberFormatException | IOException e) {
		writeMessage("���������� �����.");
		continue;
	    }
	}

    }

    public static void writeMessage(String message) {
	System.out.println(message);
    }

    public static int[] getIntegerSequence() {
	writeMessage("������� �����������");
	int sequenceLength = getNaturalNumber();
	int[] sequence = new int[sequenceLength];
	writeMessage("������ ����� �����������");
	for (int i = 1; i <= sequence.length;) {
	    writeMessage("a" + i + "=?");
	    try {
		sequence[i - 1] = Integer.parseInt(reader.readLine());
	    } catch (NumberFormatException | IOException e) {
		writeMessage("���������� �����");
		continue;
	    }
	    i++;
	}
	return sequence;
    }

}
