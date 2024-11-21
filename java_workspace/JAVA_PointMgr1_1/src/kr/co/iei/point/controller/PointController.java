package kr.co.iei.point.controller;

import java.util.Scanner;
import kr.co.iei.point.vo.Vip;
import kr.co.iei.point.vo.Gold;
import kr.co.iei.point.vo.Silver;
public class PointController {
	

	Silver[] smembarr;
	Gold[] gmembarr;
	Vip[] vmembarr;
	Scanner sc;
	int sindex;
	int gindex;
	int vindex;
	
	public PointController() {
		smembarr = new Silver[10];
		gmembarr = new Gold[10];
		vmembarr = new Vip[10];
		sc = new Scanner(System.in);
		sindex =0;
		gindex =0;
		vindex =0;
		
		smembarr[sindex++] = new Silver("silver","회원1",1000);
		smembarr[sindex++] = new Silver("silver","회원2",2000);
		gmembarr[gindex++] = new Gold("gold","회원3",3000);
		gmembarr[gindex++] = new Gold("gold","회원4",4000);
		vmembarr[vindex++] = new Vip("vip","회원5",5000);
		vmembarr[vindex++] = new Vip("vip","회원6",6000);
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
		System.out.print("회원 등급 입력[silver/gild/vip]: ");
		String grd = sc.next();
		if(grd.equals("silver")) {
			
			System.out.print("이름: ");
			String name = sc.next();
			System.out.print("포인트: ");
			int point = sc.nextInt();
			Silver s = new Silver(grd,name,point);
			
			smembarr[sindex++] = s;
			
			System.out.println("회원등록이 완료되었습니다.");
		}else if(grd.equals("gold")) {
			System.out.print("이름: ");
			String name = sc.next();
			System.out.print("포인트: ");
			int point = sc.nextInt();
			Gold g = new Gold(grd,name,point);
			
			gmembarr[gindex++] = g;
			
			System.out.println("회원등록이 완료되었습니다.");
			
		}else if(grd.equals("vip")) {
			System.out.print("이름: ");
			String name = sc.next();
			System.out.print("포인트: ");
			int point = sc.nextInt();
			Vip v = new Vip(grd,name,point);
			
			vmembarr[vindex++] = v;
			
			System.out.println("회원등록이 완료되었습니다.");
			
		}
		
		
		
	}
	
	public void printAllMember() {
		System.out.println("\n----------전체 회원 조회------------\n");
		System.out.println("등급\t이름\t포인트\t보너스");
		for(int i=0; i<sindex; i++) {
			System.out.println(smembarr[i].getGrade() + "\t" + smembarr[i].getName() + "\t" + smembarr[i].getPoint() + "\t" + smembarr[i].getBonus() +"\n");
		}
		for(int i=0; i<gindex; i++) {
			System.out.println(gmembarr[i].getGrade() + "\t" + gmembarr[i].getName() + "\t" + gmembarr[i].getPoint() + "\t" + gmembarr[i].getBonus() +"\n");
		}
		for(int i=0; i<vindex; i++) {
			System.out.println(vmembarr[i].getGrade() + "\t" + vmembarr[i].getName() + "\t" + vmembarr[i].getPoint() + "\t" + vmembarr[i].getBonus() +"\n");
		}
	}
	
	public void printOneMember() {
		System.out.println("\n----------회원정보 조회------------\n");
		System.out.print("조회할 회원의 이름을 입력하세요: ");
		String name = sc.next();
		int a = searchName(name);
		
		if(a==-1) {
			System.out.println("일치하는 회원이 없습니다.");
		}else if(a/10 == 1){ // silver
			System.out.println("등급\t이름\t포인트\t보너스");
			
			System.out.println(smembarr[a-10].getGrade() + "\t" + smembarr[a-10].getName() + "\t" + smembarr[a-10].getPoint() + "\t" + smembarr[a-10].getBonus() +"\n");
		}else if(a/10 == 2) {
			System.out.println("등급\t이름\t포인트\t보너스");
			
			System.out.println(gmembarr[a-20].getGrade() + "\t" + gmembarr[a-20].getName() + "\t" + gmembarr[a-20].getPoint() + "\t" + gmembarr[a-20].getBonus() +"\n");
			
		}else if(a/10 == 3) {
			System.out.println("등급\t이름\t포인트\t보너스");
			
			System.out.println(vmembarr[a-30].getGrade() + "\t" + vmembarr[a-30].getName() + "\t" + vmembarr[a-30].getPoint() + "\t" + vmembarr[a-30].getBonus() +"\n");
		}
	}
	
	public void updateMember() {
		System.out.println("\n----------회원정보 수정------------\n");
		System.out.print("수정할 회원의 이름을 입력하세요: ");
		String name = sc.next();
		int a = searchName(name);
		
		if(a==-1) {
			System.out.println("일치하는 회원이 없습니다.");
		}else if(a/10 == 1){ //silver라면
			
			System.out.print("수정할 등급을 입력하세요: ");
			String grade = sc.next();
			
			if(grade.equals("gold")) {  // silver -> gold
				System.out.print("수정할 포인트를 입력하세요: ");
				int point = sc.nextInt();
				gmembarr[gindex++] = new Gold("gold",name,point);
				for(int i=a-10; i<sindex-1;i++) {
					smembarr[i] = smembarr[i+1];
				}
				
				smembarr[sindex-1] = null;
				sindex--;
				System.out.println("수정이 완료되었습니다.");
			}else if(grade.equals("vip")) { //silver -> vip
				System.out.print("수정할 포인트를 입력하세요: ");
				int point = sc.nextInt();
				vmembarr[vindex++] = new Vip("vip",name,point);
				for(int i=a-10; i<sindex-1;i++) {
					smembarr[i] = smembarr[i+1];
				}
				
				smembarr[sindex-1] = null;
				sindex--;
				System.out.println("수정이 완료되었습니다.");
			}else if(grade.equals("silver")){ //silver 유지
				System.out.print("수정할 포인트를 입력하세요: ");
				int point = sc.nextInt();
				smembarr[a-10].setPoint(point);
				System.out.println("수정이 완료되었습니다.");
			}
		}else if(a/10 == 2){ //gold라면
			
			System.out.print("수정할 등급을 입력하세요: ");
			String grade = sc.next();
			
			if(grade.equals("silver")) {  // gold -> silver
				System.out.print("수정할 포인트를 입력하세요: ");
				int point = sc.nextInt();
				smembarr[sindex++] = new Silver("silver",name,point);
				for(int i=a-20; i<gindex-1;i++) {
					gmembarr[i] = gmembarr[i+1];
				}
				
				gmembarr[gindex-1] = null;
				gindex--;
				System.out.println("수정이 완료되었습니다.");
			}else if(grade.equals("vip")) { //gold -> vip
				System.out.print("수정할 포인트를 입력하세요: ");
				int point = sc.nextInt();
				vmembarr[vindex++] = new Vip("vip",name,point);
				for(int i=a-20; i<gindex-1;i++) {
					gmembarr[i] = gmembarr[i+1];
				}
				
				gmembarr[gindex-1] = null;
				gindex--;
				System.out.println("수정이 완료되었습니다.");
			}else if(grade.equals("gold")){ //gold 유지
				System.out.print("수정할 포인트를 입력하세요: ");
				int point = sc.nextInt();
				gmembarr[a-20].setPoint(point);
				System.out.println("수정이 완료되었습니다.");
			}
		}else if(a/10 == 3){ // vip라면
			
			System.out.print("수정할 등급을 입력하세요: ");
			String grade = sc.next();
			
			if(grade.equals("silver")) {  // vip -> silver
				System.out.print("수정할 포인트를 입력하세요: ");
				int point = sc.nextInt();
				smembarr[sindex++] = new Silver("silver",name,point);
				for(int i=a-30; i<vindex-1;i++) {
					vmembarr[i] = vmembarr[i+1];
				}
				
				vmembarr[vindex-1] = null;
				vindex--;
				System.out.println("수정이 완료되었습니다.");
			}else if(grade.equals("gold")) { //vip -> gold
				System.out.print("수정할 포인트를 입력하세요: ");
				int point = sc.nextInt();
				gmembarr[gindex++] = new Gold("gold",name,point);
				for(int i=a-30; i<vindex-1;i++) {
					vmembarr[i] = vmembarr[i+1];
				}
				
				vmembarr[vindex-1] = null;
				vindex--;
				System.out.println("수정이 완료되었습니다.");
			}else if(grade.equals("vip")){ //vip 유지
				System.out.print("수정할 포인트를 입력하세요: ");
				int point = sc.nextInt();
				vmembarr[a-30].setPoint(point);
				System.out.println("수정이 완료되었습니다.");
			}
			
			
			
		}
	}
	
	public void deleteMember() {
		System.out.println("\n----------회원정보 삭제------------\n");
		System.out.print("삭제할 회원의 이름을 입력하세요: ");
		String name = sc.next();
		int a = searchName(name);
		
		if(a==-1) {
			System.out.println("일치하는 회원이 없습니다.");
		}else if(a/10 ==1){
			for(int i=a-10; i<sindex-1;i++) {
				smembarr[i] = smembarr[i+1];
			}
			
			smembarr[sindex-1] = null;
			sindex--;
			
			System.out.println("삭제가 완료되었습니다.");
		}else if(a/10 ==2){
			for(int i=a-20; i<gindex-1;i++) {
				gmembarr[i] = gmembarr[i+1];
			}
			
			gmembarr[gindex-1] = null;
			gindex--;
			
			System.out.println("삭제가 완료되었습니다.");
		}else if(a/10 ==3){
			for(int i=a-30; i<vindex-1;i++) {
				vmembarr[i] = vmembarr[i+1];
			}
			
			vmembarr[vindex-1] = null;
			vindex--;
			
			System.out.println("삭제가 완료되었습니다.");
		}
	}
	
	public int searchName(String name) {
		
		int a = -1;
		for(int i=0; i<sindex; i++) {
			if(name.equals(smembarr[i].getName())) {
				a = i+10;
				return a;
			}
		}
		
		for(int i=0; i<gindex; i++) {
			if(name.equals(gmembarr[i].getName())) {
				a = i+20;
				return a;
			}
		}
		
		for(int i=0; i<vindex; i++) {
			if(name.equals(vmembarr[i].getName())) {
				a = i+30;
				return a;
			}
		}
		return a;
	}
}
