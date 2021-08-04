package com.ssafy.kiwi.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		List<Post> postList = communityRepository.findAll();
		return postList;
	}
	
	// Best 글에 들어갈 좋아요 기준 설정
	private final int CRITERION = 100;

	// 카테고리와 서브 카테고리에 맞는 글 목록 가져오기
	@Override
	public List<Post> getPostList(int category, int subCategory) {
		List<Post> postList;
		
		// Best 게시글: 3
		if (subCategory == 3) { 
			postList = communityRepository.getPostByCategoryAndLikesGreaterThan(category, CRITERION);
		} else {
			postList = communityRepository.getPostByCategoryAndSubCategory(category, subCategory);
		}
		return postList;
	}

	@Override
	public Optional<Post> getPost(int postId) {
		return communityRepository.getPostById(postId);
	}


}
