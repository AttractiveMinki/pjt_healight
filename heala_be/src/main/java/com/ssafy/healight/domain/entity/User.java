package com.ssafy.healight.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

// 회원정보를 표현

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
public class User {

	@Id
	private int id;
	
	private String email;
	private String identity;
	private String name;
	private String password;
	private String image;
	private int exp;
	private boolean gender;
	private Date birth;
	private String introduction;
	private boolean admin;

}