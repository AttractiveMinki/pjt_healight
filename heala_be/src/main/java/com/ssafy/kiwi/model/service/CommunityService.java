package com.ssafy.kiwi.model.service;

import com.ssafy.kiwi.model.domain.entity.Comment;
import com.ssafy.kiwi.model.domain.entity.LikeUser;

public interface CommunityService {
	
	// 전체 글 목록 반환하기
	Object getAllPostList();

	// 카테고리(운동,식단,마음)와 서브 카테고리(일반, Best, 정보, 질문)에 따라 글 목록 반환하기
	Object getPostList(int category, int subCategory);

	// 게시글 상세페이지 반환
    Object getPost(int postId);

    // 게시글 상세페이지: 댓글 목록 반환
	Object getPostComments(int postId);

	// 댓글 작성
	void makeComment(Comment comment);

	// 댓글 삭제
	void deleteComment(int commentId);

	// 댓글 좋아요
	Object likeComment(LikeUser likeUser);

	// 댓글 좋아요 취소
	boolean cancelLikeComment(LikeUser likeUser);
}
