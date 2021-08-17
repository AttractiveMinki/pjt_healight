package com.ssafy.kiwi.model.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import com.ssafy.kiwi.model.domain.entity.BodyInfo;
import com.ssafy.kiwi.model.domain.entity.Diet;
import com.ssafy.kiwi.model.domain.entity.User;
import com.ssafy.kiwi.model.domain.repository.BodyInfoRepository;
import com.ssafy.kiwi.model.domain.repository.DietRepository;
import com.ssafy.kiwi.model.domain.repository.UserRepository;
import com.ssafy.kiwi.model.dto.DietInfoOp;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DietServiceImpl implements DietService {
	
	final private UserRepository userRepository;
	final private BodyInfoRepository bodyInfoRepository;
	final private DietRepository dietRepository;
	
	static Date today = new Date();
	static SimpleDateFormat ageFormat = new SimpleDateFormat ("yyyy");
	static SimpleDateFormat inputFormat = new SimpleDateFormat("yyyymmdd");
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
	static SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
	
	//하루 영양 정보
	@Override
	public DietInfoOp getToday(int userId, String day) throws ParseException {
		//날짜 포맷 변환 (String yyyymmdd -> sql.Date yyyy-mm-dd) 
		Date beforeDate = inputFormat.parse(day);
		String dateString = dateFormat.format(beforeDate);
		java.sql.Date date = java.sql.Date.valueOf(dateString);
		
		//유저, 신체 정보
		User user = userRepository.getById(userId);
		BodyInfo bodyInfo = bodyInfoRepository.getRecentByUserId(userId);
		
		DietInfoOp dietInfoOp = new DietInfoOp();
		
		//입력한 경우
		if(user.getBirth() != null && bodyInfo != null) {
			double weight = bodyInfo.getWeight();
			double height = bodyInfo.getHeight();
			boolean isMan = user.isGender();
			int age = Integer.parseInt(ageFormat.format(today)) - Integer.parseInt(ageFormat.format(user.getBirth())) + 1;
			dietInfoOp.setRegister(true);
			//기초대사량 계산
			double basal = 0;
			if(isMan) basal = 66.47 + (13.75 * weight) + (5 * height) - (6.76 * age);
			else basal = 665.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age);
			int minCalory = (int)Math.round(basal/10.0) * 10;
			dietInfoOp.setMinCalory(minCalory);
			//칼로리
			double standardWeight = 0;
			if(isMan) standardWeight = (height/100.0) * (height/100.0) * 22;
			else standardWeight = (height/100.0) * (height/100.0) * 21;
			if(age<20) standardWeight *= 35;
			else standardWeight *= 30;
			dietInfoOp.setTotalCalory((int)Math.round(standardWeight/10.0) * 10);
			//탄수화물
			dietInfoOp.setTotalCarbohydrate((int)(dietInfoOp.getTotalCalory() * 0.5 / 4));
			//단백질
			dietInfoOp.setTotalProtein((int)Math.ceil(weight * 0.9));
			//지방
			dietInfoOp.setTotalFat((int)Math.ceil(dietInfoOp.getTotalCalory() * 0.2 / 8));
			//나트륨
			if(age<20) dietInfoOp.setTotalSodium(1500);
		}
		List<Object[]> sumList = dietRepository.getSumByUserId(userId, date);
		//입력한 영양 정보
		if(!sumList.isEmpty()) {
			if(sumList.get(0)[0] != null) dietInfoOp.setCalory((int) Math.floor((double)sumList.get(0)[0]));
			if(sumList.get(0)[1] != null) dietInfoOp.setCarbohydrate(Math.floor((double)sumList.get(0)[1]*10) / 10.0);
			if(sumList.get(0)[2] != null) dietInfoOp.setProtein(Math.floor((double)sumList.get(0)[2] *10) / 10.0);
			if(sumList.get(0)[3] != null) dietInfoOp.setFat(Math.floor((double)sumList.get(0)[3] *10) / 10.0); 
			if(sumList.get(0)[4] != null) dietInfoOp.setSodium((int) Math.floor((double)sumList.get(0)[4]));
		}
		return dietInfoOp;
	}

	
	//오늘의 식단 목록
	@Override
	public Object getTodayList(int userId) {
		List<String> list = dietRepository.getListByUserId(userId, today);
		return list;
	}

	//식단 기록
	@Override
	public boolean uploadDiet(Diet diet) {
		dietRepository.save(diet);
		return true;
	}

	//식단 캘린더
	@Override
	public Object calenderDiet(int userId, String month) {
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
		List<Date> dates = dietRepository.getAllDayByMonth(userId, startDate, endDate);
		//중복 제거, int로 정제
		TreeSet<Integer> dateSet = new TreeSet<>();
		for (Date d : dates) {
			int day = Integer.parseInt(dayFormat.format(d));
			dateSet.add(day);
		}
		return dateSet;
	}
	
	
}
