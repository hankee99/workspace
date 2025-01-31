package kr.co.iei;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class TestController {
/*
	@RequestMapping : get/post 상관없이 요청 처리할때 사용
	@GetMapping : get 요청 처리할때
	@PostMapping : post 요청 처리할때
 */
	
	@GetMapping(value = "/request")
	public String request() {
		System.out.println("요청 테스트");
		
		return "test/test1";
	}
	
	@GetMapping(value="/request2")
	public String request2(String str, int no) {
		
		System.out.println(str + ", " + no);
		return "test/test1";
	}
	
	@PostMapping(value="/plus")
	public String plus(int num1, int num2, Model model) {
		//TODO: process POST request
		int result = num1+num2;
		model.addAttribute("num1", num1);
		model.addAttribute("num2", num2);
		model.addAttribute("result", result);
		return "test/plus";
	}
	
	@GetMapping(value = "/thymeTest1")
	public String test1(Model model) {
		model.addAttribute("str","hi");
		model.addAttribute("n1",1);
		model.addAttribute("n2",2);
		
		return "test/thyme1";
	}
	
	@GetMapping(value = "/thymeTest2")
	public String test2(Model model) {
		Student s1 = new Student("학생1",20,"영등포");
		Student s2 = new Student("학생2",20,"마포");
		model.addAttribute("s1", s1);
		model.addAttribute("s2", s2);
		return "test/thyme2";
	}
	
	@GetMapping(value="/thymeTest3")
	public String test3(Model model) {
		List<Integer> list1 = new ArrayList<>();
		list1.add(100);
		list1.add(200);
		Student s1 = new Student("학생1",20,"영등포");
		Student s2 = new Student("학생2",20,"마포");
		List list2 = new ArrayList<Student>();
		list2.add(s1);
		list2.add(s2);
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		return "test/thyme3";
	}
	
	@GetMapping(value="/thymeTest4")
	public String test4(Model model) {
		model.addAttribute("data1", 102);
		model.addAttribute("data2", "ㅋㅋㅋㄹㅃㅃㅃㅃ");
		Student s = new Student("학생1",20,"서울");
		model.addAttribute("s", s);
		return "test/thyme4";
	}
	
	@GetMapping(value="/thymeTest5")
	public String test5(Model model) {
		model.addAttribute("num", 300);
		return "test/thyme5";
	}
	
	@GetMapping(value="/thymeTest6")
	public String test6(Model model) {
		List<Integer> list1 = new ArrayList<>();
		list1.add(100);
		list1.add(200);
		list1.add(300);
		list1.add(400);
		list1.add(500);
		
		Student s1 = new Student("학생1",20,"영등포");
		Student s2 = new Student("학생2",20,"마포");
		Student s3 = new Student("학생3",20,"영등포");
		Student s4 = new Student("학생4",20,"마포");
		Student s5 = new Student("학생5",20,"영등포");
		List list2 = new ArrayList<Student>();
		list2.add(s1);
		list2.add(s2);
		list2.add(s3);
		list2.add(s4);
		list2.add(s5);
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		return "test/thyme6";
	}
	
}
