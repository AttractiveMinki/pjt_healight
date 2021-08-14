package com.ssafy.kiwi.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class DietInfoOp {
	//생일, 신체정보 등록 여부
	private boolean register = false;
	//기초대사량(최소 섭취)
	private int minCalory = 1600;
	
	//하루 영양 정보
	private int calory;
	private double carbohydrate;
	private double protein;
	private double fat;
	private int sodium;
	
	private int totalCalory = 2000;
	private int totalCarbohydrate = 200;
	private int totalProtein = 54;
	private int totalFat = 50;
	private int totalSodium = 2000;
}
