package org.rdemirkoparan.forum.repository;

import java.util.List;

import org.rdemirkoparan.forum.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author recepd
 *
 */
public interface AnswerRepository extends JpaRepository<Answer, Long> {

	@Modifying
	@Transactional
	@Query("UPDATE Answer a SET a.useful = :bool WHERE a.id = :id")
	void setUseful (@Param("bool") Boolean bool, @Param("id") Long id);

	@Transactional
	void deleteById (Long id);

	Long countByUserId (Long user_id);

	Long countByUserIdAndUseful (Long user_id, boolean useful);

	Long countByTopicId (Long topic_id);

	List<Answer> findByUserIdOrderByCreatedDateDesc (Long user_id);

	List<Answer> findByUserIdAndUsefulOrderByCreatedDateDesc (Long user_id, boolean useful);

	List<Answer> findByTopicId (Long topic_id);
}
