package com.ssafy.kiwi.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.kiwi.model.domain.entity.CertifyImage;
import com.ssafy.kiwi.model.domain.entity.MyChallenge;
import com.ssafy.kiwi.model.domain.entity.WithChallenge;
import com.ssafy.kiwi.model.dto.WithInput;

import org.springframework.http.ResponseEntity;


public interface WithChallengeService {

	
	// 함께 챌린지 만들기 input 값들을 받아와서  with_challenge, challenge_hashtag, with_challenge_hashtag 각 테이블에 저장
	public Object makeWith(WithInput withInput);

	public List<MyChallenge> getByUserid(int user_id);

	public WithChallenge getByChallengeId(int challengeId);
	
	public ResponseEntity<List<Map<String,Object>>> getWithList(int category);

	public Object getMyChallenge(int userId);
	
	// Id에 해당하는 함께 챌린지 상세 정보 가져오기(소개, 인증) 
	public ResponseEntity<Map<String,Object>> getWithChallengeDetail(int withChallengeId, int userId);

	// 마이 챌린지 - 인증하기
	public boolean certifyMyChallenge(CertifyImage certifyImage);

	// 마이 챌린지 - 결과보기
	public Object resultMyChallenge(int userId, int withChallengeId);
	
	// 함께 챌린지 참여하기
	public void joinWithChallenge(int withChallengeId, int userId);
}
