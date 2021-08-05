package com.ssafy.kiwi.model.service;

import java.io.IOException;
import java.util.*;

import com.ssafy.kiwi.model.domain.entity.Follow;
import com.ssafy.kiwi.model.domain.entity.User;
import com.ssafy.kiwi.model.dto.ProfileIp;
import com.ssafy.kiwi.model.dto.UserSimpleOp;

public interface UserService {

	//회원가입 부분
	Optional<User> checkId(String identity);
	Optional<User> checkEmail(String email);
	void signUp(User user);

	//로그인
	Optional<User> login(String identity, String password);
	
	//유저 정보 조회
	Optional<User> getUser(int followId);
	User getUserProfile(int user_id);

	//배지 조회 (모든 정보)
	Object getAllWithBadge(int user_id);
	//배지 조회 (데이터 가공)
	Object getBadge(int userId);
	
	//프로필 편집
	Map<String, Object> getProfile(int user_id);
	boolean updateUser(int userId, ProfileIp userRequest) throws IllegalStateException, IOException;
	
	//팔로우, 언팔로우
	Follow saveFollow(Follow follow);
	Optional<Follow> findFirstByFollowIdAndUserId(int followId, int userId);
	void delete(Follow follow);

	// 유저 간단 정보 가져오기
    UserSimpleOp getUserSimpleInfo(int userId);
	List<UserSimpleOp> getUserSimpleInfoAll(List<Integer> userIdSet);
}
