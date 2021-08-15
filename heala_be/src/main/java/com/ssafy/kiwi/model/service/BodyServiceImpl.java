package com.ssafy.kiwi.model.service;

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

}
