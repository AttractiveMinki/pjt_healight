package com.ssafy.kiwi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.kiwi.model.dto.PostIp;
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
	public Object post(@RequestBody PostIp postIp) {
		feedService.post(postIp);
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

	@ApiOperation(value = "홈 피드 보기")
	@GetMapping("/home/{userId}")
	public Object getPostList(@PathVariable int userId, int page) {
		return new ResponseEntity<>(feedService.getPostList(userId, page), HttpStatus.OK);
	}
	
	@ApiOperation(value = "배지 보관함 보기")
	@GetMapping("/{userId}/badge")
	public Object getBadge(@PathVariable int userId) {
		return new ResponseEntity<>(userService.getBadge(userId), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "게시글 제외 개인 피드 정보 가져오기(본인피드)")
	@GetMapping("/{userId}/my")
	public Object getMyFeed(@PathVariable int userId) {
		return new ResponseEntity<>(feedService.getMyFeed(userId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시글 제외 개인 피드 정보 가져오기(타인피드)")
	@GetMapping("/{userId}/{myId}")
	public Object getUserFeed(@PathVariable int userId, @PathVariable int myId) {
		return new ResponseEntity<>(feedService.getUserFeed(userId, myId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시글 가져오기(본인피드)")
	@GetMapping("/post/{userId}/my/{category}")
	public Object getMyPost(@PathVariable int userId, @PathVariable int category, int page) {
		return new ResponseEntity<>(feedService.getMyPost(userId, category, page), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시글 가져오기(타인피드)")
	@GetMapping("/post/{userId}/{myId}/{category}")
	public Object getUserPost(@PathVariable int userId, @PathVariable int myId, @PathVariable int category, int page) {
		return new ResponseEntity<>(feedService.getUserPost(userId, myId, category, page), HttpStatus.OK);
	}
	
	@ApiOperation(value = "팔로워 / 팔로잉 목록 보기")
	@GetMapping("/{userId}/followList")
	public Object getOtherFollower(@PathVariable int userId,
			@RequestParam(value="myId", required=true) int myId,
			@RequestParam(value="type", required=true) int follow, int page) {
		if(userService.existId(myId) && userService.existId(userId)) {
			if(follow == 1) return new ResponseEntity<>(feedService.getFollowing(userId, myId, page), HttpStatus.OK);
			else if(follow == 2) return new ResponseEntity<>(feedService.getFollower(userId, myId, page), HttpStatus.OK);
			else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}