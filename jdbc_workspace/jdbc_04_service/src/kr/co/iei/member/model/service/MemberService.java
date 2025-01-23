package kr.co.iei.member.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ArrayList;
import kr.co.iei.member.model.dao.MemberDao;
import kr.co.iei.member.model.vo.Member;

//컨트롤러로 부터 요청을 받아서 dao를 이용해서 작업을하는 계층
//db접속하는 커넥션 관리 및 트랜잭션관리를 담당
public class MemberService {
	MemberDao memberDao;
	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
	String username = "jdbc_user"; // DB 사용자 이름
	String password = "1234"; // DB 비밀번호
	String className = "oracle.jdbc.driver.OracleDriver";
	public MemberService() {
		super();
		// TODO Auto-generated constructor stub
		memberDao = new MemberDao();
	}

	public Member selectMemberId(String memberId) {
		Connection conn = null;
		Member m = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			
			m = memberDao.selectMemberId(conn,memberId);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return m;
	}

	public ArrayList<Member> selectMemberName(String memberName) {
		Connection conn = null;
		ArrayList<Member> lst = null;
		
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			
			lst = memberDao.selectMemberName(conn,memberName);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return lst;
	}

	public ArrayList<Member> selectAllMember() {
		Connection conn = null;
		ArrayList<Member> lst = null;
		
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			
			lst = memberDao.selectAllMember(conn);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lst;
	}

	public int insertMember(Member m) {
		Connection conn = null;
		int result = 0;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			conn.setAutoCommit(false);
			result = memberDao.insertMember(conn,m);
			if(result >0) {
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
		int result = -1;
		
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			conn.setAutoCommit(false);
			Member m = memberDao.selectMemberId(conn, memberId);
			if(m != null) {
				result = memberDao.deleteMember(conn,memberId);
				if(result >0) {
					result = memberDao.insertDelMember(conn, m);
					if(result > 0) {
						conn.commit();
					}else {
						conn.rollback();
					}
				}else {
					conn.rollback();
					result = -2;
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	

	public int updateMember(String memberId, String memberPw, String memberPhone, String memberGender) {
		Connection conn = null;
		int result = 0;
		
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			conn.setAutoCommit(false);
			result = memberDao.updateMember(conn,memberId,memberPw,memberPhone,memberGender);
			if(result > 0) {
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
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	
	
	
}
