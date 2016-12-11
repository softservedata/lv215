package pack;

import java.util.Date;

public class Main {

	public static void main(String[] args) {
		long s1 = new Date().getTime();
		Excerciser.printAllRoots(1256);
		Excerciser.printAllArmstrongNumbers(10000);
		System.out.println(Excerciser.sumOfNumbers(123,3));
		long s2 = new Date().getTime();
		System.out.println(s2-s1+" sec");
	}

}
