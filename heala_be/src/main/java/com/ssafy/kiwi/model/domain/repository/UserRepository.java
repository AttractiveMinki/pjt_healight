package com.ssafy.kiwi.model.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.kiwi.model.domain.authentication.entity.Member;
import com.ssafy.kiwi.model.dto.UserFollowOp;
import com.ssafy.kiwi.model.dto.UserSimpleOp;

public interface UserRepository extends JpaRepository<Member,Integer>{
	
	Member getUserById(int id);

	// identity로 회원 조회
	Optional<Member> getUserByIdentity(String identity);
	
	// email로 회원 조회
	Optional<Member> getUserByEmail(String email);
    
	// identity와 password가 일치하는 회원 조회
    Optional<Member> getUserByIdentityAndPassword(String identity, String password);

    @Query(value = "SELECT new com.ssafy.kiwi.model.dto.UserSimpleOp(u.id, u.name, u.image) FROM Member u WHERE u.id = ?1")
	UserSimpleOp getUserSimpleInfoById(int userId);

    @Query(value = "SELECT new com.ssafy.kiwi.model.dto.UserSimpleOp(u.id, u.name, u.image) FROM Member u WHERE u.id IN (:userIdSet)")
	List<UserSimpleOp> getMemberSimpleInfoByIds(List<Integer> userIdSet);

    @Query(value = "SELECT u.exp FROM Member u WHERE u.id = :userId")
	Integer getMemberExpByUserId(int userId);
    
    // 팔로잉, 팔로워 목록
    @Query(value = "SELECT new com.ssafy.kiwi.model.dto.UserFollowOp(u.id, u.identity, u.name, u.image) FROM Member u WHERE u.id IN (:userIdSet)")
    List<UserFollowOp> getMemberFollowOpByIds(List<Integer> userIdSet);

    // id로 유저 존재여부 확인 (1:존재, 0:미존재)
	int countById(int id);

	//성별 가져오기
	@Query(value = "SELECT u.gender FROM Member u WHERE u.id = :userId")
	Boolean getGenderByMemberId(int userId);
	
	// 해당 단어가 identity 또는 name에 포함된 회원 조회
	@Query(value = "SELECT new com.ssafy.kiwi.model.dto.UserFollowOp(u.id, u.identity, u.name, u.image) FROM Member u WHERE (u.identity LIKE %:word% OR u.name LIKE %:word%)")
	Page<UserFollowOp> getMemberListByWord(String word, Pageable page);
	
}
