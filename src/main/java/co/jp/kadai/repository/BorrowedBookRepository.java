package co.jp.kadai.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BorrowedBookRepository {

	void saveBorrowedBook(@Param("bookid") String bookid, @Param("username") String username);

	void returnBorrowedBook(@Param("borrowedid") int borrowedid);

}
