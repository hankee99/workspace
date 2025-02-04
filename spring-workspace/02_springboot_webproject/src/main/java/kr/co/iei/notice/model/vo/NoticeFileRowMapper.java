package kr.co.iei.notice.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class NoticeFileRowMapper implements RowMapper<NoticeFile>{
	
	@Override
	public NoticeFile mapRow(ResultSet rs, int rowNum) throws SQLException {
		NoticeFile f = new NoticeFile();
		f.setNoticeFileNo(rs.getInt("notice_file_no"));
		f.setNoticeNo(rs.getInt("notice_no"));
		f.setFilename(rs.getString("filename"));
		f.setFilepath(rs.getString("filepath"));
		return f;
	}
	
}
