package com.ssafy.kiwi.model.dto;

import com.ssafy.kiwi.model.domain.entity.Hashtag;
import com.ssafy.kiwi.model.domain.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostIp {
	
	Post post;
	Hashtag hashtag;
}
