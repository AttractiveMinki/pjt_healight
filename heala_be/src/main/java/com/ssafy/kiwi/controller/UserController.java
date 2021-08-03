package com.ssafy.kiwi.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.kiwi.model.dto.Profile;
import com.ssafy.kiwi.model.domain.entity.User;
import com.ssafy.kiwi.model.domain.entity.UserBadge;
import com.ssafy.kiwi.model.domain.entity.Follow;
import com.ssafy.kiwi.model.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/user")
@RestController
public class UserController {
	
    final private UserService userService;

	@ApiOperation(value = "아이디 중복 검사하기.")
	@GetMapping("/checkidentity/{identity}")
	public Object checkId(@PathVariable String identity) {
		Optional<User> userOpt = userService.checkId(identity);
		if (!userOpt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "이메일 중복 검사.")
	@GetMapping("/checkemail/{email}")
	public Object checkEmail(@PathVariable String email) {
		Optional<User> userOpt = userService.checkEmail(email);
		if (!userOpt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "회원가입 하기.")
	@PostMapping("/signup")
	public Object signUp(@RequestBody User user) {
		userService.signUp(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "로그인 하기.")
	@PostMapping("/login")
	public Object login(@RequestBody User user) {
		Optional<User> userOpt = userService.login(user.getIdentity(), user.getPassword());
		if (userOpt.isPresent()) {
			return new ResponseEntity<>(userOpt.get().getId(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@ApiOperation(value = "프로필 편집 조회하기.")
	@GetMapping("/profile/{userId}")
	public Object getUser(@PathVariable int userId) {
		Optional<User> userOpt = userService.getUser(userId);
		Map<String, Object> response = new HashMap<>();
		if(userOpt.isPresent()) {
			response.put("user", userOpt);			
			response.put("badges", userService.getAllWithBadge(userId));
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}


	@ApiOperation(value = "프로필 편집 반영하기.")
	@PatchMapping("/profile/{userId}")
	public Object updateUser(@PathVariable int userId, @RequestBody Profile userRequest) throws Exception {
		
		User updateUser = userService.getUserProfile(userId);
		boolean user_update = false;
		boolean badge_update = false;
		User user = userRequest.getUser();
		List<UserBadge> badges = userRequest.getBadges();
		MultipartFile image = userRequest.getImage();

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
			System.out.println("이름바꾼다-------------------");
			updateUser.setIntroduction(user.getIntroduction());
			user_update = true;
		}
		//배지 변경
		if(badges.size()!=0) badge_update = true;
		
		if(user_update || badge_update) {
			System.out.println("변경합니다");
			if(user_update) userService.save(updateUser);
			if(badge_update) {
				System.out.println("배지 ㅇㅋ");
				userService.saveAll(badges);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@ApiOperation(value = "팔로우하기.")
	@PostMapping("/follow")
	public Object follow(@RequestBody Follow follow) {
		int userId = follow.getUserId();
		int followId = follow.getFollowId();
		// userId 와 followId 에 해당하는 유저가 있는지 검사
		Optional<User> user = userService.getUser(userId);
		Optional<User> followUser = userService.getUser(followId);

		// 있으면 follow 관계 저장
		if(user.isPresent() && followUser.isPresent()) {
			Follow newFollow = new Follow(followId, userId);
			userService.saveFollow(newFollow);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@ApiOperation(value = "팔로우 취소하기.")
	@DeleteMapping("/follow")
	public Object cancelFollow(@RequestBody Follow follow) {
		Optional<Follow> oldFollow = userService.findFirstByFollowIdAndUserId(follow.getFollowId(), follow.getUserId());

		// 있으면 follow 관계 저장
		if(oldFollow.isPresent()) {
			userService.delete(oldFollow.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}


