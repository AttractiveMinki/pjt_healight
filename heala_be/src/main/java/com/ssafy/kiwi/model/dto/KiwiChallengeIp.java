package com.ssafy.kiwi.model.dto;

import java.sql.Date;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class KiwiChallengeIp {
	
	private String name;
	private String introduction;
	private int category;
	
	private Date startDate;
	private Date endDate;
	public KiwiChallengeIp(String name, String introduction, int category) {
		super();
		this.name = name;
		this.introduction = introduction;
		this.category = category;
	}
	
}
