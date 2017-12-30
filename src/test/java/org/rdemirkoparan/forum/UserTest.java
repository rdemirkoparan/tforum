package org.rdemirkoparan.forum;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rdemirkoparan.forum.model.User;
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
public class UserTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	/**
	 * check if user added successfully
	 */
	@Test
	public void whenFindByUsername_thenReturnUser () {
		// given
		User test1 = new User ();
		test1.setUsername ("test");
		test1.setPassword ("test1");
		entityManager.persist (test1);
		entityManager.flush ();

		// when
		User found = userRepository.findByUsername (test1.getUsername ());

		// then
		assertEquals (found.getUsername (), test1.getUsername ());
	}

}
