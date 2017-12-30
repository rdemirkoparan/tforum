package org.rdemirkoparan.forum.service;

import java.util.List;

import org.rdemirkoparan.forum.model.Answer;

/**
 * @author recepd
 *
 */
public interface AnswerService {

	List<Answer> findByTopicId (Long topic_id);

	void setUseful (boolean useful, Long id);

	void deleteById (Long id);

	void save (Answer answer);

	List<Answer> findByUserIdOrderByCreatedDateDesc (long user_id);

	List<Answer> findByUserIdAndUsefulOrderByCreatedDateDesc (long user_id, boolean useful);

	Long countByUserId (Long user_id);

	Long countByUserIdAndUseful (Long user_id, boolean useful);

	Long countByTopicId (Long topic_id);
}
