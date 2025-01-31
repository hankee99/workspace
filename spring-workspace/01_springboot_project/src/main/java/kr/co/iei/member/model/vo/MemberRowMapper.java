package kr.co.iei.member.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component //이 클래스 서버 시작할때 객체 생성해두라고 하는 어노테이션
public class MemberRowMapper implements RowMapper<Member>{

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member();
		member.setEnrollDate(rs.getString("enroll_date"));
		member.setMemberId(rs.getString("member_id"));
		member.setMemberPw(rs.getString("member_pw"));
		member.setMemberNo(rs.getInt("member_no"));
		member.setMemberAddr(rs.getString("member_addr"));
		member.setMemberName(rs.getString("member_name"));
		member.setMemberPhone(rs.getString("member_phone"));
		member.setMemberLevel(rs.getInt("member_level"));
		return member;
	}
	
}
