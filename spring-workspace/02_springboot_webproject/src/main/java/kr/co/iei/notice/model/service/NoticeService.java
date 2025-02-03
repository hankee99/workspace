package kr.co.iei.notice.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.notice.model.dao.NoticeDao;
import kr.co.iei.notice.model.vo.Notice;
import kr.co.iei.notice.model.vo.NoticeListData;

@Service
public class NoticeService {
	@Autowired
	private NoticeDao dao;

	public NoticeListData selectNoticeList(int reqPage) {
		// reqPage : 사용자가 요청한 페이지 번호
		// 한 페이지에 보여줄 게시물 수(지정) : 10개
		int numPerPage = 10;
		// 쿼리문은 변경되지 않고 조회의 시작값과 끝값만 변경(start, end)
		// 사용자가 요청한 페이지에 따라서 게시물의 시작번호와 끝번호가 변경 -> 계산
		// reqPage == 1 -> start = 1 / end = 10
		// reqPage == 2 -> start = 11 / end = 20
		// reqPage == 3 -> start = 21 / end = 30
		// reqPage == 4 -> start = 31 / end = 40
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		// 해당 요청 페이지에 게시물을 조회
		List list = dao.selectNoticeList(start, end);
		// 페이지 네비게이션(사용자가 클릭해서 다른페이지를 요청할 수 있도록하는 요소)
		// 페이지 네비게이션을 Service에서 만드는 이유 -> 총게시물수, reqPage같은 데이터를 이용해서 만들어야하기 때문에
		// 전체페이지 수를 계산 -> 총 게시물 수, 페이지당 게시물 수를 이용해서 연산
		// 총 게시물 수 조회
		// select count(*) from notice
		int totalCount = dao.selectNoticeTotalCount();
		/*
		 int totalPage = 0;
		 if(totalCount%numPerPage == 0) {
		 	totalPage =	totalCount/numPerPage;
		 }else {
		 	totalPage = totalCount/numPerPage + 1;
		 }
		*/
		int totalPage = totalCount / numPerPage;
		if (totalCount % numPerPage != 0) {
			totalPage += 1;
		}
		
		// 페이지네비게이션 제작 시작
		// 페이지네비 길이 지정
		int pageNaviSize = 5;
		// 페이지네비의 시작번호를 지정
		// reqPage 1~5 : 1 2 3 4 5 (0 ~ 4)/5 -> 0 * 5 -> 0
		// reqPage 6~10 : 6 7 8 9 10 (5 ~ 9)/5 -> 1 * 5 -> 5
		// reqPage 1~5 : 11 12 13 14 15 (10 ~ 14)/5 -> 2 * 5 -> 10
		// ...
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
		// 페이지네비 html생성
		String pageNavi = "<ul class='pagination circle-style'>";
		// 이전버튼(1페이지로 시작하는게 아닌 경우에만 이전버튼 생성)
		if (pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/notice/list?reqPage=" + (pageNo - 1) + "'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		//페이지이동
		for(int i=0;i<pageNaviSize;i++) {
			pageNavi += "<li>";
			if(pageNo == reqPage) {
				pageNavi += "<a class='page-item active-page' href='/notice/list?reqPage="+pageNo+"'>";
			}else {
				pageNavi += "<a class='page-item' href='/notice/list?reqPage="+pageNo+"'>";
				
			}
			pageNavi += pageNo;
			pageNavi += "</a></li>";
			pageNo++;
			//페이징을 만들다가 최종페이지를 출력했으면 더이상 반복하지 않고 반복문을 종료
			if(pageNo > totalPage) {
				break;
			}
		}
		//다음버튼(최종페이지를 출력하지 않은경우)
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/notice/list?reqPage=" + pageNo + "'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		//service가 가지고있는 것중에 되돌려줘야 할것 -> 페이지네비, list
		//java의 메소드는 1개의 자료형만 리턴이 가능 -> 2개를 되돌려줘야 함 String, List
		//List와 문자열을 속성으로 가지고있는 객체 생성
		NoticeListData nld = new NoticeListData(list, pageNavi);
		return nld;
	}
	
	@Transactional
	public int insertNotice(Notice n) {
		int result = dao.insertNotice(n);
		return result;
	}
}
