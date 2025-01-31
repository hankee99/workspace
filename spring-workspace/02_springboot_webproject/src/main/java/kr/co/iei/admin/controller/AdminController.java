package kr.co.iei.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.member.model.service.MemberService;
import kr.co.iei.member.model.vo.Member;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping(value= "/allMember")
	public String allMember(Model model) {
		List list = memberService.selectAllMember();
		model.addAttribute("list", list);
		return "admin/allMember";
	}
	
	@GetMapping(value="/changeLevel")
	public String changeLevel(Member m, Model model) {
		int result = memberService.changeLevel(m);
		if(result>0) {
			model.addAttribute("title", "등급 변경 완료");
			model.addAttribute("text", "변경이 완료되었습니다.");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/admin/allMember");
		}
		return "common/msg";
	}
	
	@GetMapping(value="/checkedChangeLevel")
	public String checkedChangeLevel(String no, String level) {
		boolean result = memberService.checkedChangeLevel(no,level);
		return "redirect:/admin/allMember";
	}
	
	
	
}
