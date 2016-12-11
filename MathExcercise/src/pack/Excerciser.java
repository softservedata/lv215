package pack;

public class Excerciser {

	public static int sumOfNumbers(int n,int m){
		m--;
		if(m<0) return 0;
		int i = n%10;
		n/=10;
		if(m==0) return i;
		return i+sumOfNumbers(n,m);
	}
	public static void printAllRoots(int number){
		int x =(int) Math.sqrt(number);
		int remainder = number - x*x;
		int y = (int) Math.sqrt(remainder);
		printRoot(number,x,remainder,y);
		
	}
	public static void printAllArmstrongNumbers(int limit){
		for (int i = 10; i < limit; i++) {
			printArmstrongNumbers(i);
		}
	}
	private static int sum(int n,int degree){
		return n==0?0:(int) Math.pow(n%10,degree)+sum(n/10,degree);
	}
//	private static int sum(int n,int degree){
//		int sum = 0;
//		for (int i = 0; i < degree; i++) {
//			int a = n%10;
//			n/=10;
//			sum+=(int) Math.pow(a,degree);
//		}
//		return sum;
//	}
	private static int countNumbersAmount(int i){
		if(i==0) return 0;
		i/=10;
		return 1+countNumbersAmount(i);
	}

	private static void printArmstrongNumbers(int arm){
		int m = countNumbersAmount(arm);
		int sum=sum(arm,m);
		if(sum==arm) System.out.println(arm);
	}
	
	
	private static void printRoot(int number,int x,int remainder,int y){
		int count=0;
		while(true){
			if(x*x+y*y==number){
				System.out.println(x + " " + y);
				count++;
			}
			if(x>=y){
			x--;
			remainder = number - x*x;
			y = (int) Math.sqrt(remainder);
			 
		}else{
			break;
		}
		}
		if(count==0){
			System.out.println("Your number can't be decomposed");
		}
	}
	

}
