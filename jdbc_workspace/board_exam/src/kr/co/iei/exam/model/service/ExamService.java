package kr.co.iei.exam.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.iei.common.JDBCTemplate;
import kr.co.iei.exam.model.dao.ExamDao;
import kr.co.iei.exam.model.vo.Board;
import kr.co.iei.exam.model.vo.Member;
import kr.co.iei.exam.model.vo.Post;

public class ExamService {
	ExamDao examDao;

	public ExamService() {
		super();
		examDao = new ExamDao();
	}

	public int register(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = examDao.register(conn,m);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public String searchId(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		String memberId = examDao.searchId(conn,m);
		JDBCTemplate.close(conn);
		return memberId;
	}

	public int login(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int memberNo = examDao.login(conn,m);
		JDBCTemplate.close(conn);
		return memberNo;
	}

	public int registPost(Board board) {
		Connection conn = JDBCTemplate.getConnection();
		int result = examDao.registPost(conn,board);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Post> postList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Post> lst = examDao.postList(conn);
		return lst;
	}

	public Post selectPost(int postNo) {
		Connection conn = JDBCTemplate.getConnection();
		Post p = null;
		int result = examDao.plusCount(conn,postNo);
		if(result >0) {
			p = examDao.selectPost(conn,postNo);
		}
		
		if(p != null) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return p;
	}

	public int searchPost(int postNo) {
		Connection conn = JDBCTemplate.getConnection();
		int userNo = examDao.searchPost(conn, postNo);
		JDBCTemplate.close(conn);
		return userNo;
	}

	public int updatePost(Post p) {
		Connection conn = JDBCTemplate.getConnection();
		int result = examDao.updatePost(conn,p);
		if(result >0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deletePost(int postNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = examDao.deletePost(conn, postNo);
		if(result >0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Member selectMember(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = examDao.selectMember(conn,memberNo);
		JDBCTemplate.close(conn);
		return m;
	}

	public int updateMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = examDao.updateMember(conn,m);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteMember(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = examDao.deleteMember(conn,memberNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	

}
