package com.ssafy.healight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.healight.domain.entity.Post;
import com.ssafy.healight.domain.entity.User;
import com.ssafy.healight.domain.repository.FeedRepository;
import com.ssafy.healight.service.FeedService;

@CrossOrigin("*")
@RequestMapping("/feed")
@RestController


//피드 컨트롤러
public class FeedController {
	
	@Autowired
	private FeedService feedService;
	
	// 글 작성
	@PostMapping("/post")
	public Object post(@RequestBody Post post) {
		return feedService.post(post);
	}
	
	// 글 목록 불러오기
}
