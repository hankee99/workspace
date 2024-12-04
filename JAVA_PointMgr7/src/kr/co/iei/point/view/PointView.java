package kr.co.iei.point.view;

import java.util.ArrayList;
import java.util.Scanner;


import kr.co.iei.point.vo.Gold;
import kr.co.iei.point.vo.Grade;
import kr.co.iei.point.vo.Silver;
import kr.co.iei.point.vo.Vip;


public class PointView {
	Scanner sc;

	public PointView() {
		super();
		sc = new Scanner(System.in);
	}
	
	public int mainMenu() {
		System.out.println("\n-------------------학생관리 프로그램v6----------------------\n");
		System.out.println("1.회원 정보 등록");
		System.out.println("2.전체 회원 출력");
		System.out.println("3.회원 1명 출력");
		System.out.println("4.회원 정보 수정");
		System.out.println("5.회원 정보 삭제");
		System.out.println("0.프로그램 종료");
		System.out.print("선택 >> ");
		int select = sc.nextInt();
		return select;
	}
	
	public Grade insertMember() {
		System.out.println("\n------------------------회원 정보 입력-----------------------------\n");
		System.out.print("회원 등급 입력(silver, gold, vip): ");
		String grade = sc.next();
		System.out.print("회원 이름 입력: ");
		sc.nextLine();
		String name = sc.next();
		System.out.print("포인트 입력: ");
		int point = sc.nextInt();
		if(grade.equals("silver")) {
			Silver s = new Silver(grade, name, point);
			return s;
		}else if(grade.equals("gold")){
			Gold g = new Gold(grade, name, point);
			return g;
		}else if(grade.equals("vip")) {
			Vip v = new Vip(grade, name, point);
			return v;
		}
		return null;
	}
	
	public void insertMsg() {
		System.out.println("등록이 완료되었습니다.");
	}
	
	public void wrongMsg() {
		System.out.println("일치하는 회원이 없습니다.");
	}
	
	public void updateMsg() {
		System.out.println("수정이 완료되었습니다.");
	}
	
	public void deleteMsg() {
		System.out.println("삭제가 완료되었습니다.");
	}
	
	public void printAllMember(ArrayList<Grade> members) {
		System.out.println("\n------------------------전체 회원 출력-----------------------------\n");
		System.out.println("등급\t이름\t포인트\t보너스");
		for(Grade g : members) {
			System.out.println(g.getGrade() +"\t" + g.getName() +"\t" + g.getPoint() + "\t" + g.getBonus());
		}
	}
	
	public void printOneMember(Grade g) {
		System.out.println("\n------------------------회원 1명 출력-----------------------------\n");
		System.out.println("등급\t이름\t포인트\t보너스");
		System.out.println(g.getGrade() +"\t" + g.getName() +"\t" + g.getPoint() + "\t" + g.getBonus());
	}
	
	public String searchMember() {
		System.out.print("회원의 이름을 입력하세요: ");
		String name = sc.next();
		return name;
	}
	
	
	

}
