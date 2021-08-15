package com.ssafy.kiwi.model.service;

import java.util.TreeSet;

import com.ssafy.kiwi.model.dto.BodyInfoIp;

public interface BodyService {

	//신체 정보 등록
	boolean registerBody(BodyInfoIp bodyInfoIp);
	//체중 캘린더 보기
	TreeSet<Integer> calenderBody(int userId, String month);

}
