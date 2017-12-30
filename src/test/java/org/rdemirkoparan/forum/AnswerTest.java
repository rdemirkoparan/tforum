package org.rdemirkoparan.forum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rdemirkoparan.forum.model.Answer;
import org.rdemirkoparan.forum.model.Topic;
import org.rdemirkoparan.forum.model.User;
import org.rdemirkoparan.forum.repository.AnswerRepository;
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
public class AnswerTest {

	private static long topicId;

	private static final String username1 = "test1";
	private static final String username2 = "test2";

	private static final String answerContent = "answer1";

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Before
	public void setUp () {
		User test1 = new User ();
		test1.setUsername (username1);
		test1.setPassword ("test1");
		entityManager.persist (test1);
		entityManager.flush ();

		User test2 = new User ();
		test2.setUsername (username2);
		test2.setPassword ("test2");
		entityManager.persist (test2);
		entityManager.flush ();

		Topic topic = new Topic ();
		topic.setTitle ("topic1");
		topic.setContent ("test content");
		topic.setUser (test1);
		entityManager.persist (topic);
		entityManager.flush ();

		topicId = topic.getId ();

		// given
		Answer answer = new Answer ();
		answer.setContent (answerContent);
		answer.setTopic (topic);
		User user = userRepository.findByUsername (username2);
		answer.setUser (user);
		entityManager.persist (answer);
		entityManager.flush ();
	}

	/**
	 * New answer added by user2 to user1's topic
	 */
	@Test
	public void whenUserAddedAnswer2Topic () {
		// when
		List<Answer> found = answerRepository.findByTopicId (topicId);

		// then
		assertFalse (found.isEmpty ());
		assertEquals (answerContent, found.get (0).getContent ());
	}

	/**
	 * user1 voted user2's answer
	 */
	@Test
	public void whenUserFoundHelpfulOtherUsersAnswer () {
		// when
		List<Answer> answers = answerRepository.findByTopicId (topicId);

		// then 1
		assertFalse (answers.isEmpty ());

		Answer answer = answers.get (0);
		answer.setUseful (true);

		entityManager.persist (answer);
		entityManager.flush ();

		Answer found = answerRepository.findOne (answer.getId ());

		// then 2
		assertEquals (answerContent, found.getContent ());
		assertTrue (found.isUseful ());
	}

	/**
	 * user deleted answer
	 */
	@Test
	public void whenUserDeletedAnswer () {
		// when
		List<Answer> answers = answerRepository.findByTopicId (topicId);

		// then 1
		assertFalse (answers.isEmpty ());

		Long tmpAnswerId = answers.get (0).getId ();

		answerRepository.deleteById (tmpAnswerId);

		Answer found = answerRepository.findOne (tmpAnswerId);

		// then 2
		assertNull (found);
	}

}
