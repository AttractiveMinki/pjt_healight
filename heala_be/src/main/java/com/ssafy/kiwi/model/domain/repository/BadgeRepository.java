package com.ssafy.kiwi.model.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.kiwi.model.domain.entity.Badge;


public interface BadgeRepository extends JpaRepository<Badge, Integer> {
	
	
}
