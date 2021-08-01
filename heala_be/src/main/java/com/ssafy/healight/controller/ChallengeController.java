package com.ssafy.healight.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.healight.domain.entity.WithInput;
import com.ssafy.healight.service.WithChallengeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ssafy.healight.domain.entity.KiwiMission;
import com.ssafy.healight.domain.entity.WithChallengeHashtag;
import com.ssafy.healight.domain.repository.KiwiChallengeRepository;
import com.ssafy.healight.domain.repository.KiwiMissionRepository;
import com.ssafy.healight.domain.repository.KiwiUserRepository;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequestMapping("/challenge")
@RestController
@RequiredArgsConstructor
public class ChallengeController {
	
	final private WithChallengeService withChallengeService;
	
	@Autowired
	KiwiChallengeRepository kiwiChallengeRepository;
	
	@Autowired
	KiwiMissionRepository kiwiMissionRepository;
	
	@Autowired
	KiwiUserRepository kiwiUserRepository;
	
	
	@ApiOperation(value = "함께 챌린지 만들기")
	@PostMapping("/with")
	public Object makeWith(@RequestBody WithInput withInput) {
		return withChallengeService.makeWith(withInput);

	}
	
	@ApiOperation(value = "함께 챌린지 목록 가져오기")
	@GetMapping("/with")
	public ResponseEntity<List<Map<String,Object>>> getWithList(@RequestParam int category) {
		return withChallengeService.getWithList(category);

	}
	
	
	
	@ApiOperation(value = "키위 챌린지 목록 조회하기.")
	@GetMapping("/kiwi")
	public Object getKiwiChallenge() {
		return new ResponseEntity<>(kiwiChallengeRepository.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "키위 챌린지 상세 미션 조회하기.")
	@GetMapping("/kiwi/{category}/{user_id}")
	public Object getKiwiMission(@PathVariable int category, @PathVariable int user_id) {
		List<KiwiMission> kiwi_mission = kiwiMissionRepository.getMissionByCategory(category);
		return new ResponseEntity<>(kiwiMissionRepository.getMissionByCategory(category), HttpStatus.OK);
	}
}
