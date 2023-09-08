package co.jp.kadai.annotations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import co.jp.kadai.entity.User;
import co.jp.kadai.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<UsernameValidation, String>{
	
	@Autowired
	UserService userService;

	@Override
	public boolean isValid(String username, ConstraintValidatorContext cxt) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.isEmpty();
    }
}
