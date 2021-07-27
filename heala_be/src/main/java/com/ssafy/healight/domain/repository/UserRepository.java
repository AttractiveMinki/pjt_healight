package com.ssafy.healight.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.healight.domain.entity.User;


public interface UserRepository extends JpaRepository<User,Integer>{
	
	// identity로 회원 조회
	Optional<User> getUserByIdentity(String identity);
	
	// email로 회원 조회
	Optional<User> getUserByEmail(String email);
	
//	// identity로 User 객체 가져오기
//    Optional<User> getUserByIdentity(String identity);
//    
//    // email로 User 객체 가져오기
//    Optional<User> getUserByEmail(String email);
    
    
//  Optional<User> findUserByIdentityAndPassword(String identity, String password);
 
	
//	   // 이메일로 회원 조회
//    @Query("select u from User u where email = :email")
//    Optional<User> getUserByEmail(String email);
//    
//    // 닉네임으로 회원 조회
//    @Query("select u from User u where nickname = :nickname")
//    Optional<User> getUserByNickname(String nickname);
//
//    // 로그인
//    @Query("select u from User u where email = :email and password = :password")
//    Optional<User> findUserByEmailAndPassword(String email, String password);
}
