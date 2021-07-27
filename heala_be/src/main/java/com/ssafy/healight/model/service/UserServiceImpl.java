//package com.ssafy.healight.model.service;
//
//import java.util.Optional;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import com.ssafy.healight.domain.entity.User;
//import com.ssafy.healight.domain.repository.UserRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService {
//	
//	private static final String SUCCESS = "success";
//	private static final String FAIL = "fail";
//	
//	final private UserRepository userRepository = null;
//	
//	// 회원 가입
//	@Override
//	public ResponseEntity<String> signUp(User user) {
//		
////		// 아이디
////		Optional<User> findUserByIdentity = userRepository.getUserByIdentity(user.getIdentity());
////		// 이메일
////		Optional<User> findUserByEmail = userRepository.getUserByEmail(user.getEmail());
////		
////		System.out.println(findUserByIdentity);
//		
//		if (!findUserByIdentity.isPresent() && !findUserByEmail.isPresent()) {
//			userRepository.save(user);
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		else {
//			return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
//		}
//	}
//	
////	@Override
////	public User getUser(int id) {
////		return userRepository.findUserById(id).get();
//////		return userDao.getUser(id);
////	}
//	
////	// 로그인
////	@Override
////	public ResponseEntity<String> login(String identity, String password) {
////		Optional<User> user = userRepository.findUserByIdentityAndPassword(identity, password);
////		if (user.isPresent()) {
////			return user.get().getName();
////		}
////		return null;
////	}
//
//}
//
