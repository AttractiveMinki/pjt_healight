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
	
	//회원 탈퇴
	boolean delete(int userId);

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
	boolean updateUser(int userId, ProfileIp profileIp) throws IllegalStateException, IOException;

	// 유저 간단 정보 가져오기
    UserSimpleOp getUserSimpleInfo(int userId);
	List<UserSimpleOp> getUserSimpleInfoAll(List<Integer> userIdSet);

	// 댓글 전체 좋아요 정보 가져오기
    List<Integer> getAllLikeCommentByUserId(List<Integer> commentIdSet, int userId);

    // 유저 exp 정보 가져오기
	Integer getUserExpByUserId(int userId);
	
	// id로 유저 정보 조회 (true:존재, false:없는 id)
	boolean existId(int id);

	// 팔로우, 언팔로우
    boolean follow(int userId, int followId);
	boolean cancelFollow(int userId, int followId);
}
