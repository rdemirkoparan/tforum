package org.rdemirkoparan.forum.service;

import java.util.List;

import org.rdemirkoparan.forum.model.Topic;
import org.springframework.data.domain.Sort;

/**
 * @author recepd
 *
 */
public interface TopicService {

	Topic findById (Long id);

	List<Topic> findAll (Sort sort);

	List<Topic> findTopicsByUserIdOrderByCreatedDateDesc (Long user_id);

	void save (Topic topic);

	Long countByUserId (Long user_id);
}
