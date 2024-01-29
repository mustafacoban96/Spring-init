package com.shpeherd.securitypractice.securtiy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService users() {
		UserDetails user = User.builder()
			.username("user")
			.password(passwordEncoder().encode("passwordUser"))
			.roles("USER")
			.build();
		UserDetails admin = User.builder()
			.username("admin")
			.password(passwordEncoder().encode("passwordAdmin"))
			.roles("ADMIN")
			.build();
		return new InMemoryUserDetailsManager(user, admin);
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
			.headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
			.csrf(csrf -> csrf.disable())
			.formLogin(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(x -> 
				x.requestMatchers("/public/**","auth/**").permitAll()
			)
			//.authorizeHttpRequests(x -> x.requestMatchers("/private/admin/**").hasRole("ADMIN"))
			//.authorizeHttpRequests(x -> x.requestMatchers("/private/user/**").hasRole("USER"))
			.authorizeHttpRequests(x -> x.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults());
		return http.build();
		
		
	}

}
