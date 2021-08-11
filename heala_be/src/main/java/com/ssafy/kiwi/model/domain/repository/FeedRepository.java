package com.ssafy.kiwi.model.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.kiwi.model.domain.entity.Post;

public interface FeedRepository extends JpaRepository<Post,Integer>{
	
	// 자신이 팔로우 하고 있는 사람들의 Post 목록을 가져옴(공개 범위가 전체로 되어있는 것 + 양쪽 팔로우를 하고 있는 경우 친구 공개 Post까지)
	@Query(value = "SELECT * FROM post WHERE (user_id IN (:onewayfollowIdList) AND access = 0) or (user_id IN (:followForFollowIdList) AND access in (0,1))", nativeQuery = true)
	Page<Post> getByFollowAndAccess(List<Integer> onewayfollowIdList, List<Integer> followForFollowIdList, Pageable pageable);

	List<Post> getByUserId(int userId);
}
