package com.ssafy.healight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.healight.domain.repository.KiwiChallengeRepository;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RequestMapping("/challenge")
@RestController

public class ChallengeController {

	@Autowired
	KiwiChallengeRepository kiwiChallengeRepository;
	
	@ApiOperation(value = "키위 챌린지 목록 조회하기.")
	@GetMapping("/kiwi")
	public Object getUser() {
		return new ResponseEntity<>(kiwiChallengeRepository.findAll(), HttpStatus.OK);
	}
}
