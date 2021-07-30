package com.ssafy.healight.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.healight.domain.entity.Profile;
import com.ssafy.healight.domain.entity.User;
import com.ssafy.healight.domain.entity.UserBadge;
import com.ssafy.healight.domain.repository.BadgeRepository;
import com.ssafy.healight.domain.repository.UserBadgeRepository;
import com.ssafy.healight.domain.repository.UserRepository;

import io.swagger.annotations.ApiOperation;


@CrossOrigin("*")
@RequestMapping("/user")
@RestController
public class UserController {
	

	@Autowired
    UserRepository userRepository;
	
	@Autowired
    BadgeRepository badgeRepository;
	
	@Autowired
    UserBadgeRepository userbadgeRepository;
	

	// 아이디 중복 검사
	@GetMapping("/checkidentity/{identity}")
	public Object checkId(@PathVariable String identity) {
		Optional<User> userOpt = userRepository.getUserByIdentity(identity);
		if (!userOpt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// 이메일 중복 검사
	@GetMapping("/checkemail/{email}")
	public Object checkEmail(@PathVariable String email) {
		Optional<User> userOpt = userRepository.getUserByEmail(email);
		if (!userOpt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// 회원 가입
	@PostMapping("/signup")
	public Object signUp(@RequestBody User user) {
		userRepository.save(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	// 로그인
	@PostMapping("/login")
	public Object login(@RequestBody User user) {
		Optional<User> userOpt = userRepository.getUserByIdentityAndPassword(user.getIdentity(), user.getPassword());
		if (userOpt.isPresent()) {
			return new ResponseEntity<Integer>(userOpt.get().getId(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "프로필 편집 조회하기.")
	@GetMapping("/profile/{user_id}")
	public Object getUser(@PathVariable int user_id) {
		Optional<User> userOpt = Optional.of(userRepository.getUserById(user_id));
		Map<String, Object> response = new HashMap<>();
		if(userOpt.isPresent()) {
			response.put("user", userOpt);
			response.put("badges", userbadgeRepository.getAllWithBadge(user_id));
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}


	@ApiOperation(value = "프로필 편집 반영하기.")
	@PatchMapping("/profile/{user_id}")
	public Object updateUser(@PathVariable int user_id, @RequestBody Profile userRequest) throws Exception {
		User updateUser = userRepository.getUserById(user_id);
		boolean user_update = false;
		boolean badge_update = false;
		User user = userRequest.getUser();
		List<UserBadge> badges = userRequest.getBadges();
		MultipartFile image = userRequest.getImage();
		
		//프로필 사진 변경
		if(!image.isEmpty()) {
			String fileName = image.getOriginalFilename();
			Path path = Paths.get("C:/Users/82103/Desktop/healight/S05P12A605/heala_fe/src/assets" + image.getOriginalFilename());
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
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}


