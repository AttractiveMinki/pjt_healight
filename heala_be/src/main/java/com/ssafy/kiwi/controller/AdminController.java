package com.ssafy.kiwi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kiwi.model.domain.entity.Badge;
import com.ssafy.kiwi.model.dto.KiwiChallengeIp;
import com.ssafy.kiwi.model.dto.KiwiMissionIp;
import com.ssafy.kiwi.model.service.KiwiChallengeService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/admin")
@RestController
public class AdminController {
	
	final private KiwiChallengeService kiwiChallengeService;
	
	@ApiOperation(value = "키위 챌린지 추가하기.")
	@PostMapping("/kiwi/challenge")
	public Object makeKiwi(@RequestBody List<KiwiChallengeIp> kiwiChallengeIp) {
		if(kiwiChallengeService.makeKiwi(kiwiChallengeIp)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
	}
	
	@ApiOperation(value = "키위 챌린지 미션 추가하기.")
	@PostMapping("/kiwi/mission")
	public Object makeKiwiMission(@RequestBody List<KiwiMissionIp> kiwiMissionIp) {
		if(kiwiChallengeService.makeKiwiMission(kiwiMissionIp)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
	}
	
	@ApiOperation(value = "배지 추가하기.")
	@PostMapping("/badge")
	public Object makeBadge(@RequestBody List<Badge> badge) {
		if(kiwiChallengeService.makeBadge(badge)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
	}
}
