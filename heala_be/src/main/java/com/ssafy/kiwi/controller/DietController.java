package com.ssafy.kiwi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kiwi.model.service.DietService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

//식단
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/diet")
@RestController
public class DietController {
	
	final private DietService dietService;
	
	@ApiOperation(value = "하루 영양 정보 보기")
	@GetMapping("/{userId}/today")
	public Object getToday(@PathVariable int userId) {
		return new ResponseEntity<>(dietService.getToday(userId), HttpStatus.OK);
	}
}
