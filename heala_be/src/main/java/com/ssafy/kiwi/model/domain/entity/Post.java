package com.ssafy.kiwi.model.domain.entity;

import lombok.*;

import javax.persistence.*;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Post")
@Builder
@Getter
@ToString
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String image;
	private String title;
	private int category;
	
	@Column(name = "sub_category")
	private int subCategory;
	
	private int access;
	private String content;
	private int likes;
	private boolean anonymous;

	@Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP") 
	public Timestamp createdAt;
	
	@Column(name = "updated_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP") 
	public Timestamp updatedAt;
	
	@Column(name = "user_id")
	public int userId;
}