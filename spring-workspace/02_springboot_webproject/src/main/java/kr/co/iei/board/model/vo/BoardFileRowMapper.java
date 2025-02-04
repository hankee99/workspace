package kr.co.iei.board.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component
public class BoardFileRowMapper implements RowMapper<BoardFile>{

	@Override
	public BoardFile mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardFile f = new BoardFile();
		f.setBoardFileNo(rs.getInt("board_file_no"));
		f.setBoardNo(rs.getInt("board_no"));
		f.setFilename(rs.getString("filename"));
		f.setFilepath(rs.getString("filepath"));
		return f;
	}
	
}
