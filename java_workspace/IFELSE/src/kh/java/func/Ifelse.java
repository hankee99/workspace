package kh.java.func;

import java.util.Scanner;

public class Ifelse {
	
	public void man() {
		Scanner sc = new Scanner(System.in);
		System.out.print("입장하실 총 인원: ");
		int total = sc.nextInt();
		System.out.print("어른 몇명: ");
		int adl = sc.nextInt();
		System.out.print("아이 몇명: ");
		int chd = sc.nextInt();
		
		if(adl + chd == total) {
			System.out.printf("지불하실 총 금액은 %d원입니다.",adl*15000 + chd*5000);
		}else {
			System.out.println("인원이 맞지 않습니다.");
		}
		sc.close();
	}
	
	public void quiz() {
		Scanner sc = new Scanner(System.in);
		int cnt =0;
		System.out.print("첫번째퀴즈\n사과는 영어로 무엇(1.apple,2.스티브잡스)?: ");
		int ans1 = sc.nextInt();
		System.out.println((ans1 == 1) ? "정답!" : "땡!");
		System.out.print("두번째퀴즈\n바나나는 길어 길으면 기차 기차는(1.비싸,2.빨라)?: ");
		int ans2 = sc.nextInt();
		System.out.println((ans2 == 2) ? "정답!" : "땡!");
		if(ans1 == 1) {
			cnt++;
		}
		if(ans2 == 2) {
			cnt++;
		}
		System.out.printf("총 %d문제를 맞춤.",cnt);
		sc.close();
	}
	
	public void simri() {
		Scanner sc = new Scanner(System.in);
		System.out.print("당신은 술 좋아함(1.yes,2.no)?: ");
		int ans1 = sc.nextInt();
		if(ans1 == 1) {
			System.out.print("당신은 담배핌(1.yes,2.no)?: ");
			int a = sc.nextInt();
			System.out.println((a==1)? "담배는 건강에 좋지 않습니다." : "술은 간에 좋지 않습니다.");
		}else {
			System.out.print("당신은 이성친구가 있습니까?(1.yes,2.no)?: ");
			int b = sc.nextInt();
			System.out.println((b==1)? "올 ㅋ" : "힘내라");
			}
		
		sc.close();
	}
	
	public void simri2() {
		Scanner sc = new Scanner(System.in);
		System.out.print("문자입력: ");
		char a = sc.next().charAt(0);
		if(a >=65 && a<=90) {
			System.out.print("대문자를 입력하였습니다.");
			System.out.printf("소문자로 변환: %c",a+32);
		}else if(a>= 97 && a<=122){
			System.out.print("소문자를 입력하였습니다.");
			System.out.printf("대문자로 변환: %c",a-32);
		}else {
			System.out.print("잘못하셨우.");
		}
		sc.close();
	}
	
	public void test4() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("정수를 입력하세요: ");
		int num =sc.nextInt();
		switch(num) {
		case 1:
			System.out.println("11");
			break;
		case 2:
			System.out.println("22");
			break;
		case 3:
			System.out.println("33");
			break;
		default :
			System.out.println("1/2/3 중 입력하세요");
		}
		sc.close();
		
	}
	
	public void yoon_nyun() {
		Scanner sc = new Scanner(System.in);
		System.out.print("년도를 입력하세요: ");
		int year =sc.nextInt();
		System.out.print("일수를 알고싶은 달을 입력하세요: ");
		int num =sc.nextInt();
		
		
		switch(num) {
		case 4,6,9,11:
			System.out.printf("%d년도 %d월달은 30일 까지 있습니다.",year,num);
			break;
		case 2:
			if(year%4 == 0 && year%100 == 0) {
				System.out.printf("%d년도 %d월달은 28일 까지 있습니다.",year,num);
			}else if(year%4 == 0) {
				System.out.printf("%d년도 %d월달은 29일 까지 있습니다.",year,num);
			}else {
				System.out.printf("%d년도 %d월달은 28일 까지 있습니다.",year,num);
			}
			
			break;
		default:
			System.out.printf("%d년도 %d월달은 31일 까지 있습니다.",year,num);
			break;
		}
		sc.close();
		
	}
	
	public void test5() {
		Scanner sc = new Scanner(System.in);
		System.out.print("1숫자입력: ");
		int n1 =sc.nextInt();
		System.out.print("2숫자입력: ");
		int n2 =sc.nextInt();
		
		System.out.printf("%d ~ %d의 정수의 합: %d\n",n1,n2,(n2-n1+1)*(n1+n2)/2);

		sc.close();
		
	}
	
	
	
	
	
	
	public void test6() {
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자입력: ");
		int n =sc.nextInt();
		
		System.out.printf("짝수들의 합: %d",(n/2)*((n/2)+1));
		sc.close();
	}
	
	
	
	public void test7() {
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자입력: ");
		int n =sc.nextInt();
		int i = 1;
		while(i<10) {
			System.out.printf("%d * %d = %d\n",n,i,n*i);
			i++;
		}
		
		sc.close();
	}
	

}
