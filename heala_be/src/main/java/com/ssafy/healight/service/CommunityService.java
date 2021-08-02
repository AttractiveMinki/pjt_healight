package com.ssafy.healight.service;

public interface CommunityService {
	
	// 전체 글 목록 반환하기
	public Object getAllPostList();

	// 카테고리(운동,식단,마음)와 서브 카테고리(일반, Best, 정보, 질문)에 따라 글 목록 반환하기
	public Object getPostList(int category, int subCategory);
	
}
