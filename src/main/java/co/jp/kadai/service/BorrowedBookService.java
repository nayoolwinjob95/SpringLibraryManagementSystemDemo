package co.jp.kadai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.jp.kadai.repository.BorrowedBookRepository;

@Service
public class BorrowedBookService {

	@Autowired
	BorrowedBookRepository borrowedBookRepository;

	public void saveBorrowedBook(String bookid, String username) {
		borrowedBookRepository.saveBorrowedBook(bookid, username);
	}

	public void returnBorrowedBook(int borrowedid) {
		borrowedBookRepository.returnBorrowedBook(borrowedid);
	}
}
