package com.ssafy.kiwi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kiwi.model.domain.entity.MyChallengeList;
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
		return withChallengeService.makeWith(withInput);

	}
	
	@ApiOperation(value = "함께 챌린지 목록 가져오기")
	@GetMapping("/with")
	public ResponseEntity<List<Map<String,Object>>> getWithList(@RequestParam int category) {
		return withChallengeService.getWithList(category);

	}
	
	@ApiOperation(value = "함께 챌린지 상세 정보 가져오기")
	@GetMapping("/with/detail")
	public ResponseEntity<Map<String,Object>> getWithChallenge(@RequestParam int id) {
		return withChallengeService.getWithChallenge(id);
	}
	
	@ApiOperation(value = "키위 챌린지 목록 조회하기.")
	@GetMapping("/kiwi")
	public Object getKiwiChallenge() {
		return kiwiChallengeService.getKiwiChallenge();
	}
	
	@ApiOperation(value = "키위 챌린지 상세 미션 조회하기.")
	@GetMapping("/kiwi/{category}/{user_id}")
	public Object getKiwiMission(@PathVariable int category, @PathVariable int user_id) {
		return kiwiChallengeService.getKiwiMission(category, user_id);
	}
	
	@ApiOperation(value = "마이 챌린지 목록 조회하기.")
	@GetMapping("/my/{user_id}")
	public Object getMyChallenge(@PathVariable int user_id) {
		List<Integer> myChallengeIdList = withChallengeService.getByUserid(user_id);
		List<MyChallengeList> myChallengeList = new ArrayList<>();
		for (int i = 0; i < myChallengeIdList.size(); i++) {
			int challengeId = myChallengeIdList.get(i);
			MyChallengeList mcl = new MyChallengeList();
			mcl.setWithChallenge(withChallengeService.getByChallengeId(challengeId));
			myChallengeList.add(mcl);
		}
		return myChallengeList;
	}
	
}
