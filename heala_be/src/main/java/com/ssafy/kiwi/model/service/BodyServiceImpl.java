package com.ssafy.kiwi.model.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import com.ssafy.kiwi.model.domain.entity.BodyInfo;
import com.ssafy.kiwi.model.domain.entity.User;
import com.ssafy.kiwi.model.domain.repository.BodyInfoRepository;
import com.ssafy.kiwi.model.domain.repository.UserRepository;
import com.ssafy.kiwi.model.dto.BodyInfoIp;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BodyServiceImpl implements BodyService {
	
	final private UserRepository userRepository;
	final private BodyInfoRepository bodyInfoRepository;
	
	static Date today = new Date();
	static SimpleDateFormat ageFormat = new SimpleDateFormat ("yyyy");
	static SimpleDateFormat inputFormat = new SimpleDateFormat("yyyymmdd");
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
	static SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
	
	//신체 정보 등록하기
	@Override
	public boolean registerBody(BodyInfoIp bodyInfoIp) {
		//생일, 성별 갱신
		User user = userRepository.getById(bodyInfoIp.getUserId());
		user.setBirth(bodyInfoIp.getBirth());
		user.setGender(bodyInfoIp.isGender());
		userRepository.save(user);
		//키, 몸무게 저장
		BodyInfo body = new BodyInfo();
		body.setHeight(bodyInfoIp.getHeight());
		body.setWeight(bodyInfoIp.getWeight());
		body.setUserId(bodyInfoIp.getUserId());
		bodyInfoRepository.save(body);
		return true;
	}

	//체중 캘린더 보기
	@Override
	public TreeSet<Integer> calenderBody(int userId, String month) {
		//검색 기간 - 시작일 : 이번 달 1일
		StringBuilder sb = new StringBuilder();
		sb.append(month).append("-01");
		java.sql.Date startDate = java.sql.Date.valueOf(sb.toString());
				
		//검색 기간 - 마감일 : 다음 달 1일
		String nowMonth = month.substring(6);
		int year = Integer.parseInt(month.substring(0, 4));
		sb = new StringBuilder();
		switch (nowMonth) {
		case "09": sb.append(year).append("-10-01"); break;
		case "10": sb.append(year).append("-11-01"); break;
		case "11": sb.append(year).append("-12-01"); break;
		case "12": sb.append(year+1).append("-01-01"); break;
		default: sb.append(year).append("-0").append(Integer.parseInt(month.substring(6, 7))+1).append("-01");
			break;
		}
		java.sql.Date endDate = java.sql.Date.valueOf(sb.toString());
		
		//기록된 날짜 목록
		List<Date> dates = bodyInfoRepository.getAllDayByMonth(userId, startDate, endDate);
		//중복 제거, int로 정제
		TreeSet<Integer> dateSet = new TreeSet<>();
		for (Date d : dates) {
			int day = Integer.parseInt(dayFormat.format(d));
			dateSet.add(day);
		}
		return dateSet;
	}

}
