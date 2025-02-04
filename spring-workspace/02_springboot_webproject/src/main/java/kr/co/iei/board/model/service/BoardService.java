package kr.co.iei.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iei.board.model.dao.BoardDao;
import kr.co.iei.board.model.vo.Board;
import kr.co.iei.board.model.vo.BoardFile;
import kr.co.iei.board.model.vo.BoardListData;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;

	public BoardListData selectBoardList(int reqPage) {
		int numPerPage = 10;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		List list = boardDao.selectBoardList(start,end);
		int totalCount = boardDao.selectBoardTotalCount();
		
		int totalPage = totalCount / numPerPage;
		if(totalCount % numPerPage != 0) {
			totalPage++;
		}
		
		int pageNaviSize = 5;
		int pageNo = ((reqPage -1) / pageNaviSize) * pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination circle-style'>";
		
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/board/list?reqPage=" + (pageNo-1) + "'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		
		for(int i=0; i<pageNaviSize; i++) {
			pageNavi += "<li>";
			if(reqPage == pageNo) {
				pageNavi += "<a class='page-item active-page' href='/board/list?reqPage="+pageNo+"'>";
			}else {
				pageNavi += "<a class='page-item' href='/board/list?reqPage="+pageNo+"'>";
			}
			
			pageNavi += pageNo;
			pageNavi += "</a></li>";
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/board/list?reqPage=" + pageNo + "'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		BoardListData bld = new BoardListData(list,pageNavi);
		return bld;
	}

	public int insertBoard(Board b, List<BoardFile> fileList) {
		int boardNo = boardDao.newBoardNo();
		b.setBoardNo(boardNo);
		int result = boardDao.insertBoard(b);
		for(BoardFile boardFile : fileList) {
			boardFile.setBoardNo(boardNo);
			result += boardDao.insertBoardFile(boardFile);
		}
		return result;
	}
}
