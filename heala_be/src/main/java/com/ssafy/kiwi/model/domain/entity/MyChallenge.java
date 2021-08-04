package com.ssafy.kiwi.model.domain.entity;

import javax.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Table(name="my_challenge")
public class MyChallenge {
	
	@Id
	private int id;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "with_challenge_id")
	private int withChallengeId;
}
