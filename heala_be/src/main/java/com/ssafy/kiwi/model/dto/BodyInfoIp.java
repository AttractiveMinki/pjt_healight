package com.ssafy.kiwi.model.dto;

import java.sql.Date;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class BodyInfoIp {
	//신체 정보 등록
	private int userId;
	private boolean gender;
	private Date birth;
	private double height;
	private double weight;
}
