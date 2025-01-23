package kr.co.iei.member.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.iei.common.JDBCTemplate;
import kr.co.iei.member.dao.MemberDao;
import kr.co.iei.member.vo.Member;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService() {
		super();
		memberDao = new MemberDao();
	}

	public ArrayList<Member> selectAllMember() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> list = memberDao.selectAllMember(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = memberDao.insertMember(conn,m);
		JDBCTemplate.close(conn);
		return result;
	}

	public Member selectOneMember(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = memberDao.selectOneMember(conn,memberId);
		JDBCTemplate.close(conn);
		return m;
	}
	
	public ArrayList<Member> selectMemberName(String memberName) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> list = memberDao.selectMemberName(conn,memberName);
		JDBCTemplate.close(conn);
		return list;
	}
	
	public Member selectMemberId(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = memberDao.selectMemberId(conn,memberId);
		JDBCTemplate.close(conn);
		
		return m;
	}

}
