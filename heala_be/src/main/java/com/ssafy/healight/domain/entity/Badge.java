package com.ssafy.healight.domain.entity;

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
	private int Id;
	
	private String name;
	private String image;
}
