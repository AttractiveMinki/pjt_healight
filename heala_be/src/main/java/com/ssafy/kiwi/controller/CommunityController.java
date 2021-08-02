package com.ssafy.kiwi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public Object getAllPostList() {
		return communityService.getAllPostList();
	} 
	
	@ApiOperation(value = "카테고리와 서브 카테고리에 맞는 글 목록 가져오기")
	@GetMapping("/category")
	public Object getPostList(@RequestParam(value="category") int category, @RequestParam(value="sub_category") int subCategory) {
		return communityService.getPostList(category, subCategory);
	}
	
}
