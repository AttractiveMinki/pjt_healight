package com.ssafy.kiwi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kiwi.model.domain.entity.CertifyImage;
import com.ssafy.kiwi.model.dto.WithInput;
import com.ssafy.kiwi.model.service.KiwiChallengeService;
import com.ssafy.kiwi.model.service.WithChallengeService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/challenge")
@RestController
public class ChallengeController {
	
	final private KiwiChallengeService kiwiChallengeService;
	final private WithChallengeService withChallengeService;

	
	@ApiOperation(value = "함께 챌린지 만들기")
	@PostMapping("/with")
	public Object makeWith(@RequestBody WithInput withInput) {
		if(withChallengeService.makeWith(withInput)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}
	
	@ApiOperation(value = "함께 챌린지 목록 가져오기")
	@GetMapping("/with")
	public ResponseEntity<List<Map<String,Object>>> getWithList(@RequestParam int category) {
		return new ResponseEntity<>(withChallengeService.getWithList(category), HttpStatus.OK);
	}
	
	@ApiOperation(value = "함께 챌린지 상세 정보 가져오기")
	@GetMapping("/with/detail")
	public ResponseEntity<Map<String,Object>> getWithChallengeDetail(@RequestParam int withChallengeId, @RequestParam int userId) {
		return new ResponseEntity<>(withChallengeService.getWithChallengeDetail(withChallengeId, userId),
				HttpStatus.OK);
	}
	
	@ApiOperation(value = "함께 챌린지 참여하기")
	@PostMapping("/with/join")
	public Object joinWithChallenge(@RequestParam int withChallengeId, @RequestParam int userId) {
		withChallengeService.joinWithChallenge(withChallengeId, userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "키위 챌린지 목록 조회하기.")
	@GetMapping("/kiwi")
	public Object getKiwiChallenge() {
		return kiwiChallengeService.getKiwiChallenge();
	}
	
	@ApiOperation(value = "키위 챌린지 상세 미션 조회하기.")
	@GetMapping("/kiwi/{category}/{userId}")
	public Object getKiwiMission(@PathVariable int category, @PathVariable int userId) {
		return kiwiChallengeService.getKiwiMission(category, userId);
	}
	
	@ApiOperation(value = "마이 챌린지 목록 조회하기.")
	@GetMapping("/my/{userId}")
	public Object getMyChallenge(@PathVariable int userId) {
		return new ResponseEntity<>(withChallengeService.getMyChallenge(userId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "마이 챌린지 인증하기.")
	@PostMapping("/my/certify")
	public ResponseEntity<Object> certifyMyChallenge(@RequestBody CertifyImage certifyImage) {
		if(withChallengeService.certifyMyChallenge(certifyImage)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@ApiOperation(value = "마이 챌린지 결과 조회하기.")
	@GetMapping("/my/result")
	public Object resultMyChallenge(@RequestParam(value="userId", required=true) int userId,
			@RequestParam(value="withChallengeId", required=true) int withChallengeId) {
		return withChallengeService.resultMyChallenge(userId, withChallengeId);
	}
}
