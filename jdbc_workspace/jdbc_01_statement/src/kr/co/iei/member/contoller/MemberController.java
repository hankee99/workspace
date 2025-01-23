package kr.co.iei.member.contoller;

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
		while(true) {
			System.out.println("\n--------------회원관리 프로그램 v1---------------\n");
			System.out.println("1. 전체 회원 조회");
			System.out.println("2. 아이디로 회원 조회");
			System.out.println("3. 이름으로 회원 조회");
			System.out.println("4. 회원 가입");
			System.out.println("5. 회원 정보 수정");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 전체 회원 조회");
			System.out.print("선택 >> ");
			int select = sc.nextInt();
			switch(select) {
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
				return;
			default:
				System.out.println("0~6사이의 숫자를 입력하시오.");
			}
		}
	}
	
	public void selectAllMember() {
		//0-1. 반환할 객체 미리 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null; //이 객체는 수행할 쿼리가 select인 경우만 선언
		//0-2. 쿼리문 수행결과를 처리할 변수 선언
		List<Member> list = new ArrayList<>();
		//0-3. 쿼리문을 문자열 형식으로 저장 : 쿼리문 작성시 ;은 반드시 제거
		String query = "select * from member_tbl";
		
		try {
			//1. 드라이버 등록(데이터베이스와 연결할 때 사용할 클래스를 등록) -> ojdbc.jar이 제공하는 클래
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. Connection 객체 생성
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			//3. Statement 객체 생성
			stmt = conn.createStatement();
			//4. 쿼리문 수행하고 결과를 받아옴
			//5. 수행결과를 받아서 저장
			rset = stmt.executeQuery(query);
			//rset.next() : 커서를 한줄 아래로
			while(rset.next()) {
				list.add(new Member(rset.getString("member_id"),
						rset.getString("member_pw"),
						rset.getString("member_name"),
						rset.getString("member_phone"),
						rset.getInt("member_age"),
						rset.getString("member_gender"),
						rset.getDate("enroll_date")));
			}
			System.out.println("\n--------------전체 회원 조회---------------\n");
			IntStream.range(0, list.size()).forEach(i -> System.out.println(list.get(i).getMemberId() + ", "+list.get(i).getMemberPw() + ", "+list.get(i).getMemberName() + ", "+list.get(i).getMemberPhone() + ", "+list.get(i).getMemberAge() + ", "+list.get(i).getMemberGender() + ", "+list.get(i).getEnrollDate()));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void selectMemberId() {
		System.out.println("\n--------------아이디로 회원 조회---------------\n");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		System.out.print("검색할 아이디를 입력: ");
		String searchId = sc.next();
		String query = "select * from member_tbl where member_id = '" + searchId+"'";
		Member m =null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				m = new Member(rset.getString(1),
						rset.getString(2),
						rset.getString(3),
						rset.getString(4),
						rset.getInt(5),
						rset.getString(6),
						rset.getDate(7));
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
				stmt.close();
				conn.close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(m == null) {
			System.out.println("일치하는 회원이 없습니다.");
		}else {
			System.out.println(m.getMemberId() + ", "+m.getMemberPw() + ", "+m.getMemberName() + ", "+m.getMemberPhone() + ", "+m.getMemberAge() + ", "+m.getMemberGender() + ", "+m.getEnrollDate());
		}
		
		
	}
	
	public void selectMemberName() {
		System.out.println("\n--------------이름으로 회원 조회---------------\n");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		List<Member> list = new ArrayList<Member>();
		System.out.print("검색할 이름을 입력: ");
		String searchName = sc.next();
		String query = "select * from member_tbl where member_name like '" +"%" +searchName+"%"+"'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Member m = new Member(rset.getString("member_id"),
						rset.getString("member_pw"),
						rset.getString("member_name"),
						rset.getString("member_phone"),
						rset.getInt("member_age"),
						rset.getString("member_gender"),
						rset.getDate("enroll_date"));
				
				list.add(m);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(list.isEmpty()) {
			System.out.println("일치하는 회원이 없습니다.");
		}else {
			IntStream.range(0, list.size()).forEach(i -> System.out.println(list.get(i).getMemberId() + ", "+list.get(i).getMemberPw() + ", "+list.get(i).getMemberName() + ", "+list.get(i).getMemberPhone() + ", "+list.get(i).getMemberAge() + ", "+list.get(i).getMemberGender() + ", "+list.get(i).getEnrollDate()));
		}
		
	}
	
	public void register() {
		System.out.println("\n--------------회원 가입---------------\n");
		Connection conn = null;
		PreparedStatement stmt = null;
		//수업코드
//		Statement stmt = null;
//		int result = 0;
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
		Date Date = new Date(System.currentTimeMillis());
		String query = "insert into member_tbl values(?,?,?,?,?,?,?)";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			//conn.setAutoCommit(false); //자동커밋 비활성화하는 코드
			//내코드
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, Id);
			stmt.setString(2, Pw); 
			stmt.setString(3, Name); 
			stmt.setString(4, Phone);
			stmt.setInt(5, Age);
			stmt.setString(6, Gender);
			stmt.setDate(7, Date);
			
			int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("삽입된 행의 수: " + rows);
            }
            
            //수동 커밋, 롤백
//            conn.commit();
//            conn.rollback();
            
            
            //수업코드
//            stmt = conn.createStatement();
//            result = stmt.executeUpdate(query);
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("가입 실패");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//수업코드
//		if(result == 1) {
//			System.out.println("가입성공");
//		}else {
//			System.out.println("가입실패");
//		}
		
	}
	
	public void updateMember() {
		//비번, 전번, 성별 수정
		//아이디를 받아서 존재하면 나머지 정보들 받아서 수정
		System.out.println("\n--------------회원 정보 수정---------------\n");
		System.out.print("수정할 회원 아이디 입력: ");
		String memberId = sc.next();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		int check = 0;
		int result = 0;
		String query = "select count(*) cnt from member_tbl where member_id ='"+memberId+"'";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			rset.next();
			check = rset.getInt("cnt");
			if(check == 1) {
				System.out.print("수정할 비번 입력: ");
				String Pw = sc.next();
				System.out.print("수정할 전번 입력: ");
				String Phone = sc.next();
				System.out.print("수정할 성별 입력: ");
				String Gender = sc.next();
				
				query = "update member_tbl set "
						+ "member_pw = '" + Pw + "', "
						+"member_phone = '" + Phone + "', "
						+ "member_gender = '" + Gender + "' "
						+"where member_id = '"+memberId+"'";
				
				result = stmt.executeUpdate(query);
				
				if(result > 0) {
					conn.commit();
				}else {
					conn.rollback();
				}
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
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(check == 0) {
			System.out.println("일치하는 회원 없음.");
		}else {
			if(result >0) {
				System.out.println("수정 완료");
			}else {
				System.out.println("수정 실패");
			}
		}
		
		
	}
	
	public void exitMember() {
		System.out.println("\n--------------회원 삭제---------------\n");
		System.out.print("삭제할 회원 아이디 입력: ");
		String memberId = sc.next();
		Connection conn = null;
		Statement stmt = null;
		int existQ = 0;
		String query = "delete from member_tbl where member_id ='"+memberId+"'";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			existQ = stmt.executeUpdate(query);
			if(existQ >0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(existQ > 0) {
			System.out.println("삭제 성공");
		}else {
			System.out.println("삭제 실패");
		}
		
		
	}
	
}
