package com.ssafy.healight.domain.entity;


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
	private int id;
	
	private String content;
	private int point;
	private int badge_id;
	private int category;
	
	@OneToMany
	@JoinColumn(name="mission_id")
	private Collection<KiwiUser> kiwiUser;
}
