package com.ssafy.kiwi.model.domain.authentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.kiwi.model.domain.authentication.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByIdentity(String identity);
    boolean existsByIdentity(String identity);
}