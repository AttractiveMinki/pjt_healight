package com.ssafy.healight.domain.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.healight.domain.entity.WithChallenge;

public interface WithChallengeRepository extends JpaRepository<WithChallenge,Integer>{
	
	// title, category, start_date, end_date, certify_info, introduction가 일치하는 ChallengeHashtag 행이 있는지 조회
	Optional<WithChallenge> getWithChallengeByTitleAndCategoryAndStartDateAndEndDateAndCertifyInfoAndIntroduction(String title, int category, Date start_date, Date end_date, String certify_info, String introduction);
		
}