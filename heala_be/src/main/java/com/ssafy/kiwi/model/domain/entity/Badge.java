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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String image;
	
	public Badge(String name, String image) {
		super();
		this.name = name;
		this.image = image;
	}
}
