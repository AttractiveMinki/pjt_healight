package com.ssafy.kiwi.model.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.kiwi.model.domain.entity.CertifyImage;

public interface CertifyImageRepository extends JpaRepository<CertifyImage, Integer> {
	
	int countByUserIdAndWithChallengeId(int userId, int withChallengeId);
	
	List<CertifyImage> getAllByUserIdAndWithChallengeId(int userId, int withChallengeId);

	List<CertifyImage> getAllByUserIdAndWithChallengeIdOrderByTimeDesc(int userId, int withChallengeId);

	List<CertifyImage> getAllByWithChallengeIdOrderByTimeDesc(int withChallengeId);
	
}
