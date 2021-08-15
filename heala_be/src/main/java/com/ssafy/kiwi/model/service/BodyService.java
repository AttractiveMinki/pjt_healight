package com.ssafy.kiwi.model.service;

import java.util.TreeSet;

import com.ssafy.kiwi.model.dto.BodyInfoIp;
import com.ssafy.kiwi.model.dto.BodyWeightIp;

public interface BodyService {

	//신체 정보 등록
	boolean registerBody(BodyInfoIp bodyInfoIp);
	//체중 캘린더 보기
	TreeSet<Integer> calenderBody(int userId, String month);
	//오늘의 체중 기록
	boolean uploadBody(BodyWeightIp bodyWeightIp);

}
