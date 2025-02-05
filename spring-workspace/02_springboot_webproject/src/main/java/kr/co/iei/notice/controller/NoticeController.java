package kr.co.iei.notice.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.notice.model.service.NoticeService;
import kr.co.iei.notice.model.vo.Notice;
import kr.co.iei.notice.model.vo.NoticeComment;
import kr.co.iei.notice.model.vo.NoticeFile;
import kr.co.iei.notice.model.vo.NoticeListData;
import kr.co.iei.util.FileUtils;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping(value="/notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@Value("${file.root}")
	private String root; //application.properties에 설정되어있는 file.root의 값을 가지고와서 문자열로 저장
	
	@Autowired
	private FileUtils fileUtils;
	
	@GetMapping(value="/list")
	public String noticeList(Model mod, int reqPage) {
		NoticeListData nld = noticeService.selectNoticeList(reqPage);
		mod.addAttribute("list", nld.getList());
		mod.addAttribute("pageNavi", nld.getPageNavi());
		return "notice/list";
	}
	
	@GetMapping(value="/writeFrm")
	public String noticeWriteFrm() {
		return "notice/writeFrm";
	}
	
	@PostMapping(value="/write")
	public String noticeWrite(Notice n, MultipartFile[] upfile ,Model model) {
		System.out.println(n);
		System.out.println(upfile.length);
		
//		input type=text에 아무것도 입력하지 않고 submit하면 null이 아니라 빈문자열""
//		input type=file에 아무것도 입력하지 않고 submit하면 배열의 길이는 0이 아니라 1, 첫번째 파일이 비어있음
		
		//첨부파일은 서버에 업로드를 진행, 업로드된 결과(업로드한 파일명)을 db에 저장
		//notice_file 테이블에 insert하기위한 데이터 생성
		List<NoticeFile> fileList = new ArrayList<NoticeFile>();
		//첨부파일이 없으면 upfile배열의 첫번째 객체가 비어있음
		if(!upfile[0].isEmpty()) {
			String savepath = root + "/notice/";
			for(MultipartFile file : upfile) {
				//사용자가 업로드한 파일의 원본이름
				String filename = file.getOriginalFilename();
				//파일 업로드할 위치와 파일객체를 주면서 업로드 요청 -> 파일명이 겹쳤을때 중복처리를 해서 업로드 요청
				//중복처리가 끝난 파일이름을 리턴
				String filepath = fileUtils.upload(savepath,file);
				System.out.println(filename);
				System.out.println(filepath);
				NoticeFile noticeFile = new NoticeFile();
				noticeFile.setFilename(filename);
				noticeFile.setFilepath(filepath);
				fileList.add(noticeFile);
			}
		}
		
		
		
		int result = noticeService.insertNotice(n,fileList);
		model.addAttribute("title", "작성완료");
		model.addAttribute("text", "공지사항이 등록되었습니다.");
		model.addAttribute("icon", "success");
		model.addAttribute("loc", "/notice/list?reqPage=1");
		return "common/msg";
	}
	
	@GetMapping(value="/view")
	public String selectOneNotice(int noticeNo,String check, Model model) {
		Notice n = noticeService.selectOneNotice(noticeNo,check);
		if(n == null) {
			model.addAttribute("title", "게시글 조회실패");
			model.addAttribute("text", "존재하지 않는 게시물입니다.");
			model.addAttribute("icon", "warning");
			model.addAttribute("loc", "/notice/list?reqPage=1");
			return "common/msg";
		}else {
			model.addAttribute("n", n);
			return "notice/view";
		}
	}
	
	@GetMapping(value="/filedown")
	public void filedown(NoticeFile noticeFile, HttpServletResponse response) {
		String savepath = root + "/notice/";
		//downloadFile 메소드에 전달하는 데이터
		//1. 다운로드할 파일이 저장되어있는 폴더경로
		//2. 다운로드할 원본 파일이름
		//3. 폴더에 저장되어있는 실제 파일이름
		//4. 응답객체(파일다운로드를 담당하는 객체)
		fileUtils.downloadFile(savepath,noticeFile.getFilename(),noticeFile.getFilepath(),response);
	}
	
	@GetMapping(value="/delete")
	public String deleteNotice(int noticeNo, Model model) {
		List<NoticeFile> list =  noticeService.deleteNotice(noticeNo);
		String savepath = root + "/notice/";
		for(NoticeFile file : list) {
			File delFile = new File(savepath+file.getFilepath());
			delFile.delete();
		}
		
		model.addAttribute("title", "게시글 삭제완료");
		model.addAttribute("text", "게시글이 삭제되었습니다.");
		model.addAttribute("icon", "success");
		model.addAttribute("loc", "/notice/list?reqPage=1");
		
		return "common/msg";
	}
	
	@GetMapping(value="/updateFrm")
	public String updateFrm(int noticeNo, Model model) {
		Notice n = noticeService.selectOneNotice(noticeNo, "1");
		model.addAttribute("n",n);
		return "notice/updateFrm";
	}
	
	@PostMapping(value="/update")
	public String update(Notice n, MultipartFile[] upfile, int[] delFileNo) {
		//새로 추가한 파일을 업로드
		List<NoticeFile> fileList = new ArrayList<NoticeFile>();
		String savepath = root + "/notice/";
		if(!upfile[0].isEmpty()) {
			for(MultipartFile file : upfile) {
				String filename = file.getOriginalFilename();
				String filepath = fileUtils.upload(savepath, file);
				NoticeFile noticeFile = new NoticeFile();
				noticeFile.setFilename(filename);
				noticeFile.setFilepath(filepath);
				noticeFile.setNoticeNo(n.getNoticeNo());
				fileList.add(noticeFile);
			}
		}
		//수정 요청하면서 데이터 3개 전달(n : notice테이블 수정, fileList : notice_file insert, delFileNo : notice_file테이블에서 데이터를 삭제)
		List<NoticeFile> delFileList= noticeService.updateNotice(n,fileList,delFileNo);
		for(NoticeFile noticeFile : delFileList) {
			File delFile = new File(savepath + noticeFile.getFilepath());
			delFile.delete();
		}
		return "redirect:/notice/view?noticeNo=" + n.getNoticeNo() + "&check=1";
	}
	
	@PostMapping(value="/insertComment")
	public String insertComment(NoticeComment nc) {
		int result = noticeService.insertNoticeComment(nc);
		return "redirect:/notice/view?noticeNo=" + nc.getNoticeRef() + "&check=1";
	}
	
	
	
	
}
