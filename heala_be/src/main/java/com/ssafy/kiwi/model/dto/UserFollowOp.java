package com.ssafy.kiwi.model.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Data
public class UserFollowOp {
	//팔로잉, 팔로워 목록용
	private int id;
	private String identity;
	private String name;
	private String image;
	private boolean follow = false;
	
	public UserFollowOp(int id, String identity, String name, String image) {
		super();
		this.id = id;
		this.identity = identity;
		this.name = name;
		this.image = image;
		this.follow = false;
	}
	
	
}
