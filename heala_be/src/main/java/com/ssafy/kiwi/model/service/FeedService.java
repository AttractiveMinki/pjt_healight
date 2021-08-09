package com.ssafy.kiwi.model.service;

import java.util.List;

import com.ssafy.kiwi.model.domain.entity.Post;

public interface FeedService {

	// 글 작성
	public void post(Post post);

	// 글 삭제
	public boolean delete(int postId, int userId);
	
	// 자신이 팔로우 하고 있는 사람들의 Post 목록을 가져옴(공개 범위가 전체로 되어있는 것 + 양쪽 팔로우를 하고 있는 경우 친구 공개 Post까지)
	public List<Post> getPostList(int userId);
}
