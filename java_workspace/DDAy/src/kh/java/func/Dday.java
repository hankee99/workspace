package kh.java.func;

import java.util.*;

public class Dday {
	
	public void exam() {
		
		
		Scanner sc = new Scanner(System.in);
		System.out.print("D-DAY [년도] 입력 : ");
		int year = sc.nextInt();
		System.out.print("D-DAY [월] 입력 : ");
		int month =sc.nextInt();
		System.out.print("D-DAY [일] 입력 : ");
		int day = sc.nextInt();
		 
		Calendar day1 = Calendar.getInstance();
		day1.set(year, month-1, day);//년,월,일  한번에 입력하면 변경 가능
		
		Calendar today = Calendar.getInstance();//객체가 생성되는 순간의 시간을 캘린더객체로 생성
		//Calendar 객체에서 정보를 가져오는 방법 -> 인스턴스변수.get(알고싶은정보);
		
		long tod = today.getTimeInMillis();
		long dday = day1.getTimeInMillis();
		long days = (dday-tod)/(24*60*60*1000)  + 1;
		
		System.out.println(today.get(Calendar.YEAR));		//년도
		System.out.println(today.get(Calendar.MONTH)+1);	//월(0~11로 제공) -> 보정작업
		System.out.println(today.get(Calendar.DATE));		//일
		
		System.out.println("오늘날짜: "+today.get(Calendar.YEAR) + "년 " + (today.get(Calendar.MONTH)+1) + "월 " +today.get(Calendar.DATE)+"일");
		System.out.println("Dday날짜: "+year + "년 " + month + "월 " +day+"일");
		System.out.println(days+"일 남았습니다.");
	}
	

}
