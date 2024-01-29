package com.shepherd.securitypractice.basicauth.dto;

import java.util.Set;

import com.shepherd.securitypractice.basicauth.models.Role;

/*
 *https://ioflood.com/blog/java-record/#:~:text='Record'%20is%20a%20keyword%20introduced,carrier%20classes%20without%20boilerplate%20code. 
 * Using records can greatly simplify your code, but they also have their limitations. 
 * Records can’t extend other classes, and they’re immutable, meaning their state can’t be changed after they’re created. 
 * However, their simplicity and the automatic generation of methods like equals(), hashCode(), and toString() make them a powerful tool for many Java developers.*/
public record CreateUserRequest(
		String name,
		String username,
		String password,
		Set<Role> authorities

		){

}
