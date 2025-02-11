package kr.co.iei.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.member.model.service.MemberService;
import kr.co.iei.member.model.vo.Member;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;




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
		System.out.println(member);
		//로그인결과
		//1. 아이디 or 비번 잘못된경우 -> member == null
		//2. 아이디 and 비번은 정상이지만 -> 등급이 준회원인 경우 -> 조회결과의 memberLevel == 3
		//3. 아이디 and 비번이 정상이고 등급이 정회원이나 관리자인 경우 -> memberLevel == 1,2
		if(member == null) {
			//1. 아이디 or 비번 잘못된경우 -> member == null
			//아이디 비번이 잘못입력됐다는 메시지 -> 로그인페이지로 이동
			model.addAttribute("title", "로그인 실패");
			model.addAttribute("text", "아이디 또는 비밀번호를 확인하세요.");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/member/loginFrm");
			return "common/msg";
		}else {
			if(member.getMemberLevel() == 3) {
				//2. 아이디 and 비번은 정상이지만 -> 등급이 준회원인 경우 -> 조회결과의 memberLevel == 3
				//권한이 없다. 관리자에게 문의해라 -> 메인페이지로 이동
				model.addAttribute("title", "로그인 권한 없음");
				model.addAttribute("text", "관리자에게 문의하세요.");
				model.addAttribute("icon", "warning");
				model.addAttribute("loc", "/");
				return "common/msg";
			}else {
				//3. 아이디 and 비번이 정상이고 등급이 정회원이나 관리자인 경우 -> memberLevel == 1,2
				//로그인 -> 아이디/비번으로 신원인증
				//	    -> 해당 웹사이트를 이용할때 내 정보를 지속적으로 사용하기 위해서
				//		-> 로그인한 회원의 정보를 페이지를 사용하는동안 유지
				//		-> 이때 model을 이용해서 등록하면 해당요청이 끝나면 그 정보를 유지하지 않음 
				//		-> 해당 페이지를 이용하는동안 데이터 저장을 유지하려면 session객체에 저장
				//		-> 세션에 저장된 데이터가 사라지는 경우
				//		-> 1) 지정된 시간이 지나면 사라짐
				//		-> 2) 브라우저를 종료했을때
				//		-> 3) 로그아웃을 했을때
				//		-> 4) 서버 재시작되면
				//메인페이지로 이동
				session.setAttribute("member", member);
				return "redirect:/";
			}
		}
		
	}
	
	@GetMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate(); //현재 세션 정보 파기
		return "redirect:/";
	}
	
	@GetMapping(value="/joinFrm")
	public String joinFrm() {
		return "member/joinFrm";
	}
	
	@PostMapping(value="/join")
	public String join(Member m, Model model) {
		int result = memberService.insertMember(m);
		model.addAttribute("title", "회원가입 완료");
		model.addAttribute("text", "이랏샤이마세");
		model.addAttribute("icon", "success");
		model.addAttribute("loc", "/member/loginFrm");
		return "common/msg";
	}
	
	@GetMapping(value = "/checkId")
	public String checkId(String checkId, Model model) {
		Member member = memberService.selectOneMember(checkId);
		if(member == null) {
			//입력받은 아이디로 회원조회한 결과가 없는경우 -> 사용가능
			model.addAttribute("result", 0);
			
		}else {
			//입력받은 아이디로 회원조회한 결과가 있는경우 -> 중복아이디
			model.addAttribute("result", 1);
		}
		model.addAttribute("memberId", checkId);
		return "member/checkId";
	}
	
	//로그인한 회원 정보는 session에 member라는 키값으로 저장돼있음 -> 추가조회없이 세션에 있는 정보 바로 사용
	@GetMapping(value="/mypage")
	public String mypage() {
		return "member/mypage";
	}
	
	@PostMapping(value="/update")
	public String update(Member m, @SessionAttribute Member member) {
		//매개변수로 설정한 m에는 화면에서 전송해준 데이터만 들어있음(비밀번호,전화번호,주소)
		//update쿼리를 수행하려면 회원번호나 아이디를 추가로 가져와야함 -> session에서 꺼냄
		//-> session에 저장된 형태의 변수를 선언, 변수이름은 session에 setAttribute할때 저장한 키값
		//세션에 저장돼있는 정보중에 회원번호를 수정정보에 세팅
		int memberNo = member.getMemberNo();
		m.setMemberNo(memberNo);
		int result = memberService.updateMember(m);
		if(result>0) {
//			member.setMemberPw(m.getMemberPw());
//			member.setMemberPhone(m.getMemberPhone());
//			member.setMemberAddr(m.getMemberAddr());
			return "redirect:/member/mypage2";
		}else {
			return "redirect:/";
		}
	}
	
	
	@GetMapping(value="/mypage2")
	public String mypage2(@SessionAttribute Member member, Model model) {
		//마이페이지 요청 시 회원정보를 조회해서 화면으로 보내는 방법
		String memberId = member.getMemberId();
		Member m = memberService.selectOneMember(memberId);
		model.addAttribute("member", m);
		return "member/mypage2";
	}
	
	@GetMapping(value = "/delete")
	public String delete(@SessionAttribute Member member, Model model) {
		int memberNo = member.getMemberNo();
		int result = memberService.deleteMember(memberNo);
		model.addAttribute("title", "탈퇴 완료");
		model.addAttribute("text", "잘가시게...");
		model.addAttribute("icon", "success");
		model.addAttribute("loc", "/member/logout");
		return "common/msg";
	}
	
	@ResponseBody
	@GetMapping(value= "/ajaxCheckId")
	public boolean ajaxCheckId(String memberId) {
		Member m = memberService.selectOneMember(memberId);
		return m == null;
	}
	
	@RequestMapping(value="/loginMsg")
	public String loginMsg(Model model) {
		model.addAttribute("title", "로그인 확인");
		model.addAttribute("text", "로그인 후 이용 가능합니다");
		model.addAttribute("icon", "info");
		model.addAttribute("loc", "/member/loginFrm");
		return "common/msg";
	}
	
	@RequestMapping(value="/adminMsg")
	public String adminMsg(Model model) {
		model.addAttribute("title", "권한 없음");
		model.addAttribute("text", "관리자만 이용 가능합니다");
		model.addAttribute("icon", "warning");
		model.addAttribute("loc", "/member/loginFrm");
		return "common/msg";
	}
	
	
	
	
	
}
