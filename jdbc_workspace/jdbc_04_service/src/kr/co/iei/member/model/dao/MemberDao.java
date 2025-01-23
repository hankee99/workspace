package kr.co.iei.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.iei.member.model.vo.Member;

public class MemberDao {

	public Member selectMemberId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Member m = null;
		String query = "select * from member_tbl where member_id = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m = new Member(rset.getString("member_id"),
						rset.getString("member_pw"),
						rset.getString("member_name"),
						rset.getString("member_phone"),
						rset.getInt("member_age"),
						rset.getString("member_gender"),
						rset.getDate("enroll_date"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return m;
	}

	public ArrayList<Member> selectMemberName(Connection conn, String memberName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Member> lst = new ArrayList<Member>();
		String query = "select * from member_tbl where member_name like '%'||?||'%'";
		
		try {
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
						rset.getDate("enroll_date")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return lst;
	}

	public ArrayList<Member> selectAllMember(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Member> lst = new ArrayList<Member>();
		String qeury = "select * from member_tbl";
		
		try {
			pstmt = conn.prepareStatement(qeury);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				lst.add(new Member(rset.getString("member_id"),
						rset.getString("member_pw"),
						rset.getString("member_name"),
						rset.getString("member_phone"),
						rset.getInt("member_age"),
						rset.getString("member_gender"),
						rset.getDate("enroll_date")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return lst;
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
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateMember(Connection conn, String memberId, String memberPw, String memberPhone, String memberGender) {
		PreparedStatement pstmt = null;
		String query = "update member_tbl set member_pw = ?, member_phone = ?, member_gender = ? where member_id = ?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberPw);
			pstmt.setString(2, memberPhone);
			pstmt.setString(3, memberGender);
			pstmt.setString(4, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	
	
}
