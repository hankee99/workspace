package kr.co.iei.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.notice.model.service.NoticeService;
import kr.co.iei.notice.model.vo.Notice;
import kr.co.iei.notice.model.vo.NoticeListData;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping(value="/notice")
public class NoticeController {
	@Autowired
	private NoticeService serv;
	
	@GetMapping(value="/list")
	public String noticeList(Model mod, int reqPage) {
		NoticeListData nld = serv.selectNoticeList(reqPage);
		mod.addAttribute("list", nld.getList());
		mod.addAttribute("pageNavi", nld.getPageNavi());
		return "notice/list";
	}
	
	@GetMapping(value="/writeFrm")
	public String noticeWriteFrm() {
		return "notice/writeFrm";
	}
	
	@PostMapping(value="/write")
	public String noticeWrite(Notice n, Model model) {
		int result = serv.insertNotice(n);
		model.addAttribute("title", "작성완료");
		model.addAttribute("text", "공지사항이 등록되었습니다.");
		model.addAttribute("icon", "success");
		model.addAttribute("loc", "/notice/list?reqPage=1");
		return "common/msg";
	}
	
}
