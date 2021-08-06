package com.ssafy.kiwi.model.domain.repository;

import com.ssafy.kiwi.model.domain.entity.LikeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LikeUserRepository extends JpaRepository<LikeUser, Integer> {
    Optional<LikeUser> findByUserIdAndCommentId(int userId, int commentId);

    Optional<LikeUser> getByUserIdAndPostId(int userId, int postId);

    @Query(value = "SELECT lu.commentId FROM LikeUser lu WHERE lu.userId = :userId AND lu.commentId IN (:commentIdSet)")
    List<Integer> getAllLikeUserByUserId(List<Integer> commentIdSet, int userId);
}
