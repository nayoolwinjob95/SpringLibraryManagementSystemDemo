package co.jp.kadai.entity;

import lombok.Data;

@Data
public class Book {

	private String id;
	private String bookName;
	private int bookCategoryId;
	private String author;
	private int produceYear;
	private Type bookType;
	private String bookImage;
	private String ebookPdf;
	
	public enum Type {
		Book, Ebook
	}
}
