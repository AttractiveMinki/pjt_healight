package com.ssafy.kiwi.model.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.kiwi.model.domain.entity.Post;

public interface CommunityRepository extends JpaRepository<Post,Integer>{

	Page<Post> getPostByAccess(int access, Pageable pageable);
	Page<Post> getPostByCategoryAndSubCategoryAndAccess(int category, int subCategory, int access, Pageable pageable);
	Page<Post> getPostByCategoryAndAccessAndLikesGreaterThan(int category, int access, int criterion, Pageable pageable);
	
	long countByCategoryAndUserId(int category, int userId);
	long countByCategoryAndUserIdAndSubCategory(int category, int userId, int subCategory);
	
	@Query(value = "select max(p.likes) from Post p where p.category = :category and p.userId = :userId")
	int getMaxLikeByCategoryAndUserId(int category, int userId);
	@Query(value = "select sum(p.likes) from Post p where p.category = :category and p.userId = :userId")
	int getSumLikeByCategoryAndUserId(int category, int userId);
	
	@Query(value = "SELECT * from post p WHERE (p.title LIKE %:word%) OR (p.content LIKE %:word%) "
			+ "OR (p.id in (SELECT post_id FROM post_hashtag ph JOIN hashtag h ON ph.hashtag_id = h.id WHERE h.word LIKE %:word%))"
			+ "AND access = :access", nativeQuery = true)
	List<Post> getAllPostByWordAndAccess(String word, int access);
}
