package com.todolist.service;

import java.util.List;

import com.todolist.dto.UserDto;

public interface UserService {

	List<UserDto> getAllUsers();
	
}
