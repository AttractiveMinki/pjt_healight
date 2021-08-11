package com.ssafy.kiwi.model.domain.repository;

import com.ssafy.kiwi.model.domain.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Integer> {

    Optional<Follow> findFirstByFollowIdAndUserId(int followId, int userId);
    
    // id가 userId에 해당하는 Follow 객체 반환 
    List<Follow> getFollowByUserId(int userId);
    
    // 나만 follow 하고 있는 Follow 객체의 id 리스트 반환(단방향)
    @Query(value = "select follow_id from follow WHERE follow_id not in (SELECT user_id FROM follow WHERE follow_id = :userId) and user_id = :userId", nativeQuery = true)
    List<Integer> getOnewayFollowIdsByUserId(int userId);
    
    // 서로 follow 하고 있는 Follow 객체의 id 리스트 반환(양방향)
    @Query(value = "select follow_id from follow WHERE follow_id in (SELECT user_id FROM follow WHERE follow_id = :userId) and user_id = :userId", nativeQuery = true)
    List<Integer> getFollowForFollowByUserId(int userId);

    //팔로잉수
	int countByUserId(int userId);
	//팔로워수
	int countByFollowId(int followId);
    
}
