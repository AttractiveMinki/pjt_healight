package com.ssafy.kiwi.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class DietIp {
	
	private int userId;
	
	private String foodName;
	private int calory;
	private double carbohydrate;
	private double protein;
	private double fat;
	private int sodium;
	
}
