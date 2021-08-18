package com.ssafy.kiwi.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class KiwiMissionIp {
	
	private String content;
	private int point;
	private Integer badge_id;
	private int category;
	
}
