package com.shepherd.securitypractice.basicauth;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shepherd.securitypractice.basicauth.dto.CreateUserRequest;
import com.shepherd.securitypractice.basicauth.models.Role;
import com.shepherd.securitypractice.basicauth.service.UserService;

@SpringBootApplication
public class BasicAuthApplication implements CommandLineRunner{
	
	
	private final UserService userService;

	public BasicAuthApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BasicAuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createDummyData();
		
	}
	
	private void createDummyData() {
		CreateUserRequest request1 = CreateUserRequest.builder()
				.name("Mustafa")
				.username("shepherd")
				.password("pass")
				.authorities(Set.of(Role.ROLE_USER))
				.build();
		userService.createUser(request1);
		
		CreateUserRequest request2 = CreateUserRequest.builder()
				.name("Admin")
				.username("admin")
				.password("pass")
				.authorities(Set.of(Role.ROLE_ADMIN))
				.build();
		userService.createUser(request2);
		
		CreateUserRequest request3 = CreateUserRequest.builder()
				.name("FSK")
				.username("fsk")
				.password("pass")
				.authorities(Set.of(Role.ROLE_FSK))
				.build();
		userService.createUser(request3);
	}

}
