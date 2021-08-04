package com.ssafy.kiwi.model.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.kiwi.model.domain.entity.Post;

public interface CommunityRepository extends JpaRepository<Post,Integer>{

	List<Post> findAll();
	List<Post> getPostByCategoryAndSubCategory(int category, int subCategory);
	List<Post> getPostByCategoryAndLikesGreaterThan(int category, int criterion);
	Optional<Post> getPostById(int postId);
	
}
