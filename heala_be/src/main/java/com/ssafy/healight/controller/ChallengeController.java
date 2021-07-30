package com.ssafy.healight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.healight.domain.entity.WithInput;
import com.ssafy.healight.service.WithChallengeService;

@CrossOrigin("*")
@RequestMapping("/challenge")
@RestController

public class ChallengeController {
	
	@Autowired
	private WithChallengeService withChallengeService;
	
	// 글 작성
	@PostMapping("/post")
	public Object post(@RequestBody WithInput withInput) {
		withChallengeService.post(withInput);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
