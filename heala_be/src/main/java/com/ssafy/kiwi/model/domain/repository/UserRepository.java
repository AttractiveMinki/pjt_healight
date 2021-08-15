package com.ssafy.kiwi.model.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.kiwi.model.domain.entity.User;
import com.ssafy.kiwi.model.dto.UserFollowOp;
import com.ssafy.kiwi.model.dto.UserSimpleOp;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	User getUserById(int id);

	// identity로 회원 조회
	Optional<User> getUserByIdentity(String identity);
	
	// email로 회원 조회
	Optional<User> getUserByEmail(String email);
    
	// identity와 password가 일치하는 회원 조회
    Optional<User> getUserByIdentityAndPassword(String identity, String password);

    @Query(value = "SELECT new com.ssafy.kiwi.model.dto.UserSimpleOp(u.id, u.name, u.image) FROM User u WHERE u.id = ?1")
	UserSimpleOp getUserSimpleInfoById(int userId);

    @Query(value = "SELECT new com.ssafy.kiwi.model.dto.UserSimpleOp(u.id, u.name, u.image) FROM User u WHERE u.id IN (:userIdSet)")
	List<UserSimpleOp> getUserSimpleInfoByIds(List<Integer> userIdSet);

    @Query(value = "SELECT u.exp FROM User u WHERE u.id = :userId")
	Integer getUserExpByUserId(int userId);
    
    // 팔로잉, 팔로워 목록
    @Query(value = "SELECT new com.ssafy.kiwi.model.dto.UserFollowOp(u.id, u.identity, u.name, u.image) FROM User u WHERE u.id IN (:userIdSet)")
    List<UserFollowOp> getUserFollowOpByIds(List<Integer> userIdSet);

    // id로 유저 존재여부 확인 (1:존재, 0:미존재)
	int countById(int id);
	
	// 해당 단어가 identity 또는 name에 포함된 회원 조회 -> 여기에서도 필요한 op라서 UserFollowOp 객체 이름 바꾸는 것 좋을듯!
	@Query(value = "SELECT new com.ssafy.kiwi.model.dto.UserFollowOp(u.id, u.identity, u.name, u.image) FROM User u WHERE u.identity LIKE %:word% OR u.name LIKE %:word% AND u.id != :userId")
	Page<UserFollowOp> getUserListByWord(@Param("userId") int userId, @Param("word") String word, Pageable page);
	
}
