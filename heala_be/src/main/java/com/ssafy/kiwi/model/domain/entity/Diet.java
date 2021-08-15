package com.ssafy.kiwi.model.domain.entity;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Table(name = "diet")
@Entity
@Builder
@Getter
public class Diet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "food_name")
	private String foodName;
	
	private double calory;
	private double carbohydrate;
	private double protein;
	private double fat;
	private double sodium;
	
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false) 
	private Date createdAt;
	
	@Column(name = "user_id")
	private int userId;
}
