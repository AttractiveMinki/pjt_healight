package com.ssafy.kiwi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

//식단
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/diet")
@RestController
public class DietController {
	
	@ApiOperation(value = "하루 영양 정보 보기")
	@GetMapping("/{userId}/today")
	public Object getToday(@PathVariable int userId) {
		
		return null;
	}
}
