package com.ssafy.kiwi.model.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.ssafy.kiwi.model.domain.entity.*;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.kiwi.model.domain.repository.FollowRepository;
import com.ssafy.kiwi.model.domain.repository.LikeUserRepository;
import com.ssafy.kiwi.model.domain.repository.UserBadgeRepository;
import com.ssafy.kiwi.model.domain.repository.UserRepository;

import com.ssafy.kiwi.model.dto.ProfileIp;
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
	public Optional<User> checkId(String identity) {
		return userRepository.getUserByIdentity(identity);
	}

	//이메일 중복 검사
	@Override
	public Optional<User> checkEmail(String email) {
		return userRepository.getUserByEmail(email);
	}

	//회원가입
	@Override
	public void signUp(User user) {
		userRepository.save(user);
	}
	
	//회원 탈퇴
	@Override
	public boolean delete(User user) {
		Optional<User> userOpt = userRepository.getUserByIdentityAndPassword(user.getIdentity(), user.getPassword());
		if (userOpt.isPresent()) {
			userRepository.deleteById(userOpt.get().getId());
			return true;
		} else {
			return false;
		}
	}

	//로그인
	@Override
	public Optional<User> login(String identity, String password) {
		return userRepository.getUserByIdentityAndPassword(identity, password);
	}

	
	//유저 정보 조회하기 - optional user type
	@Override
	public Optional<User> getUser(int userId) {
		return Optional.of(userRepository.getUserById(userId));
	}
	
	//유저 정보 조회하기 - user type
	@Override
	public User getUserProfile(int userId) {
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
		Optional<User> userOpt = Optional.of(userRepository.getUserById(userId));
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
		User updateUser = getUserProfile(userId);
		boolean user_update = false;
		boolean badge_update = false;
		User user = profileIp.getUser();
		List<UserBadge> badges = profileIp.getBadges();
		MultipartFile image = profileIp.getImage();

		//프로필 사진 변경
		//null exception 나니까 프론트랑 어떻게 처리할지 상의하기
		if(!image.isEmpty()) {
			String fileName = image.getOriginalFilename();
			Path path = Paths.get("../../../../../../../../heala_fe/src/assets/image/profile/" + image.getOriginalFilename());
			image.transferTo(path);
			updateUser.setImage(fileName);
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
	
	//팔로우
	@Override
	public Follow saveFollow(Follow follow) { return followRepository.save(follow); }

	//팔로우 관계 찾기
	@Override
	public Optional<Follow> findFirstByFollowIdAndUserId(int followId, int userId) { return followRepository.findFirstByFollowIdAndUserId(followId, userId); }

	//언팔로우
	@Override
	public void delete(Follow follow) { followRepository.delete(follow); }

	// 유저 간단 정보(아이디, 이름, 프로필사진) 불러오기
	@Override
	public UserSimpleOp getUserSimpleInfo(int userId) {
		return userRepository.getUserSimpleInfoById(userId);
	}

	// 유저 간단 정보 여러 명 불러오기
	@Override
	public List<UserSimpleOp> getUserSimpleInfoAll(List<Integer> userIdSet) {
		return userRepository.getUserSimpleInfoByIds(userIdSet);
	}

	@Override
	public List<Integer> getAllLikeCommentByUserId(List<Integer> commentIdSet, int userId) {
		return likeUserRepository.getAllLikeUserByUserId(commentIdSet, userId);
	}

	@Override
	public Integer getUserExpByUserId(int userId) {
		return userRepository.getUserExpByUserId(userId);
	}

}
