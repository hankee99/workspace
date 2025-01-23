package kr.co.iei.member.controller;

import java.util.*;
import java.util.stream.*;
import java.sql.*;
import java.sql.Date;

import kr.co.iei.member.vo.Member;

public class MemberController {
	Scanner sc;
	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE"; // 수정 필요
	String username = "jdbc_user"; // DB 사용자 이름
	String password = "1234"; // DB 비밀번호

	public MemberController() {
		super();
		sc = new Scanner(System.in);
	}

	public void main() {
		while (true) {
			System.out.println("\n--------------회원관리 프로그램 v2---------------\n");
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
		// 0-1. 반환할 객체 미리 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from member_tbl";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Member(rset.getString("member_id"), rset.getString("member_pw"), rset.getString("member_name"), rset.getString("member_phone"), rset.getInt("member_age"), rset.getString("member_gender"), rset.getDate("enroll_date")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		if(list.size() == 0) {
			System.out.println("조회 결과 없음");
		}else {
			list.stream().forEach(i -> System.out.println(
					i.getMemberId() +", " 
		+i.getMemberPw() +", " 
					+i.getMemberName() +", " 
		+i.getMemberPhone() +", " 
					+i.getMemberAge() +", " 
		+i.getMemberGender() +", " 
					+i.getEnrollDate()));
		}
		
	}

	public void selectMemberId() {
		System.out.println("\n--------------아이디로 회원 조회---------------\n");
		System.out.print("조회할 회원 아이디: ");
		String searchId = sc.next();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member_tbl where member_id = ?";
		Member m = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setMemberAge(rset.getInt("member_age"));
				m.setMemberGender(rset.getString("member_gender"));
				m.setEnrollDate(rset.getDate("enroll_date"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (m != null) {
			System.out.println(
					m.getMemberId() + ", " + m.getMemberPw() + ", " + m.getMemberName() + ", " + m.getMemberPhone()
							+ ", " + m.getMemberAge() + ", " + m.getMemberGender() + ", " + m.getEnrollDate());
		} else {
			System.out.println("일치하는 회원 없음");
		}

	}

	public void selectMemberName() {
		System.out.println("\n--------------이름으로 회원 조회---------------\n");
		System.out.print("조회할 회원 이름: ");
		String searchName = sc.next();
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member_tbl where member_name like '%'||?||'%'";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchName);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setMemberAge(rset.getInt("member_age"));
				m.setMemberGender(rset.getString("member_gender"));
				m.setEnrollDate(rset.getDate("enroll_date"));
				list.add(m);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!list.isEmpty()) {
			list.stream().forEach(i -> System.out.println(i.getMemberId() + ", " + i.getMemberPw() + ", " + i.getMemberName()
							+ ", " + i.getMemberPhone() + ", " + i.getMemberAge() + ", " + i.getMemberGender() + ", "+ i.getEnrollDate()));
		} else {
			System.out.println("일치하는 회원 없음");
		}

	}

	public void register() {
		System.out.println("\n--------------회원 가입---------------\n");
		Connection conn = null;
		PreparedStatement pstmt = null;

		System.out.print("아이디: ");
		String Id = sc.next();
		sc.nextLine();
		System.out.print("비밀번호: ");
		String Pw = sc.next();
		sc.nextLine();
		System.out.print("이름: ");
		String Name = sc.next();
		sc.nextLine();
		System.out.print("전화번호: ");
		String Phone = sc.next();
		sc.nextLine();
		System.out.print("나이: ");
		int Age = sc.nextInt();
		System.out.print("성별(남or여): ");
		String Gender = sc.next();

		String query = "insert into member_tbl values(?,?,?,?,?,?,sysdate)";
		int rst = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, Id);
			pstmt.setString(2, Pw);
			pstmt.setString(3, Name);
			pstmt.setString(4, Phone);
			pstmt.setInt(5, Age);
			pstmt.setString(6, Gender);

			rst = pstmt.executeUpdate();
			
			if(rst>0) {
				conn.commit();
			}else {
				conn.rollback();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (rst == 0) {
			System.out.println("회원가입 실패");
		} else {
			System.out.println("가입 성공");
		}

	}

	public void updateMember() {
		System.out.println("\n--------------회원 정보 수정---------------\n");
		System.out.print("수정할 회원 아이디: ");
		String memberId = sc.next();
		String query = "select count(*) cnt from member_tbl where member_id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int n = 0;
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			rset.next();
			n = rset.getInt("cnt");

			if (n != 0) {
				System.out.print("수정할 비번 입력: ");
				String Pw = sc.next();
				System.out.print("수정할 전번 입력: ");
				String Phone = sc.next();
				System.out.print("수정할 성별 입력: ");
				String Gender = sc.next();

				query = "update member_tbl set member_pw = ?, member_phone = ?, member_gender = ? where member_id = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, Pw);
				pstmt.setString(2, Phone);
				pstmt.setString(3, Gender);
				pstmt.setString(4, memberId);
				result = pstmt.executeUpdate();
			}
			
			if(result == 0) {
				conn.rollback();
			}else {
				conn.commit();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (result != 0) {
			System.out.println("업데이트 성공");
		} else {
			System.out.println("업데이트 실패");
		}

	}

	public void exitMember() {
		System.out.println("\n--------------회원 삭제---------------\n");
		System.out.print("삭제할 회원 아이디를 입력: ");
		String memberId = sc.next();
		int rst = 0;
		String query = "delete from member_tbl where member_id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rst = pstmt.executeUpdate();
			
			if(rst == 0) {
				conn.rollback();
			}else {
				conn.commit();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (rst == 0) {
			System.out.println("삭제 실패");
		} else {
			System.out.println("삭제되었습니다.");
		}

	}

}
