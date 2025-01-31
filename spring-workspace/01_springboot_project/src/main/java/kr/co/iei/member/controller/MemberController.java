package kr.co.iei.member.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.member.model.vo.Member;
import kr.co.iei.member.service.MemberService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping(value = "/allMember")
	public String allMember(Model model) {
		//비즈니스 로직
		List list = memberService.selectAllMember();
		model.addAttribute("list", list);
		return "member/allMember";
	}
	
	@GetMapping(value = "/searchIdFrm")
	public String searchIdFrm() {
		return "member/searchIdFrm";
	}
	
	@GetMapping(value = "/searchId")
	public String searchId(String memberId, Model model) {
		Member m = memberService.selectOneMember(memberId);
//		if(m == null) {
//			return "member/searchFail";
//		}else {
//			model.addAttribute("m", m);
//			return "member/searchId";
//		}
		model.addAttribute("m", m);
		return "member/searchResult";
		
	}
	
	@GetMapping(value = "/joinFrm")
	public String joinFrm() {
		return "member/joinFrm";
	}
	
	@PostMapping(value = "/join")
	public String join(Member m) {
		int result = memberService.insertMember(m);
		//만약 처리결과를 html페이지가 아니라 다른 컨트롤러를 요청하고싶으면(response.sendRedirect(컨트롤주소))
		//-> return "redirect:컨트롤러주소"
		return "redirect:/member/allMember";
	}
}
