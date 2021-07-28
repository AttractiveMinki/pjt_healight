package com.ssafy.healight.domain.entity;

import java.sql.Timestamp;
//import java.util.Date;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
//with challenge 입력값들을 받는 클래스
public class WithInput {
	
	private String image;
	private String title;
	private int category;
	
	// Date로 나중에 수정하자 일단 Timestamp로 사용하고
	private Timestamp startdate;
	private Timestamp enddate;
	private String certify_info;
	private String introduction;
	private int user_id;
	private String[] hashtags;
	
}