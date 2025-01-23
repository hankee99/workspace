package kr.co.iei.member.dao;
//DataAccessObject : DB에 접속해서 데이터를 주고받는 기능들을 모아둔 클래스

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.iei.member.vo.Member;

public class MemberDao {
	
	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE"; // 수정 필요
	String username = "jdbc_user"; // DB 사용자 이름
	String password = "1234"; // DB 비밀번호
	String className = "oracle.jdbc.driver.OracleDriver";
	
	
	public ArrayList<Member> selectAllMember(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member_tbl";
		ArrayList<Member> lst = new ArrayList<Member>();
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				lst.add(new Member(rset.getString("member_id"),
						rset.getString("member_pw"),
						rset.getString("member_name"),
						rset.getString("member_phone"),
						rset.getInt("member_age"),
						rset.getString("member_gender"),
						rset.getDate("enroll_date")
						));
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return lst;
		
	}
	
	
	
	
	
	public Member selectMemberId(String searchId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String query = "select * from member_tbl where member_id = ?";
		
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m = new Member(rset.getString("member_id"),
						rset.getString("member_pw"),
						rset.getString("member_name"),
						rset.getString("member_phone"),
						rset.getInt("member_age"),
						rset.getString("member_gender"),
						rset.getDate("enroll_date")
						);
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
		
		return m;
	}
	
	
	public ArrayList<Member> selectMemberName(String memberName){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> lst = new ArrayList<Member>();
		String query = "select * from member_tbl where member_name like '%'||?||'%'";
		
		try {
			Class.forName(className);
			conn  = DriverManager.getConnection(jdbcUrl, username, password);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberName);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				lst.add(new Member(rset.getString("member_id"),
						rset.getString("member_pw"),
						rset.getString("member_name"),
						rset.getString("member_phone"),
						rset.getInt("member_age"),
						rset.getString("member_gender"),
						rset.getDate("enroll_date")
						));
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return lst;
	}
	
	
	public int insertMember(String memberId, String memberPw, String memberName, String memberPhone, int memberAge, String memberGender) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "insert into member_tbl values(?,?,?,?,?,?,sysdate)";
		int result = 0;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			pstmt.setString(3, memberName);
			pstmt.setString(4, memberPhone);
			pstmt.setInt(5, memberAge);
			pstmt.setString(6, memberGender);
			
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
		
	}
	
	
	
	public int updateMember(String memberPw, String memberPhone, String memberGender, String memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "update member_tbl set member_pw = ?, member_phone = ?, member_gender = ? where member_id = ?";
		int result = 0;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberPw);
			pstmt.setString(2, memberPhone);
			pstmt.setString(3, memberGender);
			pstmt.setString(4, memberId);
			
			result = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	public int deleteMember(String memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "delete from member_tbl where member_id = ?";
		int result = 0;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
			if(result>0) {
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return result;
	}
	
	public int insertDelMember(Member m) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "insert into del_member_tbl values(?,?,?,sysdate)";
		int result = 0;
		
		try {
			Class.forName(className);
			conn  = DriverManager.getConnection(jdbcUrl, username, password);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberName());
			pstmt.setString(3, m.getMemberPhone());
			result = pstmt.executeUpdate();
			if(result>0) {
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return result;
	}
	
	
}
