package com.ssafy.kiwi.controller;

import java.util.*;

import com.ssafy.kiwi.model.dto.UserIds;
import com.ssafy.kiwi.model.dto.UserSimpleInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.kiwi.model.dto.Profile;
import com.ssafy.kiwi.model.domain.entity.User;
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
	public Object getProfile(@PathVariable int userId) {
		return new ResponseEntity<>(userService.getProfile(userId), HttpStatus.OK);
	}

	@ApiOperation(value = "프로필 편집 반영하기.")
	@PatchMapping("/profile/{userId}")
	public Object updateUser(@PathVariable int userId, @RequestBody Profile userRequest) throws Exception {
		if(userService.updateUser(userId, userRequest)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

	@ApiOperation(value = "유저 한 명 간단 정보 불러오기")
	@GetMapping("/simple/{userId}")
	public Object getUserSimpleInfoOne(@PathVariable int userId){
		UserSimpleInfo userSimpleInfo = userService.getUserSimpleInfo(userId);
		if(userSimpleInfo != null){
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "유저 여러 명 간단 정보 불러오기")
	@PostMapping("/comment/users")
	public Object getUserSimpleInfoAll(@RequestBody UserIds userIds){
		List<UserSimpleInfo> userSimpleInfoList = userService.getUserSimpleInfoAll(userIds.getUserIdSet());
		return new ResponseEntity<>(userSimpleInfoList, HttpStatus.OK);
	}
}


