package kr.co.iei.member.controller;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.iei.member.model.service.MemberService;
import kr.co.iei.member.model.vo.Member;

public class MemberController {
	Scanner sc;
	MemberService memberService;

	public MemberController() {
		super();
		// TODO Auto-generated constructor stub
		sc = new Scanner(System.in);
		memberService = new MemberService();
	}

	public void main() {
		while (true) {
			System.out.println("\n--------------회원관리 프로그램 v5---------------\n");
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
				insertMember();
				break;
			case 5:
				updateMember();
				break;
			case 6:
				deleteMember();
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
		ArrayList<Member> lst = memberService.selectAllMember();
		if(lst.size() >0) {
			lst.stream().forEach(i -> System.out.println(i.toString()));
		}else {
			System.out.println("조회결과 없음.");
		}
		
	}

	public void selectMemberId() {
		System.out.println("\n--------------아이디로 회원 조회---------------\n");
		System.out.print("조회할 회원 아이디: ");
		String memberId = sc.next();
		Member m = memberService.selectMemberId(memberId);
		if(m != null) {
			System.out.println(m.toString());
		}else {
			System.out.println("회원정보를 조회할 수 없음.");
		}
	}

	public void selectMemberName() {
		System.out.println("\n--------------이름으로 회원 조회---------------\n");
		System.out.print("조회할 회원 이름: ");
		String memberName = sc.next();
		ArrayList<Member> lst = memberService.selectMemberName(memberName);
		if(lst.size() >0) {
			lst.stream().forEach(i -> System.out.println(i.toString()));
		}else {
			System.out.println("조회결과 없음.");
		}
	}

	public void insertMember() {
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
		
		Member m = new Member(memberId,memberPw,memberName,memberPhone,memberAge,memberGender,null);
		int result = memberService.insertMember(m);
		
		if(result >0) {
			System.out.println("가입 성공");
		}else {
			System.out.println("가입 실패");
		}
		
	}

	public void updateMember() {
		System.out.println("\n--------------회원 정보 수정---------------\n");
		System.out.print("수정할 회원 아이디: ");
		String memberId = sc.next();
		Member m = memberService.selectMemberId(memberId);
		if(m != null) { //일치하는 회원 있는 경우
			System.out.print("수정할 비번: ");
			String memberPw = sc.next();
			System.out.print("수정할 전번: ");
			String memberPhone = sc.next();
			System.out.print("수정할 성별: ");
			String memberGender = sc.next();
			
			Member member = new Member();
			member.setMemberPw(memberPw);
			member.setMemberPhone(memberPhone);
			member.setMemberGender(memberGender);
			member.setMemberId(memberId);
			int result = memberService.updateMember(member);
			
			if(result >0) {
				System.out.println("수정 성공");
			}else {
				System.out.println("수정 실패");
			}
		}else { //일치하는 회원 없는경우
			System.out.println("일치하는 회원이 없습니다.");
		}
	}

	public void deleteMember() {
		System.out.println("\n--------------회원 삭제---------------\n");
		System.out.println("삭제할 회원 아이디: ");
		String memberId = sc.next();
		
		int result = memberService.deleteMember(memberId);
		if(result > 0) {
			System.out.println("삭제 성공");
		}else if(result == -2) {
			System.out.println("일치하는 회원 없음");
		}else if(result == -1) {
			System.out.println("삭제 실패");
		}else if(result == 0) {
			System.out.println("del_member_tbl에 삽입 실패");
		}
		
		
		
	}

}
