package com.ssafy.kiwi.model.domain.repository;

import com.ssafy.kiwi.model.domain.entity.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Integer> {
    Optional<Scrap> getByUserIdAndPostId(int userId, int postId);
}
