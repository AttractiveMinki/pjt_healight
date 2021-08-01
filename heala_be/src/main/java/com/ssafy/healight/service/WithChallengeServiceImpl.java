package com.ssafy.healight.service;

import java.util.Optional;
import java.util.StringTokenizer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ssafy.healight.domain.entity.ChallengeHashtag;
import com.ssafy.healight.domain.entity.WithChallenge;
import com.ssafy.healight.domain.entity.WithChallengeHashtag;
import com.ssafy.healight.domain.entity.WithInput;
import com.ssafy.healight.domain.repository.ChallengeHashtagRepository;
import com.ssafy.healight.domain.repository.WithChallengeHashtagRepository;
import com.ssafy.healight.domain.repository.WithChallengeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WithChallengeServiceImpl implements WithChallengeService {
	
	final private WithChallengeRepository withChallengeRepository;
	final private ChallengeHashtagRepository challengeHashtagRepository;
	final private WithChallengeHashtagRepository withChallengeHashtagRepository;
	

	@Override
	public Object makeWith(WithInput withInput) {
		
		// with_challenge 테이블에 insert
		WithChallenge withchallenge = (WithChallenge) withInput.getWithChallenge();
		int withChallengeId = withChallengeRepository.save(withchallenge).getId();
		
		// challenge_hashtag 테이블에 insert
		String hashtags = withInput.getChallengeHashtag().getWord();
		StringTokenizer st = new StringTokenizer(hashtags," ");
		
		int challengeHashtagId;
		while(st.hasMoreTokens()) {
			String hashtag = st.nextToken().replace("#", "");
			Optional<ChallengeHashtag> checkHashtag = challengeHashtagRepository.getChallengeHashtagByWord(hashtag);
			if(!checkHashtag.isPresent()){
				ChallengeHashtag challengeHashtag = new ChallengeHashtag(hashtag);
				challengeHashtagId = challengeHashtagRepository.save(challengeHashtag).getId();
			} else {
				challengeHashtagId = checkHashtag.get().getId();
			}
			
			// with_challenge_hashtag 테이블에 각 id 값 insert
			WithChallengeHashtag withChallengeHashtag = WithChallengeHashtag.builder()
														.withChallengeId(withChallengeId)
														.challengeHashtagId(challengeHashtagId).build();
			withChallengeHashtagRepository.save(withChallengeHashtag);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
