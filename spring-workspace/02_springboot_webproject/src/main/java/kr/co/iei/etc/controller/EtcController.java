package kr.co.iei.etc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import kr.co.iei.member.model.service.MemberService;
import kr.co.iei.member.model.vo.Member;


@Controller
@RequestMapping(value="/etc")
public class EtcController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping(value="/ajax")
	public String ajax() {
		return "etc/ajax";
	}
	
	//비동기요청을 처리하는 Controller는 최종적으로 HTML페이지를 되돌려주지 않음
	//비동기 요청 Controller는 되돌려주고싶은 데이터를 리턴타입으로 작성
	//리턴을 그냥 작성하면 ViewResolver가 관여 -> HTML페이지를 찾아갈 수 있도록
	//-> 이 컨트롤러는 비동기요청을 처리하는 컨트롤러기때문에 여기서 리턴하는걸 그대로 클라이언트에게 전달
	@ResponseBody
	@GetMapping(value="/ajax1")
	public void ajax1() {
		System.out.println("서버 호출");
	}
	
	@ResponseBody
	@GetMapping(value="/ajax2")
	public String ajax2() {
		System.out.println("서버 호출 완");
		return "fuck";
	}
	
	@ResponseBody
	@GetMapping(value="/ajax3")
	public int ajax3(int su1, int num2) {
		return su1+num2;
	}
	
	@ResponseBody
	@GetMapping(value="/ajax4")
	public String ajax4(String memberId) {
		Member m = memberService.selectOneMember(memberId);
		if(m != null) {
//			JsonObject obj = new JsonObject(); //HashMap<String,Object> 형태의 객체
//			//회원번호,아이디,이름,전화번호,주소
//			obj.addProperty("memberNo", m.getMemberNo());
//			obj.addProperty("memberId", m.getMemberId());
//			obj.addProperty("memberName", m.getMemberName());
//			obj.addProperty("memberPhone", m.getMemberPhone());
//			obj.addProperty("memberAddr", m.getMemberAddr());
//			return obj.toString();
			return new Gson().toJson(m);
		}else {
			return null;
		}
	}
	
	@ResponseBody
	@PostMapping(value="/ajax5")
	public String ajax5() {
		List<Member> list = memberService.selectAllMember();
		//자바의 리스트/배열 데이터를 자바스크립트 배열형태로 변환
//		JsonArray resultArray = new JsonArray(); //JSON을 여러개 저장하는 배열같은 객체
//		for(Member m : list) {
//			JsonObject obj = new JsonObject();
//			obj.addProperty("memberNo", m.getMemberNo());
//			obj.addProperty("memberId", m.getMemberId());
//			obj.addProperty("memberName", m.getMemberName());
//			obj.addProperty("memberPhone", m.getMemberPhone());
//			obj.addProperty("memberAddr", m.getMemberAddr());
//			
//			resultArray.add(obj);
//		}
//		System.out.println(resultArray.toString());
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@GetMapping(value="/ajax6")
	public Member ajax6(String memberId) {
		Member m = memberService.selectOneMember(memberId);
		//spring boot에서는 되돌려주고싶은 자바 자료형을 그대로 리턴
		//json타입으로 결과를 되돌려주는 개발형식이 많아서
		//기본적으로 jackson-data-bind 라이브러리 추가
		//Controller에서 비동기요청으로 데이터를 리턴하면 -> JSON 형식으로 자동으로 변환해서 리턴
		return m;
	}
	
	@ResponseBody
	@PostMapping(value="/ajax7")
	public List<Member> ajax7() {
		List<Member> list = memberService.selectAllMember();
		return list;
	}
	
	
}
