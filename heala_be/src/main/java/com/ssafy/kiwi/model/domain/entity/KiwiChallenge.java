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
	private int id;
	
	private String name;
	private String introduction;
	private int category;
	
	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;
	
}
