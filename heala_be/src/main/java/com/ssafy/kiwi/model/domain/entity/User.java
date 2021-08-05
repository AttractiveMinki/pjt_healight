package com.ssafy.kiwi.model.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import java.sql.Date;

// 회원정보를 표현

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@ToString
@DynamicInsert
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
