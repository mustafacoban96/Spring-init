package com.shpeherd.securitypractice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class PrivateController {

	@GetMapping("admin")
	public String privateAdmin() {
		return "Hello from private I am admin";
	}
	
	@GetMapping("user")
	public String privateUser() {
		return "Hello from private I am user";
		
	}
	
}
