package com.ssafy.kiwi.model.dto;

import java.sql.Date;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class BodyInfoIp {
	private int userId;
	private boolean gender;
	private Date birth;
	private double height;
	private double weight;
}
