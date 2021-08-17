package com.ssafy.kiwi.model.dto;

import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class BodyRecordOp {
	//오늘 주별or월별 번호
	private int todayNumber;
	//주별or월별 평균 체중, bmi 기록
	private List<BodyBMIOp> bodyRecord;
}
