package com.ssafy.kiwi.model.service;

import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ssafy.kiwi.model.domain.entity.KiwiMission;
import com.ssafy.kiwi.model.domain.entity.KiwiUser;
import com.ssafy.kiwi.model.domain.repository.KiwiChallengeRepository;
import com.ssafy.kiwi.model.domain.repository.KiwiMissionRepository;
import com.ssafy.kiwi.model.domain.repository.KiwiUserRepository;

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
	public Object getKiwiMission(int category, int userId) {
		List<KiwiMission> kiwi_mission = kiwiMissionRepository.getMissionByCategory(category);
		for (KiwiMission km : kiwi_mission) {
			Collection<KiwiUser> kiwi_user = km.getKiwiUser();
			while(true) {
				int size = kiwi_user.size();
				int cnt = 0;
				for(KiwiUser ku : kiwi_user) {
					cnt++;
					if(ku.getUserId()!=userId) {
						kiwi_user.remove(ku);
						break;
					}
				}
				if(cnt==size) break;
			}
		}
		return new ResponseEntity<>(kiwi_mission, HttpStatus.OK);
	}
	
}
