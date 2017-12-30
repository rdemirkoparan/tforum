package org.rdemirkoparan.forum.service.impl;

import java.time.LocalDateTime;

import org.rdemirkoparan.forum.model.User;
import org.rdemirkoparan.forum.repository.UserRepository;
import org.rdemirkoparan.forum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author recepd
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger (UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * Save user
	 */
    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setCreatedDate (LocalDateTime.now ());
        userRepository.save(user);
		logger.debug ("User saved! : ", user.getId ());
    }

	/**
	 * Find user with username
	 */
    @Override
    public User findByUsername(String username) {
		return userRepository.findByUsername (username);
    }

	/**
	 * Find logged in user's username
	 */
	@Override
	public String findLoggedInUsername () {
		Object userDetails = SecurityContextHolder.getContext ().getAuthentication ().getDetails ();
		if (userDetails instanceof UserDetails) {
			return ((UserDetails) userDetails).getUsername ();
		}
		return null;
	}

	/**
	 * Find user by user id
	 */
	@Override
	public User findById (long id) {
		return userRepository.findById (id);
	}
}
