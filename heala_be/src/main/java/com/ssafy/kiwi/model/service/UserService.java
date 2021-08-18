package com.ssafy.kiwi.model.service;

import java.io.IOException;
import java.util.*;

import com.ssafy.kiwi.model.domain.authentication.entity.Member;
import com.ssafy.kiwi.model.dto.ProfileIp;
import com.ssafy.kiwi.model.dto.UserFollowOp;
import com.ssafy.kiwi.model.dto.UserSimpleOp;

public interface UserService {

	//회원가입 부분
	Optional<Member> checkId(String identity);
	Optional<Member> checkEmail(String email);
	void signUp(Member user);
	
	//회원 탈퇴
	boolean delete(int userId);

	//로그인
	Optional<Member> login(String identity, String password);
	
	//유저 정보 조회
	Optional<Member> getUser(int followId);
	Member getUserProfile(int user_id);

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
	Integer getMemberExpByUserId(int userId);
	
	// id로 유저 정보 조회 (true:존재, false:없는 id)
	boolean existId(int id);
	
	// 단어를 identity나 name에 포함하는 유저 검색
	List<UserFollowOp> getUserListByWord(int userId, String word, int page);

	// 팔로우, 언팔로우
    boolean follow(int userId, int followId);
	boolean cancelFollow(int userId, int followId);

	// 팔로우 확인
    boolean getFollowByUserIdAndFollowId(int userId, int followId);
}
