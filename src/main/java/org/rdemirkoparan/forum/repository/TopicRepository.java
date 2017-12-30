package org.rdemirkoparan.forum.repository;

import java.util.List;

import org.rdemirkoparan.forum.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author recepd
 *
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {

	Long countByUserId (Long user_id);

	Topic findById (Long id);

	List<Topic> findByUserIdOrderByCreatedDateDesc (Long user_id);
}
