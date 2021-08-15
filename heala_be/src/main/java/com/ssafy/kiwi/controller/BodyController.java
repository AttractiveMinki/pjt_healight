package com.ssafy.kiwi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kiwi.model.dto.BodyInfoIp;
import com.ssafy.kiwi.model.service.BodyService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

//식단
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/body")
@RestController
public class BodyController {
	
	final private BodyService bodyService;
	
	@ApiOperation(value = "신체 정보 등록하기")
	@PostMapping("/register")
	public Object registerBody (@RequestBody BodyInfoIp bodyInfoIp) {
		if(bodyService.registerBody(bodyInfoIp)) {			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@ApiOperation(value = "체중 캘린더 보기")
	@GetMapping("/calender")
	public Object calenderBody (@RequestParam(value="userId") int userId, @RequestParam(value="month") String month) {
		return new ResponseEntity<>(bodyService.calenderBody(userId, month), HttpStatus.OK);
	}
	
}
