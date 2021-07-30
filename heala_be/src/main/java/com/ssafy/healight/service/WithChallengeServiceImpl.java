package com.ssafy.healight.service;

import java.util.Optional;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class WithChallengeServiceImpl implements WithChallengeService {
	
	@Autowired
	private WithChallengeRepository withChallengeRepository;
	
	@Autowired
	private ChallengeHashtagRepository challengeHashtagRepository;
	
	@Autowired
	private WithChallengeHashtagRepository withChallengeHashtagRepository;
	

	@Override
	public Object post(WithInput withInput) {
		
		int challengeId;
		int hashtagId;
		
		boolean flag = true;
		
		// withchallenge에 내용 저장
		WithChallenge withChallenge = new WithChallenge(withInput.getImage(), withInput.getTitle(), withInput.getCategory(), 
				withInput.getStart_date(), withInput.getEnd_date(), withInput.getCertify_info(), withInput.getIntroduction(), withInput.getUser_id());
		Optional<WithChallenge> checkChallengeHashtag = withChallengeRepository.getWithChallengeByTitleAndCategoryAndStartDateAndEndDateAndCertifyInfoAndIntroduction(withInput.getTitle(), 
				withInput.getCategory(), withInput.getStart_date(), withInput.getEnd_date(), withInput.getCertify_info(), withInput.getIntroduction());
		
		if (!checkChallengeHashtag.isPresent()) {
//			System.out.println("여기까지 들어오나요?"); -> 안들어옴 그럼 else로 들어가서 bad_request여야 하는데?!
			withChallengeRepository.save(withChallenge);
			
			// 가장 최근에 삽입한 객체의 id 알아내기
			checkChallengeHashtag = withChallengeRepository.getWithChallengeByTitleAndCategoryAndStartDateAndEndDateAndCertifyInfoAndIntroduction(withInput.getTitle(), 
					withInput.getCategory(), withInput.getStart_date(), withInput.getEnd_date(), withInput.getCertify_info(), withInput.getIntroduction());
			challengeId = checkChallengeHashtag.get().getId();
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// challengehashtag에 내용 저장
		ChallengeHashtag challengeHashtag = null; 

		String hashtags = withInput.getHashtags();
		StringTokenizer st = new StringTokenizer(hashtags," ");
		System.out.println("test");
		
		String hashtag;
		while (st.hasMoreTokens()) {
			hashtag = st.nextToken().replace("#", "");
			Optional<ChallengeHashtag> checkHashtag = challengeHashtagRepository.getChallengeHashtagByWord(hashtag);
			if(!checkHashtag.isPresent()){
				challengeHashtag = new ChallengeHashtag(hashtag);
				System.out.println(hashtag.toString());
				challengeHashtagRepository.save(challengeHashtag);
				
				// 가장 최근에 삽입한 객체의 id 알아내기
				checkHashtag = challengeHashtagRepository.getChallengeHashtagByWord(hashtag);
				hashtagId = checkHashtag.get().getId();
				
				
				System.out.println(challengeId);
				System.out.println(hashtagId);
				
				// 관계 테이블에 저장
				WithChallengeHashtag withChallengeHashtag = new WithChallengeHashtag(challengeId, hashtagId);
				withChallengeHashtagRepository.save(withChallengeHashtag);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
