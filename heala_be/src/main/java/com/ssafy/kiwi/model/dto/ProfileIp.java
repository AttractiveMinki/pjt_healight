package com.ssafy.kiwi.model.dto;

import java.util.List;

import com.ssafy.kiwi.model.domain.authentication.entity.Member;
import com.ssafy.kiwi.model.domain.entity.UserBadge;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Getter
@Setter
public class ProfileIp {
	private Member user;
	private List<UserBadge> badges;
}
