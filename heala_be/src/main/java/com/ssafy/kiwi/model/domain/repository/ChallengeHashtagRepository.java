package com.ssafy.kiwi.model.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.kiwi.model.domain.entity.ChallengeHashtag;

public interface ChallengeHashtagRepository extends JpaRepository<ChallengeHashtag,Integer>{
	
	// word가 일치하는 해시태그 조회
	Optional<ChallengeHashtag> getChallengeHashtagByWord(String word);
	
	List<ChallengeHashtag> getAllById(int id);
}
