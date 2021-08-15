package com.ssafy.kiwi.model.domain.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.kiwi.model.domain.entity.BodyInfo;

public interface BodyInfoRepository extends JpaRepository<BodyInfo, Integer> {

	//가장 최근 신체 정보
	@Query(value = "SELECT * FROM body_info bi WHERE bi.user_id = :userId ORDER BY bi.created_at DESC, bi.id DESC LIMIT 1", nativeQuery = true)
	BodyInfo getRecentByUserId(int userId);

	//월별 기록 날짜
	@Query(value = "SELECT bi.createdAt FROM BodyInfo bi WHERE bi.userId = :userId "
			+ "AND DATE(bi.createdAt) >= :startDate AND DATE(bi.createdAt) < :endDate")
	List<Date> getAllDayByMonth(int userId, java.sql.Date startDate, java.sql.Date endDate);
	
	//이번주 몇주차인지 번호 리턴
	@Query(value = "select extract(week from now())", nativeQuery = true)
	int getWeekNum();

	//주별 평균 기록 리스트, 현재부터 23주 전까지
	@Query(value = "select extract(week from created_at), avg(weight) from body_info "
			+ "where (user_id = :userId)  and (created_at between date_sub(curdate(), interval 23 week) and curdate()+1)"
			+ "group by week(created_at) order by created_at", nativeQuery = true)
	List<Object> getWeeklyRecordByUserId(int userId);

}
