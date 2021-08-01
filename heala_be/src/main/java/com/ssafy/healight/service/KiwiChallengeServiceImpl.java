package com.ssafy.healight.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ssafy.healight.domain.entity.KiwiMission;
import com.ssafy.healight.domain.repository.KiwiChallengeRepository;
import com.ssafy.healight.domain.repository.KiwiMissionRepository;
import com.ssafy.healight.domain.repository.KiwiUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class KiwiChallengeServiceImpl implements KiwiChallengeService {
	
	final private KiwiChallengeRepository kiwiChallengeRepository;
	final private KiwiMissionRepository kiwiMissionRepository;
	final private KiwiUserRepository kiwiUserRepository;

	
	//키위 챌린지 목록 조회
	@Override
	public Object getKiwiChallenge() {
		return new ResponseEntity<>(kiwiChallengeRepository.findAll(), HttpStatus.OK);
	}
	
	//키위 챌린지 상세 조회
	@Override
	public Object getKiwiMission(int category, int user_id) {
		List<KiwiMission> kiwi_mission = kiwiMissionRepository.getMissionByCategory(category);
//		List<KiwiMission> result_mission = kiwiMissionRepository.getAllWithUser(category, user_id);
//		return new ResponseEntity<>(result_mission, HttpStatus.OK);
		return new ResponseEntity<>(kiwiMissionRepository.getMissionByCategory(category), HttpStatus.OK);
	}

	
	
	
}
