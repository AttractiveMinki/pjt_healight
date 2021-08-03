package com.ssafy.kiwi.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.kiwi.model.domain.entity.MyChallenge;
import com.ssafy.kiwi.model.domain.entity.WithChallenge;
import com.ssafy.kiwi.model.dto.WithInput;

import org.springframework.http.ResponseEntity;


public interface WithChallengeService {

	/** 함께 챌린지 만들기 input 값들을 받아와서 
	    with_challenge, challenge_hashtag, with_challenge_hashtag 각 테이블에 저장
	*/
	public Object makeWith(WithInput withInput);

	public List<MyChallenge> getByUserid(int user_id);

	public WithChallenge getByChallengeId(int challengeId);
	
	public ResponseEntity<List<Map<String,Object>>> getWithList(int category);

	public Object getMyChallenge(int userId);
}
