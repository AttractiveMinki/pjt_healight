package com.ssafy.healight.domain.entity;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Getter
@Setter
public class Profile {
	private User user;
	private List<UserBadge> badges;
	private MultipartFile image;
}
