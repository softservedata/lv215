package butyter.task01;

public class Task01 {

	public static void main(String[] args) {
		int number = LogicalTasks.inputNaturalNumber();
		System.out.println("Reversed number (Task 88b) - " + LogicalTasks.reverseNumber(number));
		System.out.println("Mersenne primes (Task 559) - " + LogicalTasks.getMersennePrimes(number));
		System.out.println("Natural dividers (Task 224) - " + LogicalTasks.getNaturalDividers(number));
	}
}
