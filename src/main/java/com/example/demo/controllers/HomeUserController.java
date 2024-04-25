package com.example.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.User;
import com.example.demo.services.UserService;

@Controller
public class HomeUserController {

	@Autowired
	private UserService userService;
	@RequestMapping("/login")
	public String login() {
		
		return"login";
	}
	
	@RequestMapping("/register")
	public String register(Model model) {
		
		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}
	
	@PostMapping("/register")
	public String doRegister(@ModelAttribute("user") User user) {
		
		//mã hóa mật khẩu
		
		String hasPass = new BCryptPasswordEncoder().encode(user.getPassWord());
		user.setPassWord(hasPass);
		user.setEnabled(true);
		this.userService.create(user);
		return "login";
	}
}
