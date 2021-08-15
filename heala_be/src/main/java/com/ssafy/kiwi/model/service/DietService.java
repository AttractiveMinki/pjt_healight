package com.ssafy.kiwi.model.service;

import java.text.ParseException;

import com.ssafy.kiwi.model.dto.DietInfoOp;

public interface DietService {

	//하루 영양 정보
	DietInfoOp getToday(int userId, String day) throws ParseException;

	//오늘의 식단 목록
	Object getTodayList(int userId);

}
