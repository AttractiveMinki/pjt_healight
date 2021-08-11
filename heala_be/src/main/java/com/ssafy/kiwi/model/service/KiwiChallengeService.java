package com.ssafy.kiwi.model.service;

public interface KiwiChallengeService {

	Object getKiwiChallenge();

	Object getKiwiMission(int category, int user_id);

	Object completeKiwiMission(int category, int userId, int missionId);

}
