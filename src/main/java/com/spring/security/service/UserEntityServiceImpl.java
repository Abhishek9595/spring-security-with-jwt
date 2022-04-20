package com.spring.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.security.dto.UserDto;
import com.spring.security.model.Role;
import com.spring.security.model.UserEntity;
import com.spring.security.repository.RoleRepository;
import com.spring.security.repository.UserRepository;

@Service
public class UserEntityServiceImpl implements UserEntityService {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserEntity registerUser(UserDto user) {
		String password = user.getPassword();
		user.setPassword(encoder.encode(password));
		List<Role> roleList = new ArrayList<>();
		Role role = new Role();
		role.setRole(user.getRoleName());
		roleList.add(role);
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(user.getUsername());
		userEntity.setPassword(user.getPassword());
		userEntity.setRoles(roleList);
		roleRepository.save(role);
		return userRepository.save(userEntity);
	}

	@Override
	public UserEntity findUser(String username) {
		return userRepository.findByUsername(username);
	}

}
