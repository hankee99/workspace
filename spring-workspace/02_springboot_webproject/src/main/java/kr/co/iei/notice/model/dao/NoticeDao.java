package kr.co.iei.notice.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.notice.model.vo.Notice;
import kr.co.iei.notice.model.vo.NoticeFileRowMapper;
import kr.co.iei.notice.model.vo.NoticeRowMapper;

@Repository
public class NoticeDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private NoticeRowMapper noticeRowMapper;
	@Autowired
	private NoticeFileRowMapper FileRowMapper;
	
	public List selectNoticeList(int startNum, int endNum) {
		String query = """
				select *
				from (
					select rownum as rnum, n.*
					from (
						select *
						from notice
						order by notice_no desc
						) n
				)
				where rnum between ? and ?
				""";
		Object[] params = {startNum, endNum};
		List list = jdbc.query(query, noticeRowMapper, params);
		return list;
	}
	
	public int selectNoticeTotalCount() {
		String query = "select count(*) from notice";
		//조회 결과가 단일값(1셀)인 경우 사용하는 메소드
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}

	public int insertNotice(Notice n) {
		String query = "insert into notice values(notice_seq.nextval,?,?,?,0,to_char(sysdate,'yyyy-mm-dd'))"; 
		Object[] params = {n.getNoticeTitle(),n.getNoticeWriter(),n.getNoticeContent()};
		int result = jdbc.update(query, params);
		return result;
	}
}
