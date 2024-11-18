package kh.java.test1;


import java.util.Scanner;

public class MethodTest1 {
	public void test1() {
		System.out.println("메소드 테스트1");
		
	}
	
	protected void test2() {  //같은 패키지 안에서만 사용
		System.out.println("메소드 테스트2");
	}
	
	private void test3() {  //같은 클래스 안에서만 사용
		System.out.println("메소드 테스트3");
	}
	
	public void test4() {
		System.out.println("메소드 테스트4"); 
	}
	
	public void test15() {
		Scanner sc = new Scanner(System.in);
		System.out.print("첫번째 정수 입력: ");
		int n1 = sc.nextInt();
		System.out.print("두번째 정수 입력: ");
		int n2 = sc.nextInt();
		System.out.print("세번째 정수 입력: ");
		int n3 = sc.nextInt();
		
		
		System.out.println(add(n1,n2));
		System.out.println(add(n1,n2,n3));
		
		
	}
	
	public int add(int a, int b) {
		int rst = a+b;
		return rst;
	}
	public int add(int a, int b,int c) {
		int rst = a+b+c;
		
		return rst;
	}

}
