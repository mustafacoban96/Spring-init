package com.shepherd.securitypractice.jwttoken.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shepherd.securitypractice.jwttoken.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);

}
