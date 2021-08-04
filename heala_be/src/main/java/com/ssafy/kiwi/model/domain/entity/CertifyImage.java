package com.ssafy.kiwi.model.domain.entity;

import java.util.*;
import javax.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Table(name = "certify_image")
@Entity
@Builder
@Getter
public class CertifyImage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String image;
	
	@Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date time;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="with_challenge_id")
	private int withChallengeId;
	
}
