package com.pokemonreview.api.security;


import java.security.Key;
import java.util.Date;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTGenerator {

	private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	public String generateToken (Authentication authentication){
		
		String username = authentication.getName();
		Date currenDate = new Date();
		Date expireDate = new Date(currenDate.getTime() + SecurityConstants.JWT_EXPIRATION);
		
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(expireDate)
				.signWith(KEY, SignatureAlgorithm.HS512)
				.compact();
		System.out.println("New token :");
		System.out.println(token);
		return token;
	}
	
	public String getUsernameFromJWT(String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(KEY)
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(KEY)
			.build()
			.parseClaimsJws(token);
			return true;
		}
		catch (Exception e) {
			throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect",e.fillInStackTrace());
		}
	}
}
