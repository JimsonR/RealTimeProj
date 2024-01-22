package com.main.service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenValidationService {
	private final String SEC_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

	public String loadUserEmail(String token) {
		return extractClaims(token, Claims::getSubject);
	}

	private <T> T extractClaims(String token, Function<Claims, T> func) {
		Claims claim = extractAllClaims(token);
		return func.apply(claim);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SEC_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	private <T> T getTokenExpairyDate(String token, Function<Claims, T> func) {
		Claims claim = extractAllClaims(token);
		return func.apply(claim);
	}

	public boolean isTokenExpaired(String token) {
		Date tokenExpairyDate = getTokenExpairyDate(token, Claims::getExpiration);
		return tokenExpairyDate.before(new Date());
	}

}
