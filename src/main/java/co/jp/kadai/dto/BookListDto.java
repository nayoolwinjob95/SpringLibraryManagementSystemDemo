package co.jp.kadai.dto;

import lombok.Data;

@Data
public class BookListDto {

	private String id;
	private String bookName;
	private String bookCategoryId;
	private String author;
	private String produceYear;
	private String bookType;
	private String bookImage;
	private String ebookPdf;
	private String borrowedId;
	private String username;
	private String borrowedFlag;
}
