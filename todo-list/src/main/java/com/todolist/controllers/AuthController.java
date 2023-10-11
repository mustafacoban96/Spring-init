package com.todolist.controllers;

import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.todolist.dto.AuthResponseDto;
import com.todolist.dto.LoginDto;
import com.todolist.dto.RegisterDto;
import com.todolist.mapper.RegisterMapper;
import com.todolist.models.Role;
import com.todolist.models.UserEntity;
import com.todolist.repository.RoleRepository;
import com.todolist.repository.UserRepository;
import com.todolist.security.JWTGenerator;


@RestController
@RequestMapping("/todo/auth")
public class AuthController {
	
	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private JWTGenerator jwtGenerator;
	private PasswordEncoder passwordEncoder;
	
	
	public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
			RoleRepository roleRepository,JWTGenerator jwtGeneratoriGenerator,PasswordEncoder passwordEncoder ) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtGenerator = jwtGenerator;
	}
	
	@PostMapping("register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
		if(userRepository.existsByUsername(registerDto.getUsername())) {
			return new ResponseEntity<>("username is taken",HttpStatus.BAD_REQUEST);
		}
		if(userRepository.existsByEmail(registerDto.getEmail())) {
			return new ResponseEntity<>("email is taken",HttpStatus.BAD_REQUEST);
		}
		// i created mapper for encrypt.
		UserEntity user = RegisterMapper.mapToRegisterEntity(registerDto);
		
		Role roles = roleRepository.findByName("USER").get();
		user.setRoles(Collections.singletonList(roles));
		
		userRepository.save(user);
		return new ResponseEntity<>("User registered success!",HttpStatus.OK);
	}
	
	@PostMapping("login")
	public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
		Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtGenerator.generateToken(authentication);
		return new ResponseEntity<>(new AuthResponseDto(token),HttpStatus.OK);
	}
	
	

}
