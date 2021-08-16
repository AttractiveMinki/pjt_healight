package com.ssafy.kiwi.model.service;

import java.util.*;

import com.ssafy.kiwi.model.domain.entity.CertifyImage;
import com.ssafy.kiwi.model.domain.entity.MyChallenge;
import com.ssafy.kiwi.model.domain.entity.WithChallenge;
import com.ssafy.kiwi.model.dto.CertifyImageOp;
import com.ssafy.kiwi.model.dto.WithChallengeIp;


public interface WithChallengeService {

	
	// 함께 챌린지 만들기 input 값들을 받아와서  with_challenge, challenge_hashtag, with_challenge_hashtag 각 테이블에 저장
	public boolean makeWith(WithChallengeIp withChallengeIp);

	// 함께 챌린지 - 목록 만들기
	public List<Map<String, Object>> getWithList(int category, int page);
	
	// Id에 해당하는 함께 챌린지 상세 정보 가져오기(소개, 인증) 
	public Map<String, Object> getWithChallengeDetail(int withChallengeId, int userId);
	
	public List<MyChallenge> getByUserid(int user_id);

	public WithChallenge getByChallengeId(int challengeId);

	public List<Map<String, Object>> getMyChallenge(int userId);

	// 함께 챌린지 참여하기
	public void joinWithChallenge(int withChallengeId, int userId);
	
	// 마이 챌린지 - 인증하기
	public boolean certifyMyChallenge(CertifyImage certifyImage);

	// 마이 챌린지 - 결과보기
	public Object resultMyChallenge(int userId, int withChallengeId);

	// 마이 챌린지 - 인증사진 모아보기
	public List<CertifyImageOp> photoMyChallenge(int userId, int withChallengeId);
}
