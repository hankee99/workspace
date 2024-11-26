package kr.co.ie.point.controller;
import java.util.*;

import kr.co.iei.point.vo.*;
public class PointControllerImpl implements PointController{
	Grade[] members;
	Scanner sc;
	int index;
	

	public PointControllerImpl() {
		super();
		members = new Grade[30];
		sc = new Scanner(System.in);
		index = 0;
		
		members[index++] = new Silver("silver", "회원1", 1000);
		members[index++] = new Vip("vip", "회원2", 3000);
		members[index++] = new Vip("vip", "회원3",2000);
		members[index++] = new Gold("gold","회원4",2500);
		members[index++] = new Silver("silver", "회원5", 500);
		
		
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
		System.out.print("회원 등급 입력[silver/gild/vip]: ");
		String grd = sc.next();
		
	}

	@Override
	public void printAllMember() {
		System.out.println("\n----------전체 회원 조회------------\n");
		System.out.println("등급\t이름\t포인트\t보너스");
		
	}

	@Override
	public void printOneMember() {
		System.out.println("\n----------회원정보 조회------------\n");
		System.out.print("조회할 회원의 이름을 입력하세요: ");
		String name = sc.next();
		int a = searchMember(name);
		
	}

	@Override
	public void updateMember() {
		System.out.println("\n----------회원정보 수정------------\n");
		System.out.print("수정할 회원의 이름을 입력하세요: ");
		String name = sc.next();
		int a = searchMember(name);
		
	}

	@Override
	public void deleteMember() {
		System.out.println("\n----------회원정보 삭제------------\n");
		System.out.print("삭제할 회원의 이름을 입력하세요: ");
		String name = sc.next();
		int a = searchMember(name);
	}

	@Override
	public int searchMember(String name) {
		for(int i=0; i<index; i++) {
			
		}
		return -1;
		
	}
	

}
