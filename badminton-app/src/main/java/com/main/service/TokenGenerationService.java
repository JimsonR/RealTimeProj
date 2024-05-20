package com.main.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.main.entity.Users;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenGenerationService {
	private  final String SEC_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
	
	public String generateToken(UserDetails user) {
		return Jwts.builder()
		 .addClaims(new HashMap<String, Object>())
		 .setSubject(user.getUsername())
		 .setIssuedAt(new Date(System.currentTimeMillis()))
//		 .setExpiration(new Date(System.currentTimeMillis() + 80 * 1000 * 24 ))
		 .signWith(getSigningKey(), SignatureAlgorithm.HS256)
		 .compact();
		 
	}

	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SEC_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
