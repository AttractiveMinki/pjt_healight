package com.ssafy.kiwi.model.domain.entity;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Table(name = "body_info")
@Entity
@Builder
@Getter
public class BodyInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private double height;
	private double weight;
	
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false) 
	private Date createdAt;
	
	@Column(name = "user_id")
	private int userId;
}
