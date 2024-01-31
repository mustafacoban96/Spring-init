package com.shepherd.securitypractice.jwttoken.dto;

import java.util.Set;

import com.shepherd.securitypractice.jwttoken.models.Role;

public record CreateUserRequest(
		String name,
		String username,
		String password,
		Set<Role> authorities
		
		) {

}
