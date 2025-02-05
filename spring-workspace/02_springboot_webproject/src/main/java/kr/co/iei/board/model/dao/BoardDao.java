package kr.co.iei.board.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.board.model.vo.Board;
import kr.co.iei.board.model.vo.BoardFile;
import kr.co.iei.board.model.vo.BoardFileRowMapper;
import kr.co.iei.board.model.vo.BoardRowMapper;

@Repository
public class BoardDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private BoardRowMapper boardRowMapper;
	
	@Autowired
	private BoardFileRowMapper boardFileRowMapper;
	
	public List selectBoardList(int start, int end) {
		String query = "select * from (select rownum as rnum, b.* from (select * from board order by board_no desc) b) where rnum between ? and ?";
		Object[] params = {start,end};
		List list = jdbc.query(query, boardRowMapper, params);
		return list;
	}
	public int selectBoardTotalCount() {
		String query = "select count(*) from board";
		int total = jdbc.queryForObject(query, Integer.class);
		return total;
	}
	public int newBoardNo() {
		String query = "select board_seq.nextval from dual";
		int boardNo = jdbc.queryForObject(query, Integer.class);
		return boardNo;
	}
	public int insertBoard(Board b) {
		String query = "insert into board values(?,?,?,?,0,to_char(sysdate,'yyyy-mm-dd'))";
		Object[] params = {b.getBoardNo(),b.getBoardTitle(),b.getBoardWriter(),b.getBoardContent()};
		int result = jdbc.update(query,params);
		return result;
	}
	public int insertBoardFile(BoardFile boardFile) {
		String query = "insert into board_file values(board_seq.nextval,?,?,?)";
		Object[] params = {boardFile.getBoardNo(),boardFile.getFilename(),boardFile.getFilepath()};
		int result = jdbc.update(query,params);
		return result;
	}
	public int updateReadCount(int boardNo) {
		String query = "update board set read_count = read_count + 1 where board_no = ?";
		Object[] params = {boardNo};
		int result = jdbc.update(query,params);
		return result;
	}
	public Board selectOneBoard(int boardNo) {
		String query = "select * from board where board_no = ?";
		Object[] params = {boardNo};
		List list = jdbc.query(query, boardRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}else {
			Board b = (Board)list.get(0);
			return b;
		}
	}
	public List selectBoardFile(int boardNo) {
		String query = "select * from board_file where board_no = ?";
		Object[] params = {boardNo};
		List list = jdbc.query(query, boardFileRowMapper, params);
		return list;
	}
	
}
