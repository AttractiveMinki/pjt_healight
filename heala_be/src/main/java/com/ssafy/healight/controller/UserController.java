package com.ssafy.healight.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@RequestMapping("/user")
@RestController

public class UserController {
	
	/*
	@Autowired
	private UserService userService;

	// 회원 가입
	@PostMapping("/signup")
	public boolean signUp(@RequestBody User user) {
		return userService.signUp(user);
	}

	// 회원 정보 불러오기
	@GetMapping("/{id}")
	public User getUser(@PathVariable String id) {
		return userService.getUser(id);
	}

	// 회원 정보 수정
	@PutMapping("/modify")
	public boolean modifyUser(@RequestBody User user) {
		return userService.modifyUser(user);
	}

	// 회원 탈퇴
	@PutMapping("/{id}")
	public boolean deleteUser(@RequestBody User user) {
		return userService.deleteUser(user);
	}

	// 로그인
	@PostMapping("/login")
	public String login(@RequestBody User user, HttpSession session) {
		String name = userService.login(user.getId(), user.getPassword());
		if (name != null) {
			session.setAttribute("id", user.getId());
			session.setAttribute("name", name);
			return name;
		} else {
			return "false";
		}
	}

	// 로그아웃
	@GetMapping("/logout")
	public boolean logout(HttpSession session) {
		session.invalidate();
		return true;
	}
	*/
}
