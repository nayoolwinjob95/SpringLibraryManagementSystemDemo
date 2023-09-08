package co.jp.kadai.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.jp.kadai.entity.User;

@Mapper
public interface UserRepository {

	void register(User user);

	Optional<User> findOneByUsername(@Param("username") String username);

}
