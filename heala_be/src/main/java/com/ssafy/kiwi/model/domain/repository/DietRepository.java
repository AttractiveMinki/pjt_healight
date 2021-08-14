package com.ssafy.kiwi.model.domain.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.kiwi.model.domain.entity.Diet;

public interface DietRepository extends JpaRepository<Diet, Integer> {

	@Query(value = "SELECT SUM(d.calory), SUM(d.carbohydrate), SUM(d.protein), SUM(d.fat), SUM(d.sodium)"
			+ "FROM Diet d WHERE d.userId = :userId AND DATE(d.createdAt) = :date")
	List<Object[]> getSumByUserId(int userId, Date date);

}
