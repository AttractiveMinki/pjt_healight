package com.ssafy.kiwi.model.service;

import java.text.SimpleDateFormat;
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

import com.ssafy.kiwi.model.domain.entity.CertifyImage;
import com.ssafy.kiwi.model.domain.entity.ChallengeHashtag;
import com.ssafy.kiwi.model.domain.entity.MyChallenge;
import com.ssafy.kiwi.model.domain.entity.User;
import com.ssafy.kiwi.model.domain.entity.WithChallenge;
import com.ssafy.kiwi.model.domain.entity.WithChallengeHashtag;
import com.ssafy.kiwi.model.domain.repository.CertifyImageRepository;
import com.ssafy.kiwi.model.domain.repository.ChallengeHashtagRepository;
import com.ssafy.kiwi.model.domain.repository.MyChallengeRepository;
import com.ssafy.kiwi.model.domain.repository.UserRepository;
import com.ssafy.kiwi.model.domain.repository.WithChallengeHashtagRepository;
import com.ssafy.kiwi.model.domain.repository.WithChallengeRepository;
import com.ssafy.kiwi.model.dto.WithInput;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WithChallengeServiceImpl implements WithChallengeService {
	
	final private WithChallengeRepository withChallengeRepository;
	final private ChallengeHashtagRepository challengeHashtagRepository;
	final private WithChallengeHashtagRepository withChallengeHashtagRepository;
	final private MyChallengeRepository myChallengeRepository;
	final private CertifyImageRepository certifyImageRepository;
	final private UserRepository userRepository;

	//[함께 챌린지] 함께 챌린지 만들기
	@Override
	public Object makeWith(WithInput withInput) {
		
		// with_challenge 테이블에 insert
		WithChallenge withchallenge = (WithChallenge) withInput.getWithChallenge();
		int withChallengeId = withChallengeRepository.save(withchallenge).getId();
		
		// my_challenge 테이블에 insert
		MyChallenge myChallenge = MyChallenge.builder().userId(withchallenge.getUserId())
				.withChallengeId(withChallengeId) // MyChallenge 변수명 카멜 표기법으로 이후 통일
				.build();
		myChallengeRepository.save(myChallenge);

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


	//[함께 챌린지] 함께 챌린지 목록 가져오기
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

	//[함께 챌린지] Id에 해당하는 함께 챌린지 상세 정보 가져오기(소개, 인증)
	@Override
	public ResponseEntity<Map<String, Object>> getWithChallengeDetail(int withChallengeId, int userId) {
		Map<String, Object> map = new HashMap<>();
		
		Optional<WithChallenge> withChallenge = withChallengeRepository.findWithChallengeById(withChallengeId);
		map.put("withChallenge", withChallenge);
		
		// 해시태그들 담을 문자열 리스트
		ArrayList<String> hashtags = new ArrayList<>();
		
		// 해당 id에 연결되어 있는 해시태그들 가져오기
		List<WithChallengeHashtag> list = withChallengeHashtagRepository.getAllWithChallengeHashtag(withChallengeId);
		for (int i = 0, size = list.size(); i < size; i++) {
			hashtags.add(list.get(i).getChallengehashtag().getWord());
		}
		map.put("hashtags", hashtags);
		
		// 현재 로그인 한 사용자가 챌린지에 참여하고 있는지 여부 확인
		boolean participated = false;
		Optional<MyChallenge> myChallenge = myChallengeRepository.getMyChallengeByUserIdAndWithChallengeId(userId, withChallengeId);
		if (myChallenge.isPresent()) {
			participated = true;
		}
		map.put("participated", participated);
		
		// 챌린지 참가자 수 가져오기
		int participantsNum = myChallengeRepository.countByWithChallengeId(withChallengeId);
		map.put("participantsNum", participantsNum);
		
		// 키위 점수 계산하기(1일: 100 point)
		int diffDays = (int)(withChallenge.get().getEndDate().getTime() - withChallenge.get().getStartDate().getTime())/(24*60*60*1000);
		int kiwiPoint = diffDays * 100;
		map.put("kiwiPoint", kiwiPoint);
		
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}

	//[마이 챌린지] 함께 챌린지 id 리스트 가져오기
	@Override
	public List<MyChallenge> getByUserid(int userId) {
		return myChallengeRepository.getMyChallengeByUserId(userId);
	}

	//[마이 챌린지] 함께 챌린지 정보 가져오기
	@Override
	public WithChallenge getByChallengeId(int challengeId) {
		return withChallengeRepository.getById(challengeId);
	}

	//[마이 챌린지] 챌린지 목록 조회하기
	@Override
	public Object getMyChallenge(int userId) {
		//함께 챌린지 id 리스트 가져오기
		List<MyChallenge> myChallengeIdList = getByUserid(userId);
		//응답할 리스트 만들기
		List<Map<String,Object>> myChallengeList = new ArrayList<>();
		for (int i = 0; i < myChallengeIdList.size(); i++) {
			//함께 챌린지 id별로 챌린지 정보 가져오기
			int challengeId = myChallengeIdList.get(i).getWithChallengeId();
			WithChallenge nowChallenge = getByChallengeId(challengeId);
			//함께 챌린지 id별로 해시태그 정보 가져오기
			List<WithChallengeHashtag> nowHash = withChallengeHashtagRepository.getAllWithChallengeHashtag(challengeId);
			List<String> hashtagWord = new ArrayList<>();
			for (WithChallengeHashtag wch : nowHash) {
				String word = wch.getChallengehashtag().getWord();
				hashtagWord.add(word);				
			}
			//필요한 정보만 맵에 담아 리스트에 저장
			//달성률은 마이 챌린지 인증하기 개발 후 추가 예정 
			Map<String,Object> map = new HashMap<>();
			map.put("title", nowChallenge.getTitle());
			map.put("startDate", nowChallenge.getStartDate());
			map.put("endDate", nowChallenge.getEndDate());
			map.put("hashtag", hashtagWord);
			myChallengeList.add(map);
		}
		return myChallengeList;
	}


	//[마이 챌린지] 인증하기
	@Override
	public boolean certifyMyChallenge(CertifyImage certifyImage) {
		//사진 저장
		certifyImageRepository.save(certifyImage);
		//포인트 증가
		User user = userRepository.getById(certifyImage.getUserId());
		int exp = user.getExp();
		exp += 10;
		user.setExp(exp);
		userRepository.save(user);
		return true;
	}

	//[마이 챌린지] 결과보기
	@Override
	public Object resultMyChallenge(int userId, int withChallengeId) {
		Map<String, Object> map = new HashMap<>();
		//해당 챌린지 정보 가져오기
		Optional<WithChallenge> withChallenge = withChallengeRepository.findWithChallengeById(withChallengeId);
		//챌린지 달성률 : 매일 인증 가정
		long days = (withChallenge.get().getEndDate().getTime() - withChallenge.get().getStartDate().getTime()) / (24*60*60*1000);
		long totalCnt = days * withChallenge.get().getCertifyDay();
		int certifyCnt = certifyImageRepository.countByUserIdAndWithChallengeId(userId, withChallengeId);
		double achievement = (double)certifyCnt / (double)totalCnt * 100;
		map.put("achievement", Math.floor(achievement*10)/10.0);
		//획득 포인트
		if(achievement >= 85) map.put("point", withChallenge.get().getKiwiPoint());
		else map.put("point", 0);
		//인증 사진 리스트 (가공)
		List<CertifyImage> beforeImage = certifyImageRepository.getAllByUserIdAndWithChallengeId(userId, withChallengeId);
		List<Map<String, Object>> afterImage = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < beforeImage.size(); i++) {
			CertifyImage ci = beforeImage.get(i);
			Map<String, Object> imgMap = new HashMap<>();
			imgMap.put("id", ci.getId());
			imgMap.put("image", ci.getImage());
			imgMap.put("time", format.format(ci.getTime()));
			afterImage.add(imgMap);
		}
		map.put("certifyImage", afterImage);
		return map;
	}

	
}
