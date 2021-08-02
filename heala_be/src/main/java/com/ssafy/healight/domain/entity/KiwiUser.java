package com.ssafy.healight.domain.entity;

import java.sql.Date;

import javax.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Table(name="mission_user")
public class KiwiUser {

	@Id
	private int id;
	
	private boolean complete;
	private Date complete_date;

	@Column(name="user_id")
	private int userid;
	private int mission_id;
}
