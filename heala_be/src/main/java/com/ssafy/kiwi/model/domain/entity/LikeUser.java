package com.ssafy.kiwi.model.domain.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@ToString
public class LikeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "post_id")
    private int postId;

    @Column(name = "comment_id")
    private int commentId;
}
