package com.ssafy.kiwi.model.service;

import java.util.List;

import com.ssafy.kiwi.model.domain.entity.Badge;
import com.ssafy.kiwi.model.dto.KiwiChallengeIp;
import com.ssafy.kiwi.model.dto.KiwiMissionIp;

public interface KiwiChallengeService {

	Object getKiwiChallenge();

	Object getKiwiMission(int category, int user_id);

	Object completeKiwiMission(int category, int userId, int missionId);

	//admin-----------
	boolean makeKiwi(List<KiwiChallengeIp> kiwiChallengeIp);
	boolean makeKiwiMission(List<KiwiMissionIp> kiwiMissionIp);
	boolean makeBadge(List<Badge> badge);


}
