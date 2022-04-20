package com.spring.security.model;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class AuthenticationRequest implements Serializable {

	private String username;
	private String password;

}
