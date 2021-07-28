package com.ssafy.healight.domain.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.healight.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User getUserById(int id);

}
