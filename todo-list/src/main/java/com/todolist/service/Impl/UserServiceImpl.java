package com.todolist.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.todolist.dto.UserDto;
import com.todolist.mapper.UserMapper;
import com.todolist.models.User;
import com.todolist.repository.UserRepository;
import com.todolist.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	
	

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}



	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map((user) -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
	}

}
