package com.ssafy.kiwi.model.service;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ssafy.kiwi.model.domain.entity.Badge;
import com.ssafy.kiwi.model.domain.entity.Hashtag;
import com.ssafy.kiwi.model.domain.entity.Post;
import com.ssafy.kiwi.model.domain.entity.PostHashtag;
import com.ssafy.kiwi.model.domain.entity.User;
import com.ssafy.kiwi.model.domain.entity.UserBadge;
import com.ssafy.kiwi.model.domain.repository.FeedRepository;
import com.ssafy.kiwi.model.domain.repository.FollowRepository;
import com.ssafy.kiwi.model.domain.repository.HashtagRepository;
import com.ssafy.kiwi.model.domain.repository.PostHashtagRepository;
import com.ssafy.kiwi.model.domain.repository.UserBadgeRepository;
import com.ssafy.kiwi.model.domain.repository.UserRepository;
import com.ssafy.kiwi.model.dto.PostIp;
import com.ssafy.kiwi.model.dto.PostSimpleOp;
import com.ssafy.kiwi.model.dto.UserFollowOp;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FeedServiceImpl implements FeedService {

	final private FeedRepository feedRepository;
	final private FollowRepository followRepository;
	final private UserRepository userRepository;
	final private UserBadgeRepository userBadgeRepository;
	final private HashtagRepository hashtagRepository;
	final private PostHashtagRepository postHashtagRepository;
	
	// 글 작성
	@Override
	public void post(PostIp postIp) {
		int postId = feedRepository.save(postIp.getPost()).getId();

		// hashtag가 있는 경우 hashtag 테이블에 insert
		if (postIp.getHashtag() != null) {
			String words = postIp.getHashtag().getWord();
			StringTokenizer st = new StringTokenizer(words, " ");

			int hashtagId;
			while (st.hasMoreTokens()) {
				String word = st.nextToken().replace("#", "");
				Optional<Hashtag> checkHashtag = hashtagRepository.getHashtagByWord(word);
				if (!checkHashtag.isPresent()) {
					Hashtag hashtag = new Hashtag(word);
					hashtagId = hashtagRepository.save(hashtag).getId();
				} else {
					hashtagId = checkHashtag.get().getId();
				}

				// post_hashtag 테이블에 각 id 값 insert
				PostHashtag postHashtag = PostHashtag.builder().postId(postId).hashtagId(hashtagId).build();
				postHashtagRepository.save(postHashtag);
			}
		}
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

	
	// 게시글 제외 개인 피드 정보 가져오기(본인)
	@Override
	public Map<String, Object> getMyFeed(int userId) {
		Map<String, Object> map = getFeedInfo(userId);
		return map;
	}

	// 게시글 제외 개인 피드 정보 가져오기(타인)
	@Override
	public Map<String, Object> getUserFeed(int userId, int myId) {
		int num = followRepository.countFollowState(userId, myId);
		if(num==2) num = 1; //맞팔인 경우
		else num = 0; //그 외 경우
		Map<String, Object> map = getFeedInfo(userId);
		return map;
	}
	
	// 게시글 제외 개인 피드 정보 가져오기
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
	
	// 개인 피드 게시글 가져오기(본인 피드)
	@Override
	public List<PostSimpleOp> getMyPost(int userId, int category) {
		List<Post> postList = feedRepository.getByUserIdAndCategory(userId, category);
		List<PostSimpleOp> list = new ArrayList<>();
		
		for (Post p : postList) {
			list.add(new PostSimpleOp(p.getId(), p.getImage()));
		}
		return list;
	}
	
	// 개인 피드 게시글 가져오기(타인 피드)
	@Override
	public List<PostSimpleOp> getUserPost(int userId, int myId, int category) {
		
		int num = followRepository.countFollowState(userId, myId);
		if(num==2) num = 1; //맞팔인 경우
		else num = 0; //그 외 경우
		List<Post> postList = feedRepository.getLimitByUserIdAndCategory(userId, num, category);
		
		List<PostSimpleOp> list = new ArrayList<>();
		
		for (Post p : postList) {
			list.add(new PostSimpleOp(p.getId(), p.getImage()));
		}
		return list;
	}
	
	
	//팔로워 목록
	@Override
	public List<UserFollowOp> getFollower(int userId, int myId, int page) {
		Page<Integer> followerPage = followRepository.getFollowerIdsByUserId(userId, PageRequest.of(page, 15)); //id
		List<Integer> follower = followerPage.getContent();
		List<UserFollowOp> followers = userRepository.getUserFollowOpByIds(follower); //유저 정보
		for(UserFollowOp ufo : followers) { //팔로잉 여부 확인
			int id = ufo.getId();
			if(followRepository.countByFollowIdAndUserId(id, myId)==1) ufo.setFollow(true);
		}
		return followers;
	}

	//팔로잉 목록
	@Override
	public List<UserFollowOp> getFollowing(int userId, int myId, int page) {
		Page<Integer> followingPage = followRepository.getFollowingIdsByUserId(userId, PageRequest.of(page, 15)); //id
		List<Integer> following = followingPage.getContent();
		List<UserFollowOp> followings = userRepository.getUserFollowOpByIds(following); //유저 정보
		for(UserFollowOp ufo : followings) { //팔로잉 여부 확인
			int id = ufo.getId();
			if(followRepository.countByFollowIdAndUserId(id, myId)==1) ufo.setFollow(true);
		}
		return followings;
	}

}