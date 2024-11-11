package kh.java.func;

import java.util.Scanner;

public class ScanTest {
	
	public void testscan() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("국어를 입력하시오: ");
		int g =sc.nextInt();
		System.out.print("수학을 입력하시오: ");
		int s =sc.nextInt();
		System.out.print("영어를 입력하시오: ");
		int e =sc.nextInt();
		
		int sum = g+s+e;
		double ave = (g+s+e)/3.0;
		
		
		System.out.printf("당신 성적의 총 합은 %d점이고 평균은 %.2f입니다.",sum,ave);
		
		sc.close();
		
	}

}
