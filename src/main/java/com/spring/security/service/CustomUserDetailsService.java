package com.spring.security.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.model.AuthenticationRequest;
import com.spring.security.model.Role;
import com.spring.security.model.UserEntity;
import com.spring.security.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDetails userDetails;

	@Autowired
	private JwtUtil jwtUtil;

	private String jwtToken;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username);
		List<Role> roleList = user.getRoles();
		List<SimpleGrantedAuthority> authorities = roleList.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
		return new User(user.getUsername(), user.getPassword(), authorities);
	}

	public String authenticateUser(AuthenticationRequest request) throws Exception {
		try {
			manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException exception) {
			throw new Exception("Incorrect Username or Password", exception);
		}
		userDetails = loadUserByUsername(request.getUsername());
		jwtToken = jwtUtil.generateToken(userDetails);
		return jwtToken;
	}

	@SuppressWarnings("serial")
	@Bean
	public UserDetails userDetails() {
		return new UserDetails() {

			@Override
			public boolean isEnabled() {
				return false;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return false;
			}

			@Override
			public boolean isAccountNonLocked() {
				return false;
			}

			@Override
			public boolean isAccountNonExpired() {
				return false;
			}

			@Override
			public String getUsername() {
				return null;
			}

			@Override
			public String getPassword() {
				return null;
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return null;
			}
		};
	}

}
