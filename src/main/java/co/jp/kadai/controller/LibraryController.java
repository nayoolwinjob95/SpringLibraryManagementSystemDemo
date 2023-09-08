package co.jp.kadai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.jp.kadai.dto.BookListDto;
import co.jp.kadai.entity.Category;
import co.jp.kadai.service.BookService;
import co.jp.kadai.service.CategoryService;

@Controller
public class LibraryController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	BookService bookservice;

	@GetMapping(value = "/")
	public String viewIndex(Model model) {
		List<Category> categoryList = categoryService.getCategory();
		List<BookListDto> bookList = bookservice.getBook();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		model.addAttribute("categoryList", categoryList);
		model.addAttribute("bookList", bookList);
		model.addAttribute("username", username);

		return "index";
	}

	@GetMapping(value = "/search")
	public String search(Model model, @RequestParam(name = "category", required = false) Integer category,
			@RequestParam(name = "author", required = false) String author,
			@RequestParam(name = "registrationNo", required = false) String registrationNo,
			@RequestParam(name = "bookName", required = false) String bookName,
			@RequestParam(name = "bookType", required = false) String bookType) {
		List<Category> categoryList = categoryService.getCategory();
		List<BookListDto> bookList = bookservice.getBook(category, author, registrationNo, bookName, bookType);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		model.addAttribute("categoryList", categoryList);
		model.addAttribute("bookList", bookList);
		model.addAttribute("username", username);

		return "index";
	}

}
