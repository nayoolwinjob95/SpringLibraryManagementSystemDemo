package co.jp.kadai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.jp.kadai.service.BorrowedBookService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BorrowedBookController {

	@Autowired
	BorrowedBookService borrowedBookService;

	@GetMapping(value = "/borrow/{bookid}")
	public String borrowBook(Model model, @PathVariable String bookid, HttpServletRequest request) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		borrowedBookService.saveBorrowedBook(bookid, username);

		return "redirect:" + request.getHeader("Referer");
	}

	@GetMapping(value = "/return/{borrowedid}")
	public String returnBook(Model model, @PathVariable int borrowedid, HttpServletRequest request) {
		borrowedBookService.returnBorrowedBook(borrowedid);

		return "redirect:" + request.getHeader("Referer");
	}
}
