package com.ssafy.kiwi.model.service;

import java.util.*;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.kiwi.model.domain.entity.Comment;
import com.ssafy.kiwi.model.domain.entity.KiwiMission;
import com.ssafy.kiwi.model.domain.entity.KiwiUser;
import com.ssafy.kiwi.model.domain.entity.Post;
import com.ssafy.kiwi.model.domain.entity.User;
import com.ssafy.kiwi.model.domain.repository.CommentRepository;
import com.ssafy.kiwi.model.domain.repository.KiwiChallengeRepository;
import com.ssafy.kiwi.model.domain.repository.KiwiMissionRepository;
import com.ssafy.kiwi.model.domain.repository.KiwiUserRepository;
import com.ssafy.kiwi.model.domain.repository.PostRepository;
import com.ssafy.kiwi.model.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class KiwiChallengeServiceImpl implements KiwiChallengeService {
	
	final private KiwiChallengeRepository kiwiChallengeRepository;
	final private KiwiMissionRepository kiwiMissionRepository;
	final private KiwiUserRepository kiwiUserRepository;
	final private PostRepository postRepository;
	final private UserRepository userRepository;
	final private CommentRepository commentRepository;

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
				else return false;
			}
			else if(missionId%10==2) { //댓글 5개 작성
				if(missionComment(category, userId, missionId, 5)) {
					return completed(userId, missionId);
				}
				else return false;
			}
			else if(missionId%10==3) { //챌린지 1회 참가
				if(missionChallengeJoin(category, userId, missionId, 1)) {
					return completed(userId, missionId);
				}
				else return false; 
			}
			else if(missionId%10==4) {
				
			}
			else if(missionId%10==5) {
				
			}
			else if(missionId%10==6) {
				
			}
			else if(missionId%10==7) { //게시글 10개 작성
				if(missionWrite(category, userId, missionId, 10)) {
					return completed(userId, missionId);
				}
				else return false;
			}
			else if(missionId%10==8) {
				
			}
			else if(missionId%10==9) {
				
			}
			else {//게시글 25개 작성
				if(missionWrite(category, userId, missionId, 25)) {
					return completed(userId, missionId);
				}
				else return false;
			}
			
		}
		return null;
	}


	//미션 성공시 Response 값
	private Object completed(int userId, int missionId) {
		Map<String, Object> mission = new HashMap<>();
		//완료날짜 담기
		KiwiUser ku = kiwiUserRepository.getByUserIdAndMissionId(userId, missionId);
		mission.put("completeDate", ku.getCompleteDate());
		//획득 포인트 담기
		KiwiMission km = kiwiMissionRepository.getById(missionId);
		int point = km.getPoint();
		mission.put("point", point);
		//포인트 적립
		User user = userRepository.getById(userId);
		int exp = user.getExp() + point;
		user.setExp(exp);
		userRepository.save(user);
		return mission;
	}
	
	//미션 성공시 mission_user 테이블에 성공 내역 저장
	private boolean saveMissionUser(int missionId, int userId) {
		KiwiUser missionUser = new KiwiUser();
		missionUser.setMissionId(missionId);
		missionUser.setUserId(userId);
		kiwiUserRepository.save(missionUser);
		return true;
	}

	//미션 성공 여부 확인 - 작성 게시글 수
	private boolean missionWrite(int category, int userId, int missionId, int num) {
		long cnt = postRepository.countByCategoryAndUserId(category, userId);
		System.out.println("cnt : "+cnt);
		if(cnt>=num) {
			return saveMissionUser(missionId, userId);
		}
		else return false;
	}
	
	//미션 성공 여부 확인 - 작성 댓글 수
	//추후 프록시로 구현하여 성능 향상 예정
	private boolean missionComment(int category, int userId, int missionId, int count) {
		List<Comment> comments = commentRepository.findAllByUserId(userId);
		int cnt = 0;
		for (Comment c : comments) {
			if(cnt==count) {
				return saveMissionUser(missionId, userId);
			}
			Post post = postRepository.getById(c.getPostId());
			if(post.getCategory() == category) cnt++;
		}
		return false;
	}
	
	//미션 성공 여부 확인 - 챌린지 참가 여부
	private boolean missionChallengeJoin(int category, int userId, int missionId, int count) {
		List<Map<String,Object>> myChallengeList = withChallengeService.getMyChallenge(userId);
		for (Map<String,Object> map : myChallengeList) {
			if((int)map.get("category") == category) {
				return saveMissionUser(missionId, userId);
			}
		}
		return false;
	}
}
