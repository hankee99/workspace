package kh.java;
import java.util.Scanner;

public class Ex {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("a: ");
		int a = sc.nextInt();
		System.out.print("b: ");
		int b = sc.nextInt();
		System.out.print("c: ");
		int c = sc.nextInt();
		
		if(a != b && b!=c && c!=a) {
			System.out.println((a+b+c));
		}else if(a == b && b == c && c == a){
			System.out.println((a+b+c)*(a*a + b*b + c*c)*(a*a*a + b*b*b + c*c*c));
		}else {
			System.out.println((a+b+c)*(a*a + b*b + c*c));
		}
		sc.close();

	}

}
