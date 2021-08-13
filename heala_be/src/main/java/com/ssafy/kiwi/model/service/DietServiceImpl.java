package com.ssafy.kiwi.model.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.ssafy.kiwi.model.domain.entity.BodyInfo;
import com.ssafy.kiwi.model.domain.entity.User;
import com.ssafy.kiwi.model.domain.repository.BodyInfoRepository;
import com.ssafy.kiwi.model.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DietServiceImpl implements DietService {
	
	final private BodyInfoRepository bodyInfoRepository;
	final private UserRepository userRepository;
	
	static Date today = new Date();
	static 
	
	@Override
	public Object getToday(int userId) {
		BodyInfo bodyInfo = bodyInfoRepository.getRecentByUserId(userId);
		User user = userRepository.getById(userId);
		int totalCalory = 0;
		if(user.getBirth() == null || bodyInfo == null) totalCalory = 1700;
		else {
			boolean isMan = user.isGender();
			if(isMan) {
//				int age = 
//				double cal = 66.47 + (13.75 * bodyInfo.getWeight()) + (5 * bodyInfo.getHeight()) 
			}
		}
		return null;
	}
	
	
	public Object getDaily(int userId) {
		return null;
	}
	
}
