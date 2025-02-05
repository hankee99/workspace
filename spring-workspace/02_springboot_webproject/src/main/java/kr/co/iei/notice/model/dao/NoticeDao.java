package kr.co.iei.notice.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.notice.model.vo.Notice;
import kr.co.iei.notice.model.vo.NoticeComment;
import kr.co.iei.notice.model.vo.NoticeCommentRowMapper;
import kr.co.iei.notice.model.vo.NoticeFile;
import kr.co.iei.notice.model.vo.NoticeFileRowMapper;
import kr.co.iei.notice.model.vo.NoticeRowMapper;

@Repository
public class NoticeDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private NoticeRowMapper noticeRowMapper;
	@Autowired
	private NoticeFileRowMapper noticeFileRowMapper;
	@Autowired
	private NoticeCommentRowMapper noticeCommentRowMapper;
	
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
		String query = "insert into notice values(?,?,?,?,0,to_char(sysdate,'yyyy-mm-dd'))"; 
		Object[] params = {n.getNoticeNo(),n.getNoticeTitle(),n.getNoticeWriter(),n.getNoticeContent()};
		int result = jdbc.update(query, params);
		return result;
	}

	public int insertNoticeFile(NoticeFile noticeFile) {
		String query = "insert into notice_file values(notice_file_seq.nextval,?,?,?)";
		Object[] params = {noticeFile.getNoticeNo(),noticeFile.getFilename(),noticeFile.getFilepath()};
		int result = jdbc.update(query, params);
		return result;
	}

	public int newNoticeNo() {
		String query = "select notice_seq.nextval from dual";
		int noticeNo = jdbc.queryForObject(query, Integer.class);
		return noticeNo;
	}

	public Notice selectOneNotice(int noticeNo) {
		String query = query = "select * from notice where notice_no = ?";
		Object[] params = {noticeNo};
		List list = jdbc.query(query, noticeRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}else {
			Notice n = (Notice)list.get(0);
			return n;
		}
	}

	public int updateReadCount(int noticeNo) {
		String query = "update notice set read_count = read_count + 1 where notice_no = ?";
		Object[] params = {noticeNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public List selectNoticeFile(int noticeNo) {
		String query = "select * from notice_file where notice_no = ?";
		Object[] params = {noticeNo};
		List list = jdbc.query(query, noticeFileRowMapper, params);
		return list;
	}

	public int deleteNotice(int noticeNo) {
		String query = "delete from notice where notice_no = ?";
		Object[] params = {noticeNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public int updateNotice(Notice n) {
		String query = "update notice set notice_title =? , notice_content =? where notice_no = ?";
		Object[] params = {n.getNoticeTitle(), n.getNoticeContent(), n.getNoticeNo()};
		int result = jdbc.update(query, params);
		return result;
	}

	public NoticeFile selectOneNoticeFile(int noticeFileNo) {
		String query = "select * from notice_file where notice_file_no = ?";
		Object[] params = {noticeFileNo};
		List list = jdbc.query(query, noticeFileRowMapper, params);
		NoticeFile noticeFile = (NoticeFile)list.get(0);
		return noticeFile;
	}

	public int deleteNoticeFile(int noticeFileNo) {
		String query = "delete from notice_file where notice_file_no =?";
		Object[] params = {noticeFileNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public int insertNoticeComment(NoticeComment nc) {
		String query = "insert into notice_comment values(notice_comment_seq.nextval,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),?,?)";
		String noticeCommentRef = nc.getNoticeCommentRef() == 0 ? null : String.valueOf(nc.getNoticeCommentRef());
		Object[] params = {nc.getNoticeCommentWriter(),nc.getNoticeCommentContent(),nc.getNoticeRef(),noticeCommentRef};
		int result = jdbc.update(query, params);
		return result;
	}

	public List selectNoticeCommentList(int noticeNo) {
		String query = "select * from notice_comment where notice_ref = ? and notice_comment_ref is null order by 1";
		Object[] params = {noticeNo};
		List list = jdbc.query(query, noticeCommentRowMapper, params);
		return list;
	}

	public List selectNoticeReCommentList(int noticeNo) {
		String query = "select * from notice_comment where notice_ref = ? and notice_comment_ref is not null order by 1";
		Object[] params = {noticeNo};
		List list = jdbc.query(query, noticeCommentRowMapper, params);
		return list;
	}
}
