package kh.java.prac;

import java.util.Scanner;

public class Sample {
	
	public void sample1() {
		Scanner sc = new Scanner(System.in);
		System.out.print("문자입력: ");
		String str= sc.next();
		
		int a = str.charAt(0);
		
		System.out.printf("A의 유니코드값: %d",a);
		sc.close();
		
	}
	public void sample2() {
		Scanner sc = new Scanner(System.in);
		System.out.print("국어 입력: ");
		int g = sc.nextInt();
		System.out.print("수학 입력: ");
		int s = sc.nextInt();
		System.out.print("영어 입력: ");
		int e = sc.nextInt();
		
		System.out.printf("총점: %d\n평균: %d",g+s+e,(g+s+e)/3);
		sc.close();
	}
	
	public void sample3() {
		Scanner sc = new Scanner(System.in);
		System.out.print("첫수 입력: ");
		int a = sc.nextInt();
		System.out.print("둘수 입력: ");
		int b = sc.nextInt();
		
		int sum = a+b;
		int min = a-b;
		int mul = a*b;
		double na = (double)a/b;
		System.out.printf("합: %d\n차: %d\n곱: %d\n나누기: %.2f",sum,min,mul,na);
		sc.close();
	}
	
	public void sample4() {
		Scanner sc = new Scanner(System.in);
		System.out.print("나이 입력: ");
		int a = sc.nextInt();
		
		System.out.print((a>=20) ? "성인" : "입장불가");
		sc.close();
		
	}
	
	public void sample5() {
		Scanner sc = new Scanner(System.in);
		System.out.print("국어 입력: ");
		int g = sc.nextInt();
		System.out.print("수학 입력: ");
		int s = sc.nextInt();
		System.out.print("영어 입력: ");
		int e = sc.nextInt();
		
		int sum = g+s+e;
		double ave = (g+s+e)/3.0;
		String str = (ave>=60) ? "합" : "불합";
		System.out.printf("총점: %d\n평균: %.2f\n%s",sum,ave,str);
		sc.close();
	}
	
	public void sample6() {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력: ");
		int a = sc.nextInt();
		
		System.out.println((a%2 ==0) ? "짝수":"홀수");
		sc.close();
	}

}
