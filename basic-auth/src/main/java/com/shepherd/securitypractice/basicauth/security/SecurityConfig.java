package com.shepherd.securitypractice.basicauth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
		.headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(x -> 
						x.requestMatchers("/public/**").permitAll()
						.requestMatchers("/private/**").authenticated()
					
		)
		.formLogin(AbstractHttpConfigurer::disable)
		//.authorizeHttpRequests(x -> x.requestMatchers("/private/**").authenticated())
		//.authorizeHttpRequests(x -> x.anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
}
