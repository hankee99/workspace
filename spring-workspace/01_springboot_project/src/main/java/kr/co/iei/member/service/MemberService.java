package kr.co.iei.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.member.model.dao.MemberDao;
import kr.co.iei.member.model.vo.Member;

@Service //이 클래스가 Service클래스임을 표시하면서 객체를 생성하는 어노테이션
public class MemberService {
	@Autowired //Spring이 자동으로 생성해서 가지고 있는 객체 중 일치하는 타입의 객체 주소를 대입
	private MemberDao memberDao;

	public List selectAllMember() {
		List list = memberDao.selectAllMember();
		return list;
	}

	public Member selectOneMember(String memberId) {
		Member m = memberDao.selectOneMember(memberId);
		return m;
	}
	
	@Transactional //에러가 발생하지 않으면 커밋, 에러가 발생하면 롤백
	public int insertMember(Member m) {
		int result = memberDao.insertMember(m);
		return result;
	}
}
