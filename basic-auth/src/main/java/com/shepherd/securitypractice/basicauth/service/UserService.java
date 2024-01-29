package com.shepherd.securitypractice.basicauth.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shepherd.securitypractice.basicauth.dto.CreateUserRequest;
import com.shepherd.securitypractice.basicauth.models.User;
import com.shepherd.securitypractice.basicauth.repository.UserRepository;



/*
 * 
 * 
 accountNonExpired(true): Bu metot, kullanıcının hesap süresinin geçerli olup olmadığını kontrol eder. Eğer true olarak ayarlanmışsa, hesap süresi geçerli kabul edilir.

credentialsNonExpired(true): Bu metot, kullanıcının kimlik bilgilerinin (şifresi gibi) geçerli olup olmadığını kontrol eder. Eğer true olarak ayarlanmışsa, kimlik bilgileri geçerli kabul edilir.

isEnabled(true): Bu metot, kullanıcının hesabının etkin olup olmadığını kontrol eder. Eğer true olarak ayarlanmışsa, hesap etkin kabul edilir.

accountNonLocked(true): Bu metot, kullanıcının hesabının kilitli olup olmadığını kontrol eder. Eğer true olarak ayarlanmışsa, hesap kilitli değil kabul edilir.
 * */

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	private final BCryptPasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public User createUser(CreateUserRequest request) {
		
		User newUser = User.builder()
				.name(request.name())
				.username(request.username())
				.password(request.password())
				.accountNonExpired(true)
				.credentialsNonExpired(true)
				.isEnabled(true)
				.accountNonLocked(true)
				.build();
		
		return userRepository.save(newUser);
	}
	
	

}
