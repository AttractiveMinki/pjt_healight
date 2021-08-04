package com.ssafy.kiwi.model.domain.repository;

//import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.kiwi.model.domain.entity.WithChallenge;

public interface WithChallengeRepository extends JpaRepository<WithChallenge,Integer>{
	
	// category 일치하는 WithChallenge 목록 가져오기
	List<WithChallenge> getByCategory(int category);
	
	// id 일치하는 WithChallenge 가져오기
	WithChallenge getById(int id);
	
	// id 일치하는 WithChallenge 가져오기
	Optional<WithChallenge> findWithChallengeById(int id);

}
