package com.ssafy.kiwi.model.domain.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.kiwi.model.domain.entity.Diet;

public interface DietRepository extends JpaRepository<Diet, Integer> {

	//섭취한 총 영양 정보
	@Query(value = "SELECT SUM(d.calory), SUM(d.carbohydrate), SUM(d.protein), SUM(d.fat), SUM(d.sodium)"
			+ "FROM Diet d WHERE d.userId = :userId AND DATE(d.createdAt) = :date")
	List<Object[]> getSumByUserId(int userId, Date date);

	//섭취한 음식명
	@Query(value = "SELECT d.foodName FROM Diet d WHERE d.userId = :userId AND DATE(d.createdAt) = :date ORDER BY ID DESC")
	List<String> getListByUserId(int userId, Date date);

	//월별 기록 날짜
	@Query(value = "SELECT d.createdAt FROM Diet d WHERE d.userId = :userId "
			+ "AND DATE(d.createdAt) >= :startDate AND DATE(d.createdAt) < :endDate")
	List<Date> getAllDayByMonth(int userId, Date startDate, Date endDate);

}
