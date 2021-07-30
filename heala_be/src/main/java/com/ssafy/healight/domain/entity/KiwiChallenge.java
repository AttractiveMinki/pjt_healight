package com.ssafy.healight.domain.entity;

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
	private Date start_date;
	private Date end_date;
	
}
