package com.spring.security.service;

import com.spring.security.dto.UserDto;
import com.spring.security.model.UserEntity;

public interface UserEntityService {

	UserEntity registerUser(UserDto user);

	UserEntity findUser(String username);

}
