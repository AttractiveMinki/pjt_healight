package com.ssafy.kiwi.model.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.kiwi.model.domain.entity.MyChallenge;

@Repository
public interface MyChallengeRepository extends JpaRepository<MyChallenge, Integer> {

	@Query(value = "select with_challenge_id from MyChallenge where user_id = :user_id")
	List<Integer> getListByUserid(int user_id);

}
