package com.ssafy.healight.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.healight.domain.entity.Badge;


public interface BadgeRepository extends JpaRepository<Badge, Integer> {
	
	
}
