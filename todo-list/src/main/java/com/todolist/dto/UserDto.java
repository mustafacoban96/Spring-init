package com.todolist.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class UserDto {
	
	private Long id;
	private String name;
	private String password;
	private String email;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}
