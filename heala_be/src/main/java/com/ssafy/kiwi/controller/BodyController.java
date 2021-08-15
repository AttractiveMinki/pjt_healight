package com.ssafy.kiwi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
		return new ResponseEntity<>(bodyService.registerBody(bodyInfoIp), HttpStatus.OK);
	}
	
	
	
}
