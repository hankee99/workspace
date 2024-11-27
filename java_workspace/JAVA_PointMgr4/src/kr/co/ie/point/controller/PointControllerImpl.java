package kr.co.ie.point.controller;
import kr.co.ie.point.vo.Grade;
import kr.co.ie.point.vo.Silver;
import kr.co.ie.point.vo.Gold;
import kr.co.ie.point.vo.Vip;

import java.net.FileNameMap;
import java.util.*;
public class PointControllerImpl implements PointController{
	Scanner sc;
	ArrayList<Grade> memArr;

	public PointControllerImpl() {
		super();
		sc = new Scanner(System.in);
		
		memArr = new ArrayList<>();
		
		Silver s1 = new Silver("silver","회원1",1000);
		Vip v1 = new Vip("vip","회원2",5000);
		Gold g1 = new Gold("gold","회원3",3000);
		Vip v2 = new Vip("vip","회원4",7000);
		Gold g2 = new Gold("gold","회원5",4000);
		Silver s2 = new Silver("silver","회원6",2000);
		memArr.add(s1);
		memArr.add(v1);
		memArr.add(g1);
		memArr.add(v2);
		memArr.add(g2);
		memArr.add(s2);
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
			
			memArr.add(s);
			System.out.println("회원등록이 완료되었습니다.");
		}else if(grd.equals("gold")) {
			System.out.print("이름: ");
			String name = sc.next();
			System.out.print("포인트: ");
			int point = sc.nextInt();
			Gold g = new Gold(grd,name,point);
			
			memArr.add(g);
			System.out.println("회원등록이 완료되었습니다.");
			
		}else if(grd.equals("vip")) {
			System.out.print("이름: ");
			String name = sc.next();
			System.out.print("포인트: ");
			int point = sc.nextInt();
			Vip v = new Vip(grd,name,point);
			
			memArr.add(v);
			System.out.println("회원등록이 완료되었습니다.");
			
		}
		
		
		
	}
	
	@Override
	public void printAllMember() {
		System.out.println("\n----------전체 회원 조회------------\n");
		System.out.println("등급\t이름\t포인트\t보너스");
		for(int i=0; i<memArr.size(); i++) {
			System.out.println(memArr.get(i).getGrade() + "\t" + memArr.get(i).getName() + "\t" + memArr.get(i).getPoint() + "\t" + memArr.get(i).getBonus());
		}
		
	}
	
	@Override
	public void printOneMember() {
		System.out.println("\n----------회원정보 조회------------\n");
		System.out.print("조회할 회원의 이름을 입력하세요: ");
		String name = sc.next();
		int i = searchMember(name);
		
		if(i==-1) {
			System.out.println("일치하는 회원이 없습니다.");
		}else{
			System.out.println("등급\t이름\t포인트\t보너스");
			
			System.out.println(memArr.get(i).getGrade() + "\t" + memArr.get(i).getName() + "\t" + memArr.get(i).getPoint() + "\t" + memArr.get(i).getBonus() +"\n");
		}
		
	}
	
	@Override
	public void updateMember() {
		System.out.println("\n----------회원정보 수정------------\n");
		System.out.print("수정할 회원의 이름을 입력하세요: ");
		String name = sc.next();
		sc.nextLine();
		int i = searchMember(name);
		if(i == -1) {
			System.out.println("일치하는 회원이 없습니다.");
		}else {
			System.out.print("수정할 등급을 입력하세요: ");
			String grade = sc.next();
			if(grade.equals("silver")) {
				System.out.print("수정할 포인트를 입력하세요: ");
				int point = sc.nextInt();
				memArr.set(i, new Silver(grade,name,point));
			}else if(grade.equals("gold")) {
				System.out.print("수정할 포인트를 입력하세요: ");
				int point = sc.nextInt();
				memArr.set(i, new Gold(grade,name,point));
			}else if(grade.equals("vip")) {
				System.out.print("수정할 포인트를 입력하세요: ");
				int point = sc.nextInt();
				memArr.set(i, new Vip(grade,name,point));
				
			}
			System.out.println("수정이 완료되었습니다.");
		}
		
	}
	
	@Override
	public void deleteMember() {
		System.out.println("\n----------회원정보 삭제------------\n");
		System.out.print("삭제할 회원의 이름을 입력하세요: ");
		String name = sc.next();
		int i = searchMember(name);
		
		memArr.remove(i);
		System.out.println("삭제가 완료되었습니다.");
		
	}
	
	@Override
	public int searchMember(String name) {
		for(int i=0; i<memArr.size(); i++){
			if(name.equals(memArr.get(i).getName())) return i;
		}
		return -1;
	}
	

}
