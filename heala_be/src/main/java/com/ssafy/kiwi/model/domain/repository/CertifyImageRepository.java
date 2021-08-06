package com.ssafy.kiwi.model.domain.repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.kiwi.model.domain.entity.CertifyImage;
import com.ssafy.kiwi.model.dto.UserSimpleOp;

public interface CertifyImageRepository extends JpaRepository<CertifyImage, Integer> {
	
	int countByUserIdAndWithChallengeId(int userId, int withChallengeId);
	
	List<CertifyImage> getAllByUserIdAndWithChallengeId(int userId, int withChallengeId);

	List<CertifyImage> getAllByUserIdAndWithChallengeIdOrderByTimeDesc(int userId, int withChallengeId);
	
//	@Query(value = "SELECT DISTINCT new com.ssafy.kiwi.model.dto.CertifyImageTimeOp(ci.time) FROM CertifyImage ci WHERE ci.user_id = :userId AND ci.with_challenge_id = :withChallengeId")
//	List<CertifyImageTimeOp> getTimesByUserIdAndWithChallengeId(int userId, int withChallengeId);
	
//	@Query(value = "SELECT DISTINCT * FROM CertifyImage ci WHERE ci.user_id = :userId AND ci.with_challenge_id = :withChallengeId")
//	List<CertifyImageTimeOp> getTimeByUserIdAndWithChallengeId(int userId, int withChallengeId);
	
//	@Query(value = "SELECT new com.ssafy.kiwi.model.dto.CertifyImageTimeOp(u.id, u.name, u.image) FROM User u WHERE u.id IN (:userIdSet)")
//	@Query(value = "SELECT new com.ssafy.kiwi.model.dto.UserSimpleOp(u.id, u.name, u.image) FROM User u WHERE u.id IN (:userIdSet)")
//	List<UserSimpleOp> getUserSimpleInfoByIds(
	
}
