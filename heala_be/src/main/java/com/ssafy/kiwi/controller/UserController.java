package com.ssafy.kiwi.controller;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.kiwi.model.domain.authentication.entity.Member;
import com.ssafy.kiwi.model.domain.entity.Follow;
import com.ssafy.kiwi.model.dto.CommentIdSetIp;
import com.ssafy.kiwi.model.dto.ProfileIp;
import com.ssafy.kiwi.model.dto.UserFollowOp;
import com.ssafy.kiwi.model.dto.UserIdSetIp;
import com.ssafy.kiwi.model.dto.UserSimpleOp;
import com.ssafy.kiwi.model.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/user")
@RestController
public class UserController {
	
    final private UserService userService;

    
	@ApiOperation(value = "아이디 중복 검사하기.")
	@GetMapping("/checkidentity/{identity}")
	public Object checkId(@PathVariable String identity) {
		Optional<Member> userOpt = userService.checkId(identity);
		if (!userOpt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "이메일 중복 검사.")
	@GetMapping("/checkemail/{email}")
	public Object checkEmail(@PathVariable String email) {
		Optional<Member> userOpt = userService.checkEmail(email);
		if (!userOpt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "본인 인증(아이디, 비밀번호 일치 확인)")
	@PostMapping("/checkAuthorization")
	public Object authorize(@RequestBody Member user) {
		Optional<Member> userOpt = userService.login(user.getIdentity(), user.getPassword());
		if (userOpt.isPresent()) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@ApiOperation(value = "회원 탈퇴")
	@DeleteMapping("/{userId}")
	public Object delete(@PathVariable int userId) {
		boolean isPresent = userService.delete(userId);
		if (isPresent) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401: 권한 없음
		}
	}
	
	@ApiOperation(value = "프로필 편집 조회하기.")
	@GetMapping("/profile/{userId}")
	public Object getProfile(@PathVariable int userId) {
		return new ResponseEntity<>(userService.getProfile(userId), HttpStatus.OK);
	}

	@ApiOperation(value = "프로필 편집 반영하기.")
	@PatchMapping("/profile/{userId}")
	public Object updateUser(@PathVariable int userId, @RequestBody ProfileIp profileIp) throws Exception {
		if(userService.updateUser(userId, profileIp)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@ApiOperation(value = "팔로우 조회하기.")
	@GetMapping("/follow")
	public Object follow(@RequestParam int userId, @RequestParam int followId) {
		if(userService.getFollowByUserIdAndFollowId(userId, followId)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.OK);
	}
	
	@ApiOperation(value = "팔로우하기.")
	@PostMapping("/follow")
	public Object follow(@RequestBody Follow follow) {
		if(userService.follow(follow.getUserId(), follow.getFollowId())) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	@ApiOperation(value = "팔로우 취소하기.")
	@DeleteMapping("/follow")
	public Object cancelFollow(@RequestParam int followId, @RequestParam int userId) {
		if(userService.cancelFollow(userId, followId)){
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "유저 한 명 간단 정보 불러오기")
	@GetMapping("/simple")
	public Object getUserSimpleInfoOne(@RequestParam int userId){
		UserSimpleOp userSimpleOp = userService.getUserSimpleInfo(userId);
		if(userSimpleOp != null){
			return new ResponseEntity<>(userSimpleOp, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "유저 여러 명 간단 정보 불러오기")
	@PostMapping("/comment")
	public Object getAllUserSimpleInfoByUserId(@RequestBody UserIdSetIp userIdSetIp){
		List<UserSimpleOp> userSimpleOpList = userService.getUserSimpleInfoAll(userIdSetIp.getUserIdSet());
		return new ResponseEntity<>(userSimpleOpList, HttpStatus.OK);
	}

	@ApiOperation(value = "유저 exp 정보 불러오기")
	@GetMapping("/exp")
	public Object getUserExpByUserId(@RequestParam int userId) {
		Integer exp = userService.getMemberExpByUserId(userId);
		if(exp != null){
			return new ResponseEntity<>(exp, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "댓글 전체 좋아요 여부 불러오기")
	@PostMapping("/comment/like")
	public Object getAllCommentLikeUserByUserId(@RequestBody CommentIdSetIp commentIdSetIp){
		List<Integer> likeCommentList = userService.getAllLikeCommentByUserId(commentIdSetIp.getCommentIdSet(), commentIdSetIp.getUserId());
		return new ResponseEntity<>(likeCommentList, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "유저 검색")
	@GetMapping("/search")
	public Object getUserListByWord(@RequestParam int userId, @RequestParam String word, int page){
		List<UserFollowOp> searchUserList = userService.getUserListByWord(userId, word, page);
		return new ResponseEntity<>(searchUserList, HttpStatus.OK);
	}
	
}

