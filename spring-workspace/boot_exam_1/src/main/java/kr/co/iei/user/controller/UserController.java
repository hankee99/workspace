package kr.co.iei.user.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.user.model.dto.UserDTO;

import kr.co.iei.user.model.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping(value = "/searchUser")
	public String searchUser(UserDTO u, Model model) {
		UserDTO user = userService.searchUser(u);

		model.addAttribute("u", user);
		System.out.println(user.getUserNo());
		return "user/searchResult";

	}

}