package com.ssafy.kiwi.model.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.kiwi.model.domain.entity.MyChallenge;

@Repository
public interface MyChallengeRepository extends JpaRepository<MyChallenge, Integer> {

	// userId에 일치하는 함께 챌린지 id들을 리스트로 가져옴
	List<MyChallenge> getMyChallengeByUserId(int userId);

	// userId와 challengeId가 일치하는 MyChallenge 객체 가져오기
	Optional<MyChallenge> getMyChallengeByUserIdAndWithChallengeId(int userId, int withChallengeId);
	
	int countByWithChallengeId(int withChallengeId);
}
