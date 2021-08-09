package com.ssafy.kiwi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.kiwi.model.domain.entity.Post;
import com.ssafy.kiwi.model.service.FeedService;
import com.ssafy.kiwi.model.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/feed")
@RestController

//피드 컨트롤러
public class FeedController {

	final private FeedService feedService;
	final private UserService userService;

	@ApiOperation(value = "글 작성하기")
	@PostMapping("/post")
	public Object post(@RequestBody Post post) {
		feedService.post(post);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "글 삭제하기")
	@DeleteMapping("/post")
	public Object delete(@RequestParam int postId, @RequestParam int userId) {
		boolean isPossible = feedService.delete(postId, userId);
		if (isPossible) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@ApiOperation(value = "배지 보관함 보기")
	@GetMapping("/badge/{userId}")
	public Object getBadge(@PathVariable int userId) {
		return new ResponseEntity<>(userService.getBadge(userId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "홈 피드 보기")
	@GetMapping("/home/{userId}")
	public Object getPostList(@PathVariable int userId, int page) {
		return new ResponseEntity<>(feedService.getPostList(userId, page), HttpStatus.OK);
	}
}
