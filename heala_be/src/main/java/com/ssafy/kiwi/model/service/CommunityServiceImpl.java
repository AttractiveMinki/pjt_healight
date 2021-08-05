package com.ssafy.kiwi.model.service;

import java.util.*;

import com.ssafy.kiwi.model.domain.entity.Comment;
import com.ssafy.kiwi.model.domain.entity.LikeUser;
import com.ssafy.kiwi.model.domain.repository.CommentRepository;
import com.ssafy.kiwi.model.domain.repository.LikeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.kiwi.model.domain.entity.Post;
import com.ssafy.kiwi.model.domain.repository.CommunityRepository;

@Service
public class CommunityServiceImpl implements CommunityService {
	
	@Autowired
	private CommunityRepository communityRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private LikeUserRepository likeUserRepository;

	// 커뮤니티 전체 글 목록 가져오기
	@Override
	public List<Post> getAllPostList() {
		final int access = 0; // 전체 공개: 0
		
		List<Post> postList = communityRepository.getPostByAccess(access);
		return postList;
	}
	
	// Best 글에 들어갈 좋아요 기준 설정
	private final int CRITERION = 100;

	// 카테고리와 서브 카테고리에 맞는 글 목록 가져오기
	@Override
	public List<Post> getPostList(int category, int subCategory) {
		List<Post> postList;
		final int access = 0; // 전체 공개: 0
		
		// Best 게시글: 3
		if (subCategory == 3) { 
			postList = communityRepository.getPostByCategoryAndAccessAndLikesGreaterThan(category, access, CRITERION);
		} else {
			postList = communityRepository.getPostByCategoryAndSubCategoryAndAccess(category, subCategory, access);
		}
		return postList;
	}

	// 커뮤니티 글 가져오기
	@Override
	public Optional<Post> getPost(int postId) {
		return communityRepository.findById(postId);
	}

	// 댓글 목록 가져오기
	@Override
	public Object getPostComments(int postId) {
		return commentRepository.findAllByPostId(postId);
	}

	// 댓글 작성
	@Override
	public void makeComment(Comment comment) {
		commentRepository.save(comment);
	}

	// 댓글 삭제
	@Override
	public void deleteComment(int commentId) {
		// 실패 시 에러 발생함
		commentRepository.deleteById(commentId);
		commentRepository.deleteAllByCommentId(commentId);
	}

	// 댓글 좋아요
	@Override
	public boolean likeComment(LikeUser likeUser) {
		likeUserRepository.save(likeUser);
		return true;
	}

	// 댓글 좋아요 취소
	@Override
	public boolean cancelLikeComment(LikeUser likeUser) {
		Optional<LikeUser> oldLikeUser = likeUserRepository.findByUserIdAndCommentId(likeUser.getUserId(), likeUser.getCommentId());
		if(oldLikeUser.isPresent()){
			likeUserRepository.deleteById(oldLikeUser.get().getId());
			return true;
		}
		return false;
	}
}
