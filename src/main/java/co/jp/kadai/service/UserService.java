package co.jp.kadai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.jp.kadai.dto.UserDto;
import co.jp.kadai.entity.User;
import co.jp.kadai.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void register(UserDto userDto) {
		User user = this.convertToUser(userDto);
		userRepository.register(user);
	}

	private User convertToUser(UserDto userDto) {
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setPhoneNo(Integer.valueOf(userDto.getPhoneNo()));

		return user;
	}

	public Optional<User> getUserByUsername(String username) {
		Optional<User> user = userRepository.findOneByUsername(username);
		return user;
	}
}
