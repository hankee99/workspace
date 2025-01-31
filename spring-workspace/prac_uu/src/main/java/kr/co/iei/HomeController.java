package kr.co.iei;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class HomeController {
	@GetMapping(value="/")
	public String main() {
		return "index";
	}
	
}
