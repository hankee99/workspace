package kh.java.func;

import java.util.Scanner;

public class Example {	
	public void exam1() {
		Scanner sc = new Scanner(System.in);
		System.out.print("첫번째 정수입력: ");
		int a= sc.nextInt();
		System.out.print("두번째 정수입력: ");
		int b = sc.nextInt();
		
		System.out.printf("더하기: %d\n빼기: %d\n곱하기: %d\n몫: \n나머지: %d",a+b,a-b,a*b,a/b,a%b);
		sc.close();
	}
	public void exam2() {
		Scanner sc = new Scanner(System.in);
		System.out.print("가로 입력: ");
		double a = sc.nextDouble();
		System.out.print("세로 입력: ");
		double b = sc.nextDouble();
		
		System.out.printf("적: %.2f\n둘레: %.1f",a*b,2*(a+b));
		sc.close();
	}
	
	public void exam3() {
		Scanner sc = new Scanner(System.in);
		System.out.print("영단어 입력: ");
		String str = sc.next();
		
		System.out.printf("첫번째 문자: %c\n두번째 문자: %c\n세번째 문자: %c",str.charAt(0),str.charAt(1),str.charAt(2));
		sc.close();
	}

}
