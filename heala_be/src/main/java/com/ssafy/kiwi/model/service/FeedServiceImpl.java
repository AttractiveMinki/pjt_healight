package com.ssafy.kiwi.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ssafy.kiwi.model.domain.entity.Follow;
import com.ssafy.kiwi.model.domain.entity.Post;
import com.ssafy.kiwi.model.domain.repository.FeedRepository;
import com.ssafy.kiwi.model.domain.repository.FollowRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FeedServiceImpl implements FeedService {

	final private FeedRepository feedRepository;
	final private FollowRepository followRepository;
	
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

}
