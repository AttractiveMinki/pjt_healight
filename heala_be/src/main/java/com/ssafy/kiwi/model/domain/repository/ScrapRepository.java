package com.ssafy.kiwi.model.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.kiwi.model.domain.entity.Scrap;

public interface ScrapRepository extends JpaRepository<Scrap, Integer> {
    Optional<Scrap> getByUserIdAndPostId(int userId, int postId);
    
    //스크랩한 게시글 번호 리스트
    @Query(value = "SELECT s.postId FROM Scrap s WHERE s.userId = :userId")
	List<Integer> getPostIdByUserId(int userId);
}
