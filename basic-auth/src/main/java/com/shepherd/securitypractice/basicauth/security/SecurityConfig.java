package com.shepherd.securitypractice.basicauth.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.shepherd.securitypractice.basicauth.models.Role;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
		
		MvcRequestMatcher.Builder mvcRequestBuilder = new MvcRequestMatcher.Builder(introspector);
		
		http
		.headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
		.csrf(csrf -> csrf.ignoringRequestMatchers(mvcRequestBuilder.pattern("/public/**"))
				.ignoringRequestMatchers(PathRequest.toH2Console())
				)
		.authorizeHttpRequests(x -> 
						x.requestMatchers(mvcRequestBuilder.pattern("/public/**")).permitAll()
						.requestMatchers(mvcRequestBuilder.pattern("/private/**")).hasAnyRole(Role.ROLE_USER.name(),Role.ROLE_ADMIN.name(),Role.ROLE_FSK.name())
						.requestMatchers(PathRequest.toH2Console()).hasAnyRole("ADMIN")
						.anyRequest().authenticated()
		)
		.formLogin(AbstractHttpConfigurer::disable)
		//.authorizeHttpRequests(x -> x.requestMatchers("/private/**").authenticated())
		//.authorizeHttpRequests(x -> x.anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
}
