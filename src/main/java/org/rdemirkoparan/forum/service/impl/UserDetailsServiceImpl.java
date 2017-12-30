package org.rdemirkoparan.forum.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.rdemirkoparan.forum.model.User;
import org.rdemirkoparan.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author recepd
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

	/**
	 * set user role to admin and return
	 */
    @Override
    @Transactional(readOnly = true)
	public UserDetails loadUserByUsername (String username) {
        User user = userRepository.findByUsername(username);
		if (null == user) {
			throw new UsernameNotFoundException (username);
		}

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		// I didn't want to implement role mechanism. But it can be added in the future to ensure user rights 
		grantedAuthorities.add (new SimpleGrantedAuthority ("ADMIN"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
