package com.ssafy.healight.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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


	@Override
	public ResponseEntity<List<Map<String,Object>>> getWithList(int category) {
		
		// 반환할 Map<String, Object> 리스트 생성
		// "withChallenge", "hashtags"
		List<Map<String,Object>> response = new LinkedList<Map<String,Object>>();
		Map<String,Object> map; 
		
		// 카테고리에 해당하는 함께 챌린지 목록 가져오기
		List<WithChallenge> withChallengeList = withChallengeRepository.getByCategory(category);
		
		for (int i = 0, size=withChallengeList.size(); i < size; i++) {
			map = new HashMap<String, Object>();
			
			WithChallenge withchallenge = withChallengeList.get(i);
			map.put("withChallenge", withchallenge); // withChallenge 객체 한 개 Map에 저장
			
			int withChallengeId = withchallenge.getId(); // 해당 withChallenge의 id 가져오기
			
			// 해시태그들 담을 문자열 리스트
			ArrayList<String> hashtags = new ArrayList<String>();
			
			// 해당 id에 연결되어 있는 해시태그들 가져오기
			List<WithChallengeHashtag> list = withChallengeHashtagRepository.getAllWithChallengeHashtag(withChallengeId);
			for (int j = 0, size2=list.size(); j < size2; j++) {
				hashtags.add(list.get(j).getChallengehashtag().getWord()); // 리스트에 해시태그 하나씩 추가
			}
			map.put("hashtags", hashtags); // withChallenge에 연결된 해시 태그들 리스트 Map에 저장
			
			response.add(map);
		}
		
		return new ResponseEntity<List<Map<String,Object>>>(response, HttpStatus.OK);
	}
}
