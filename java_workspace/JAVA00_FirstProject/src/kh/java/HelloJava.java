package kh.java;

import java.util.Scanner;
import java.lang.Math;

public class HelloJava {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("a를 입력하시오: ");
		int a = sc.nextInt();
		System.out.print("b를 입력하시오: ");
		int b = sc.nextInt();

		int cnt1 = 0;
		int cnt2 = 0;
		int B = b;
		int A = a;

		while (B > 0) {
			B /= 10;
			cnt1++;
		}

		while (A > 0) {
			A /= 10;
			cnt2++;
		}

		int ab = a * (int) Math.pow(10, cnt1) + b;
		int ba = b * (int) Math.pow(10, cnt2) + a;

		System.out.print("ab와 ba중 더 큰 수는 ");
		if (ab >= ba) {
			System.out.println(ab);
		} else {
			System.out.println(ba);
		}

		System.out.print("ab와 2ab중 더 큰 수는 ");
		if (ab >= 2 * a * b) {
			System.out.println(ab);
		} else {
			System.out.println(2 * a * b);
		}
		sc.close();
	}

}
