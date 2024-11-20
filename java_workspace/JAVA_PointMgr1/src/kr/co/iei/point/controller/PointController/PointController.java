package kr.co.iei.point.controller.PointController;
import java.util.Scanner;

import kr.co.iei.point.vo.Silver.Silver;
public class PointController {
	
	Silver[] membarr;
	Scanner sc;
	int index;
	
	public PointController() {
		membarr = new Silver[10];
		sc = new Scanner(System.in);
		index =0;
		
		membarr[index++] = new Silver("S","회원1",1000);
		membarr[index++] = new Silver("B","회원2",2000);
		membarr[index++] = new Silver("G","회원3",3000);
		membarr[index++] = new Silver("P","회원4",4000);
		membarr[index++] = new Silver("D","회원5",5000);
		membarr[index++] = new Silver("VIP","회원6",6000);
	}
	
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
			}
		}
	}
	
	public void insertMember() {
		
		System.out.println("\n----------회원정보 등록------------\n");
		System.out.print("등급: ");
		String rank = sc.next();
		sc.nextLine();
		System.out.print("이름: ");
		String name = sc.next();
		System.out.print("포인트: ");
		int point = sc.nextInt();
		Silver s = new Silver(rank,name,point);
		
		membarr[index++] = s;
		
		System.out.println("회원등록이 완료되었습니다.");
	}
	
	public void printAllMember() {
		System.out.println("\n----------전체 회원 조회------------\n");
		System.out.println("등급\t이름\t포인트\t보너스");
		for(int i=0; i<index; i++) {
			System.out.println(membarr[i].getRank() + "\t" + membarr[i].getName() + "\t" + membarr[i].getPoint() + "\t" + membarr[i].getPoint()*0.02 +"\n");
		}
	}
	
	public void printOneMember() {
		System.out.println("\n----------회원정보 조회------------\n");
		System.out.print("조회할 회원의 이름을 입력하세요: ");
		String name = sc.next();
		int a = searchName(name);
		
		if(a==-1) {
			System.out.println("일치하는 회원이 없습니다.");
		}else {
			System.out.println("등급\t이름\t포인트\t보너스");
			
			System.out.println(membarr[a].getRank() + "\t" + membarr[a].getName() + "\t" + membarr[a].getPoint() + "\t" + membarr[a].getPoint()*0.02 +"\n");
		}
	}
	
	public void updateMember() {
		System.out.println("\n----------회원정보 수정------------\n");
		System.out.print("수정할 회원의 이름을 입력하세요: ");
		String name = sc.next();
		int a = searchName(name);
		
		if(a==-1) {
			System.out.println("일치하는 회원이 없습니다.");
		}else {
			System.out.print("수정할 등급을 입력하세요: ");
			String rank = sc.next();
			System.out.print("수정할 포인트를 입력하세요: ");
			int point = sc.nextInt();
			membarr[a].setRank(rank);
			membarr[a].setPoint(point);
			System.out.println("수정이 완료되었습니다.");
		}
	}
	
	public void deleteMember() {
		System.out.println("\n----------회원정보 삭제------------\n");
		System.out.print("삭제할 회원의 이름을 입력하세요: ");
		String name = sc.next();
		int a = searchName(name);
		
		if(a==-1) {
			System.out.println("일치하는 회원이 없습니다.");
		}else {
			for(int i=a; i<index-1;i++) {
				membarr[i] = membarr[i+1];
			}
			
			membarr[index-1] = null;
			index--;
			
			System.out.println("삭제가 완료되었습니다.");
		}
	}
	
	public int searchName(String name) {
		int a =-1;
		for(int i=0; i<index; i++) {
			if(name.equals(membarr[i].getName())) {
				a = i;
				break;
			}
		}
		return a;
	}
}
