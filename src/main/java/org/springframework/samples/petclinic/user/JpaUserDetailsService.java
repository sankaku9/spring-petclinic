package org.springframework.samples.petclinic.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		logger.info("DB user select result: {}", user);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		logger.info("Authorities: {}", user.getAuthorities());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true, getAuthorities(user.getAuthorities()));
	}

	private List<GrantedAuthority> getAuthorities(Set<Authority> authorities) {
		List<GrantedAuthority> authList = new ArrayList<>();
		for (Authority authority : authorities) {
			authList.add(new SimpleGrantedAuthority(authority.getAuthority()));
		}
		return authList;
	}

}
