package com.ssafy.kiwi.model.service;

import java.util.List;

import com.ssafy.kiwi.model.domain.entity.Badge;
import com.ssafy.kiwi.model.domain.entity.KiwiChallenge;
import com.ssafy.kiwi.model.domain.entity.KiwiMission;

public interface KiwiChallengeService {

	Object getKiwiChallenge();

	Object getKiwiMission(int category, int user_id);

	Object completeKiwiMission(int category, int userId, int missionId);

	//admin-----------
	boolean makeKiwi(List<KiwiChallenge> kiwiChallenge);
	boolean makeKiwiMission(List<KiwiMission> kiwiChallenge);
	boolean makeBadge(List<Badge> badge);


}
