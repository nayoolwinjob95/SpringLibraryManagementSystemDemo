package co.jp.kadai.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDto {

	@NotBlank(message = "IDを入力してください。")
	private String id;
	@NotBlank(message = "Nameを入力してください。")
	private String name;

}
