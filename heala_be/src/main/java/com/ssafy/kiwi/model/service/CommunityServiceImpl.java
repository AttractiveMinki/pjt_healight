package com.ssafy.kiwi.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.kiwi.model.domain.entity.Post;
import com.ssafy.kiwi.model.domain.repository.CommunityRepository;

@Service
public class CommunityServiceImpl implements CommunityService {
	
	@Autowired
	private CommunityRepository communityRepository;

	// 커뮤니티 전체 글 목록 가져오기
	@Override
	public List<Post> getAllPostList() {
		final int access = 0; // 전체 공개: 0
		
		List<Post> postList = communityRepository.getPostByAccess(access);
		return postList;
	}
	
	// Best 글에 들어갈 좋아요 기준 설정
	private final int CRITERION = 100;

	// 카테고리와 서브 카테고리에 맞는 글 목록 가져오기
	@Override
	public List<Post> getPostList(int category, int subCategory) {
		List<Post> postList;
		final int access = 0; // 전체 공개: 0
		
		// Best 게시글: 3
		if (subCategory == 3) { 
			postList = communityRepository.getPostByCategoryAndAccessAndLikesGreaterThan(category, access, CRITERION);
		} else {
			postList = communityRepository.getPostByCategoryAndSubCategoryAndAccess(category, subCategory, access);
		}
		return postList;
	}

	// 커뮤니티 글 가져오기
	@Override
	public Optional<Post> getPost(int postId) {
		return communityRepository.getPostById(postId);
	}


}
