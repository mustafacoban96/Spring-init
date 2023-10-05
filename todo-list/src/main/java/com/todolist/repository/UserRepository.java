package com.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
}
