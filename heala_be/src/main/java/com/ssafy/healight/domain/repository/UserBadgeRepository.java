package com.ssafy.healight.domain.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.healight.domain.entity.UserBadge;


public interface UserBadgeRepository extends JpaRepository<UserBadge, Integer> {
	
	@Query(value = "select ub from UserBadge ub left join fetch ub.badge where ub.user_id = :user_id")
	 List<UserBadge> getAllWithBadge(int user_id);

	
}
