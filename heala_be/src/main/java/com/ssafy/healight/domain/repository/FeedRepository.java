package com.ssafy.healight.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.healight.domain.entity.Post;

public interface FeedRepository extends JpaRepository<Post,Integer>{

}
