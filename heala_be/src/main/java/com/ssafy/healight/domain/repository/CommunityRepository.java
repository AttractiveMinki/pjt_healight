package com.ssafy.healight.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.healight.domain.entity.Post;

public interface CommunityRepository extends JpaRepository<Post,Integer>{

	List<Post> findAll();
	List<Post> getPostByCategoryAndSubCategory(int category, int subCategory);
	List<Post> getPostByCategoryAndLikesGreaterThan(int category, int criterion);
	
}
