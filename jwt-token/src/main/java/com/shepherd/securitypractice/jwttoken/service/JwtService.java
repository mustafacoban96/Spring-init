package com.shepherd.securitypractice.jwttoken.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	
	@Value("${jwt.key}")
	private String SECRET;
	
	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("can", "wia");
		return createToken(claims, userName);
		
		
	}
	
	
	private String createToken(Map<String, Object> claims,String userName) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis())) // generation date of token
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 2))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}
	
	
	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	// To validate for incoming token
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		
		String username = extractUsername(token);
		Date expiration = extractExpiration(token);
		
		return userDetails.getUsername().equals(username) && expiration.after(new Date());
	}
	
	
	
	private Date extractExpiration(String token) {
		Claims claims = Jwts
				.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJwt(token)
				.getBody();
		return claims.getExpiration();
	}
	
	public String extractUsername(String token) {
		Claims claims = Jwts
				.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJwt(token)
				.getBody();
		return claims.getSubject();
	}
	
	
	
	
	
	
	

}
