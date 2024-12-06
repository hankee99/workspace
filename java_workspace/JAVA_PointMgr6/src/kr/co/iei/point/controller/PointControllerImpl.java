package kr.co.iei.point.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import kr.co.iei.point.dao.PointDao;
import kr.co.iei.point.vo.Silver;
import kr.co.iei.point.vo.Gold;
import kr.co.iei.point.vo.Vip;
import kr.co.iei.point.vo.Grade;


public class PointControllerImpl implements PointController {
	Scanner sc;
	PointDao dao;

	public PointControllerImpl() {
		super();
		sc = new Scanner(System.in);
		dao = new PointDao();

	}

	@Override
	public void main() {
		while (true) {
			System.out.println("--------------회원관리 프로그램----------------");
			System.out.println("1.회원정보 등록");
			System.out.println("2.전체 회원정보 조회");
			System.out.println("3.회원정보 조회(검색한 1명)");
			System.out.println("4.회원정보 수정");
			System.out.println("5.회원정보 삭제");
			System.out.println("0.프로그램 종료");
			System.out.print("선택 >> ");
			int select = sc.nextInt();
			switch (select) {
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
		sc.nextLine();
		
		if(grd.equals("silver")) {
			System.out.print("이름 입력: ");
			String name = sc.next();
			System.out.print("포인트 입력: ");
			int point = sc.nextInt();
			Silver s = new Silver(grd, name, point);
			dao.insertMember(s);
			System.out.println("등록완료");
		}else if(grd.equals("gold")) {
			System.out.print("이름 입력: ");
			String name = sc.next();
			System.out.print("포인트 입력: ");
			int point = sc.nextInt();
			Gold g = new Gold(grd, name, point);
			dao.insertMember(g);
			System.out.println("등록완료");
		}else if(grd.equals("vip")) {
			System.out.print("이름 입력: ");
			String name = sc.next();
			System.out.print("포인트 입력: ");
			int point = sc.nextInt();
			Vip v = new Vip(grd, name, point);
			dao.insertMember(v);
			System.out.println("등록완료");
		}else {
			System.out.println("잘못 입력하셨습니다.");
		}

		
	}

	@Override
	public void printAllMember() {
		System.out.println("\n----------전체 회원 조회------------\n");
		System.out.println("등급\t이름\t포인트\t보너스");
		for(int i=0; i<dao.getMemberList().size(); i++) {
			System.out.println(dao.getMemberList().get(i).getGrade() + "\t" + dao.getMemberList().get(i).getName() + "\t" +dao.getMemberList().get(i).getPoint() + "\t" +dao.getMemberList().get(i).getBonus());
		}

	}

	@Override
	public void printOneMember() {
		System.out.println("\n----------회원정보 조회------------\n");
		System.out.print("조회할 회원의 이름을 입력하세요: ");
		String name = sc.next();
		
		if(dao.searchMember(name) == -1) {
			System.out.println("일치하는 회원이 없습니다.");
		}else {
			System.out.println(dao.getMember(name).getGrade() + "\t" + dao.getMember(name).getName() + "\t" + dao.getMember(name).getPoint() + "\t" + dao.getMember(name).getBonus());
		}


	}

	@Override
	public void updateMember() {
		System.out.println("\n----------회원정보 수정------------\n");
		System.out.print("수정할 회원의 이름을 입력하세요: ");
		String name = sc.next();
		sc.nextLine();
		
		if(dao.searchMember(name) != -1) {
			System.out.print("수정할 등급: ");
			String grade = sc.next();
			System.out.print("수정할 포인트: ");
			int point = sc.nextInt();
			dao.updateMember(grade, name, point);
			System.out.println("수정이 완료되었습니다.");
		}else {
			System.out.println("일치하는 회원이 없습니다.");
		}


	}

	@Override
	public void deleteMember() {
		System.out.println("\n----------회원정보 삭제------------\n");
		System.out.print("삭제할 회원의 이름을 입력하세요: ");
		String name = sc.next();
		if(dao.searchMember(name) != -1) {			
			dao.deleteMember(name);
			System.out.println("삭제가 완료되었습니다.");
		}else {
			System.out.println("일치하는 회원이 없습니다.");
		}


	}

}
