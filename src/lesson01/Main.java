package lesson01;

public class Main {

	public static void main(String[] args) {
		int number = ConsoleHelper.getNaturalNumber();
		// 86
		ConsoleHelper.writeMessage("Кількість цифр в числі: " + MyMath.getCountOfDigitsInNaturalNumber(number));
		ConsoleHelper.writeMessage("Перша цифра в числі: " + MyMath.getFirstDigitInNaturalNumber(number));
		ConsoleHelper.writeMessage("Сума цифр в числі: " + MyMath.getSumOfDigitsInNaturalNumber(number));
		ConsoleHelper.writeMessage("Знакозмінна сума цифр в числі: " + MyMath.getAlternatingSumOfDigitsInNaturalNumber(number));		
		// 330
		ConsoleHelper.writeMessage("Ідеальні числа менші від заданого числа:");
		MyMath.getPerfectNumbers(number).forEach(System.out::println);
		// 182
		int[] sequence = ConsoleHelper.getIntegerSequence();
		int[] sequenceProcessingResult = MyMath.getCountAndSumDiv5NotDiv7(sequence);
		System.out.println(
				"Кількість членів послідовності що діляться на 5 і не діляться на 7 " + sequenceProcessingResult[0]);
		System.out.println(
				"Сума членів послідовності що діляться на 5 і не діляться на 7 " + sequenceProcessingResult[1]);
	}

}
