package com.ssafy.kiwi.model.service;

import java.util.List;
import java.util.Optional;

import com.ssafy.kiwi.model.domain.entity.Post;
import com.ssafy.kiwi.model.domain.entity.Comment;
import com.ssafy.kiwi.model.domain.entity.LikeUser;
import com.ssafy.kiwi.model.domain.entity.Scrap;

public interface CommunityService {
	
	// 전체 글 목록 반환하기
	List<Post> getAllPostList(int page);

	// 카테고리(운동,식단,마음)와 서브 카테고리(일반, Best, 정보, 질문)에 따라 글 목록 반환하기
	List<Post> getPostList(int category, int subCategory, int page);

	// 커뮤니티 글 가져오기
	Optional<Post> getPost(int postId);

    // 게시글 상세페이지: 댓글 목록 반환
	Object getPostComments(int postId);

	// 댓글 작성
	void makeComment(Comment comment);

	// 댓글 삭제
	void deleteComment(int commentId);

	// 댓글 좋아요
	boolean likeComment(LikeUser likeUser);

	// 댓글 좋아요 취소
	boolean cancelLikeComment(int userId, int commentId);
	
	// 댓글 개수 세기
	int countComment(int postId);

	// 게시글 좋아요
	boolean likePost(LikeUser likeUser);

	// 게시글 좋아요 취소
	boolean cancelLikePost(int userId, int postId);

	// 게시글 스크랩
	boolean scrapPost(Scrap scrap);

	// 게시글 스크랩 취소
	boolean cancelScrapPost(int userId, int postId);

	// 게시글 스크랩 여부 조회
	boolean getScrap(int postId, int userId);

	// 게시글 좋아요 여부 조회
	boolean getLike(int postId, int userId);

}
