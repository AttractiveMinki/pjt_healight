package com.ssafy.healight.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.healight.domain.entity.ChallengeHashtag;

public interface ChallengeHashtagRepository extends JpaRepository<ChallengeHashtag,Integer>{
	
	// word가 일치하는 해시태그 조회
	Optional<ChallengeHashtag> getChallengeHashtagByWord(String word);

}
