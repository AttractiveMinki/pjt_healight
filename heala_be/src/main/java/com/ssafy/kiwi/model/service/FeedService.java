package com.ssafy.kiwi.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssafy.kiwi.model.domain.entity.Post;
import com.ssafy.kiwi.model.dto.UserFollowOp;

public interface FeedService {

	// 글 작성
	public void post(Post post);
	// 글 삭제
	public boolean delete(int postId, int userId);
	
	// 자신이 팔로우 하고 있는 사람들의 Post 목록을 가져옴(공개 범위가 전체로 되어있는 것 + 양쪽 팔로우를 하고 있는 경우 친구 공개 Post까지)
	public List<Post> getPostList(int userId, int page);

	// 개인 피드 (본인)
	public Map<String, Object> getMyFeed(int userId);
	// 개인 피드 (타인)
	public Map<String, Object> getUserFeed(int userId, int myId);

	// 팔로워 목록 가져오기
	public List<UserFollowOp> getFollower(int userId, int myId);
	// 팔로잉 목록 가져오기
	public List<UserFollowOp> getFollowing(int userId, int myId);
}
