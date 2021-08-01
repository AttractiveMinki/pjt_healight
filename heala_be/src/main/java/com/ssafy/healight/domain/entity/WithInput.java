package com.ssafy.healight.domain.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
//with challenge 입력값들을 받는 클래스
public class WithInput {
	
	WithChallenge withChallenge;
	ChallengeHashtag challengeHashtag;
	
}