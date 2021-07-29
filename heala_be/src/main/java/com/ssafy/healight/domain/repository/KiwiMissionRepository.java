package com.ssafy.healight.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.healight.domain.entity.KiwiMission;

public interface KiwiMissionRepository extends JpaRepository<KiwiMission, Integer>{

	List<KiwiMission> getMissionByCategory(int category);

}
