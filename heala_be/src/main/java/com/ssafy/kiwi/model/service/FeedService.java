package com.ssafy.kiwi.model.service;

import com.ssafy.kiwi.model.domain.entity.Post;

public interface FeedService {

	// 글 작성
	public Object post(Post post);

	// 글 삭제
	public boolean delete(int postId, int userId);
}
