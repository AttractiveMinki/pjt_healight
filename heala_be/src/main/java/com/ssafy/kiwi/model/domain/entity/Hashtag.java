package com.ssafy.kiwi.model.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Hashtag")
@Builder
@Getter
@Setter
@ToString
@Table(name = "hashtag")
public class Hashtag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String word;

	public Hashtag(String word) {
		super();
		this.word = word;
	}
}
