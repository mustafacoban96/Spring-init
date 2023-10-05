package com.todolist.mapper;

import com.todolist.dto.UserDto;
import com.todolist.models.User;

public class UserMapper {
	
	
	public static UserDto mapToUserDto(User user) {
		UserDto userDto = UserDto.builder()
				.id(user.getId())
				.name(user.getName())
				.email(user.getEmail())
				.password(user.getPassword())
				.createdOn(user.getCreatedOn())
				.updatedOn(user.getUpdatedOn())
				.build();
		return userDto;
	}
	
	public static User user(UserDto userDto) {
		User user = User.builder()
				.id(userDto.getId())
				.name(userDto.getName())
				.email(userDto.getEmail())
				.password(userDto.getPassword())
				.createdOn(userDto.getCreatedOn())
				.updatedOn(userDto.getUpdatedOn())
				.build();
		return user;
	}
}
