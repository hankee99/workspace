package kr.co.iei.member.model.service;

import java.util.List;
import java.util.stream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.member.model.dao.MemberDao;
import kr.co.iei.member.model.vo.Member;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;

	public Member selectOneMember(Member m) {
		Member member = memberDao.selectOneMember(m);
		return member;
	}
	
	@Transactional
	public int insertMember(Member m) {
		int result = memberDao.insertMember(m);
		return result;
	}

	public Member selectOneMember(String checkId) {
		Member member = memberDao.selectOneMember(checkId);
		return member;
	}
	
	@Transactional
	public int updateMember(Member m) {
		int result = memberDao.updateMember(m);
		return result;
	}
	
	@Transactional
	public int deleteMember(int memberNo) {
		int result = memberDao.deleteMember(memberNo);
		return result;
	}

	public List selectAllMember() {
		List list = memberDao.selectAllMember();
		return list;
	}
	
	@Transactional
	public int changeLevel(Member m) {
		int result = memberDao.changeLevel(m);
		return result;
	}
	
	public boolean checkedChangeLevel(String no, String level) {
		String[] noArr = no.split("/");
		String[] levelArr = level.split("/");
		boolean flag = true;
		for(int i=0; i<noArr.length; i++) {
			Member m = new Member();
			int memberNo = Integer.parseInt(noArr[i]);
			m.setMemberNo(memberNo);
			int memberLevel = Integer.parseInt(levelArr[i]);
			m.setMemberLevel(memberLevel);
			int result = memberDao.changeLevel(m);
			if(result == 0) {
				flag = false;
				break;
			}
		}
		
		return flag;
	}
}
