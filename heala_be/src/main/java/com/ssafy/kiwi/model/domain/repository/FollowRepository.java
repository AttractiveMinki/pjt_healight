package com.ssafy.kiwi.model.domain.repository;

import com.ssafy.kiwi.model.domain.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Integer> {

    Optional<Follow> findFirstByFollowIdAndUserId(int followId, int userId);
}
