package kr.co.iei.member.model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.iei.common.JDBCTemplate;
import kr.co.iei.member.model.vo.Member;

public class MemberDao {
	public Member getMember(ResultSet rset) {
		Member m = null;
		try {
			m = new Member(rset.getString("member_id"),
					rset.getString("member_pw"),
					rset.getString("member_name"),
					rset.getString("member_phone"),
					rset.getInt("member_age"),
					rset.getString("member_gender"),
					rset.getDate("enroll_date"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}

	public Member selectMemberId(Connection conn, String memberId) {
		String query = "select * from member_tbl where member_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m = getMember(rset);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}

	public ArrayList selectAllMember(Connection conn) {
		String query = "select * from member_tbl";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> lst = new ArrayList<Member>();
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = getMember(rset);
				lst.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return lst;
	}

	public ArrayList<Member> selectMemberName(Connection conn, String memberName) {
		String query = "select * from member_tbl where member_name like '%'||?||'%'";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> lst = new ArrayList<Member>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberName);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = getMember(rset);
				lst.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return lst;
	}

	public int updateMember(Connection conn, Member member) {
		String query = "update member_tbl set member_pw=?, member_phone=?, member_gender=? where member_id = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		 try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getMemberPhone());
			pstmt.setString(3, member.getMemberGender());
			pstmt.setString(4, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		String query = "insert into member_tbl values(?,?,?,?,?,?,sysdate)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getMemberPhone());
			pstmt.setInt(5, m.getMemberAge());
			pstmt.setString(6, m.getMemberGender());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		String query = "delete from member_tbl where member_id = ?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertDelMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		String query = "insert into del_member_tbl values(?,?,?,sysdate)";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberName());
			pstmt.setString(3, m.getMemberPhone());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	

}
