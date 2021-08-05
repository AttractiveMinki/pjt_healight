package com.ssafy.kiwi.model.domain.entity;


import java.util.Collection;

import javax.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Table(name="mission")
public class KiwiMission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String content;
	private int point;
	//int는 null값이 올 수 없으므로 Integer로 지정
	private Integer badge_id;
	private int category;
	
	@OneToMany
	@JoinColumn(name="mission_id")
	private Collection<KiwiUser> kiwiUser;
}
