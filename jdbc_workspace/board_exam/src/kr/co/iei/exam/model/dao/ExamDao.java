package kr.co.iei.exam.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.iei.common.JDBCTemplate;
import kr.co.iei.exam.model.vo.Board;
import kr.co.iei.exam.model.vo.Member;
import kr.co.iei.exam.model.vo.Post;

public class ExamDao {

	public int register(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		String query = "insert into member_tbl values(member_seq.nextval,?,?,?,?)";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getMemberPhone());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public String searchId(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select member_id from member_tbl where member_name = ? and member_phone = ?";
		String Id = "";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberName());
			pstmt.setString(2, m.getMemberPhone());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				Id = rset.getString("member_id");
			}else {
				Id = " ";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Id;
	}

	public int login(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select member_no from member_tbl where member_id = ? and member_pw = ?";
		int result = -1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("member_no");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int registPost(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into board_tbl values(board_seq.nextval, ?, ?, ?, 0, sysdate)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getBoardWriter());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Post> postList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Post> lst = new ArrayList<Post>();
		String query = "select board_no, board_title, nvl(member_name,'탈퇴회원') member_name, read_count, write_date from BOARD_TBL left join member_tbl on (board_writer = member_no)";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Post p = new Post(rset.getInt("board_no"),rset.getString("board_title"),null,rset.getString("member_name"),rset.getInt("read_count"),rset.getDate("write_date"));
				lst.add(p);
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

	public Post selectPost(Connection conn, int postNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select board_no, board_title, board_content, nvl(member_name,'탈퇴회원') member_name, read_count, write_date from BOARD_TBL left join member_tbl on (board_writer = member_no) where board_no = ?";
		Post p = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, postNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				p = new Post(rset.getInt("board_no"),rset.getString("board_title"), rset.getString("board_content") ,rset.getString("member_name"),rset.getInt("read_count"),rset.getDate("write_date"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return p;
	}

	public int plusCount(Connection conn, int postNo) {
		PreparedStatement pstmt = null;
		String query = "update board_tbl set read_count = (select read_count from board_tbl where board_no = ?)+1 where board_no = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, postNo);
			pstmt.setInt(2, postNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int searchPost(Connection conn, int postNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select nvl(board_writer,-1) board_writer from board_tbl where board_no = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, postNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("board_writer");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updatePost(Connection conn, Post p) {
		PreparedStatement pstmt = null;
		String query = "update board_tbl set board_title = ?, board_content = ? where board_no = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getBoardTitle());
			pstmt.setString(2, p.getBoardContent());
			pstmt.setInt(3, p.getBoardNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deletePost(Connection conn, int postNo) {
		PreparedStatement pstmt = null;
		String query = "delete from board_tbl where board_no = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, postNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Member selectMember(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = new Member();
		String query = "select * from member_tbl where member_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			rset.next();
			m.setMemberNo(rset.getInt("member_no"));
			m.setMemberId(rset.getString("member_id"));
			m.setMemberPw(rset.getString("member_pw"));
			m.setMemberName(rset.getString("member_name"));
			m.setMemberPhone(rset.getString("member_phone"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}

	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		String query = "update member_tbl set member_pw = ?, member_phone = ? where member_no = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberPw());
			pstmt.setString(2, m.getMemberPhone());
			pstmt.setInt(3, m.getMemberNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteMember(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		String query = "delete from member_tbl where member_no = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			
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
