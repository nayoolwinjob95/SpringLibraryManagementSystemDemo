package co.jp.kadai.entity;

import lombok.Data;

@Data
public class BorrowedBook {

	private int id;
	private String bookId;
	private int userId;
	private int borrowedFlag;
}
