package com.ssafy.kiwi.model.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.kiwi.model.domain.entity.BodyInfo;

public interface BodyInfoRepository extends JpaRepository<BodyInfo, Integer> {

	@Query(value = "SELECT bi FROM BodyInfo bi WHERE bi.userId = :userId ORDER BY bi.createdAt DESC, bi.id DESC LIMIT 1", nativeQuery = true)
	BodyInfo getRecentByUserId(int userId);

}
