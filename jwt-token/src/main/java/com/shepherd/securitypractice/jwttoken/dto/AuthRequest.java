package com.shepherd.securitypractice.jwttoken.dto;

public record AuthRequest(
		
		String username,
		String password
		
		) {

}
