package com.shepherd.securitypractice.basicauth.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	ROLE_USER,
	ROLE_ADMIN,
	ROLE_MOD,
	ROLE_FSK;

	@Override
	public String getAuthority() {
		return name();
	}

}
