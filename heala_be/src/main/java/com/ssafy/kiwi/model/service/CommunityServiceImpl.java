package com.ssafy.kiwi.model.service;

import java.util.List;

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
	public Object getAllPostList() {
		List<Post> postList = communityRepository.findAll();
		return new ResponseEntity<>(postList, HttpStatus.OK);
	}
	
	// Best 글에 들어갈 좋아요 기준 설정
	private final int CRITERION = 100;

	// 카테고리와 서브 카테고리에 맞는 글 목록 가져오기
	@Override
	public Object getPostList(int category, int subCategory) {
		List<Post> postList;
		
		// Best 게시글: 3
		if (subCategory == 3) { 
			postList = communityRepository.getPostByCategoryAndLikesGreaterThan(category, CRITERION);
		} else {
			postList = communityRepository.getPostByCategoryAndSubCategory(category, subCategory);
		}
		return new ResponseEntity<>(postList, HttpStatus.OK);
	}


}