package com.ssafy.kiwi.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping
@RestController
public class ApiController {
	
	@Value("${diet.api.key}")
	private String apiKey;

	@ApiOperation(value = "식품 영양 성분 정보 보기")
	@GetMapping("/data")
	public String callApi(@RequestParam(value="desc_kor", required = true) String desc_kor) throws IOException {
		
		String urlStr = "http://apis.data.go.kr/1470000/FoodNtrIrdntInfoService/getFoodNtrItdntList?"
				+ "serviceKey=" + apiKey
				+ "&desc_kor=" + URLEncoder.encode(desc_kor, "UTF-8")
				+ "&numOfRows=30"
				+ "&type=json";
		
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line).append("\n");
        }
        rd.close();
        conn.disconnect();
		return sb.toString();
		
	}
}
