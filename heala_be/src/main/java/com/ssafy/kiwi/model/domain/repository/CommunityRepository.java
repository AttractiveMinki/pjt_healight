package com.ssafy.kiwi.model.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.kiwi.model.domain.entity.Post;

public interface CommunityRepository extends JpaRepository<Post,Integer>{

	List<Post> getPostByAccess(int access);
	List<Post> getPostByCategoryAndSubCategoryAndAccess(int category, int subCategory, int access);
	List<Post> getPostByCategoryAndAccessAndLikesGreaterThan(int category, int access, int criterion);
	
}
