package com.ssafy.healight.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.healight.domain.entity.KiwiMission;

public interface KiwiMissionRepository extends JpaRepository<KiwiMission, Integer>{

	List<KiwiMission> getMissionByCategory(int category);
//	@Query("select m.id, m.content, m.point, m.badge_id, u.complete, u.complete_date, u.mission_id"
//			+ "from mission m, mission_user u"
//			+ "where m.m.category = :category and u.user_id = :userid")
//	List<KiwiMission> getMissionByCategoryAndUserid(int category, int userid);

}
