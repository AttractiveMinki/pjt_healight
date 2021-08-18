package com.ssafy.kiwi.model.domain.entity;


import java.sql.Date;

import javax.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
public class KiwiChallenge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String introduction;
	private int category;
	
	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	public KiwiChallenge(String name, String introduction, int category, Date startDate, Date endDate) {
		super();
		this.name = name;
		this.introduction = introduction;
		this.category = category;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public KiwiChallenge(String name, String introduction, int category) {
		super();
		this.name = name;
		this.introduction = introduction;
		this.category = category;
	}
	
}
