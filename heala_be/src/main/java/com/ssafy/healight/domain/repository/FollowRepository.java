package com.ssafy.healight.domain.repository;

import com.ssafy.healight.domain.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Integer> {

    Optional<Follow> getFollowById(int userId);

    Optional<Follow> findFirstByFollowIdAndUserId(int followId, int userId);
}
