package kr.co.iei.exam.controller;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.iei.exam.model.service.ExamService;
import kr.co.iei.exam.model.vo.Board;
import kr.co.iei.exam.model.vo.Member;
import kr.co.iei.exam.model.vo.Post;

public class ExamController {
	Scanner sc;
	ExamService examService;
	public ExamController() {
		super();
		sc = new Scanner(System.in);
		examService = new ExamService();
	}

	public void main() {
		while (true) {
			System.out.println("\n---------- KH커뮤니티 ----------\n");
			System.out.println("1. 로그인 하기");
			System.out.println("2. 회원가입");
			System.out.println("3. 아이디 찾기");
			System.out.println("0. 프로그램 종료");
			System.out.print("선택 >> ");
			int select = sc.nextInt();
			switch (select) {
			case 1:
				login();
				break;
			case 2:
				register();
				break;
			case 3:
				searchId();
				break;
			case 0:
				System.out.println("프로그램 종료...");
				return;
			default:
				System.out.println("0~6사이의 숫자를 입력하시오.");
			}
		}
	}

	public void login() {
		System.out.println("\n---------- 로그인 ----------\n");
		System.out.print("ID 입력: ");
		String memberId = sc.next();
		System.out.print("PW 입력: ");
		String memberPw = sc.next();
		Member m = new Member();
		m.setMemberId(memberId);
		m.setMemberPw(memberPw);
		int memberNo = examService.login(m);
		if (memberNo != -1) {
			System.out.println("longin!");
			board(memberNo);
		} else {
			System.out.println("아이디 또는 비번을 확인하세요.");
		}
	}

	public void register() {
		System.out.println("\n---------- 회원가입 ----------\n");
		System.out.print("ID 입력: ");
		String memberId = sc.next();
		System.out.print("비밀번호 입력: ");
		String memberPw = sc.next();
		System.out.print("이름 입력: ");
		String memberName = sc.next();
		System.out.print("전화번호 입력: ");
		String memberPhone = sc.next();
		Member m = new Member();
		m.setMemberId(memberId);
		m.setMemberPw(memberPw);
		m.setMemberName(memberName);
		m.setMemberPhone(memberPhone);
		int result = examService.register(m);

		if (result > 0) {
			System.out.println("가입 성공!");
		} else {
			System.out.println("가입 실패");
		}
	}

	public void searchId() {
		System.out.println("\n---------- 아이디 찾기 ----------\n");
		System.out.print("이름 입력: ");
		String memberName = sc.next();
		System.out.print("전화번호 입력: ");
		String memberPhone = sc.next();
		Member m = new Member();
		m.setMemberName(memberName);
		m.setMemberPhone(memberPhone);
		String memberId = examService.searchId(m);
		if (memberId.equals(" ")) {
			System.out.println("일치하는 회원 없음");
		} else {
			System.out.println("ID: " + memberId);
		}

	}

	public void board(int memberNo) {
		while (true) {
			System.out.println("\n---------- KH커뮤니티 ----------\n");
			System.out.println("1. 게시물 목록 보기");
			System.out.println("2. 게시물 상세 보기");
			System.out.println("3. 게시물 등록");
			System.out.println("4. 게시물 수정");
			System.out.println("5. 게시물 삭제");
			System.out.println("6. 내정보 보기");
			System.out.println("7. 내정보 변경");
			System.out.println("8. 회원 탈퇴");
			System.out.println("0. 로그아웃");
			System.out.print("선택 >> ");
			int select = sc.nextInt();
			switch (select) {
			case 1:
				postList();
				break;
			case 2:
				selectPost();
				break;
			case 3:
				registPost(memberNo);
				break;
			case 4:
				updatePost(memberNo);
				break;
			case 5:
				deletePost(memberNo);
				break;
			case 6:
				selectMember(memberNo);
				break;
			case 7:
				updateMember(memberNo);
				break;
			case 8:
				int n = deleteMember(memberNo);
				if(n == 1) {
					return;
				}else {
					break;
				}
				
			case 0:
				return;
			default:
				System.out.println("0~8사이의 숫자를 입력하시오.");
			}
		}
	}

	public void postList() {
		System.out.println("\n---------- 게시물 목록 ----------\n");
		ArrayList<Post> lst = examService.postList();
		if(!lst.isEmpty()) {
			lst.stream().forEach(i -> System.out.println(i.toString()));
		}
	}

	public void selectPost() {
		System.out.println("\n---------- 게시물 정보 ----------\n");
		System.out.print("게시글 번호 입력: ");
		int postNo = sc.nextInt();
		Post p = examService.selectPost(postNo);
		if(p != null) {
			System.out.println("게시물 번호: "+ p.getBoardNo());
			System.out.println("게시물 제목: "+ p.getBoardTitle());
			System.out.println("게시물 내용: "+ p.getBoardContent());
			System.out.println("게시물 작성자: "+ p.getMemberName());
			System.out.println("게시물 조회수: "+ p.getReadCount());
			System.out.println("게시물 작성일: "+ p.getWriteDate());
		}else {
			System.out.println("일치하는 게시물이 없습니다.");
		}
	}

	public void registPost(int memberNo) {
		System.out.println("\n---------- 게시물 등록 ----------\n");
		System.out.print("제목 입력: ");
		String postName = sc.next();
		System.out.print("내용 입력: ");
		String contents = sc.next();
		Board board = new Board();
		board.setBoardTitle(postName);
		board.setBoardContent(contents);
		board.setBoardWriter(memberNo);
		
		int result = examService.registPost(board);
		if(result > 0) {
			System.out.println("게시물 등록 성공");
		}else {
			System.out.println("게시글 등록 실패");
		}
		
	}

	public void updatePost(int memberNo) {
		System.out.println("\n---------- 게시물 수정 ----------\n");
		System.out.print("수정할 게시글 번호 입력: ");
		int postNo = sc.nextInt();
		int userNo = examService.searchPost(postNo);
		if(userNo != memberNo && userNo != 0) {
			System.out.println("작성자만 수정 가능합니다.");
		}else if(userNo == 0){
			System.out.println("존재하지 않는 게시물입니다.");
		}else {
			System.out.print("제목 입력: ");
			String newTitle = sc.next();
			System.out.print("내용 입력: ");
			String newContent = sc.next();
			Post p = new Post();
			p.setBoardTitle(newTitle);
			p.setBoardContent(newContent);
			p.setBoardNo(postNo);
			int result = examService.updatePost(p);
			if(result>0) {
				System.out.println("수정 성공");
			}else {
				System.out.println("수정 실패");
			}
		}
		
	}

	public void deletePost(int memberNo) {
		System.out.println("\n---------- 게시물 삭제 ----------\n");
		System.out.print("삭제할 게시글 번호 입력: ");
		int postNo = sc.nextInt();
		int userNo = examService.searchPost(postNo);
		if(userNo != memberNo && userNo != 0) {
			System.out.println("작성자만 삭제 가능합니다.");
		}else if(userNo == 0){
			System.out.println("존재하지 않는 게시물입니다.");
		}else {
			int result = examService.deletePost(postNo);
			if(result>0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
		}
	}

	public void selectMember(int memberNo) {
		System.out.println("\n---------- 내 정보 보기 ----------\n");
		Member m = examService.selectMember(memberNo);
		System.out.println("회원번호: "+m.getMemberNo());
		System.out.println("아이디: "+m.getMemberId());
		System.out.println("비밀번호: "+m.getMemberPw());
		System.out.println("이름: "+m.getMemberName());
		System.out.println("전화번호: "+m.getMemberPhone());
	}

	public void updateMember(int memberNo) {
		System.out.println("\n---------- 내 정보 수정 ----------\n");
		System.out.print("PW 입력: ");
		String newPw = sc.next();
		System.out.print("전화번호 입력: ");
		String newPhone = sc.next();
		Member m = new Member();
		m.setMemberNo(memberNo);
		m.setMemberPw(newPw);
		m.setMemberPhone(newPhone);
		int result = examService.updateMember(m);
		if(result>0) {
			System.out.println("정보 수정 성공");
		}else {
			System.out.println("정보 수정 실패");
		}
	}

	public int deleteMember(int memberNo) {
		System.out.println("\n---------- 회원 탈퇴 ----------\n");
		System.out.print("정말 탈퇴 하시겠습니까?(1.YES / 2.NO): ");
		int select = sc.nextInt();
		if(select == 1) {
			int result = examService.deleteMember(memberNo);
			if(result>0) {
				System.out.println("탈퇴 성공");
			}else {
				System.out.println("탈퇴 실패");
				select = 2;
			}
		}
		return select;
	}

}
