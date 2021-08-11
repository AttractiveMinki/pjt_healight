package com.ssafy.kiwi.model.service;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ssafy.kiwi.model.domain.entity.Badge;
import com.ssafy.kiwi.model.domain.entity.Post;
import com.ssafy.kiwi.model.domain.entity.User;
import com.ssafy.kiwi.model.domain.entity.UserBadge;
import com.ssafy.kiwi.model.domain.repository.FeedRepository;
import com.ssafy.kiwi.model.domain.repository.FollowRepository;
import com.ssafy.kiwi.model.domain.repository.UserBadgeRepository;
import com.ssafy.kiwi.model.domain.repository.UserRepository;
import com.ssafy.kiwi.model.dto.PostSimpleOp;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FeedServiceImpl implements FeedService {

	final private FeedRepository feedRepository;
	final private FollowRepository followRepository;
	final private UserRepository userRepository;
	final private UserBadgeRepository userBadgeRepository;
	
	// 글 작성
	@Override
	public void post(Post post) {
		feedRepository.save(post);
	}

	// 글 삭제
	@Override
	public boolean delete(int postId, int userId) {
		boolean isPossible = false;
		int checkId = feedRepository.getById(postId).getUserId();
		if (checkId == userId) {
			feedRepository.deleteById(postId);
			isPossible = true;
		}
		return isPossible;
	}
	
	// 자신이 팔로우 하고 있는 사람들의 Post 목록을 가져옴(공개 범위가 전체로 되어있는 것 + 양쪽 팔로우를 하고 있는 경우 친구 공개 Post까지)
	@Override
	public List<Post> getPostList(int userId, int page) {
		
		// 나만 팔로우 하고 있는 사람들 id 목록
		List<Integer> onewayfollowIdList = followRepository.getOnewayFollowIdsByUserId(userId);
		
		// 맞팔 하고 있는 사람들 id 목록
		List<Integer> followForFollowIdList = followRepository.getFollowForFollowByUserId(userId);
		
		// userId 홈 화면에 나타날 피드 목록 가져오기
		Page<Post> postPage = feedRepository.getByFollowAndAccess(onewayfollowIdList, followForFollowIdList, PageRequest.of(page, 10, Sort.by("created_at").descending()));
		List<Post> postList = postPage.getContent();
		return postList;
	}

	
	//개인 피드 (본인)
	@Override
	public Map<String, Object> getMyFeed(int userId) {
		List<Post> postList = feedRepository.getByUserId(userId);
		Map<String, Object> map = getFeedPost(postList, getFeedInfo(userId));
		return map;
	}

	//개인 피드 (타인)
	@Override
	public Map<String, Object> getUserFeed(int userId, int myId) {
		int num = followRepository.countFollowState(userId, myId);
		if(num==2) num = 1; //맞팔인 경우
		else num = 0; //그 외 경우
		List<Post> postList = feedRepository.getLimitByUserId(userId, num);
		Map<String, Object> map = getFeedPost(postList, getFeedInfo(userId));
		return map;
	}
	
	//개인 피드 정보 - 게시글 제외
	public Map<String, Object> getFeedInfo(int userId){
		User user = userRepository.getById(userId);
		Map<String, Object> map = new HashMap<>();
		map.put("id", user.getId());
		map.put("identity", user.getIdentity());
		map.put("image", user.getImage());
		map.put("name", user.getName());
		map.put("exp", user.getExp());
		map.put("introduction", user.getIntroduction());
		//대표 배지
		List<Badge> selectedBadge = new ArrayList<>();
		for(UserBadge ub : userBadgeRepository.getBySelectedAndUserId(userId)) {
			selectedBadge.add(ub.getBadge());
		}
		map.put("badge", selectedBadge);		
		//팔로잉, 팔로워 수
		int following = followRepository.countByUserId(userId);
		int follower = followRepository.countByFollowId(userId);
		map.put("following", following);
		map.put("follower", follower);
		return map;
	}
	
	//개인 피드 정보 - 게시글 추가
	public Map<String, Object> getFeedPost(List<Post> postList, Map<String, Object> map){
		List<PostSimpleOp> exerciseList = new ArrayList<>();
		List<PostSimpleOp> foodList = new ArrayList<>();
		List<PostSimpleOp> heartList = new ArrayList<>();
		for(Post p : postList) {
			if(p.getCategory() == 0) exerciseList.add(new PostSimpleOp(p.getId(), p.getImage()));
			else if(p.getCategory() == 1) foodList.add(new PostSimpleOp(p.getId(), p.getImage()));
			else heartList.add(new PostSimpleOp(p.getId(), p.getImage()));
		}
		map.put("exercise", exerciseList);
		map.put("food", foodList);
		map.put("heart", heartList);
		return map;
	}

}
