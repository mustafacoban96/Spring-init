package com.shepherd.securitypractice.basicauth.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class PrivateController {

	

	@GetMapping("admin")
	public String privateAdmin() {
		return "Hello i am private admin";
	}
	
	
	
	@GetMapping("user")
	public String privateUser() {
		return "Hello i am private user";
	}
	
	
	@GetMapping("fsk")
	public String privateFsk() {
		return "hello i am private FSK";
	}
	
}
