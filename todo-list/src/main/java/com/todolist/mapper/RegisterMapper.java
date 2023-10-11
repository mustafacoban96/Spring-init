package com.todolist.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.todolist.dto.RegisterDto;
import com.todolist.models.UserEntity;

import lombok.Data;
import lombok.Getter;


@Component
public class RegisterMapper {
	
	@Autowired
	private static PasswordEncoder passwordEncoder;
	
	
	
	public static UserEntity mapToRegisterEntity(RegisterDto registerDto) {
		UserEntity user = UserEntity.builder()
				.username(registerDto.getUsername())
				.email(registerDto.getEmail())
				.password(passwordEncoder.encode(registerDto.getPassword()))
				.build();
		return user;
	}
}
