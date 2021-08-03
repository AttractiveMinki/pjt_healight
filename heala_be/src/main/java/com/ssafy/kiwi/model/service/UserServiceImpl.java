package com.ssafy.kiwi.model.service;

import java.util.List;
import java.util.Optional;

import com.ssafy.kiwi.model.domain.entity.Follow;
import com.ssafy.kiwi.model.domain.repository.FollowRepository;
import org.springframework.stereotype.Service;

import com.ssafy.kiwi.model.domain.entity.User;
import com.ssafy.kiwi.model.domain.entity.UserBadge;
import com.ssafy.kiwi.model.domain.repository.UserBadgeRepository;
import com.ssafy.kiwi.model.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	
    final private UserRepository userRepository;
    final private UserBadgeRepository userbadgeRepository;
    final private FollowRepository followRepository;
    
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

	//로그인
	@Override
	public Optional<User> login(String identity, String password) {
		return userRepository.getUserByIdentityAndPassword(identity, password);
	}

	//프로필 편집 조회하기 - optional user 타입
	@Override
	public Optional<User> getUser(int userId) {
		return Optional.of(userRepository.getUserById(userId));
	}

	//프로필 편집 조회하기 - user 타입
	@Override
	public User getUserProfile(int userId) {
		return userRepository.getUserById(userId);
	}
	
	//배지 정보 가져오기
	@Override
	public Object getAllWithBadge(int userId) {
		return userbadgeRepository.getAllWithBadge(userId);
	}

	//프로필 편집 저장 - 유저
	@Override
	public void save(User updateUser) {
		userRepository.save(updateUser);
	}

	//프로필 편집 저장 - 배지
	@Override
	public void saveAll(List<UserBadge> badges) {
		userbadgeRepository.saveAll(badges);
	}

	@Override
	public Follow saveFollow(Follow follow) { return followRepository.save(follow); }

	@Override
	public Optional<Follow> findFirstByFollowIdAndUserId(int followId, int userId) { return followRepository.findFirstByFollowIdAndUserId(followId, userId); }

	@Override
	public void delete(Follow follow) { followRepository.delete(follow); }
}
