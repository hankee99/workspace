package kr.co.ie.point.controller;

import java.util.HashMap;
import java.util.Scanner;

import kr.co.ie.point.vo.Gold;
import kr.co.ie.point.vo.Grade;
import kr.co.ie.point.vo.Silver;
import kr.co.ie.point.vo.Vip;

public class PointControllerImpl implements PointController{
	
	Scanner sc;
	HashMap<String, Grade> map;
	
	public PointControllerImpl() {
		super();
		sc = new Scanner(System.in);
		map = new HashMap<String, Grade>();
		Silver s1 = new Silver("silver","회원1",1000);
		Silver s2 = new Silver("silver","회원2",2000);
		Gold g1 = new Gold("gold","회원3",3000);
		Vip v1 = new Vip("vip","회원4",7000);
		map.put(s1.getName(), s1);
		map.put(s2.getName(), s2);
		map.put(g1.getName(), g1);
		map.put(v1.getName(), v1);
		
	}

	@Override
	public void main() {
		while(true) {
			System.out.println("--------------회원관리 프로그램----------------");
			System.out.println("1.회원정보 등록");
			System.out.println("2.전체 회원정보 조회");
			System.out.println("3.회원정보 조회(검색한 1명)");
			System.out.println("4.회원정보 수정");
			System.out.println("5.회원정보 삭제");
			System.out.println("0.프로그램 종료");
			System.out.print("선택 >> ");
			int select = sc.nextInt();
			switch(select) {
			case 1:
				insertMember();
				break;
			case 2:
				printAllMember();
				break;
			case 3:
				printOneMember();
				break;
			case 4:
				updateMember();
				break;
			case 5:
				deleteMember();
				break;
			case 0:
				System.out.println("프로그램이 종료되었습니다.");
				return;
			default:
				System.out.println("잘못입력하셨습니다.");
				break;
			}
		}
	}
	
	@Override
	public void insertMember() {
		System.out.println("\n----------회원정보 등록------------\n");
		System.out.print("회원 등급 입력[silver/gold/vip]: ");
		String grd = sc.next();
		
		if(grd.equals("silver")) {
			
			System.out.print("이름: ");
			String name = sc.next();
			System.out.print("포인트: ");
			int point = sc.nextInt();
			Silver s = new Silver(grd,name,point);
			
			map.put(name, s);
			System.out.println("회원등록이 완료되었습니다.");
		}else if(grd.equals("gold")) {
			System.out.print("이름: ");
			String name = sc.next();
			System.out.print("포인트: ");
			int point = sc.nextInt();
			Gold g = new Gold(grd,name,point);
			
			map.put(name, g);
			System.out.println("회원등록이 완료되었습니다.");
			
		}else if(grd.equals("vip")) {
			System.out.print("이름: ");
			String name = sc.next();
			System.out.print("포인트: ");
			int point = sc.nextInt();
			Vip v = new Vip(grd,name,point);
			
			map.put(name, v);
			System.out.println("회원등록이 완료되었습니다.");
			
		}
	}
	@Override
	public void printAllMember() {
		System.out.println("\n----------전체 회원 조회------------\n");
		System.out.println("등급\t이름\t포인트\t보너스");
		for(String str : map.keySet()) {
			System.out.println(map.get(str).getGrade() + "\t" + map.get(str).getName() + "\t" + map.get(str).getPoint() + "\t" + map.get(str).getBonus());
		}
		
	}
	@Override
	public void printOneMember() {
		System.out.println("\n----------회원정보 조회------------\n");
		System.out.print("조회할 회원의 이름을 입력하세요: ");
		String name = sc.next();
		
		if(!map.containsKey(name)) {
			System.out.println("일치하는 회원이 없습니다.");
		}else {
			System.out.println(map.get(name).getGrade() + "\t" + map.get(name).getName() + "\t" + map.get(name).getPoint() + "\t" + map.get(name).getBonus());
		}
		
	}
	@Override
	public void updateMember() {
		System.out.println("\n----------회원정보 수정------------\n");
		System.out.print("수정할 회원의 이름을 입력하세요: ");
		String name = sc.next();
		sc.nextLine();
		
		if(!map.containsKey(name)) {
			System.out.println("일치하는 회원이 없습니다.");
		}else {
			System.out.print("수정할 등급을 입력하세요: ");
			String grade = sc.next();
			if(grade.equals("silver")) {
				System.out.print("수정할 포인트를 입력하세요: ");
				int point = sc.nextInt();
				Silver s = new Silver(grade,name,point);
				map.put(name, s);
			}else if(grade.equals("gold")) {
				System.out.print("수정할 포인트를 입력하세요: ");
				int point = sc.nextInt();
				Gold g = new Gold(grade,name,point);
				map.put(name, g);
			}else if(grade.equals("vip")) {
				System.out.print("수정할 포인트를 입력하세요: ");
				int point = sc.nextInt();
				Vip v = new Vip(grade,name,point);
				map.put(name, v);
			}
			System.out.println("수정이 완료되었습니다.");
		}
		
	}
	@Override
	public void deleteMember() {
		System.out.println("\n----------회원정보 삭제------------\n");
		System.out.print("삭제할 회원의 이름을 입력하세요: ");
		String name = sc.next();
		
		if(map.remove(name) == null) {
			System.out.println("일치하는 회원이 없습니다.");
		}else {
			System.out.println("삭제가 완료되었습니다.");
		}
		
	}
}
