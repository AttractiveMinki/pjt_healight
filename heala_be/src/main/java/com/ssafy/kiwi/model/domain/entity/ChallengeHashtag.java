
package com.ssafy.kiwi.model.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Entity(name = "ChallengeHashtag")
@Builder
@Getter
@Setter
@ToString
@Table(name = "challenge_hashtag")
public class ChallengeHashtag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String word;

	public ChallengeHashtag(String word) {
		super();
		this.word = word;
	}

}
