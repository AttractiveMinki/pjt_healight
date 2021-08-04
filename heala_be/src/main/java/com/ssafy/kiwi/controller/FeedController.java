package com.ssafy.kiwi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kiwi.model.domain.entity.Post;
import com.ssafy.kiwi.model.service.FeedService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/feed")
@RestController

//피드 컨트롤러
public class FeedController {
	
	final private FeedService feedService;
	
	@ApiOperation(value = "글 작성하기")
	@PostMapping("/post")
	public ResponseEntity post(@RequestBody Post post) {
		 feedService.post(post);
		 return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
