package co.jp.kadai.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.jp.kadai.dto.BookDto;
import co.jp.kadai.dto.BookListDto;
import co.jp.kadai.entity.Book;
import co.jp.kadai.entity.Book.Type;
import co.jp.kadai.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	public List<BookListDto> getBook() {
		return bookRepository.getBook();
	}

	public List<BookListDto> getBook(Integer category, String author, String registrationNo, String bookName, String bookType) {
		return bookRepository.getBook(category, author, registrationNo, bookName, bookType);
	}

	public BookDto getBookWithMaxId() {
		BookDto book = bookRepository.getBookWithMaxId();
		if (book != null) {
			String lastId = book.getId().substring(1);
			book.setId("C" + String.format("%05d", Integer.valueOf(lastId) + 1));
		} else {
			book = new BookDto();
			book.setId("C00001");
		}

		return book;
	}

	public void saveFile(BookDto bookDto) throws IllegalStateException, IOException {
		File file = new File("");
		String absolutePath = file.getAbsolutePath();
		String imgDir = absolutePath + "\\target\\classes\\static\\images\\books\\";
		String pdfDir = absolutePath + "\\target\\classes\\static\\images\\pdf\\";

		MultipartFile bookImage = bookDto.getBookImage();
		if (!bookImage.isEmpty()) {
			String[] ext = bookImage.getOriginalFilename().split("\\.");
			bookImage.transferTo(new File(imgDir + bookDto.getId() + "." + ext[ext.length - 1]));
		}

		MultipartFile pdf = bookDto.getEbookPdf();
		if (!pdf.isEmpty()) {
			pdf.transferTo(new File(pdfDir + bookDto.getId() + ".pdf"));
		}

	}

	public void saveBook(BookDto bookDto) {
		Book book = this.convertToBook(bookDto);
		bookRepository.saveBook(book);
		Book savedbook = bookRepository.getBookWithId(bookDto.getId());
		if (savedbook != null) {
			try {
				this.saveFile(bookDto);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private Book convertToBook(BookDto bookDto) {
		Book book = new Book();
		book.setId(bookDto.getId());
		book.setBookName(bookDto.getBookName());
		book.setBookCategoryId(Integer.valueOf(bookDto.getBookCategoryId()));
		book.setAuthor(bookDto.getAuthor());
		book.setProduceYear(Integer.valueOf(bookDto.getProduceYear()));
		book.setBookType(Type.valueOf(bookDto.getBookType()));

		MultipartFile bookImage = bookDto.getBookImage();
		if (!bookImage.isEmpty()) {
			String[] ext = bookImage.getOriginalFilename().split("\\.");
			book.setBookImage(bookDto.getId() + "." + ext[ext.length - 1]);
		}

		MultipartFile pdf = bookDto.getEbookPdf();
		if (!pdf.isEmpty()) {
			book.setEbookPdf(bookDto.getId() + ".pdf");
		}
		return book;
	}

	public BookDto getBookWithId(String id) {
		Book book = bookRepository.getBookWithId(id);
		BookDto bookDto = this.convertToBookDto(book);

		return bookDto;
	}

	private BookDto convertToBookDto(Book book) {
		BookDto bookDto = new BookDto();
		bookDto.setId(book.getId());
		bookDto.setBookName(book.getBookName());
		bookDto.setBookCategoryId(String.valueOf(book.getBookCategoryId()));
		bookDto.setAuthor(book.getAuthor());
		bookDto.setProduceYear(String.valueOf(book.getProduceYear()));
		bookDto.setBookType(String.valueOf(book.getBookType()));

		return bookDto;
	}

	public void updateBook(BookDto bookDto) {
		Book book = this.convertToBook(bookDto);
		bookRepository.updateBook(book);
		Book savedbook = bookRepository.getBookWithId(bookDto.getId());
		if (savedbook != null) {
			try {
				this.saveFile(bookDto);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
