package kr.co.iei.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.board.model.service.BoardService;
import kr.co.iei.board.model.vo.Board;
import kr.co.iei.board.model.vo.BoardFile;
import kr.co.iei.board.model.vo.BoardListData;
import kr.co.iei.util.FileUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping(value = "/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Value("${file.root}")
	private String root;
	
	@Autowired
	private FileUtils fileUtils;
	
	
	@GetMapping(value="/list")
	public String boardList(Model model, int reqPage) {
		BoardListData bld = boardService.selectBoardList(reqPage);
		model.addAttribute("list", bld.getList());
		model.addAttribute("pageNavi", bld.getPageNavi());
		return "board/list";
	}
	
	@GetMapping(value="/writeFrm")
	public String boardWriteFrm() {
		return "board/writeFrm";
	}
	
	@PostMapping(value="/write")
	public String boardWrite(Board b, MultipartFile[] upfile, Model model) {
		List<BoardFile> fileList = new ArrayList<BoardFile>();
		if(!upfile[0].isEmpty()) {
			String savepath = root + "/board/";
			for(MultipartFile file : upfile) {
				String fimename = file.getOriginalFilename();
				String filepath = fileUtils.upload(savepath, file);
				BoardFile boardFile = new BoardFile();
				boardFile.setFilename(fimename);
				boardFile.setFilepath(filepath);
				fileList.add(boardFile);
			}
		}
		
		int result = boardService.insertBoard(b,fileList);
		
		model.addAttribute("title", "작성완료");
		model.addAttribute("text", "게시물이 등록되었습니다.");
		model.addAttribute("icon", "success");
		model.addAttribute("loc", "/board/list?reqPage=1");
		return "common/msg";
	}
	
	@GetMapping(value="/view")
	public String selectOneBoard(int boardNo, String check, Model model) {
		Board b = boardService.selectOneBoard(boardNo,check);
		
		if(b == null) {
			model.addAttribute("title", "조회 실패");
			model.addAttribute("text", "존재하지 않는 게시물입니다.");
			model.addAttribute("icon", "warning");
			model.addAttribute("loc", "/board/list?reqPage=1");
			return "common/msg";
		}else {
			model.addAttribute("b", b);
			return "board/view";
		}
	}
	
	
	
}
