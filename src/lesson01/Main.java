package lesson01;

public class Main {

	public static void main(String[] args) {
		int number = ConsoleHelper.getNaturalNumber();
		// 86
		ConsoleHelper.writeMessage("ʳ������ ���� � ����: "
				+ MyMath.getCountOfDigitsInNaturalNumber(number));
		ConsoleHelper.writeMessage("����� ����� � ����: " + MyMath.getFirstDigitInNaturalNumber(number));
		ConsoleHelper.writeMessage("���� ���� � ����: " + MyMath.getSumOfDigitsInNaturalNumber(number));
		ConsoleHelper.writeMessage("���������� ���� ���� � ����: " + MyMath.getAlternatingSumOfDigitsInNaturalNumber(number));		
		// 330
		ConsoleHelper.writeMessage("������� ����� ����� �� �������� �����:");
		MyMath.getPerfectNumbers(number).forEach(System.out::println);
		// 182
		int[] sequence = ConsoleHelper.getIntegerSequence();
		int[] sequenceProcessingResult = MyMath.getCountAndSumDiv5NotDiv7(sequence);
		System.out.println(
				"ʳ������ ����� ����������� �� ������� �� 5 � �� ������� �� 7 " + sequenceProcessingResult[0]);
		System.out.println(
				"���� ����� ����������� �� ������� �� 5 � �� ������� �� 7 " + sequenceProcessingResult[1]);
	}

}
