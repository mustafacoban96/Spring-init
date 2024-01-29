package com.shpeherd.securitypractice.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class PrivateController {
	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("admin")
	public String privateAdmin() {
		return "Hello from private I am admin";
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("user")
	public String privateUser() {
		return "Hello from private I am user";
		
	}
	
}
