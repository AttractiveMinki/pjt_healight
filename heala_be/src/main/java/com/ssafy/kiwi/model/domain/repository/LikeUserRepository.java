package com.ssafy.kiwi.model.domain.repository;

import com.ssafy.kiwi.model.domain.entity.LikeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeUserRepository extends JpaRepository<LikeUser, Integer> {
    Optional<LikeUser> findByUserIdAndCommentId(int userId, int commentId);
}
