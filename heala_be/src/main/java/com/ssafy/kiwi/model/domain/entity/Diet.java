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
@Setter
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

	public Diet(String foodName, double calory, double carbohydrate, double protein, double fat,
			double sodium, int userId) {
		super();
		this.foodName = foodName;
		this.calory = calory;
		this.carbohydrate = carbohydrate;
		this.protein = protein;
		this.fat = fat;
		this.sodium = sodium;
		this.userId = userId;
	}
	
	
}
