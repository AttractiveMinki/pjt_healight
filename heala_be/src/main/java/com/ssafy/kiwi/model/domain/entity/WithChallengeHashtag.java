package com.ssafy.kiwi.model.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Entity(name = "WithChallengeHashtag")
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
	
	//다대일 : challenge_hashtag의 id와 연결
	@ManyToOne
	@JoinColumn(name="challenge_hashtag_id", insertable = false, updatable = false)
	private ChallengeHashtag challengehashtag;
	
	
	
}