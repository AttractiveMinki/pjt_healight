package com.ssafy.healight.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ssafy.healight.domain.entity.User;
import com.ssafy.healight.domain.entity.UserBadge;
import com.ssafy.healight.domain.repository.BadgeRepository;
import com.ssafy.healight.domain.repository.UserBadgeRepository;
import com.ssafy.healight.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	
    final private UserRepository userRepository;
    final private BadgeRepository badgeRepository;
    final private UserBadgeRepository userbadgeRepository;
    
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
	public Optional<User> getUser(int user_id) {
		return Optional.of(userRepository.getUserById(user_id));
	}

	//프로필 편집 조회하기 - user 타입
	@Override
	public User getUserProfile(int user_id) {
		return userRepository.getUserById(user_id);
	}
	
	//배지 정보 가져오기
	@Override
	public Object getAllWithBadge(int user_id) {
		return userbadgeRepository.getAllWithBadge(user_id);
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



	
	
	
	
	
	
	
}
