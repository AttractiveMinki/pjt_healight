package com.ssafy.healight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.healight.domain.entity.KiwiMission;
import com.ssafy.healight.domain.repository.KiwiChallengeRepository;
import com.ssafy.healight.domain.repository.KiwiMissionRepository;
import com.ssafy.healight.domain.repository.KiwiUserRepository;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RequestMapping("/challenge")
@RestController

public class ChallengeController {

	@Autowired
	KiwiChallengeRepository kiwiChallengeRepository;
	
	@Autowired
	KiwiMissionRepository kiwiMissionRepository;
	
	@Autowired
	KiwiUserRepository kiwiUserRepository;
	
	
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
