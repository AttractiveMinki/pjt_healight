package com.ssafy.healight.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.healight.domain.entity.User;
import com.ssafy.healight.domain.repository.UserRepository;

import io.swagger.annotations.ApiOperation;


@CrossOrigin("*")
@RequestMapping("/user")
@RestController

public class UserController {
	
	@Autowired
    UserRepository userRepository;
	
	@ApiOperation(value = "프로필 편집 조회하기.")
	@GetMapping("/profile/{user_id}")
	public Object getUser(@PathVariable int user_id) {
		Optional<User> userOpt = Optional.of(userRepository.getUserById(user_id));
		if(userOpt.isPresent()) {
			return new ResponseEntity<Optional<User>>(userOpt, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@ApiOperation(value = "프로필 편집 반영하기.")
	@PatchMapping("/profile/{user_id}")
	public Object updateUser(@PathVariable int user_id, @RequestBody User user) {
		User updateUser = userRepository.getUserById(user_id);
		boolean update = false;
		if(StringUtils.hasLength(user.getImage())) {
			updateUser.setImage(user.getImage());
			update = true;
		}
		if(StringUtils.hasLength(user.getName())) {
			updateUser.setName(user.getName());
			update = true;
		}
		if(StringUtils.hasLength(user.getIdentity())) {
			updateUser.setIdentity(user.getIdentity());
			update = true;
		}
		if(StringUtils.hasLength(user.getIntroduction())) {
			updateUser.setIntroduction(user.getIntroduction());
			update = true;
		}
		if(update) {
			userRepository.save(updateUser);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
