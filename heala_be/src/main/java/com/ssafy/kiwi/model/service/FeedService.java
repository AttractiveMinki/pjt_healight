package com.ssafy.kiwi.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.kiwi.model.domain.entity.Post;
import com.ssafy.kiwi.model.dto.PostIp;
import com.ssafy.kiwi.model.dto.PostSimpleOp;
import com.ssafy.kiwi.model.dto.UserFollowOp;

public interface FeedService {

	// 글 작성
	public void post(PostIp postIp);
	// 글 삭제
	public boolean delete(int postId, int userId);
	// 글 수정
	public boolean update(int postId, PostIp postIp);
	
	// 자신이 팔로우 하고 있는 사람들의 Post 목록을 가져옴(공개 범위가 전체로 되어있는 것 + 양쪽 팔로우를 하고 있는 경우 친구 공개 Post까지)
	public List<Post> getPostList(int userId, int page);

	// 게시글 제외 개인 피드 정보 가져오기(본인)
	public Map<String, Object> getMyFeed(int userId);
	// 게시글 제외 개인 피드 정보 가져오기(타인)
	public Map<String, Object> getUserFeed(int userId, int myId);
	
	// 개인 피드 게시글 가져오기(본인 피드)
	public List<PostSimpleOp> getMyPost(int userId, int category, int page);
	// 개인 피드 게시글 가져오기(타인 피드)
	public List<PostSimpleOp> getUserPost(int userId, int myId, int category, int page);

	// 팔로워 목록 가져오기
	public List<UserFollowOp> getFollower(int userId, int myId, int page);
	// 팔로잉 목록 가져오기
	public List<UserFollowOp> getFollowing(int userId, int myId, int page);
	
	// 스크랩 게시글 목록 가져오기
	public Object getScrapList(int userId, int page);
	
	// 글에 연결된 해시태그들 가져오기
	public Object getPostHashtags(int postId);
	
}
