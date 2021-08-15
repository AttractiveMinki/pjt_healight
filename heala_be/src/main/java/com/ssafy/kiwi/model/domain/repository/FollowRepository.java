package com.ssafy.kiwi.model.domain.repository;

import com.ssafy.kiwi.model.domain.entity.Follow;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Integer> {

    Optional<Follow> findFirstByFollowIdAndUserId(int followId, int userId);
    
    // id가 userId에 해당하는 Follow 객체 반환 
    List<Follow> getFollowByUserId(int userId);
    
    // 내가 follow 하고 있는 Follow 객체의 id 리스트 반환(단방향) : 맞팔 제외
    @Query(value = "select follow_id from follow WHERE follow_id not in (SELECT user_id FROM follow WHERE follow_id = :userId) and user_id = :userId", nativeQuery = true)
    List<Integer> getOnewayFollowIdsByUserId(int userId);
    
    // 맞팔 : 서로 follow 하고 있는 Follow 객체의 id 리스트 반환(양방향)
    @Query(value = "select follow_id from follow WHERE follow_id in (SELECT user_id FROM follow WHERE follow_id = :userId) and user_id = :userId", nativeQuery = true)
    List<Integer> getFollowForFollowByUserId(int userId);
    
    

    // 팔로잉수
	int countByUserId(int userId);
	// 팔로워수
	int countByFollowId(int followId);
	
    // 팔로워 : 나를 follow 하고 있는 Follow 객체의 id 리스트 반환
    @Query(value = "select user_id from follow WHERE follow_id = :userId", nativeQuery = true)
    Page<Integer> getFollowerIdsByUserId(int userId, Pageable pageable);
    
    // 팔로잉 : 내가 follow 하고 있는 Follow 객체의 id 리스트 반환
    @Query(value = "select follow_id from follow WHERE user_id = :userId", nativeQuery = true)
    Page<Integer> getFollowingIdsByUserId(int userId, Pageable pageable);

    // 내가 상대를 팔로우 하는지 확인 (1:팔로잉, 0:팔로우하지 않음)
	int countByFollowIdAndUserId(int id, int myId);
	
	// 맞팔 확인 (2:맞팔)
	@Query(value="select count(*) from Follow where (userId = :userId and followId = :myId) or (userId = :myId and followId = :userId)")
	int countFollowState(int userId, int myId);
    
}
