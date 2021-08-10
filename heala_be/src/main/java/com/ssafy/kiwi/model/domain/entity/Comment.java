package com.ssafy.kiwi.model.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;

    private int likes;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(name = "comment_id")
    private int commentId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "post_id")
    private int postId;

    public void increaseLikes() {
        this.likes += 1;
    }

    public void decreaseLikes() {
        this.likes -= 1;
    }
    
//    @ManyToOne
//	@JoinColumn(name="post_id", insertable = false, updatable = false)
//    private Post post;
}
