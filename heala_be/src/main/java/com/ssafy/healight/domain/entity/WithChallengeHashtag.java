package com.ssafy.healight.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "WithChallengeHashtag")
@Builder
@Getter
@ToString
@Table(name = "with_challenge_hashtag")
public class WithChallengeHashtag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "with_challenge_id")
	private int withChallengeId;
	
	@Column(name = "challenge_hashtag_id")
	private int challengeHashtagId;

	public WithChallengeHashtag(int withChallengeId, int challengeHashtagId) {
		super();
		this.withChallengeId = withChallengeId;
		this.challengeHashtagId = challengeHashtagId;
	}
	
	
	
}