package kr.co.iei.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.member.service.MemberService;
import kr.co.iei.member.vo.Member;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping(value="/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping(value="/loginFrm")
	public String loginFrm() {
		return "member/login";
	}
	
	@PostMapping(value="/login")
	public String login(Member m, Model model, HttpSession session) {
		Member member = memberService.selectOneMember(m);
		
		if(member == null) {
			model.addAttribute("title", "로그인 실패");
			model.addAttribute("text", "아이디 비번 확인하삼");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/member/loginFrm");
			return "common/msg";
		}else {
			if(member.getMemberLevel() == 3) {
				model.addAttribute("title", "권한 없음");
				model.addAttribute("text", "관리자에게 문의");
				model.addAttribute("icon", "warning");
				model.addAttribute("loc", "/member/loginFrm");
				return "common/msg";
			}else {
				session.setAttribute("member", member);
				return "redirect:/";
			}
		}
		
		
	}
	
	
	
}
