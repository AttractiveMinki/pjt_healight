package com.ssafy.healight.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ssafy.healight.domain.entity.KiwiChallenge;

public interface KiwiChallengeRepository extends JpaRepository<KiwiChallenge, Integer> {
	
	List<KiwiChallenge> findAll();

}
