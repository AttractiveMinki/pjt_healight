package com.ssafy.kiwi.model.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.kiwi.model.domain.entity.MyChallenge;

@Repository
public interface MyChallengeRepository extends JpaRepository<MyChallenge, Integer> {

	// userId에 일치하는 함께 챌린지 id들을 리스트로 가져옴
//	@Query(value = "select m from my_challenge where user_id = ?1")
	List<MyChallenge> getMyChallengeByUserId(int userId);

}
