package com.shepherd.securitypractice.jwttoken.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shepherd.securitypractice.jwttoken.dto.AuthRequest;
import com.shepherd.securitypractice.jwttoken.dto.CreateUserRequest;
import com.shepherd.securitypractice.jwttoken.models.User;
import com.shepherd.securitypractice.jwttoken.service.JwtService;
import com.shepherd.securitypractice.jwttoken.service.UserService;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {
	
	private final UserService userService;
	
	private final JwtService jwtService;
	
	private final AuthenticationManager authenticationManager;

	public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
	
		this.userService = userService;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Hello world this is shepherd";
	}
	
	
	
	
	@PostMapping("/generateToken")
	public String generateToken(@RequestBody AuthRequest request) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(request.username());
		}
		log.info("invalid username " + request.username());
		throw new UsernameNotFoundException("Invalid username {}" + request.username());
	}
	
	
	@PostMapping("/addNewUser")
	public User addUser(@RequestBody CreateUserRequest request) {
		return userService.createUser(request);
	}
	
	
	@GetMapping("/user")
	public String getUserString() {
		return "this is user";
	}
	
	@GetMapping("/admin")
	public String getAdminString() {
		return "this is Admin";
	}
	
	
	
	
	
	
}
