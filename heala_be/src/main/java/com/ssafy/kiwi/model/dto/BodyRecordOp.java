package com.ssafy.kiwi.model.dto;

import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class BodyRecordOp {
	private int thisWeek;
	private List<Object> bodyRecord;
}
