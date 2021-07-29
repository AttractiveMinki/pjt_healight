package com.ssafy.healight.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.healight.domain.entity.KiwiUser;

public interface KiwiUserRepository extends JpaRepository<KiwiUser, Integer>{

//	Object getKiwiUserByCategoryAndUserId(int category, int user_id);

}
