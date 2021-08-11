package com.ssafy.kiwi.model.domain.repository;

import com.ssafy.kiwi.model.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
    List<Comment> findAllByPostId(int postId);
    List<Comment> findAllByUserId(int userId);

    @Transactional
    void deleteAllByCommentId(int commentId);
    
    // 댓글 갯수 세기
    @Query(value = "SELECT COUNT(*) FROM comment WHERE post_id = :postId", nativeQuery =  true) 
    int countCommentByPostId(int postId);

}
