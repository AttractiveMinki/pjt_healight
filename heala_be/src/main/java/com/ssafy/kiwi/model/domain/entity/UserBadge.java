package com.ssafy.kiwi.model.domain.entity;

import javax.persistence.*;
import lombok.*;


@Getter
@Entity
@Table(name = "user_badge")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserBadge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean selected;
	private int user_id;
	private int badge_id;

	//다대일 : badge의 id와 연결
	@ManyToOne
	@JoinColumn(name="id", insertable=false, updatable=false)
	private Badge badge;
	
}
