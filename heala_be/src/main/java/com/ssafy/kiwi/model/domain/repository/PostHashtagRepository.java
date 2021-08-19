package com.ssafy.kiwi.model.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.kiwi.model.domain.entity.PostHashtag;

public interface PostHashtagRepository extends JpaRepository<PostHashtag, Integer>{

	void deleteAllByPostId(int postId);
	
	// post와 연결된 해시태그 아이디 조회
	@Query(value = "SELECT hashtag_id FROM post_hashtag WHERE post_id = :postId", nativeQuery = true)
	List<Integer> getHashtagIdsByPostId(int postId);

}
