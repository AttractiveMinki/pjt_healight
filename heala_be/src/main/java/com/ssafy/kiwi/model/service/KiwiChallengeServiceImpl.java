package com.ssafy.kiwi.model.service;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.kiwi.model.domain.entity.Badge;
import com.ssafy.kiwi.model.domain.entity.Comment;
import com.ssafy.kiwi.model.domain.entity.KiwiChallenge;
import com.ssafy.kiwi.model.domain.entity.KiwiMission;
import com.ssafy.kiwi.model.domain.entity.KiwiUser;
import com.ssafy.kiwi.model.domain.entity.Post;
import com.ssafy.kiwi.model.domain.entity.User;
import com.ssafy.kiwi.model.domain.entity.UserBadge;
import com.ssafy.kiwi.model.domain.repository.BadgeRepository;
import com.ssafy.kiwi.model.domain.repository.CommentRepository;
import com.ssafy.kiwi.model.domain.repository.CommunityRepository;
import com.ssafy.kiwi.model.domain.repository.KiwiChallengeRepository;
import com.ssafy.kiwi.model.domain.repository.KiwiMissionRepository;
import com.ssafy.kiwi.model.domain.repository.KiwiUserRepository;
import com.ssafy.kiwi.model.domain.repository.UserBadgeRepository;
import com.ssafy.kiwi.model.domain.repository.UserRepository;
import com.ssafy.kiwi.model.dto.KiwiChallengeIp;
import com.ssafy.kiwi.model.dto.KiwiMissionIp;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class KiwiChallengeServiceImpl implements KiwiChallengeService {
	
	final private KiwiChallengeRepository kiwiChallengeRepository;
	final private KiwiMissionRepository kiwiMissionRepository;
	final private KiwiUserRepository kiwiUserRepository;
	final private UserRepository userRepository;
	final private CommentRepository commentRepository;
	final private CommunityRepository communityRepository;
	final private BadgeRepository badgeRepository;
	final private UserBadgeRepository userBadgeRepository;

	final private WithChallengeService withChallengeService;
	
	//키위 챌린지 목록 조회
	@Override
	public Object getKiwiChallenge() {
		return kiwiChallengeRepository.findAll();
	}
	
	//키위 챌린지 상세 조회
	@Override
	public Object getKiwiMission(int category, int userId) {
		//미션 목록 가져오기
		List<KiwiMission> kiwi_mission = kiwiMissionRepository.getMissionByCategory(category);
		
		List<Map<String,Object>> missions = new ArrayList<>();
		//각 미션별 체크
		for (KiwiMission km : kiwi_mission) {
			Map<String, Object> mission = new HashMap<>();
			mission.put("id", km.getId());
			mission.put("content", km.getContent());
			mission.put("badgeId", km.getBadge_id());
			
			//userId에 맞는 미션 성공 여부 찾기
			boolean complete = false;
			Collection<KiwiUser> kiwi_user = km.getKiwiUser();
			for(KiwiUser ku : kiwi_user) {
				if(ku.getUserId()==userId) {
					mission.put("completeDate", ku.getCompleteDate());
					complete = true;
					break;
				}
			}
			if(!complete) mission.put("completeDate", null);
			missions.add(mission);
		}
		return missions;
	}

	//키위 챌린지 미션 성공 여부 확인
	@Transactional
	@Override
	public Object completeKiwiMission(int category, int userId, int missionId) {
		if(missionId <= 30) {
			if(missionId%10==1) { //게시글 1개 작성
				if(missionWrite(category, userId, missionId, 1)) {
					return completed(userId, missionId);
				}
			}
			else if(missionId%10==2) { //댓글 5개 작성
				if(missionComment(category, userId, missionId, 5)) {
					return completed(userId, missionId);
				}
			}
			else if(missionId%10==3) { //챌린지 1회 참가
				if(missionChallengeJoin(category, userId, missionId, 1)) {
					return completed(userId, missionId);
				}
			}
			else if(missionId%10==4) { //챌린지 1회 달성
				if(missionChallengeCompleted(category, userId, missionId, 1)) {
					return completed(userId, missionId);
				}
			}
			else if(missionId%10==5) { //한 게시글에 좋아요 15개 받기
				if(missionLike(category, userId, missionId, 15)) {
					return completed(userId, missionId);
				}
			}
			else if(missionId%10==6) { //정보 게시글 5개 작성
				if(missionWriteInfo(category, userId, missionId, 1, 5)) {
					return completed(userId, missionId);
				}
			}
			else if(missionId%10==7) { //게시글 10개 작성
				if(missionWrite(category, userId, missionId, 10)) {
					return completed(userId, missionId);
				}
			}
			else if(missionId%10==8) { //전체 게시글 좋아요 총합 100개 받기
				if(missionLikeTotal(category, userId, missionId, 100)) {
					return completed(userId, missionId);
				}
			}
			else if(missionId%10==9) { //챌린지 3회 달성
				if(missionChallengeCompleted(category, userId, missionId, 3)) {
					return completed(userId, missionId);
				}
			}
			else if(missionId%10==0){//게시글 25개 작성
				if(missionWrite(category, userId, missionId, 25)) {
					return completed(userId, missionId);
				}
			}
			else return false;
		}
		return null;
	}
	

	//미션 성공시 Response 값
	@Transactional
	private Object completed(int userId, int missionId) {
		Map<String, Object> mission = new HashMap<>();
		//완료날짜 담기
		KiwiUser ku = kiwiUserRepository.getByUserIdAndMissionId(userId, missionId);
		mission.put("completeDate", ku.getCompleteDate());
		//획득 포인트 담기
		KiwiMission km = kiwiMissionRepository.getById(missionId);
		int point = km.getPoint();
		mission.put("point", point);
		//배지 획득 가능한 경우
		if(km.getBadge_id()!=null && km.getBadge_id()>0) {
			//배지 정보 담기 (id, 이름, 이미지)
			Badge badge = badgeRepository.getById(km.getBadge_id());
			mission.put("badgeImage", badge.getImage());
			mission.put("badgeName", badge.getName());
			UserBadge ub = new UserBadge();
			ub.setSelected(false);
			ub.setBadge_id(km.getBadge_id());
			ub.setUser_id(userId);
			userBadgeRepository.save(ub);
		}
		//포인트 적립
		User user = userRepository.getById(userId);
		int exp = user.getExp() + point;
		user.setExp(exp);
		userRepository.save(user);
		return mission;
	}
	
	//미션 성공시 mission_user 테이블에 성공 내역 저장
	@Transactional
	private boolean saveMissionUser(int missionId, int userId) {
		KiwiUser missionUser = new KiwiUser();
		missionUser.setMissionId(missionId);
		missionUser.setUserId(userId);
		kiwiUserRepository.save(missionUser);
		return true;
	}

	//미션 성공 여부 확인 - 작성 게시글 수
	private boolean missionWrite(int category, int userId, int missionId, int num) {
		long cnt = communityRepository.countByCategoryAndUserId(category, userId);
		System.out.println("cnt : "+cnt);
		if(cnt>=num) {
			return saveMissionUser(missionId, userId);
		}
		else return false;
	}
	
	//미션 성공 여부 확인 - 정보 게시글 작성 수 확인
	private boolean missionWriteInfo(int category, int userId, int missionId, int subCategory, int num) {
		long cnt = communityRepository.countByCategoryAndUserIdAndSubCategory(category, userId, subCategory);
		System.out.println("cnt : "+cnt);
		if(cnt>=num) {
			return saveMissionUser(missionId, userId);
		}
		else return false;
	}
	
	//미션 성공 여부 확인 - 작성 댓글 수
	//추후 프록시로 구현하여 성능 향상 예정
	private boolean missionComment(int category, int userId, int missionId, int num) {
		List<Comment> comments = commentRepository.findAllByUserId(userId);
		int cnt = 0;
		for (Comment c : comments) {
			if(cnt==num) {
				return saveMissionUser(missionId, userId);
			}
			Post post = communityRepository.getById(c.getPostId());
			if(post.getCategory() == category) cnt++;
		}
		return false;
	}
	
	//미션 성공 여부 확인 - 한 게시글 좋아요 개수 파악
	private boolean missionLike(int category, int userId, int missionId, int likes) {
		int maxLike = communityRepository.getMaxLikeByCategoryAndUserId(category, userId);
		if(maxLike >= likes) {
			return saveMissionUser(missionId, userId);
		}
		return false;
	}
	
	//미션 성공 여부 확인 - 전체 게시글 좋아요 총합 개수 파악
	private boolean missionLikeTotal(int category, int userId, int missionId, int likes) {
		int sumLike = communityRepository.getSumLikeByCategoryAndUserId(category, userId);
		System.out.println(sumLike);
		if(sumLike >= likes) {
			return saveMissionUser(missionId, userId);
		}
		return false;
	}
	
	//미션 성공 여부 확인 - 챌린지 참가 여부
	private boolean missionChallengeJoin(int category, int userId, int missionId, int num) {
		List<Map<String,Object>> myChallengeList = withChallengeService.getMyChallenge(userId);
		for (Map<String,Object> map : myChallengeList) {
			if((int)map.get("category") == category) {
				return saveMissionUser(missionId, userId);
			}
		}
		return false;
	}
	
	//미션 성공 여부 확인 - 챌린지 성공 여부
	@Transactional
	private boolean missionChallengeCompleted(int category, int userId, int missionId, int num) {
		List<Map<String,Object>> myChallengeList = withChallengeService.getMyChallenge(userId);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date today = new Date();
		int cnt = 0;
		for (Map<String,Object> map : myChallengeList) {
			if(((int)map.get("category")==category) && ((double)map.get("achievement")>=85)) {
				//현재 기준 챌린지 종료 여부 확인
				String end = dateFormat.format(map.get("endDate"));
				int diff = end.compareTo(dateFormat.format(today));
				if(diff<0) cnt++;
			}
			if(cnt >= num) return saveMissionUser(missionId, userId);					
		}
		return false;
	}
	
	//-------------------------admin---------------------------
	//키위 챌린지 추가
	@Override
	public boolean makeKiwi(List<KiwiChallengeIp> kiwiChallengeIp) {
		List<KiwiChallenge> kiwiChallenge = new ArrayList<>();
		for(KiwiChallengeIp kci : kiwiChallengeIp) {
			if(kci.getCategory() == 3) kiwiChallenge.add(new KiwiChallenge(kci.getName(), kci.getIntroduction(),
					kci.getCategory(), kci.getStartDate(), kci.getEndDate()));
			else kiwiChallenge.add(new KiwiChallenge(kci.getName(), kci.getIntroduction(),
					kci.getCategory()));
		}
		kiwiChallengeRepository.saveAll(kiwiChallenge);
		return true;
	}
	
	//키위 챌린지 미션 추가
	@Override
	public boolean makeKiwiMission(List<KiwiMissionIp> kiwiMissionIp) {
		List<KiwiMission> kiwiMission = new ArrayList<>();
		for (KiwiMissionIp km : kiwiMissionIp) {
			kiwiMission.add(new KiwiMission(km.getContent(), km.getPoint(), km.getBadge_id(), km.getCategory()));
		}
		kiwiMissionRepository.saveAll(kiwiMission);
		return true;
	}

	//배지 추가
	@Override
	public boolean makeBadge(List<Badge> badge) {
		badgeRepository.saveAll(badge);
		return true;
	}
}
