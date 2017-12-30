/**
 * 
 */
package org.rdemirkoparan.forum.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.rdemirkoparan.forum.model.Answer;
import org.rdemirkoparan.forum.repository.AnswerRepository;
import org.rdemirkoparan.forum.service.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author recepd
 *
 */
@Service
public class AnswerServiceImpl implements AnswerService {

	private static final Logger logger = LoggerFactory.getLogger (AnswerServiceImpl.class);

	@Autowired
	private AnswerRepository answerRepository;

	/**
	 * Find all answers of given topic
	 */
	@Override
	public List<Answer> findByTopicId (Long topic_id) {
		return answerRepository.findByTopicId (topic_id);
	}

	/**
	 * Change useful switch
	 */
	@Override
	public void setUseful (boolean useful, Long id) {
		answerRepository.setUseful (useful, id);
		logger.debug ("Answer useful status changed. New status : ", useful);
	}

	/**
	 * Delete answer
	 */
	@Override
	public void deleteById (Long id) {
		answerRepository.deleteById (id);
		logger.debug ("Answer deleted! : ", id);
	}

	/**
	 * Save new answer
	 */
	@Override
	public void save (Answer answer) {
		answer.setCreatedDate (LocalDateTime.now ());
		answer.setUseful (false);
		answerRepository.save (answer);
		logger.debug ("Answer saved! : ", answer.getId ());
	}

	/**
	 * Find given user's answers with descending date ordered
	 */
	@Override
	public List<Answer> findByUserIdOrderByCreatedDateDesc (long user_id) {
		return answerRepository.findByUserIdOrderByCreatedDateDesc (user_id);
	}

	/**
	 * Find given user's helpful answers with descending date ordered
	 */
	@Override
	public List<Answer> findByUserIdAndUsefulOrderByCreatedDateDesc (long user_id, boolean useful) {
		return answerRepository.findByUserIdAndUsefulOrderByCreatedDateDesc (user_id, useful);
	}

	/**
	 * Count given user's answers
	 */
	@Override
	public Long countByUserId (Long user_id) {
		return answerRepository.countByUserId (user_id);
	}

	/**
	 * Count given user's helpful answers
	 */
	@Override
	public Long countByUserIdAndUseful (Long user_id, boolean useful) {
		return answerRepository.countByUserIdAndUseful (user_id, useful);
	}

	/**
	 * Count given topic's answers
	 */
	@Override
	public Long countByTopicId (Long topic_id) {
		return answerRepository.countByTopicId (topic_id);
	}

}
