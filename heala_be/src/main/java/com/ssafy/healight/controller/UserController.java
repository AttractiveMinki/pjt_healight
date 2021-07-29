package com.ssafy.healight.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public Object updateUser(@PathVariable int user_id, @RequestBody Profile userRequest) {
		User updateUser = userRepository.getUserById(user_id);
		boolean user_update = false;
		boolean badge_update = false;
		User user = userRequest.getUser();
		List<UserBadge> badges = userRequest.getBadges();
		if(StringUtils.hasLength(user.getImage())) {
			updateUser.setImage(user.getImage());
			user_update = true;
		}
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
		if(badges.size()!=0) badge_update = true;
		
		if(user_update || badge_update) {
			if(user_update) userRepository.save(updateUser);
			if(badge_update) userbadgeRepository.saveAll(badges);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
