package com.ssafy.kiwi.model.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.kiwi.model.domain.entity.KiwiMission;

public interface KiwiMissionRepository extends JpaRepository<KiwiMission, Integer>{

	List<KiwiMission> getMissionByCategory(int category);
//	@Query("select m.id, m.content, m.point, m.badge_id, u.complete, u.complete_date, u.mission_id"
//			+ "from mission m, mission_user u"
//			+ "where m.m.category = :category and u.user_id = :userid")
//	List<KiwiMission> getMissionByCategoryAndUserid(int category, int userid);
	
//	@Query(value = "select m from KiwiMission m left join fetch m.kiwiUser where m.category = :category and m.user_id = :user_id")
//	List<KiwiMission> getAllWithUser(int category, int user_id);

}
