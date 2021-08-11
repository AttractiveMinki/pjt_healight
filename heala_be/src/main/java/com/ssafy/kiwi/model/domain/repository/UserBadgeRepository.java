package com.ssafy.kiwi.model.domain.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.kiwi.model.domain.entity.UserBadge;


public interface UserBadgeRepository extends JpaRepository<UserBadge, Integer> {
	
	@Query(value = "select ub from UserBadge ub left join fetch ub.badge where ub.user_id = :userId")
	List<UserBadge> getAllWithBadge(int userId);

	//대표 배지 목록 가져오기
	@Query(value = "select ub from UserBadge ub left join fetch ub.badge where ub.selected = 1 and ub.user_id = :userId")
	List<UserBadge> getBySelectedAndUserId(int userId);

	
}
