package com.ssafy.kiwi.model.domain.entity;

import javax.persistence.*;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Table(name = "badge")
@Entity
@Builder
@Getter
public class Badge {
	@Id
	private int id;
	
	private String name;
	private String image;
}
