package com.ssafy.kiwi.model.service;

import java.text.ParseException;

public interface DietService {

	//하루 영양 정보
	Object getToday(int userId, String day) throws ParseException;

}
