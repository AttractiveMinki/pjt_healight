package com.ssafy.kiwi.model.service;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ssafy.kiwi.model.domain.entity.Comment;
import com.ssafy.kiwi.model.domain.entity.LikeUser;
import com.ssafy.kiwi.model.domain.entity.Post;
import com.ssafy.kiwi.model.domain.entity.Scrap;
import com.ssafy.kiwi.model.domain.repository.CommentRepository;
import com.ssafy.kiwi.model.domain.repository.CommunityRepository;
import com.ssafy.kiwi.model.domain.repository.LikeUserRepository;
import com.ssafy.kiwi.model.domain.repository.ScrapRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommunityServiceImpl implements CommunityService {
	
	final private CommunityRepository communityRepository;
	final private CommentRepository commentRepository;
	final private LikeUserRepository likeUserRepository;
	final private ScrapRepository scrapRepository;

	// 커뮤니티 전체 글 목록 가져오기
	@Override
	public List<Post> getAllPostList(int page) {
		final int access = 0; // 전체 공개: 0
		
		Page<Post> postPage = communityRepository.getPostByAccess(access, PageRequest.of(page, 10, Sort.by("createdAt").descending()));
		List<Post> postList = postPage.getContent();
		return postList;
	}
	
	// Best 글에 들어갈 좋아요 기준 설정
	private final int CRITERION = 100;

	// 카테고리와 서브 카테고리에 맞는 글 목록 가져오기
	@Override
	public List<Post> getPostList(int category, int subCategory, int page) {
		Page<Post> postPage;
		final int access = 0; // 전체 공개: 0
		
		// Best 게시글: 3
		if (subCategory == 3) { 
			postPage = communityRepository.getPostByCategoryAndAccessAndLikesGreaterThan(category, access, CRITERION, PageRequest.of(page, 10, Sort.by("createdAt").descending()));
		} else {
			postPage = communityRepository.getPostByCategoryAndSubCategoryAndAccess(category, subCategory, access, PageRequest.of(page, 10, Sort.by("createdAt").descending()));
		}
		List<Post> postList = postPage.getContent();
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
		if(!commentRepository.findById(likeUser.getCommentId()).isPresent()){
			return false;
		}
		if(likeUserRepository.findByUserIdAndCommentId(likeUser.getUserId(), likeUser.getCommentId()).isPresent()){
			return false;
		}
		Optional<Comment> comment = commentRepository.findById(likeUser.getCommentId());
		if(comment.isPresent()){
			Comment newComment = comment.get();
			newComment.increaseLikes();
			commentRepository.save(newComment);
		}
		likeUserRepository.save(likeUser);
		return true;
	}

	// 댓글 좋아요 취소
	@Override
	public boolean cancelLikeComment(int userId, int commentId) {
		Optional<LikeUser> oldLikeUser = likeUserRepository.findByUserIdAndCommentId(userId, commentId);
		if(oldLikeUser.isPresent()){
			likeUserRepository.deleteById(oldLikeUser.get().getId());
			Optional<Comment> comment = commentRepository.findById(commentId);
			if(comment.isPresent()){
				Comment newComment = comment.get();
				newComment.decreaseLikes();
				commentRepository.save(newComment);
			}
			return true;
		}
		return false;
	}
	
	// 댓글 개수 세기
	@Override
	public int countComment(int postId) {
		return commentRepository.countCommentByPostId(postId);
	}

	// 게시글 좋아요
	@Override
	public boolean likePost(LikeUser likeUser) {
		if(!communityRepository.findById(likeUser.getPostId()).isPresent()){
			return false;
		}
		if(getLike(likeUser.getPostId(), likeUser.getUserId())) {
			return false;
		}
		Optional<Post> post = communityRepository.findById(likeUser.getPostId());
		if(post.isPresent()){
			Post newPost = post.get();
			newPost.increaseLikes();
			communityRepository.save(newPost);
		}
		likeUserRepository.save(likeUser);
		return true;
	}

	// 게시글 좋아요 취소
	@Override
	public boolean cancelLikePost(int userId, int postId) {
		Optional<LikeUser> oldLikeUser = likeUserRepository.getByUserIdAndPostId(userId, postId);
		if(oldLikeUser.isPresent()){
			likeUserRepository.deleteById(oldLikeUser.get().getId());
			Optional<Post> post = communityRepository.findById(postId);
			if(post.isPresent()){
				Post newPost = post.get();
				newPost.decreaseLikes();
				communityRepository.save(newPost);
			}
			return true;
		}
		return false;
	}

	// 게시글 스크랩
	@Override
	public boolean scrapPost(Scrap scrap) {
		if(!communityRepository.findById(scrap.getPostId()).isPresent()){
			return false;
		}
		if(getScrap(scrap.getPostId(), scrap.getUserId())){
			return false;
		}
		scrapRepository.save(scrap);
		return true;
	}

	// 게시글 스크랩 취소
	@Override
	public boolean cancelScrapPost(int userId, int postId) {
		Optional<Scrap> oldScrap = scrapRepository.getByUserIdAndPostId(userId, postId);
		if(oldScrap.isPresent()){
			scrapRepository.deleteById(oldScrap.get().getId());
			return true;
		}
		return false;
	}

	// 게시글 스크랩 여부 조회
	@Override
	public boolean getScrap(int postId, int userId) {
		Optional<Scrap> scrap = scrapRepository.getByUserIdAndPostId(userId, postId);
		return scrap.isPresent();
	}

	// 게시글 좋아요 여부 조회
	@Override
	public boolean getLike(int postId, int userId) {
		Optional<LikeUser> likeUser = likeUserRepository.getByUserIdAndPostId(userId, postId);
		return likeUser.isPresent();
	}

	// 전체 글 중 단어를 제목 또는 내용에 포함하는 글 목록 반환
	@Override
	public List<Post> getAllPostListByWord(String word) {
		final int access = 0; // 전체 공개: 0
		List<Post> searchPostList = communityRepository.getAllPostByWordAndAccess(word, access);
		return searchPostList;
	}
}
