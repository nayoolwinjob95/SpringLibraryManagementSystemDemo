package co.jp.kadai.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookDto {

	private String id;
	@NotBlank(message = "本の名前を入力してください。")
	private String bookName;
	@NotBlank(message = "本のカテゴリーを入力してください。")
	private String bookCategoryId;
	@NotBlank(message = "著者の名前を入力してください。")
	private String author;
	@NotBlank(message = "生産年を入力してください。")
	private String produceYear;
	@NotBlank(message = "本の種類を入力してください。")
	private String bookType;
	private MultipartFile bookImage;
	private MultipartFile ebookPdf;
}
