package com.ssafy.kiwi.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class BodyWeightIp {
	//오늘의 체중 기록
	private int userId;
	private double weight;
}
