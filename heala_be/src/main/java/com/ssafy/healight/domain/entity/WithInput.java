package com.ssafy.healight.domain.entity;

import java.sql.Date;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
//with challenge 입력값들을 받는 클래스
public class WithInput {
	
	private String image;
	private String title;
	private int category;
	private Date start_date;
	private Date end_date;
	private String certify_info;
	private String introduction;
	private int user_id;
	private String hashtags;
	
}