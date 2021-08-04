package com.ssafy.kiwi.model.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.kiwi.model.domain.entity.Post;
import com.ssafy.kiwi.model.domain.repository.FeedRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FeedServiceImpl implements FeedService {

	final private FeedRepository feedRepository;
	
	@Override
	public Object post(@RequestBody Post post) {
		feedRepository.save(post);
		return new ResponseEntity<>(HttpStatus.OK);
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

}
