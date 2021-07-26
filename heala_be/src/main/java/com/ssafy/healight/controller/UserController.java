package com.ssafy.healight.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.healight.domain.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RequestMapping("/user")
@RestController
@Api(value="Healight", description="Healight Resouces Management 2021")
public class UserController {
	
	@ApiOperation(value = "회원가입시 아이디 중복을 검사한다.")
	@GetMapping("/{id}")
	public User getUser(@PathVariable String id) {
		return new User();
	}
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
