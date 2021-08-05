package com.ssafy.kiwi.model.domain.repository;

import com.ssafy.kiwi.model.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByPostId(int postId);

    @Transactional
    void deleteAllByCommentId(int commentId);
}
