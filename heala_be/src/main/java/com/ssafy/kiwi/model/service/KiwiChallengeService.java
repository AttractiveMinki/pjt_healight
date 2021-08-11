package com.ssafy.kiwi.model.service;

import java.util.List;

import com.ssafy.kiwi.model.domain.entity.KiwiMission;

public interface KiwiChallengeService {

	Object getKiwiChallenge();

	Object getKiwiMission(int category, int user_id);

	Object completeKiwiMission(int category, int userId, int missionId);

	boolean makeKiwi(List<KiwiMission> kiwiChallenge);

}
