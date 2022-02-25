import java.math.BigInteger;
import java.util.Scanner;

public class Fibonacci {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		Scanner input = new Scanner(System.in);
		
		
		System.out.print("Choose the number of terms: ");
		long x = input.nextInt();
		
		BigInteger t1 = new BigInteger("0");
		BigInteger t2 = new BigInteger("1");
		
		if(x == 1) {				//if the user just inputs 1
			System.out.print(t1);
		}
		if(x == 2) {				//if the user just inputs 2
			System.out.print(t1 + "\n" + t2);
		}
		
		if(x > 2) {	
			System.out.print(t1 + "\n" + t2 + "\n");
			for(int i = 0; i < x; ++i) {
			
				BigInteger t3 = t1.add(t2);
				
				System.out.println(t3);
				
				t1 = t2;
				t2 = t3;
			}
		} else {
			System.out.println("Invalid");
		}
	}
}
