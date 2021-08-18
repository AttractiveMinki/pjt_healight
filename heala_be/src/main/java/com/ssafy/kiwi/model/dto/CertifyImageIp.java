package com.ssafy.kiwi.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class CertifyImageIp {
	
	private String image;
	private int userId;
	private int withChallengeId;
	
}