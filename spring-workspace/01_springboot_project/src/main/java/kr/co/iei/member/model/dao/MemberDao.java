package kr.co.iei.member.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.member.model.vo.Member;
import kr.co.iei.member.model.vo.MemberRowMapper;

@Repository //이 클래스가 DAO클래스임을 표시하면서 Spring에게 객체를 생성하라고 하는 어노테이션
public class MemberDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private MemberRowMapper memberRowMapper;
	
	public List selectAllMember() {
		//1. query문 작성(PSTMT방식)
		String query = "select * from member_tbl order by 1";
		//2. 쿼리에 위치홀더가 있으면(위치홀더에 들어갈 값을 Object[]형태로 순서대로 대입)
		//3. jdbc객체의 메소드로 쿼리문 수행(조회 : query() / 삽입,수정,삭제 : update() )
		//query(para1,para2)
		//query(para1,para2,para3)
		//query()메소드는 조회결과의 row수와 상관없이 무조건 List타입으로 리턴
		//para1 : 수행할 쿼리문
		//para2 : select 결과를 처리할 rowmapper 객체(rset에서 꺼낸 데이터를 자바형식으로 반환하는 객체)
		//para3 : 위치홀더에 들어갈 데이터가 순서대로 저장되어있는 Object[] (2번과정에서 생성한 배열)
		List list = jdbc.query(query, memberRowMapper);
		return list;
	}

	public Member selectOneMember(String memberId) {
		String query = "select * from member_tbl where member_id = ?";
		Object[] params = {memberId};
		List list = jdbc.query(query, memberRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}else {
			Member m = (Member)list.get(0);
			return m;
		}
		
	}

	public int insertMember(Member m) {
		String query = "insert into member_tbl values(member_seq.nextval,?,?,?,?,?,3,to_char(sysdate,'yyyy-mm-dd'))";
		Object[] params = {m.getMemberId(),m.getMemberPw(),m.getMemberName(),m.getMemberPhone(),m.getMemberAddr()};
		//insert,update,delete 쿼리문은 update()사용
		//update(para1)
		//update(para1,para2)
		//para1 : 문자열로 되어있는 쿼리
		//para2 : 위치홀더가 있으면 위치홀더에 들어갈 값을 순서대로 넣은 Object[]
		int result = jdbc.update(query, params);
		return result;
	}

}
