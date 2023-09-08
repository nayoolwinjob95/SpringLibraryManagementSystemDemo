package co.jp.kadai.dto;

import co.jp.kadai.annotations.UsernameValidation;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

	private String id;
	@UsernameValidation(message = "ユーザー名が既に登録されてます。")
	@NotBlank(message = "名前を入力してください。")
	private String username;
	@NotBlank(message = "メールを入力してください。")
	private String email;
	@NotBlank(message = "パスワードを入力してください。")
	private String password;
	@NotBlank(message = "電話番号を入力してください。")
	private String phoneNo;
}
