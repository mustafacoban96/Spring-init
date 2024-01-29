package com.shepherd.securitypractice.basicauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shepherd.securitypractice.basicauth.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String userName);
}
