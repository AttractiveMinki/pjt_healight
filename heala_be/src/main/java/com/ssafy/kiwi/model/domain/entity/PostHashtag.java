package com.ssafy.kiwi.model.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PostHashtag")
@Builder
@Getter
@ToString
@Table(name = "post_hashtag")
public class PostHashtag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "post_id")
	private int postId;

	@Column(name = "hashtag_id")
	private int hashtagId;

	// 다대일 : hashtag의 id와 연결
	@ManyToOne
	@JoinColumn(name = "hashtag_id", insertable = false, updatable = false)
	private Hashtag hashtag;
}
