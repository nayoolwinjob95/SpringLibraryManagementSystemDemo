package co.jp.kadai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import co.jp.kadai.dto.UserDto;
import co.jp.kadai.service.UserService;
import jakarta.validation.Valid;

@Controller
public class SecurityController {

	@Autowired
	UserService userService;

	@GetMapping("/register")
	public String viewRegister(Model model) {
		model.addAttribute("form", new UserDto());
		return "register";
	}

	@PostMapping("/register")
	public String register(Model model, @Valid @ModelAttribute("form") UserDto userDto, BindingResult result) {
		if (result.hasErrors()) {
			return "register";
		}
		userService.register(userDto);
		return "redirect:/";
	}

	@GetMapping("/login")
	public String viewLogin(Model model) {
		return "login";
	}
}
