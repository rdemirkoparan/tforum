package org.rdemirkoparan.forum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rdemirkoparan.forum.model.Topic;
import org.rdemirkoparan.forum.model.User;
import org.rdemirkoparan.forum.repository.TopicRepository;
import org.rdemirkoparan.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author recepd
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TopicTest {

	private static final String username = "test1";

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TopicRepository topicRepository;

	@Before
	public void setUp () {
		User test1 = new User ();
		test1.setUsername (username);
		test1.setPassword ("test1");
		entityManager.persist (test1);
		entityManager.flush ();
	}

	/**
	 * user added topic
	 */
	@Test
	public void whenUserAddedTopic () {
		// given
		Topic topic = new Topic ();
		topic.setTitle ("topic1");
		topic.setContent ("test content");
		User user = userRepository.findByUsername (username);
		topic.setUser (user);
		entityManager.persist (topic);
		entityManager.flush ();

		// when
		List<Topic> found = topicRepository.findByUserIdOrderByCreatedDateDesc (user.getId ());

		// then
		assertFalse (found.isEmpty ());
		assertEquals (found.get (0).getTitle (), topic.getTitle ());
	}

}
