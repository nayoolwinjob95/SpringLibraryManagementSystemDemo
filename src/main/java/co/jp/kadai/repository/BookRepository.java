package co.jp.kadai.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.jp.kadai.dto.BookDto;
import co.jp.kadai.dto.BookListDto;
import co.jp.kadai.entity.Book;

@Mapper
public interface BookRepository {

	BookDto getBookWithMaxId();

	void saveBook(Book book);

	Book getBookWithId(String id);

	List<BookListDto> getBook();

	List<BookListDto> getBook(@Param("category") Integer category, @Param("author") String author,
			@Param("registrationNo") String registrationNo, @Param("bookName") String bookName,
			@Param("bookType") String bookType);

	void updateBook(Book book);

}
