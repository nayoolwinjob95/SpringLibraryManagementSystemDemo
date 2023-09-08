package co.jp.kadai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.jp.kadai.dto.BookDto;
import co.jp.kadai.entity.Book;
import co.jp.kadai.entity.Category;
import co.jp.kadai.service.BookService;
import co.jp.kadai.service.CategoryService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	BookService bookService;

	@GetMapping(value = "/addBook")
	public String viewAddBook(Model model) {
		List<Category> categoryList = categoryService.getCategory();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("bookTypes", Book.Type.values());
		model.addAttribute("form", bookService.getBookWithMaxId());

		return "addbook";
	}

	@PostMapping(value = "/addBook")
	public String addBook(Model model, @Valid @ModelAttribute("form") BookDto bookDto, BindingResult result) {
		if (result.hasErrors()) {
			List<Category> categoryList = categoryService.getCategory();
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("bookTypes", Book.Type.values());
			return "addbook";
		}
		bookService.saveBook(bookDto);

		return "redirect:/";
	}

	@GetMapping(value = "/updateBook/{id}")
	public String viewUpdateBook(Model model, @PathVariable String id) {
		List<Category> categoryList = categoryService.getCategory();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("bookTypes", Book.Type.values());
		model.addAttribute("form", bookService.getBookWithId(id));

		return "updatebook";
	}

	@PostMapping(value = "/updateBook/{id}")
	public String updateBook(Model model, @PathVariable String id, @Valid @ModelAttribute("form") BookDto bookDto,
			BindingResult result) {
		if (result.hasErrors()) {
			List<Category> categoryList = categoryService.getCategory();
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("bookTypes", Book.Type.values());
			model.addAttribute("id", id);
			return "addbook";
		}
		bookService.updateBook(bookDto);

		return "redirect:/";
	}

}
