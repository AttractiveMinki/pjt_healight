package com.ssafy.kiwi.model.dto;

import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class BodyBMIOp {
	int weekNumber;
	double weight;
	double bmi;
	public BodyBMIOp(int _weekNumber, double _weight) {
		super();
		weekNumber = _weekNumber;
		weight = _weight;
		bmi = 0;
	}
	public BodyBMIOp(double _weight) {
		super();
		weekNumber = 0;
		weight = _weight;
		bmi = 0;
	}
	
}
