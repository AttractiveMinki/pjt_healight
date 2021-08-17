package com.ssafy.kiwi.model.service;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import com.ssafy.kiwi.model.dto.CertifyImageOp;
import com.ssafy.kiwi.model.dto.CertifyImageSimpleOp;
import com.ssafy.kiwi.model.dto.WithChallengeIp;

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
	public boolean makeWith(WithChallengeIp withChallengeIp) {
		
		// with_challenge 테이블에 insert
		WithChallenge withchallenge = (WithChallenge) withChallengeIp.getWithChallenge();
		
		// 키위 점수 계산하기(1일: 100 point)
		int diffDays = (int)((withchallenge.getEndDate().getTime() - withchallenge.getStartDate().getTime())/(24*60*60*1000));
		int kiwiPoint = diffDays * 100;
		withchallenge.setKiwiPoint(kiwiPoint);

		int withChallengeId = withChallengeRepository.save(withchallenge).getId();
		
		// my_challenge 테이블에 insert
		MyChallenge myChallenge = MyChallenge.builder().userId(withchallenge.getUserId())
				.withChallengeId(withChallengeId) // MyChallenge 변수명 카멜 표기법으로 이후 통일
				.build();
		myChallengeRepository.save(myChallenge);

		// hashtag가 있는 경우 challenge_hashtag 테이블에 insert
		if (withChallengeIp.getChallengeHashtag() != null) {
			String hashtags = withChallengeIp.getChallengeHashtag().getWord();
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
		}
		
		return true;
	}

	//[함께 챌린지] 함께 챌린지 목록 가져오기
	@Override
	public List<Map<String, Object>> getWithList(int category, int page) {
		// 반환할 Map<String, Object> 리스트 생성
		// "withChallenge", "hashtags"
		List<Map<String,Object>> response = new LinkedList<Map<String,Object>>();
		Map<String,Object> map; 
		
		// 카테고리에 해당하는 함께 챌린지 목록 가져오기
		Page<WithChallenge> withChallengePage= withChallengeRepository.getByCategory(category, PageRequest.of(page, 10, Sort.by("createdAt").descending()));
		List<WithChallenge> withChallengeList = withChallengePage.getContent();
		
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
		return response;
	}
	
	//[함께 챌린지] Id에 해당하는 함께 챌린지 상세 정보 가져오기(소개, 인증)
	@Override
	public Map<String, Object> getWithChallengeDetail(int withChallengeId, int userId) {
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
		
		// 참가자 인증사진 날짜별로 가져오기
		map.put("certifyImage", photoWithChallenge(withChallengeId));
		
		return map;
	}

	
	//[함께 챌린지] 참가하기
	@Override
	public void joinWithChallenge(int withChallengeId, int userId) {
		MyChallenge mychallenge = MyChallenge.builder().withChallengeId(withChallengeId).userId(userId).build();
		myChallengeRepository.save(mychallenge);
	}
	
	//[함께 챌린지] 인증사진 모아보기
	public List<CertifyImageOp> photoWithChallenge(int withChallengeId) {
		//가공 전 리스트 CertifyImage, 가공 후 리스트 CertifyImageOp
		List<CertifyImage> beforeImage = certifyImageRepository.getAllByWithChallengeIdOrderByTimeDesc(withChallengeId);
		List<CertifyImageOp> afterImage = new ArrayList<>();
		//날짜 변환
		SimpleDateFormat format = new SimpleDateFormat ("yyyy/MM/dd");			
				
		if(!beforeImage.isEmpty()) {
			//가공 후 리스트에 넣을 객체 cio
			CertifyImageOp cio = new CertifyImageOp();
			//cio의 날짜
			cio.setDate(format.format(beforeImage.get(0).getTime()));
			//cio의 사진 정보 리스트 cisoList
			List<CertifyImageSimpleOp> cisoList = new ArrayList<>();
			for (CertifyImage ci : beforeImage) {
				//같은 날짜의 cio인 경우, cisoList에 추가하기
				if(format.format(ci.getTime()).equals(cio.getDate())) {
					cisoList.add(new CertifyImageSimpleOp(ci.getId(), ci.getImage()));
				}
				//다른 날짜의 cio인 경우
				else {
					cio.setList(cisoList);  //cio에 사진 정보 리스트 cisoList 저장 후
					afterImage.add(cio);  //이전 cio는 리스트에 넣기
					//새 cio 만들고 날짜 지정, 사진 정보 리스트 새로 만들기.
					cio = new CertifyImageOp();
					cio.setDate(format.format(ci.getTime()));
					cisoList = new ArrayList<>();
					cisoList.add(new CertifyImageSimpleOp(ci.getId(), ci.getImage()));
				}
			}
			//마지막 정보 담기
			cio.setList(cisoList);  //cio에 사진 정보 리스트 cisoList 저장 후
			afterImage.add(cio);  //이전 cio는 리스트에 넣기
		}
		return afterImage;
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
	public List<Map<String, Object>> getMyChallenge(int userId) {
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
			Map<String,Object> map = new HashMap<>();
			map.put("id", nowChallenge.getId());
			map.put("category", nowChallenge.getCategory());
			map.put("title", nowChallenge.getTitle());
			map.put("startDate", nowChallenge.getStartDate());
			map.put("endDate", nowChallenge.getEndDate());
			map.put("hashtag", hashtagWord);
			
			//챌린지 달성률 : 매일 인증 가정
			long days = (nowChallenge.getEndDate().getTime() - nowChallenge.getStartDate().getTime()) / (24*60*60*1000);
			long totalCnt = days * nowChallenge.getCertifyDay();
			int certifyCnt = certifyImageRepository.countByUserIdAndWithChallengeId(userId, challengeId);
			double achievement = (double)certifyCnt / (double)totalCnt * 100;
			map.put("achievement", Math.floor(achievement*10)/10.0);
			
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
		System.out.println("days" + days);
		long totalCnt = days * withChallenge.get().getCertifyDay();
		System.out.println(totalCnt);
		int certifyCnt = certifyImageRepository.countByUserIdAndWithChallengeId(userId, withChallengeId);
		System.out.println("certifyCnt" + certifyCnt);
		double achievement = (double)certifyCnt / (double)totalCnt * 100;
		map.put("achievement", Math.floor(achievement*10)/10.0);
		//획득 포인트
		if(achievement >= 85) map.put("point", withChallenge.get().getKiwiPoint());
		else map.put("point", 0);
		//인증 사진 리스트 (가공)
		List<CertifyImage> beforeImage = certifyImageRepository.getAllByUserIdAndWithChallengeId(userId, withChallengeId);
		List<Map<String, Object>> afterImage = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
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

	//[마이 챌린지] 인증사진 모아보기
	@Override
	public List<CertifyImageOp> photoMyChallenge(int userId, int withChallengeId) {
		//가공 전 리스트 CertifyImage, 가공 후 리스트 CertifyImageOp
		List<CertifyImage> beforeImage = certifyImageRepository.getAllByUserIdAndWithChallengeIdOrderByTimeDesc(userId, withChallengeId);
		List<CertifyImageOp> afterImage = new ArrayList<>();
		//날짜 변환
		SimpleDateFormat format = new SimpleDateFormat ("yyyy/MM/dd");			
		
		if(!beforeImage.isEmpty()) {
			//가공 후 리스트에 넣을 객체 cio
			CertifyImageOp cio = new CertifyImageOp();
			//cio의 날짜
			cio.setDate(format.format(beforeImage.get(0).getTime()));
			//cio의 사진 정보 리스트 cisoList
			List<CertifyImageSimpleOp> cisoList = new ArrayList<>();
			for (CertifyImage ci : beforeImage) {
				//같은 날짜의 cio인 경우, cisoList에 추가하기
				if(format.format(ci.getTime()).equals(cio.getDate())) {
					cisoList.add(new CertifyImageSimpleOp(ci.getId(), ci.getImage()));
				}
				//다른 날짜의 cio인 경우
				else {
					cio.setList(cisoList);  //cio에 사진 정보 리스트 cisoList 저장 후
					afterImage.add(cio);  //이전 cio는 리스트에 넣기
					//새 cio 만들고 날짜 지정, 사진 정보 리스트 새로 만들기.
					cio = new CertifyImageOp();
					cio.setDate(format.format(ci.getTime()));
					cisoList = new ArrayList<>();
					cisoList.add(new CertifyImageSimpleOp(ci.getId(), ci.getImage()));
				}
			}
			//마지막 정보 담기
			cio.setList(cisoList);  //cio에 사진 정보 리스트 cisoList 저장 후
			afterImage.add(cio);  //이전 cio는 리스트에 넣기
		}
		return afterImage;
	}
	
}
