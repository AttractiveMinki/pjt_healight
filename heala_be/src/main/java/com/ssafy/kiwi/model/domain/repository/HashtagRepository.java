package com.ssafy.kiwi.model.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.kiwi.model.domain.entity.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag, Integer> {

	// word가 일치하는 해시태그 조회
	Optional<Hashtag> getHashtagByWord(String word);
}
