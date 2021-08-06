package com.ssafy.kiwi.model.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.kiwi.model.domain.entity.User;
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
}
