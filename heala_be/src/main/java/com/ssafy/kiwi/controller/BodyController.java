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
import com.ssafy.kiwi.model.dto.BodyWeightIp;
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
	
	@ApiOperation(value = "오늘의 체중 기록하기")
	@PostMapping("/calender")
	public Object uploadBody (@RequestBody BodyWeightIp bodyWeightIp) {
		if(bodyService.uploadBody(bodyWeightIp)) {
			return new ResponseEntity<>(HttpStatus.OK);			
		}
		else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@ApiOperation(value = "체중 기록 확인하기(주별,월별)")
	@GetMapping("/record")
	public Object weeklyBody (@RequestParam(value="userId") int userId, @RequestParam(value="type") String type) {
		Object response = bodyService.recordBody(userId, type);
		if(response==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
