package com.ssafy.kiwi.model.dto;

import com.ssafy.kiwi.model.domain.entity.ChallengeHashtag;
import com.ssafy.kiwi.model.domain.entity.WithChallenge;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
//with challenge 입력값들을 받는 클래스
public class WithChallengeIp {
	
	WithChallenge withChallenge;
	ChallengeHashtag challengeHashtag;
	
}