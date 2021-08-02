package com.ssafy.kiwi.model.service;

import java.util.List;
import java.util.Optional;

import com.ssafy.kiwi.model.domain.entity.Follow;
import com.ssafy.kiwi.model.domain.entity.User;
import com.ssafy.kiwi.model.domain.entity.UserBadge;

public interface UserService {

	Optional<User> checkId(String identity);

	Optional<User> checkEmail(String email);

	void signUp(User user);

	Optional<User> login(String identity, String password);

	Optional<User> getUser(int user_id);

	User getUserProfile(int user_id);

	Object getAllWithBadge(int user_id);

	void save(User updateUser);

	void saveAll(List<UserBadge> badges);

	Follow saveFollow(Follow follow);
	
	Optional<Follow> findFirstByFollowIdAndUserId(int followId, int userId);

	void delete(Follow follow);
}
