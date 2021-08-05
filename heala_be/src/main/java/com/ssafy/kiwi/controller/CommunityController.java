package com.ssafy.kiwi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.kiwi.model.domain.entity.Comment;
import com.ssafy.kiwi.model.domain.entity.LikeUser;
import org.springframework.web.bind.annotation.*;

import com.ssafy.kiwi.model.domain.entity.Post;
import com.ssafy.kiwi.model.service.CommunityService;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/community")
@RestController
public class CommunityController {
	
	final private CommunityService communityService;
	
	@ApiOperation(value = "커뮤니티 전체 글 목록 가져오기")
	@GetMapping
	public ResponseEntity<List<Post>> getAllPostList() {
		List<Post> postList = communityService.getAllPostList();
		return new ResponseEntity<>(postList, HttpStatus.OK);
	} 
	
	@ApiOperation(value = "카테고리와 서브 카테고리에 맞는 글 목록 가져오기")
	@GetMapping("/category")
	public Object getPostList(@RequestParam int category, @RequestParam int subCategory) {
		List<Post> postList = communityService.getPostList(category, subCategory);
		return new ResponseEntity<>(postList, HttpStatus.OK);
	}
	
	@ApiOperation(value = "커뮤니티 글 상세 화면")
	@GetMapping("/post/{postId}")
	public Object getPost(@PathVariable int postId) {
		Optional<Post> post = communityService.getPost(postId);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}

	@ApiOperation(value = "댓글 목록 가져오기")
	@GetMapping("/post/{postId}/comment")
	public Object getPostComments(@PathVariable int postId){
		return new ResponseEntity<>(communityService.getPostComments(postId), HttpStatus.OK);
	}

	@ApiOperation(value = "댓글 작성")
	@PostMapping("/post/comment")
	public Object makeComment(@RequestBody Comment comment){
		communityService.makeComment(comment);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "댓글 삭제")
	@PostMapping("/post/comment/{commentId}")
	public Object deleteComment(@PathVariable int commentId){
		communityService.deleteComment(commentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "댓글 좋아요")
	@PostMapping("/post/comment/like")
	public Object likeComment(@RequestBody LikeUser likeUser){
		if(communityService.likeComment(likeUser)){
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "댓글 좋아요")
	@DeleteMapping("/post/comment/like")
	public Object cancelLkeComment(@RequestBody LikeUser likeUser){
		if(communityService.cancelLikeComment(likeUser)){
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
