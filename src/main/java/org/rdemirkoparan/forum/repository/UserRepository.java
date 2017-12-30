package org.rdemirkoparan.forum.repository;

import org.rdemirkoparan.forum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author recepd
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findById (Long id);

	User findByUsername (String username);

}
