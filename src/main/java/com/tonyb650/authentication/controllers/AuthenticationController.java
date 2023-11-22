package com.tonyb650.authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tonyb650.authentication.models.LoginUser;
import com.tonyb650.authentication.models.User;
import com.tonyb650.authentication.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AuthenticationController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String loginPage(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "login.jsp";
	}
	
	@GetMapping("/welcome") 
	public String welcomePage(HttpSession session){
		try {
			boolean loggedIn = session.getAttribute("isLoggedIn").equals(true);
			return "welcome.jsp";
		} catch (Exception e) {
			return "notloggedin.jsp";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		System.out.println("User logged out / session invalidated.");
		return "redirect:/";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		userService.register(newUser, result);
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser()); // clear login side of page
			return "login.jsp";
		}
		// Success, add user to session
		session.setAttribute("userName", newUser.getUserName());
		session.setAttribute("id", newUser.getId());
		session.setAttribute("isLoggedIn", true);
		return "redirect:/welcome";
	}
	
	@PostMapping("/login")
	public String register(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
		User user = userService.login(newLogin, result);
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User()); // clear register side of page
			return "login.jsp";
		}
		// Success, add user to session
		session.setAttribute("userName", user.getUserName());
		session.setAttribute("id", user.getId());
		session.setAttribute("isLoggedIn", true);
		return "redirect:/welcome";
	}
	
}
