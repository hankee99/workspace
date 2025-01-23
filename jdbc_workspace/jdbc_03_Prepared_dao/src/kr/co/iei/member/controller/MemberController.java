package kr.co.iei.member.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import kr.co.iei.member.dao.MemberDao;
import kr.co.iei.member.vo.Member;

public class MemberController {
	Scanner sc;
	MemberDao memberDao;

	public MemberController() {
		super();
		sc = new Scanner(System.in);
		memberDao = new MemberDao();
	}

	public void main() {
		while (true) {
			System.out.println("\n--------------회원관리 프로그램 v3---------------\n");
			System.out.println("1. 전체 회원 조회");
			System.out.println("2. 아이디로 회원 조회");
			System.out.println("3. 이름으로 회원 조회");
			System.out.println("4. 회원 가입");
			System.out.println("5. 회원 정보 수정");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			System.out.print("선택 >> ");
			int select = sc.nextInt();
			switch (select) {
			case 1:
				selectAllMember();
				break;
			case 2:
				selectMemberId();
				break;
			case 3:
				selectMemberName();
				break;
			case 4:
				register();
				break;
			case 5:
				updateMember();
				break;
			case 6:
				exitMember();
				break;
			case 0:
				System.out.println("프로그램 종료...");
				return;
			default:
				System.out.println("0~6사이의 숫자를 입력하시오.");
			}
		}
	}

	public void selectAllMember() {
		System.out.println("\n--------------전체 회원 조회---------------\n");
		ArrayList<Member> lst =  memberDao.selectAllMember();
		if(lst.size() == 0) {
			System.out.println("조회결과 없음");
		}else {
			lst.stream().forEach(i -> System.out.println(i.getMemberId() + ", "+
					i.getMemberPw() + ", "+
								i.getMemberName() + ", "+
					i.getMemberPhone() + ", "+ 
								i.getMemberAge() + ", "+ 
								i.getMemberGender() + ", "+ i.getEnrollDate()
					));
		}
		

	}

	public void selectMemberId() {
		System.out.println("\n--------------아이디로 회원 조회---------------\n");
		System.out.print("조회할 회원 아이디: ");
		String searchId = sc.next();
		Member m = memberDao.selectMemberId(searchId);
		if(m == null) {
			System.out.println("일치하는 회원이 없습니다.");
		}else {
			System.out.println(m.getMemberId() + ", "+
		m.getMemberPw() + ", "+
					m.getMemberName() + ", "+
		m.getMemberPhone() + ", "+ 
					m.getMemberAge() + ", "+ 
					m.getMemberGender() + ", "+ m.getEnrollDate()
		);
		}

	}

	public void selectMemberName() {
		System.out.println("\n--------------이름으로 회원 조회---------------\n");
		System.out.print("조회할 회원 이름: ");
		String searchName = sc.next();
		ArrayList<Member> lst = memberDao.selectMemberName(searchName);
		if(lst.size() == 0) {
			System.out.println("조회결과 없음");
		}else {
			lst.stream().forEach(i -> System.out.println(i.getMemberId() + ", "+
		i.getMemberPw() + ", "+
					i.getMemberName() + ", "+
		i.getMemberPhone() + ", "+ 
					i.getMemberAge() + ", "+ 
					i.getMemberGender() + ", "+ i.getEnrollDate()
		));
		}

	}

	public void register() {
		System.out.println("\n--------------회원 가입---------------\n");
		System.out.print("아이디: ");
		String memberId = sc.next();
		System.out.print("비번: ");
		String memberPw = sc.next();
		System.out.print("이름: ");
		String memberName = sc.next();
		System.out.print("전번: ");
		String memberPhone = sc.next();
		System.out.print("나이: ");
		int memberAge = sc.nextInt();
		System.out.print("성별: ");
		String memberGender = sc.next();
		
		int result = memberDao.insertMember(memberId, memberPw, memberName, memberPhone, memberAge, memberGender);
		if(result == 0) {
			System.out.println("가입실패");
		}else {
			System.out.println("가입성공");
		}
		
	}

	public void updateMember() {
		System.out.println("\n--------------회원 정보 수정---------------\n");
		System.out.print("수정할 회원 아이디: ");
		String memberId = sc.next();
		Member m = memberDao.selectMemberId(memberId);
		if(m != null) {
			System.out.print("수정할 비번: ");
			String memberPw = sc.next();
			System.out.print("수정할 전번: ");
			String memberPhone = sc.next();
			System.out.print("수정할 성별: ");
			String memberGender = sc.next();
			int result = memberDao.updateMember(memberPw, memberPhone, memberGender, memberId);
			if(result == 0) {
				System.out.println("업데이트 실패");
			}else {
				System.out.println("업데이트 성공");
			}
		}else {
			System.out.println("일치하는 회원이 없습니다.");
		}

	}
	
	public void exitMember() {
		System.out.println("\n--------------회원 정보 삭제---------------\n");
		System.out.print("삭제할 회원 아이디: ");
		String memberId = sc.next();
		Member m = memberDao.selectMemberId(memberId);
		if(m != null) {
			int result = memberDao.deleteMember(memberId);
			if(result == 0) {
				System.out.println("삭제 실패");
			}else {
				int result2 = memberDao.insertDelMember(m);
				if(result2 >0) {
					System.out.printf("%d명 삭제 성공",result);
				}else {
					System.out.println("del_member_tbl insert 실패");
				}
				
			}
		}else {
			System.out.println("회원정보를 조회할 수 없습니다.");
		}

	}

}
