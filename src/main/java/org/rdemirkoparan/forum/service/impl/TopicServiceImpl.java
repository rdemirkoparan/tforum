/**
 * 
 */
package org.rdemirkoparan.forum.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.rdemirkoparan.forum.model.Topic;
import org.rdemirkoparan.forum.repository.TopicRepository;
import org.rdemirkoparan.forum.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author recepd
 *
 */
@Service
public class TopicServiceImpl implements TopicService {

	private static final Logger logger = LoggerFactory.getLogger (TopicServiceImpl.class);

	@Autowired
	private TopicRepository topicRepository;

	/**
	 * Find topic bu id
	 */
	@Override
	public Topic findById (Long id) {
		return topicRepository.findById (id);
	}

	/**
	 * Find all topics sorted (descending)
	 */
	@Override
	public List<Topic> findAll (Sort sort) {
		return topicRepository.findAll (sort);
	}

	/**
	 * Find user's topics
	 */
	@Override
	public List<Topic> findTopicsByUserIdOrderByCreatedDateDesc (Long user_id) {
		return topicRepository.findByUserIdOrderByCreatedDateDesc (user_id);
	}

	/**
	 * Save topic
	 */
	@Override
	public void save (Topic topic) {
		topic.setCreatedDate (LocalDateTime.now ());
		topicRepository.save (topic);
		logger.debug ("Topic saved! : ", topic.getId ());
	}

	/**
	 * Count user's topics
	 */
	@Override
	public Long countByUserId (Long user_id) {
		return topicRepository.countByUserId (user_id);
	}

}
