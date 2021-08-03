package com.ssafy.kiwi.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ssafy.kiwi.model.domain.entity.KiwiMission;
import com.ssafy.kiwi.model.domain.entity.KiwiUser;
import com.ssafy.kiwi.model.domain.repository.KiwiChallengeRepository;
import com.ssafy.kiwi.model.domain.repository.KiwiMissionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class KiwiChallengeServiceImpl implements KiwiChallengeService {
	
	final private KiwiChallengeRepository kiwiChallengeRepository;
	final private KiwiMissionRepository kiwiMissionRepository;

	
	//키위 챌린지 목록 조회
	@Override
	public Object getKiwiChallenge() {
		return new ResponseEntity<>(kiwiChallengeRepository.findAll(), HttpStatus.OK);
	}
	
	//키위 챌린지 상세 조회
	@Override
	public Object getKiwiMission(int category, int userId) {
		//미션 목록 가져오기
		List<KiwiMission> kiwi_mission = kiwiMissionRepository.getMissionByCategory(category);
		
		List<Map<String,Object>> missions = new ArrayList<>();
		//각 미션별 체크
		for (KiwiMission km : kiwi_mission) {
			Map<String, Object> mission = new HashMap<>();
			mission.put("id", km.getId());
			mission.put("content", km.getContent());
			mission.put("badgeId", km.getBadge_id());
			
			//userId에 맞는 미션 성공 여부 찾기
			Collection<KiwiUser> kiwi_user = km.getKiwiUser();
			for(KiwiUser ku : kiwi_user) {
				if(ku.getUserId()==userId) {
					mission.put("completeDate", ku.getCompleteDate());
					break;
				}
			}
			missions.add(mission);
		}
		return new ResponseEntity<>(missions, HttpStatus.OK);
	}
	
}
