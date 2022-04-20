package com.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.dto.UserDto;
import com.spring.security.model.AuthenticationRequest;
import com.spring.security.model.UserEntity;
import com.spring.security.response.ResponseMessage;
import com.spring.security.service.CustomUserDetailsService;
import com.spring.security.service.UserEntityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(value = "/api/v1", tags = "Resource Controller")
public class ResourceController {

	@Autowired
	private CustomUserDetailsService customService;

	@Autowired
	private UserEntityService userService;

	@GetMapping("/user")
	@ApiOperation(value = "User Dashboard", notes = "User Page", tags = "Spring Security Application")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User Successfully logged in"),
			@ApiResponse(code = 404, message = "User Not Found"), @ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "User Not Permitted") })
	public ResponseEntity<ResponseMessage> userDashboard() {
		ResponseMessage message = new ResponseMessage(false, "Welcome to user dashboard", UserDetailsService.class);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/admin")
	@ApiOperation(value = "Admin Dashboard", notes = "Admin Page", tags = "Spring Security Application")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Admin Successfully logged in"),
			@ApiResponse(code = 404, message = "Admin Not Found"), @ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Admin Not Permitted") })
	public ResponseEntity<ResponseMessage> adminDashboard() {
		ResponseMessage message = new ResponseMessage(false, "Welcome to admin dashboard", null);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@PostMapping("/register")
	@ApiOperation(value = "Registration", notes = "Registration Page", tags = "Spring Security Application")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registration Successful"),
			@ApiResponse(code = 404, message = "Registration Unsuccessful"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Registration not Permitted") })
	public ResponseEntity<ResponseMessage> register(@RequestBody UserDto userDetails) {
		UserEntity user = userService.registerUser(userDetails);
		ResponseMessage message = new ResponseMessage(false, "User Registered Successfully", user);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@PostMapping("/authenticate")
	@ApiOperation(value = "Authentication", notes = "Login Page", tags = "Spring Security Application")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Authentication Successful"),
			@ApiResponse(code = 404, message = "Authentication Unsuccessful"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Authentication Not Permitted") })
	public ResponseEntity<ResponseMessage> authenticate(@RequestBody AuthenticationRequest request) throws Exception {
		String jwtToken = customService.authenticateUser(request);
		ResponseMessage message = new ResponseMessage(false, "Authenticated Successfully", jwtToken);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
