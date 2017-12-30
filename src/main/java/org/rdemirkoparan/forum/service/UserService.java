package org.rdemirkoparan.forum.service;

import org.rdemirkoparan.forum.model.User;

/**
 * @author recepd
 *
 */
public interface UserService {

	void save (User user);

	User findByUsername (String username);

	String findLoggedInUsername ();

	User findById (long id);

}
