package com.ssafy.kiwi.model.service;

import java.util.List;
import java.util.Optional;

import com.ssafy.kiwi.model.domain.entity.Post;

public interface CommunityService {
	
	// 전체 글 목록 반환하기
	public List<Post> getAllPostList();

	// 카테고리(운동,식단,마음)와 서브 카테고리(일반, Best, 정보, 질문)에 따라 글 목록 반환하기
	public List<Post> getPostList(int category, int subCategory);

	// 커뮤니티 글 가져오기
	public Optional<Post> getPost(int postId);
	
}
