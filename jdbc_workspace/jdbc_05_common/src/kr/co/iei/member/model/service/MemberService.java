package kr.co.iei.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.iei.common.JDBCTemplate;
import kr.co.iei.member.model.dao.MemberDao;
import kr.co.iei.member.model.vo.Member;

public class MemberService {
	MemberDao memberDao;
	public MemberService() {
		super();
		memberDao = new MemberDao();
	}

	public Member selectMemberId(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = memberDao.selectMemberId(conn,memberId);
		JDBCTemplate.close(conn);
		return m;
	}

	public ArrayList<Member> selectAllMember() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList lst = memberDao.selectAllMember(conn);
		JDBCTemplate.close(conn);
		return lst;
	}

	public ArrayList<Member> selectMemberName(String memberName) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> lst = memberDao.selectMemberName(conn,memberName);
		return lst;
	}

	public int updateMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		int result = memberDao.updateMember(conn,member);
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = memberDao.insertMember(conn,m);
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteMember(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = memberDao.selectMemberId(conn, memberId);
		int result = 0;
		if(m != null) {//일치하는 회원 있을때
			result = memberDao.deleteMember(conn,memberId);
			if(result >0) {//삭제에 성공했을때
				result = memberDao.insertDelMember(conn,m);
				if(result >0) {
					JDBCTemplate.commit(conn);
				}else {
					JDBCTemplate.rollback(conn);
				}
			}else {//삭제에 실패했을때
				JDBCTemplate.rollback(conn);
				result = -1;
			}
		}else { //일치하는 회원 없을때
			JDBCTemplate.rollback(conn);
			result = -2;
		}
		
		JDBCTemplate.close(conn);
		return result;
	}
	

}
