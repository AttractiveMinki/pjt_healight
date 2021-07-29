package com.ssafy.healight.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
	
}
