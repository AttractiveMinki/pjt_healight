package com.ssafy.kiwi.model.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.kiwi.model.domain.entity.User;


public interface UserRepository extends JpaRepository<User,Integer>{
	
	User getUserById(int id);

	// identity로 회원 조회
	Optional<User> getUserByIdentity(String identity);
	
	// email로 회원 조회
	Optional<User> getUserByEmail(String email);
    
	// identity와 password가 일치하는 회원 조회
    Optional<User> getUserByIdentityAndPassword(String identity, String password);
 
}
