package com.ssafy.healight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.healight.domain.entity.Post;
import com.ssafy.healight.service.FeedService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RequestMapping("/feed")
@RestController


public class FeedController {
	
	@Autowired
	private FeedService feedService;
	
	@ApiOperation(value = "글 작성하기")
	@PostMapping("/post")
	public Object post(@RequestBody Post post) {
		return feedService.post(post);
	}
	
}
