package co.jp.kadai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.jp.kadai.dto.CategoryDto;
import co.jp.kadai.service.CategoryService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping(value = "/addCategory")
	public String viewAddCategory(Model model) {
		model.addAttribute("form", categoryService.getCategoryWithMaxId());
		return "addbookcategory";
	}

	@PostMapping(value = "/addCategory")
	public String submitCategory(Model model, @Valid @ModelAttribute("form") CategoryDto categoryDto,
			BindingResult result) {
		if (result.hasErrors()) {
			return "addbookcategory";
		}
		categoryService.save(categoryDto);
		return "redirect:/";

	}
}
