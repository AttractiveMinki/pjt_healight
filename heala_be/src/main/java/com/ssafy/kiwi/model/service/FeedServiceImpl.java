package com.ssafy.kiwi.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.kiwi.model.domain.entity.Post;
import com.ssafy.kiwi.model.domain.repository.FeedRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FeedServiceImpl implements FeedService {

	final private FeedRepository feedRepository;
	
	// 글 작성
	@Override
	public void post(Post post) {
		feedRepository.save(post);
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
