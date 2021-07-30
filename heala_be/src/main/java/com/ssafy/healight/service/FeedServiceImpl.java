package com.ssafy.healight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.healight.domain.entity.Post;
import com.ssafy.healight.domain.repository.FeedRepository;

public class FeedServiceImpl implements FeedService {

	@Autowired
	private FeedRepository feedRepository;
	
	@Override
	public Object post(@RequestBody Post post) {
		feedRepository.save(post);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
