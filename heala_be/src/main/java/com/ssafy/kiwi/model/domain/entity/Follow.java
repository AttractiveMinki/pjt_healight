package com.ssafy.kiwi.model.domain.entity;

import lombok.*;

import javax.persistence.*;

// 팔로우 관계

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@ToString
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "follow_id")
    private int followId;

    @Column(name = "user_id")
    private int userId;

    public Follow(int followId, int userId) {
        this.followId = followId;
        this.userId = userId;
    }
}
