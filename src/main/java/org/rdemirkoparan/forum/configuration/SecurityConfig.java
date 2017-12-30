package org.rdemirkoparan.forum.configuration;


import static org.rdemirkoparan.forum.util.GlobalConstants.HOME;
import static org.rdemirkoparan.forum.util.GlobalConstants.LOGIN;
import static org.rdemirkoparan.forum.util.GlobalConstants.LOGOUT;
import static org.rdemirkoparan.forum.util.GlobalConstants.RESOURCES;
import static org.rdemirkoparan.forum.util.GlobalConstants.ROOT;
import static org.rdemirkoparan.forum.util.GlobalConstants.SIGNUP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author recepd
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder () {
		return new BCryptPasswordEncoder ();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 * 
	 * 
	 */
	@Override
	protected void configure (HttpSecurity http) throws Exception {
        http.csrf ().disable ()
        .authorizeRequests()
			.antMatchers (ROOT, HOME, RESOURCES, SIGNUP).permitAll ()
            .anyRequest().authenticated()
            .and()
        .formLogin()
			.loginPage (LOGIN)
			.failureUrl ("/login?error=true")
            .permitAll()
            .and()
        .logout()
			.logoutUrl (LOGOUT)
            .permitAll()
			.logoutSuccessUrl (LOGIN);
	}

	@Autowired
	public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService (userDetailsService).passwordEncoder (bCryptPasswordEncoder ());
	}
}