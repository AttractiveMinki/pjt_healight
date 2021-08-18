package com.ssafy.kiwi.model.service;

import java.io.IOException;
import java.util.*;

import com.ssafy.kiwi.model.domain.authentication.entity.Member;
import com.ssafy.kiwi.model.domain.entity.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ssafy.kiwi.model.domain.repository.FollowRepository;
import com.ssafy.kiwi.model.domain.repository.LikeUserRepository;
import com.ssafy.kiwi.model.domain.repository.UserBadgeRepository;
import com.ssafy.kiwi.model.domain.repository.UserRepository;
import com.ssafy.kiwi.model.dto.ProfileIp;
import com.ssafy.kiwi.model.dto.UserFollowOp;
import com.ssafy.kiwi.model.dto.UserSimpleOp;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	
    final private UserRepository userRepository;
    final private UserBadgeRepository userbadgeRepository;
    final private FollowRepository followRepository;
    final private LikeUserRepository likeUserRepository;
    
    //아이디 중복 검사
	@Override
	public Optional<Member> checkId(String identity) {
		return userRepository.getUserByIdentity(identity);
	}

	//이메일 중복 검사
	@Override
	public Optional<Member> checkEmail(String email) {
		return userRepository.getUserByEmail(email);
	}

	//회원가입
	@Override
	public void signUp(Member user) {
		userRepository.save(user);
	}
	
	//회원 탈퇴
	@Override
	public boolean delete(int userId) {
		Optional<Member> user = userRepository.findById(userId);
		if (user.isPresent()) {
			userRepository.deleteById(user.get().getId());
			return true;
		} else {
			return false;
		}
	}

	//로그인
	@Override
	public Optional<Member> login(String identity, String password) {
		return userRepository.getUserByIdentityAndPassword(identity, password);
	}

	
	//유저 정보 조회하기 - optional user type
	@Override
	public Optional<Member> getUser(int userId) {
		return Optional.of(userRepository.getUserById(userId));
	}
	
	//유저 정보 조회하기 - user type
	@Override
	public Member getUserProfile(int userId) {
		return userRepository.getUserById(userId);
	}
	
	//배지 정보 가져오기 - 모든 정보
	@Override
	public List<UserBadge> getAllWithBadge(int userId) {
		return userbadgeRepository.getAllWithBadge(userId);
	}
	
	//배지 정보 가져오기 - 가공 (배지보관함용)
	@Override
	public Object getBadge(int userId) {
		List<UserBadge> userBadges = getAllWithBadge(userId);
		List<Badge> badge = new ArrayList<>();
		for (UserBadge ub : userBadges) {
			badge.add(ub.getBadge());
		}
		return badge;
	}
	
	//프로필 편집 조회하기
	@Override
	public Map<String, Object> getProfile(int userId) {
		Optional<Member> userOpt = userRepository.findById(userId);
		Map<String, Object> response = new HashMap<>();
		if(userOpt.isPresent()) {
			response.put("image", userOpt.get().getImage());
			response.put("name", userOpt.get().getName());
			response.put("identity", userOpt.get().getIdentity());
			response.put("introduction", userOpt.get().getIntroduction());
			response.put("badges", getAllWithBadge(userId));
		}
		return response;
	}

	//프로필 편집 반영하기
	@Override
	public boolean updateUser(int userId, ProfileIp profileIp) throws IllegalStateException, IOException {
		Member updateUser = getUserProfile(userId);
		boolean user_update = false;
		boolean badge_update = false;
		Member user = profileIp.getUser();
		List<UserBadge> badges = profileIp.getBadges();

		//프로필 사진 변경
		if(StringUtils.hasLength(user.getImage())) {
			updateUser.setImage(user.getImage());
			user_update = true;
		}
		//프로필 사진 외 정보 변경
		if(StringUtils.hasLength(user.getName())) {
			updateUser.setName(user.getName());
			user_update = true;
		}
		if(StringUtils.hasLength(user.getIdentity())) {
			updateUser.setIdentity(user.getIdentity());
			user_update = true;
		}
		if(StringUtils.hasLength(user.getIntroduction())) {
			updateUser.setIntroduction(user.getIntroduction());
			user_update = true;
		}
		//배지 변경
		if(badges.size()!=0) badge_update = true;
		
		if(user_update || badge_update) {
			if(user_update) userRepository.save(updateUser);
			if(badge_update) userbadgeRepository.saveAll(badges);
			return true;
		}
		return false;
	}

	// 팔로우
	@Override
	public boolean follow(int userId, int followId) {
		// userId 와 followId 에 해당하는 유저가 있는지 검사
		Optional<Member> user = userRepository.findById(userId);
		Optional<Member> followUser = userRepository.findById(followId);
		if(!user.isPresent() || !followUser.isPresent()) {
			return false;
		}

		if(followRepository.findByFollowIdAndUserId(followId, userId).isPresent()){
			return false;
		}

		Follow follow = new Follow(followId, userId);
		followRepository.save(follow);
		return true;
	}

	// 언팔로우
	@Override
	public boolean cancelFollow(int userId, int followId) {
		Optional<Follow> follow = followRepository.findByFollowIdAndUserId(followId, userId);
		if(!follow.isPresent()) return false;
		followRepository.delete(follow.get());
		return true;
	}

	@Override
	public boolean getFollowByUserIdAndFollowId(int userId, int followId) {
		Optional<Follow> follow = followRepository.findByFollowIdAndUserId(followId, userId);
		if(follow.isPresent()) return true;
		return false;
	}

	// 유저 간단 정보(아이디, 이름, 프로필사진) 불러오기
	@Override
	public UserSimpleOp getUserSimpleInfo(int userId) {
		return userRepository.getUserSimpleInfoById(userId);
	}

	// 유저 간단 정보 여러 명 불러오기
	@Override
	public List<UserSimpleOp> getUserSimpleInfoAll(List<Integer> userIdSet) {
		return userRepository.getMemberSimpleInfoByIds(userIdSet);
	}

	@Override
	public List<Integer> getAllLikeCommentByUserId(List<Integer> commentIdSet, int userId) {
		return likeUserRepository.getAllLikeUserByUserId(commentIdSet, userId);
	}

	@Override
	public Integer getMemberExpByUserId(int userId) {
		return userRepository.getMemberExpByUserId(userId);
	}

	// id로 유저 존재여부 확인
	@Override
	public boolean existId(int id) {
		if(userRepository.countById(id)==1) return true;
		else return false;
	}

	// 단어를 identity나 name에 포함하는 유저 검색
	@Override
	public List<UserFollowOp> getUserListByWord(int userId, String word, int page) {
		Page<UserFollowOp> userListPage = userRepository.getMemberListByWord(userId, word, PageRequest.of(page, 20, Sort.by("id").descending())); // word 포함하는 유저 리스트
		List<UserFollowOp> userList = userListPage.getContent();
		for (UserFollowOp user : userList) { // 팔로잉 여부 확인
			int otherUserId = user.getId();
			if (followRepository.countByFollowIdAndUserId(otherUserId, userId)==1) user.setFollow(true);
		}
		return userList;
	}
	

}
