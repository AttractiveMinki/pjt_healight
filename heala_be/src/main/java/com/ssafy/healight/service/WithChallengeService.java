package com.ssafy.healight.service;

import com.ssafy.healight.domain.entity.WithInput;

public interface WithChallengeService {

	/** 함께 챌린지 만들기 input 값들을 받아와서 
	    with_challenge, challenge_hashtag, with_challenge_hashtag 각 테이블에 저장
	*/
	public Object makeWith(WithInput withInput);
	
}
