package com.ssafy.kiwi.model.domain.entity;

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
		
	@Column(name="complete_date")
	private Date completeDate;

	@Column(name="user_id")
	private int userId;
	
	@Column(name="mission_id")
	private int missionId;
}
