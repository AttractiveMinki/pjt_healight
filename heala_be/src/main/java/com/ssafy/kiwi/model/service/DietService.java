package com.ssafy.kiwi.model.service;

import java.text.ParseException;

import com.ssafy.kiwi.model.dto.DietInfoOp;
import com.ssafy.kiwi.model.dto.DietIp;

public interface DietService {

	//하루 영양 정보
	DietInfoOp getToday(int userId, String day) throws ParseException;

	//오늘의 식단 목록
	Object getTodayList(int userId);

	//식단 기록
	boolean uploadDiet(DietIp dietIp);

	//식단 캘린더
	Object calenderDiet(int userId, String month);

}
