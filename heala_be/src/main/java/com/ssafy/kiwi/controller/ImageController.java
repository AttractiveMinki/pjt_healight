package com.ssafy.kiwi.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.kiwi.model.service.ImageServer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/image")
@RestController
public class ImageController {
	
	final private ImageServer imageServer;
	
	@PostMapping
	public Object registerImageIntoServer(@RequestParam MultipartFile file) throws IOException {
		return new ResponseEntity<String>(imageServer.registerImageIntoServer(file), HttpStatus.OK);
	}
	
	@PostMapping("/kiwi")
	public Object registerKiwiImageIntoServer(@RequestParam MultipartFile file) throws IOException {
		return new ResponseEntity<String>(imageServer.registerKiwiImageIntoServer(file), HttpStatus.OK);
	}
	
}