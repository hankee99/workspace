package kr.co.iei.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.board.model.service.BoardService;
import kr.co.iei.board.model.vo.BoardListData;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping(value="/list")
	public String boardList(Model model, int reqPage) {
		BoardListData bld = boardService.selectBoardList(reqPage);
		model.addAttribute("list", bld.getList());
		model.addAttribute("pageNavi", bld.getPageNavi());
		return "board/list";
	}
	
}
